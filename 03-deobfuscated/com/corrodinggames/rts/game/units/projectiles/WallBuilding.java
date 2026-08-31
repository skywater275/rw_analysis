/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class WallBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    int e;
    float f;
    Rect g = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.F;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        Texture e2 = l2.bO.a(R$drawable.experimental_tank);
        d = PlayerState.a(e2);
        a = l2.bO.a(R$drawable.experimental_tank_dead);
        b = l2.bO.a(R$drawable.experimental_tank_turret);
        c = UnitInstance.a(e2, e2.m() / 2, e2.l());
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return d[this.player.getTeamIndex()];
    }


    public Texture k() {
        return c;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && this.eq > -2.0f && this.cm >= 1.0f;
    }


    public float G() {
        return 4.0f;
    }


    public float H() {
        return 4.0f;
    }


    public Texture d(int n2) {
        if (this.R(n2)) {
            return null;
        }
        return b;
    }


    public boolean e() {
        GlobalState l2 = GlobalState.B();
        this.a(com.corrodinggames.rts.game.units.UnitState.e);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public WallBuilding(boolean bl) {
        super(bl);
        this.a(d[7], 2);
        this.cj = 37.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 6000.0f;
        this.M = d[7];
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.isDead) {
            if (this.cl != 0.0f) {
                this.S(2);
            } else {
                this.S(4);
            }
        }
        if (this.cK) {
            this.f += f2;
            if (this.f > 5.0f) {
                this.f = 0.0f;
                this.e = 1 - this.e;
            }
        }
    }


    public float bN() {
        return 80000.0f;
    }


    public void a(UnitInstance am2, int n2) {
        if (!this.R(n2)) {
            PointF pointF = this.E(n2);
            MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(255, 247, 212, 129);
            f2.h = 120.0f;
            f2.t = 5.0f;
            f2.l = am2;
            f2.Y = 60.0f;
            f2.U = 40.0f;
            f2.Z = 45.0f;
            f2.aa = true;
            f2.x = 2.0f;
            f2.aQ = true;
            f2.P = (short)9;
            f2.x = 1.0f;
            f2.em = this.em;
            GlobalState l2 = GlobalState.B();
            l2.bR.a(pointF.a, pointF.b, this.eq, 16745216);
            l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
            l2.bR.a(f2, -1127220);
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.v, 0.3f, this.eo, this.ep);
        } else {
            PointF pointF = this.E(n2);
            pointF.a(this.eo, this.ep);
            MovementController f3 = com.corrodinggames.rts.game.MovementController.a(this, this.eo, this.ep);
            f3.ar = Color.a(255, 230, 230, 50);
            f3.U = 60.0f;
            f3.l = am2;
            f3.h = 190.0f;
            f3.t = 3.0f;
            f3.r = 6.0f;
            f3.aH = true;
            f3.aI = 10.0f;
            f3.aJ = 15.0f;
            f3.aM = true;
            f3.aQ = true;
            f3.aG = true;
            f3.em = this.em;
            GlobalState l3 = GlobalState.B();
            l3.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.2f, this.eo, this.ep);
            l3.bR.a(f3, -1118720);
            l3.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        }
    }


    public boolean a(int n2, UnitInstance am2, boolean bl, boolean bl2) {
        if (!bl && bl2 && !this.h(am2)) {
            return false;
        }
        return !(this.R(n2) ? !am2.i() : am2.i());
    }


    public float m() {
        return 310.0f;
    }


    public float b(int n2) {
        if (this.R(n2)) {
            n2 -= 4;
        }
        return 110 - n2 * 20;
    }


    public float e(int n2) {
        if (this.R(n2)) {
            n2 -= 4;
        }
        return n2 * 20;
    }


    public float z() {
        return 0.4f;
    }


    public float bc() {
        return 1.0f;
    }


    public int bh() {
        return 1;
    }


    public float A() {
        return 0.8f;
    }


    public float B() {
        return 0.04f;
    }


    public float w(int n2) {
        if (this.R(n2)) {
            return 1.0f;
        }
        return 0.08f;
    }


    public float c(int n2) {
        if (this.R(n2)) {
            return 4.5f;
        }
        return 2.5f;
    }


    public float C() {
        return 0.03f;
    }


    public float D() {
        return 0.08f;
    }


    public Rect a_(boolean bl) {
        if (this.isDead && !bl) {
            return super.a_(bl);
        }
        if (bl) {
            return super.a_(bl);
        }
        int n2 = 0;
        int n3 = 0;
        this.g.a(n2 += this.e * this.es, n3, n2 + this.es, n3 + this.et);
        return this.g;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this);
        return true;
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return true;
    }


    public float g(int n2) {
        return 20.0f;
    }


    public PointF G(int n2) {
        PointF pointF = super.G(n2);
        float f2 = pointF.a;
        float f3 = pointF.b;
        if (!this.R(n2)) {
            if (n2 <= 1) {
                f2 += com.corrodinggames.rts.gameFramework.GameUtils.k(this.cg) * 5.0f;
                f3 += com.corrodinggames.rts.gameFramework.GameUtils.j(this.cg) * 5.0f;
            }
            float f4 = -45 + 90 * n2;
            f2 += com.corrodinggames.rts.gameFramework.GameUtils.k(this.cg + f4) * 18.0f;
            f3 += com.corrodinggames.rts.gameFramework.GameUtils.j(this.cg + f4) * 18.0f;
        }
        bh.a(f2, f3);
        return bh;
    }

    public boolean R(int n2) {
        return n2 >= 4;
    }

    @Override
    public int bl() {
        return 6;
    }


    public void e(float f2) {
        super.isRenderable(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public int cw() {
        return 5;
    }


    public boolean dd() {
        return true;
    }


    public float H(int n2) {
        return -2.0f;
    }


    public float I(int n2) {
        return 4.0f;
    }

    @Override
    public float J(int n2) {
        return 12.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
