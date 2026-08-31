/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.slots.AbstractCommandSlot;
import com.corrodinggames.rts.game.units.commands.slots.FactoryBuildSlot;
import com.corrodinggames.rts.game.units.commands.slots.d;
import com.corrodinggames.rts.game.units.commands.slots.e;
import com.corrodinggames.rts.game.units.commands.slots.f;
import com.corrodinggames.rts.game.units.commands.slots.g;
import com.corrodinggames.rts.game.units.commands.slots.h;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class BuildActionSlot
extends MobileBuilderBase {
    static Texture g = null;
    private static Texture experimentLevel = null;
    private static Texture requiredResource = null;
    private static Texture costValue = null;
    private static Texture timeValue = null;
    private static Texture statMultiplier = null;
    static Texture[] h = new Texture[10];
    static Texture i = null;
    boolean j;
    int k;
    FactoryBuildSlot l = new h(this);
    static String t = "gun";
    static String u = "gunT2";
    static String v = "gunT3";
    static String w = "artillery";
    static String x = "flamethrower";
    static String C = "aa_t1";
    static String D = "aa_t2";
    static String E = "aa_flak";
    static Texture F = null;
    static Texture[] G = new Texture[10];
    boolean H = true;
    float I;
    float J;
    boolean K;
    Rect dK = new Rect();
    public static GameAction dL = new BuildActionSlot$1(101);
    public static GameAction dM = new BuildActionSlot$2(104);
    public static GameAction dN = new BuildActionSlot$3(102);
    public static GameAction dO = new BuildActionSlot$4(103);
    static ArrayList dP = new ArrayList();

    public int M() {
        return this.l.b();
    }


    public float H(int n2) {
        return this.l.h(n2);
    }


    public void a_(String string) {
        this.b(string);
    }

    public void b(String string) {
        if (!this.l.a(string)) {
            FactoryBuildSlot c2 = this.l;
            this.l = this.c(string);
            this.l.a(c2);
        }
    }

    public FactoryBuildSlot c(String string) {
        if (string.equals(t)) {
            return new h(this);
        }
        if (string.equals(u)) {
            return new f(this);
        }
        if (string.equals(v)) {
            return new g(this);
        }
        if (string.equals(w)) {
            return new d(this);
        }
        if (string.equals(x)) {
            return new e(this);
        }
        return null;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.j);
        as2.a(this.k == 1);
        as2.c(this.l.c());
        as2.a(this.k);
        super.a(as2);
    }

    @Override
    public void a(InputNetStream k2) {
        boolean bl = k2.readBoolean();
        if (bl) {
            this.a(2);
        }
        if (k2.b() >= 27) {
            int n2 = this.k = k2.e() ? 1 : 0;
        }
        if (k2.b() >= 35) {
            String string = k2.readString();
            if (!this.l.a(string)) {
                this.b(string);
            }
            this.k = k2.readInt();
        } else if (bl && !(this instanceof AbstractCommandSlot)) {
            this.b(u);
        }
        super.a(k2);
    }


    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return G[this.player.R()];
    }

    public static void dB() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        g = l2.bO.a(R$drawable.turret_base);
        i = l2.bO.a(R$drawable.turret_base_dead);
        experimentLevel = l2.bO.a(R$drawable.turret_top);
        requiredResource = l2.bO.a(R$drawable.turret_top_l2);
        costValue = l2.bO.a(R$drawable.turret_top_l3);
        timeValue = l2.bO.a(R$drawable.turret_top_artillery);
        statMultiplier = l2.bO.a(R$drawable.turret_top_flame);
        h = com.corrodinggames.rts.game.PlayerState.a(g);
        F = l2.bO.a(R$drawable.unit_icon_building_turrent);
        G = com.corrodinggames.rts.game.PlayerState.a(F);
    }


    public boolean L() {
        this.M = i;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.d);
        return true;
    }


    public Texture d() {
        if (this.isDead) {
            return i;
        }
        if (this.player == null) {
            return h[h.length - 1];
        }
        return h[this.player.R()];
    }


    public Texture k() {
        return null;
    }


    public Texture d(int n2) {
        return this.l.d(n2);
    }

    public BuildActionSlot(boolean bl) {
        super(bl);
        this.T(35);
        this.U(42);
        this.ck = this.cj = 16.0f;
        this.hp = this.maxHp = 700.0f;
        this.M = g;
        this.cL[0].turretAngle = (float)com.corrodinggames.rts.gameFramework.GameUtils.a(this, -180, 180);  // 02b ap.a = turretAngle
        this.n.a(0, 0, 1, 1);  // 02b b.java L171: this.n (BuildSlot 矩形, sourceRect 为幻觉名)
        this.o.a(0, 0, 1, 1);  // 02b b.java L172: this.o
    }

    public void s(float f2) {
        int n2 = 0;
        if (this.cL[n2].a()) {
            if (this.H) {
                this.I = this.cL[n2].turretAngle;  // 02b ap.a = turretAngle
                this.H = false;
                this.J = (float)com.corrodinggames.rts.gameFramework.GameUtils.a(this, 0, 120);
            }
            this.J += f2;
            if (this.J > 450.0f) {
                this.J = (float)com.corrodinggames.rts.gameFramework.GameUtils.a(this, 0, 30);
                boolean bl = this.K = !this.K;
            }
            if (this.J < 120.0f) {
                if (this.K) {
                    this.a(f2 * 0.3f, this.I - 20.0f, n2);
                } else {
                    this.a(f2 * 0.3f, this.I + 20.0f, n2);
                }
            }
        } else {
            this.H = true;
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bT()) {
            this.l.a(f2);
        }
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        this.l.a(am2, n2);
    }

    @Override
    public float m() {
        return this.l.a();
    }

    @Override
    public float b(int n2) {
        return this.l.a(n2);
    }

    @Override
    public float c(int n2) {
        return this.l.e(n2);
    }


    public float w(int n2) {
        return this.l.f(n2);
    }


    public boolean b(int n2, float f2) {
        return false;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        if (!this.isDead) {
            this.dC();
        }
        return true;
    }

    void dC() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Texture e2 = null;
        int n2 = 0;
        e2 = this.d(n2);
        PointF pointF = this.G(n2);
        l2.bO.a(e2, pointF.a - com.corrodinggames.rts.gameFramework.GlobalState.B().cw, pointF.b - com.corrodinggames.rts.gameFramework.GlobalState.B().cx, this.cL[n2].turretAngle, this.f());  // 02b ap.a = turretAngle
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.f;
    }

    @Override
    public boolean l() {
        return true;
    }


    public boolean af() {
        return false;
    }


    public float g(int n2) {
        return this.l.g(n2);
    }


    public void M(int n2) {
        if (this.b(n2) < 10.0f) {
            return;
        }
        super.M(n2);
    }

    @Override
    public void a(BuilderUnit j2) {
        GameAction s2 = this.a(j2.j);
        if (s2 != null) {
            s2.f(this);
        } else {
            com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("specialAction=null on completeQueueItem(turret) for item.uIndex:" + j2.j + " id:" + this.eh, true);
        }
    }


    public com.corrodinggames.rts.game.units.actions.ActionId cm() {
        if (this.M() == 1) {
            return dL.N();
        }
        if (this.l instanceof f) {
            return dM.N();
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public void a(ArrayList arrayList) {
        arrayList.clear();
        if (this.M() == 1) {
            arrayList.add(dN.N());
            arrayList.add(dO.N());
        }
    }


    public void a(int n2) {
        if (n2 == 1) {
            this.j = false;
        } else if (n2 == 2 && !this.j) {
            this.j = true;
        }
    }


    public PointF E(int n2) {
        return this.l.c(n2);
    }

    @Override
    public float bV() {
        if (this.cL[0].e > 0.0f && this.l.a(w)) {
            return 1.0f - this.cL[0].e / this.b(0);
        }
        return super.bV();
    }


    public PointF G(int n2) {
        bh.a(super.G(n2));
        bh.b(0.0f, -5.0f);
        return bh;
    }


    public ArrayList N() {
        return dP;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public float cZ() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.tilePixelWidth;
    }


    public float da() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.tilePixelHeight;
    }


    public float db() {
        return super.db() - 8.0f;
    }


    public int cL() {
        return this.l.d();
    }


    public float q(int n2) {
        return this.l.b(n2);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }

    static /* synthetic */ PointF a(BuildActionSlot b2, int n2) {
        return b2.E(n2);
    }

    static /* synthetic */ Texture dD() {
        return experimentLevel;
    }

    static /* synthetic */ Texture dE() {
        return requiredResource;
    }

    static /* synthetic */ PointF b(BuildActionSlot b2, int n2) {
        return b2.E(n2);
    }

    static /* synthetic */ Texture dF() {
        return costValue;
    }

    static /* synthetic */ PointF c(BuildActionSlot b2, int n2) {
        return b2.E(n2);
    }

    static /* synthetic */ Texture dG() {
        return timeValue;
    }

    static /* synthetic */ Texture dH() {
        return statMultiplier;
    }

    static /* synthetic */ void a(BuildActionSlot b2) {
        b2.W();
    }

    static /* synthetic */ void b(BuildActionSlot b2) {
        b2.W();
    }

    static /* synthetic */ void c(BuildActionSlot b2) {
        b2.W();
    }

    static /* synthetic */ void d(BuildActionSlot b2) {
        b2.W();
    }

    static {
        dP.add(dL);
        dP.add(dM);
        dP.add(dN);
        dP.add(dO);
    }
}
