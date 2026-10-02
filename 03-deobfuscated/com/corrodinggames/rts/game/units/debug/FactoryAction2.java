/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.debug;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ReclaimAction;
import com.corrodinggames.rts.game.units.actions.BuildQueueAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.MovementPath;
import com.corrodinggames.rts.game.units.debug.FactoryAction6;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;

public strictfp class FactoryAction2
extends FactoryAction6
implements MovementPath {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture[] e = new Texture[10];
    PointF[] f = new PointF[6];
    PointF[] g = new PointF[this.f.length];
    Rect h = new Rect();
    static GameAction i = new ReclaimAction(false);

    @Override
    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.h[this.player.getTeamIndex()];
    }

    public UnitRegistry f() {
        return com.corrodinggames.rts.game.units.UnitRegistry.L;
    }


    public PointF[] b() {
        return this.f;
    }


    public PointF[] e_() {
        return this.g;
    }


    public float bN() {
        return 6000.0f;
    }

    public static void t_() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.builder_ship);
        a = l2.bO.a(R$drawable.builder_ship_dead);
        c = l2.bO.a(R$drawable.builder_ship_turret);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
        d = UnitInstance.a(b, b.m(), b.l());
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return e[this.player.getTeamIndex()];
    }


    public Texture d(int n2) {
        return c;
    }


    public Texture k() {
        return d;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && this.eq > -2.0f;
    }


    public float G() {
        return 3.0f;
    }


    public float H() {
        return 3.0f;
    }


    public boolean e() {
        GlobalState l2 = GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public FactoryAction2(boolean bl) {
        super(bl);
        this.b(b);
        this.ck = this.cj = 13.0f;
        this.hp = this.maxHp = 500.0f;
        this.M = b;
        for (int i2 = 0; i2 < this.f.length; ++i2) {
            this.f[i2] = new PointF();
            this.g[i2] = new PointF();
        }
    }


    public float m() {
        return 240.0f;
    }


    public float z() {
        return 0.8f;
    }


    public float A() {
        return 1.9f;
    }


    public float B() {
        return 0.12f;
    }


    public float c(int n2) {
        return 3.5f;
    }


    public float w(int n2) {
        return 0.25f;
    }


    public float C() {
        return 0.03f;
    }


    public float D() {
        return 0.1f;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.isDead) {
            com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.a(f2, this);
        }
    }


    public void a(float f2, boolean bl) {
super.a(f2, bl);  // 02b am.a(f,bl);
        if (!this.isDead) {
            com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.b(f2, this);
        }
    }

    @Override
    public boolean c(float f2) {
        float f3;
        if (!super.c(f2)) {
            return false;
        }
        GlobalState l2 = GlobalState.B();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this);
        if (!this.isDead && (f3 = this.cL[0].maxRotationAngle / this.e(0)) != 0.0f) {
            PointF pointF = this.E(0);
            l2.bO.i();
            l2.bO.b(pointF.a - l2.cw, pointF.b - l2.cx - this.eq);
            l2.bO.a(f3, f3);
            if (this.Y()) {
                l2.bO.a(com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.f, 0.0f, 0.0f, null);
            } else {
                l2.bO.a(com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.e, 0.0f, 0.0f, null);
            }
            l2.bO.j();
        }
        return true;
    }


    public boolean l() {
        return false;
    }


    public float g(int n2) {
        return 11.0f;
    }

    @Override
    public int bl() {
        return 1;
    }


    public PointF G(int n2) {
        float f2 = 8.0f;
        float f3 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(this.cg) * f2;
        float f4 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(this.cg) * f2;
        bh.a(f3, f4);
        return bh;
    }


    public float b(int n2) {
        return 120 - n2 * 28;
    }


    public float e(int n2) {
        return 30.0f;
    }


    public float f(int n2) {
        return 1.3f;
    }


    public boolean a(UnitInstance am2) {
        if (am2.q()) {
            return false;
        }
        return am2.isFactoryBuilding();
    }


    public void a(GameAction s2, boolean bl) {
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(i);
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.a, 1, 1));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.f, 1, 2));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.g, 1, 3));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.b, 1, 4));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.c, 1, 5));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.d, 1, 6));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.J, 1, 7));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.y, 1, 8));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.B, 1, 9));
    }


    public ArrayList N() {
        return this.f().a(this.V());
    }


    public void a(UnitInstance am2, int n2) {
    }


    public int y() {
        return 145;
    }


    public boolean g(UnitInstance am2, boolean bl) {
        return true;
    }


    public float f(UnitTypeHandle as2) {
        int n2 = this.y();
        int n3 = as2.a(this);
        if (n3 == 0 && as2.p()) {
            n3 = 110;
        }
        return n2 += n3;
    }


    public int u(UnitInstance am2) {
        return (int)this.f(am2.r());
    }


    public int v(UnitInstance am2) {
        return (int)this.f(am2.r());
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.f();
    }
}
