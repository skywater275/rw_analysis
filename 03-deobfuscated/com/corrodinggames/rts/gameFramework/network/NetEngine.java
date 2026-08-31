/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.Intent
 *  android.net.DhcpInfo
 *  android.net.wifi.WifiManager
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.game.map.TileEntry;
import com.corrodinggames.rts.gameFramework.GameInput;
import com.corrodinggames.rts.gameFramework.NullInput;
import com.corrodinggames.rts.gameFramework.AxisTrigger;
import com.corrodinggames.rts.gameFramework.ByteIndexedMap;
import com.corrodinggames.rts.gameFramework.PingTimer;
import com.corrodinggames.rts.gameFramework.TextureCache;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;
import com.corrodinggames.rts.gameFramework.MusicFactory;

import com.corrodinggames.rts.gameFramework.ui.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.appFramework.ContextMenuActivity;
import com.corrodinggames.rts.appFramework.p;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.custom.bd;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.ChatSystem;
import com.corrodinggames.rts.gameFramework.network.NetworkAuth;
import com.corrodinggames.rts.gameFramework.network.IntArray$1;
import com.corrodinggames.rts.gameFramework.network.IntArray$3;
import com.corrodinggames.rts.gameFramework.network.IntArray$4;
import com.corrodinggames.rts.gameFramework.network.IntArray$5;
import com.corrodinggames.rts.gameFramework.network.IntArray$6;
import com.corrodinggames.rts.gameFramework.network.IntArray$7;
import com.corrodinggames.rts.gameFramework.network.IntArray$8;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.network.UDPBroadcastListener;
import com.corrodinggames.rts.gameFramework.network.NetworkException;
import com.corrodinggames.rts.gameFramework.network.MatchConfig;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.ServerInfo;
import com.corrodinggames.rts.gameFramework.network.ChecksumCalculator;
import com.corrodinggames.rts.gameFramework.network.ChecksumField;
import com.corrodinggames.rts.gameFramework.network.ConnectionState;
import com.corrodinggames.rts.gameFramework.network.ServerConnector;
import com.corrodinggames.rts.gameFramework.network.ServerListener;
import com.corrodinggames.rts.gameFramework.network.SecurityHasher;
import com.corrodinggames.rts.gameFramework.network.NetworkUtils;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import com.corrodinggames.rts.gameFramework.network.KeepAliveTimer;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.network.GameServerInfo;
import com.corrodinggames.rts.gameFramework.network.SteamSocket;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public strictfp final class NetEngine {
    public int aw = 5;  // 02b ad.aw L145

    public float ae;
    public String bw;
    public static final boolean a = false;
    public static boolean b = true;
    public static boolean c = false;
    public NetworkAuth d = new NetworkAuth();
    public int e;
    ArrayList f;
    public boolean g;
    public int h = 25;
    public boolean i;
    public float j;
    public float k;
    public boolean l = false;
    public int m;
    public String n;
    public boolean o;
    public boolean p;
    public boolean q;
    public static boolean r = true;
    public boolean s;
    public int t = 5005;
    public String u;
    public boolean v = false;
    public long w = 1L;
    public boolean x = false;
    public String y;
    private boolean bG;
    public PlayerState z;
    public boolean A;
    private boolean bH = false;
    public volatile boolean B = false;
    public boolean C;
    public boolean D;
    public String E;
    public boolean F = false;
    public boolean G;
    public boolean useSteamRelay;
    public int I = 0;
    private volatile float bI = 1.0f;
    public volatile float J = 1.0f;
    public Float K;
    public String L;
    public ArrayList<ServerInfo> M = new ArrayList<ServerInfo>();
    public boolean N;
    public int totalPlayers;
    public int P;
    public int Q;
    public int R;
    public String S;
    public int T = -1;
    public int U = -1;
    public int V = -1;
    public int W = com.corrodinggames.rts.gameFramework.GameUtils.a(1, 9000000);
    public int X = 0;
    public boolean Y;
    public float Z;
    boolean aa;
    public float packetsPerSecond;
    public float ac;
    public boolean packetRateLimit;
    public float packetTimer;
    public boolean af;
    public boolean disconnectStateFlag;
    public int resyncStageCounter = -1;
    public int ai = 300;
    public boolean aj;
    public boolean ak;
    public boolean connectionLost;
    public ChecksumCalculator am = new ChecksumCalculator();
    public boolean an;
    public boolean ao = true;
    public int ap;
    public int aq;
    public int ar;
    public static boolean as;
    float at = 0.0f;
    long au;
    public boolean av;
    public int syncFrameThreshold = 5;
    public int ax = 5;
    public MatchConfig ay = new MatchConfig();
    public String az = null;
    public InputNetStream inputNetStream;
    public InputNetStream aB;
    public ChatSystem packetBuffer = new ChatSystem();
    Thread aD;
    ServerListener aE;
    Thread aF;
    ServerListener aG;
    Timer aH;
    KeepAliveTimer aI;
    Thread aJ;
    UDPBroadcastListener aK;
    PacketDecoder aL;
    public ConcurrentLinkedQueue<PacketDecoder> aM = new ConcurrentLinkedQueue<PacketDecoder>();
    ConcurrentLinkedQueue<NetworkPacket> aN = new ConcurrentLinkedQueue<NetworkPacket>();
    boolean aO;
    volatile int aP = 1;
    Object aQ = new Object();
    String aR;
    String aS;
    public String aT;
    public Boolean cloudServicesEnabled;
    public Boolean aV;
    public boolean aW;
    public boolean debugNetworkOverlay = false;
    boolean aY = false;
    boolean aZ = false;
    public float ba;
    public boolean pingUpdated;
    public boolean highLatencyDetected;
    public boolean packetLossDetected;
    public boolean bandwidthLimited;
    public boolean bf;
    public String externalIpAddress;
    public String bh = null;
    public ConcurrentLinkedQueue<GameServerInfo> bi = new ConcurrentLinkedQueue<GameServerInfo>();
    public com.corrodinggames.rts.game.HumanPlayer bj;
    public com.corrodinggames.rts.game.HumanPlayer bk;
    public final Object bl = new Object();
    public boolean bm = false;
    float bn;
    float bo;
    int bp;
    int bq;
    boolean br = false;
    public long bs;
    public long bt;
    boolean bu = false;
    public Socket bv = null;
    public String lastErrorMessage = null;
    public boolean bx;
    boolean by = false;
    boolean bz = false;
    static ArrayList bA;
    boolean bB = false;
    final Object bC = new Object();
    Timer bD;
    public static PasswordManager bE;
    ServerConnector bF;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ServerInfo registerRelayServer(PacketDecoder c2) {
        String string = c2.f();
        long l = System.currentTimeMillis();
        if (string == null) {
            c2.b("Is banned: No target");
            return null;
        }
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            for (ServerInfo aj2 : this.M) {
                if (!string.equals(aj2.a) || aj2.b <= l) continue;
                return aj2;
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean registerRelayServer(PacketDecoder c2, String string, int n2) {
        if (c2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Ban failed: No connection");
            return false;
        }
        String string2 = c2.f();
        if (string2 == null) {
            c2.b("Ban failed: No target");
            return false;
        }
        ServerInfo aj2 = new ServerInfo();
        aj2.a = c2.f();
        aj2.b = System.currentTimeMillis() + (long)(n2 * 1000);
        aj2.c = string;
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            this.m();
            this.M.add(aj2);
        }
        c2.c("Banned " + string2 + " for " + n2 + "s");
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void registerRelayServer() {
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            this.M.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void m() {
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            int n2 = 0;
            long l2 = System.currentTimeMillis();
            Iterator iterator = this.M.iterator();
            while (iterator.hasNext()) {
                ++n2;
                ServerInfo aj2 = (ServerInfo) iterator.next();
                boolean bl = false;
                if (aj2.b < l2) {
                    bl = true;
                }
                if (n2 > 1000) {
                    bl = true;
                }
                if (!bl) continue;
                iterator.remove();
            }
        }
    }

    public String registerRelayServer(String string) {
        string = string.trim();
        this.y = string = string.replace(" ", "_");
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.y != null && !this.y.equals(l2.bQ.lastNetworkPlayerName)) {
            l2.bQ.lastNetworkPlayerName = this.y;
            l2.bQ.save();
        }
        return string;
    }

    public void registerRelayServer(float f2, String string) {
        if ((double)f2 < 0.1) {
            NetEngine.registerRelayServer("setCurrentStepRate:" + f2 + " is too small, source:" + string, true);
            return;
        }
        this.bI = f2;
    }

    public float c() {
        return this.bI;
    }

    public void sendIncorrectPassword() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.resyncStageCounter = l2.bx;
        this.am.b();
        this.an = false;
    }

    public void registerRelayServer(OutputNetStream as2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        as2.c(0);
        this.ay.a(as2);
        as2.a(l2.bB);
        as2.a(l2.bC);
    }

    public void registerRelayServer(InputNetStream k2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        k2.d();
        this.ay.a(k2);
        l2.bB = k2.readInt();
        l2.bC = k2.readInt();
    }

    public MatchConfig kickTeam() {
        MatchConfig ah2;
        if (this.C) {
            ah2 = this.ay;
        } else if (this.useSteamRelay) {
            ah2 = this.ay.getah();
        } else {
            ah2 = null;
            com.corrodinggames.rts.gameFramework.GlobalState.b("getChangeableSetup", "Clicked but not server or proxy controller");
        }
        return ah2;
    }

    public void kickTeamImpl() {
        if (this.F) {
            com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.aiDifficulty = this.ay.f;
        }
        if (!this.C && !this.F) {
            return;
        }
        if (this.aW) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("updateAIDifficulty with gameHasBeenStarted=true");
        } else {
            for (int j = 0; j < com.corrodinggames.rts.game.PlayerState.c; ++j) {
                PlayerState n2 = com.corrodinggames.rts.game.PlayerState.u(j);
                if (n2 == null) continue;
                this.registerRelayServer(n2);
            }
        }
        this.updateAllAINames();
    }

    public void registerRelayServer(PlayerState n2) {
        if (n2.w) {
            n2.c("aiDifficultyOverride=" + n2.z);
            n2.x = n2.z != null ? n2.z : this.ay.f;
        }
    }

    public boolean m(PlayerState n2) {
        String string;
        boolean bl = false;
        if (n2.w && !(string = "AI - " + this.m(n2.getPlayerColorInt())).equals(n2.v)) {
            n2.v = string;
            bl = true;
        }
        return bl;
    }

    public void registerRelayServer(MatchConfig ah2) {
        if (this.C) {
            this.kickTeamImpl();
            this.P();
            this.L();
            com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
        } else if (this.useSteamRelay) {
            this.m(ah2);
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.e("applyChangedSetup but not server or proxy controller");
        }
    }

    private void m(MatchConfig ah2) {
        String string;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.GlobalState.e("applyProxyControl");
        MatchConfig ah3 = this.ay;
        if (!ah3.b.equals(ah2.b)) {
            string = com.corrodinggames.rts.appFramework.ContextMenuActivity.e(ah2.b);
            string = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.o(string);
            l2.bX.k("-map '" + string + "'");
        }
        if (ah3.e != ah2.e) {
            string = !ah2.e ? "true" : "false";
            l2.bX.k("-revealedmap " + string);
        }
        if (ah3.d != ah2.d) {
            string = l2.bX.registerRelayServer(ah2.d);
            l2.bX.k("-fog " + string);
        }
        if (ah3.c != ah2.c) {
            int n2 = l2.bX.kickTeam(ah2.c);
            l2.bX.k("-credits " + n2);
        }
        if (!GameUtils.cosFast(ah3.h, ah2.h)) {
            l2.bX.k("-income " + com.corrodinggames.rts.gameFramework.GameUtils.a(ah2.h, 1));
        }
        if (ah3.i != ah2.i) {
            String string2 = !ah2.i ? "true" : "false";
            l2.bX.k("-nukes " + string2);
        }
        if (ah3.f != ah2.f) {
            l2.bX.k("-ai " + ah2.f);
        }
        if (ah3.g != ah2.g) {
            l2.bX.k("-startingunits " + ah2.g);
        }
        if (ah3.l != ah2.l) {
            String string3 = ah2.l ? "true" : "false";
            l2.bX.k("-sharedControl " + string3);
        }
    }

    public String registerRelayServer(int n2) {
        if (n2 == 0) {
            return "off";
        }
        if (n2 == 1) {
            return "basic";
        }
        if (n2 == 0) {
            return "los";
        }
        return "Unknown";
    }

    public String m(int n2) {
        return this.c(n2);
    }

    // 02b j/ad.d(String) (FF 娑撱垺鏌熷▔鏇氱秼 閳?缂冩垹绮堕弮銉ョ箶 閳?缁犫偓閸?
    public void d(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e(string);
    }

    public String c(int n2) {
        if (n2 == -2) {
            return "Very Easy";
        }
        if (n2 == -1) {
            return "Easy";
        }
        if (n2 == 0) {
            return "Medium";
        }
        if (n2 == 1) {
            return "Hard";
        }
        if (n2 == 0) {
            return "Very Hard";
        }
        if (n2 == 3) {
            return "Impossible";
        }
        return "Unknown";
    }

    public String sendPacketToClients() {
        return this.sendIncorrectPassword(this.ay.g);
    }

    public ArrayList cancelNotification() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 1; i2 <= 4; ++i2) {
            arrayList.add(i2);
        }
        arrayList.addAll(com.corrodinggames.rts.game.units.custom.ModUnitRegistry.s());
        return arrayList;
    }

    public String sendIncorrectPassword(int n2) {
        if (n2 == 1) {
            return "Normal (1 builder)";
        }
        if (n2 == 0) {
            return "Small Army";
        }
        if (n2 == 3) {
            return "3 Engineers";
        }
        if (n2 == 4) {
            return "3 Engineers (No Command Center)";
        }
        if (n2 == 5) {
            return "Experimental Spider";
        }
        if (n2 == 9) {
            return "Custom";
        }
        com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(n2);
        if (l2 != null) {
            return l2.e();
        }
        return "Unknown";
    }

    public String j() {
        if (this.ay.c == 0) {
            return "Default ($" + this.k() + ")";
        }
        return "$" + this.k();
    }

    public final int k() {  // 02b ad.java L520-522 (kickTeam 娑撳搫澶熺憴澶婃倳)
        return this.e(this.ay.c);
    }

    public int kickTeam(int n2) {
        if (n2 == 0) {
            return 4000;
        }
        if (n2 == 1) {
            return 0;
        }
        if (n2 == 0) {
            return 1000;
        }
        if (n2 == 3) {
            return 2000;
        }
        if (n2 == 4) {
            return 5000;
        }
        if (n2 == 5) {
            return 10000;
        }
        if (n2 == 6) {
            return 50000;
        }
        if (n2 == 7) {
            return 100000;
        }
        if (n2 == 8) {
            return 200000;
        }
        return 999;
    }

    public String prepareChatMessage() {
        return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.o(this.az);
    }

    public boolean extractCommandName5() {
        return this.aW;
    }

    public boolean receiveChatMessage() {
        return this.d.e();
    }

    public synchronized void registerRelayServer(boolean bl, String string, Boolean bl2) {
        this.aV = bl;
        this.aT = string;
        this.cloudServicesEnabled = bl2;
        com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
    }

    // 02b j/ad.a(g) 閸栧懐楠?(FF 娑?閳?pong 濞夈劌鍞?閳?婵梹澧?registerRelayServer)
    void a(GameServerInfo g2) {
        this.registerRelayServer(g2);
    }

    void registerRelayServer(GameServerInfo g2) {
        for (GameServerInfo g3 : this.bi) {
            if (!g3.a || !g3.c.equals(g2.c) || g3.g != g2.g) continue;
            g3.o = this.p();
        }
        g2.o = this.p();
        this.bi.add(g2);
        com.corrodinggames.rts.appFramework.p.l();
    }

    public long p() {
        return System.currentTimeMillis();
    }

    public NetEngine() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.e = l2.c(true);
        this.aR = com.corrodinggames.rts.gameFramework.GameUtils.e(40);
        this.aL = new PacketDecoder(this, null);
        this.aL.p = true;
        this.bj = new com.corrodinggames.rts.game.HumanPlayer(-3, false);
        this.bj.v = "SPECTATOR";
        this.bk = new com.corrodinggames.rts.game.HumanPlayer(-1, false);
        this.bk.v = "ADMIN";
    }

    public void P() {
        // 02b ad.java L3280-3285: P() 鐠佹澘缍嶉崣鎴︹偓浣规闂傚瓨鍩?(鐎涙顔孭閸氬苯鎮?
        if (this.au == 0L) {
            this.au = System.currentTimeMillis();
        }
    }
    public void q() {
        this.registerRelayServer(false);
    }

    public void enableChecksum() {
        this.registerRelayServer(true);
    }

    public void resetAllState() {
        this.bH = false;
        this.bG = false;
        this.z = null;
        this.p = false;
        this.bs = System.currentTimeMillis();
        this.X = 0;
        this.I = 0;
        this.w = 1L;
        this.registerRelayServer(1.0f, "new");
        this.Z = 10.0f;
        this.N = false;
        this.Q = 10;
        this.R = 0;
        this.Y = false;
        this.aa = false;
        this.connectionLost = false;
        this.ak = false;
        this.packetsPerSecond = 0.0f;
        this.ac = 0.0f;
        this.packetRateLimit = false;
        this.af = false;
        this.aW = false;
        this.aY = false;
        this.aZ = false;
        this.ba = 0.0f;
        this.debugNetworkOverlay = false;
        this.pingUpdated = false;
        this.highLatencyDetected = false;
        this.packetLossDetected = false;
        this.bandwidthLimited = false;
        this.disconnectStateFlag = false;
        this.resyncStageCounter = -1;
        this.am.a = 0L;
        this.br = false;
        this.am.a();
        this.an = false;
        this.ao = true;
        this.ap = 0;
        this.aq = 0;
        this.ar = 0;
        this.at = 0.0f;
        this.bn = 0.0f;
        this.bo = 0.0f;
        this.bp = 0;
        this.bq = -1000;
        com.corrodinggames.rts.gameFramework.network.SecurityHasher.i = 55;
        com.corrodinggames.rts.gameFramework.network.SecurityHasher.j = 66;
    }

    public void registerRelayServer(boolean bl) {
        this.B = false;
        this.C = false;
        this.f = null;
        this.F = false;
        this.D = false;
        this.E = null;
        this.x = false;
        this.useSteamRelay = false;
        this.G = false;
        this.av = false;
        this.A = false;
        this.resetAllState();
        this.S = null;
        this.m = 0;
        this.i = false;
        this.j = 0.0f;
        this.k = 0.0f;
        this.bz = false;
        this.aB = null;
        this.ax = com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.teamUnitCapHostedGame;
        if (this.ax < 1) {
            this.ax = 1;
        }
        this.syncFrameThreshold = this.ax;
        this.ay.g = 1;
        this.ay.h = 1.0f;
        this.ay.i = false;
        this.ay.j = false;
        this.ay.l = false;
        this.ay.c = 0;
        this.ay.m = false;
        this.ay.n = false;
        this.ay.o = true;
        this.ay.tournamentMode = false;
        this.ay.q = 0;
        this.registerRelayServer();
        this.packetBuffer.c();
        com.corrodinggames.rts.gameFramework.GlobalState.B().bS.clearSelection();
        if ("<CHAT ONLY>".equals(this.ay.b)) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Chat only map selection - restarting");
            this.ay.a();
        }
        if (!bl) {
            com.corrodinggames.rts.game.PlayerState.recalculateEconomy();
        }
        String string = com.corrodinggames.rts.game.units.custom.ag.b(this.o);
    }

    public void emptyPlaceholderT() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void L() {
        // 02b ad.java L3026-3036: L() 閸欐垿鈧?ServerInfo 缂佹瑦澧嶉張澶庣箾閹?(鐎涙顔孡閸氬苯鎮?
        for (PacketDecoder c2 : this.aM) {
            if (c2.p) {
                this.c(c2);
            }
        }
    }
    public synchronized void m(String string) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.GlobalState.e("Disconnect: " + string);
        if (this.C) {
            this.cancelLobbyKickTimer();
            com.corrodinggames.rts.gameFramework.network.WebAPIClient.d();
            if (this.aE != null) {
                this.aE.b();
                try {
                    if (this.aD != null) {
                        this.aD.join();
                    }
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                this.aE = null;
                this.aD = null;
            }
            if (this.aG != null) {
                this.aG.b();
                try {
                    if (this.aF != null) {
                        this.aF.join();
                    }
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                this.aG = null;
                this.aF = null;
            }
            if (this.aH != null) {
                this.aH.cancel();
                this.aH = null;
                this.aI = null;
            }
            if (this.aK != null) {
                this.aK.b();
                this.aK = null;
                this.aJ = null;
            }
        }
        this.q(string);
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().j();
        Object object = this.bl;
        synchronized (object) {
            this.B = false;
            this.C = false;
            this.F = false;
            this.f = null;
            try {
                this.wait(50L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            this.aW = false;
            l2.cb.e();
            l2.e();
            this.updateMultiplayerNotification();
            this.bm = false;
            this.bl.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void waitForSyncComplete() {
        Object object = this.bl;
        synchronized (object) {
            if (!this.B) {
                return;
            }
            this.bm = true;
            try {
                this.bl.wait();
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public void m(PacketDecoder c2) {
        this.aM.remove(c2);
    }

    private synchronized void cleanClosedConnections() {
        Iterator iterator = this.aM.iterator();
        while (iterator.hasNext()) {
            PacketDecoder c2 = (PacketDecoder) iterator.next();
            if (!c2.a) continue;
            iterator.remove();
        }
    }

    void registerRelayServer(byte[] byArray, PacketDecoder c2) {
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aU()) {
            Log.d("RustedWarfare", "Ignoring incoming resync tagged OutputNetStream debug only");
        } else {
            if (c2.u) {
                Log.d("RustedWarfare", "Ignoring desync client save, OutputNetStream past desync was already saved");
                return;
            }
            c2.u = true;
            Log.d("RustedWarfare", "Saving client save for debugging");
            String string = "desyncs/";
            String string2 = "desync_" + com.corrodinggames.rts.gameFramework.GameUtils.a("d MMM yyyy HH.mm.ss") + "_" + c2.c;
            File file = new File(string + string2);
            file.getParentFile().mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byArray);
                fileOutputStream.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    public void triggerDesyncResync() {
        if (this.br) {
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Adding quick resync command");
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Command e2 = l2.cf.gete3();
        e2.i = com.corrodinggames.rts.game.PlayerState.i;
        e2.r = true;
        e2.u = 200;
        l2.bX.registerRelayServer(e2);
        this.br = true;
    }

    public void quickResync() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        OutputNetStream as2 = new OutputNetStream();
        try {
            l2.ca.a(as2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        try {
            as2.a();
        }
        catch (RuntimeException  iOException) {
            iOException.printStackTrace();
        }
        byte[] byArray = as2.d();
        as2.h();
        if (this.C) {
            for (PacketDecoder c2 : this.aM) {
                if (!c2.w) continue;
                c2.w = false;
                c2.v = false;
                this.registerRelayServer(c2, byArray, this.l, false);
            }
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Loading quick resync save data (bytes:" + byArray.length + ")");
        InputNetStream k2 = new InputNetStream(byArray);
        l2.a("Game resync (quick)...", true);
        int n2 = l2.bx;
        int n3 = l2.by;
        l2.ca.a(k2, true, true, true);
        l2.bx = n2;
        l2.by = n3;
        this.X = l2.bx + 1;
        this.disconnectStateFlag = false;
        this.resyncStageCounter = this.X + 1;
        this.am.a = 0L;
        for (PacketDecoder c3 : this.aM) {
            c3.v = false;
        }
        this.br = false;
        ++this.ar;
        this.bn = 0.0f;
        this.bo = 0.0f;
        if (this.bp < 1) {
            ++this.bp;
        }
        this.bq = l2.bx;
    }

    public synchronized void checkAllForDesync() {
        for (PacketDecoder c2 : this.aM) {
            if (c2.w) {
                throw new RuntimeException("Player: " + c2.e() + " has complete desync");
            }
            if (c2.v) {
                throw new RuntimeException("Player: " + c2.e() + " has minor desync");
            }
            if (c2.x != 0) continue;
            throw new RuntimeException("Player: " + c2.e() + " has no sync matches");
        }
    }

    private synchronized void kickTeam(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        this.bn += f2;
        for (Object object : this.aM) {
            if (((PacketDecoder) object).w) {
                bl = true;
            }
            if (!((PacketDecoder) object).v) continue;
            if (this.g) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("desync_count:" + ((PacketDecoder) object).y + " lastResyncTimer:" + this.bn);
            }
            if (((PacketDecoder) object).y >= 4 && !(this.bn > 3600.0f)) continue;
            bl3 = true;
        }
        if (bl3) {
            this.bo += f2;
            if (c && this.bo > 5.0f) {
                bl2 = true;
            }
            if (this.bp == 0) {
                if (this.bo > 60.0f) {
                    bl2 = true;
                }
            } else if (this.bp == 1) {
                if (this.bo > 420.0f) {
                    bl2 = true;
                }
            } else if (this.bp == 0) {
                if (this.bo > 3600.0f) {
                    bl2 = true;
                }
            } else if (this.bp == 3 && this.bo > 14400.0f) {
                bl2 = true;
            }
        }
        if (as && bl2) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("disableDesyncFixing==true, running quick resync instead");
            bl2 = false;
            bl = true;
        }
        if (!bl2 && bl) {
            if (b) {
                this.triggerDesyncResync();
            } else {
                bl2 = true;
            }
        }
        if (bl2) {
            Object object = "";
            for (PacketDecoder c2 : this.aM) {
                if (!c2.w && !c2.v) continue;
                if (!((String)object).equals("")) {
                    object = (String)object + ", ";
                }
                object = (String)object + c2.e();
            }
            this.j("Resyncing game for " + (String)object + "...");
            this.resetDesyncTracking();
            this.registerRelayServer(this.l, false, true);
        }
    }

    private void resetDesyncTracking() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.bn = 0.0f;
        this.bo = 0.0f;
        ++this.bp;
        this.bq = l2.bx;
        for (PacketDecoder c2 : this.aM) {
            c2.w = false;
            c2.v = false;
            c2.x = 0;
        }
    }

    public void c(String string) {
        this.q(string);
    }

    private void q(String string) {
        for (PacketDecoder c2 : this.aM) {
            c2.a(string);
        }
        this.aM.clear();
        this.aN.clear();
        this.aP = 1;
        this.aO = false;
    }

    public long getNextUnitId() {
        long l2;
        boolean bl = false;
        if (bl) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("New id set:" + this.w + 1);
            com.corrodinggames.rts.gameFramework.GlobalState.T();
        }
        if ((l2 = this.w++) == 0L) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("getNextUnitId: id==0");
            com.corrodinggames.rts.gameFramework.GlobalState.T();
        }
        return l2;
    }

    public long getCurrentGameId() {
        return this.w;
    }

    public void registerRelayServer(long l2) {
        this.w = l2;
    }

    public boolean registerRelayServer(boolean bl, int n2) {
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p || !c2.h() || c2.s || c2.D) continue;
            if (bl) {
                this.j("Still waiting on: " + c2.e());
            }
            return false;
        }
        return true;
    }

    public void clearConnectionFlags() {
        for (PacketDecoder c2 : this.aM) {
            c2.C = false;
            c2.D = false;
        }
    }

    public int getConnectedPlayerCount() {
        int n2 = 0;
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p || !c2.h() || c2.s) continue;
            ++n2;
        }
        return n2;
    }

    public int getDistinctPlayerCount() {
        ArrayList<com.corrodinggames.rts.game.HumanPlayer> arrayList = new ArrayList<com.corrodinggames.rts.game.HumanPlayer>();
        int n2 = 0;
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p || !c2.h() || c2.s) continue;
            com.corrodinggames.rts.game.HumanPlayer e2 = c2.z;
            if (e2 != null) {
                if (arrayList.contains(e2)) continue;
                arrayList.add(e2);
            }
            ++n2;
        }
        return n2;
    }

    public int getTotalConnectionCount() {
        int n2 = 0;
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p || c2.s) continue;
            ++n2;
        }
        return n2;
    }

    public int getInGamePlayerCount() {
        int n2 = 0;
        n2 += this.getDistinctPlayerCount();
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aU()) {
            ++n2;
        }
        return n2;
    }

    public void sendIncorrectPassword(String string) {
        Log.b("RustedWarfare", "network:" + string);
    }

    public static void kickTeam(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("network debug: " + string);
    }

    public void kickTeamImpl(String string) {
        Log.d("RustedWarfare", "reportProblem:" + string);
        if (this.aW) {
            this.m(null, -1, null, string);
        } else {
            this.m(null, -1, null, string);
        }
    }

    public static void sendPacketToClients(String string) {
        NetEngine.registerRelayServer(string, true);
    }

    public static void registerRelayServer(String string, boolean bl) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        NetEngine ad2 = l2.bX;
        String string2 = "desync:" + string;
        com.corrodinggames.rts.gameFramework.GlobalState.b(string2);
        com.corrodinggames.rts.gameFramework.GlobalState.T();
        ++ad2.ap;
        if (ad2.ao) {
            String string3;
            if (ad2.ap > 0 || as) {
                bl = true;
            }
            if (ad2.ap > 10) {
                string3 = "<suppressing desync errors>";
                ad2.ao = false;
                bl = true;
            } else {
                string3 = string2;
            }
            if (bl) {
                string3 = "-i " + string3;
            }
            ad2.m(string3);
        }
    }

    public static void registerRelayServer(String string, String string2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        String string3 = string2;
        l2.cb.a(-1, string, string3, l2.bx);
        if (l2.bS != null && l2.bS.selectionGroup != null) {
            l2.bS.selectionGroup.a(string, string3);
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("interfaceEngine/messageInterface==null");
        }
    }

    public void emptyPlaceholderF() {
    }

    public void registerRelayServer(Command e2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        e2.c = this.X;
        e2.g();
        l2.cf.b.add(e2);
    }

    public void checkConnectionPings() {
        for (PacketDecoder c2 : this.aM) {
            if (c2.p && c2.b() != -2 && c2.b() <= 500 && c2.b() >= 0) continue;
        }
    }

    public void showPlayerListPopup() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        String string = "";
        ArrayList arrayList = com.corrodinggames.rts.game.PlayerState.a(true);
        for (PlayerState n2 : (java.util.Collection<PlayerState>) (java.util.Collection) arrayList) {
            if (n2 == null) continue;
            String string2 = "unnamed";
            if (n2.v != null) {
                string2 = n2.v;
            }
            String string3 = " " + n2.getPingDisplayString();
            String string4 = "\u2022";
            string = string + string4 + n2.getPlayerName().toLowerCase() + " [Team " + n2.h() + "] - " + string2 + string3 + "\n";
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("showPlayerListPopup(): Showing playlist messagebox.");
        l2.c("Players", string);
    }

    public void registerRelayServer(float f2) {
        Object object;
        Object object2;
        int n2;
        int n3;
        Object object3;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.at += f2;
        if (this.aZ) {
            if (this.ba > 0.0f) {
                this.ba -= f2 / 60.0f;
                com.corrodinggames.rts.gameFramework.GlobalState.B().bS.a("Returning to battleroom in " + (int)this.ba + "...", 3500);
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Sending returnToBattleroomEvent...");
                this.aZ = false;
                this.cancelNotification((PacketDecoder) null);
            }
        }
        if (this.aY) {
            this.executeReturnToBattleroom();
        }
        if (this.at > 60.0f) {
            this.checkConnectionPings();
            this.at = 0.0f;
        }
        if (this.aW && !this.debugNetworkOverlay) {
            this.debugNetworkOverlay = true;
            object3 = com.corrodinggames.rts.game.PlayerState.getAliveTeamIds();
            n3 = 0;
            n2 = 0;
            Iterator iterator2 = ((ArrayList)object3).iterator();
            while (iterator2.hasNext()) {
                object = (Integer)iterator2.next();
                int n4 = com.corrodinggames.rts.game.PlayerState.a((Integer)object, false);
                if (n4 > n2) {
                    n2 = n4;
                }
                ++n3;
            }
            if (n3 > 0 && n2 <= 1) {
                this.pingUpdated = true;
            }
        }
        if (!this.C && !this.bH) {
            this.ad();
            this.bH = true;
        }
        if (this.C) {
            if (!this.aa && this.aW) {
                if (this.registerRelayServer(false, 0)) {
                    this.Z = com.corrodinggames.rts.gameFramework.GameUtils.a(this.Z, f2);
                    if (this.Z == 0.0f) {
                        this.aa = true;
                        NetEngine.registerRelayServer("", "<All players ready>");
                        this.d.a();
                    }
                } else {
                    this.packetsPerSecond += f2;
                    this.ac += f2;
                    float f3 = 900.0f;
                    if (this.packetsPerSecond > f3) {
                        this.aa = true;
                        NetEngine.registerRelayServer("", "Starting game without all players ready!");
                    } else if (this.ac > 180.0f) {
                        this.ac = 0.0f;
                        this.registerRelayServer(true, (int)((f3 - this.packetsPerSecond) / 60.0f));
                    }
                }
            }
            if (this.aa) {
                boolean bl = false;
                if (this.ak) {
                    bl = true;
                }
                if (this.connectionLost) {
                    bl = true;
                }
                if (l2.bx >= this.X - this.R && !bl) {
                    n3 = this.X + this.Q;
                    ++this.totalPlayers;
                    n2 = 0;
                    for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
                        object = com.corrodinggames.rts.game.PlayerState.u(i2);
                        if (object == null || ((PlayerState) object).V == 0 || ((PlayerState) object).isIdle() || ((PlayerState) object).V >= 40) continue;
                        n2 = 1;
                    }
                    if (l2.b() != 0 && l2.b() < 40 && !com.corrodinggames.rts.gameFramework.GlobalState.aU()) {
                        n2 = 1;
                    }
                    if (n2 != 0) {
                        ++this.P;
                    }
                    if (this.totalPlayers > 8) {
                        float f4 = 1.0f;
                        if (this.P > 4) {
                            f4 = 2.0f;
                        }
                        if (this.K != null) {
                            f4 = this.K.floatValue();
                        }
                        if (f4 != this.c()) {
                            com.corrodinggames.rts.gameFramework.GlobalState.e("Changing step rate to " + f4);
                            object = l2.cf.gete3();
                            ((Command) object).i = com.corrodinggames.rts.game.PlayerState.i;
                            ((Command) object).r = true;
                            ((Command) object).s = f4;
                            this.registerRelayServer((Command) object);
                        }
                        this.totalPlayers = 0;
                        this.P = 0;
                    }
                    object2 = new OutputNetStream();
                    try {
                        ((OutputNetStream) object2).a(n3);
                        int n5 = 0;
                        for (Command e2 : l2.cf.b) {
                            if (e2.c != this.X) continue;
                            ++n5;
                        }
                        ((OutputNetStream) object2).a(n5);
                        for (Command e2 : l2.cf.b) {
                            if (e2.c != this.X) continue;
                            e2.a((OutputNetStream) object2);
                        }
                    }
                    catch (IOException  iOException) {
                        throw new RuntimeException(iOException);
                    }
                    object = ((OutputNetStream) object2).b(10);
                    ((NetworkPacket) object).isSystemPacket = true;
                    this.sendIncorrectPassword((NetworkPacket) object);
                    this.X = n3;
                }
            }
        }
        if (!l2.cf.d.isEmpty()) {
            Iterator iterator3 = l2.cf.d.iterator();
            while (iterator3.hasNext()) {
                Command e3 = (Command) iterator3.next();
                n2 = 0;
                if (n2 != 0) {
                    l2.cf.c.add(e3);
                    iterator3.remove();
                    continue;
                }
                if (!e3.x) {
                    e3.b();
                }
                if (!e3.a()) continue;
                l2.cf.c.add(e3);
                iterator3.remove();
            }
        }
        if (!this.C) {
            if (!l2.cf.c.isEmpty()) {
                for (Command e4 : l2.cf.c) {
                    if (e4.e()) continue;
                    e4.j();
                    OutputNetStream as2 = new OutputNetStream();
                    try {
                        e4.a(as2);
                    }
                    catch (IOException  iOException) {
                        throw new RuntimeException(iOException);
                    }
                    this.sendIncorrectPassword(as2.b(20));
                }
                l2.cf.c.clear();
            }
        } else if (!l2.cf.c.isEmpty()) {
            for (Command e5 : l2.cf.c) {
                if (e5.e()) continue;
                if (!e5.l()) {
                    NetEngine.registerRelayServer("Skipped command issued from server", false);
                    continue;
                }
                e5.j();
                this.registerRelayServer(e5);
            }
            l2.cf.c.clear();
        }
        while (!this.aN.isEmpty()) {
            object3 = (NetworkPacket) this.aN.remove();
            try {
                this.registerRelayServer((NetworkPacket) object3);
            }
            catch (IOException iOException) {
                String string = "None";
                object2 = ((NetworkPacket) object3).connection;
                if (object2 != null) {
                    string = ((PacketDecoder) object2).g();
                    object = iOException.getMessage();
                    if (object == null) {
                        object = "IO error";
                    }
                    ((PacketDecoder) object2).a((String)object);
                    NetEngine.sendPacketToClients("IO error on processGamePacket for " + ((PacketDecoder) object2).e());
                }
                com.corrodinggames.rts.gameFramework.GlobalState.a("Error on processGamePacket ip:" + string, (Throwable)iOException);
            }
        }
        if (this.C) {
            if (!this.B) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping server updates, not networked");
            } else {
                this.cleanClosedConnections();
                if (!this.aj) {
                    this.kickTeam(f2);
                }
            }
        }
        if (this.B) {
            object3 = "Game paused.";
            if (this.connectionLost) {
                l2.bS.b("Game paused.", 100);
            } else {
                l2.bS.a("Game paused.");
            }
        }
        if (l2.bx < this.X) {
            this.Y = false;
        }
        if (this.bm) {
            this.m("queDisconnect");
        }
    }

    public void m(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2 == null) {
            return;
        }
        if (!this.C && this.B) {
            Object object;
            boolean bl = false;
            Iterator iterator4 = this.aM.iterator();
            Object object2;
            while (iterator4.hasNext()) {
                object = (PacketDecoder) iterator4.next();
                if (!((PacketDecoder) object).p || ((PacketDecoder) object).a) continue;
                bl = true;
            }
            if (this.bandwidthLimited && this.extractCommandName5()) {
                l2.bS.b("Game ended by server.");
                com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
            } else if (!bl && this.extractCommandName5()) {
                l2.bS.b("Server Disconnected.");
                com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
            }
            if (bl && (this.Y || this.bs + 1000L < System.currentTimeMillis()) && !this.C && (object2 = this.getClientConnection()) != null && ((PacketDecoder) object2).U > 20000) {
                object = "Receiving network data: " + ((PacketDecoder) object2).V + "/" + ((PacketDecoder) object2).U;
                com.corrodinggames.rts.gameFramework.GlobalState.e((String)object);
                l2.bS.d((String)object);
                if (!this.aW && this.bt + 4000L < System.currentTimeMillis()) {
                    this.bt = System.currentTimeMillis();
                    this.receiveChatMessage((String)object);
                }
                this.registerRelayServer((PacketDecoder) object2, ((PacketDecoder) object2).V, ((PacketDecoder) object2).U);
            }
        }
    }

    public void c(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.bs = System.currentTimeMillis();
        if (this.B && (this.resyncStageCounter + this.ai < l2.bx || this.resyncStageCounter == -1)) {
            this.sendIncorrectPassword();
            l2.cb.a(this.am);
        }
        if ((this.B || l2.cb.j()) && this.N) {
            this.N = false;
            this.quickResync();
        }
        if (!this.B) {
            return;
        }
        if (this.C && !this.an && this.resyncStageCounter + this.ai / 2 < l2.bx && this.resyncStageCounter != -1) {
            try {
                OutputNetStream as2 = new OutputNetStream();
                as2.a(this.resyncStageCounter);
                as2.a(this.am.a);
                as2.a(this.am.b.size());
                for (ChecksumField al2 : this.am.b) {
                    as2.a(al2.b);
                }
                NetworkPacket au2 = as2.b(30);
                this.sendPacketToClients(au2);
                if (this.g) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Sent checksum to client [" + this.resyncStageCounter + "]");
                }
                this.an = true;
            }
            catch (RuntimeException  iOException) {
                throw new RuntimeException(iOException);
            }
        }
    }

    public boolean shouldGameBePaused() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bU.e()) {
            if (!this.bu) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("shouldGameBePaused: isGoingToBlockThisFrame()==true: " + l2.bU.f());
            }
            this.bu = true;
            return true;
        }
        if (this.bu) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("shouldGameBePaused: isGoingToBlockThisFrame()==false");
        }
        this.bu = false;
        return false;
    }

    public void registerRelayServer(float f2, boolean bl) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bx >= this.X) {
            if (l2.bx > this.X) {
                throw new RuntimeException("game frame:" + l2.bx + " is greater then nest step:" + this.X);
            }
            this.Y = true;
        }
        if (bl && this.shouldGameBePaused()) {
            this.Y = true;
        }
    }

    public void registerRelayServer(NetworkPacket au2) throws IOException {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.m(au2)) {
            this.sendIncorrectPassword("filtered packet (type:" + au2.packetLength + ")");
            return;
        }
        switch (au2.packetLength) {
            case 10: {
                if (this.C) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! we don't follow orders");
                    break;
                }
                if (au2.connection.t) {
                    this.sendIncorrectPassword("ignoring command");
                    break;
                }
                InputNetStream k2 = new InputNetStream(au2);
                int n2 = k2.readInt();
                int n3 = k2.readInt();
                for (int i2 = 0; i2 < n3; ++i2) {
                    Command e2 = l2.cf.gete3();
                    e2.c = this.X;
                    e2.a(k2);
                    this.registerRelayServer(e2);
                }
                if (n2 < this.X) {
                    String string = "New nextBlockingFrame:" + n2 + " is smaller than current step:" + this.X;
                    NetEngine.registerRelayServer(string, false);
                }
                this.X = n2;
                break;
            }
            case 20: {
                if (!this.C) {
                    this.sendIncorrectPassword("we are not ByteIndexedMap server! skipping");
                    break;
                }
                InputNetStream k3 = new InputNetStream(au2);
                PacketDecoder c2 = au2.connection;
                if (c2.a()) break;
                com.corrodinggames.rts.game.HumanPlayer e3 = c2.z;
                if (e3 == null) {
                    this.sendIncorrectPassword("Player is null for message ADDCLIENTCOMMAND, skipping");
                    break;
                }
                Command e4 = l2.cf.gete3();
                e4.a(k3);
                e4.p = e3;
                if (e4.r) {
                    this.sendIncorrectPassword("Got system action from client, ignoring (" + c2.c + ")");
                    e4.r = false;
                }
                if (e4.c() == null) {
                    NetEngine.registerRelayServer("Invalid command from '" + e3.v + "', no team found", false);
                    break;
                }
                if (!e4.l()) {
                    NetEngine.registerRelayServer("Ignored command from '" + e3.v + "', check failed", false);
                    break;
                }
                this.registerRelayServer(e4);
                break;
            }
            case 35: {
                InputNetStream k4 = new InputNetStream(au2);
                k4.d();
                int n4 = k4.readInt();
                int n5 = k4.readInt();
                float f2 = k4.readFloat();
                float f3 = k4.readFloat();
                if (!this.C && (double)f2 < 0.1) {
                    NetEngine.registerRelayServer("resync packet with setCurrentStepRate:" + f2 + " is too small", true);
                }
                PacketDecoder c3 = au2.connection;
                if (c3.t) {
                    this.sendIncorrectPassword("ignoring resync command");
                    break;
                }
                boolean bl = k4.readBoolean();
                boolean bl2 = k4.readBoolean();
                if (bl2) {
                    if (!this.C) {
                        this.sendIncorrectPassword("we are not ByteIndexedMap server, but got ByteIndexedMap debug game save! skipping");
                        break;
                    }
                    byte[] byArray = k4.c("gameSave");
                    this.registerRelayServer(byArray, c3);
                    break;
                }
                com.corrodinggames.rts.gameFramework.GlobalState.e("Reloading from network save");
                if (bl && !this.C) {
                    this.registerRelayServer(false, true, false);
                }
                byte[] byArray = k4.c("gameSave");
                com.corrodinggames.rts.gameFramework.GlobalState.e("Save size: " + byArray.length);
                if (this.l) {
                    this.registerRelayServer(byArray, c3);
                }
                l2.cb.a(byArray, l2.bx, n4, n5, f2, f3);
                InputNetStream k5 = new InputNetStream(byArray);
                l2.a("Resyncing game from server...", true);
                l2.ca.a(k5, true, true, true);
                l2.Z();
                ++this.ar;
                l2.bx = n4;
                l2.by = n5;
                this.X = n4 + 1;
                this.disconnectStateFlag = false;
                this.resyncStageCounter = this.X + 1;
                this.am.a = 0L;
                if ((double)f2 < 0.1) {
                    NetEngine.registerRelayServer("resync setCurrentStepRate:" + f2 + " is too small", true);
                }
                this.registerRelayServer(f2, "rsync");
                this.J = f3;
                break;
            }
            case 30: {
                PacketDecoder c4 = au2.connection;
                InputNetStream k6 = new InputNetStream(au2);
                int n6 = k6.readInt();
                long l3 = k6.i();
                if (this.disconnectStateFlag) {
                    this.sendIncorrectPassword("PACKET_SYNCCHECKSUM: skipping frame:" + n6 + ", we were told to wait for resync");
                    break;
                }
                OutputNetStream as2 = new OutputNetStream();
                as2.c(0);
                as2.a(n6);
                as2.a(this.resyncStageCounter);
                if (this.resyncStageCounter != n6 || this.am.a == 0L) {
                    as2.a(false);
                    Log.d("RustedWarfare", "got remoteSyncFrame for:" + n6 + " needed:" + this.resyncStageCounter + " lastSyncCheckSum:" + this.am.a);
                } else {
                    int n7;
                    as2.a(true);
                    Log.d("RustedWarfare", "Running checksum");
                    as2.a(l3);
                    as2.a(this.am.a);
                    boolean bl = false;
                    if (l3 != this.am.a) {
                        NetEngine.registerRelayServer("Checksum doesn't match. Got:" + l3 + " expected:" + this.am.a, false);
                        bl = true;
                        com.corrodinggames.rts.gameFramework.GlobalState.e("--- Desync for frame: " + n6 + " ---");
                        for (Object object : com.corrodinggames.rts.game.PlayerState.c()) {
                            ((PlayerState) object).validateTeamTracker();
                        }
                    } else {
                        ++this.aq;
                    }
                    if ((n7 = k6.readInt()) != this.am.b.size()) {
                        Log.d("RustedWarfare", "checkSumSize!=syncCheckList.size()");
                    }
                    as2.e("checkList");
                    as2.a(n7);
                    as2.a(this.am.b.size());
                    for (ChecksumField al2 : this.am.b) {
                        long l4 = k6.i();
                        as2.a(l4);
                        as2.a(al2.b);
                        if (l4 == al2.b || !al2.c) continue;
                        NetEngine.sendPacketToClients("[" + n6 + "] check(" + al2.a + "): " + l4 + "!=" + al2.b);
                        bl = true;
                    }
                    as2.a("checkList");
                    as2.a(bl);
                }
                if (this.C) break;
                NetworkPacket au3 = as2.b(31);
                this.registerRelayServer(c4, au3);
                break;
            }
            case 31: {
                if (!this.C) {
                    this.sendIncorrectPassword("we are not ByteIndexedMap server, but got PACKET_SYNCCHECKSUM_STATUS");
                    break;
                }
                PacketDecoder c5 = au2.connection;
                InputNetStream k7 = new InputNetStream(au2);
                k7.d();
                int n8 = k7.readInt();
                int n9 = k7.readInt();
                boolean bl = k7.readBoolean();
                if (bl) {
                    long l5 = k7.i();
                    long l6 = k7.i();
                    k7.b("checkList");
                    k7.readInt();
                    int n10 = k7.readInt();
                    if (n10 != this.am.b.size()) {
                        Log.d("RustedWarfare", "checkSumSize!=syncCheckList.size()");
                    }
                    for (ChecksumField al3 : this.am.b) {
                        long l7;
                        long l8 = k7.i();
                        if (l8 == (l7 = k7.i())) continue;
                        com.corrodinggames.rts.gameFramework.GlobalState.b(al3.a + " Checksum [" + n8 + "]. server:" + l8 + " client:" + l7);
                    }
                    k7.d("checkList");
                    boolean bl3 = k7.readBoolean();
                    if (this.bq >= n8) {
                        this.sendIncorrectPassword("Not marking desync, already resynced before frame: " + this.bq + "<=" + n8);
                        break;
                    }
                    if (!c5.v && bl3) {
                        ++c5.y;
                    }
                    c5.v = bl3;
                    if (!bl3) {
                        if (this.g) {
                            com.corrodinggames.rts.gameFramework.GlobalState.e("checksum: client checksum match [" + n8 + "]");
                        }
                        ++c5.x;
                        break;
                    }
                    com.corrodinggames.rts.gameFramework.GlobalState.e("client:" + c5.e() + " desync [" + n8 + "]");
                    if (!this.aj || this.ak) break;
                    NetEngine.registerRelayServer("pauseOnDesync is active, pausing", false);
                    this.ak = true;
                    break;
                }
                if (!this.g) break;
                com.corrodinggames.rts.gameFramework.GlobalState.e("checksum for:" + c5.e() + " frameMatch==false client:" + n9 + " server:[" + n8 + "]");
                break;
            }
            default: {
                this.sendIncorrectPassword("we did not handle packet:" + au2.packetLength);
            }
        }
    }

    public synchronized boolean m(NetworkPacket au2) {
        if (this.C) {
            PacketDecoder c2 = au2.connection;
            if (c2 == null) {
                return false;
            }
            if (!c2.p && au2.packetLength != 105 && au2.packetLength != 110 && au2.packetLength != 111 && au2.packetLength != 108 && au2.packetLength != 160) {
                return true;
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void c(NetworkPacket au2) throws IOException {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.m(au2)) {
            this.sendIncorrectPassword("filtered packet (type:" + au2.packetLength + ")");
            return;
        }
        switch (au2.packetLength) {
            case 105: {
                this.sendIncorrectPassword("got PACKET_GET_SERVER_INFO");
                if (this.C) break;
                this.sendIncorrectPassword("we are not ByteIndexedMap server! skipping");
                break;
            }
            case 106: {
                boolean bl;
                if (this.C) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! we don't follow orders");
                    break;
                }
                InputNetStream k2 = new InputNetStream(au2);
                PacketDecoder c2 = au2.connection;
                k2.readString();
                k2.readInt();
                this.ay.a = (GameModeEnum) k2.b(GameModeEnum.class);
                this.ay.b = k2.readString();
                this.ay.c = k2.readInt();
                this.ay.d = k2.readInt();
                this.ay.e = k2.readBoolean();
                this.ay.f = k2.readInt();
                byte by = k2.d();
                this.G = k2.readBoolean();
                this.useSteamRelay = k2.readBoolean();
                this.av = true;
                if (by >= 1) {
                    this.syncFrameThreshold = k2.readInt();
                    this.ax = k2.readInt();
                }
                if (by >= 0) {
                    this.ay.g = k2.readInt();
                    this.ay.h = k2.readFloat();
                    this.ay.i = k2.readBoolean();
                    this.ay.j = k2.readBoolean();
                }
                if (by >= 3 && (bl = k2.readBoolean())) {
                    try {
                        com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(k2);
                        this.x = true;
                    }
                    catch (bd bd2) {
                        this.m("Missing unit:" + bd2.getMessage() + " d:" + bd2.amountValue);
                        this.m("Server sync mismatch", bd2.getMessage());
                        if (!com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                            l2.i(bd2.getMessage());
                        }
                        String string = "Server sync mismatch";
                        if (bd2.a != null) {
                            string = bd2.a;
                        }
                        l2.d(string, bd2.getMessage());
                        break;
                    }
                }
                if (by >= 4) {
                    this.ay.l = k2.readBoolean();
                }
                if (by >= 5) {
                    this.ay.m = k2.readBoolean();
                }
                if (by >= 6) {
                    this.ay.n = k2.readBoolean();
                }
                if (by >= 7) {
                    this.ay.o = k2.readBoolean();
                    this.ay.tournamentMode = k2.readBoolean();
                }
                if (by >= 8) {
                    this.ay.q = k2.readInt();
                }
                com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
                break;
            }
            case 115: {
                boolean bl;
                int n2;
                if (this.C) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! we don't follow orders");
                    break;
                }
                InputNetStream k3 = new InputNetStream(au2);
                k3.b(au2.connection.E);
                PacketDecoder c3 = au2.connection;
                int n3 = k3.readInt();
                PlayerState n4 = null;
                int n5 = 8;
                boolean bl2 = false;
                if (k3.c() >= 90) {
                    n2 = 0;
                    if (k3.c() >= 141) {
                        n2 = 1;
                        bl2 = k3.readBoolean();
                    }
                    n5 = k3.readInt();
                    com.corrodinggames.rts.game.PlayerState.b(n5, false);
                    k3.a("teams", n2 != 0);
                    if (n5 > com.corrodinggames.rts.game.PlayerState.c) {
                        throw new IOException("Cannot load:" + n5 + " teams");
                    }
                } else if (this.aW) {
                    NetEngine.sendPacketToClients("Warning old team system used in started game, stream version:" + k3.c());
                }
                for (n2 = 0; n2 < n5; ++n2) {
                    PlayerState n6 = com.corrodinggames.rts.game.PlayerState.u(n2);
                    boolean bl3 = k3.readBoolean();
                    if (!bl3) {
                        if (n6 != null) {
                            if (this.aW) {
                                NetEngine.registerRelayServer("Warning team:" + n2 + " removed while game is running", false);
                            }
                            n6.updateResourceDisplay();
                        }
                    } else {
                        int n7 = k3.readInt();
                        if (n6 == null) {
                            if (this.aW) {
                                NetEngine.registerRelayServer("Warning team:" + n2 + " added while game is running", false);
                            }
                            if (!this.C && n6 instanceof com.corrodinggames.rts.game.ai.AIStrategy) {
                                NetEngine.registerRelayServer("Warning we are ByteIndexedMap client with ServerConnector AI team", false);
                            }
                            n6 = new com.corrodinggames.rts.game.HumanPlayer(n2);
                        }
                        if (bl2) {
                            n6.a(k3);
                        } else {
                            n6.a(k3, this.aW);
                        }
                    }
                    if (n6 == null || n6.k != n3) continue;
                    n4 = n6;
                }
                if (k3.c() >= 90) {
                    k3.d("teams");
                }
                this.z = n4;
                this.ay.d = k3.readInt();
                this.ay.c = k3.readInt();
                this.ay.e = k3.readBoolean();
                this.ay.f = k3.readInt();
                n2 = k3.d();
                this.syncFrameThreshold = k3.readInt();
                this.ax = k3.readInt();
                if (n2 >= 0) {
                    this.ay.g = k3.readInt();
                    this.ay.h = k3.readFloat();
                    this.ay.i = k3.readBoolean();
                    this.ay.j = k3.readBoolean();
                }
                if (n2 >= 3 && (bl = k3.readBoolean())) {
                    try {
                        com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(k3);
                        this.x = true;
                    }
                    catch (bd bd3) {
                        this.m("Missing unit:" + bd3.getMessage() + " d:" + bd3.amountValue);
                        this.m("Connection Failed", bd3.getMessage());
                        if (!com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                            l2.i(bd3.getMessage());
                        }
                        l2.d("Connection Failed", bd3.getMessage());
                        break;
                    }
                }
                if (n2 >= 4) {
                    this.ay.l = k3.readBoolean();
                }
                if (n2 >= 5) {
                    this.connectionLost = k3.readBoolean();
                }
                com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
                break;
            }
            case 116: {
                if (this.C) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! we don't follow orders");
                    break;
                }
                InputNetStream k4 = new InputNetStream(au2);
                PacketDecoder c4 = au2.connection;
                int n8 = k4.readInt();
                boolean bl = k4.readBoolean();
                if (!bl || this.bandwidthLimited) break;
                this.bandwidthLimited = bl;
                break;
            }
            case 160: {
                String string;
                InputNetStream k5 = new InputNetStream(au2);
                PacketDecoder c5 = au2.connection;
                String string2 = k5.readString();
                int n9 = k5.readInt();
                int n10 = k5.readInt();
                int n11 = 1;
                if (n9 >= 1) {
                    n11 = k5.readInt();
                }
                if (c5.i) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("steam: request info packet");
                }
                if (n9 >= 0 && (string = k5.j()) != null) {
                    c5.c("Using query string: " + string);
                    c5.o = string;
                }
                if (n9 >= 3) {
                    k5.readString();
                }
                if (n9 >= 4) {
                    string = k5.readString();
                    String string3 = k5.readString();
                    if (com.corrodinggames.rts.gameFramework.GlobalState.aU()) {
                        c5.c("Misc: " + string3);
                    }
                }
                this.sendPacketToClients(c5);
                break;
            }
            case 161: {
                if (this.C) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! we don't PREREGISTER_INFO");
                    break;
                }
                InputNetStream k6 = new InputNetStream(au2);
                PacketDecoder c6 = au2.connection;
                if (c6.i) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("steam: got info packet");
                }
                String string = k6.readString();
                int n12 = k6.readInt();
                int n13 = k6.readInt();
                int n14 = k6.readInt();
                String string4 = k6.readString();
                this.S = k6.readString();
                c6.E = n13;
                if (n12 >= 1) {
                    this.T = k6.readInt();
                }
                if (n12 >= 0) {
                    this.U = k6.readInt();
                    this.V = k6.readInt();
                }
                if (this.bz) {
                    this.sendIncorrectPassword("PACKET_SEND_PREREGISTER_INFO: Register connection has already been sent (resending)");
                }
                this.sendPacketToClients(c6);
                break;
            }
            case 163: {
                if (this.C) {
                    this.sendIncorrectPassword("we are already ByteIndexedMap server");
                    break;
                }
                InputNetStream k7 = new InputNetStream(au2);
                k7.d();
                int n15 = k7.readInt();
                int n16 = k7.readInt();
                String string = k7.j();
                this.sendIncorrectPassword("Relay version: " + n15);
                break;
            }
            case 170: {
                this.sendIncorrectPassword("Got 'become server' packet");
                if (this.C) {
                    this.sendIncorrectPassword("we are already ByteIndexedMap server");
                    break;
                }
                PacketDecoder c7 = au2.connection;
                InputNetStream k8 = new InputNetStream(au2);
                byte by = k8.d();
                boolean bl = k8.readBoolean();
                boolean bl4 = k8.readBoolean();
                String string = k8.j();
                boolean bl5 = k8.readBoolean();
                boolean bl6 = k8.readBoolean();
                String string5 = k8.j();
                boolean bl7 = false;
                if (by >= 1) {
                    bl7 = k8.readBoolean();
                }
                String string6 = null;
                if (by >= 0) {
                    string6 = k8.j();
                }
                this.sendIncorrectPassword("Multicast:" + bl7);
                c7.r = bl7;
                if (bl) {
                    c7.q = true;
                }
                if (bl4) {
                    c7.s = true;
                }
                this.D = true;
                this.E = string5;
                l2.bX.n = null;
                l2.bX.o = bl5;
                l2.bX.q = bl6;
                this.c(false);
                if (string6 != null) {
                    if (this.z != null) {
                        this.z.P = string6;
                    } else {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("Become server: No local team");
                    }
                }
                if (l2.bX.q) {
                    // empty if block
                }
                if (string != null) {
                    l2.bQ.networkServerId = string;
                }
                if (l2.bx > 60) {
                    this.aa = true;
                }
                if (this.x || this.aW) break;
                com.corrodinggames.rts.gameFramework.GlobalState.e("enableAllCustomUnitsPossible mods:" + this.o);
                com.corrodinggames.rts.game.units.custom.ag.b(this.o);
                this.x = true;
                break;
            }
            case 172: {
                PacketDecoder c8 = au2.connection;
                if (!c8.q) {
                    this.sendIncorrectPassword("forwarding not allowed on this connection");
                    break;
                }
                this.sendIncorrectPassword("got FORWARD_CLIENT_ADD");
                InputNetStream k9 = new InputNetStream(au2);
                byte by = k9.d();
                int n17 = k9.readInt();
                String string = k9.readString();
                String string7 = k9.j();
                String string8 = null;
                if (by >= 1) {
                    string8 = k9.j();
                }
                if (this.registerRelayServer(c8, n17) != null) {
                    this.sendIncorrectPassword("Not adding client:" + n17 + " already exists");
                    break;
                }
                PacketDecoder c9 = this.registerRelayServer(c8, n17, string, string8);
                if (c9 == null || string7 == null) break;
                com.corrodinggames.rts.game.HumanPlayer e2 = com.corrodinggames.rts.game.PlayerState.b(string);
                if (e2 == null) {
                    this.sendIncorrectPassword("PACKET_FORWARD_CLIENT_ADD: Failed to find existing player with id:" + string);
                    for (PlayerState n18 : (java.util.Collection<PlayerState>) (java.util.Collection) com.corrodinggames.rts.game.PlayerState.c()) {
                        if (n18 == null) continue;
                        this.sendIncorrectPassword("option: " + n18.v + " - " + n18.P + " - localPlayer:" + (this.z == n18));
                    }
                    break;
                }
                e2.O = string7;
                break;
            }
            case 173: {
                PacketDecoder c10 = au2.connection;
                if (!c10.q) {
                    this.sendIncorrectPassword("forwarding not allowed on this connection");
                    break;
                }
                this.sendIncorrectPassword("got FORWARD_CLIENT_REMOVE");
                InputNetStream k10 = new InputNetStream(au2);
                byte by = k10.d();
                int n19 = k10.readInt();
                String string = null;
                PacketDecoder c11 = this.registerRelayServer(c10, n19);
                if (c11 == null) break;
                this.m(c11, string);
                break;
            }
            case 174: {
                PacketDecoder c12 = au2.connection;
                if (!c12.q) {
                    this.sendIncorrectPassword("forwarding not allowed on this connection");
                    break;
                }
                InputNetStream k11 = new InputNetStream(au2);
                int n20 = k11.readInt();
                byte[] byArray = k11.t();
                PacketDecoder c13 = this.registerRelayServer(c12, n20);
                if (c13 == null) {
                    this.sendIncorrectPassword("PACKET_FORWARD_CLIENT_FROM failed, cannot find client");
                    break;
                }
                if (!(c13.d instanceof SteamSocket)) {
                    this.sendIncorrectPassword("PACKET_FORWARD_CLIENT_FROM failed, socket is wrong type");
                    break;
                }
                SteamSocket h2 = (SteamSocket) c13.d;
                h2.d.a(byArray);
                break;
            }
            case 175: {
                this.sendIncorrectPassword("got PACKET_FORWARD_CLIENT_TO");
                break;
            }
            case 176: {
                this.sendIncorrectPassword("got PACKET_FORWARD_CLIENT_TO_REPEATED");
                break;
            }
            case 178: {
                this.sendIncorrectPassword("got PACKET_RECONNECT_TO");
                PacketDecoder c14 = au2.connection;
                if (this.C && !c14.q) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server, ");
                    break;
                }
                InputNetStream k12 = new InputNetStream(au2);
                k12.d();
                int n21 = k12.readInt();
                boolean bl = k12.readBoolean();
                int n22 = k12.readInt();
                ArrayList<String> arrayList = new ArrayList<String>();
                for (int i2 = 0; i2 < n22; ++i2) {
                    String string = k12.readString();
                    arrayList.add(string);
                }
                this.registerRelayServer(arrayList, bl);
                break;
            }
            case 110: {
                String string;
                ServerInfo aj2;
                this.sendIncorrectPassword("got REGISTER_CONNECTION");
                if (!this.C) {
                    this.sendIncorrectPassword("we are not ByteIndexedMap server! skipping");
                    break;
                }
                InputNetStream k13 = new InputNetStream(au2);
                PacketDecoder c15 = au2.connection;
                String string9 = k13.readString();
                int n23 = k13.readInt();
                int n24 = k13.readInt();
                int n25 = k13.readInt();
                String string10 = k13.readString();
                String string11 = k13.j();
                String string12 = null;
                c15.E = n24;
                if (n23 >= 1) {
                    c15.L = k13.readString();
                }
                if (n23 >= 0) {
                    string12 = k13.readString();
                }
                int n26 = -1;
                if (n23 >= 3) {
                    n26 = k13.readInt();
                }
                String string13 = "MISSING";
                if (n23 >= 4) {
                    string13 = k13.readString();
                }
                String string14 = "";
                if (n23 >= 5) {
                    string14 = k13.readString();
                }
                if (string10.length() > 20) {
                    this.registerRelayServer(c15, "Your username is too long");
                    c15.a("kicked");
                    break;
                }
                if ((string10 = NetEngine.p(string10)).length() < 0) {
                    this.registerRelayServer(c15, "Your username is too short");
                    c15.a("kicked");
                    break;
                }
                com.corrodinggames.rts.game.HumanPlayer e3 = null;
                if (string12 != null && (e3 = com.corrodinggames.rts.game.PlayerState.a(string12)) != null) {
                    this.sendIncorrectPassword("Existing player: " + e3.k + " - " + e3.v);
                }
                if ((aj2 = this.registerRelayServer(c15)) != null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Connection banned for " + aj2.b() + " more seconds");
                    String string15 = aj2.a();
                    this.registerRelayServer(c15, string15);
                    c15.a("kicked");
                    break;
                }
                String string16 = this.d.a(c15, string10, n24, n25, c15.L, e3);
                if (string16 != null) {
                    this.registerRelayServer(c15, string16);
                    c15.a("kicked");
                    break;
                }
                if (n24 < this.e && !this.v) {
                    this.registerRelayServer(c15, "Game is out of date, please update to v" + l2.getVersion());
                    c15.a("kicked");
                    break;
                }
                if (n24 > this.e && !this.v) {
                    this.registerRelayServer(c15, "Your client is newer then the server. Server is on: v" + l2.getVersion());
                    c15.a("kicked");
                    break;
                }
                if (!this.v && n26 != l2.z()) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("New Player kicked: Unit checksum mismatch: clientUnitsChecksum=" + n26 + " game.getAllUnitsChecksum():" + l2.z());
                    this.registerRelayServer(c15, "Your core units are different to the server's core units. Game can not be synchronized");
                    c15.a("kicked");
                    break;
                }
                if (!this.v && !(string = this.sendPacketToClients(c15.M)).equals(string13)) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("New Player kicked: Integrity Check Failed: expectedResponse=" + string + " clientResponse=" + string13);
                    this.registerRelayServer(c15, "Your 'Rusted Warfare' client is different to the server. Game can not be synchronized.");
                    c15.a("kicked");
                    break;
                }
                if (!this.aW && this.ay.tournamentMode) {
                    this.registerRelayServer(c15, "Room is locked. New players cannot join this server.");
                    c15.a("kicked");
                    break;
                }
                if (this.aW && e3 == null && !this.s) {
                    this.registerRelayServer(c15, "A game has already been started on this server");
                    c15.a("kicked");
                    break;
                }
                if (this.n != null && e3 == null && !(string = com.corrodinggames.rts.gameFramework.GameUtils.e(this.n)).equals(string11)) {
                    if (string11 == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("processSystemPacket", "Player tried to join but needs ByteIndexedMap password");
                    } else {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("processSystemPacket", "Player tried to join but had MusicFactory incorrect password");
                    }
                    this.sendIncorrectPassword(c15);
                    break;
                }
                string = this.sendPacketToClients(this.W);
                if (!string.equals(string14)) {
                    c15.c("no extra");
                    c15.N = true;
                }
                if (c15.z == null) {
                    Object object = this.bC;
                    synchronized (object) {
                        int n27 = e3 == null ? com.corrodinggames.rts.game.PlayerState.getUnitCount() : e3.k;
                        if (n27 == -1 && !this.v) {
                            this.registerRelayServer(c15, "No free slots on server");
                            c15.a("no free slots");
                            break;
                        }
                        String string17 = this.d.a(c15, string10);
                        if (string17 != null) {
                            this.registerRelayServer(c15, string17);
                            c15.a("kicked");
                        } else {
                            Object object2;
                            com.corrodinggames.rts.gameFramework.network.SecurityHasher.a(c15);
                            if (!this.v && c15.O) {
                                this.registerRelayServer(c15, "");
                                c15.a("kicked");
                                break;
                            }
                            String string18 = null;
                            if (e3 != null) {
                                c15.z = e3;
                                object2 = "";
                                if (this.aW) {
                                    object2 = e3.b() ? " (Spectator)" : " (Team " + e3.h() + ")";
                                }
                                this.j("'" + c15.z.v + "' reconnected. " + (String)object2);
                                c15.w = true;
                                string18 = e3.v;
                                e3.P = c15.m;
                            } else {
                                if (this.v && n27 == -1) {
                                    c15.z = new com.corrodinggames.rts.game.HumanPlayer(-3);
                                } else {
                                    c15.z = new com.corrodinggames.rts.game.HumanPlayer(n27);
                                    c15.z.r = n27 % 2;
                                }
                                if (this.aW && this.s) {
                                    c15.w = true;
                                }
                            }
                            if (e3 == null && string10 != null) {
                                object2 = this.getAllTeamsSynchronized();
                                for (int i3 = 0; i3 < 10; ++i3) {
                                    boolean bl = false;
                                    String string19 = string10;
                                    if (i3 > 0) {
                                        string19 = string19 + "(" + i3 + ")";
                                    }
                                    Iterator iterator = ((ArrayList)object2).iterator();
                                    while (iterator.hasNext()) {
                                        PlayerState n28 = (PlayerState) iterator.next();
                                        if (!string19.equalsIgnoreCase(n28.v)) continue;
                                        bl = true;
                                    }
                                    if (bl) continue;
                                    string10 = string19;
                                    break;
                                }
                            }
                            c15.z.v = string10;
                            c15.z.O = string12;
                            c15.z.P = c15.m;
                            c15.E = n24;
                            com.corrodinggames.rts.gameFramework.GlobalState.b("processSystemPacket", "New player: " + string10 + ", networkVersion:" + c15.E + " existing:" + (e3 != null));
                            c15.p = true;
                            if (e3 == null) {
                                this.d.a(c15.z);
                            }
                            com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
                            this.kickTeam(c15);
                            this.c(c15);
                            this.d.c(c15, string10, string18);
                            if ((e3 != null || this.s) && this.aW) {
                                boolean bl = true;
                                this.registerRelayServer(c15, bl);
                            }
                        }
                        break;
                    }
                }
                com.corrodinggames.rts.gameFramework.GlobalState.b("processSystemPacket", "This connection already has ByteIndexedMap player");
                break;
            }
            case 113: {
                if (this.C) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! skipping: " + au2.packetLength);
                    break;
                }
                NetEngine.a(bE);
                break;
            }
            case 117: {
                PacketDecoder c16 = au2.connection;
                if (this.C && !c16.q) {
                    this.sendIncorrectPassword("we are ByteIndexedMap server! skipping: " + au2.packetLength);
                    break;
                }
                InputNetStream k14 = new InputNetStream(au2);
                k14.d();
                int n29 = k14.readInt();
                String string = k14.readString();
                PasswordManager ae2 = new PasswordManager();
                ae2.isRequired = true;
                ae2.minLength = n29;
                ae2.passwordHash = string;
                NetEngine.a(ae2);
                break;
            }
            case 118: {
                break;
            }
            case 140: {
                if (!this.C) {
                    this.sendIncorrectPassword("we are not ByteIndexedMap server! skipping");
                    break;
                }
                PacketDecoder c17 = au2.connection;
                InputNetStream k15 = new InputNetStream(au2);
                com.corrodinggames.rts.game.HumanPlayer e4 = c17.z;
                if (e4 == null) {
                    if (c17.q) {
                        this.sendIncorrectPassword("Allowing message from non player on forwarding connection");
                        e4 = this.bk;
                    } else {
                        this.sendIncorrectPassword("player is null for message, skipping");
                        break;
                    }
                }
                String string = k15.readString();
                k15.d();
                string = NetEngine.cancelNotification(string);
                if (!this.d.a(c17, e4.v, string)) break;
                if (this.packetBuffer.a(c17, 60000) > this.h) {
                    if (com.corrodinggames.rts.gameFramework.GameUtils.a(c17.g, System.nanoTime()) > 60000L) {
                        c17.g = System.nanoTime();
                        this.j("Anti-spam: Too many messages from '" + c17.e() + "'");
                    }
                    if (!this.g) break;
                    com.corrodinggames.rts.gameFramework.GlobalState.e("extraDebug:" + string);
                    break;
                }
                this.registerRelayServer(c17, e4, e4.v, string);
                this.d.b(c17, e4.v, string);
                this.m(c17, e4, e4.v, string);
                break;
            }
            case 141: {
                Object object;
                if (this.C) {
                    object = au2.connection;
                    if (!((PacketDecoder) object).q) {
                        this.sendIncorrectPassword("error, we are ByteIndexedMap server but got: PACKET_RECEIVE_CHAT_FROM_SERVER");
                        break;
                    }
                }
                object = new InputNetStream(au2);
                String string = ((InputNetStream) object).readString();
                byte by = ((InputNetStream) object).d();
                String string20 = ((InputNetStream) object).j();
                ((InputNetStream) object).readInt();
                int n30 = -1;
                if (by >= 3) {
                    n30 = ((InputNetStream) object).readInt();
                }
                this.m(null, n30, string20, string);
                break;
            }
            case 122: {
                if (this.C) {
                    this.sendIncorrectPassword("error, we are ByteIndexedMap server but got: PACKET_RETURN_TO_BATTLEROOM");
                    break;
                }
                this.setReturnToBattleroomFlag();
                break;
            }
            case 120: {
                if (this.C) {
                    this.sendIncorrectPassword("error, we are ByteIndexedMap server but got: PACKET_START_GAME");
                    break;
                }
                InputNetStream k16 = new InputNetStream(au2);
                k16.d();
                this.ay.a = (GameModeEnum) k16.b(GameModeEnum.class);
                if (this.ay.a == com.corrodinggames.rts.gameFramework.network.GameModeEnum.c) {
                    this.inputNetStream = k16.u();
                } else if (this.ay.a == com.corrodinggames.rts.gameFramework.network.GameModeEnum.b) {
                    this.aB = k16.u();
                }
                this.az = k16.readString();
                this.onNetworkGameStarted();
                break;
            }
            case 150: {
                if (this.C) {
                    this.sendIncorrectPassword("error, we are ByteIndexedMap server but got: PACKET_SEND_KICK");
                    break;
                }
                InputNetStream k17 = new InputNetStream(au2);
                String string = k17.readString();
                string = com.corrodinggames.rts.gameFramework.steam.Localization.c(string);
                this.sendIncorrectPassword("we got kicked, reason:" + string);
                this.m("I was kicked");
                this.m("Kicked", "Kicked: " + string);
                l2.d("Kicked", "Kicked: " + string);
                l2.i("Kicked: " + string);
                break;
            }
            case 151: {
                int n31;
                String string;
                PacketDecoder c18 = au2.connection;
                if (this.C && !c18.q) {
                    this.sendIncorrectPassword("error, we are ByteIndexedMap server but got: 151");
                    break;
                }
                long l3 = com.corrodinggames.rts.gameFramework.ExtraManager.a();
                InputNetStream k18 = new InputNetStream(au2);
                int n32 = k18.readInt();
                int n33 = k18.readInt();
                if (k18.readBoolean()) {
                    com.corrodinggames.rts.gameFramework.network.SecurityHasher.i = k18.readInt();
                }
                if (k18.readBoolean()) {
                    com.corrodinggames.rts.gameFramework.network.SecurityHasher.j = k18.readInt();
                }
                String string21 = "";
                if (n33 == 0) {
                    string21 = "" + com.corrodinggames.rts.gameFramework.network.SecurityHasher.i;
                }
                if (n33 == 1) {
                    string21 = "" + com.corrodinggames.rts.gameFramework.network.SecurityHasher.j;
                }
                if (n33 == 0) {
                    string21 = this.sendPacketToClients(com.corrodinggames.rts.gameFramework.network.SecurityHasher.i);
                }
                if (n33 == 3) {
                    string21 = com.corrodinggames.rts.gameFramework.GameUtils.c(com.corrodinggames.rts.gameFramework.network.SecurityHasher.i + "|" + com.corrodinggames.rts.gameFramework.network.SecurityHasher.j);
                }
                if (n33 == 4) {
                    string21 = com.corrodinggames.rts.gameFramework.GameUtils.c(com.corrodinggames.rts.gameFramework.network.SecurityHasher.i + "|" + com.corrodinggames.rts.gameFramework.network.SecurityHasher.j);
                }
                if (n33 == 5 || n33 == 6) {
                    string = k18.readString();
                    String string22 = k18.readString();
                    n31 = k18.readInt();
                    if (n33 == 6) {
                        string22 = string22 + com.corrodinggames.rts.gameFramework.network.SecurityHasher.i;
                    }
                    if (n31 > 10000000) {
                        string21 = "max";
                    } else {
                        string21 = "-1";
                        for (int i4 = 0; i4 <= n31; ++i4) {
                            if (!com.corrodinggames.rts.gameFramework.GameUtils.c(string22 + i4).equals(string)) continue;
                            string21 = "" + i4;
                            break;
                        }
                    }
                }
                if (n33 == 7) {
                    string = k18.readString();
                    int n34 = k18.readInt();
                    if (n34 > 10000) {
                        string21 = "max";
                    } else {
                        string21 = "";
                        for (n31 = 0; n31 < n34; ++n31) {
                            string21 = string21 + string;
                        }
                    }
                }
                float f2 = com.corrodinggames.rts.gameFramework.ExtraManager.a(l3);
                OutputNetStream as2 = new OutputNetStream();
                as2.a(n32);
                as2.a(n33);
                as2.c(string21);
                as2.a(f2);
                this.registerRelayServer(c18, as2.b(152));
                break;
            }
            case 112: {
                if (!this.C) {
                    this.sendIncorrectPassword("we are not ByteIndexedMap server! skipping");
                    break;
                }
                PacketDecoder c19 = au2.connection;
                InputNetStream k19 = new InputNetStream(au2);
                c19.C = k19.readBoolean();
                c19.D = k19.readBoolean();
                break;
            }
            case 108: {
                PacketDecoder c20 = au2.connection;
                InputNetStream k20 = new InputNetStream(au2);
                long l4 = k20.i();
                k20.d();
                OutputNetStream as3 = new OutputNetStream();
                as3.a(l4);
                as3.c(1);
                int n35 = l2.b();
                if (n35 > 130) {
                    n35 = 130;
                }
                as3.c(n35);
                NetworkPacket au3 = as3.b(109);
                this.registerRelayServer(c20, au3);
                break;
            }
            case 109: {
                int n36;
                if (!this.C) {
                    this.sendIncorrectPassword("we are not ByteIndexedMap server! skipping");
                    break;
                }
                long l5 = System.currentTimeMillis();
                PacketDecoder c21 = au2.connection;
                InputNetStream k21 = new InputNetStream(au2);
                long l6 = k21.i();
                byte by = k21.d();
                byte by2 = 0;
                if (by >= 1) {
                    by2 = k21.d();
                }
                c21.A = n36 = (int)(l5 - l6);
                c21.B = l5;
                if (c21.z != null) {
                    c21.z.W = n36;
                    c21.z.X = l5;
                    c21.z.V = by2;
                }
                if (c21.q && this.C && this.D && this.z != null) {
                    this.z.W = n36;
                    this.z.X = l5;
                }
                if (this.aW) break;
                com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
                break;
            }
            case 4: {
                PacketDecoder c22 = au2.connection;
                InputNetStream k22 = new InputNetStream(au2);
                byte by = k22.d();
                k22.readInt();
                k22.readInt();
                break;
            }
            case 111: {
                InputNetStream k23 = new InputNetStream(au2);
                PacketDecoder c23 = au2.connection;
                String string = null;
                try {
                    string = k23.readString();
                }
                catch (RuntimeException  iOException) {
                    com.corrodinggames.rts.gameFramework.GlobalState.a("Error reading disconnect reason", (Throwable)iOException);
                }
                this.sendIncorrectPassword("Got ByteIndexedMap disconnect packet:" + string);
                if (c23 != null) {
                    c23.a(false, false, string);
                }
                if (this.C) break;
                break;
            }
            default: {
                this.sendIncorrectPassword("we did not handle packet:" + au2.packetLength);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public static String cancelNotification(String string) {
        if (string == null) {
            return null;
        }
        if (string.length() > 250) {
            string = string.substring(0, 250);
        }
        if (string.contains("\n")) {
            string = string.replace("\n", "?");
        }
        string = string.replace("\u0000", ".");
        boolean bl = false;
        for (char n2 : string.toCharArray()) {
            if (!Character.isISOControl(n2)) continue;
            bl = true;
            break;
        }
        if (bl) {

            StringBuilder stringBuilder = new StringBuilder();
            char[] cArray = string.toCharArray();
            int n2 = cArray.length;
            for (int i2 = 0; i2 < n2; ++i2) {
                char c2 = cArray[i2];
                if (!Character.isISOControl(c2)) {
                    stringBuilder.append(c2);
                }
            }
            string = stringBuilder.toString();
        }
        return string;
    }

    public void dismissInterfaceOverlay() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bS.g.m();
    }

    public void closeBattleroom() {
        this.m((String)null, (String)null);
    }

    public void performNATPunchthrough(String string) {
        // 02b ad.java L4201-4218: m(String) 閸欐垿鈧浇浜版径鈺傜Х閹?婢跺嫮鎮婇崨鎴掓姢
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("sendChatMessage: not networked:" + string);
            this.m((PacketDecoder) null, -1, (String) null, string);
        } else if (this.C) {
            this.registerRelayServer((PacketDecoder) null, this.z, this.y, string);
            this.m((PacketDecoder) null, this.z, this.y, string);
        } else {
            try {
                OutputNetStream as2 = new OutputNetStream();
                as2.c(string);
                as2.c(0);
                this.sendPacketToClients(as2.b(140));
            } catch (RuntimeException iOException) {
                throw new RuntimeException(iOException);
            }
        }
    }
    public void m(String string, String string2) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("closeBattleroom..");
        com.corrodinggames.rts.appFramework.DialogHelper.a(string, string2);
        this.d.d();
    }

    public synchronized void logAllConnections() {
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p) continue;
            this.c(c2);
        }
    }

    public synchronized void c(PacketDecoder c2) {
        if (!this.C) {
            this.sendIncorrectPassword("sendServerInfo: we are not ByteIndexedMap server!");
            return;
        }
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.c("com.corrodinggames.rts");
            as2.a(this.e);
            as2.a(this.ay.a);
            if (this.v) {
                as2.c("<CHAT ONLY>");
            } else {
                as2.c(this.ay.b == null ? "<NULL>" : com.corrodinggames.rts.gameFramework.filesystem.FileLoader.o(this.ay.b));
            }
            as2.a(this.ay.c);
            as2.a(this.ay.d);
            as2.a(this.ay.e);
            as2.a(this.ay.f);
            as2.c(8);
            as2.a(this.d.a(c2));
            boolean bl = this.d.b(c2);
            as2.a(bl);
            as2.a(this.syncFrameThreshold);
            as2.a(this.ax);
            as2.a(this.ay.g);
            as2.a(this.ay.h);
            as2.a(this.ay.i);
            as2.a(this.ay.j);
            if (this.v) {
                as2.a(false);
            } else {
                as2.a(true);
                com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(as2);
            }
            as2.a(this.ay.l);
            as2.a(this.ay.m);
            as2.a(this.ay.n);
            as2.a(this.ay.o);
            as2.a(this.ay.tournamentMode);
            as2.a(this.ay.q);
        }
        catch (IOException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.registerRelayServer(c2, as2.b(106));
    }

    public synchronized void registerRelayServer(PacketDecoder c2, String string) {
        if (!this.C) {
            this.sendIncorrectPassword("sendKick: we are not ByteIndexedMap server!");
            return;
        }
        this.sendIncorrectPassword("kicking client reason:" + string);
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.c(string);
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.registerRelayServer(c2, as2.b(150));
    }

    public synchronized void sendIncorrectPassword(PacketDecoder c2) {
        if (!this.C) {
            this.sendIncorrectPassword("sendIncorrectPassword: we are not ByteIndexedMap server!");
            return;
        }
        this.sendIncorrectPassword("sendIncorrectPassword");
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.a(0);
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.registerRelayServer(c2, as2.b(113));
    }

    public void connectToServer() {
        if (this.C) {
            PlayerState n2;
            int n3;
            for (n3 = 0; n3 < com.corrodinggames.rts.game.PlayerState.f; ++n3) {
                n2 = com.corrodinggames.rts.game.PlayerState.u(n3);
                if (n2 == null) continue;
                n2.ac = this.v ? 0 : (n2.b() ? 100 : n2.r);
                if (n2.b()) {
                    n2.D = -1;
                    continue;
                }
                int n4 = n2.getAllyCount();
                if (n2.C != null) {
                    n4 = n2.C;
                } else if (this.registerRelayServer(n4, (PlayerState) null)) {
                    n4 = -1;
                }
                n2.D = n4;
            }
            for (n3 = 0; n3 < com.corrodinggames.rts.game.PlayerState.f; ++n3) {
                n2 = com.corrodinggames.rts.game.PlayerState.u(n3);
                if (n2 == null || n2.D != -1 || n2.b()) continue;
                n2.D = this.disconnect();
            }
        }
    }

    public int disconnect() {
        for (int i2 = 0; i2 < 10; ++i2) {
            if (this.kickTeamImpl(i2)) continue;
            return i2;
        }
        return -1;
    }

    public boolean kickTeamImpl(int n2) {
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.f; ++i2) {
            PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (n3 == null || n3.D != n2 || n3.b()) continue;
            return true;
        }
        return false;
    }

    public boolean registerRelayServer(int n2, PlayerState n3) {
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.f; ++i2) {
            PlayerState n4 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (n4 == null || n4 == n3 || n4.C == null || n4.C != n2 || n4.b()) continue;
            return true;
        }
        return false;
    }

    public void startServer() {
        if (this.C) {
            long l2 = System.currentTimeMillis();
            int n2 = com.corrodinggames.rts.gameFramework.GlobalState.B().by;
            if (this.z != null && !this.D) {
                this.z.W = -99;
                this.z.X = l2;
            }
            this.connectToServer();
            for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
                PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(i2);
                if (n3 == null) continue;
                boolean bl = this.z == n3;
                n3.c(bl);
                if (!this.aW) {
                    // empty if block
                }
                if (!this.aW || this.F || n3.w) continue;
                boolean bl2 = false;
                if (n3.isIdle()) {
                    bl2 = true;
                }
                long l3 = 60000L;
                if (n3.Z > 180000) {
                    l3 = 160000L;
                }
                boolean bl3 = false;
                if (this.aa) {
                    if (n3.Y == -1L) {
                        n3.Y = l2;
                        n3.Z = n2;
                    }
                    if ((this.ak || this.connectionLost) && !n3.ab) {
                        n3.Y = l2;
                        n3.Z = n2;
                    }
                    if (n3.Y + l3 < l2) {
                        bl3 = true;
                    }
                }
                if (n3.ab != bl3) {
                    n3.ab = bl3;
                }
                if (bl3) {
                    bl2 = true;
                    if (!n3.aa) {
                        boolean bl4;
                        boolean bl5 = bl4 = n3.G || n3.F || n3.J || n3.b();
                        if (!bl4) {
                            n3.aa = true;
                        }
                    }
                }
                if (n3.J == bl2) continue;
                if (!(!bl2 || n3.G || n3.F || n3.I || n3.b())) {
                    String string = "-t [Sharing control due to disconnect]";
                    if (bl3) {
                        string = "-t [Sharing control due to afk]";
                    }
                    com.corrodinggames.rts.gameFramework.GlobalState.e(n3.v + " - " + string);
                    int n4 = com.corrodinggames.rts.game.PlayerState.a(n3.r, true);
                    if (n4 > 1) {
                        this.registerRelayServer(null, n3, n3.v, string);
                    }
                }
                n3.J = bl2;
            }
        }
    }

    public void stopServer() {
        if (this.au == 0L) {
            this.au = System.currentTimeMillis();
        }
    }

    public void sendSync() {
        this.au = 0L;
        this.kickTeam((PacketDecoder) null);
    }

    public void kickTeam(PacketDecoder c2) {
        if (!this.C) {
            this.sendIncorrectPassword("sendUpdatePlayer: we are not ByteIndexedMap server!");
            return;
        }
        this.startServer();
        for (PacketDecoder c3 : this.aM) {
            int n2;
            if (!c3.p) continue;
            OutputNetStream as2 = new OutputNetStream(c3.E);
            try {
                int n3;
                as2.a(c3.c());
                n2 = com.corrodinggames.rts.game.PlayerState.c;
                boolean bl = false;
                if (as2.g() >= 90) {
                    n3 = 0;
                    if (as2.g() >= 141) {
                        n3 = 1;
                        if (this.aW && c3.Q) {
                            bl = true;
                        }
                        as2.a(bl);
                    }
                    as2.a(n2);
                    as2.a("teams", n3 != 0);
                } else {
                    n2 = 8;
                    if (!this.v) {
                        this.sendIncorrectPassword("sendUpdatePlayer: warning saving with lower team count");
                    }
                }
                for (n3 = 0; n3 < n2; ++n3) {
                    PlayerState n4 = com.corrodinggames.rts.game.PlayerState.u(n3);
                    as2.a(n4 != null);
                    if (n4 == null) continue;
                    int n5 = 0;
                    if (n4 instanceof com.corrodinggames.rts.game.ai.AIStrategy) {
                        n5 = 1;
                    }
                    as2.a(n5);
                    if (bl) {
                        n4.c(as2);
                        continue;
                    }
                    n4.b(as2);
                }
                if (as2.g() >= 90) {
                    as2.a("teams");
                }
                as2.a(this.ay.d);
                as2.a(this.ay.c);
                as2.a(this.ay.e);
                as2.a(this.ay.f);
                as2.c(5);
                as2.a(this.syncFrameThreshold);
                as2.a(this.ax);
                as2.a(this.ay.g);
                as2.a(this.ay.h);
                as2.a(this.ay.i);
                as2.a(this.ay.j);
                as2.a(false);
                as2.a(this.ay.l);
                as2.a(this.connectionLost);
            }
            catch (IOException  iOException) {
                throw new RuntimeException(iOException);
            }
            n2 = -1;
            if (c2 == c3 && c3.E <= 26) {
                n2 = 1000;
            }
            c3.Q = true;
            this.registerRelayServer(c3, as2.a(115, n2));
        }
    }

    public void registerRelayServer(PacketDecoder c2, int n2, int n3) {
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.c(0);
            as2.a(n2);
            as2.a(n3);
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.registerRelayServer(c2, as2.b(4));
    }

    public synchronized boolean processPackets() {
        if (this.updateConnections()) {
            this.p = true;
            this.ay.d = 0;
            return true;
        }
        return false;
    }

    public synchronized boolean updateConnections() {
        if (this.B) {
            this.m("Started singleplayer");
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.enableChecksum();
        this.B = true;
        this.C = true;
        this.F = true;
        this.ay.a = l2.getMapType();
        this.ay.b = l2.getSanitizedMapName();
        this.generateServerUUID();
        this.z = l2.bs;
        com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
        this.m = l2.bQ.networkPort;
        this.sendIncorrectPassword("singleplayer server started");
        return true;
    }

    private void randomizeConnectionLock() {
        this.ay.q = com.corrodinggames.rts.gameFramework.GameUtils.a(1, 1000000000);
    }

    public synchronized boolean m(boolean bl) {
        if (this.B) {
            throw new RuntimeException("networking already started");
        }
        this.q();
        this.B = true;
        this.C = true;
        this.generateServerUUID();
        this.randomizeConnectionLock();
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.c(bl);
        com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
        this.m = l2.bQ.networkPort;
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().i();
        this.aE = new ServerListener(this);
        try {
            this.aE.a(false);
        }
        catch (IOException  iOException) {
            iOException.printStackTrace();
            l2.a("Could not open tcp port:" + this.m + ", check this port is not in use or change the port in the game settings", 1);
            this.m("Could not open tcp port");
            return false;
        }
        this.aD = new Thread(this.aE);
        this.aD.setDaemon(true);
        this.aD.start();
        boolean bl2 = true;
        if (bl2) {
            this.aG = new ServerListener(this);
            try {
                this.aG.a(true);
            }
            catch (IOException  iOException) {
                iOException.printStackTrace();
                l2.a("Could not open udp port:" + this.m + ", check this port is not in use or change the port in the game settings", 1);
                this.m("Could not open udp port");
                return false;
            }
            this.aF = new Thread(this.aG);
            this.aF.start();
        }
        this.updateMultiplayerNotification();
        if (this.q) {
            com.corrodinggames.rts.gameFramework.network.WebAPIClient.b();
        }
        this.aV = null;
        if (r) {
            com.corrodinggames.rts.gameFramework.network.WebAPIClient.a();
        }
        this.sendIncorrectPassword("server started");
        return true;
    }

    public void c(boolean bl) {
        this.C = true;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.z == null) {
            int n2;
            com.corrodinggames.rts.game.HumanPlayer e2 = null;
            if (!bl) {
                n2 = com.corrodinggames.rts.game.PlayerState.getUnitCount();
                if (n2 == -1) {
                    throw new RuntimeException("playerId is -1 for server player");
                }
            } else {
                e2 = this.bk;
                n2 = this.bk.k;
            }
            if (e2 == null) {
                e2 = new com.corrodinggames.rts.game.HumanPlayer(n2);
                e2.v = this.y;
                l2.bs = e2;
            }
            this.z = e2;
        }
        if (this.aI == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("pingerTask starting");
            this.aI = new KeepAliveTimer(this);
            this.aH = new Timer();
            this.aH.schedule((TimerTask)this.aI, 100L, 100L);
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.e("pingerTask already active");
        }
        com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
    }

    public boolean broadcastCommand() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bQ.udpInMultiplayer;
    }

    public ServerConnector registerRelayServer(String string, boolean bl, Runnable runnable) {
        ServerConnector an2 = new ServerConnector(string, bl, runnable);
        an2.b();
        return an2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Socket m(String string, boolean bl) throws IOException, NetworkException {
        InetSocketAddress inetSocketAddress;
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.GlobalState.e("Connect to server: " + string + " (force tcp:" + bl + ")");
        boolean bl2 = false;
        String string2 = string.trim();
        if (string2.startsWith("get|")) {
            Object object2;
            Object object3;
            Object object4;
            boolean bl3;
            int n2;
            String string3;
            String[] stringArray = string2.split("\\|");
            try {
                String string4 = stringArray[0];
                string3 = stringArray[1];
                n2 = Integer.parseInt(stringArray[0]);
                bl3 = Boolean.parseBoolean(stringArray[3]);
                int n3 = Integer.parseInt(stringArray[4]);
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                String string5 = "Bad server connect string";
                throw new IOException(string5);
            }
            if (bl3) {
                l2.bX.n = null;
                object4 = new Object();
                object3 = new NetEngine$1(object4);
                com.corrodinggames.rts.gameFramework.GlobalState.e("Asking for password..");
                object2 = object4;
                synchronized (object2) {
                    NetEngine.a((PasswordManager) object3);
                    try {
                        object4.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                if (l2.bX.n == null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("No password entered");
                    throw new NetworkException();
                }
                com.corrodinggames.rts.gameFramework.GlobalState.e("Password has been entered");
            }
            object4 = null;
            if (bl3 && (object4 = l2.bX.n) == null) {
                throw new IOException("This server requires ByteIndexedMap password but no password was provided");
            }
            object3 = new Object();
            object2 = new NetEngine$2(object3);
            Object object5 = object3;
            synchronized (object5) {
                com.corrodinggames.rts.gameFramework.network.WebAPIClient.a((ServerResult) object2, string3, n2, (String)object4);
                try {
                    object3.wait(15000L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
            if (((ServerResult) object2).errorString != null) {
                throw new IOException(((ServerResult) object2).errorString);
            }
            if (((ServerResult) object2).resultString == null) {
                throw new IOException("Failed to get game server info.");
            }
            object5 = NetEngine.m(((ServerResult) object2).resultString, bl);
            return (Socket) object5;
        }
        if (string2.toLowerCase(Locale.ENGLISH).endsWith(".relay")) {
            string2 = string2 + ".corrodinggames.com";
        }
        if (string2.startsWith("[TCP]")) {
            string2 = string2.substring("[TCP]".length());
            bl = true;
        }
        if (!(string2.length() <= 4 || string2.contains(":") || string2.contains(".") || string2.equals("localhost") || string2.contains("/") || string2.contains("\\"))) {
            String string6 = ".relay.corrodinggames.com";
            String string7 = "" + string2.charAt(0);
            String string8 = string7 + string6 + "/" + string2;
            com.corrodinggames.rts.gameFramework.GlobalState.e("Converting connect string to: " + string8);
            string2 = string8;
        }
        l2.bX.L = null;
        if (string2.contains("/") || string2.contains("\\")) {
            int n4 = string2.indexOf("/");
            int n5 = string2.indexOf("\\");
            if (n4 == -1) {
                n4 = string2.length();
            }
            if (n5 == -1) {
                n5 = string2.length();
            }
            int n6 = com.corrodinggames.rts.gameFramework.GameUtils.c(n4, n5);
            object = string2.substring(n6 + 1);
            if (!((String)(object = ((String)object).trim())).equals("")) {
                l2.bX.L = (String) object;
            }
            string2 = string2.substring(0, n6);
        }
        String string9 = string2;
        int n7 = 5123;
        String[] stringArray = string2.split(":");
        if (stringArray.length > 1) {
            string9 = null;
            for (int i2 = 0; i2 < stringArray.length - 1; ++i2) {
                string9 = string9 == null ? "" : string9 + ":";
                string9 = string9 + stringArray[i2];
            }
            object = stringArray[stringArray.length - 1];
            try {
                n7 = Integer.parseInt((String)object);
            }
            catch (NumberFormatException numberFormatException) {
                String string10 = "Bad port number:" + (String)object;
                numberFormatException.printStackTrace();
                throw new IOException(string10);
            }
        }
        if (!bl && l2.bX.broadcastCommand()) {
            bl2 = true;
        }
        int n8 = 7000;
        com.corrodinggames.rts.gameFramework.GlobalState.e("");
        com.corrodinggames.rts.gameFramework.GlobalState.e("===============================");
        com.corrodinggames.rts.gameFramework.GlobalState.e("Connect to: " + string2);
        if (!bl2) {
            object = new Socket();
            com.corrodinggames.rts.gameFramework.GlobalState.e("connecting to Server.. (tcp)");
        } else {
            object = new network.reliableudp.ReliableSocket();
            com.corrodinggames.rts.gameFramework.GlobalState.e("connecting to Server.. (udp)");
            n8 = 5000;
        }
        ((Socket)object).setTcpNoDelay(true);
        try {
            inetSocketAddress = new InetSocketAddress(InetAddress.getByName(string9), n7);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            String string11 = "Incorrect server format";
            com.corrodinggames.rts.gameFramework.GlobalState.b("IllegalArgumentException.." + string11);
            illegalArgumentException.printStackTrace();
            throw new IOException(string11, illegalArgumentException);
        }
        try {
            ((Socket)object).connect(inetSocketAddress, n8);
        }
        catch (UnknownHostException unknownHostException) {
            String string12 = "Failed to connect to host";
            if (bl2) {
                string12 = string12 + " (udp)";
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("UnknownHostException.." + string12);
            unknownHostException.printStackTrace();
            throw new IOException(string12, unknownHostException);
        }
        catch (IOException iOException) {
            String string13 = "Failed to connect to host";
            if (bl2) {
                string13 = string13 + " (udp)";
            }
            string13 = string13 + " - " + iOException.getMessage();
            com.corrodinggames.rts.gameFramework.GlobalState.e("IOException.." + string13);
            iOException.printStackTrace();
            throw new IOException(string13, iOException);
        }
        return (Socket) object;
    }

    public void showReconnectDialog() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.ui.panels.f f2 = com.corrodinggames.rts.gameFramework.ui.panels.f.a(com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.multiplayerReconnect.message", new Object[0]), false);
        f2.a(com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.resume", new Object[0]), new NetEngine$3(this, f2));
        f2.a(com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.reconnect", new Object[0]), new NetEngine$4(this, f2));
        f2.a(com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.ingame.disconnect", new Object[0]), new NetEngine$5(this, f2, l2));
        l2.bS.a(f2);
        this.bx = true;
    }

    public synchronized boolean reconnectToServer() {
        Socket socket = this.bv;
        if (socket == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reconnectToServer: lastConnectedTo==null");
            return false;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("reconnectToServer attempted");
        if (this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reconnectToServer: disconnecting");
            this.m("reconnecting");
        }
        if (socket.getInetAddress() == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("reconnectToServer: lastConnectedTo.getInetAddress()==null");
            return false;
        }
        String string = socket.getInetAddress().getHostAddress();
        int n2 = socket.getPort();
        String string2 = string + ":" + n2;
        com.corrodinggames.rts.gameFramework.GlobalState.e("reconnectToServer: connecting to: " + string2);
        try {
            boolean bl = false;
            Socket socket2 = NetEngine.m(string2, bl);
            boolean bl2 = this.registerRelayServer(socket2);
            return bl2;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
        catch (NetworkException ag2) {
            ag2.printStackTrace();
            return false;
        }
    }

    /* 02b ad.java L3793: 内部 c.d()/f() 抛 IOException (R8 移除 throws) */
    public synchronized boolean registerRelayServer(Socket socket) throws IOException {
        if (this.B) {
            this.m("starting new");
        }
        if (socket == null) {
            throw new RuntimeException("connectedSocket==null");
        }
        this.q();
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.m = socket.getPort();
        this.B = true;
        this.C = false;
        this.sendIncorrectPassword("connected to Server..");
        PacketDecoder c2 = new PacketDecoder(this, socket);
        c2.p = true;
        c2.d();
        this.aM.add(c2);
        this.kickTeamImpl(c2);
        this.updateMultiplayerNotification();
        this.bv = socket;
        return true;
    }

    public PacketDecoder c(PlayerState n2) {
        for (PacketDecoder c2 : this.aM) {
            if (c2.z != n2) continue;
            return c2;
        }
        return null;
    }

    public PacketDecoder sendIncorrectPassword(PlayerState n2) {
        for (PacketDecoder c2 : this.aM) {
            if (c2.a || c2.z != n2) continue;
            return c2;
        }
        return null;
    }

    public PacketDecoder getClientConnection() {
        if (this.C) {
            return null;
        }
        for (PacketDecoder c2 : this.aM) {
            if (c2.a) continue;
            return c2;
        }
        return null;
    }

    public void sendIncorrectPassword(NetworkPacket au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping sendPacketToAll, not networked");
            return;
        }
        this.cancelNotification(au2);
    }

    private void cancelNotification(NetworkPacket au2) {
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p || c2.a || c2.s) continue;
            c2.a(au2);
        }
    }

    public void kickTeam(NetworkPacket au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping sendPacketToAllIncludingRelay, not networked");
            return;
        }
        for (PacketDecoder c2 : this.aM) {
            if (!c2.p || c2.a) continue;
            c2.a(au2);
        }
    }

    public void kickTeamImpl(NetworkPacket au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping sendPacketToServer, not networked");
            return;
        }
        if (this.C) {
            throw new RuntimeException("We are ByteIndexedMap server");
        }
        this.sendIncorrectPassword(au2);
    }

    public void sendPacketToClients(NetworkPacket au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping sendPacketToClients, not networked");
            return;
        }
        if (!this.C) {
            throw new RuntimeException("We are not ByteIndexedMap server");
        }
        this.sendIncorrectPassword(au2);
    }

    public void registerRelayServer(PacketDecoder c2, NetworkPacket au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping sendPacketOnConnection, not networked");
            return;
        }
        c2.a(au2);
    }

    public void registerAllConnections() {
        if (this.C) {
            this.sendIncorrectPassword("registerConnection: We are ByteIndexedMap server");
        }
        for (PacketDecoder c2 : this.aM) {
            this.sendPacketToClients(c2);
        }
    }

    public void generateClientId() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bQ.networkClientId = null;
        if (this.S == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("generateNewClientId: serverUUID==null");
            this.S = "x";
        }
        this.getOwnClientIdHashed();
        l2.bQ.save();
    }

    public String getOwnClientIdHashed() {
        String string;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = false;
        if (l2.bQ.networkClientId == null) {
            bl = true;
        }
        if (!this.by) {
            this.by = true;
            if (com.corrodinggames.rts.gameFramework.GlobalState.av() && !(string = this.getMacAddressHash()).equals(l2.bQ.networkClientIdMachineKey)) {
                if (l2.bQ.networkClientIdMachineKey != null) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Machine appears to have changed: " + l2.bQ.networkClientIdMachineKey + " vs " + string);
                }
                l2.bQ.networkClientIdMachineKey = string;
                bl = true;
            }
        }
        if (bl) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("new networkClientId needed");
            l2.bQ.networkClientId = UUID.randomUUID().toString();
            l2.bQ.save();
        }
        string = l2.bQ.networkClientId;
        if (this.S == null) {
            throw new RuntimeException("getOwnClientIdHashed: serverUUID==null");
        }
        String string2 = com.corrodinggames.rts.gameFramework.GameUtils.e(string + this.S);
        return string2;
    }

    public void generateServerUUID() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bQ.networkServerId = UUID.randomUUID().toString();
        l2.bQ.save();
    }

    public String getServerUUID() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bQ.networkServerId == null) {
            this.generateServerUUID();
        }
        return l2.bQ.networkServerId;
    }

    public String getServerIdentifier() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.C) {
            return l2.bQ.networkServerId;
        }
        return this.S;
    }

    public void kickTeamImpl(PacketDecoder c2) {
        OutputNetStream as2 = new OutputNetStream();
        try {
            int n2 = 4;
            int n3 = 1;
            if (com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                n3 = 0;
            }
            if (com.corrodinggames.rts.gameFramework.GlobalState.aZ) {
                n3 = 3;
            }
            as2.c("com.corrodinggames.rts");
            as2.a(n2);
            as2.a(this.e);
            as2.a(n3);
            as2.b(this.L);
            as2.c(this.y);
            as2.c(com.corrodinggames.rts.gameFramework.steam.Localization.c());
            String string = "";
            if (com.corrodinggames.rts.gameFramework.GlobalState.aT) {
                string = string + "d";
            }
            as2.c(string);
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.registerRelayServer(c2, as2.b(160));
    }

    public void sendPacketToClients(PacketDecoder c2) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("sendRegisterConnection...");
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.c("com.corrodinggames.rts");
            as2.a(5);
            as2.a(this.e);
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            as2.a(l2.c(true));
            as2.c(this.y);
            String string = null;
            if (this.n != null) {
                string = com.corrodinggames.rts.gameFramework.GameUtils.e(this.n);
            }
            as2.b(string);
            as2.c(l2.extractMapLevel());
            as2.c(this.getOwnClientIdHashed());
            as2.a(l2.z());
            as2.c(this.sendPacketToClients(this.T));
            as2.c(this.sendPacketToClients(this.U));
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.registerRelayServer(c2, as2.b(110));
        this.bz = true;
    }

    public String sendPacketToClients(int n2) {
        return com.corrodinggames.rts.gameFramework.GameUtils.formatSeconds(n2);
    }

    public void sendReadyStateToServer() {
        if (this.C) {
            throw new RuntimeException("We are ByteIndexedMap server");
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.a(this.bG);
            as2.a(l2.bq);
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.kickTeamImpl(as2.b(112));
    }

    public static String i(String string) {
        // 02b ad.java L2966-3009: 聊天消息清洗 (250截断/换行转?/空字符转./滤ISO控制字符)
        if (string == null) {
            return null;
        }
        if (string.length() > 250) {
            string = string.substring(0, 250);
        }
        if (string.contains("\n")) {
            string = string.replace("\n", "?");
        }
        string = string.replace("\u0000", ".");
        boolean bl = false;
        char[] cArray = string.toCharArray();
        int n2 = cArray.length;
        int n3;
        for (n3 = 0; n3 < n2; ++n3) {
            char c2 = cArray[n3];
            if (!Character.isISOControl(c2)) continue;
            bl = true;
            break;
        }
        if (bl) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] cArray2 = string.toCharArray();
            n3 = cArray2.length;
            for (int n4 = 0; n4 < n3; ++n4) {
                char c3 = cArray2[n4];
                if (Character.isISOControl(c3)) continue;
                stringBuilder.append(c3);
            }
            string = stringBuilder.toString();
        }
        return string;
    }

    public void j(String string) {
        if (!this.C) {
            this.sendIncorrectPassword("cannot send sendSystemMessage:" + string + ", we are not ByteIndexedMap server");
            return;
        }
        if (!this.B || this.F) {
            this.sendIncorrectPassword("cannot send sendSystemMessage:" + string + ", not networked");
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("sendSystemMessage:" + string);
        this.registerRelayServer(null, null, null, string);
    }

    public void ad() {
        // 02b ad.java L4137-4153: 鐎广垺鍩涚粩顖氬絺闁?ping/鐢箑顔旀穱鈩冧紖閸?(bG + 閺冨爼妫块幋?
        if (this.C) {
            throw new RuntimeException("We are a server");
        } else {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            OutputNetStream as2 = new OutputNetStream();
            try {
                as2.a(this.bG);
                as2.a(l2.bq);
            } catch (RuntimeException iOException) {
                throw new RuntimeException(iOException);
            }
            this.sendPacketToClients(as2.b(112));
        }
    }
    public void k(String string) {
        this.performNATPunchthrough("-qc " + string);
    }

    public void prepareChatMessage(String string) {
        String string2;
        boolean bl = true;
        String string3 = null;
        if (string != null && ((string2 = string.trim()).startsWith("-") || string2.startsWith(".") || string2.startsWith("_")) && string2.length() >= 0) {
            String string4 = string2.substring(1).trim();
            int n2 = string4.indexOf(" ");
            if (n2 == -1) {
                n2 = string4.length();
            }
            string3 = string4.substring(0, n2).toLowerCase(Locale.ENGLISH);
        }
        if ("share".equals(string3)) {
            bl = false;
        }
        if ("t".equals(string3)) {
            bl = false;
        }
        if (bl) {
            string = "-t " + string;
        }
        this.performNATPunchthrough(string);
    }

    public void registerRelayServer(PacketDecoder c2, PlayerState n2, String string, String string2) {
        this.registerRelayServer(c2, n2, string, string2, null);
    }

    public void registerRelayServer(PacketDecoder c2, PlayerState n2, String string, String string2, PacketDecoder c3) {
        try {
            boolean bl = false;
            boolean bl2 = false;
            String string3 = NetEngine.a(string2);
            if ("t".equalsIgnoreCase(string3)) {
                if (n2 != null) {
                    bl = true;
                    string2 = string2.substring("-t".length());
                    string2 = "[TEAM] " + string2;
                } else {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("toOnlyTeams failed team==null");
                }
            }
            if (n2 != null && "surrender".equalsIgnoreCase(string3)) {
                bl = true;
                string2 = "[TEAM] " + string2;
            }
            if (n2 != null && "i".equalsIgnoreCase(string3)) {
                bl2 = true;
                string2 = string2.substring("-i".length());
                string2 = "[INFO] " + string2;
            }
            if (n2 != null && "qc".equalsIgnoreCase(string3)) {
                bl2 = true;
                string2 = string2.substring("-qc".length());
                string2 = "[COMMAND] " + string2;
            }
            if (!bl2 && n2 != null && n2 != this.bj && n2 != this.bk && !this.d.a(c2, n2, string2, bl)) {
                bl2 = true;
            }
            OutputNetStream as2 = new OutputNetStream();
            as2.c(string2);
            as2.c(3);
            as2.b(string);
            as2.a(c2);
            int n3 = -1;
            if (n2 != null) {
                n3 = n2.k;
            }
            as2.a(n3);
            NetworkPacket au2 = as2.b(141);
            if (bl) {
                for (PacketDecoder c4 : this.aM) {
                    com.corrodinggames.rts.game.HumanPlayer e2;
                    if (!c4.p || c4.a || (e2 = c4.z) == null || !e2.d(n2)) continue;
                    c4.a(au2);
                }
                PlayerState n4 = this.z;
                if (n4 != null && n4.d(n2)) {
                    this.m(c2, n3, string, string2);
                }
            } else if (bl2) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("info message:" + NetEngine.c(string, string2));
            } else {
                if (c3 != null) {
                    this.registerRelayServer(c3, au2);
                } else {
                    this.sendPacketToClients(au2);
                }
                this.m(c2, n3, string, string2);
            }
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static String a(String string) {
        if (string == null) {
            return null;
        }
        String string2 = string.trim();
        if ((string2.startsWith("-") || string2.startsWith(".") || string2.startsWith("_")) && string2.length() >= 0) {
            String string3 = string2.substring(1).trim();
            int n2 = string3.indexOf(" ");
            if (n2 == -1) {
                n2 = string3.length();
            }
            return string3.substring(0, n2).toLowerCase(Locale.ENGLISH);
        }
        return null;
    }

    public static String c(String string, String string2) {
        if (string != null) {
            return string + ": " + string2;
        }
        return string2;
    }

    public void receiveChatMessage(String string) {
        string = com.corrodinggames.rts.gameFramework.steam.Localization.c(string);
        int n2 = -1;
        String string2 = null;
        PacketDecoder c2 = null;
        this.packetBuffer.a(n2, string2, string, c2);
        this.d.a(n2, string2, string, c2);
        boolean bl = false;
        if (this.aW) {
            bl = true;
        }
        if (!this.B) {
            bl = true;
        }
        if (bl) {
            NetEngine.registerRelayServer(string2, string);
        } else {
            String string3 = NetEngine.c(string2, string);
            if (!com.corrodinggames.rts.gameFramework.GlobalState.aU) {
                com.corrodinggames.rts.appFramework.DialogHelper.d(string3);
            }
        }
    }

    private void m(PacketDecoder c2, int n2, String string, String string2) {
        if (!this.B && string2.startsWith("-i ")) {
            return;
        }
        if (!this.B && string2.startsWith("-qc ")) {
            return;
        }
        string2 = com.corrodinggames.rts.gameFramework.steam.Localization.c(string2);
        if (string != null) {
            boolean bl = true;
            if (string2 != null) {
                if (string2.equals("-surrender")) {
                    // empty if block
                }
                if (this.z == null || n2 < 0 || this.z.k == n2) {
                    // empty if block
                }
            }
            if (bl) {
                this.sendIncorrectPassword("New Message", string + ": " + string2);
            }
        }
        PacketDecoder c3 = null;
        if (this.C) {
            c3 = c2;
        }
        this.packetBuffer.a(n2, string, string2, c3);
        this.d.a(n2, string, string2, c2);
        boolean bl = false;
        if (this.aW) {
            bl = true;
        }
        if (!this.B) {
            bl = true;
        }
        if (bl) {
            NetEngine.registerRelayServer(string, string2);
        } else {
            String string3 = NetEngine.c(string, string2);
            if (!com.corrodinggames.rts.gameFramework.GlobalState.aU) {
                com.corrodinggames.rts.appFramework.DialogHelper.d(string3);
            }
        }
    }

    public void registerRelayServer(PacketDecoder c2, byte[] byArray, boolean bl, boolean bl2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        try {
            OutputNetStream as2 = new OutputNetStream();
            as2.c(0);
            as2.a(l2.bx);
            as2.a(l2.by);
            as2.a(this.c());
            as2.a(1.0f);
            as2.a(bl);
            as2.a(bl2);
            as2.e("gameSave");
            as2.b(byArray);
            as2.a("gameSave");
            NetworkPacket au2 = as2.b(35);
            this.registerRelayServer(c2, au2);
        }
        catch (IOException  iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void registerRelayServer(boolean bl, boolean bl2, boolean bl3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        try {
            OutputNetStream as2 = new OutputNetStream();
            as2.c(0);
            as2.a(l2.bx);
            as2.a(l2.by);
            as2.a(this.c());
            as2.a(1.0f);
            as2.a(bl);
            as2.a(bl2);
            as2.e("gameSave");
            l2.ca.a(as2);
            as2.a("gameSave");
            if (bl) {
                // empty if block
            }
            NetworkPacket au2 = as2.b(35);
            this.sendIncorrectPassword(au2);
            if (bl3) {
                if (!this.C) {
                    throw new RuntimeException("sendResyncSave: reloadCreatedSave: We are not ByteIndexedMap server");
                }
                au2.connection = this.aL;
                this.registerRelayServer(au2);
            }
        }
        catch (IOException  iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public boolean finalizeGameStart() {
        this.sendSync();
        this.L();
        return this.registerRelayServer((PacketDecoder) null, false);
    }

    public boolean registerRelayServer(PacketDecoder c2, boolean bl) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Sending start game....");
        if (!this.C) {
            throw new RuntimeException("We are not ByteIndexedMap server");
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        OutputNetStream as2 = new OutputNetStream();
        try {
            as2.c(0);
            as2.a(this.ay.a);
            if (this.ay.a == com.corrodinggames.rts.gameFramework.network.GameModeEnum.c) {
                try {
                    l2.ca.a(this.ay.b, as2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    l2.c("Map error starting game", "Map error: " + iOException.getMessage());
                    return false;
                }
                as2.c("SAVE:" + this.ay.b);
            } else if (this.ay.a == com.corrodinggames.rts.gameFramework.network.GameModeEnum.b) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Starting with custom map: " + this.prepareChatMessage());
                try {
                    com.corrodinggames.rts.game.map.MapEngine.a(this.az, as2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    l2.c("Map error starting game", "Map error: " + iOException.getMessage());
                    return false;
                }
                as2.c("STEAM:" + this.prepareChatMessage());
            } else {
                as2.c(this.prepareChatMessage());
            }
            as2.a(bl);
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        NetworkPacket au2 = as2.b(120);
        if (c2 == null) {
            this.sendPacketToClients(au2);
        } else {
            this.registerRelayServer(c2, au2);
        }
        if (!this.aW) {
            this.onNetworkGameStarted();
        }
        return true;
    }

    public void onStartGameFailed() {
        this.highLatencyDetected = true;
        com.corrodinggames.rts.gameFramework.GlobalState.e("onStartGameFailed");
        if (this.C) {
            this.aW = false;
            this.j("Map load failed.");
        } else {
            this.m("Map load failed");
        }
    }

    private void onNetworkGameStarted() {
        this.aY = false;
        this.aW = true;
        this.highLatencyDetected = false;
        this.packetLossDetected = false;
        com.corrodinggames.rts.gameFramework.GlobalState.e("Starting new network game (" + this.getServerIdentifier() + ")");
        if (this.q && this.C) {
            com.corrodinggames.rts.gameFramework.network.WebAPIClient.c();
        }
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            com.corrodinggames.rts.appFramework.DialogHelper.m();
        }
        this.d.b();
    }

    public void scheduleReturnToBattleroom() {
        this.sendIncorrectPassword(5.0f);
    }

    public void sendIncorrectPassword(float f2) {
        if (!this.C) {
            throw new RuntimeException("We are not ByteIndexedMap server");
        }
        if (this.aZ) {
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("Setting up return to battleroom timer...");
        this.ba = f2;
        this.aZ = true;
        this.j("Game ended by host. Returning to battleroom in " + (int)f2 + " seconds...");
    }

    public void cancelNotification(PacketDecoder c2) {
        if (!this.C) {
            throw new RuntimeException("We are not ByteIndexedMap server");
        }
        try {
            OutputNetStream as2 = new OutputNetStream();
            as2.c(0);
            NetworkPacket au2 = as2.b(122);
            if (c2 == null) {
                this.sendPacketToClients(au2);
            } else {
                this.registerRelayServer(c2, au2);
            }
        }
        catch (RuntimeException  iOException) {
            throw new RuntimeException(iOException);
        }
        this.onNetworkGameStarted();
    }

    private void setReturnToBattleroomFlag() {
        this.aY = true;
    }

    private void executeReturnToBattleroom() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("----- returnToBattleroom -----");
        this.aY = false;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.cb.e();
        PlayerState n2 = this.z;
        l2.isKeyJustPressed();
        this.resetAllState();
        this.z = n2;
        l2.bx = 0;
        l2.by = 0;
        this.clearConnectionFlags();
        com.corrodinggames.rts.game.PlayerState.resetAllPlayers();
        if (this.C) {
            this.randomizeConnectionLock();
        }
        this.dismissInterfaceOverlay();
        if (this.q && this.C) {
            com.corrodinggames.rts.gameFramework.network.WebAPIClient.c();
        }
        if (!com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            // empty if block
        }
    }

    public String getFirstDisconnectIp() {
        ArrayList<String> arrayList = this.getLocalIPAddresses();
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return (String)arrayList.get(0);
    }

    public String getDisconnectIpList() {
        ArrayList<String> arrayList = this.getLocalIPAddresses();
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        String string = "";
        boolean bl = true;
        for (String string2 : arrayList) {
            if (bl) {
                bl = false;
            } else {
                string = string + ", ";
            }
            string = string + string2;
        }
        return string;
    }

    public int e(int n2) {  // 02b ad.java L524-526 缁旑垰褰涚悰?
        return n2 == 0 ? 4000 : (n2 == 1 ? 0 : (n2 == 2 ? 1000 : (n2 == 3 ? 2000 : (n2 == 4 ? 5000 : (n2 == 5 ? 10000 : (n2 == 6 ? 50000 : (n2 == 7 ? 100000 : (n2 == 8 ? 200000 : 999))))))));
    }

    public int C() {  // 02b ad.java L1110-1132 閻溾晛顔嶇拋鈩冩殶
        ArrayList arrayList = new ArrayList();
        int n2 = 0;
        Iterator iterator = this.aM.iterator();
        while (iterator.hasNext()) {
            PacketDecoder packetDecoder = (PacketDecoder)iterator.next();
            if (packetDecoder.p && packetDecoder.h() && !packetDecoder.s) {
                com.corrodinggames.rts.game.HumanPlayer humanPlayer = packetDecoder.z;
                if (humanPlayer != null) {
                    if (arrayList.contains(humanPlayer)) continue;
                    arrayList.add(humanPlayer);
                }
                ++n2;
            }
        }
        return n2;
    }

    public int E() {  // 02b ad.java L1148-1156
        int n2 = this.C();
        if (!com.corrodinggames.rts.gameFramework.GlobalState.ax()) {
            ++n2;
        }
        return n2;
    }

    public String g(int n2) {  // 02b ad.java L4110-4131 閺嶏繝鐛欓崪?
        String string = "";
        string = string + "c:" + n2;
        string = string + "m:" + (n2 * 87 + 24);
        string = string + "0:" + this.e(0) * 11 * n2;
        string = string + "1:" + (this.e(1) * 12 + n2);
        string = string + "2:" + this.e(2) * 13 * n2;
        string = string + "3:" + (this.e(3) * 14 + n2);
        string = string + "4:" + this.e(4) * 15 * n2;
        string = string + "5:" + (this.e(5) * 16 + n2);
        string = string + "6:" + this.e(6) * 17 * n2;
        string = string + "7:" + (this.e(7) * 18 + n2);
        string = string + "8:" + this.e(8) * 19 * n2;
        string = string + "t1:" + com.corrodinggames.rts.game.PlayerState.j.o * 11.0D * (double)n2;
        int n3 = 5 * n2;
        if (this.k() != this.e(this.ay.c)) {
            n3 = 7 * n2;
        }
        string = string + "d:" + n3;
        return string;
    }

    public Boolean aU;  // 02b ad.java L170

    public String au() {  // 02b ad.java L5460-5488 (mods 閸掓銆?
        if (!this.o) {
            return null;
        }
        GlobalState l2 = GlobalState.B();
        ArrayList arrayList = l2.bZ.j();
        String string = "";
        int n2 = 0;
        String string3;
        for (Iterator iterator = arrayList.iterator(); iterator.hasNext(); string = string + string3) {
            com.corrodinggames.rts.gameFramework.mods.ModInfo modInfo = (com.corrodinggames.rts.gameFramework.mods.ModInfo)iterator.next();
            if (n2 != 0) {
                string = string + "; ";
            }
            if (n2 > 1 && n2 < arrayList.size() - 1) {
                string = string + "" + (arrayList.size() - n2) + " more...";
                break;
            }
            ++n2;
            string3 = modInfo.b();
            string3.replace(";", ".");
        }
        return string;
    }

    public synchronized void a(boolean bl, String string, Boolean boolean2) {  // 02b ad.java L549-554
        this.aV = Boolean.valueOf(bl);
        this.aT = string;
        this.aU = boolean2;
        // 02b: com.corrodinggames.rts.appFramework.n.o() (03 鐎电懓绨插鍛叀, 缁犫偓閸栨牜娓烽悾?
    }

    public String ah() {  // 02b ad.java L4623-4626
        ArrayList arrayList = this.getLocalIPAddresses();
        return arrayList != null && arrayList.size() != 0 ? (String)arrayList.get(0) : null;
    }

    public ArrayList<String> getLocalIPAddresses() {
        if (bA != null) {
            return new ArrayList(bA);
        }
        long l2 = com.corrodinggames.rts.gameFramework.ExtraManager.a();
        ArrayList arrayList = null;
        ArrayList arrayList2 = this.sendIncorrectPassword(true);
        arrayList = arrayList2 != null && arrayList2.size() > 0 ? arrayList2 : this.sendIncorrectPassword(false);
        double d2 = com.corrodinggames.rts.gameFramework.ExtraManager.a(l2);
        if (d2 > 2.0) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("getLocalIpAddressList was slow, taking:" + com.corrodinggames.rts.gameFramework.ExtraManager.a(d2));
        }
        if (d2 > 10.0 && arrayList != null && arrayList.size() > 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("getLocalIpAddressList: creating cache");
            bA = new ArrayList(arrayList);
        }
        return arrayList;
    }

    public String getMacAddressHash() {
        String string = null;
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                byte[] byArray = networkInterface.getHardwareAddress();
                if (byArray == null) continue;
                String string2 = new String(byArray);
                if ((string2 = string2.trim()).length() <= 0) continue;
                string = string2;
                break;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (string != null) {
            return com.corrodinggames.rts.gameFramework.GameUtils.c(string);
        }
        return "[blank]";
    }

    public ArrayList<String> sendIncorrectPassword(boolean bl) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                Enumeration<InetAddress> enumeration2 = networkInterface.getInetAddresses();
                while (enumeration2.hasMoreElements()) {
                    String string;
                    InetAddress inetAddress = enumeration2.nextElement();
                    if (inetAddress.isLoopbackAddress() || (string = inetAddress.getHostAddress().toString()).contains("%")) continue;
                    if (!bl) {
                        arrayList.add(string);
                        continue;
                    }
                    if (!string.contains(".")) continue;
                    arrayList.add(string);
                }
            }
        }
        catch (SocketException socketException) {
            Log.d("RustedWarfare", socketException.toString());
        }
        return arrayList;
    }

    InetAddress al() {
        try {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            WifiManager wifiManager = (WifiManager)l2.am.c("wifi");
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            int n2 = dhcpInfo.ipAddress & dhcpInfo.netmask | ~dhcpInfo.netmask;
            byte[] byArray = new byte[4];
            for (int i2 = 0; i2 < 4; ++i2) {
                byArray[i2] = (byte)(n2 >> i2 * 8 & 0xFF);
            }
            return InetAddress.getByAddress(byArray);
        }
        catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
            return null;
        }
    }

    public void sendIncorrectPassword(String string, String string2) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.F || l2.cb.j()) {
            return;
        }
        boolean bl = com.corrodinggames.rts.appFramework.DialogHelper.l();
        com.corrodinggames.rts.appFramework.AppFramework f2 = l2.ao;
        if (f2 != null && !f2.e()) {
            bl = true;
        }
        if (bl) {
            if (this.bB) {
                this.cancelNotification(0);
            }
            return;
        }
        NotificationManager notificationManager = (NotificationManager)l2.am.c("notification");
        Intent intent = new Intent(l2.am, com.corrodinggames.rts.appFramework.testing.class);
        PendingIntent pendingIntent = PendingIntent.getActivity((Context)l2.am, (int)0, (Intent)intent, (int)0);
        if (Build.VERSION.SDK_INT >= 11) {
            android.app.Notification.Builder builder = new android.app.Notification.Builder(l2.am);
            builder.setContentTitle((CharSequence)"Rusted Warfare Multiplayer");
            builder.setContentText((CharSequence)(string + ": " + string2));
            builder.setSmallIcon(R$drawable.icon);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(false);
            builder.setAutoCancel(true);
            this.registerRelayServer(notificationManager);
            this.registerRelayServer(builder, "multiplayerChatId");
            android.app.Notification notification = builder.getNotification();
            notificationManager.notify(0, notification);
            this.bB = true;
        }
    }

    public void updateMultiplayerNotification() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.B && l2 != null && l2.M()) {
            this.showGameInProgressNotification();
        } else {
            this.cancelNotification(1);
            this.cancelNotification(0);
        }
    }

    private void registerRelayServer(android.app.Notification.Builder builder, String string) {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Method method = builder.getClass().getDeclaredMethod("setChannelId", String.class);
                method.invoke(builder, string);
            }
            catch (Exception exception) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("setChannelId failed", (Throwable)exception);
            }
        }
    }

    private void registerRelayServer(NotificationManager notificationManager) {
        this.registerRelayServer(notificationManager, "multiplayerChatId", "Multiplayer Chat");
        this.registerRelayServer(notificationManager, "multiplayerStatusId", "Multiplayer Status");
    }

    private void registerRelayServer(NotificationManager notificationManager, String string, String string2) {
        if (Build.VERSION.SDK_INT >= 26) {
            int n2 = 3;
            try {
                Class<?> clazz = Class.forName("android.app.NotificationChannel");
                Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, CharSequence.class, Integer.TYPE);
                Object obj = constructor.newInstance(string, string2, n2);
                Method method = notificationManager.getClass().getDeclaredMethod("createNotificationChannel", clazz);
                method.invoke(notificationManager, obj);
            }
            catch (Exception exception) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("Creating notification channel failed", (Throwable)exception);
            }
        }
    }

    private void showGameInProgressNotification() {
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Intent intent = new Intent(l2.am, com.corrodinggames.rts.appFramework.testing.class);
        PendingIntent pendingIntent = PendingIntent.getActivity((Context)l2.am, (int)0, (Intent)intent, (int)0);
        NotificationManager notificationManager = (NotificationManager)l2.am.c("notification");
        if (Build.VERSION.SDK_INT >= 11) {
            if (Build.VERSION.SDK_INT >= 26) {
                // empty if block
            }
            android.app.Notification.Builder builder = new android.app.Notification.Builder(l2.am);
            builder.setContentTitle((CharSequence)"Rusted Warfare Multiplayer");
            builder.setContentText((CharSequence)"A multiplayer game is in progress");
            builder.setSmallIcon(R$drawable.icon);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);
            this.registerRelayServer(notificationManager);
            this.registerRelayServer(builder, "multiplayerStatusId");
            if (Build.VERSION.SDK_INT >= 16) {
                builder.build();
            }
            android.app.Notification notification = builder.getNotification();
            notificationManager.notify(1, notification);
        }
    }

    private void cancelNotification(int n2) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        NotificationManager notificationManager = (NotificationManager)l2.am.c("notification");
        notificationManager.cancel(n2);
    }

    public int getHumanPlayerCount() {
        int n2 = 0;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
            PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (n3 == null || n3.w) continue;
            ++n2;
        }
        return n2;
    }

    public int getTotalPlayerCount() {
        int n2 = 0;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
            PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (n3 == null) continue;
            ++n2;
        }
        return n2;
    }

    public void kickTeam(PlayerState n2) {
        if (this.C) {
            this.kickTeamImpl(n2);
        } else if (this.useSteamRelay) {
            this.k("-kick " + (n2.k + 1));
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("kickTeamAndAttachedPlayer: but not server or proxy controller");
        }
    }

    public void kickTeamImpl(PlayerState n2) {
        if (n2 instanceof com.corrodinggames.rts.game.ai.AIStrategy) {
            n2.updateResourceDisplay();
        } else {
            if (this.z == n2) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("kickTeamAndAttachedPlayer", "Cannot kick self");
                return;
            }
            PacketDecoder c2 = this.c(n2);
            if (c2 == null) {
                NetEngine.registerRelayServer("Kick player: cannot find connection for team", false);
            } else {
                int n3 = com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.banTimeInSecondsAfterKick;
                if (n3 > 0) {
                    this.registerRelayServer(c2, "Temporarily banned due to recent kick", n3);
                }
                this.registerRelayServer(c2, "Kicked by host");
                c2.a("Kicked by host");
            }
            n2.updateResourceDisplay();
        }
        this.P();
        com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
    }

    public void addAIToGame() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.C) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("addAIToGame", "We are not ByteIndexedMap server");
            return;
        }
        int n2 = com.corrodinggames.rts.game.PlayerState.getUnitCount();
        if (n2 == -1) {
            l2.a("No free slots for AI", 1);
        }
        com.corrodinggames.rts.game.ai.AIStrategy a2 = new com.corrodinggames.rts.game.ai.AIStrategy(n2);
        a2.v = "AI";
        a2.r = n2 % 2;
        a2.x = this.ay.f;
        this.updateAllAINames();
        l2.bX.d.a(a2);
        l2.bX.kickTeam((PacketDecoder) null);
        com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
    }

    public boolean updateAllAINames() {
        if (!this.C && this.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("updateNamesOfAI", "We are not ByteIndexedMap server");
            return false;
        }
        boolean bl = false;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
            PlayerState n2 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (n2 == null || !this.m(n2)) continue;
            bl = true;
        }
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void registerRelayServer(PlayerState n2, int n3) {
        Object object = this.bC;
        synchronized (object) {
            this.c(n2, n3);
        }
    }

    private void c(PlayerState n2, int n3) {
        if (n2.k != n3) {
            int n4 = n2.k;
            int n5 = n2.r;
            boolean bl = false;
            if (n3 == -3) {
                bl = true;
                n3 = com.corrodinggames.rts.game.PlayerState.getBuildingCount();
                if (n3 == -1) {
                    NetEngine.kickTeam("No free spectator slots");
                    return;
                }
            }
            PlayerState n6 = com.corrodinggames.rts.game.PlayerState.u(n3);
            n2.f(n3);
            n2.r = n5;
            if (bl) {
                n2.r = -3;
            }
            if (n6 != null) {
                int n7 = n6.r;
                n6.f(n4);
                n6.r = n7 == -3 ? -3 : n5;
            }
            this.connectToServer();
            this.P();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void registerRelayServer(ConnectionState am2) {
        Object object = this.bC;
        synchronized (object) {
            this.m(am2);
        }
    }

    public synchronized void m(ConnectionState am2) {  // 02b ad.a(am) 閸忣剙绱戦弬瑙勭《 (MultiplayerUI teamsSet_apply 鐠嬪啰鏁?
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!l2.bX.C) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Not server");
            return;
        }
        if (am2 == ConnectionState.a) {
            int n2;
            int n3;
            ArrayList<PlayerState> arrayList = new ArrayList<PlayerState>();
            for (n3 = 0; n3 < com.corrodinggames.rts.game.PlayerState.c; ++n3) {
                PlayerState n4 = com.corrodinggames.rts.game.PlayerState.u(n3);
                if (n4 == null) continue;
                arrayList.add(n4);
            }
            Collections.shuffle(arrayList);
            n3 = arrayList.size() / 2;
            if (arrayList.size() % 2 != 0) {
                n3 += com.corrodinggames.rts.gameFramework.GameUtils.a(0, 1);
            }
            if (n3 >= arrayList.size()) {
                n3 = arrayList.size();
            }
            int n5 = 0;
            int n6 = 0;
            for (n2 = n5; n2 < n3; ++n2) {
                ((PlayerState) arrayList.get(n2)).f(n6);
                n6 += 0;
                ((PlayerState) arrayList.get((int)n2)).r = 0;
            }
            n6 = 1;
            for (n2 = n5 += n3; n2 < arrayList.size(); ++n2) {
                ((PlayerState) arrayList.get(n2)).f(n6);
                n6 += 0;
                ((PlayerState) arrayList.get((int)n2)).r = 1;
            }
        } else if (am2 == ConnectionState.b) {
            PlayerState n7;
            int n8;
            int n9;
            int n10;
            ArrayList<PlayerState> arrayList = new ArrayList<PlayerState>();
            for (n10 = 0; n10 < com.corrodinggames.rts.game.PlayerState.c; ++n10) {
                PlayerState n11 = com.corrodinggames.rts.game.PlayerState.u(n10);
                if (n11 == null) continue;
                arrayList.add(n11);
            }
            Collections.shuffle(arrayList);
            n10 = arrayList.size() / 3;
            if (n10 >= arrayList.size()) {
                n10 = arrayList.size();
            }
            int n12 = 0;
            int n13 = 0;
            for (n9 = n12; n9 < n10; ++n9) {
                PlayerState n14 = (PlayerState) arrayList.get(n9);
                n14.f(n13);
                n14.r = 0;
                n13 += 3;
                arrayList.set(n9, null);
            }
            n9 = (n12 += n10) + arrayList.size() / 3;
            if (n9 >= arrayList.size()) {
                n9 = arrayList.size();
            }
            if (n12 >= arrayList.size()) {
                n12 = arrayList.size();
            }
            n13 = 1;
            for (n8 = n12; n8 < n9; ++n8) {
                n7 = (PlayerState) arrayList.get(n8);
                n7.f(n13);
                n7.r = 1;
                n13 += 3;
                arrayList.set(n8, null);
            }
            if ((n12 += n10) >= arrayList.size()) {
                n12 = arrayList.size();
            }
            n13 = 0;
            for (n8 = n12; n8 < arrayList.size(); ++n8) {
                n7 = (PlayerState) arrayList.get(n8);
                if (n13 < com.corrodinggames.rts.game.PlayerState.c) continue;
                n7.f(n13);
                n7.r = 0;
                n13 += 3;
                arrayList.set(n8, null);
            }
            for (n8 = 0; n8 < arrayList.size(); ++n8) {
                n7 = (PlayerState) arrayList.get(n8);
                if (n7 == null) continue;
                for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
                    if (com.corrodinggames.rts.game.PlayerState.u(i2) != null) continue;
                    n7.f(i2);
                    n7.r = 0;
                    arrayList.set(n8, null);
                }
            }
        } else if (am2 == ConnectionState.c) {
            int n15;
            ArrayList<PlayerState> arrayList = new ArrayList<PlayerState>();
            for (n15 = 0; n15 < com.corrodinggames.rts.game.PlayerState.c; ++n15) {
                PlayerState n16 = com.corrodinggames.rts.game.PlayerState.u(n15);
                if (n16 == null) continue;
                arrayList.add(n16);
            }
            Collections.shuffle(arrayList);
            n15 = 0;
            for (int i3 = 0; i3 < arrayList.size(); ++i3) {
                ((PlayerState) arrayList.get(i3)).f(n15);
                ((PlayerState) arrayList.get((int)i3)).r = n15++;
            }
        } else if (am2 == ConnectionState.d) {
            int n17;
            ArrayList<PlayerState> arrayList = new ArrayList<PlayerState>();
            for (n17 = 0; n17 < com.corrodinggames.rts.game.PlayerState.c; ++n17) {
                PlayerState n18 = com.corrodinggames.rts.game.PlayerState.u(n17);
                if (n18 == null) continue;
                arrayList.add(n18);
            }
            Collections.shuffle(arrayList);
            n17 = 0;
            for (int i4 = 0; i4 < arrayList.size(); ++i4) {
                int n19 = com.corrodinggames.rts.game.PlayerState.getBuildingCount();
                if (n19 != -1) {
                    ((PlayerState) arrayList.get(i4)).f(n19);
                }
                ((PlayerState) arrayList.get((int)i4)).r = -3;
                ++n17;
            }
        } else {
            throw new RuntimeException("overrideTeamLayout: unhandled layout: " + (Object)((Object)am2));
        }
        this.connectToServer();
    }

    public void registerRelayServer(PlayerState n2, int n3, Integer n4) {
        String string = "";
        if (n4 != null) {
            string = " " + n4;
        }
        if (!this.useSteamRelay && this.z == n2) {
            this.k("-self_move " + (n3 + 1) + string);
            return;
        }
        this.k("-move " + (n2.k + 1) + " " + (n3 + 1) + string);
    }

    public void m(PlayerState n2, int n3) {
        if (n3 != -1) {
            ++n3;
        }
        if (!this.useSteamRelay && this.z == n2) {
            this.k("-self_team " + n3);
            return;
        }
        this.k("-team " + (n2.k + 1) + " " + n3);
    }

    public void sendPacketToClients(PlayerState n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = false;
        String string = n2.v;
        if (string == null) {
            string = "Player - " + (n2.k + 1) + "";
        }
        String string2 = string + " was defeated";
        if (!this.pingUpdated) {
            string2 = string2 + " (Team: " + n2.h() + ")";
        } else {
            int n3 = com.corrodinggames.rts.game.PlayerState.g();
            string2 = string2 + " (" + n3 + " players remaining)";
            if (n3 == 1) {
                bl = true;
            }
        }
        if (!l2.N() && l2.bx < 60) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Not showing defeated message: " + string2);
            string2 = null;
        }
        if (n2.E) {
            string2 = null;
        }
        if (string2 != null) {
            this.j(string2);
        }
        if (bl) {
            com.corrodinggames.rts.game.PlayerState.checkDefeatedPlayers();
        }
    }

    public void cancelNotification(PlayerState n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        String string = n2.v;
        if (string == null) {
            string = "Player - " + (n2.k + 1) + "";
        }
        boolean bl = false;
        String string2 = l2.bx < 10 ? string + " had no starting units" : string + " has been wiped out";
        if (!this.pingUpdated) {
            string2 = string2 + " (Team: " + n2.h() + ")";
        } else {
            int n3 = com.corrodinggames.rts.game.PlayerState.g();
            string2 = string2 + " (" + n3 + " players remaining)";
            if (n3 == 1) {
                bl = true;
            }
        }
        if (!l2.N() && l2.bx < 60) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Not showing defeated message: " + string2);
            string2 = null;
        }
        if (n2.E) {
            string2 = null;
        }
        if (n2.b()) {
            string2 = null;
        }
        if (string2 != null) {
            this.j(string2);
        }
        if (bl) {
            com.corrodinggames.rts.game.PlayerState.checkDefeatedPlayers();
        }
    }

    public synchronized void cancelLobbyKickTimer() {
        if (this.bD != null) {
            this.bD.cancel();
            this.bD = null;
        }
    }

    public String getFogModeText() {
        // 02b ad.java L466-468: g() 闂嗙偓膩瀵繑鏋冮張?
        return this.ay.d == 0 ? "No fog" : (this.ay.d == 1 ? "Basic fog" : (this.ay.d == 2 ? "Line of Sight" : "Unknown"));
    }
    public synchronized void startLobbyKickTimer() {
        if (this.q && this.C && this.bD == null) {
            this.bD = new Timer();
            NetEngine$6 ad$6 = new NetEngine$6(this);
            this.bD.schedule((TimerTask)ad$6, 60000L, 60000L);
        }
    }

    public String getServerStatusText() {
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        String string = "";
        if (l2.bX.C && !l2.bX.F) {
            String string2 = l2.bX.getDisconnectIpList();
            if (this.D) {
                if (this.E != null) {
                    object = this.E;
                    string = string + (String)object;
                }
            } else if (string2 != null) {
                object = "Local IP address: " + string2 + " port: " + l2.bX.m;
                if (l2.bX.aV != null) {
                    if (!l2.bX.aV.booleanValue()) {
                        object = (String)object + "\nUnable to get ByteIndexedMap public IP address, check your internet connection";
                    } else if (l2.bX.aT != null && l2.bX.cloudServicesEnabled != null) {
                        object = (String)object + "\nYour public address is " + (l2.bX.cloudServicesEnabled != false ? "<Open>" : "<CLOSED>") + " to the internet";
                    }
                } else {
                    object = (String)object + "\nRetrieving your public IP...";
                }
                string = string + (String)object;
            } else {
                string = string + "You do not have ByteIndexedMap network connection";
            }
        }
        if (l2.P()) {
            string = this.p ? string + "SandBox Mode!\nPlace any unit, Control all teams, Special powers" : string + "Local skirmish";
        }
        boolean bl = true;
        if (com.corrodinggames.rts.gameFramework.GlobalState.at() && l2.bX.C) {
            bl = false;
        }
        if (string.length() != 0) {
            string = string + "\n";
            if (com.corrodinggames.rts.gameFramework.GlobalState.av()) {
                string = string + "\n";
            }
        }
        if (l2.bX.av || l2.bX.C) {
            if (bl) {
                if (l2.bX.ay.a != null) {
                    string = string + "Game Mode: " + l2.bX.ay.a.a();
                }
                if (l2.bX.ay.b != null) {
                    string = string + "\nMap: " + com.corrodinggames.rts.appFramework.ContextMenuActivity.e(l2.bX.ay.b);
                }
            }
            string = string + "\nStarting Credits: " + l2.bX.j();
            string = string + "\nFog: " + l2.bX.getFogModeText();
            if (l2.bX.ay.g != 1) {
                string = string + "\nStarting Units: " + l2.bX.sendPacketToClients();
            }
            if (l2.bX.ay.h != 1.0f) {
                string = string + "\n" + com.corrodinggames.rts.gameFramework.GameUtils.a(l2.bX.ay.h, 1) + "X income";
            }
            if (l2.bX.ay.i) {
                string = string + "\nNo nukes";
            }
            if (l2.bX.ay.l) {
                string = string + "\nShared control: On";
            }
            if (this.C) {
                if (l2.bX.n != null) {
                    string = string + "\nPassword Protection: On";
                }
                if (!l2.bX.q && !l2.bX.F) {
                    string = string + "\nServer Visibility: Hidden";
                }
                if (l2.bX.o && !l2.bX.F) {
                    object = l2.bZ.j();
                    string = string + "\n-- Required Mods: --\n";
                    int n2 = 0;
                    Iterator iterator = ((ArrayList)object).iterator();
                    while (iterator.hasNext()) {
                        com.corrodinggames.rts.gameFramework.mods.ModInfo b2 = (com.corrodinggames.rts.gameFramework.mods.ModInfo)iterator.next();
                        if (n2 > 0 && n2 < ((ArrayList)object).size() - 1) {
                            string = string + "" + (((ArrayList)object).size() - n2) + " more mods...";
                            break;
                        }
                        ++n2;
                        String string3 = b2.b();
                        string3.replace("\"", "'");
                        string3.replace(";", ".");
                        string = string + " mod: \"" + string3 + "\"\n";
                    }
                }
            }
        }
        return string;
    }

    public String getNetworkMapPath() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bX.ay.b == null) {
            return null;
        }
        if (l2.bX.ay.a == null) {
            return null;
        }
        if (l2.bX.ay.a == com.corrodinggames.rts.gameFramework.network.GameModeEnum.a) {
            return "maps/skirmish/" + l2.bX.ay.b;
        }
        if (l2.bX.ay.a == com.corrodinggames.rts.gameFramework.network.GameModeEnum.b) {
            return "/SD/rusted_warfare_maps/" + l2.bX.ay.b;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("getNetworkMapPath: unhandled type:" + (Object)((Object)l2.bX.ay.a));
        return null;
    }

    public boolean isServerOrRelay() {
        return this.C || this.useSteamRelay;
    }

    public void registerRelayServer(String string, PacketDecoder c2) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("sendCommandError: " + string);
        if (c2 == null) {
            this.m(null, -1, null, string);
        } else {
            this.registerRelayServer(null, null, null, string, c2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean m(PacketDecoder c2, PlayerState n2, String string, String string2) {
        int n3;
        String string3;
        String string4 = null;
        String string5 = "";
        String[] stringArray = new String[]{};
        String string6 = string2.trim();
        boolean bl = false;
        if (string6.startsWith("-qc ")) {
            string6 = string6.substring("-qc ".length());
            string6 = string6.trim();
            bl = true;
        }
        if ((string6.startsWith("-") || string6.startsWith(".") || string6.startsWith("_")) && string6.length() >= 0) {
            string3 = string6.substring(1).trim();
            n3 = string3.indexOf(" ");
            if (n3 == -1) {
                n3 = string3.length();
            }
            string4 = string3.substring(0, n3).toLowerCase(Locale.ENGLISH);
            if (n3 != -1 && string3.length() >= n3 + 1) {
                string5 = string3.substring(n3 + 1).trim();
                stringArray = string5.split(" ");
            }
        }
        if (string4 == null) {
            return false;
        }
        if (bl && !"self_move".equals(string4) && !"self_team".equals(string4)) {
            return false;
        }
        if ("pause".equals(string4) || "unpause".equals(string4)) {
            boolean bl2;
            if (n2 == null) {
                this.registerRelayServer("[Could not find player]", c2);
                return true;
            }
            if (!(this.C && n2 == this.z || this.d.b(c2))) {
                this.registerRelayServer("[Only the host can change pause state]", c2);
                return true;
            }
            if (!this.aW) {
                this.registerRelayServer("[Game not yet started]", c2);
                return true;
            }
            boolean bl3 = bl2 = !this.connectionLost;
            if ("unpause".equals(string4)) {
                bl2 = false;
            }
            this.kickTeam(bl2);
            return true;
        }
        if ("endgame".equals(string4)) {
            if (n2 == null) {
                this.registerRelayServer("[Could not find player]", c2);
                return true;
            }
            if (!this.C || n2 != this.z) {
                this.registerRelayServer("[Only the host can end game]", c2);
                return true;
            }
            if (!this.aW) {
                this.registerRelayServer("[Game not yet started]", c2);
                return true;
            }
            this.scheduleReturnToBattleroom();
            return true;
        }
        if ("teamlock".equals(string4)) {
            if (n2 == null) {
                this.registerRelayServer("[Could not find player]", c2);
                return true;
            }
            if (!(this.C && n2 == this.z || this.d.b(c2))) {
                this.registerRelayServer("[Only the host can change teamlock]", c2);
                return true;
            }
            if ("true".equalsIgnoreCase(string5) || "on".equalsIgnoreCase(string5)) {
                this.ay.m = true;
                this.registerRelayServer("[teams are locked]", c2);
                return true;
            }
            if ("false".equalsIgnoreCase(string5) || "off".equalsIgnoreCase(string5)) {
                this.ay.m = false;
                this.registerRelayServer("[teams are unlocked]", c2);
                return true;
            }
            this.registerRelayServer("[Expected true or false]", c2);
            return true;
        }
        if ("roomlock".equals(string4)) {
            if (n2 == null) {
                this.registerRelayServer("[Could not find player]", c2);
                return true;
            }
            if (!this.C || n2 != this.z) {
                this.registerRelayServer("[Only the host can change roomlock]", c2);
                return true;
            }
            if ("true".equalsIgnoreCase(string5) || "on".equalsIgnoreCase(string5)) {
                this.ay.tournamentMode = true;
                this.registerRelayServer("[room is locked]", c2);
                return true;
            }
            if ("false".equalsIgnoreCase(string5) || "off".equalsIgnoreCase(string5)) {
                this.ay.tournamentMode = false;
                this.registerRelayServer("[room is unlocked]", c2);
                return true;
            }
            this.registerRelayServer("[Expected true or false]", c2);
            return true;
        }
        if ("share".equals(string4)) {
            if (n2 == null) {
                this.registerRelayServer("[Could not find player]", c2);
                return true;
            }
            if (!this.ay.l) {
                this.registerRelayServer("[Shared control is not enabled in this game]", c2);
                return true;
            }
            if ("true".equalsIgnoreCase(string5) || "on".equalsIgnoreCase(string5)) {
                if (!n2.I) {
                    n2.I = true;
                    this.j("[shared control now on for " + string + "]");
                } else {
                    this.j("[shared control already on for " + string + "]");
                }
                return true;
            }
            if ("false".equalsIgnoreCase(string5) || "off".equalsIgnoreCase(string5)) {
                if (n2.I) {
                    n2.I = false;
                    this.j("[shared control now off for " + string + "]");
                } else {
                    this.j("[shared control already off for " + string + "]");
                }
                return true;
            }
            this.registerRelayServer("[Expected true or false]", c2);
            return true;
        }
        if ("self_move".equals(string4)) {
            int n4;
            boolean bl4;
            int n5;
            if (n2 == null) {
                this.registerRelayServer("[Cannot Move - Player not found]", c2);
                return true;
            }
            if (this.aW) {
                this.registerRelayServer("[Cannot Move '" + n2.v + "' - Game has been started]", c2);
                return true;
            }
            if (this.receiveChatMessage()) {
                this.registerRelayServer("[Cannot Move '" + n2.v + "' - Game is starting]", c2);
                return true;
            }
            if (this.ay.m) {
                this.registerRelayServer("[Cannot Move '" + n2.v + "' - Teams locked]", c2);
                return true;
            }
            if (stringArray.length > 0) {
                try {
                    n5 = Integer.valueOf(stringArray[0]);
                }
                catch (NumberFormatException numberFormatException) {
                    this.registerRelayServer("[Cannot Move '" + n2.v + "' - team '" + stringArray[0] + "' is not ByteIndexedMap number]", c2);
                    return true;
                }
            } else {
                this.registerRelayServer("[Cannot Move '" + n2.v + "' - No target]", c2);
                return true;
            }
            Integer n6 = null;
            if (stringArray.length > 1) {
                try {
                    n6 = Integer.valueOf(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    this.registerRelayServer("[Cannot Move '" + n2.v + "' - ally group '" + stringArray[1] + "' is not ByteIndexedMap number]", c2);
                    return true;
                }
                if (n6 != -1 && (n6 < 1 || n6 > 99)) {
                    this.registerRelayServer("[Cannot Move Team - Ally group - Out of range]", c2);
                    return true;
                }
            }
            boolean bl5 = false;
            if (n5 - 1 == -3) {
                if (!this.ay.o) {
                    this.registerRelayServer("[Spectators are disabled on this server]", c2);
                    return true;
                }
                Object object = this.bC;
                synchronized (object) {
                    n5 = com.corrodinggames.rts.game.PlayerState.getBuildingCount();
                    if (n5 != -1) {
                        this.registerRelayServer(n2, -3);
                    }
                }
                bl5 = true;
            }
            boolean bl6 = bl4 = (n4 = n2.r) == -3;
            if (!bl5) {
                if (n5 < 1 || n5 > com.corrodinggames.rts.game.PlayerState.c) {
                    this.registerRelayServer("[Cannot Move '" + n2.v + "' - target slotId must between 1-" + com.corrodinggames.rts.game.PlayerState.c + "]", c2);
                    return true;
                }
                Object object = this.bC;
                synchronized (object) {
                    PlayerState n7;
                    if (this.z != n2 && (n7 = com.corrodinggames.rts.game.PlayerState.u(n5 - 1)) != null && !n7.w && !n7.b()) {
                        this.registerRelayServer("[Cannot move '" + n2.v + "' to slot: " + n5 + " - Player: " + n7.v + " is in that slot.]", c2);
                        return true;
                    }
                    this.registerRelayServer(n2, n5 - 1);
                }
            }
            n2.r = n4;
            if (n6 != null) {
                n2.r = n6 == -1 ? n2.k % 2 : n6;
            }
            if (this.ay.n) {
                n2.r = n2.k % 2;
            }
            if (bl5) {
                n2.r = -3;
            }
            if (bl5) {
                if (!bl4) {
                    this.j("Player '" + n2.v + "' is now ByteIndexedMap spectator");
                }
            } else {
                this.j("Player '" + n2.v + "' moved themselves to: " + n5);
            }
            this.P();
            com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
            return true;
        }
        if ("self_team".equals(string4)) {
            int n8;
            if (n2 == null) {
                this.registerRelayServer("[Cannot Set Team - Player not found]", c2);
                return true;
            }
            if (this.aW) {
                this.registerRelayServer("[" + n2.v + ": Cannot Set Team - Game has been started]", c2);
                return true;
            }
            if (this.receiveChatMessage()) {
                this.registerRelayServer("[" + n2.v + ": Cannot Set Team - Game is starting]", c2);
                return true;
            }
            if (this.ay.m) {
                this.registerRelayServer("[" + n2.v + ": Cannot Set Team - Teams locked]", c2);
                return true;
            }
            if (this.ay.n) {
                return true;
            }
            try {
                n8 = Integer.valueOf(string5);
            }
            catch (NumberFormatException numberFormatException) {
                this.performNATPunchthrough("'" + string5 + "' is not ByteIndexedMap number");
                return true;
            }
            if (n8 == -1) {
                n3 = n2.k % 2;
            } else {
                if (n8 < 1 || n8 > 99) {
                    this.registerRelayServer("[Cannot Set Team - Out of range]", c2);
                    return true;
                }
                n3 = n8 - 1;
            }
            if (n2.r != n3) {
                n2.r = n3;
                this.registerRelayServer("Player '" + n2.v + "' team changed to: " + n8, c2);
            }
            this.P();
            com.corrodinggames.rts.appFramework.DialogHelper.o();  // 02b n.o()
            return true;
        }
        if ("surrender".equals(string4)) {
            if (!this.aW) {
                this.registerRelayServer("[Cannot Surrender - Game has not started]", c2);
                return true;
            }
            if (n2 == null) {
                this.registerRelayServer("[Could not find player]", c2);
                return true;
            }
            string3 = "";
            if (!n2.k()) {
                n2.markSyncFrame();
                n3 = n2.isActive() ? 1 : 0;
                com.corrodinggames.rts.gameFramework.GlobalState.e(string + ": Is voting to surrender (can surrender:" + (n3 != 0) + ", afk:" + n2.ab + ", defeated:" + n2.G + ", disconnected:" + n2.isIdle() + ")");
                string3 = n3 != 0 ? "" : "(Cannot vote) ";
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.e(string + ": Is already voting to surrender but updating timestamp");
                n2.markSyncFrame();
                string3 = "(Already voted) ";
            }
            String string7 = com.corrodinggames.rts.game.PlayerState.b(n2.r) + "/" + com.corrodinggames.rts.game.PlayerState.c(n2.r);
            String string8 = "-t " + string3 + "[Votes to surrender " + string7 + "]";
            this.registerRelayServer(c2, n2, string, string8);
            return true;
        }
        return false;
    }

    public static void a(PasswordManager ae2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bX != null) {
            l2.bX.d.a(ae2);
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU) {
            return;
        }
        NetEngine$7 ad$7 = new NetEngine$7(ae2);
        com.corrodinggames.rts.appFramework.AndroidUIHelper.a(ad$7);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList getAllTeamsSynchronized() {
        Object object = this.bC;
        synchronized (object) {
            return com.corrodinggames.rts.game.PlayerState.c();
        }
    }

    public void kickTeam(boolean bl) {
        this.connectionLost = bl;
        if (this.connectionLost) {
            this.j("Game Paused");
        } else {
            this.j("Game unpaused");
        }
    }

    public void m(PacketDecoder c2, String string) {
        c2.a(false, false, string);
    }

    public void c(PacketDecoder c2, String string) {
        for (PacketDecoder c3 : this.aM) {
            if (c3.j != c2) continue;
            this.m(c3, string);
        }
    }

    public PacketDecoder registerRelayServer(PacketDecoder c2, int n2, String string, String string2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        SteamSocket h2 = new SteamSocket(c2, n2);
        PacketDecoder c3 = new PacketDecoder(this, h2);
        c3.k = n2;
        c3.j = c2;
        c3.m = string;
        c3.n = string2;
        try {
            c3.d();
            l2.bX.aM.add(c3);
            l2.bX.sendSync();
            return c3;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            c3.a("crash");
            return null;
        }
    }

    public PacketDecoder registerRelayServer(PacketDecoder c2, int n2) {
        for (PacketDecoder c3 : this.aM) {
            if (c3.k != n2 || c3.j != c2) continue;
            return c3;
        }
        return null;
    }

    public static String p(String string) {
        string = string.trim();
        string = string.replace("\n", ".");
        string = string.replace("\r", ".");
        string = string.replace("\t", ".");
        string = string.replace("\u0000", ".");
        string = string.replace(" ", "_");
        while (string.startsWith(".") || string.startsWith("-") || string.startsWith(" ")) {
            string = string.substring(1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c2 : string.toCharArray()) {
            if (Character.isISOControl(c2)) continue;
            stringBuilder.append(c2);
        }
        string = stringBuilder.toString();
        return string;
    }

    public void registerRelayServer(ArrayList arrayList, boolean bl) {
        if (this.bF != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("startJoinServerInternalThread: Already joining");
            return;
        }
        if (arrayList.size() == 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("startJoinServerInternalThread: no servers");
            return;
        }
        String string = (String)arrayList.get(0);
        boolean bl2 = false;
        NetEngine$8 ad$8 = new NetEngine$8(this, bl);
        this.bF = this.registerRelayServer(string, bl2, ad$8);
    }
    static PasswordManager networkStats;  // v19.113o auto_align R3a: 闂堟瑦鈧礁娼＄挧瀣偓鍏兼￥婢圭増妲戠悰銉﹀絻

    static {
        networkStats = new PasswordManager();
    }


    public void a(long l2) {  // 02b j/ad.java L1064-1066: this.w = var1
        this.w = l2;
    }


    public static void g(String string) {  // 02b ad.java L1176-1178 (缁犫偓閸? desync 閺冦儱绻? 鐎瑰本鏆ｉ柅鏄忕帆瀵板懏鐓?
        com.corrodinggames.rts.gameFramework.GlobalState.b("desync:" + string);
    }

    public strictfp boolean I() {  // 02b ad.java L1638-1655 (shouldGameBePaused)
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bU.e()) {
            if (!this.bu) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("shouldGameBePaused: isGoingToBlockThisFrame()==true: " + l2.bU.f());
            }
            this.bu = true;
            return true;
        }
        if (this.bu) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("shouldGameBePaused: isGoingToBlockThisFrame()==false");
        }
        this.bu = false;
        return false;
    }

    public void a(float f2, boolean bl) {  // 02b j/ad.java L1657-1671
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bx >= this.X) {
            if (l2.bx > this.X) {
                throw new RuntimeException("game frame:" + l2.bx + " is greater then nest step:" + this.X);
            }
            this.Y = true;
        }
        if (bl && this.I()) {
            this.Y = true;
        }
    }


    public boolean aq() {  // 02b j/ad.java L4961: 閺囧瓨鏌?AI 閸氬秶袨
        if (!this.C && this.B) {
            GlobalState.b("updateNamesOfAI", "We are not a server");
            return false;
        }
        boolean bl = false;
        for (int i = 0; i < PlayerState.c; ++i) {
            PlayerState playerState = PlayerState.k(i);
            if (playerState != null && this.b(playerState)) {
                bl = true;
            }
        }
        return bl;
    }

    public boolean b(PlayerState n2) {  // 02b j/ad.java: AI 閸氬秶袨閺囧瓨鏌?
        boolean bl = false;
        if (n2.w) {
            String string = "AI - " + this.b(n2.C());
            if (!string.equals(n2.v)) {
                n2.v = string;
                bl = true;
            }
        }
        return bl;
    }

    public String b(int n2) {  // 02b j/ad.java L474: 婵梹澧?c(int)
        return this.c(n2);
    }

    public boolean ad;  // 02b j/ad.java L126


    public void b(float var1) {  // 02b j/ad.b(float) 简化 TODO (v19.133f)
    }

}