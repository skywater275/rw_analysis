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
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class FactoryAction4
extends FactoryAction6 {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    static PointF e = new PointF();
    Rect f = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.o;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.scout_ship);
        a = l2.bO.a(R$drawable.scout_ship_dead);
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
        return 3.0f;
    }


    public float H() {
        return 3.0f;
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

    public FactoryAction4(boolean bl) {
        super(bl);
        this.T(17);
        this.U(31);
        this.cj = 15.0f;
        this.ck = this.cj - 2.0f;
        this.hp = this.maxHp = 350.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public PointF E(int n2) {
        float f2 = 6.0f;
        float f3 = this.cg;
        float f4 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(f3) * f2;
        float f5 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(f3) * f2;
        e.a(f4, f5);
        return e;
    }


    public float q(int n2) {
        return 62.0f;
    }


    public void a(UnitInstance am2, int n2) {
        GlobalState l2 = GlobalState.B();
        PointF pointF = this.E(n2);
        if (!am2.Q()) {
            MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(255, 230, 230, 50);
            f2.U = 62.0f;
            f2.l = am2;
            f2.h = 190.0f;
            f2.t = 2.0f;
            f2.aH = true;
            f2.aM = true;
            f2.aQ = true;
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq, -1118720);
            l2.bR.a(f2, -1118720);
        } else {
            MovementController f3 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq - 1.0f, n2);
            f3.ar = Color.a(255, 0, 0, 150);
            f3.x = 1.0f;
            f3.U = 42.0f;
            f3.l = am2;
            f3.h = 220.0f;
            f3.t = 1.9f;
            f3.aM = true;
            f3.aQ = true;
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq, -1118720);
        }
    }


    public float m() {
        return 200.0f;
    }


    public float b(int n2) {
        return 170.0f;
    }


    public float z() {
        return 1.2f;
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


    public float c(int n2) {
        return 99.0f;
    }


    public float C() {
        return 0.05f;
    }


    public float D() {
        return 0.1f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }


    public boolean l() {
        return true;
    }


    public boolean ae() {
        return true;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
