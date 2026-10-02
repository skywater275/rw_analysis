/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.a.a;
import a.a.a.b;
import a.a.a.c;
import a.a.a.d;
import a.a.a.e;
import a.a.a.f;
import a.a.a.g;
import a.a.h$1;
import a.a.i;
import a.a.j;
import a.a.k;
import a.a.l;
import a.a.m;
import a.a.n;
import a.a.o;
import a.a.p;
import a.a.q;
import a.a.r;
import a.a.s;
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

public class h
extends Socket {
    protected DatagramSocket c;
    protected SocketAddress d;
    protected o e;
    protected q f;
    private byte[] a;
    private boolean b = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = true;
    private int l = 0;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private int p = -1;
    private Object q = new Object();
    private Object r = new Object();
    private ArrayList s = new ArrayList();
    private ArrayList t = new ArrayList();
    protected r g = a.a.r.a;
    private ArrayList u = new ArrayList();
    private ArrayList v = new ArrayList();
    private ArrayList w = new ArrayList();
    private Object x = new Object();
    private i y = new i();
    private Thread z;
    private int A = 32;
    private int B = 32;
    private int C;
    private int D;
    public boolean h = false;
    private a.a.a.i E = new a.a.a.i("rudp-NullSegmentTimer", new l(this, null));
    private a.a.a.i F = new a.a.a.i("rudp-RetransmissionTimer", new n(this, null));
    private a.a.a.i G = new a.a.a.i("rudp-CumulativeAckTimer", new j(this, null));
    private a.a.a.i H = new a.a.a.i("rudp-KeepAliveTimer", new k(this, null));
    private static final boolean I = Boolean.getBoolean("net.rudp.debug");

    public h() {
        this(new r());
    }

    public h(r r2) {
        this(new DatagramSocket(), r2);
    }

    protected h(DatagramSocket datagramSocket) {
        this(datagramSocket, new r());
    }

    protected h(DatagramSocket datagramSocket, r r2) {
        if (datagramSocket == null) {
            throw new NullPointerException("sock");
        }
        this.a(datagramSocket, r2);
    }

    protected void a(DatagramSocket datagramSocket, r r2) {
        this.c = datagramSocket;
        this.g = r2;
        this.C = (this.g.a() - 6) * 32;
        this.D = (this.g.a() - 6) * 32;
        if (this.z == null) {
            this.z = new m(this);
            this.z.start();
        }
    }

    @Override
    public void bind(SocketAddress socketAddress) {
        this.c.bind(socketAddress);
    }

    @Override
    public void connect(SocketAddress socketAddress) {
        this.connect(socketAddress, 0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void connect(SocketAddress socketAddress, int n2) {
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
        this.d = (InetSocketAddress)socketAddress;
        this.f();
        this.l = 2;
        Random random = new Random(System.currentTimeMillis());
        g g2 = new g(this.y.a(random.nextInt(255)), this.g.b(), this.g.a(), this.g.h(), this.g.i(), this.g.g(), this.g.c(), this.g.d(), this.g.e(), this.g.f());
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
        if (this.l == 3) {
            return;
        }
        object = this.u;
        synchronized (object) {
            this.u.clear();
            this.u.notifyAll();
        }
        this.y.l();
        this.F.e();
        switch (this.l) {
            case 2: {
                this.k();
                this.l = 0;
                if (bl) {
                    throw new SocketTimeoutException();
                }
                throw new SocketException("Connection refused");
            }
            case 0: 
            case 4: {
                this.l = 0;
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
        return ((InetSocketAddress)this.d).getAddress();
    }

    @Override
    public int getPort() {
        if (!this.isConnected()) {
            return 0;
        }
        return ((InetSocketAddress)this.d).getPort();
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        if (!this.isConnected()) {
            return null;
        }
        return new InetSocketAddress(this.getInetAddress(), this.getPort());
    }

    public SocketAddress c() {
        return this.d;
    }

    @Override
    public InetAddress getLocalAddress() {
        return this.c.getLocalAddress();
    }

    @Override
    public int getLocalPort() {
        return this.c.getLocalPort();
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        return this.c.getLocalSocketAddress();
    }

    @Override
    public synchronized InputStream getInputStream() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isInputShutdown()) {
            throw new SocketException("Socket input is shutdown");
        }
        if (this.e == null) {
            this.e = new o(this);
        }
        return this.e;
    }

    @Override
    public synchronized OutputStream getOutputStream() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isOutputShutdown()) {
            throw new SocketException("Socket output is shutdown");
        }
        if (this.f == null) {
            this.f = new q(this);
        }
        return this.f;
    }

    public void d() {
        this.b = true;
        this.l = 0;
        this.c.close();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void close() {
        Object object = this.q;
        synchronized (object) {
            Object object2;
            if (this.isClosed()) {
                return;
            }
            this.g();
            switch (this.l) {
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
                    this.a(new d(this.y.a()));
                    this.e();
                    break;
                }
                case 0: {
                    this.c.close();
                }
            }
            if (this.l != 0) {
                this.p = this.l;
            }
            this.b = true;
            this.l = 0;
            this.l();
            object2 = this.u;
            synchronized (object2) {
                this.u.notify();
            }
            object2 = this.w;
            synchronized (object2) {
                this.w.notify();
            }
        }
    }

    @Override
    public boolean isBound() {
        return this.c.isBound();
    }

    @Override
    public boolean isConnected() {
        return this.i;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isClosed() {
        Object object = this.q;
        synchronized (object) {
            return this.b;
        }
    }

    @Override
    public void setSoTimeout(int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        this.m = n2;
    }

    @Override
    public synchronized void setSendBufferSize(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("negative receive size");
        }
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (this.isConnected()) {
            return;
        }
        this.C = n2;
    }

    @Override
    public synchronized int getSendBufferSize() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        return this.C;
    }

    @Override
    public synchronized void setReceiveBufferSize(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("negative send size");
        }
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (this.isConnected()) {
            return;
        }
        this.D = n2;
    }

    @Override
    public synchronized int getReceiveBufferSize() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        return this.D;
    }

    @Override
    public void setTcpNoDelay(boolean bl) {
    }

    @Override
    public boolean getTcpNoDelay() {
        return false;
    }

    @Override
    public synchronized void setKeepAlive(boolean bl) {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!(this.k ^ bl)) {
            return;
        }
        this.k = bl;
        if (this.isConnected()) {
            if (this.k) {
                this.H.a(this.g.g() * 6, this.g.g() * 6);
            } else {
                this.H.e();
            }
        }
    }

    @Override
    public synchronized boolean getKeepAlive() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        return this.k;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void shutdownInput() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isInputShutdown()) {
            throw new SocketException("Socket input is already shutdown");
        }
        this.n = true;
        Object object = this.x;
        synchronized (object) {
            this.x.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void shutdownOutput() {
        if (this.isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (!this.isConnected()) {
            throw new SocketException("Socket is not connected");
        }
        if (this.isOutputShutdown()) {
            throw new SocketException("Socket output is already shutdown");
        }
        this.o = true;
        ArrayList arrayList = this.u;
        synchronized (arrayList) {
            this.u.notifyAll();
        }
    }

    @Override
    public boolean isInputShutdown() {
        return this.n;
    }

    @Override
    public boolean isOutputShutdown() {
        return this.o;
    }

    protected void a(byte[] byArray, int n2, int n3) {
        this.a(byArray, n2, n3, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(byte[] byArray, int n2, int n3, boolean bl) {
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
            Object object = this.r;
            synchronized (object) {
                while (this.j) {
                    try {
                        this.r.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                n4 = Math.min(this.g.a() - 6, n3 - i2);
                b b2 = new b(this.y.a(), this.y.b(), byArray, n2 + i2, n4);
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
    protected int b(byte[] var1_1, int var2_2, int var3_3) {
        var4_4 = 0;
        var5_5 = this.x;
        synchronized (var5_5) {
            while (true) lbl-1000:
            // 6 sources

            {
                if (this.w.isEmpty()) {
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
                        if (this.m == 0) {
                            this.x.wait();
                        }
                        var6_6 = System.currentTimeMillis();
                        this.x.wait(this.m);
                        if (System.currentTimeMillis() - var6_6 < (long)this.m) ** GOTO lbl-1000
                        throw new SocketTimeoutException();
                    }
                    catch (InterruptedException var6_7) {
                        if (!a.a.h.I) ** GOTO lbl-1000
                        var6_7.printStackTrace();
                    }
                    continue;
                }
                var6_8 = this.w.iterator();
                while (var6_8.hasNext()) {
                    var7_9 = (a.a.a.h)var6_8.next();
                    if (var7_9 instanceof f) {
                        var6_8.remove();
                        break;
                    }
                    if (var7_9 instanceof d) {
                        if (var4_4 > 0) break;
                        var6_8.remove();
                        return -1;
                    }
                    if (!(var7_9 instanceof b)) continue;
                    var8_10 = ((b)var7_9).c();
                    if (var8_10.length + var4_4 > var3_3) {
                        if (var4_4 > 0) break;
                        throw new IOException("insufficient buffer space");
                    }
                    System.arraycopy(var8_10, 0, var1_1, var2_2 + var4_4, var8_10.length);
                    var4_4 += var8_10.length;
                    var6_8.remove();
                }
                if (var4_4 > 0) break;
            }
            return var4_4;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(s s2) {
        if (s2 == null) {
            throw new NullPointerException("stateListener");
        }
        ArrayList arrayList = this.t;
        synchronized (arrayList) {
            if (!this.t.contains(s2)) {
                this.t.add(s2);
            }
        }
    }

    private void a(a.a.a.h h2) {
        if (h2 instanceof b || h2 instanceof f || h2 instanceof d || h2 instanceof e) {
            this.h(h2);
        }
        if (h2 instanceof b || h2 instanceof f || h2 instanceof d) {
            this.E.d();
        }
        if (I) {
            this.a("sent " + h2);
        }
        this.d(h2);
    }

    private a.a.a.h i() {
        a.a.a.h h2 = this.a();
        if (h2 != null) {
            if (I) {
                this.a("recv " + h2);
            }
            if (h2 instanceof b || h2 instanceof e || h2 instanceof f || h2 instanceof d || h2 instanceof g) {
                this.y.c();
            }
            if (this.k) {
                this.H.d();
            }
        }
        return h2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void e(a.a.a.h h2) {
        Object object = this.u;
        synchronized (object) {
            while (this.u.size() >= this.A || this.y.j() > this.g.b()) {
                if (this.b) {
                    throw new SocketException("Socket is closed");
                }
                try {
                    this.u.wait(10000L);
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            this.y.i();
            this.u.add(h2);
        }
        if (this.b) {
            throw new SocketException("Socket is closed");
        }
        if (!(h2 instanceof c) && !(h2 instanceof a)) {
            object = this.F;
            synchronized (object) {
                if (this.F.c()) {
                    this.F.a(this.g.h(), this.g.h());
                }
            }
        }
        this.a(h2);
        if (h2 instanceof b) {
            object = this.s;
            synchronized (object) {
                for (p p2 : this.s) {
                    p2.a();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void f(a.a.a.h h2) {
        if (this.g.c() > 0) {
            h2.b(h2.o() + 1);
        }
        if (this.g.c() != 0 && h2.o() > this.g.c()) {
            this.m();
            return;
        }
        this.a(h2);
        if (h2 instanceof b) {
            ArrayList arrayList = this.s;
            synchronized (arrayList) {
                for (p p2 : this.s) {
                    p2.b();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void j() {
        if (this.isConnected()) {
            this.E.e();
            if (this.k) {
                this.H.e();
            }
            Object object = this.r;
            synchronized (object) {
                this.j = false;
                this.r.notify();
            }
        }
        Object object = this;
        synchronized (object) {
            this.f();
            this.i = true;
            this.l = 3;
            this.notify();
        }
        object = this.t;
        synchronized (object) {
            for (s s2 : this.t) {
                s2.a(this);
            }
        }
        this.E.a(0L, this.g.g());
        if (this.k) {
            this.H.a(this.g.g() * 6, this.g.g() * 6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void k() {
        ArrayList arrayList = this.t;
        synchronized (arrayList) {
            for (s s2 : this.t) {
                s2.b(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void l() {
        ArrayList arrayList = this.t;
        synchronized (arrayList) {
            for (s s2 : this.t) {
                s2.c(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void m() {
        Object object = this.q;
        synchronized (object) {
            if (this.isClosed()) {
                return;
            }
            switch (this.l) {
                case 2: {
                    Iterator iterator = this;
                    synchronized (iterator) {
                        this.notify();
                        break;
                    }
                }
                case 1: 
                case 3: 
                case 4: {
                    this.i = false;
                    Iterator iterator = this.u;
                    synchronized (iterator) {
                        this.u.notifyAll();
                    }
                    iterator = this.x;
                    synchronized (iterator) {
                        this.x.notify();
                    }
                    this.e();
                }
            }
            this.l = 0;
            this.b = true;
        }
        object = this.t;
        synchronized (object) {
            for (s s2 : this.t) {
                s2.d(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n() {
        ArrayList arrayList = this.t;
        synchronized (arrayList) {
            for (s s2 : this.t) {
                s2.e(this);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void a(g g2) {
        switch (this.l) {
            case 0: {
                this.l = 1;
                this.g = new r(this.A, this.B, g2.e(), g2.c(), g2.i(), g2.j(), g2.k(), g2.l(), g2.h(), g2.f(), g2.g());
                this.y.b(g2.m());
                Random random = new Random(System.currentTimeMillis());
                g g3 = new g(this.y.a(random.nextInt(255)), this.g.b(), this.g.a(), this.g.h(), this.g.i(), this.g.g(), this.g.c(), this.g.d(), this.g.e(), this.g.f());
                g3.a(g2.m());
                this.e(g3);
                break;
            }
            case 1: {
                ArrayList arrayList = this.u;
                synchronized (arrayList) {
                    for (a.a.a.h h2 : this.u) {
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
                this.y.b(g2.m());
                this.l = 3;
                this.o();
                this.j();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(c c2) {
        int[] nArray = c2.c();
        int n2 = c2.n();
        int n3 = nArray[nArray.length - 1];
        ArrayList arrayList = this.u;
        synchronized (arrayList) {
            Iterator iterator = this.u.iterator();
            block5: while (iterator.hasNext()) {
                a.a.a.h h2 = (a.a.a.h)iterator.next();
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
            for (a.a.a.h h2 : this.u) {
                if (this.a(n2, h2.m()) >= 0 || this.a(n3, h2.m()) <= 0) continue;
                try {
                    this.f(h2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
            this.u.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void g(a.a.a.h h2) {
        Object object;
        if (h2 instanceof f) {
            object = this.r;
            synchronized (object) {
                this.j = true;
            }
            this.n();
        }
        if (h2 instanceof d) {
            switch (this.l) {
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
                    this.l = 4;
                }
            }
        }
        boolean bl = false;
        Object object2 = this.x;
        synchronized (object2) {
            if (this.a(h2.m(), this.y.b()) > 0) {
                if (this.a(h2.m(), a.a.h.b(this.y.b())) == 0) {
                    bl = true;
                    if (this.w.size() == 0 || this.w.size() + this.v.size() < this.B) {
                        this.y.b(h2.m());
                        if (h2 instanceof b || h2 instanceof f || h2 instanceof d) {
                            this.w.add(h2);
                        }
                        if (h2 instanceof b) {
                            ArrayList arrayList = this.s;
                            synchronized (arrayList) {
                                for (p p2 : this.s) {
                                    p2.c();
                                }
                            }
                        }
                        this.r();
                    }
                } else if (this.w.size() + this.v.size() < this.B) {
                    boolean bl2 = false;
                    for (int i2 = 0; i2 < this.v.size() && !bl2; ++i2) {
                        a.a.a.h h3 = (a.a.a.h)this.v.get(i2);
                        int n2 = this.a(h2.m(), h3.m());
                        if (n2 == 0) {
                            bl2 = true;
                            continue;
                        }
                        if (n2 >= 0) continue;
                        this.v.add(i2, h2);
                        bl2 = true;
                    }
                    if (!bl2) {
                        this.v.add(h2);
                    }
                    this.y.f();
                    if (h2 instanceof b) {
                        ArrayList arrayList = this.s;
                        synchronized (arrayList) {
                            for (p p3 : this.s) {
                                p3.d();
                            }
                        }
                    }
                }
            }
            if (bl && (h2 instanceof f || h2 instanceof e || h2 instanceof d)) {
                this.o();
            } else if (this.y.g() > 0 && (this.g.e() == 0 || this.y.g() > this.g.e())) {
                this.p();
            } else if (this.y.d() > 0 && (this.g.d() == 0 || this.y.d() > this.g.d())) {
                this.q();
            } else {
                a.a.a.i i3 = this.G;
                synchronized (i3) {
                    if (this.G.c()) {
                        this.G.a(this.g.i());
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void o() {
        Object object = this.x;
        synchronized (object) {
            if (!this.v.isEmpty()) {
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
        Object object = this.x;
        synchronized (object) {
            int n2;
            if (this.v.isEmpty()) {
                return;
            }
            this.y.e();
            this.y.h();
            int[] nArray = new int[this.v.size()];
            for (n2 = 0; n2 < nArray.length; ++n2) {
                a.a.a.h h2 = (a.a.a.h)this.v.get(n2);
                nArray[n2] = h2.m();
            }
            try {
                n2 = this.y.b();
                this.a((a.a.a.h)new c(a.a.h.b(n2), n2, nArray));
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    private void q() {
        if (this.y.e() == 0) {
            return;
        }
        try {
            int n2 = this.y.b();
            this.a(new a(a.a.h.b(n2), n2));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void h(a.a.a.h h2) {
        if (this.y.e() == 0) {
            return;
        }
        h2.a(this.y.b());
    }

    protected boolean b(a.a.a.h h2) {
        int n2 = h2.n();
        if (n2 < 0) {
            return false;
        }
        for (a.a.a.h h3 : this.u) {
            if (this.a(h3.m(), n2) > 0) continue;
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void c(a.a.a.h h2) {
        int n2 = h2.n();
        if (n2 < 0) {
            return;
        }
        this.y.k();
        ArrayList arrayList = this.u;
        synchronized (arrayList) {
            Iterator iterator = this.u.iterator();
            while (iterator.hasNext()) {
                a.a.a.h h3 = (a.a.a.h)iterator.next();
                if (this.a(h3.m(), n2) > 0) continue;
                iterator.remove();
            }
            if (this.l == 1) {
                boolean bl = false;
                if (!this.u.isEmpty()) {
                    for (a.a.a.h h4 : this.u) {
                        if (!(h4 instanceof g)) continue;
                        bl = true;
                    }
                }
                if (bl) {
                    this.a("Bad first ack: " + n2);
                    return;
                }
                this.l = 3;
                this.j();
            }
            if (this.u.isEmpty()) {
                this.F.e();
            }
            this.u.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void r() {
        Object object = this.x;
        synchronized (object) {
            Iterator iterator = this.v.iterator();
            while (iterator.hasNext()) {
                a.a.a.h h2 = (a.a.a.h)iterator.next();
                if (this.a(h2.m(), a.a.h.b(this.y.b())) != 0) continue;
                this.y.b(h2.m());
                if (h2 instanceof b || h2 instanceof f || h2 instanceof d) {
                    this.w.add(h2);
                }
                iterator.remove();
            }
            this.x.notify();
        }
    }

    protected void d(a.a.a.h h2) {
        block2: {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(h2.d(), h2.b(), this.d);
                this.c.send(datagramPacket);
            }
            catch (IOException iOException) {
                if (this.isClosed()) break block2;
                iOException.printStackTrace();
            }
        }
    }

    protected a.a.a.h a() {
        try {
            if (this.a == null) {
                this.a = new byte[65535];
            }
            DatagramPacket datagramPacket = new DatagramPacket(this.a, this.a.length);
            this.c.receive(datagramPacket);
            return a.a.a.h.b(datagramPacket.getData(), 0, datagramPacket.getLength());
        }
        catch (IOException iOException) {
            if (!this.isClosed()) {
                iOException.printStackTrace();
            }
            return null;
        }
    }

    protected void b() {
        this.c.close();
    }

    protected void e() {
        this.E.e();
        this.H.e();
        this.l = 4;
        h$1 h$1 = new h$1(this);
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
        if (!this.h) {
            this.h = true;
            this.E.a();
            this.F.a();
            this.G.a();
            this.H.a();
        }
    }

    public synchronized void g() {
        if (this.h) {
            this.h = false;
            this.F.f();
            this.G.f();
            this.H.f();
            this.E.f();
        }
    }

    static /* synthetic */ a.a.a.i a(h h2) {
        return h2.H;
    }

    static /* synthetic */ a.a.a.i b(h h2) {
        return h2.E;
    }

    static /* synthetic */ a.a.a.i c(h h2) {
        return h2.F;
    }

    static /* synthetic */ a.a.a.i d(h h2) {
        return h2.G;
    }

    static /* synthetic */ void e(h h2) {
        h2.l();
    }

    static /* synthetic */ int a(int n) {
        return a.a.h.b(n);
    }

    static /* synthetic */ a.a.a.h f(h h2) {
        return h2.i();
    }

    static /* synthetic */ void a(h h2, c c2) {
        h2.a(c2);
    }

    static /* synthetic */ void a(h h2, a.a.a.h h3) {
        h2.g(h3);
    }

    static /* synthetic */ ArrayList g(h h2) {
        return h2.u;
    }

    static /* synthetic */ i h(h h2) {
        return h2.y;
    }

    static /* synthetic */ void b(h h2, a.a.a.h h3) {
        h2.e(h3);
    }

    static /* synthetic */ boolean h() {
        return I;
    }

    static /* synthetic */ void c(h h2, a.a.a.h h3) {
        h2.f(h3);
    }

    static /* synthetic */ void i(h h2) {
        h2.o();
    }

    static /* synthetic */ void j(h h2) {
        h2.m();
    }
}
