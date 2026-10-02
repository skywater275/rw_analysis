/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import network.reliableudp.ReliableServerSocket;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.ServerListener$1;
import com.corrodinggames.rts.gameFramework.network.ServerAddress;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public strictfp class ServerListener
implements Runnable {
    public final boolean debugLogging = false;
    public static boolean listenEnabled = true;
    private final NetEngine netEngineRef;
    volatile boolean c = true;
    ServerSocket d;
    int e;
    boolean f;
    long g = -1L;
    final boolean h = false;
    final boolean i = true;
    final Object j = new Object();
    ArrayList k = new ArrayList();
    final Object l = new Object();
    int m = 0;
    int n = 0;
    boolean o;
    boolean p;
    boolean q;

    ServerListener(NetEngine ad2) {
        this.netEngineRef = ad2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean a(InetAddress inetAddress, boolean bl) {
        int n2;
        Object object;
        if (inetAddress == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("isIpAllowed: inetAddress==null");
            return true;
        }
        if (!listenEnabled) {  // 02b ao L44: !b (v19.133f2 修正)
            return true;
        }
        long l2 = System.currentTimeMillis();
        if (l2 > this.g + 60000L) {
            this.g = l2;
            object = this.l;
            synchronized (object) {
                this.k.clear();
            }
            this.m = 0;
            this.n = 0;
            this.o = false;
            this.p = false;
            this.q = false;
        }
        object = this.l;
        synchronized (object) {
            n2 = 0;
            for (Object object2 : this.k) {
                if (!inetAddress.equals(((ServerAddress) object2).a)) continue;
                ++((ServerAddress) object2).b;
                int n3 = 30;
                if (this.n > 100) {
                    n3 = 10;
                }
                if (this.n > 250) {
                    n3 = 5;
                }
                if (((ServerAddress) object2).b > n3) {
                    if (!((ServerAddress) object2).c) {
                        ((ServerAddress) object2).c = true;
                        com.corrodinggames.rts.gameFramework.GlobalState.e("DOS: Too many attempts:" + ((ServerAddress) object2).b + " ip:" + inetAddress.toString());
                    }
                    if (((ServerAddress) object2).b > 300 && !((ServerAddress) object2).d) {
                        ((ServerAddress) object2).d = true;
                        com.corrodinggames.rts.gameFramework.GlobalState.e("DOS: Excessive attempts:" + ((ServerAddress) object2).b + " ip:" + inetAddress.toString());
                    }
                    return false;
                }
                n2 = 1;  // 02b ao L96: var6=true (v19.133f2 修正)
                break;
            }
            if (n2 == 0) {
                Object object3;
                if (bl) {
                    ++this.m;
                }
                if (this.k.size() > 200) {
                    object3 = null;
                    for (ServerAddress ap2 : (java.util.Collection<ServerAddress>) (java.util.Collection) this.k) {
                        if (object3 != null && ((ServerAddress) object3).b <= ap2.b) continue;
                        object3 = ap2;
                    }
                    if (object3 != null) {
                        this.k.remove(object3);
                    }
                }
                object3 = new ServerAddress(this);
                ((ServerAddress) object3).a = inetAddress;
                this.k.add(object3);
            }
        }
        if (this.m > 500) {
            if (!this.p) {
                this.p = true;
                com.corrodinggames.rts.gameFramework.GlobalState.e("DOS: Too many unique attempts: " + this.m + ". udp:" + this.f);
            }
            return false;
        }
        int n4 = 0;
        n2 = 0;
        for (Object object2 : this.netEngineRef.aM) {
            ++n2;
            if (((PacketDecoder) object2).e == null || !inetAddress.equals(((PacketDecoder) object2).e)) continue;
            ++n4;
        }
        int n5 = 20;
        if (n2 > 150) {
            n5 = 10;
        }
        if (n2 > 200) {
            n5 = 5;
        }
        if (n4 > n5) {
            if (!this.q) {
                this.q = true;
                com.corrodinggames.rts.gameFramework.GlobalState.e("DOS: Too open connections from same ip:" + inetAddress.toString() + " (count:" + n4 + ") max:" + n5);
            }
            return false;
        }
        if (n2 > 300) {
            if (!this.o) {
                this.o = true;
                com.corrodinggames.rts.gameFramework.GlobalState.e("DOS: Too open connections locking down:" + inetAddress.toString() + " (count:" + n2 + ")");
            }
            return false;
        }
        ++this.n;
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a() throws IOException {
        this.netEngineRef.sendIncorrectPassword("Recreating server socket " + (this.f ? "udp" : "tcp"));
        Object object = this.j;
        synchronized (object) {
            if (this.d != null) {
                try {
                    this.d.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                this.d = null;
            }
            if (!this.c) {
                throw new IOException("recreate on non-active socket");
            }
            this.a(this.f);  // 02b ao L197: a(boolean) (v19.133f2 修正)
        }
    }

    public void a(boolean bl) throws IOException {
        this.e = this.netEngineRef.m;
        this.netEngineRef.sendIncorrectPassword("starting socket.. " + (bl ? "udp" : "tcp") + " port: " + this.e);
        this.f = bl;
        if (!bl) {
            this.d = new ServerSocket(this.e);
        } else {
            ReliableServerSocket b2 = new ReliableServerSocket(this.netEngineRef.m, 0, null, true);  // 02b ao L209: a/a/b (v19.133f2 修正)
            b2.a(new ServerListener$1(this));
            this.d = b2;
        }
    }

    @Override
    public void run() {
        com.corrodinggames.rts.gameFramework.GlobalState.initIntegrityAndCrashHandler();
        Thread.currentThread().setName("NewConnectionWorker-" + (this.f ? "udp" : "tcp") + " - " + this.e);
        int n2 = 0;
        int n3 = 0;
        this.netEngineRef.sendIncorrectPassword("reading..");
        while (this.c) {
            String string;
            Object object;
            Socket socket;
            try {
                socket = this.d.accept();
            }
            catch (IOException iOException) {
                int n4;
                if (!this.c) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("ServerSocket-accept(" + (this.f ? "udp" : "tcp") + "): Got expected IOException after closed socket");
                    break;
                }
                object = com.corrodinggames.rts.gameFramework.GlobalState.B();
                com.corrodinggames.rts.gameFramework.GlobalState.e("ServerSocket-accept(" + (this.f ? "udp" : "tcp") + ") failed: " + iOException.getMessage() + " (closed:" + this.d.isClosed() + ")");
                if (++n2 > 100) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Too many server socket fails");
                    this.b();
                    return;
                }
                try {
                    this.a();
                }
                catch (IOException iOException2) {
                    iOException2.printStackTrace();
                    com.corrodinggames.rts.gameFramework.GlobalState.B().i("Warning server socket got closed and could not be recreated");
                    this.b();
                    return;
                }
                if (n3 >= 3 || (n4 = ((com.corrodinggames.rts.gameFramework.GlobalState) object).bX.getConnectedPlayerCount()) <= 0) continue;  // 02b ao L253: var5.bX.D() (v19.133f2 修正)
                string = "Warning: server socket got closed and needed to be recreated, players were likely disconnected (but can rejoin).";
                if (com.corrodinggames.rts.gameFramework.GlobalState.aZ) {
                    string = string + "\n This likely due to iOS removing sockets of background apps. Avoid minimising the game when hosting.";
                }
                com.corrodinggames.rts.gameFramework.GlobalState.B().i(string);
                ++n3;
                continue;
            }
            try {
                socket.setTcpNoDelay(true);
                socket.setSoTimeout(15000);
                String string2 = "<unknown>";
                InetAddress inetAddress = socket.getInetAddress();  // 02b ao L271: InetAddress var11 (v19.133f2 修正)
                if (inetAddress != null) {
                    string2 = inetAddress.getHostAddress();
                }
                if (!this.a(inetAddress, true)) {  // 02b ao L276: a(InetAddress,boolean) (v19.133f2 修正)
                    socket.close();
                    continue;
                }
                PacketDecoder c2 = new PacketDecoder(this.netEngineRef, socket);
                string = "Accepted new connection id:" + c2.c + ".. (ip:" + string2 + ")";
                if (this.f) {
                    string = string + " (udp)";
                }
                this.netEngineRef.sendIncorrectPassword(string);
                c2.h = this.f;
                c2.e = inetAddress;  // 02b ao L285 (v19.133f2 修正)
                c2.d();
                this.netEngineRef.aM.add(c2);
            }
            catch (IOException iOException) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Got IOException on new player connection");
                iOException.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b() {
        Object object = this.j;
        synchronized (object) {
            this.c = false;
            if (this.d != null) {
                try {
                    this.d.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                this.d = null;
            }
        }
    }
}
