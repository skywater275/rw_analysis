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

public strictfp class RepairBay
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture[] e = new Texture[10];
    static Texture f = null;
    Rect g = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.r;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.laser_tank_base);
        a = l2.bO.a(R$drawable.laser_tank_dead);
        c = l2.bO.a(R$drawable.laser_tank_turrent);
        d = l2.bO.a(R$drawable.laser_tank_charge);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
        f = com.corrodinggames.rts.game.units.UnitInstance.a(b, b.m(), b.l());
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return e[this.player.getTeamIndex()];  // 02b e/k.java L47: e[this.bX.R()]
    }


    public Texture k() {
        return f;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && !this.isDead;
    }


    public float G() {
        return 2.0f;
    }


    public float H() {
        return 2.0f;
    }


    public Texture d(int n2) {
        return c;
    }


    public boolean e() {
        GlobalState l2 = GlobalState.B();
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.b);
        return true;
    }

    public RepairBay(boolean bl) {
        super(bl);
        this.a(b, 1);
        this.cj = 14.0f;
        this.ck = this.cj + 2.0f;
        this.hp = this.maxHp = 300.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public float q(int n2) {
        return 450.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 8.0f;
        f2.B = true;
        f2.A = true;
        f2.aQ = true;
        f2.ar = Color.a(80, 255, 0, 0);
        GlobalState l2 = GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.y, 0.3f, pointF.a, pointF.b);
    }


    public float bW() {
        if (this.cL[0].shootCooldown > 0.0f) {
            return 1.0f - this.cL[0].shootCooldown / this.b(0);
        }
        if (this.cL[0].maxRotationAngle != 0.0f) {
            return this.cL[0].maxRotationAngle / this.e(0);
        }
        return super.bW();
    }


    public boolean bX() {
        return this.cL[0].shootCooldown > 0.0f;
    }


    public float m() {
        return 190.0f;
    }


    public float b(int n2) {
        return 450.0f;
    }


    public float e(int n2) {
        return 80.0f;
    }


    public float z() {
        return 0.7f;
    }


    public float A() {
        return 1.5f;
    }


    public float B() {
        return 0.1f;
    }


    public float c(int n2) {
        return 3.0f;
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
            l2.bO.b(pointF.a - l2.cw, pointF.b - l2.cx);
            l2.bO.a(f3, f3);
            l2.bO.a(d, 0.0f, 0.0f, null);
            l2.bO.j();
        }
        return true;
    }


    public float C() {
        return 0.07f;
    }


    public float D() {
        return 0.12f;
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return true;
    }


    public float g(int n2) {
        return 19.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
