/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class StorageBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture[] e = new Texture[10];
    int f;
    float g;
    float h;
    Rect i = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.i;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.tank2);
        a = l2.bO.a(R$drawable.tank2_dead);
        c = l2.bO.a(R$drawable.tank2_turret);
        d = l2.bO.a(R$drawable.tank2_shadow);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return e[this.player.getTeamIndex()];
    }


    public Texture k() {
        return d;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && !this.isDead;
    }


    public float G() {
        return 3.0f;
    }


    public float H() {
        return 3.0f;
    }


    public Texture d(int n2) {
        return c;
    }


    public boolean e() {
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.b);
        return true;
    }

    public StorageBuilding(boolean bl) {
        super(bl);
        this.a(b, 3);
        this.cj = 11.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 210.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead) {
            return;
        }
        if (this.cf != 0.0f) {
            this.g += f2;
            if (this.g > 1.0f) {
                this.g = 0.0f;
                ++this.f;
                if (this.f > 2) {
                    this.f = 0;
                }
            }
            if (this.cf > 0.0f && this.el) {
                this.h += f2;
                if (this.h > 9.0f) {
                    this.h = 0.0f;
                    this.K();
                }
            }
        }
    }

    public void K() {
        GlobalState l2 = GlobalState.B();
        for (int i2 = 0; i2 <= 1; ++i2) {
            float f2 = i2 == 0 ? -20 : 20;
            float f3 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(this.cg + 180.0f + f2) * this.cj;
            float f4 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(this.cg + 180.0f + f2) * this.cj;
            l2.bR.c(f3, f4, this.eq, this.cg + 180.0f, 0);
        }
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = 30.0f;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 3.0f;
        f2.P = 1;
        f2.x = 1.0f;
        GlobalState l2 = GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.GameUtils.c(-0.07f, 0.07f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.q, 0.3f, f3, pointF.a, pointF.b);
    }


    public float m() {
        return 130.0f;
    }


    public float b(int n2) {
        return 75.0f;
    }


    public float z() {
        return 1.0f;
    }


    public float A() {
        return 4.1f;
    }


    public float c(int n2) {
        return 4.0f;
    }


    public float B() {
        return 0.25f;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this);
        if (!this.isDead) {
            // empty if block
        }
        return true;
    }


    public float C() {
        return 0.07f;
    }


    public float D() {
        return 0.17f;
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return false;
    }


    public float g(int n2) {
        return 20.0f;
    }


    public Rect a_(boolean bl) {
        if (bl) {
            return super.a_(bl);
        }
        if (this.isDead) {
            return super.a_(bl);
        }
        return super.a(bl, this.f);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
