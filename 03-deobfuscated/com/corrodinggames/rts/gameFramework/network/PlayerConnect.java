/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.gameFramework.RenderThread;
import com.corrodinggames.rts.gameFramework.network.SendWorker;
import com.corrodinggames.rts.gameFramework.KeyBinding;

import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.ReceiveWorker;
import com.corrodinggames.rts.gameFramework.network.SendWorker;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public strictfp class PlayerConnect {
    private final NetEngine netEngine;
    volatile boolean a = false;
    volatile boolean b = false;
    public int c;
    public Socket d;
    InetAddress e;
    ConcurrentLinkedQueue f = new ConcurrentLinkedQueue();
    public long connectTime;
    public boolean isConnected;
    public boolean isAuthenticated;
    public PacketDecoder connectionObj;
    public int assignedSlot = -1;
    NetworkPacket l;  // 02b j/c.java L28: au=NetworkPacket
    public String playerName;
    public String ipAddress;
    public String clientVersion;
    public boolean isHostPlayer;
    public boolean isObserver;
    public boolean isReady;
    public boolean hasDesynced;
    public boolean needsResync;
    public boolean isBanned;
    public boolean isKicked;
    public boolean hasTimedOut;
    public int pingMs;
    public int packetsLost;
    public HumanPlayer commandHandler;  // 02b c.java L42: game.e=HumanPlayer (class-discoveries)
    int A = -1;
    long B = -1L;
    boolean C = false;
    boolean D = false;
    public int connectionState = 999999;
    ReceiveWorker F;  // 02b j/c.java L48: d=ReceiveWorker
    SendWorker G;  // 02b j/c.java L49: e=SendWorker
    Thread H;
    Thread I;
    boolean J = false;
    boolean K = false;
    // 02b j/c.java L31 o 与 L54 L 均为 String (03 合并为一个 clientVersion; 02b 中均未被引用)
    public int gameSpeedPreference;
    public boolean isRelayConnection;
    public boolean isSteamRelay;
    public int receivedChunks;
    public boolean isRegistrationComplete;
    public int commandRateCount;
    public long commandRateStartTime;
    public boolean commandRateLimited;
    volatile int packetBytesExpected;
    volatile int packetBytesRead;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PlayerConnect(NetEngine ad2, Socket socket) {
        this.netEngine = ad2;
        this.d = socket;
        Object object = this.netEngine.aQ;
        synchronized (object) {
            this.c = this.netEngine.aP++;
        }
        this.gameSpeedPreference = GameUtils.a(1, 1000000);
    }

    public boolean getad() {
        if (this.commandRateStartTime < System.currentTimeMillis() - 10000L) {
            this.commandRateStartTime = System.currentTimeMillis();
            this.commandRateCount = 0;
        }
        if (this.commandRateCount > 100) {
            if (!this.commandRateLimited) {
                this.commandRateLimited = true;
                this.c("Command limit was reached");
            }
            return true;
        }
        ++this.commandRateCount;
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
        HumanPlayer e2 = this.commandHandler;
        if (e2 != null) {
            return e2.k;
        }
        return -1;
    }

    public synchronized void getString() {
        this.G = new SendWorker(this.connectionObj);  // 02b c.java L107: new e(this) — Worker 面向 PacketDecoder 侧
        this.I = new Thread(this.G);
        this.I.setDaemon(true);
        this.I.start();
        this.F = new ReceiveWorker(this.connectionObj, null);  // 02b c.java L111: new d(this)
        this.H = new Thread(this.F);
        this.H.setDaemon(true);
        this.H.start();
    }

    private void disconnect() {
        HumanPlayer e2;
        this.a = true;
        if (this.netEngine.C && !this.netEngine.extractCommandName5() && (e2 = this.commandHandler) != null) {
            this.commandHandler = null;
            PacketDecoder c2 = this.netEngine.sendIncorrectPassword(e2);  // 02b c.java L123
            if (c2 == null) {
                e2.I();
                this.netEngine.stopServer();
                com.corrodinggames.rts.appFramework.n.o();
            }
        }
        if (this.H != null) {
            this.H.interrupt();
        }
        this.netEngine.m(this.connectionObj);  // 02b c.java L136: W.b(this)
        this.isHostPlayer = false;
        if (this.isObserver) {
            this.netEngine.c(this.connectionObj, "Closing");  // 02b c.java L139: W.c(this)
        }
    }

    private synchronized void closeConnection() {
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
        this.netEngine.m(this.connectionObj);  // 02b c.java L136: W.b(this)
    }

    public void getad(String string) {
        OutputNetStream as2 = new OutputNetStream();  // 02b c.java L160: as=OutputNetStream
        try {
            if (string == null) {
                string = "NULL";
            }
            as2.c(string);
        }
        catch (RuntimeException iOException) {
            throw new RuntimeException(iOException);
        }
        this.getad(as2.b(111));
        this.closeConnection();  // 02b c.java L173: this.j()
    }

    private synchronized void getad(boolean bl, boolean bl2) {
        this.getad(bl, bl2, "Time out");
    }

    public String getPlayerName() {
        String string = "<null>";
        if (this.commandHandler != null) {
            string = this.commandHandler.v;
        }
        return string;
    }

    public String getPlayerIp() {
        if (this.connectionObj != null) {
            return this.ipAddress;
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

    public String getConnectionId() {
        if (this.connectionObj != null) {
            if (this.ipAddress == null) {
                return "<forwarded unknown>";
            }
            return this.ipAddress;
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

    public synchronized void getad(boolean bl, boolean bl2, String string) {
        if (!this.a) {
            Object object;
            this.c("handleRemoteDisconnect");
            String string2 = null;
            if (this.commandHandler != null) {
                string2 = this.commandHandler.v;
            }
            String string3 = null;
            if (this.commandHandler != null) {
                String string4 = "player";
                object = "";
                if (this.commandHandler.b()) {
                    string4 = "spectator";
                } else if (this.netEngine.aW) {
                    int n2 = this.commandHandler.a(false, false);
                    object = n2 == 0 ? " (Had no units)" : " (Team " + this.commandHandler.h() + ")";
                }
                string3 = string4 + " '" + this.commandHandler.v + "' disconnected" + (String)object;
            } else if (this.isHostPlayer) {
                string3 = this.hasDesynced && this.isObserver ? "relay server disconnected" : "a player disconnected";
            }
            if (!this.netEngine.C) {
                string3 = "The server disconnected";
            }
            if (string3 != null && string != null) {
                string3 = string3 + "  (" + NetEngine.i(string) + ")";  // 02b ad.java L271: ad.i(var3)
            }
            this.disconnect();  // 02b c.java L274: this.i()
            if (string3 != null) {
                boolean bl3 = false;
                if (this.commandHandler != null && this.netEngine.C && (object = this.netEngine.sendIncorrectPassword(this.commandHandler)) != null) {
                    bl3 = true;
                }
                if (!bl3) {
                    if (!this.netEngine.C) {
                        this.netEngine.kickTeamImpl(string3);
                    } else {
                        this.netEngine.j(string3);
                    }
                } else {
                    this.c("Not sending: '" + string3 + "' still another active connection");
                }
            }
            this.netEngine.d.b(this.connectionObj, string2);  // 02b c.java L295: W.d.b(this, var4)
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

    public void getad(String string, Throwable throwable) {
        com.corrodinggames.rts.gameFramework.GlobalState.a(this.getString(string), throwable);
    }

    public void b(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b(this.getString(string));
    }

    public void c(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e(this.getString(string));
    }

    public String getString(String string) {
        string = "id:" + this.c + ": " + string;
        HumanPlayer e2 = this.commandHandler;
        if (e2 != null) {
            string = string + " (Player:" + e2.v + ")";
        }
        return string;
    }

    public void getad(NetworkPacket au2) {
        if (this.G == null && this.a) {
            return;
        }
        this.G.a(au2);
    }

    public boolean isConnectionAlive() {
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

    static /* synthetic */ void getad(PlayerConnect c2, boolean bl, boolean bl2) {  // 02b c.java L385
        c2.getad(bl, bl2);
    }

    static /* synthetic */ NetEngine a(PlayerConnect c2) {  // 02b c.java L390-391: static ad a(c)
        return c2.netEngine;
    }
}
