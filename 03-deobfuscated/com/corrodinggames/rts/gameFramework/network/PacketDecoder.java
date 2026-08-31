/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.ReceiveWorker;
import com.corrodinggames.rts.gameFramework.network.SendWorker;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public strictfp class PacketDecoder {
    private final NetEngine W;
    volatile boolean a = false;
    volatile boolean b = false;
    public int c;
    public Socket d;
    InetAddress e;
    ConcurrentLinkedQueue f = new ConcurrentLinkedQueue();
    public long g;
    public boolean h;
    public boolean i;
    public PacketDecoder j;
    public int k = -1;
    NetworkPacket l;
    public String m;
    public String n;
    public String o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public int x;
    public int y;
    public com.corrodinggames.rts.game.HumanPlayer z;
    int A = -1;
    long B = -1L;
    boolean C = false;
    boolean D = false;
    public int E = 999999;
    ReceiveWorker F;
    SendWorker G;
    Thread H;
    Thread I;
    boolean J = false;
    boolean K = false;
    public String L;
    public int M;
    public boolean N;
    public boolean O;
    public int P;
    public boolean Q;
    public int R;
    public long S;
    public boolean T;
    volatile int U;
    volatile int V;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PacketDecoder(NetEngine ad2, Socket socket) {
        this.W = ad2;
        this.d = socket;
        Object object = this.W.aQ;
        synchronized (object) {
            this.c = this.W.aP++;
        }
        this.M = com.corrodinggames.rts.gameFramework.GameUtils.a(1, 1000000);
    }

    public boolean a() {
        if (this.S < System.currentTimeMillis() - 10000L) {
            this.S = System.currentTimeMillis();
            this.R = 0;
        }
        if (this.R > 100) {
            if (!this.T) {
                this.T = true;
                this.c("Command limit was reached");
            }
            return true;
        }
        ++this.R;
        return false;
    }

    public int b() {
        if (this.B == -1L) {
            return -2;
        }
        if (this.B < System.currentTimeMillis() - 5000L) {
            return -1;
        }
        return this.A;
    }

    int c() {
        // 02b j/c.java L101-105: c() 返回玩家槽位 (z.k), 无玩家时 -1
        com.corrodinggames.rts.game.HumanPlayer e2 = this.z;
        if (e2 != null) {
            return e2.k;
        }
        return -1;
    }
    public synchronized void d() throws IOException {
        this.G = new SendWorker(this);
        this.I = new Thread(this.G);
        this.I.setDaemon(true);
        this.I.start();
        this.F = new ReceiveWorker(this, null);
        this.H = new Thread(this.F);
        this.H.setDaemon(true);
        this.H.start();
    }

    private void i() {
        com.corrodinggames.rts.game.HumanPlayer e2;
        this.a = true;
        if (this.W.C && !this.W.extractCommandName5() && (e2 = this.z) != null) {
            this.z = null;
            PacketDecoder c2 = this.W.sendIncorrectPassword(e2);
            if (c2 == null) {
                e2.I();
                this.W.stopServer();
                com.corrodinggames.rts.appFramework.n.o();
            }
        }
        if (this.H != null) {
            this.H.interrupt();
        }
        this.W.m(this);
        this.p = false;
    }

    private synchronized void j() {
        if (this.a) {
            return;
        }
        this.b = true;
        if (this.G != null) {
            this.G.a();
        }
        if (this.H != null) {
            this.H.interrupt();
        }
        this.W.m(this);
    }

    public void a(String string) {
        OutputNetStream as2 = new OutputNetStream();
        if (string == null) {
            string = "NULL";
        }
        as2.c(string);
        this.a(as2.b(111));
        this.j();
    }

    private synchronized void a(boolean bl, boolean bl2) {
        this.a(bl, bl2, "Time out");
    }

    public String e() {
        String string = "<null>";
        if (this.z != null) {
            string = this.z.v;
        }
        return string;
    }

    public String f() {
        if (this.j != null) {
            return this.n;
        }
        try {
            InetAddress inetAddress;
            Socket socket = this.d;
            if (socket != null && (inetAddress = socket.getInetAddress()) != null) {
                return inetAddress.getHostAddress();
            }
            return null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public String g() {
        if (this.j != null) {
            if (this.n == null) {
                return "<forwarded unknown>";
            }
            return this.n;
        }
        String string = "<no socket>";
        try {
            Socket socket = this.d;
            if (socket != null) {
                string = "<no bond socket>";
                InetAddress inetAddress = socket.getInetAddress();
                if (inetAddress != null) {
                    string = inetAddress.getHostAddress();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return "<socket error>";
        }
        return string;
    }

    public synchronized void a(boolean bl, boolean bl2, String string) {
        if (!this.a) {
            Object object;
            this.c("handleRemoteDisconnect");
            String string2 = null;
            if (this.z != null) {
                string2 = this.z.v;
            }
            String string3 = null;
            if (this.z != null) {
                String string4 = "player";
                object = "";
                if (this.z.b()) {
                    string4 = "spectator";
                } else if (this.W.aW) {
                    int n2 = this.z.a(false, false);
                    object = n2 == 0 ? " (Had no units)" : " (Team " + this.z.h() + ")";
                }
                string3 = string4 + " '" + this.z.v + "' disconnected" + (String)object;
            } else if (this.p) {
                string3 = this.s && this.q ? "relay server disconnected" : "a player disconnected";
            }
            if (!this.W.C) {
                string3 = "The server disconnected";
            }
            if (string3 != null && string != null) {
                string3 = string3 + "  (" + NetEngine.i(string) + ")";  // 02b ad.java L271: ad.i(var3)
            }
            this.i();
            if (string3 != null) {
                boolean bl3 = false;
                if (this.z != null && this.W.C && (object = this.W.sendIncorrectPassword(this.z)) != null) {
                    bl3 = true;
                }
                if (!bl3) {
                    if (!this.W.C) {
                        this.W.kickTeamImpl(string3);
                    } else {
                        this.W.j(string3);
                    }
                } else {
                    this.c("Not sending: '" + string3 + "' still another active connection");
                }
            }
            this.W.d.b(this, string2);
        } else {
            this.c("handleRemoteDisconnect: connection is already disconnecting");
        }
        if (!bl2 && this.G != null) {
            this.G.a();
        }
        if (bl2) {
            this.J = true;
        }
        if (bl) {
            this.K = true;
        }
        if (this.J && this.K) {
            try {
                this.d.close();
            }
            catch (IOException iOException) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("Error while closing network socket", (Throwable)iOException);
            }
            this.I = null;
            this.H = null;
            this.G = null;
            this.F = null;
            if (this.f != null) {
                this.f.clear();
            }
        }
    }

    public void a(String string, Throwable throwable) {
        com.corrodinggames.rts.gameFramework.GlobalState.a(this.d(string), throwable);
    }

    public void b(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b(this.d(string));
    }

    public void c(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e(this.d(string));
    }

    public String d(String string) {
        string = "id:" + this.c + ": " + string;
        com.corrodinggames.rts.game.HumanPlayer e2 = this.z;
        if (e2 != null) {
            string = string + " (Player:" + e2.v + ")";
        }
        return string;
    }

    public void a(NetworkPacket au2) {
        if (this.G == null && this.a) {
            return;
        }
        this.G.a(au2);
    }

    public boolean h() {
        return !this.a;
    }

    public void finalize() {
        try {
            if (this.d == null || this.d.isClosed()) {
                return;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("Connection::finalize called on unclosed socket (index:" + this.c + ")");
            if (this.d.getInetAddress() == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping possible steam socket");
            }
            try {
                this.d.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }
    }

    static /* synthetic */ void a(PacketDecoder c2, boolean bl, boolean bl2) {
        c2.a(bl, bl2);
    }

    static /* synthetic */ NetEngine a(PacketDecoder c2) {
        return c2.W;
    }
}
