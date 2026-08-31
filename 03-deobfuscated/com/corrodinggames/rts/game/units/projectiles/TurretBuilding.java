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
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class TurretBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture[] c = new Texture[10];
    static Texture d = null;
    int e;
    float f;
    float g;
    Rect h = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.w;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        Texture e2 = l2.bO.a(R$drawable.heavy_tank);
        c = PlayerState.a(e2);
        a = l2.bO.a(R$drawable.heavy_tank_dead);
        b = l2.bO.a(R$drawable.heavy_tank_turret);
        d = UnitInstance.a(e2, e2.m() / 3, e2.l());
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return c[this.player.getTeamIndex()];
    }


    public Texture k() {
        return d;
    }


    public Texture d(int n2) {
        return b;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && !this.isDead && this.cm >= 1.0f && !this.cq;
    }


    public float G() {
        return 2.0f;
    }


    public float H() {
        return 2.0f;
    }


    public boolean e() {
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.c);
        return true;
    }

    public TurretBuilding(boolean bl) {
        super(bl);
        this.a(c[7], 3);
        this.cj = 15.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 600.0f;
        this.M = c[7];
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead) {
            return;
        }
        if (this.cf != 0.0f) {
            this.f += f2;
            if ((double)this.f > 1.4) {
                this.f = 0.0f;
                ++this.e;
                if (this.e > 2) {
                    this.e = 0;
                }
            }
            if (this.el) {
                this.g += f2;
                if (this.g > 9.0f) {
                    this.g = 0.0f;
                    this.K();
                }
            }
        }
    }

    public void K() {
        GlobalState l2 = GlobalState.B();
        float f2 = this.cg;
        if (this.cf < 0.0f) {
            f2 += 180.0f;
        }
        for (int i2 = 0; i2 <= 1; ++i2) {
            float f3 = i2 == 0 ? -20 : 20;
            float f4 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(f2 + 180.0f + f3) * this.cj;
            float f5 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(f2 + 180.0f + f3) * this.cj;
            l2.bR.c(f4, f5, this.eq, f2 + 180.0f, 0);
        }
    }


    public float bN() {
        return 7000.0f;
    }


    public float q(int n2) {
        return 50.0f;
    }


    public void a(UnitInstance am2, int n2) {
        GlobalState l2 = GlobalState.B();
        if (!am2.i()) {
            PointF pointF = this.E(n2);
            MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(235, 150, 230, 40);
            f2.U = this.q(n2);
            f2.l = am2;
            f2.h = 60.0f;
            f2.t = 4.0f;
            f2.x = 2.0f;
            f2.aQ = true;
            f2.z = true;
            l2.bR.a(f2, -16716288);
            l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
            l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.u, 0.3f, this.eo, this.ep);
        } else {
            PointF pointF = this.E(n2);
            pointF.a(this.eo, this.ep);
            com.corrodinggames.rts.game.MovementController f3 = com.corrodinggames.rts.game.MovementController.a(this, this.eo, this.ep);
            f3.ar = Color.a(255, 230, 230, 50);
            f3.U = this.q(n2);
            f3.l = am2;
            f3.h = 190.0f;
            f3.t = 0.5f;
            f3.r = 5.0f;
            f3.aH = true;
            f3.aI = 10.0f;
            f3.aJ = 15.0f;
            f3.aM = true;
            f3.aQ = true;
            f3.aG = true;
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.2f, this.eo, this.ep);
            l2.bR.a(f3, -1118720);
            l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        }
    }


    public float m() {
        return 160.0f;
    }


    public float b(int n2) {
        return 70.0f;
    }


    public float z() {
        return 0.8f;
    }


    public float bc() {
        return 1.0f;
    }


    public float A() {
        return 1.9f;
    }


    public float B() {
        return 0.2f;
    }


    public float w(int n2) {
        return 0.12f;
    }


    public float c(int n2) {
        return 3.0f;
    }


    public float C() {
        return 0.05f;
    }


    public float D() {
        return 0.1f;
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
        return 21.0f;
    }


    public Rect a_(boolean bl) {
        if (bl) {
            return super.a_(bl);
        }
        if (this.isDead) {
            return super.a_(bl);
        }
        return super.a(bl, this.e);
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


    public void e(float f2) {
        super.isRenderable(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
