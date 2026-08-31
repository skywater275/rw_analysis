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
import com.corrodinggames.rts.gameFramework.effects.HUDManager;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class FactoryAction1
extends FactoryAction6 {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture[] e = new Texture[10];
    Rect f = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.u;
    }


    public float bN() {
        return 9000.0f;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.battle_ship_t2);
        a = l2.bO.a(R$drawable.battle_ship_t2_dead);
        c = l2.bO.a(R$drawable.battle_ship_t2_turret);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
        d = UnitInstance.a(b, b.m(), b.l());
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return e[this.player.R()];
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

    public FactoryAction1(boolean bl) {
        super(bl);
        this.b(b);
        this.ck = this.cj = 20.0f;
        this.hp = this.maxHp = 1200.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public float q(int n2) {
        return 65.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 80.0f;
        f2.x = 2.0f;
        f2.t = 4.0f;
        f2.S = true;
        f2.ar = Color.a(255, 180, 180, 0);
        f2.aQ = true;
        GlobalState l2 = GlobalState.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.r, 0.2f, pointF.a, pointF.b);
        l2.bR.a(f2, -1118720);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        if (e2 != null) {
            com.corrodinggames.rts.gameFramework.effects.HUDManager.a(e2, this);
        }
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118720);
    }


    public float m() {
        return 240.0f;
    }


    public float z() {
        return 0.8f;
    }


    public float bc() {
        return 1.0f;
    }


    public float C(int n2) {
        if (this.ci && (double)this.bc() > 0.95) {
            if (n2 == 0) {
                return this.cg + 140.0f;
            }
            return this.cg - 140.0f;
        }
        return this.cg;
    }


    public float A() {
        return 1.8f;
    }


    public float B() {
        return 0.08f;
    }


    public float c(int n2) {
        return 2.5f;
    }


    public float w(int n2) {
        return 0.08f;
    }


    public float C() {
        return 0.03f;
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
        return false;
    }


    public float g(int n2) {
        return 15.0f;
    }

    @Override
    public int bl() {
        return 2;
    }


    public PointF G(int n2) {
        PointF pointF = super.G(n2);
        float f2 = pointF.a;
        float f3 = pointF.b;
        float f4 = n2 == 0 ? 22.0f : 4.0f;
        bh.a(f2 += com.corrodinggames.rts.gameFramework.GameUtils.k(this.cg) * f4, f3 += com.corrodinggames.rts.gameFramework.GameUtils.j(this.cg) * f4);
        return bh;
    }


    public float b(int n2) {
        return 120 - n2 * 28;
    }


    public float e(int n2) {
        return n2 * 30;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
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
