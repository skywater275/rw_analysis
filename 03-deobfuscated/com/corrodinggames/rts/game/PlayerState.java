/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.MatchConfig;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.PingTimer;
import com.corrodinggames.rts.gameFramework.RenderThread;
import com.corrodinggames.rts.game.units.ResourceRate;
import com.corrodinggames.rts.game.units.DecorUnit;
import com.corrodinggames.rts.game.units.UnitAttachment;
import com.corrodinggames.rts.game.units.PathState;
import com.corrodinggames.rts.game.units.DecorType3;
import com.corrodinggames.rts.game.units.UnitCategory;
import com.corrodinggames.rts.game.units.DecorType1;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.game.units.DecorType4;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import java.util.Iterator;

import android.graphics.Color;
import android.graphics.Paint;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.NeutralPlayer;
import com.corrodinggames.rts.game.GameMode;
import com.corrodinggames.rts.game.UnitManager;
import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.UnitTypeCount;
import com.corrodinggames.rts.game.TeamUnitTracker;
import com.corrodinggames.rts.game.LobbyPlayer;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.effects.EffectRenderer;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitTypeComparator;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.CollisionShape;
import com.corrodinggames.rts.game.units.commands.CommandCenter;
import com.corrodinggames.rts.game.units.AmphibiousUnit;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GamePhase;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.FontRenderer;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public strictfp abstract class PlayerState
extends BaseGameObject
implements Comparable {

    public double p = 0.0;


    static int[] ag = new int[10];


    static String[] ah = new String[10];

    static CustomArrayList a = new CustomArrayList();
    static PlayerState[] b = new PlayerState[0];
    public static int c = 10;
    public static int d = 0;
    public static int e = 100;
    public static int f = c + d;
    public static PlayerState g = new HumanPlayer(-1, false, "<blank>");
    public static PlayerState h = new NeutralPlayer(-2);
    public static PlayerState i = new NeutralPlayer(-1);
    // v19.112d 补全 (javap 铁证: n.d(game.n) 字节码还原)

    // v19.113 补全 (02b n.java:1168 + javap 字节码 + 运行时崩溃栈三重铁证: n.c(game.n))
    // 敌对判定: r(队伍id) 不同 → true; 任一方为中立玩家 i → false
    public final strictfp boolean d(PlayerState n2) {  // 02b n.d(n): 团队比较 (javap -c 铁证)
        if (n2 == i && this == i) {
            return true;
        }
        if (n2 == i || this == i) {
            return false;
        }
        return this.r == n2.r;
    }

    public final boolean isEnemy(PlayerState n2) {
        // v19.113r 防御 patch: 原版 bug — AIStrategy 挑到 bX=null 僵尸单位时 n2=null → NPE (运行时验证: 单测后 AI 对局必现)
        return n2 != null && n2 != i && this != i && this.r != n2.r;
    }

    // v19.112d 补全 (02b 铁证: n.f(int) = c(var1, true))
    public void setTeamIdAndRegister(int n2) {
        this.c(n2, true);
    }

    // v19.115 勘误: v19.112d 曾误补 boolean creditBuffer (02b n.p = double; 138 行已正确声明 double creditBuffer)

    // v19.112d 补全 (02b game/n.java L1509 铁证: static n k(int))
    public static strictfp int h(int n2) {  // 02b n.h(int): 队伍颜色索引
    return n2 >= c ? i(-3) : i(n2 % 2);
    }

public static int i(int n2) {
        return n2 >= 0 && n2 < 10 ? ag[n2] : (n2 == -3 ? android.graphics.Color.a(185, 90, 90, 90) : -7829368);
    }

    public static void a(com.corrodinggames.rts.game.units.UnitInstance am2) {
    }

    public static void c(com.corrodinggames.rts.game.units.UnitInstance am2) {
    }

    public static void b(com.corrodinggames.rts.game.units.UnitInstance am2) {
    }

    public static PlayerState k(int n2) {
        if (n2 == -1) {
            return i;
        }
        if (n2 == -2) {
            return h;
        }
        if (n2 >= f) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("team index too high: " + n2);
            return null;
        }
        if (n2 < 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("team index too low: " + n2);
            return null;
        }
        return as[n2];
    }

    // v19.112 补全 (javap 铁证: game.n w()=T.b / x()=T.a)
    public int w() {
        return this.T.b;
    }

    public int x() {
        return this.T.a;
    }


    // 02b n.java L464-473: f() 静态 — 活跃团队 id 列表
    public static ArrayList f() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < c; ++i) {
            PlayerState n2 = as[i];
            if (n2 != null && !n2.b() && !arrayList.contains(Integer.valueOf(n2.r))) {
                arrayList.add(Integer.valueOf(n2.r));
            }
        }
        return arrayList;
    }
    private static PlayerState[] as = new PlayerState[f];
    public double o;  // 02b n.o (double)
    public static PlayerState j = new LobbyPlayer(-99);
    public int k = -1;
    public final String l = "Note to modifiers: Changing credits will not allow you to cheat in multiplayer games, but it will only break sync";
    public boolean m;
    public boolean n;
    public double credits = 4000.0;  // v19.113g 铁证: setTeamCredits 777777→存档 779064 经济调整 + 02b 注释 "Changing credits..."
    public double creditBuffer = 0.0;
    public int q = 0;
    public int r;
    public com.corrodinggames.rts.game.units.UnitType s = com.corrodinggames.rts.game.units.AmphibiousUnit.a(this);  // 02 铁证: n.s:units.y = units.t.a(this)
    public com.corrodinggames.rts.game.units.UnitType t = com.corrodinggames.rts.game.units.AmphibiousUnit.a(this);
    public boolean u = false;
    public String v;
    public boolean w;
    public int x;
    public boolean y;
    public Integer z;
    public Integer A;
    public Integer B;
    public Integer C;
    public int D = -1;
    private boolean at;
    private int au = -9999;
    public boolean E;
    private int av = -9999;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public final Object K = new Object();
    public int L;
    public int M;
    public byte[][] N;
    public String O;
    public String P;
    public int Q;
    public int R;
    public boolean S = true;
    public TeamUnitTracker T = new TeamUnitTracker();
    public boolean U;
    public byte V;
    public int W = -1;
    public long X = -1L;
    public long Y = -1L;
    public int Z = -1;
    public boolean aa;
    public boolean ab;
    public int ac = 0;
    int ad;
    public Paint ae = new UniquePaint();
    public Paint af = new UniquePaint();
    static int[] colorPalette = new int[10];
    static String[] colorNames = new String[10];
    int ai = -2;
    static int aj = -99;
    com.corrodinggames.rts.game.units.custom.UnitConfig ak = com.corrodinggames.rts.game.units.custom.TeamTag.d;  // 02: custom.g.d
    EffectManager al = new EffectManager();  // 02b game.n.al = custom.e.f (EffectManager), 非 MovementController
    public EffectRenderer am = new EffectRenderer();  // 02b game.n.am = custom.e.c (EffectRenderer), 非 NetworkPlayer
    public float an;
    public static float ao = 40.0f;
    public static float ap = 10.0f;
    long aq = -9999L;
    double ar;

    public int compareTo(PlayerState n2) {
        int n3 = this.ac - n2.ac;
        if (n3 != 0) {
            return n3;
        }
        int n4 = this.k - n2.k;
        if (n4 != 0) {
            return n4;
        }
        if (this.v != null && n2.v != null) {
            return this.v.compareTo(n2.v);
        }
        return 0;
    }

    public void writePlayerBaseState(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {  // v19.113g: 02 n.b(as) 写方向 (旧误名 getActivePlayers)
        as2.c(this.k);
        as2.a((int)this.credits);
        as2.a(this.r);
        as2.b(this.v);
        as2.a(this.U);
        if (as2.g() > 26) {
            as2.a(this.getCreditsInteger());
            as2.d("lastPingTimeReceivedAt");
            as2.a(this.X);
        }
        if (as2.g() >= 55) {
            as2.a(this.w);
            as2.a(this.x);
        }
        if (as2.g() >= 91) {
            as2.a(this.ac);
            as2.c(0);
        }
        if (as2.g() >= 97) {
            as2.a(this.I);
            as2.a(this.J);
        }
        if (as2.g() >= 125) {
            as2.a(this.E);
            as2.a(this.at);
            as2.a(this.au);
        }
        if (as2.g() >= 149) {
            as2.b(this.P);
            as2.a(this.Q);
        }
        if (as2.g() >= 156) {
            as2.a(this.z);
            as2.a(this.A);
            as2.a(this.B);
            as2.a(this.C);
            as2.a(this.D);
        }
    }

    public void b(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {
        // 02b game/n.java L116-162: b(as) 玩家状态写流 (全量)
        as2.c(this.k);
        as2.a((int) this.credits);
        as2.a(this.r);
        as2.b(this.v);
        as2.a(this.U);
        if (as2.g() > 26) {
            as2.a(this.getCreditsInteger());
            as2.d("lastPingTimeReceivedAt");
            as2.a(this.X);
        }
        if (as2.g() >= 55) {
            as2.a(this.w);
            as2.a(this.x);
        }
        if (as2.g() >= 91) {
            as2.a(this.ac);
            as2.c(0);
        }
        if (as2.g() >= 97) {
            as2.a(this.I);
            as2.a(this.J);
        }
        if (as2.g() >= 125) {
            as2.a(this.E);
            as2.a(this.at);
            as2.a(this.au);
        }
        if (as2.g() >= 149) {
            as2.b(this.P);
            as2.a(this.Q);
        }
        if (as2.g() >= 156) {
            as2.a(this.z);
            as2.a(this.A);
            as2.a(this.B);
            as2.a(this.C);
            as2.a(this.D);
        }
    }
    public void c(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {
        as2.c(0);
        as2.a(this.getCreditsInteger());
        as2.a(this.I);
        as2.a(this.J);
    }

    public void a(InputNetStream k2) {
        k2.d();
        this.W = k2.readInt();
        this.X = System.currentTimeMillis();
        this.I = k2.readBoolean();
        this.J = k2.readBoolean();
    }

        // v19.113g 补全 (02b n.java:322 n.d(as) 直译): 写战争迷雾 N 二维数组
    public void writeFogOfWar(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {
        as2.d("-- Saving fog --");
        as2.a(this.N != null);
        if (this.N != null) {
            as2.a(this.L);
            as2.a(this.M);
            for (int n3 = 0; n3 < this.L; ++n3) {
                for (int n4 = 0; n4 < this.M; ++n4) {
                    as2.c(this.N[n3][n4]);
                }
            }
        }
        as2.d("--End fog--");
    }

public void b(InputNetStream k2) {
        this.a(k2, false);
    }

    public void a(InputNetStream k2, boolean bl) {
        int n2;
        boolean bl2;
        if (!bl) {
            this.setTeamIdAndRegister(k2.d());
            this.credits = k2.readInt();
            this.creditBuffer = 0.0;
            this.q = 0;
            this.r = k2.readInt();
            this.v = k2.j();
            this.U = k2.readBoolean();
        } else {
            k2.d();
            k2.readInt();
            k2.readInt();
            k2.j();
            k2.readBoolean();
        }
        if (k2.b() >= 14) {
            this.W = k2.readInt();
            k2.i();
            this.X = System.currentTimeMillis();
        }
        if (k2.b() >= 34 && k2.c() >= 55) {
            bl2 = k2.readBoolean();
            n2 = k2.readInt();
            if (!bl) {
                this.w = bl2;
                this.x = n2;
            }
        } else {
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            if (l2.bX.B) {
                com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("AI was skipping in networked game, steam version:" + k2.c());
            }
        }
        if (k2.b() >= 50 && k2.c() >= 91) {
            this.ac = k2.readInt();
            k2.d();
        }
        if (k2.b() >= 52 && k2.c() >= 97) {
            this.I = k2.readBoolean();
            this.J = k2.readBoolean();
        }
        if (k2.b() >= 70 && k2.c() >= 125) {
            bl2 = k2.readBoolean();
            n2 = k2.readBoolean() ? 1 : 0;
            int n3 = k2.readInt();
            if (!bl) {
                this.E = bl2;
                this.at = n2 != 0;
                this.au = n3;
            }
        }
        if (k2.b() >= 90 && k2.c() >= 149) {
            String string = k2.j();
            n2 = k2.readInt();
            if (!bl) {
                this.P = string;
                this.Q = n2;
            }
        }
        if (k2.b() >= 93 && k2.c() >= 156) {
            Integer n4 = k2.k();
            Integer n5 = k2.k();
            Integer n6 = k2.k();
            Integer n7 = k2.k();
            int n8 = k2.readInt();
            if (!bl) {
                if (this.z != n4) {
                    this.c("readIn aiDifficultyOverride was:" + this.z + " now:  " + n4);
                }
                this.z = n4;
                this.A = n5;
                this.B = n6;
                this.C = n7;
                this.D = n8;
            }
        }
    }


    @Override
    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {  // v19.112d: BaseGameObject 抽象对齐 {
        as2.d("Writing team: " + this.v);
        this.writePlayerBaseState(as2);  // v19.113g 02b 铁证: n.b(as) 写 k/o(资金)/r/v/U/A()/X(最后ping)/w/x; 存档 "Writing team: Player" 互证
        if (as2.g() >= 44) {  // v19.113g: 02 n.a(as) 用 var1.g() (写流版本), 非 readFloat
            as2.c(4);
            as2.a(this.G);
            as2.a(this.F);
            boolean bl = true;
            as2.a(bl);
            if (bl) {
                this.writeFogOfWar(as2);  // v19.113g 02b 铁证: n.d(as) 写 "-- Saving fog --" N 二维迷雾数组; 旧误译 isSameTeamAs(PlayerState) 参数类型都不符
            }
            this.al.a(as2);
            com.corrodinggames.rts.game.units.custom.TeamTag.serializeTags(this.ak, as2);  // v19.113g: 02 g.a(ak, as) 是写方向 (序列化标签)
            as2.a(this.y);
        }
    }

    public void c(InputNetStream k2) {
        this.b(k2);
        if (k2.b() >= 26) {
            boolean bl;
            byte by = k2.d();
            this.G = k2.readBoolean();
            if (by >= 1) {
                this.F = k2.readBoolean();
            }
            if (bl = k2.readBoolean()) {
                this.d(k2);
            }
            if (by >= 2) {
                this.al.a(k2);
            }
            if (by >= 3) {
                this.a(com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(k2));
            }
            if (by >= 4) {
                this.y = k2.readBoolean();
            }
        }
    }

    public void a() {
        if (this.N != null) {
            for (int i2 = 0; i2 < this.L; ++i2) {
                for (int i3 = 0; i3 < this.M; ++i3) {
                    this.N[i2][i3] = 0;
                }
            }
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bs == this) {
            l2.bW.O = true;
            if (l2.bL != null) {
                l2.bL.clearAllFog();
                l2.bL.g();
            }
        }
    }

    public boolean b() {
        return this.r == -3;
    }

    public boolean p() {  // 02b n.java L655
        return this.I || this.J;
    }

    public boolean b(PlayerState n2) {  // 02b n.java L651: b(n) (getActivePlayers 为幻觉名)
        return this.p() && n2 != null && this.d(n2);
    }

    public static ArrayList a(boolean bl) {
        ArrayList<PlayerState> arrayList = new ArrayList<PlayerState>();
        for (int i2 = 0; i2 < f; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || !bl && !n2.b()) continue;
            arrayList.add(n2);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static ArrayList getAliveTeamIds() {
        // 02b game/n.java L464-476: f() 存活玩家团队ID列表
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 0; i2 < f; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || n2.b() || arrayList.contains(Integer.valueOf(n2.r))) continue;
            arrayList.add(Integer.valueOf(n2.r));
        }
        Collections.sort(arrayList);
        return arrayList;
    }
    public static ArrayList c() {
        return PlayerState.b(false);
    }

    public static ArrayList b(boolean bl) {
        ArrayList<PlayerState> arrayList = new ArrayList<PlayerState>();
        for (int i2 = 0; i2 < f; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || !bl && n2.b()) continue;
            arrayList.add(n2);
        }
        return arrayList;
    }

    public static int a(int n2, boolean bl) {
        int n3 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n4 = b[i2];
            if (n4 == null || n4.r != n2 || n4.b() || bl && n4.w) continue;
            ++n3;
        }
        return n3;
    }

    public static void b(int n2, boolean bl) throws IOException {
        if (n2 < 10) {
            return;
        }
        if (n2 == c) {
            return;
        }
        if (n2 > e) {
            throw new IOException("setMaxTeamId: " + n2 + " is over limit of:" + e);
        }
        if (!bl && n2 <= c) {
            return;
        }
        int n3 = n2 + d;
        PlayerState[] nArray = new PlayerState[n3];
        for (int i2 = 0; i2 < as.length; ++i2) {
            PlayerState n4 = b[i2];
            if (i2 >= nArray.length) continue;
            nArray[i2] = n4;
        }
        as = nArray;
        c = n2;
        f = n3;
    }

    public static String a(int n2) {
        if (n2 == 0) {
            return "A";
        }
        if (n2 == 1) {
            return "B";
        }
        if (n2 == 2) {
            return "C";
        }
        if (n2 == 3) {
            return "D";
        }
        if (n2 == 4) {
            return "E";
        }
        if (n2 == 5) {
            return "F";
        }
        if (n2 == 6) {
            return "G";
        }
        if (n2 == 7) {
            return "H";
        }
        if (n2 == 8) {
            return "I";
        }
        if (n2 == 9) {
            return "J";
        }
        if (n2 == 10) {
            return "K";
        }
        if (n2 == -3) {
            return "S";
        }
        return "" + n2;
    }



    public void markSyncFrame() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.au = l2.by;
    }

    public boolean isActive() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if ((this.F || this.G) && !l2.bX.ay.l) {
            return false;
        }
        if (this.w) {
            return false;
        }
        if (this.b()) {
            return false;
        }
        return !this.ab || this.k();
    }

    public static int b(int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n4 = b[i2];
            if (n4 == null || n4.r != n2 || !n4.k() || !n4.isActive()) continue;
            ++n3;
        }
        return n3;
    }

    public static int c(int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n4 = b[i2];
            if (n4 == null || n4.r != n2 || !n4.isActive()) continue;
            ++n3;
        }
        return n3;
    }

    public static void resetAllPlayers() {
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null) continue;
            n2.resetForNewGame();
        }
        PlayerState.onResourceChanged();
    }

    public static void resetSurrenderTimestamps() {
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null) continue;
            n2.au = -9999;
        }
    }


    public boolean isActivePlayer() {
        return this.I || this.J;
    }

    public boolean isLocalPlayer() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bs == this;
    }

    public void c(boolean bl) {
        this.Q = bl ? 1 : 0;
    }

    public boolean isHost() {
        return this.Q == 1;
    }

    public final int a(boolean bl, boolean bl2) {
        TeamUnitTracker s2 = this.T;
        int n2 = s2.c;
        if (bl) {
            n2 += s2.f;
        }
        if (bl2) {
            n2 += s2.e;
        }
        return n2;
    }

    public final int getTotalUnitCount() {
        return this.T.c + this.T.f + this.T.e;
    }

    public final int a(com.corrodinggames.rts.game.units.custom.TeamTag g2, boolean bl, boolean bl2) {
        int n2;
        TeamUnitTracker s2 = this.T;
        if (s2.d == 0) {
            return 0;
        }
        UnitManager p2 = null;
        com.corrodinggames.rts.game.BuildQueue t2 = s2.p;
        UnitManager[] pArray = t2.b;
        int n3 = t2.c;
        for (n2 = 0; n2 < n3; ++n2) {
            UnitManager p3 = pArray[n2];
            if (p3.teamTag != g2) continue;
            p2 = p3;
            break;
        }
        if (p2 == null) {
            p2 = s2.incrementUnitCount(g2);
            if (p2.priorityLevel > 50) {
                t2.a(p2);
            }
            p2.priorityLevel = (short)(p2.priorityLevel + 1);
        }
        n2 = p2.totalUnitCount;
        if (bl) {
            n2 += p2.activeUnitCount;
        }
        if (bl2) {
            n2 += p2.maxAllowedUnits;
        }
        return n2;
    }

    public boolean validateTeamTracker() {
        boolean bl = false;
        TeamUnitTracker s2 = this.checkDefeat(false);
        if (this.T.b != s2.b) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("unitCountExcludingBuildingsIncludingQueued: " + this.T.b + "!=" + s2.b + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.a != s2.a) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("unitsMax: " + this.T.a + "!=" + s2.a + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.g != s2.g) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("incomeRate: " + this.T.g + "!=" + s2.g + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.f != s2.f) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("incompleteUnitCountOfAllTypes: " + this.T.f + "!=" + s2.f + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.e != s2.e) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("queuedCountOfAllTypes: " + this.T.e + "!=" + s2.e + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.c != s2.c) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("unitCountOfAllTypesOnlyCompleted: " + this.T.c + "!=" + s2.c + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (!this.T.h.e(s2.h)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("customIncomeRate: " + this.T.h + "!=" + s2.h + " (team:" + this.k + " fails: " + this.ad + ")");
            com.corrodinggames.rts.gameFramework.GlobalState.b("currentCaches:" + this.T.h.a(false, true, 30, true, true));
            com.corrodinggames.rts.gameFramework.GlobalState.b("targetUnitCache:" + s2.h.a(false, true, 30, true, true));
            ++this.ad;
            bl = true;
        }
        if (!this.T.l.e(s2.l)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("streamingRateNegative (team:" + this.k + " fails: " + this.ad + ")");
            com.corrodinggames.rts.gameFramework.GlobalState.b("currentCaches:" + this.T.l.a(false, true, 30, true, true));
            com.corrodinggames.rts.gameFramework.GlobalState.b("targetUnitCache:" + s2.l.a(false, true, 30, true, true));
            ++this.ad;
            bl = true;
        }
        if (!this.T.k.e(s2.k)) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("streamingRatePositive (team:" + this.k + " fails: " + this.ad + ")");
            com.corrodinggames.rts.gameFramework.GlobalState.b("currentCaches:" + this.T.k.a(false, true, 30, true, true));
            com.corrodinggames.rts.gameFramework.GlobalState.b("targetUnitCache:" + s2.k.a(false, true, 30, true, true));
            ++this.ad;
            bl = true;
        }
        return bl;
    }

    private TeamUnitTracker checkDefeat(boolean bl) {  // 02b n.e(boolean): 统计本玩家单位到 TeamUnitTracker
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        TeamUnitTracker s2 = new TeamUnitTracker();
        s2.a = l2.bB;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n2 = 0;
        for (int n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size(); n2 < n3; ++n2) {
            UnitInstance am2 = amArray[n2];
            if (am2.player == this) {
                s2.incrementUnitCount(am2);
                if (bl) {
                    am2.bY = true;
                }
            }
        }
        if (s2.a > l2.bC) {
            s2.a = l2.bC;
        }
        return s2;
    }

    public int getIncomeRate() {
        int n2 = this.T.g;
        n2 = (int)((float)n2 * this.D());
        return n2;
    }

    public int getScaledTotalUnits() {
        return (int)((float)this.getIncomeRate() * this.getExpenseRate() + 0.5f);
    }

    public int a(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2) {
        int n2 = 0;
        return n2 -= (int)this.T.l.a(a2);
    }

    public int b(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2) {
        int n2 = a2 == com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.D ? this.T.g : (int)this.T.h.a(a2);
        n2 += (int)this.T.k.a(a2);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = false;
        if (a2 == com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.D) {
            bl = true;
        }
        if (bl) {
            n2 = (int)((float)n2 * this.D());
        }
        return n2;
    }

    public int getTotalUnitCount2() {
        return this.T.b;
    }

    public int getMaxUnitCount() {
        return this.T.a;
    }

    public String getPingDisplayString() {
        int n2 = this.getCreditsInteger();
        if (n2 == -99) {
            return "";
        }
        if (this.w) {
            return "";
        }
        if (n2 == -2) {
            return "(disconnected)";
        }
        if (n2 == -1) {
            return "(disconnected)";
        }
        return "(" + n2 + ")";
    }

    public String getHostDisplayString() {
        int n2 = this.getCreditsInteger();
        if (n2 == -99) {
            return "HOST";
        }
        if (this.w) {
            return "-";
        }
        if (n2 == -1) {
            return "N/A";
        }
        if (n2 == -2) {
            return "-";
        }
        if (this.r()) {
            return n2 + " (HOST)";
        }
        return "" + n2;
    }

    public int getCreditsInteger() {
        if (this.X == -1L) {
            return -2;
        }
        if (this.X < System.currentTimeMillis() - 5000L) {
            return -1;
        }
        return this.W;
    }

    public boolean isIdle() {
        if (this.X == -99L) {
            return false;
        }
        return this.X != -1L && this.X < System.currentTimeMillis() - 15000L;
    }

    public void a(float f2) {
        this.an += f2;
        if (this.an > 90.0f) {
            this.an = 0.0f;
            this.am.a();
        }
        ++this.q;
        if (this.q > 1000 && this.creditBuffer != 0.0) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Warning: anti-lag credits is still: " + this.creditBuffer + " (force clearing)");
            this.creditBuffer = 0.0;
        }
    }

    public final int getPlayerColorInt() {
        if (this.y) {
            return this.x;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if ((l2.bX.B || l2.cb.i()) && !l2.bX.F) {
            if (this.z != null && this.z != this.x) {
                this.c("aiDifficultyOverride:  " + this.z + "!=" + this.x);
            }
            return this.x;
        }
        if (this.z != null) {
            return this.z;
        }
        int n2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.aiDifficulty;
        return n2;
    }

    public final float getIncomeRate2() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.O()) {
            return l2.bX.ay.h;
        }
        return 1.0f;
    }

    public final float getExpenseRate() {
        if (!this.w) {
            return 1.0f;
        }
        int n2 = this.C();
        float f2 = 1.0f;
        f2 = n2 > 0 ? (f2 += (float)n2 * 0.4f) : (f2 += (float)n2 * 0.3f);
        if (n2 == 3) {
            f2 += 1.5f;
        }
        if (f2 < 0.1f) {
            f2 = 0.1f;
        }
        return f2;
    }

    public final void d(float f2) {
        this.credits += (double) f2;
        if (this.credits > 9.99999999E8) {
            this.credits = 9.99999999E8;
        }
    }


    public final void b(float f2) {
        if (!this.w) {
            this.c(f2);
            return;
        }
        float f3 = this.getExpenseRate();
        this.c(f3 * f2);
    }

    public final void c(float f2) {
        this.a(f2 *= this.D());
    }

    public static void recalculateEconomy() {
        try {
            PlayerState.b(10, true);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        for (int i2 = 0; i2 < as.length; ++i2) {
            PlayerState.as[i2] = null;
        }
    }

    public static HumanPlayer a(String string) {
        if (string == null || string.equals("")) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("findExistingPlayer: No clientId id");
            return null;
        }
        for (int i2 = 0; i2 < as.length; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || !string.equals(n2.O)) continue;
            if (n2 instanceof HumanPlayer) {
                return (HumanPlayer)n2;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.b("Player:" + i2 + " with matching clientId is not an instanceof player");
        }
        return null;
    }

    public static HumanPlayer b(String string) {
        if (string == null || string.equals("")) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("No id");
            return null;
        }
        for (int i2 = 0; i2 < as.length; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || !string.equals(n2.P)) continue;
            if (n2 instanceof HumanPlayer) {
                return (HumanPlayer)n2;
            }
            com.corrodinggames.rts.gameFramework.GlobalState.b("Player:" + i2 + " with matching clientId is not an instanceof player");
        }
        return null;
    }

    public static int getUnitCount() {
        for (int i2 = 0; i2 < c; ++i2) {
            if (b[i2] != null) continue;
            return i2;
        }
        return -1;
    }

    public static int getBuildingCount() {
        int n2;
        for (n2 = c; n2 < f; ++n2) {
            if (b[n2] != null) continue;
            return n2;
        }
        for (n2 = c - 1; n2 >= 0; --n2) {
            if (b[n2] != null) continue;
            return n2;
        }
        return -1;
    }

    public void updateResourceDisplay() {
        for (int i2 = 0; i2 < as.length; ++i2) {
            if (b[i2] != this) continue;
            PlayerState.as[i2] = null;
        }
    }

    public PlayerState() {
        this.w = this instanceof com.corrodinggames.rts.game.ai.AIStrategy;
    }

    public PlayerState(int n2) {
        this(n2, true);
    }

    public PlayerState(int n2, boolean bl) {
        this();
        this.c(n2, bl);
    }

    public void c(int n2, boolean bl) {
        if (this.k != n2) {
            if (bl) {
                this.I();
            }
            this.k = n2;
            this.r = n2;
            if (bl && n2 != -3) {
                PlayerState n3 = b[n2];
                if (n3 != null) {
                    n3.c("Being replaced");
                }
                PlayerState.as[n2] = this;
            }
            this.J();
        }
    }

    public void processResourceTick() {
        int n2 = this.K();
        this.ae.b(n2);
        int n3 = Color.a(Color.a(n2), (int)((float)Color.b(n2) * 0.5f), (int)((float)Color.c(n2) * 0.5f), (int)((float)Color.d(n2) * 0.5f));
        this.af.b(n3);
    }

    public boolean a(double d2) {
        return this.credits >= d2 || d2 == 0.0;
    }

    public final boolean c(PlayerState n2) {
        if (n2 == i || this == i) {
            return false;
        }
        return this.r != n2.r;
    }

    public int getMaxUnitCapacity() {
        return PlayerState.i(this.R());
    }

    public static void recalculateCapacities() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        try {
            PlayerState.d(l2.bQ.teamColors);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("initColors: Failed to read setting: '" + l2.bQ.teamColors + "': " + illegalArgumentException.getMessage(), (Throwable)illegalArgumentException);
            PlayerState.d("#00ff00,#d02013,#0463f3,#ffff40,#00ffff,#d0f8f7,#000000,#ff00ea,#ff7f18,#9368c4");
        }
        try {
            PlayerState.e(l2.bQ.teamColorsNames);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            com.corrodinggames.rts.gameFramework.GlobalState.a("initColors: Failed to read setting: '" + l2.bQ.teamColorsNames + "': " + illegalArgumentException.getMessage(), (Throwable)illegalArgumentException);
            PlayerState.e("GREEN,RED,BLUE,YELLOW,CYAN,WHITE,BLACK,PINK,ORANGE,PURPLE");
        }
    }

    public int getUsedUnitCapacity() {
        if (this.r == -3) {
            return PlayerState.i(-3);
        }
        return PlayerState.h(this.k);
    }

    public String getPlayerName() {
        if (this.k == -1) {
            return "GRAY";
        }
        if (this.k == -2) {
            return "GRAY";
        }
        return PlayerState.j(this.R());
    }









    public static PlayerState u(int n2) {
        if (n2 == -1) {
            return i;
        }
        if (n2 == -2) {
            return h;
        }
        if (n2 >= f) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("team index too high: " + n2);
            return null;
        }
        if (n2 < 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("team index too low: " + n2);
            return null;
        }
        return b[n2];
    }

    public void a(StopAction y2) {
    }

    public static void b(StopAction y2) {
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null) continue;
            n2.a(y2);
        }
    }




    public static void markAllPlayersDirty() {
        PlayerState.i.S = true;
        PlayerState.h.S = true;
        for (Object object2 : PlayerState.c()) {
            PlayerState n2 = (PlayerState) object2;
            n2.S = true;
        }
    }

    public static void onPlayerVictory() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.M()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping updateAllCachesFromChangedMetadata due to desync risk");
            return;
        }
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null) continue;
            n2.S = true;
        }
    }

    public static void checkDefeatedPlayers() {
        PlayerState.i.d(false);
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || n2.b() || n2.G || n2.F || n2.E) continue;
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            l2.bX.sendPacketToClients(n2);
        }
    }

    public int getTeamIndex() {
        if (this.D == -1) {
            return this.S();
        }
        return this.D;
    }

    public int getAllyCount() {
        PlayerState n2;
        if (this.k == -1) {
            return 5;
        }
        if (this.k == -2) {
            return 5;
        }
        int n3 = this.k;
        if (n3 >= 10) {
            n3 %= 10;
        }
        if (c > 10 && (n2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bX.z) != null && n2 != this && n2.getTeamIndex() == n3) {
            n3 = n3 != 5 ? 5 : 4;
        }
        return n3;
    }

    public void onMatchStart() {
    }

    public void a(com.corrodinggames.rts.game.units.custom.UnitConfig h2) {
        this.ak = h2;
    }

    public com.corrodinggames.rts.game.units.custom.UnitConfig getUnitFilter() {
        return this.ak;
    }

    public void b(com.corrodinggames.rts.game.units.custom.UnitConfig h2) {
        com.corrodinggames.rts.game.units.custom.UnitConfig h3 = this.U();
        if (h3 == null || h3.b() == 0) {
            this.a(h2);
            return;
        }
        if (com.corrodinggames.rts.game.units.custom.TeamTag.b(h3, h2)) {
            return;
        }
        com.corrodinggames.rts.game.units.custom.CollisionShape i2 = new com.corrodinggames.rts.game.units.custom.CollisionShape(h3);
        if (i2.a(h2)) {
            this.a(i2.a());
            return;
        }
    }

    public void c(com.corrodinggames.rts.game.units.custom.UnitConfig h2) {
        com.corrodinggames.rts.game.units.custom.UnitConfig h3 = this.U();
        if (h3 == null || h3.b() == 0) {
            return;
        }
        if (!com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(h2, h3)) {
            return;
        }
        com.corrodinggames.rts.game.units.custom.CollisionShape i2 = new com.corrodinggames.rts.game.units.custom.CollisionShape(h3);
        if (i2.b(h2)) {
            this.a(i2.a());
            return;
        }
    }

    public EffectManager getTeamStatModifiers() {  // 02b n.V(): return al (custom.e.f)
        return this.al;
    }

    public double c(com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2) {
        return this.al.a(a2);
    }

    public boolean a(UnitTypeComparator q2, PlayerState n2) {  // 02b n.java L1858: a(q,n) — q=UnitTypeComparator (v19.133f6 修正, ResourceType 为幻觉)
        if (q2 == UnitTypeComparator.own) {
            return n2 == this;
        }
        if (q2 == UnitTypeComparator.any) {
            return true;
        }
        if (q2 == UnitTypeComparator.ally) {
            return this.d(n2);
        }
        if (q2 == UnitTypeComparator.allyNotOwn) {
            return n2 != this && this.d(n2);
        }
        if (q2 == UnitTypeComparator.enemy) {
            return this.c(n2);
        }
        if (q2 == UnitTypeComparator.neutral) {
            return n2 == i;
        }
        if (q2 == UnitTypeComparator.notOwn) {
            return n2 != this;
        }
        throw new RuntimeException("Unsupported type: " + (Object)((Object)q2));
    }

    public void debugUnitCountByType() {
        int n2;
        com.corrodinggames.rts.gameFramework.GlobalState.e("debugUnitCountByType for team:" + this.k);
        CustomArrayList m2 = new CustomArrayList();
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (n2 = 0; n2 < n3; ++n2) {
            Object object = amArray[n2];
            if (((UnitInstance) object).player != this || ((UnitInstance) object).isDead) continue;
            com.corrodinggames.rts.game.units.UnitTypeHandle object2 = ((UnitInstance) object).dz;
            boolean bl2 = false;
            for (UnitTypeCount r2 : (java.util.Collection<UnitTypeCount>) (java.util.Collection) m2) {
                if (r2.a != object2) continue;
                ++r2.b;
                bl2 = true;
                break;
            }
            if (bl2) continue;
            UnitTypeCount r3 = new UnitTypeCount();
            r3.a = object2;
            r3.b = 1;
            m2.add(r3);
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("--- Units ---");
        n2 = 0;
        for (Object object : m2) {
            if (((UnitTypeCount) object).a.j()) continue;
            com.corrodinggames.rts.gameFramework.GlobalState.e(((UnitTypeCount) object).a.i() + " - count:" + ((UnitTypeCount) object).b);
            n2 += ((UnitTypeCount) object).b;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("total:" + n2);
        com.corrodinggames.rts.gameFramework.GlobalState.e("--- Buildings/Ignored in count ---");
        int n4 = 0;
        for (Object object2 : m2) {
            if (!((UnitTypeCount) object2).a.j()) continue;
            com.corrodinggames.rts.gameFramework.GlobalState.e(((UnitTypeCount) object2).a.i() + " - count:" + ((UnitTypeCount) object2).b);
            n4 += ((UnitTypeCount) object2).b;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("total:" + n4);
    }

    public void c(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Team(id: " + this.k + ", name:" + this.v + "):" + string);
    }

    public int b(com.corrodinggames.rts.game.units.custom.TeamTag g2, boolean bl2, boolean bl3) {
        int n2 = 0;
        if (this == i) {
            return 0;
        }
        PlayerState[] nArray = as;
        int n3 = c;
        for (int i2 = 0; i2 < n3; ++i2) {
            PlayerState n4 = nArray[i2];
            if (n4 == null || this == n4 || this.r == n4.r) continue;
            if (g2 == null) {
                n2 += n4.a(bl2, bl3);
                continue;
            }
            n2 += n4.a(g2, bl2, bl3);
        }
        return n2;
    }

    public int c(com.corrodinggames.rts.game.units.custom.TeamTag g2, boolean bl2, boolean bl3) {
        int n2 = 0;
        PlayerState[] nArray = as;
        int n3 = c;
        for (int i2 = 0; i2 < n3; ++i2) {
            PlayerState n4 = nArray[i2];
            if (n4 == null || this == n4 || !this.d(n4)) continue;
            if (g2 == null) {
                n2 += n4.a(bl2, bl3);
                continue;
            }
            n2 += n4.a(g2, bl2, bl3);
        }
        return n2;
    }

    public static void updateAllUnitCapacities() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PlayerState.i.T.a = l2.bB;
        PlayerState.h.T.a = l2.bB;
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null) continue;
            n2.T.a = l2.bB;
        }
    }

    public static void onResourceChanged() {
        PlayerState.i.resetForNewGame();
        PlayerState.h.resetForNewGame();
    }

    public void resetForNewGame() {
        this.m = false;
        this.n = false;
        this.credits = 4000.0;
        this.creditBuffer = 0.0;
        this.q = 0;
        this.ai = -2;
        this.at = false;
        this.au = -9999;
        this.E = false;
        this.av = -9999;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.am.a();
        this.an = 0.0f;
        this.ad = 0;
        this.R = 0;
        this.S = true;
        this.T = new TeamUnitTracker();
        this.T.a = com.corrodinggames.rts.gameFramework.GlobalState.B().bB;
        this.ak = com.corrodinggames.rts.game.units.custom.TeamTag.d;  // 02: custom.g.d
        this.al = new EffectManager();
    }

    public double getCreditsTotal() {
        long l2 = System.currentTimeMillis();
        if (GameUtils.c(this.aq - l2) > 166.66666f) {
            this.aq = l2;
            this.ar = this.credits + this.creditBuffer;
        }
        return this.ar;
    }

    public EffectManager get_ab() {  // 02b n.ab(): return V() (custom.e.f)
        return this.V();
    }










    public void d(InputNetStream k2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        boolean bl = k2.readBoolean();
        if (bl) {
            this.L = k2.readInt();
            this.M = k2.readInt();
            boolean bl2 = true;
            int n2 = this.L;
            int n3 = this.M;
            if (l2.bL != null) {
                n2 = l2.bL.mapHeight;
                n3 = l2.bL.tileWidth;
                if (this.L != n2 || this.M != n3) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("Map size does not match fog size: " + this.L + "!=" + n2 + "|" + this.M + "!=" + n3);
                }
            }
            this.N = bl2 ? new byte[n2][n3] : (byte[][])null;
            for (int i2 = 0; i2 < this.L; ++i2) {
                for (int i3 = 0; i3 < this.M; ++i3) {
                    if (bl2) {
                        this.N[i2][i3] = k2.d();
                        continue;
                    }
                    k2.d();
                }
            }
        } else {
            this.N = null;
        }
    }






    public static PlayerState[] d() {
        return b;
    }



    public static void d(int n2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!l2.bX.C) {
            return;
        }
        if (l2.cb.j()) {
            return;
        }
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n3 = b[i2];
            if (n3 == null || n3.r != n2 || n3.at) continue;
            n3.at = true;
            com.corrodinggames.rts.gameFramework.Command e2 = l2.cf.gete3();
            e2.i = n3;
            e2.r = true;
            e2.u = 100;
            l2.bX.registerRelayServer(e2);
        }
    }



    public static void e(int n2) {
        int n3 = -9999;
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n4 = b[i2];
            if (n4 == null || n4.r != n2 || !n4.j() || !n4.m() || n4.au <= n3) continue;
            n3 = n4.au;
        }
        if (n3 >= 0 && com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(n3, 120000)) {
            for (PlayerState n5 : as) {
                if (n5 == null || n5.r != n2) continue;
                n5.au = -9999;
            }
        }
    }






    public void d(boolean bl) {  // 02b n.d(boolean): 重建单位统计缓存 (T = e(true))
        if (bl || this.S) {
            this.T = this.e(true);
            this.S = false;
            if (this.R < this.T.b) {
                this.R = this.T.b;
            }
            if (!this.n && this.T.m) {
                this.n = true;
            }
            if (!this.m && this.getTotalUnitCount() > 0) {
                this.m = true;
            }
            this.onMatchStart();
        }
    }

    private TeamUnitTracker e(boolean bl) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        TeamUnitTracker s2 = new TeamUnitTracker();
        s2.a = l2.bB;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n2 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (am2.player != this) continue;
            s2.incrementUnitCount(am2);
            if (!bl) continue;
            am2.bY = true;
        }
        if (s2.a > l2.bC) {
            s2.a = l2.bC;
        }
        return s2;
    }



    public void f(int n2) {
        this.c(n2, true);
    }



    public boolean g(int n2) {
        return this.credits + this.p >= (double)n2 || n2 == 0;
    }



    private static void d(String string) {
        String[] stringArray = string.split(",");
        if (stringArray.length != 10) {
            throw new IllegalArgumentException("Expected 10 hex colors");
        }
        for (int i2 = 0; i2 < 10; ++i2) {
            String string2 = stringArray[i2];
            com.corrodinggames.rts.game.PlayerState.ag[i2] = Color.a(string2);
        }
    }



    public static void e() {  // 02b n.e() 静态: 重建 b 数组 (a 收集 i/h + 全部玩家)
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList arrayList1 = a;
        arrayList1.clear();
        arrayList1.add(i);
        arrayList1.add(h);
        for (int n2 = 0; n2 < c; ++n2) {
            PlayerState n3 = as[n2];
            if (n3 == null) continue;
            arrayList1.add(n3);
        }
        if (b.length != arrayList1.a) {
            b = new PlayerState[arrayList1.a];
        }
        int n4 = arrayList1.a;
        Object[] objectArray = arrayList1.a();
        for (int n5 = 0; n5 < n4; ++n5) {
            b[n5] = (PlayerState) objectArray[n5];
        }
    }

    private static void e(String string) {
        String[] stringArray = string.split(",");
        if (stringArray.length != 10) {
            throw new IllegalArgumentException("Expected 10 team color names");
        }
        for (int i2 = 0; i2 < 10; ++i2) {
            com.corrodinggames.rts.game.PlayerState.ah[i2] = stringArray[i2];
        }
    }



    public static com.corrodinggames.rts.gameFramework.rendering.Texture[] a(com.corrodinggames.rts.gameFramework.rendering.Texture e2) {
        return com.corrodinggames.rts.game.PlayerState.a(e2, com.corrodinggames.rts.game.GameMode.a, false);
    }



    public static com.corrodinggames.rts.gameFramework.rendering.Texture[] a(com.corrodinggames.rts.gameFramework.rendering.Texture e2, GameMode o2, boolean bl) {
        if (!bl || e2.A()) {
            return com.corrodinggames.rts.game.PlayerState.b(e2, o2);
        }
        return com.corrodinggames.rts.game.PlayerState.a(e2, o2);
    }



    public static com.corrodinggames.rts.gameFramework.rendering.Texture[] a(com.corrodinggames.rts.gameFramework.rendering.Texture e2, GameMode o2) {
        com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU && !com.corrodinggames.rts.gameFramework.GlobalState.aW || o2 == com.corrodinggames.rts.game.GameMode.e) {
            for (int i2 = 0; i2 < eArray.length; ++i2) {
                eArray[i2] = e2;
            }
            return eArray;
        }
        com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray2 = e2.setRenderTarget(o2);
        if (eArray2 != null) {
            return eArray2;
        }
        ExtraManager br2 = com.corrodinggames.rts.gameFramework.GlobalState.B().cd;
        br2.a(GamePhase.D);
        for (int i3 = 0; i3 < eArray.length; ++i3) {
            int n2 = com.corrodinggames.rts.game.PlayerState.i(i3);
            eArray[i3] = i3 == 0 ? e2 : new com.corrodinggames.rts.gameFramework.rendering.TeamColorTexture(e2, n2, o2, i3);
        }
        br2.b(GamePhase.D);
        e2.setRenderTarget(o2, eArray);
        return eArray;
    }



    public static com.corrodinggames.rts.gameFramework.rendering.Texture[] b(com.corrodinggames.rts.gameFramework.rendering.Texture e2, GameMode o2) {
        int n2;
        com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
        if (com.corrodinggames.rts.gameFramework.GlobalState.aU && !com.corrodinggames.rts.gameFramework.GlobalState.aW || o2 == com.corrodinggames.rts.game.GameMode.e || e2.A()) {
            for (int i2 = 0; i2 < eArray.length; ++i2) {
                eArray[i2] = e2;
            }
            return eArray;
        }
        com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray2 = e2.setRenderTarget(o2);
        if (eArray2 != null) {
            return eArray2;
        }
        ExtraManager br2 = com.corrodinggames.rts.gameFramework.GlobalState.B().cd;
        br2.a(GamePhase.D);
        int[] nArray = new int[10];
        int[] nArray2 = new int[10];
        for (n2 = 0; n2 < nArray.length; ++n2) {
            nArray[n2] = com.corrodinggames.rts.game.PlayerState.i(n2);
            nArray2[n2] = n2;
        }
        for (n2 = 0; n2 < eArray.length; ++n2) {
            if (n2 == 0) continue;
            eArray[n2] = e2.h();
            eArray[n2].setRenderTarget("color(" + n2 + "):" + e2.setRenderTarget());
            eArray[n2].j();
        }
        e2.j();
        if (o2 == com.corrodinggames.rts.game.GameMode.b) {
            com.corrodinggames.rts.game.PlayerState.b(e2, eArray, nArray);
        } else if (o2 == com.corrodinggames.rts.game.GameMode.d) {
            com.corrodinggames.rts.game.PlayerState.a(e2, eArray, nArray, nArray2);
        } else {
            com.corrodinggames.rts.game.PlayerState.a(e2, eArray, nArray);
        }
        for (n2 = 0; n2 < eArray.length; ++n2) {
            if (eArray[n2] == null) continue;
            eArray[n2].p();
            eArray[n2].s();
        }
        e2.r();
        eArray[0] = e2;
        br2.b(GamePhase.D);
        e2.setRenderTarget(o2, eArray);
        return eArray;
    }



    public static void a(com.corrodinggames.rts.gameFramework.rendering.Texture e2, com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray, int[] nArray) {
        int n2 = e2.m();
        int n3 = e2.l();
        int[] nArray2 = new int[nArray.length];
        int[] nArray3 = new int[nArray.length];
        int[] nArray4 = new int[nArray.length];
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            nArray2[i2] = Color.b(nArray[i2]);
            nArray3[i2] = Color.c(nArray[i2]);
            nArray4[i2] = Color.d(nArray[i2]);
        }
        float f2 = 0.003921569f;
        for (int i3 = 0; i3 < n3; ++i3) {
            for (int i4 = 0; i4 < n2; ++i4) {
                int n4;
                int n5;
                int n6;
                int n7;
                int n8;
                int n9;
                int n10 = e2.setRenderTarget(i4, i3);
                int n11 = com.corrodinggames.rts.gameFramework.rendering.FontRenderer.a(n10);
                if (n11 == 0) {
                    if (n10 == 0) continue;
                    for (n9 = 0; n9 < eArray.length; ++n9) {
                        if (eArray[n9] == null) continue;
                        eArray[n9].a(i4, i3, 0);
                    }
                    continue;
                }
                n9 = com.corrodinggames.rts.gameFramework.rendering.FontRenderer.c(n10);
                if (n9 <= 0 || (n8 = com.corrodinggames.rts.gameFramework.rendering.FontRenderer.b(n10)) != (n7 = com.corrodinggames.rts.gameFramework.rendering.FontRenderer.d(n10))) continue;
                if (n8 == 0) {
                    n6 = n9;
                    for (int i5 = 0; i5 < eArray.length; ++i5) {
                        if (eArray[i5] == null) continue;
                        int n12 = nArray2[i5] * n6 >> 8;
                        n5 = nArray3[i5] * n6 >> 8;
                        n4 = nArray4[i5] * n6 >> 8;
                        eArray[i5].setRenderTarget(i4, i3, Color.a(n11, n12, n5, n4));
                    }
                    continue;
                }
                if (n9 == n8) continue;
                n6 = n8;
                float f3 = (float)n6 * 0.003921569f;
                float f4 = (float)n9 * 0.003921569f - f3;
                for (n5 = 0; n5 < eArray.length; ++n5) {
                    if (eArray[n5] == null) continue;
                    n4 = (int)((float)n6 + (float)nArray2[n5] * f4);
                    int n13 = (int)((float)n6 + (float)nArray3[n5] * f4);
                    int n14 = (int)((float)n6 + (float)nArray4[n5] * f4);
                    n4 = com.corrodinggames.rts.gameFramework.GameUtils.b(n4, 0, 255);
                    n13 = com.corrodinggames.rts.gameFramework.GameUtils.b(n13, 0, 255);
                    n14 = com.corrodinggames.rts.gameFramework.GameUtils.b(n14, 0, 255);
                    eArray[n5].setRenderTarget(i4, i3, Color.a(n11, n4, n13, n14));
                }
            }
        }
    }



    public static void b(com.corrodinggames.rts.gameFramework.rendering.Texture e2, com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray, int[] nArray) {
        int n2;
        int n3 = e2.m();
        int n4 = e2.l();
        int[] nArray2 = new int[nArray.length];
        int[] nArray3 = new int[nArray.length];
        int[] nArray4 = new int[nArray.length];
        for (n2 = 0; n2 < nArray.length; ++n2) {
            nArray2[n2] = Color.b(nArray[n2]);
            nArray3[n2] = Color.c(nArray[n2]);
            nArray4[n2] = Color.d(nArray[n2]);
        }
        for (n2 = 0; n2 < n3; ++n2) {
            for (int i2 = 0; i2 < n4; ++i2) {
                int n5 = e2.setRenderTarget(n2, i2);
                int n6 = Color.a(n5);
                if (n6 <= 0) continue;
                int n7 = Color.b(n5);
                int n8 = Color.c(n5);
                int n9 = Color.d(n5);
                float f2 = 0.15f;
                for (int i3 = 0; i3 < eArray.length; ++i3) {
                    int n10 = (int)((float)n7 + (float)nArray2[i3] * f2);
                    int n11 = (int)((float)n8 + (float)nArray3[i3] * f2);
                    int n12 = (int)((float)n9 + (float)nArray4[i3] * f2);
                    n10 = com.corrodinggames.rts.gameFramework.GameUtils.b(n10, 0, 255);
                    n11 = com.corrodinggames.rts.gameFramework.GameUtils.b(n11, 0, 255);
                    n12 = com.corrodinggames.rts.gameFramework.GameUtils.b(n12, 0, 255);
                    if (eArray[i3] == null) continue;
                    eArray[i3].setRenderTarget(n2, i2, Color.a(n6, n10, n11, n12));
                }
            }
        }
    }



    public static void a(com.corrodinggames.rts.gameFramework.rendering.Texture e2, com.corrodinggames.rts.gameFramework.rendering.Texture[] eArray, int[] nArray, int[] nArray2) {
        int n2;
        int n3 = e2.m();
        int n4 = e2.l();
        int[] nArray3 = new int[nArray.length];
        int[] nArray4 = new int[nArray.length];
        int[] nArray5 = new int[nArray.length];
        for (n2 = 0; n2 < nArray.length; ++n2) {
            nArray3[n2] = Color.b(nArray[n2]);
            nArray4[n2] = Color.c(nArray[n2]);
            nArray5[n2] = Color.d(nArray[n2]);
        }
        for (n2 = 0; n2 < n4; ++n2) {
            for (int i2 = 0; i2 < n3; ++i2) {
                int n5;
                int n6 = e2.setRenderTarget(i2, n2);
                int n7 = Color.a(n6);
                if (n7 == 0) {
                    if (Color.b(n6) <= 0 && Color.c(n6) <= 0 && Color.d(n6) <= 0) continue;
                    for (n5 = 0; n5 < eArray.length; ++n5) {
                        if (eArray[n5] == null) continue;
                        eArray[n5].setRenderTarget(i2, n2, Color.a(0, 0, 0, 0));
                    }
                    continue;
                }
                n5 = Color.c(n6);
                int n8 = Color.b(n6);
                int n9 = Color.d(n6);
                float f2 = com.corrodinggames.rts.gameFramework.GameUtils.c(com.corrodinggames.rts.gameFramework.GameUtils.c(n8, n5), n9);
                float f3 = com.corrodinggames.rts.gameFramework.GameUtils.d(n8 - n5);
                f3 = com.corrodinggames.rts.gameFramework.GameUtils.f(f3, (float)com.corrodinggames.rts.gameFramework.GameUtils.d(n5 - n9));
                if (!((f3 = com.corrodinggames.rts.gameFramework.GameUtils.f(f3, (float)com.corrodinggames.rts.gameFramework.GameUtils.d(n9 - n8))) > 15.0f)) continue;
                for (int i3 = 0; i3 < eArray.length; ++i3) {
                    if (eArray[i3] == null) continue;
                    float f4 = f3 / 255.0f;
                    int n10 = (int)(f2 + (float)nArray3[i3] * f4);
                    int n11 = (int)(f2 + (float)nArray4[i3] * f4);
                    int n12 = (int)(f2 + (float)nArray5[i3] * f4);
                    n10 = com.corrodinggames.rts.gameFramework.GameUtils.b(n10, 0, 255);
                    n11 = com.corrodinggames.rts.gameFramework.GameUtils.b(n11, 0, 255);
                    n12 = com.corrodinggames.rts.gameFramework.GameUtils.b(n12, 0, 255);
                    eArray[i3].a(i2, n2, Color.a(n7, n10, n11, n12));
                }
            }
        }
    }



    public void a(UnitType y2) {
    }



    public static void b(UnitType y2) {
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null) continue;
            n2.a(y2);
        }
    }



    public void e(float f2) {  // 02b n.e(float): AI 经济策略更新 (每帧)
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.ai > 0) {
            --this.ai;
            return;
        }
        if (this.ai == -2) {
            this.ai = this.k;
        } else {
            this.ai = 10;
        }
        if (!this.G && !l2.cb.j()) {
            boolean bl = false;
            boolean bl2 = false;
            boolean bl3 = false;
            boolean bl4 = l2.bX.ay.l;
            boolean bl5 = false;
            UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
            int n2 = 0;
            UnitInstance am2;
            for (int n3 = com.corrodinggames.rts.game.units.UnitInstance.bE.size(); n2 < n3; ++n2) {
                am2 = amArray[n2];
                if (am2.player == this) {
                    if (!am2.isCapturable()) {
                        bl = true;
                        if (!this.F && (am2.bJ() || am2.ak())) {
                            bl2 = true;
                            break;
                        }
                    } else {
                        bl5 = true;
                    }
                } else if (bl4 && am2.player != null && am2.player.d(this) && !am2.isCapturable()) {
                    bl3 = true;
                }
            }
            if (!bl && !bl3) {
                boolean bl6 = false;
                if (bl5 && l2.bx < 100 && l2.bv) {
                    bl6 = true;
                }
                this.G = true;
                this.a();
                Iterator iterator5 = com.corrodinggames.rts.game.units.UnitInstance.bE.iterator();
                while (iterator5.hasNext()) {
                    am2 = (UnitInstance) iterator5.next();
                    if (am2.player == this && !am2.u()) {
                        if (bl6 && !am2.isDead && am2.isCapturable()) {
                            UnitTypeHandle as2 = am2.r();
                            String string = am2.c() + " Warning: This unit got ignored in defeated check and now being removed";
                            if (as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry && ((com.corrodinggames.rts.game.units.custom.ModUnitRegistry) as2).aO) {
                                string = string + " (Likely due to canNotBeDirectlyAttacked:true)";
                            }
                            l2.cb.a(-1, null, string, l2.bx);
                            if (l2.bS != null && l2.bS.selectionGroup != null) {
                                l2.bS.selectionGroup.a(null, string);
                            }
                        }
                        am2.cj();
                    }
                }
                l2.bX.cancelNotification(this);
            }
            if (!bl2 && !this.F && !this.G) {
                this.F = true;
                l2.bX.sendPacketToClients(this);
            }
        }
    }

    public static void f(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PlayerState.i.a(f2);
        PlayerState.h.a(f2);
        block0: for (int i2 = 0; i2 < c; ++i2) {
            int n2;
            PlayerState n3 = b[i2];
            if (n3 == null) continue;
            n3.a(f2);
            n3.e(f2);
            if (!n3.at && (n2 = com.corrodinggames.rts.game.PlayerState.b(n3.r)) > 0) {
                int n4 = com.corrodinggames.rts.game.PlayerState.c(n3.r);
                if (n2 >= n4) {
                    com.corrodinggames.rts.game.PlayerState.d(n3.r);
                    com.corrodinggames.rts.game.PlayerState.o();
                } else {
                    com.corrodinggames.rts.game.PlayerState.e(n3.r);
                }
            }
            if (!n3.E) continue;
            if (n3.av < 0) {
                n3.av = l2.by;
            }
            if (n3.G) continue;
            n2 = 0;
            Iterator iterator4 = com.corrodinggames.rts.game.units.UnitInstance.bE.iterator();
            while (iterator4.hasNext()) {
                UnitInstance am2 = (UnitInstance) iterator4.next();
                if (am2.player != n3 || am2.u()) continue;
                boolean bl2 = false;
                int n5 = 1;
                if (com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(n3.av, 10000)) {
                    bl2 = true;
                    n5 = 50;
                } else if (com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(n3.av, 6000)) {
                    bl2 = com.corrodinggames.rts.gameFramework.GameUtils.a(am2, 0, 100) > 90;
                    n5 = 20;
                } else if (com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(n3.av, 2000)) {
                    bl2 = com.corrodinggames.rts.gameFramework.GameUtils.a(am2, 0, 100) > 98;
                    n5 = 2;
                }
                if (am2 instanceof CommandCenter) {
                    bl2 = true;
                }
                if (!bl2) continue;
                am2.hp = -1.0f;
                if (++n2 <= n5) continue;
                continue block0;
            }
        }
        if (l2.P() && l2.bQ.aiDifficulty != aj) {
            l2.bX.aq();
            aj = l2.bQ.aiDifficulty;
        }
    }



    public static void g(float f2) {
        PlayerState[] nArray;
        com.corrodinggames.rts.game.PlayerState.e();
        for (PlayerState n2 : nArray = com.corrodinggames.rts.game.PlayerState.d()) {
            n2.d(false);
        }
    }



    public static void h(float f2) {
        for (int i2 = 0; i2 < c; ++i2) {
            PlayerState n2 = b[i2];
            if (n2 == null || !(n2 instanceof AIStrategy)) continue;
            AIStrategy a2 = (AIStrategy)n2;
            a2.i(f2);
        }
    }



    public com.corrodinggames.rts.game.units.custom.UnitConfig U() {
        return this.ak;
    }



    public EffectManager V() {
        return this.al;
    }



    public EffectManager ab() {
        return this.V();
    }



    public void d(UnitInstance am2) {
    }






    @Override
    public int compareTo(Object object) {
        return this.compareTo((PlayerState) object);
    }


    // v19.111 分批补全 (符号存在性过滤, 批0)











    public static strictfp int g() {
    int var0 = 0;

    for(int i = 0; i < c; ++i) {
    PlayerState var2 = as[i];
    if(var2 != null && !var2.b() && !var2.F && !var2.G) {
    ++var0;
    }
    }

    return var0;
    }



    public strictfp String h() {
    return a(this.r);
    }



    public strictfp void i() {
    this.E = false;
    this.at = false;
    this.au = -9999;
    }



    public strictfp boolean j() {
    return this.E;
    }



    public strictfp boolean k() {
    return this.au >= 0;
    }



    public strictfp void l() {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    this.au = var1.by;
    }



    public strictfp boolean m() {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    return (this.F || this.G) && !var1.bX.ay.l?false:(this.w?false:(this.isIdle()?false:!this.ab || this.k()));
    }



    public static strictfp void o() {
    for(int i = 0; i < c; ++i) {
    PlayerState var1 = as[i];
    if(var1 != null) {
    var1.au = -9999;
    }
    }

    }






    public strictfp boolean q() {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    return var1.bs == this;
    }



    public strictfp boolean r() {
    return this.Q == 1;
    }



    public final strictfp int s() {
    return this.T.c + this.T.f + this.T.e;
    }



    public strictfp boolean t() {
    boolean var1 = false;
    TeamUnitTracker var2 = this.e(false);
    if(this.T.b != var2.b) {
    com.corrodinggames.rts.gameFramework.l.b("unitCountExcludingBuildingsIncludingQueued: " + this.T.b + "!=" + var2.b + " (team:" + this.k + " fails: " + this.ad + ")");
    ++this.ad;
    var1 = true;
    }

    if(this.T.a != var2.a) {
    com.corrodinggames.rts.gameFramework.l.b("unitsMax: " + this.T.a + "!=" + var2.a + " (team:" + this.k + " fails: " + this.ad + ")");
    ++this.ad;
    var1 = true;
    }

    if(this.T.g != var2.g) {
    com.corrodinggames.rts.gameFramework.l.b("incomeRate: " + this.T.g + "!=" + var2.g + " (team:" + this.k + " fails: " + this.ad + ")");
    ++this.ad;
    var1 = true;
    }

    if(this.T.f != var2.f) {
    com.corrodinggames.rts.gameFramework.l.b("incompleteUnitCountOfAllTypes: " + this.T.f + "!=" + var2.f + " (team:" + this.k + " fails: " + this.ad + ")");
    ++this.ad;
    var1 = true;
    }

    if(this.T.e != var2.e) {
    com.corrodinggames.rts.gameFramework.l.b("queuedCountOfAllTypes: " + this.T.e + "!=" + var2.e + " (team:" + this.k + " fails: " + this.ad + ")");
    ++this.ad;
    var1 = true;
    }

    if(this.T.c != var2.c) {
    com.corrodinggames.rts.gameFramework.l.b("unitCountOfAllTypesOnlyCompleted: " + this.T.c + "!=" + var2.c + " (team:" + this.k + " fails: " + this.ad + ")");
    ++this.ad;
    var1 = true;
    }

    if(!this.T.h.e(var2.h)) {
    com.corrodinggames.rts.gameFramework.l.b("customIncomeRate: " + this.T.h + "!=" + var2.h + " (team:" + this.k + " fails: " + this.ad + ")");
    com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.h.a(false, true, 30, true, true));
    com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + var2.h.a(false, true, 30, true, true));
    ++this.ad;
    var1 = true;
    }

    if(!this.T.l.e(var2.l)) {
    com.corrodinggames.rts.gameFramework.l.b("streamingRateNegative (team:" + this.k + " fails: " + this.ad + ")");
    com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.l.a(false, true, 30, true, true));
    com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + var2.l.a(false, true, 30, true, true));
    ++this.ad;
    var1 = true;
    }

    if(!this.T.k.e(var2.k)) {
    com.corrodinggames.rts.gameFramework.l.b("streamingRatePositive (team:" + this.k + " fails: " + this.ad + ")");
    com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.k.a(false, true, 30, true, true));
    com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + var2.k.a(false, true, 30, true, true));
    ++this.ad;
    var1 = true;
    }

    if(var1) {
    ;
    }

    return var1;
    }



    public strictfp int v() {
    return (int)((float)this.getIncomeRate() * this.getExpenseRate() + 0.5F);
    }



    public strictfp String y() {
    int var1 = this.getCreditsInteger();
    return var1 == -99?"":(this.w?"":(var1 == -2?"(disconnected)":(var1 == -1?"(disconnected)":"(" + var1 + ")")));
    }



    public strictfp String z() {
    int var1 = this.getCreditsInteger();
    return var1 == -99?"HOST":(this.w?"-":(var1 == -1?"N/A":(var1 == -2?"-":(this.r()?var1 + " (HOST)":"" + var1))));
    }



    public final strictfp int C() {
    if(this.y) {
    return this.x;
    } else {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    if((var1.bX.B || var1.cb.i()) && !var1.bX.F) {
    if(this.z != null && this.z.intValue() != this.x) {
    this.c("aiDifficultyOverride:  " + this.z + "!=" + this.x);
    }

    return this.x;
    } else if(this.z != null) {
    return this.z.intValue();
    } else {
    int var2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.aiDifficulty;
    return var2;
    }
    }
    }



    public final strictfp float D() {
    com.corrodinggames.rts.gameFramework.GlobalState var1 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    return var1.O()?var1.bX.ay.h:1.0F;
    }



    public static strictfp void F() {
    try {
    b(10, true);
    } catch (IOException e) {
    throw new RuntimeException(e);
    }

    for(int i = 0; i < as.length; ++i) {
    as[i] = null;
    }

    }



    public static strictfp int G() {
    for(int i = 0; i < c; ++i) {
    if(as[i] == null) {
    return i;
    }
    }

    return -1;
    }



    public static strictfp int H() {
    int var0;
    for(var0 = c; var0 < f; ++var0) {
    if(as[var0] == null) {
    return var0;
    }
    }

    for(var0 = c - 1; var0 >= 0; --var0) {
    if(as[var0] == null) {
    return var0;
    }
    }

    return -1;
    }



    public strictfp void I() {
    for(int i = 0; i < as.length; ++i) {
    if(as[i] == this) {
    as[i] = null;
    }
    }

    }






    public strictfp void J() {
    int var1 = this.K();
    this.ae.b(var1);
    int var2 = Color.a(Color.a(var1), (int)((float)Color.b(var1) * 0.5F), (int)((float)Color.c(var1) * 0.5F), (int)((float)Color.d(var1) * 0.5F));
    this.af.b(var2);
    }



    public strictfp int K() {
    return i(this.R());
    }



    public strictfp int M() {
    return this.r == -3?i(-3):h(this.k);
    }



    public strictfp String N() {
    return this.k == -1?"GRAY":(this.k == -2?"GRAY":j(this.R()));
    }



    public static strictfp String j(int var0) {
    return var0 >= 0 && var0 < 10?ah[var0]:"GRAY";
    }



    public static strictfp void O() {
    i.S = true;
    h.S = true;

    PlayerState var1;
    for(Iterator it = c().iterator(); it.hasNext(); var1.S = true) {
    var1 = (PlayerState)it.next();
    }

    }



    public static strictfp void P() {
    com.corrodinggames.rts.gameFramework.GlobalState var0 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    if(var0.M()) {
    com.corrodinggames.rts.gameFramework.GlobalState.e("Skipping updateAllCachesFromChangedMetadata due to desync risk");
    } else {
    for(int i = 0; i < c; ++i) {
    PlayerState var2 = as[i];
    if(var2 != null) {
    var2.S = true;
    }
    }

    }
    }



    public static strictfp void Q() {
    PlayerState.i.d(false);

    for(int i = 0; i < c; ++i) {
    PlayerState var1 = as[i];
    if(var1 != null && !var1.b() && !var1.G && !var1.F && !var1.E) {
    com.corrodinggames.rts.gameFramework.GlobalState var2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    var2.bX.sendPacketToClients(var1);
    }
    }

    }



    public strictfp int R() {
    return this.D == -1?this.S():this.D;
    }



    public strictfp int S() {
    if(this.k == -1) {
    return 5;
    } else if(this.k == -2) {
    return 5;
    } else {
    int var1 = this.k;
    if(var1 >= 10) {
    var1 %= 10;
    }

    if(c > 10) {
    PlayerState var2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bX.z;
    if(var2 != null && var2 != this && var2.R() == var1) {
    if(var1 != 5) {
    var1 = 5;
    } else {
    var1 = 4;
    }
    }
    }

    return var1;
    }
    }



    public strictfp void T() {}



    public static strictfp void X() {
    com.corrodinggames.rts.gameFramework.GlobalState var0 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    i.T.a = var0.bB;
    h.T.a = var0.bB;

    for(int i = 0; i < c; ++i) {
    PlayerState var2 = as[i];
    if(var2 != null) {
    var2.T.a = var0.bB;
    }
    }

    }



    public static strictfp void Y() {
    i.resetForNewGame();
    h.resetForNewGame();
    }


    public void W() {
        // v19.115p 批5 补缺: 02b n.W() 字节码铁证 (bp.java 超限清场调用点) — 简化 TODO
    }



}
