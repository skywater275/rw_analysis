/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.debug;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.WaterUnit;
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
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class FactoryAction3
extends FactoryAction6 {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    Rect e = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.p;
    }


    public float bN() {
        return 1500.0f;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.gun_boat);
        a = l2.bO.a(R$drawable.gun_boat_dead);
        c = UnitInstance.a(b, b.m(), b.l());
        d = com.corrodinggames.rts.game.PlayerState.a(b);
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return d[this.player.R()];
    }


    public Texture k() {
        return c;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && this.eq > -2.0f;
    }


    public float G() {
        return 1.0f;
    }


    public float H() {
        return 1.0f;
    }


    public Texture d(int n2) {
        return null;
    }


    public boolean e() {
        GlobalState l2 = GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public FactoryAction3(boolean bl) {
        super(bl);
        this.T(15);
        this.U(27);
        this.cj = 12.0f;
        this.ck = this.cj - 2.0f;
        this.hp = this.maxHp = 170.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public float q(int n2) {
        return 12.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = MovementController.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.eq = this.eq;
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 30.0f;
        f2.t = 8.0f;
        f2.S = false;
        f2.ar = Color.a(255, 180, 180, 0);
        GlobalState l2 = GlobalState.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.s, 0.2f, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118720);
    }


    public float m() {
        return 120.0f;
    }


    public float b(int n2) {
        return 60.0f;
    }


    public float z() {
        return 1.5f;
    }


    public float A() {
        return 2.8f;
    }


    public float B() {
        return 0.35f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public float C() {
        return 0.06f;
    }


    public float D() {
        return 0.2f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return false;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
