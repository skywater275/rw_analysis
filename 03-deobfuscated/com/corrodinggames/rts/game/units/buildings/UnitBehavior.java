/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.buildings.AbstractUnitBehavior;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class UnitBehavior
extends AbstractUnitBehavior {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture[] e = new Texture[10];
    float f;
    Rect g = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.m;
    }

    public static void f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        b = l2.bO.loadImageFromResource(R$drawable.ship);
        c = l2.bO.loadImageFromResource(R$drawable.ship_shadow);
        a = l2.bO.loadImageFromResource(R$drawable.ship_dead);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return e[this.player.getTeamIndex()];
    }


    public Texture k() {
        return c;
    }


    public Texture d(int n2) {
        return d;
    }

    @Override
    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public UnitBehavior(boolean bl) {
        super(bl);
        this.T(24);
        this.U(22);
        this.cj = 11.0f;
        this.ck = this.cj + 0.0f;
        this.hp = this.maxHp = 250.0f;
        this.M = b;
        this.N = c;
        this.eq = 0.0f;
        this.f = 0.18f;
        this.S(5);
    }

    @Override
    public boolean I() {
        return true;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead) {
            return;
        }
        this.eq = GameUtils.a(this.eq, 20.0f, 0.3f * f2);
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = 30.0f;
        f2.l = am2;
        f2.h = 75.0f;
        f2.t = 6.0f;
        f2.x = 2.0f;
        f2.y = 4.0f;
        f2.ar = Color.a(250, 74, 232, 255);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        if (e2 != null) {
            e2.aq = 10;
        }
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.GameUtils.c(-0.1f, 0.1f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.y, 0.14f, f3, pointF.a, pointF.b);
    }


    public float m() {
        return 170.0f;
    }


    public float b(int n2) {
        return 40.0f;
    }


    public float z() {
        if (this.eq < 15.0f) {
            return 0.0f;
        }
        return 2.4f;
    }


    public float A() {
        return 3.7f;
    }


    public float B() {
        return 0.4f;
    }


    public float c(int n2) {
        return 3.7f;
    }


    public boolean bm() {
        return false;
    }


    public float w(int n2) {
        return 0.4f;
    }


    public boolean E() {
        return false;
    }


    public float g(int n2) {
        return 10.0f;
    }


    public float C() {
        return 0.1f;
    }


    public float D() {
        return 0.16f;
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return true;
    }


    public boolean ag() {
        return false;
    }


    public boolean bi() {
        return true;
    }


    public boolean bj() {
        return true;
    }


    public float d(boolean bl) {
        return this.cL[0].turretAngle + 90.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
