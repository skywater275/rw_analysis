/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.NetworkPlayer;
import com.corrodinggames.rts.game.HumanPlayer;

import network.reliableudp.AddressFilter;
import network.reliableudp.ReliableServerThread;
import network.reliableudp.ReliableClientSocket;
import network.reliableudp.ServerConnectionListener;
import network.reliableudp.ConnectionEventHandler;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReliableServerSocket
extends ServerSocket {
    AddressFilter udpSocket;
    private DatagramSocket acceptQueue;
    private int bound;
    private int closed;
    private boolean acceptThread;
    private ArrayList acceptLock;
    private HashMap backlog;
    private HashMap j;
    private HashMap k;
    long profile;
    int localPort;
    private ConnectionEventHandler l;

    public ReliableServerSocket() throws SocketException, IOException {
        this(new DatagramSocket(null), 0);
    }

    public ReliableServerSocket(int n, int n2, InetAddress inetAddress, boolean bl) throws SocketException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket(null);
        datagramSocket.setReuseAddress(bl);
        datagramSocket.bind(new InetSocketAddress(inetAddress, n));
        this.a(datagramSocket, n2);
    }

    public ReliableServerSocket(DatagramSocket datagramSocket, int n) throws SocketException, IOException {
        this.a(datagramSocket, n);
    }

    public void a(DatagramSocket datagramSocket, int n) {
        if (datagramSocket == null) {
            throw new NullPointerException("sock");
        }
        this.acceptQueue = datagramSocket;
        this.closed = n <= 0 ? 50 : n;
        this.acceptLock = new ArrayList(this.closed);
        this.backlog = new HashMap();
        this.j = new HashMap();
        this.k = new HashMap();
        this.l = new ServerConnectionListener(this, null);
        this.bound = 0;
        this.acceptThread = false;
        new ReliableServerThread(this).start();
    }

    public void a(AddressFilter c2) {
        this.udpSocket = c2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Socket accept() throws SocketException, SocketTimeoutException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        ArrayList arrayList = this.acceptLock;
        synchronized (arrayList) {
            while (this.acceptLock.isEmpty()) {
                try {
                    if (this.bound == 0) {
                        this.acceptLock.wait();
                    } else {
                        long l = System.currentTimeMillis();
                        this.acceptLock.wait(this.bound);
                        if (System.currentTimeMillis() - l >= (long)this.bound) {
                            throw new SocketTimeoutException();
                        }
                    }
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                if (!this.isClosed()) continue;
                throw new SocketException("Socket is closed");
            }
            return (Socket)this.acceptLock.remove(0);
        }
    }

    @Override
    public synchronized void bind(SocketAddress socketAddress) throws SocketException {
        this.bind(socketAddress, 0);
    }

    @Override
    public synchronized void bind(SocketAddress socketAddress, int n) throws SocketException {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        this.acceptQueue.setReuseAddress(true);
        this.acceptQueue.bind(socketAddress);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void close() {
        if (this.isClosed()) {
            return;
        }
        this.acceptThread = true;
        Cloneable cloneable = this.acceptLock;
        synchronized (cloneable) {
            this.acceptLock.clear();
            this.acceptLock.notify();
        }
        cloneable = this.backlog;
        synchronized (cloneable) {
            if (this.backlog.isEmpty()) {
                this.acceptQueue.close();
            }
        }
    }

    @Override
    public InetAddress getInetAddress() {
        return this.acceptQueue.getInetAddress();
    }

    @Override
    public int getLocalPort() {
        return this.acceptQueue.getLocalPort();
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        return this.acceptQueue.getLocalSocketAddress();
    }

    @Override
    public boolean isBound() {
        return this.acceptQueue.isBound();
    }

    @Override
    public boolean isClosed() {
        return this.acceptThread;
    }

    @Override
    public void setSoTimeout(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        this.bound = n;
    }

    @Override
    public int getSoTimeout() {
        return this.bound;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(SocketAddress socketAddress, ReliableClientSocket e2) {
        HashMap hashMap = this.backlog;
        synchronized (hashMap) {
            e2.a(this.l);
            this.backlog.put(socketAddress, e2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ReliableClientSocket a(SocketAddress socketAddress) {
        HashMap hashMap = this.backlog;
        synchronized (hashMap) {
            ReliableClientSocket e2 = (ReliableClientSocket) this.backlog.remove(socketAddress);
            if (this.backlog.isEmpty() && this.isClosed()) {
                this.acceptQueue.close();
            }
            return e2;
        }
    }

    private void a(String string) {
        if (this.profile + 5000L < System.currentTimeMillis()) {
            this.profile = System.currentTimeMillis();
            this.localPort = 0;
        }
        if (this.localPort > 20) {
            return;
        }
        ++this.localPort;
        System.out.println(string);
    }

    static /* synthetic */ DatagramSocket a(ReliableServerSocket b2) {
        return b2.acceptQueue;
    }

    static /* synthetic */ void a(ReliableServerSocket b2, String string) {
        b2.a(string);
    }

    static /* synthetic */ HashMap b(ReliableServerSocket b2) {
        return b2.backlog;
    }

    static /* synthetic */ HashMap c(ReliableServerSocket b2) {
        return b2.k;
    }

    static /* synthetic */ HashMap d(ReliableServerSocket b2) {
        return b2.j;
    }

    static /* synthetic */ void a(ReliableServerSocket b2, SocketAddress socketAddress, ReliableClientSocket e2) {
        b2.a(socketAddress, e2);
    }

    static /* synthetic */ ArrayList e(ReliableServerSocket b2) {
        return b2.acceptLock;
    }

    static /* synthetic */ ReliableClientSocket a(ReliableServerSocket b2, SocketAddress socketAddress) {
        return b2.a(socketAddress);
    }
}
