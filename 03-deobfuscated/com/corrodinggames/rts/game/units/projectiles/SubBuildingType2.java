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
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class SubBuildingType2
extends AbstractSubBuilding {
    float a = 0.0f;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture[] e = new Texture[10];
    Rect f = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.j;
    }

    public static void f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        c = l2.bO.loadImageFromResource(R$drawable.hover_tank);
        b = l2.bO.loadImageFromResource(R$drawable.hover_tank_dead);
        d = l2.bO.loadImageFromResource(R$drawable.hover_tank_shadow);
        e = com.corrodinggames.rts.game.PlayerState.a(c);
    }


    public Texture d() {
        if (this.isDead) {
            return b;
        }
        return e[this.player.getTeamIndex()];
    }


    public Texture k() {
        return d;
    }


    public Texture d(int n2) {
        return null;
    }


    public boolean e() {
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.b);
        return true;
    }

    public SubBuildingType2(boolean bl) {
        super(bl);
        this.b(c);
        this.cj = 7.0f;
        this.ck = this.cj + 2.0f;
        this.hp = this.maxHp = 150.0f;
        this.M = c;
        this.N = d;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead || !this.bT()) {
            return;
        }
        this.a += 3.0f * f2;
        if (this.a > 360.0f) {
            this.a -= 360.0f;
        }
        this.eq = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, 4.0f + com.corrodinggames.rts.gameFramework.GameUtils.j(this.a) * 1.5f, 0.1f * f2);
    }


    public float q(int n2) {
        return 23.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.ar = Color.a(255, 50, 230, 50);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 85.0f;
        f2.t = 2.0f;
        f2.r = 6.0f;
        f2.s = 0.2f;
        f2.P = (short)6;
        f2.x = 1.0f;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -14483678);
        l2.bR.a(f2, -16716288);
        float f3 = 1.3f + com.corrodinggames.rts.gameFramework.GameUtils.c(-0.07f, 0.07f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.z, 0.3f, f3, pointF.a, pointF.b);
    }


    public boolean E() {
        return false;
    }


    public float m() {
        return 140.0f;
    }


    public float b(int n2) {
        return 90.0f;
    }


    public float z() {
        return 1.0f;
    }


    public float A() {
        return 180.0f;
    }


    public void i(float f2) {
        this.cg += f2;
        if (this.cg > 180.0f) {
            this.cg -= 360.0f;
        }
        if (this.cg < -180.0f) {
            this.cg += 360.0f;
        }
    }


    public float C() {
        return 0.04f;
    }


    public float D() {
        return 0.09f;
    }


    public boolean bi() {
        return true;
    }


    public boolean bj() {
        return true;
    }


    public float c(int n2) {
        return 4.0f;
    }


    public float w(int n2) {
        return 0.2f;
    }


    public float d(boolean bl) {
        return this.cL[0].turretAngle + 90.0f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return true;
    }


    public float g(int n2) {
        return 2.0f;
    }


    public float B() {
        return 0.5f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
