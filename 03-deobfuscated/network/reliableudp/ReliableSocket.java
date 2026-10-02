/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.gameFramework.CrashHandler;
import com.corrodinggames.rts.game.UnitTypeCount;

import network.reliableudp.core.AckPacket;
import network.reliableudp.core.DataPacket;
import network.reliableudp.core.ExtendedAckPacket;
import network.reliableudp.core.FinPacket;
import network.reliableudp.core.NullPacket;
import network.reliableudp.core.ResetPacket;
import network.reliableudp.core.SynPacket;
import network.reliableudp.core.TaskRunner;
import network.reliableudp.SocketStats;
import network.reliableudp.SocketInitTask;
import network.reliableudp.SocketStartTask;
import network.reliableudp.NullPacketSender;
import network.reliableudp.ReliableSocketThread;
import network.reliableudp.PacketSender;
import network.reliableudp.ReliableInputStream;
import network.reliableudp.SocketLifecycle;
import network.reliableudp.ReliableOutputStream;
import network.reliableudp.ReliableProfile;
import network.reliableudp.ConnectionEventHandler;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ReliableSocket
extends Socket {
    protected DatagramSocket udpSocket;
    protected SocketAddress remoteAddress;
    protected ReliableInputStream inputStream;
    protected ReliableOutputStream outputStream;
    private byte[] receiveBuffer;
    private boolean connected = false;
    private boolean inputShutdown = false;
    private boolean outputShutdown = false;
    private boolean autoStart = true;
    private int receiveWindowSize = 0;
    private int sendWindowSize = 0;
    private boolean closed = false;
    private boolean closing = false;
    private int soTimeout = -1;
    private Object receiveLock = new Object();
    private Object sendLock = new Object();
    private ArrayList pendingConnections = new ArrayList();
    private ArrayList activeConnections = new ArrayList();
    protected ReliableProfile profile = network.reliableudp.ReliableProfile.a;
    private ArrayList receiveQueue = new ArrayList();
    private ArrayList sendQueue = new ArrayList();
    private ArrayList eventListeners = new ArrayList();
    private Object stateLock = new Object();
    private SocketStats taskRunner = new SocketStats();
    private Thread receiverThread;
    private int maxRetransmissions = 32;
    private int maxCumulativeAcks = 32;
    private int receiveQueueCapacity;
    private int sendQueueCapacity;
    public boolean debugEnabled = false;
    private TaskRunner nullSegmentTimer = new TaskRunner("rudp-NullSegmentTimer", new NullPacketSender(this, null));
    private TaskRunner retransmissionTimer = new TaskRunner("rudp-RetransmissionTimer", new PacketSender(this, null));
    private TaskRunner cumulativeAckTimer = new TaskRunner("rudp-CumulativeAckTimer", new SocketInitTask(this, null));
    private TaskRunner keepAliveTimer = new TaskRunner("rudp-KeepAliveTimer", new SocketStartTask(this, null));
    private static final boolean globalDebugFlag = Boolean.getBoolean("net.rudp.debug");

    public ReliableSocket() throws SocketException {
        this(new ReliableProfile());
    }

    public ReliableSocket(ReliableProfile r2) throws SocketException {
        this(new DatagramSocket(), r2);
    }

    protected ReliableSocket(DatagramSocket datagramSocket) {
        this(datagramSocket, new ReliableProfile());
    }

    protected ReliableSocket(DatagramSocket datagramSocket, ReliableProfile r2) {
        if (datagramSocket == null) {
            throw new NullPointerException("sock");
        }
        this.a(datagramSocket, r2);
    }

    protected void a(DatagramSocket datagramSocket, ReliableProfile r2) {
        this.udpSocket = datagramSocket;
        this.profile = r2;
        this.receiveQueueCapacity = (this.profile.a() - 6) * 32;
        this.sendQueueCapacity = (this.profile.a() - 6) * 32;
        if (this.receiverThread == null) {
            this.receiverThread = new ReliableSocketThread(this);
            this.receiverThread.start();
        }
    }

    @Override
    public void bind(SocketAddress socketAddress) throws SocketException {
        this.udpSocket.bind(socketAddress);
    }

    @Override
    public void connect(SocketAddress socketAddress) throws SocketException, SocketTimeoutException, IOException {
        this.connect(socketAddress, 0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void connect(SocketAddress socketAddress, int n2) throws SocketException, SocketTimeoutException, IOException {
        if (socketAddress == null) {
            throw new IllegalArgumentException("connect: The address can't be null");
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("connect: timeout can't be negative");
        }
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (this.isConnected()) {
            throw new SocketException("already connected");
        }
        if (!(socketAddress instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Unsupported address type");
        }
        this.remoteAddress = (InetSocketAddress)socketAddress;
        this.getOutputStream();
        this.receiveWindowSize = 2;
        Random random = new Random(System.currentTimeMillis());
        SynPacket g2 = new SynPacket(this.taskRunner.a(random.nextInt(255)), this.profile.b(), this.profile.a(), this.profile.h(), this.profile.i(), this.profile.g(), this.profile.c(), this.profile.d(), this.profile.e(), this.profile.f());
        this.e(g2);
        boolean bl = false;
        Object object = this;
        synchronized (object) {
            if (!this.isConnected()) {
                try {
                    if (n2 == 0) {
                        this.wait();
                    } else {
                        long l2 = System.currentTimeMillis();
                        this.wait(n2);
                        if (System.currentTimeMillis() - l2 >= (long)n2) {
                            bl = true;
                        }
                    }
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
        if (this.receiveWindowSize == 3) {
            return;
        }
        object = this.receiveQueue;
        synchronized (object) {
            this.receiveQueue.clear();
            this.receiveQueue.notifyAll();
        }
        this.taskRunner.l();
        this.retransmissionTimer.e();
        switch (this.receiveWindowSize) {
            case 2: {
                this.k();
                this.receiveWindowSize = 0;
                if (bl) {
                    throw new SocketTimeoutException();
                }
                throw new SocketException("Connection refused");
            }
            case 0: 
            case 4: {
                this.receiveWindowSize = 0;
                throw new SocketException("Socket closed");
            }
        }
    }

    @Override
    public SocketChannel getChannel() {
        return null;
    }

    @Override
    public InetAddress getInetAddress() {
        if (!this.isConnected()) {
            return null;
        }
        return ((InetSocketAddress)this.remoteAddress).getAddress();
    }

    @Override
    public int getPort() {
        if (!this.isConnected()) {
            return 0;
        }
        return ((InetSocketAddress)this.remoteAddress).getPort();
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        if (!this.isConnected()) {
            return null;
        }
        return new InetSocketAddress(this.getInetAddress(), this.getPort());
    }

    public SocketAddress c() {
        return this.remoteAddress;
    }

    @Override
    public InetAddress getLocalAddress() {
        return this.udpSocket.getLocalAddress();
    }

    @Override
    public int getLocalPort() {
        return this.udpSocket.getLocalPort();
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        return this.udpSocket.getLocalSocketAddress();
    }

    @Override
    public synchronized InputStream getInputStream() throws IOException, SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isInputShutdown()) {
            throw new SocketException("Socket input is shutdown");
        }
        if (this.inputStream == null) {
            this.inputStream = new ReliableInputStream(this);
        }
        return this.inputStream;
    }

    @Override
    public synchronized OutputStream getOutputStream() throws IOException, SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isOutputShutdown()) {
            throw new SocketException("Socket output is shutdown");
        }
        if (this.outputStream == null) {
            this.outputStream = new ReliableOutputStream(this);
        }
        return this.outputStream;
    }

    public void d() {
        this.connected = true;
        this.receiveWindowSize = 0;
        this.udpSocket.close();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void close() throws IOException {
        Object object = this.receiveLock;
        synchronized (object) {
            Object object2;
            if (this.isClosed()) {
                return;
            }
            this.g();
            switch (this.receiveWindowSize) {
                case 2: {
                    object2 = this;
                    synchronized (object2) {
                        this.notify();
                        break;
                    }
                }
                case 1: 
                case 3: 
                case 4: {
                    this.a(new FinPacket(this.taskRunner.a()));
                    this.e();
                    break;
                }
                case 0: {
                    this.udpSocket.close();
                }
            }
            if (this.receiveWindowSize != 0) {
                this.soTimeout = this.receiveWindowSize;
            }
            this.connected = true;
            this.receiveWindowSize = 0;
            this.l();
            object2 = this.receiveQueue;
            synchronized (object2) {
                this.receiveQueue.notify();
            }
            object2 = this.eventListeners;
            synchronized (object2) {
                this.eventListeners.notify();
            }
        }
    }

    @Override
    public boolean isBound() {
        return this.udpSocket.isBound();
    }

    @Override
    public boolean isConnected() {
        return this.inputShutdown;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isClosed() {
        Object object = this.receiveLock;
        synchronized (object) {
            return this.connected;
        }
    }

    @Override
    public void setSoTimeout(int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        this.sendWindowSize = n2;
    }

    @Override
    public synchronized void setSendBufferSize(int n2) throws SocketException {
        if (n2 <= 0) {
            throw new IllegalArgumentException("negative receive size");
        }
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (this.isConnected()) {
            return;
        }
        this.receiveQueueCapacity = n2;
    }

    @Override
    public synchronized int getSendBufferSize() throws SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        return this.receiveQueueCapacity;
    }

    @Override
    public synchronized void setReceiveBufferSize(int n2) throws SocketException {
        if (n2 <= 0) {
            throw new IllegalArgumentException("negative send size");
        }
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (this.isConnected()) {
            return;
        }
        this.sendQueueCapacity = n2;
    }

    @Override
    public synchronized int getReceiveBufferSize() throws SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        return this.sendQueueCapacity;
    }

    @Override
    public void setTcpNoDelay(boolean bl) {
    }

    @Override
    public boolean getTcpNoDelay() {
        return false;
    }

    @Override
    public synchronized void setKeepAlive(boolean bl) throws SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!(this.autoStart ^ bl)) {
            return;
        }
        this.autoStart = bl;
        if (this.isConnected()) {
            if (this.autoStart) {
                this.keepAliveTimer.a(this.profile.g() * 6, this.profile.g() * 6);
            } else {
                this.keepAliveTimer.e();
            }
        }
    }

    @Override
    public synchronized boolean getKeepAlive() throws SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        return this.autoStart;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void shutdownInput() throws IOException, SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isInputShutdown()) {
            throw new SocketException("Socket input is already shutdown");
        }
        this.closed = true;
        Object object = this.stateLock;
        synchronized (object) {
            this.stateLock.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void shutdownOutput() throws IOException, SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isOutputShutdown()) {
            throw new SocketException("Socket output is already shutdown");
        }
        this.closing = true;
        ArrayList arrayList = this.receiveQueue;
        synchronized (arrayList) {
            this.receiveQueue.notifyAll();
        }
    }

    @Override
    public boolean isInputShutdown() {
        return this.closed;
    }

    @Override
    public boolean isOutputShutdown() {
        return this.closing;
    }

    protected void a(byte[] byArray, int n2, int n3) throws IOException, SocketException {
        this.a(byArray, n2, n3, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(byte[] byArray, int n2, int n3, boolean bl) throws IOException, SocketException {
        int n4;
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (this.isOutputShutdown()) {
            throw new IOException("Socket output is shutdown");
        }
        if (!this.isConnected()) {
            throw new SocketException("Connection reset");
        }
        for (int i2 = 0; i2 < n3; i2 += n4) {
            Object object = this.sendLock;
            synchronized (object) {
                while (this.outputShutdown) {
                    try {
                        this.sendLock.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                n4 = Math.min(this.profile.a() - 6, n3 - i2);
                DataPacket b2 = new DataPacket(this.taskRunner.a(), this.taskRunner.b(), byArray, n2 + i2, n4);
                this.e(b2);
                if (bl) {
                    this.a(b2);
                }
                continue;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected int b(byte[] var1_1, int var2_2, int var3_3) throws IOException, SocketException, EOFException, SocketTimeoutException {
        // 02b a/a/h.java L485-557 直译: 接收数据 (eventListeners 队列 + 等待窗口)
        int var4_4 = 0;
        Object var5_5 = this.stateLock;
        synchronized (var5_5) {
            while (true) {
                while (!this.eventListeners.isEmpty()) {
                    Iterator var6_8 = this.eventListeners.iterator();
                    while (var6_8.hasNext()) {
                        network.reliableudp.core.Packet var7_9 = (network.reliableudp.core.Packet) var6_8.next();
                        if (var7_9 instanceof network.reliableudp.core.ResetPacket) {
                            var6_8.remove();
                            break;
                        }
                        if (var7_9 instanceof network.reliableudp.core.FinPacket) {
                            if (var4_4 <= 0) {
                                var6_8.remove();
                                return -1;
                            }
                            break;
                        }
                        if (!(var7_9 instanceof network.reliableudp.core.DataPacket)) continue;
                        byte[] var8_10 = ((network.reliableudp.core.DataPacket) var7_9).c();
                        if (var8_10.length + var4_4 > var3_3) {
                            if (var4_4 <= 0) {
                                throw new IOException("insufficient buffer space");
                            }
                            break;
                        }
                        System.arraycopy(var8_10, 0, var1_1, var2_2 + var4_4, var8_10.length);
                        var4_4 += var8_10.length;
                        var6_8.remove();
                    }
                    if (var4_4 > 0) {
                        return var4_4;
                    }
                }
                if (this.isClosed()) {
                    throw new SocketException("Socket is closed");
                }
                if (this.isInputShutdown()) {
                    throw new EOFException();
                }
                if (!this.isConnected()) {
                    throw new SocketException("Connection reset");
                }
                try {
                    if (this.sendWindowSize == 0) {
                        this.stateLock.wait();
                    } else {
                        long var12 = System.currentTimeMillis();
                        this.stateLock.wait((long) this.sendWindowSize);
                        if (System.currentTimeMillis() - var12 >= (long) this.sendWindowSize) {
                            throw new SocketTimeoutException();
                        }
                    }
                } catch (InterruptedException var10) {
                    if (ReliableSocket.globalDebugFlag) {
                        var10.printStackTrace();
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(ConnectionEventHandler s2) {
        if (s2 == null) {
            throw new NullPointerException("stateListener");
        }
        ArrayList arrayList = this.activeConnections;
        synchronized (arrayList) {
            if (!this.activeConnections.contains(s2)) {
                this.activeConnections.add(s2);
            }
        }
    }

    private void a(network.reliableudp.core.Packet h2) throws IOException {
        if (h2 instanceof DataPacket || h2 instanceof ResetPacket || h2 instanceof FinPacket || h2 instanceof NullPacket) {
            this.h(h2);
        }
        if (h2 instanceof DataPacket || h2 instanceof ResetPacket || h2 instanceof FinPacket) {
            this.nullSegmentTimer.d();
        }
        if (ReliableSocket.globalDebugFlag) {
            this.a("sent " + h2);
        }
        this.d(h2);
    }

    private network.reliableudp.core.Packet i() {
        network.reliableudp.core.Packet h2 = this.a();
        if (h2 != null) {
            if (ReliableSocket.globalDebugFlag) {
                this.a("recv " + h2);
            }
            if (h2 instanceof DataPacket || h2 instanceof NullPacket || h2 instanceof ResetPacket || h2 instanceof FinPacket || h2 instanceof SynPacket) {
                this.taskRunner.c();
            }
            if (this.autoStart) {
                this.keepAliveTimer.d();
            }
        }
        return h2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void e(network.reliableudp.core.Packet h2) throws SocketException, IOException {
        Object object = this.receiveQueue;
        synchronized (object) {
            while (this.receiveQueue.size() >= this.maxRetransmissions || this.taskRunner.j() > this.profile.b()) {
                if (this.connected) {
                    throw new SocketException("Socket is closed");
                }
                try {
                    this.receiveQueue.wait(10000L);
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            this.taskRunner.i();
            this.receiveQueue.add(h2);
        }
        if (this.connected) {
            throw new SocketException("Socket is closed");
        }
        if (!(h2 instanceof ExtendedAckPacket) && !(h2 instanceof AckPacket)) {
            object = this.retransmissionTimer;
            synchronized (object) {
                if (this.retransmissionTimer.c()) {
                    this.retransmissionTimer.a(this.profile.h(), this.profile.h());
                }
            }
        }
        this.a(h2);
        if (h2 instanceof DataPacket) {
            object = this.pendingConnections;
            synchronized (object) {
                for (SocketLifecycle p2 : (java.util.Collection<SocketLifecycle>) (java.util.Collection) this.pendingConnections) {
                    p2.a();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void f(network.reliableudp.core.Packet h2) throws IOException {
        if (this.profile.c() > 0) {
            h2.b(h2.o() + 1);
        }
        if (this.profile.c() != 0 && h2.o() > this.profile.c()) {
            this.m();
            return;
        }
        this.a(h2);
        if (h2 instanceof DataPacket) {
            ArrayList arrayList = this.pendingConnections;
            synchronized (arrayList) {
                for (SocketLifecycle p2 : (java.util.Collection<SocketLifecycle>) (java.util.Collection) this.pendingConnections) {
                    p2.b();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void j() throws IOException {
        if (this.isConnected()) {
            this.nullSegmentTimer.e();
            if (this.autoStart) {
                this.keepAliveTimer.e();
            }
            Object object = this.sendLock;
            synchronized (object) {
                this.outputShutdown = false;
                this.sendLock.notify();
            }
        }
        Object object = this;
        synchronized (object) {
            this.getOutputStream();
            this.inputShutdown = true;
            this.receiveWindowSize = 3;
            this.notify();
        }
        object = this.activeConnections;
        synchronized (object) {
            for (ConnectionEventHandler s2 : (java.util.Collection<ConnectionEventHandler>) (java.util.Collection) this.activeConnections) {
                s2.a(this);
            }
        }
        this.nullSegmentTimer.a(0L, this.profile.g());
        if (this.autoStart) {
            this.keepAliveTimer.a(this.profile.g() * 6, this.profile.g() * 6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void k() {
        ArrayList arrayList = this.activeConnections;
        synchronized (arrayList) {
            for (ConnectionEventHandler s2 : (java.util.Collection<ConnectionEventHandler>) (java.util.Collection) this.activeConnections) {
                s2.b(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void l() {
        ArrayList arrayList = this.activeConnections;
        synchronized (arrayList) {
            for (ConnectionEventHandler s2 : (java.util.Collection<ConnectionEventHandler>) (java.util.Collection) this.activeConnections) {
                s2.c(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void m() {
        Object object = this.receiveLock;
        synchronized (object) {
            if (this.isClosed()) {
                return;
            }
            switch (this.receiveWindowSize) {
                case 2: {
                    Object iterator = this;
                    synchronized (iterator) {
                        this.notify();
                        break;
                    }
                }
                case 1: 
                case 3: 
                case 4: {
                    this.inputShutdown = false;
                    Object iterator = this.receiveQueue;
                    synchronized (iterator) {
                        this.receiveQueue.notifyAll();
                    }
                    iterator = this.stateLock;
                    synchronized (iterator) {
                        this.stateLock.notify();
                    }
                    this.e();
                }
            }
            this.receiveWindowSize = 0;
            this.connected = true;
        }
        object = this.activeConnections;
        synchronized (object) {
            for (ConnectionEventHandler s2 : (java.util.Collection<ConnectionEventHandler>) (java.util.Collection) this.activeConnections) {
                s2.d(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n() {
        ArrayList arrayList = this.activeConnections;
        synchronized (arrayList) {
            for (ConnectionEventHandler s2 : (java.util.Collection<ConnectionEventHandler>) (java.util.Collection) this.activeConnections) {
                s2.e(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void a(SynPacket g2) throws SocketException, IOException {
        switch (this.receiveWindowSize) {
            case 0: {
                this.receiveWindowSize = 1;
                this.profile = new ReliableProfile(this.maxRetransmissions, this.maxCumulativeAcks, g2.e(), g2.c(), g2.i(), g2.j(), g2.k(), g2.l(), g2.h(), g2.f(), g2.g());
                this.taskRunner.b(g2.m());
                Random random = new Random(System.currentTimeMillis());
                SynPacket g3 = new SynPacket(this.taskRunner.a(random.nextInt(255)), this.profile.b(), this.profile.a(), this.profile.h(), this.profile.i(), this.profile.g(), this.profile.c(), this.profile.d(), this.profile.e(), this.profile.f());
                g3.a(g2.m());
                this.e(g3);
                break;
            }
            case 1: {
                ArrayList arrayList = this.receiveQueue;
                synchronized (arrayList) {
                    for (network.reliableudp.core.Packet h2 : (java.util.Collection<network.reliableudp.core.Packet>) (java.util.Collection) this.receiveQueue) {
                        try {
                            this.f(h2);
                        }
                        catch (IOException iOException) {
                            iOException.printStackTrace();
                        }
                    }
                    break;
                }
            }
            case 2: {
                this.taskRunner.b(g2.m());
                this.receiveWindowSize = 3;
                this.o();
                this.j();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(ExtendedAckPacket c2) {
        int[] nArray = c2.c();
        int n2 = c2.n();
        int n3 = nArray[nArray.length - 1];
        ArrayList arrayList = this.receiveQueue;
        synchronized (arrayList) {
            Iterator iterator = this.receiveQueue.iterator();
            block5: while (iterator.hasNext()) {
                network.reliableudp.core.Packet h2 = (network.reliableudp.core.Packet)iterator.next();
                if (this.a(h2.m(), n2) <= 0) {
                    iterator.remove();
                    continue;
                }
                for (int i2 = 0; i2 < nArray.length; ++i2) {
                    if (this.a(h2.m(), nArray[i2]) != 0) continue;
                    iterator.remove();
                    continue block5;
                }
            }
            for (network.reliableudp.core.Packet h2 : (java.util.Collection<network.reliableudp.core.Packet>) (java.util.Collection) this.receiveQueue) {
                if (this.a(n2, h2.m()) >= 0 || this.a(n3, h2.m()) <= 0) continue;
                try {
                    this.f(h2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
            this.receiveQueue.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void g(network.reliableudp.core.Packet h2) {
        Object object;
        if (h2 instanceof ResetPacket) {
            object = this.sendLock;
            synchronized (object) {
                this.outputShutdown = true;
            }
            this.n();
        }
        if (h2 instanceof FinPacket) {
            switch (this.receiveWindowSize) {
                case 2: {
                    object = this;
                    synchronized (object) {
                        this.notify();
                        break;
                    }
                }
                case 0: {
                    break;
                }
                default: {
                    this.receiveWindowSize = 4;
                }
            }
        }
        boolean bl = false;
        Object object2 = this.stateLock;
        synchronized (object2) {
            if (this.a(h2.m(), this.taskRunner.b()) > 0) {
                if (this.a(h2.m(), ReliableSocket.b(this.taskRunner.b())) == 0) {
                    bl = true;
                    if (this.eventListeners.size() == 0 || this.eventListeners.size() + this.sendQueue.size() < this.maxCumulativeAcks) {
                        this.taskRunner.b(h2.m());
                        if (h2 instanceof DataPacket || h2 instanceof ResetPacket || h2 instanceof FinPacket) {
                            this.eventListeners.add(h2);
                        }
                        if (h2 instanceof DataPacket) {
                            ArrayList arrayList = this.pendingConnections;
                            synchronized (arrayList) {
                                for (SocketLifecycle p2 : (java.util.Collection<SocketLifecycle>) (java.util.Collection) this.pendingConnections) {
                                    p2.c();
                                }
                            }
                        }
                        this.r();
                    }
                } else if (this.eventListeners.size() + this.sendQueue.size() < this.maxCumulativeAcks) {
                    boolean bl2 = false;
                    for (int i2 = 0; i2 < this.sendQueue.size() && !bl2; ++i2) {
                        network.reliableudp.core.Packet h3 = (network.reliableudp.core.Packet)this.sendQueue.get(i2);
                        int n2 = this.a(h2.m(), h3.m());
                        if (n2 == 0) {
                            bl2 = true;
                            continue;
                        }
                        if (n2 >= 0) continue;
                        this.sendQueue.add(i2, h2);
                        bl2 = true;
                    }
                    if (!bl2) {
                        this.sendQueue.add(h2);
                    }
                    this.taskRunner.f();
                    if (h2 instanceof DataPacket) {
                        ArrayList arrayList = this.pendingConnections;
                        synchronized (arrayList) {
                            for (SocketLifecycle p3 : (java.util.Collection<SocketLifecycle>) (java.util.Collection) this.pendingConnections) {
                                p3.d();
                            }
                        }
                    }
                }
            }
            if (bl && (h2 instanceof ResetPacket || h2 instanceof NullPacket || h2 instanceof FinPacket)) {
                this.o();
            } else if (this.taskRunner.g() > 0 && (this.profile.e() == 0 || this.taskRunner.g() > this.profile.e())) {
                this.p();
            } else if (this.taskRunner.d() > 0 && (this.profile.d() == 0 || this.taskRunner.d() > this.profile.d())) {
                this.q();
            } else {
                network.reliableudp.core.TaskRunner i3 = this.cumulativeAckTimer;
                synchronized (i3) {
                    if (this.cumulativeAckTimer.c()) {
                        this.cumulativeAckTimer.a(this.profile.i());
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void o() {
        Object object = this.stateLock;
        synchronized (object) {
            if (!this.sendQueue.isEmpty()) {
                this.p();
                return;
            }
            this.q();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void p() {
        Object object = this.stateLock;
        synchronized (object) {
            int n2;
            if (this.sendQueue.isEmpty()) {
                return;
            }
            this.taskRunner.e();
            this.taskRunner.h();
            int[] nArray = new int[this.sendQueue.size()];
            for (n2 = 0; n2 < nArray.length; ++n2) {
                network.reliableudp.core.Packet h2 = (network.reliableudp.core.Packet)this.sendQueue.get(n2);
                nArray[n2] = h2.m();
            }
            try {
                n2 = this.taskRunner.b();
                this.a((network.reliableudp.core.Packet)new ExtendedAckPacket(ReliableSocket.b(n2), n2, nArray));
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    private void q() {
        if (this.taskRunner.e() == 0) {
            return;
        }
        try {
            int n2 = this.taskRunner.b();
            this.a(new AckPacket(ReliableSocket.b(n2), n2));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void h(network.reliableudp.core.Packet h2) {
        if (this.taskRunner.e() == 0) {
            return;
        }
        h2.a(this.taskRunner.b());
    }

    protected boolean b(network.reliableudp.core.Packet h2) throws IOException {
        int n2 = h2.n();
        if (n2 < 0) {
            return false;
        }
        for (network.reliableudp.core.Packet h3 : (java.util.Collection<network.reliableudp.core.Packet>) (java.util.Collection) this.receiveQueue) {
            if (this.a(h3.m(), n2) > 0) continue;
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void c(network.reliableudp.core.Packet h2) throws IOException {
        int n2 = h2.n();
        if (n2 < 0) {
            return;
        }
        this.taskRunner.k();
        ArrayList arrayList = this.receiveQueue;
        synchronized (arrayList) {
            Iterator iterator = this.receiveQueue.iterator();
            while (iterator.hasNext()) {
                network.reliableudp.core.Packet h3 = (network.reliableudp.core.Packet)iterator.next();
                if (this.a(h3.m(), n2) > 0) continue;
                iterator.remove();
            }
            if (this.receiveWindowSize == 1) {
                boolean bl = false;
                if (!this.receiveQueue.isEmpty()) {
                    for (network.reliableudp.core.Packet h4 : (java.util.Collection<network.reliableudp.core.Packet>) (java.util.Collection) this.receiveQueue) {
                        if (!(h4 instanceof SynPacket)) continue;
                        bl = true;
                    }
                }
                if (bl) {
                    this.a("Bad first ack: " + n2);
                    return;
                }
                this.receiveWindowSize = 3;
                this.j();
            }
            if (this.receiveQueue.isEmpty()) {
                this.retransmissionTimer.e();
            }
            this.receiveQueue.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void r() {
        Object object = this.stateLock;
        synchronized (object) {
            Iterator iterator = this.sendQueue.iterator();
            while (iterator.hasNext()) {
                network.reliableudp.core.Packet h2 = (network.reliableudp.core.Packet)iterator.next();
                if (this.a(h2.m(), ReliableSocket.b(this.taskRunner.b())) != 0) continue;
                this.taskRunner.b(h2.m());
                if (h2 instanceof DataPacket || h2 instanceof ResetPacket || h2 instanceof FinPacket) {
                    this.eventListeners.add(h2);
                }
                iterator.remove();
            }
            this.stateLock.notify();
        }
    }

    protected void d(network.reliableudp.core.Packet h2) {
        block2: {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(h2.d(), h2.b(), this.remoteAddress);
                this.udpSocket.send(datagramPacket);
            }
            catch (IOException iOException) {
                if (this.isClosed()) break block2;
                iOException.printStackTrace();
            }
        }
    }

    protected network.reliableudp.core.Packet a() {
        try {
            if (this.receiveBuffer == null) {
                this.receiveBuffer = new byte[65535];
            }
            DatagramPacket datagramPacket = new DatagramPacket(this.receiveBuffer, this.receiveBuffer.length);
            this.udpSocket.receive(datagramPacket);
            return network.reliableudp.core.Packet.b(datagramPacket.getData(), 0, datagramPacket.getLength());
        }
        catch (IOException iOException) {
            if (!this.isClosed()) {
                iOException.printStackTrace();
            }
            return null;
        }
    }

    protected void b() {
        this.udpSocket.close();
    }

    protected void e() {
        this.nullSegmentTimer.e();
        this.keepAliveTimer.e();
        this.receiveWindowSize = 4;
        ReliableSocket$1 h$1 = new ReliableSocket$1(this);
        h$1.setName("ReliableSocket-Closing");
        h$1.setDaemon(true);
        h$1.start();
    }

    protected synchronized void a(String string) {
        System.out.println(this.getLocalPort() + ": " + string);
    }

    private static int b(int n2) {
        return (n2 + 1) % 255;
    }

    private int a(int n2, int n3) {
        if (n2 == n3) {
            return 0;
        }
        if (n2 < n3 && n3 - n2 > 127 || n2 > n3 && n2 - n3 < 127) {
            return 1;
        }
        return -1;
    }

    public synchronized void f() {
        if (!this.debugEnabled) {
            this.debugEnabled = true;
            this.nullSegmentTimer.a();
            this.retransmissionTimer.a();
            this.cumulativeAckTimer.a();
            this.keepAliveTimer.a();
        }
    }

    public synchronized void g() {
        if (this.debugEnabled) {
            this.debugEnabled = false;
            this.retransmissionTimer.f();
            this.cumulativeAckTimer.f();
            this.keepAliveTimer.f();
            this.nullSegmentTimer.f();
        }
    }

    static /* synthetic */ network.reliableudp.core.TaskRunner a(ReliableSocket h2) {
        return h2.keepAliveTimer;
    }

    static /* synthetic */ network.reliableudp.core.TaskRunner b(ReliableSocket h2) {
        return h2.nullSegmentTimer;
    }

    static /* synthetic */ network.reliableudp.core.TaskRunner c(ReliableSocket h2) {
        return h2.retransmissionTimer;
    }

    static /* synthetic */ network.reliableudp.core.TaskRunner d(ReliableSocket h2) {
        return h2.cumulativeAckTimer;
    }

    static /* synthetic */ void e(ReliableSocket h2) {
        h2.l();
    }

    static /* synthetic */ int a(int n) {
        return ReliableSocket.b(n);
    }

    static /* synthetic */ network.reliableudp.core.Packet f(ReliableSocket h2) {
        return h2.i();
    }

    static /* synthetic */ void a(ReliableSocket h2, ExtendedAckPacket c2) {
        h2.a(c2);
    }

    static /* synthetic */ void a(ReliableSocket h2, network.reliableudp.core.Packet h3) {
        h2.g(h3);
    }

    static /* synthetic */ ArrayList g(ReliableSocket h2) {
        return h2.receiveQueue;
    }

    static /* synthetic */ SocketStats h(ReliableSocket h2) {
        return h2.taskRunner;
    }

    static /* synthetic */ void b(ReliableSocket h2, network.reliableudp.core.Packet h3) throws SocketException, IOException {
        h2.e(h3);
    }

    static /* synthetic */ boolean h() {
        return globalDebugFlag;
    }

    static /* synthetic */ void c(ReliableSocket h2, network.reliableudp.core.Packet h3) throws IOException {
        h2.f(h3);
    }

    static /* synthetic */ void i(ReliableSocket h2) {
        h2.o();
    }

    static /* synthetic */ void j(ReliableSocket h2) {
        h2.m();
    }
}
