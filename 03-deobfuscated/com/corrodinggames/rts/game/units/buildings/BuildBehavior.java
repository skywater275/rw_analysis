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

public strictfp class BuildBehavior
extends AbstractUnitBehavior {
    static com.corrodinggames.rts.gameFramework.rendering.Texture a = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture b = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture c = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture d = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] e = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    float f = 0.0f;
    Rect g = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.n;
    }

    public static void f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        b = l2.bO.a(R$drawable.gunship);
        c = l2.bO.a(R$drawable.gunship_shadow);
        a = l2.bO.a(R$drawable.gunship_dead);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return a;
        }
        return e[this.player.getTeamIndex()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return c;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d(int n2) {
        return null;
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

    public BuildBehavior(boolean bl) {
        super(bl);
        this.T(25);
        this.U(35);
        this.cj = 15.0f;
        this.ck = this.cj + 0.0f;
        this.hp = this.maxHp = 260.0f;
        this.M = b;
        this.N = c;
        this.eq = 0.0f;
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
        this.f += 2.0f * f2;
        if (this.f > 360.0f) {
            this.f -= 360.0f;
        }
        this.eq = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, 20.0f + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.f) * 1.5f, 0.1f * f2);
    }


    public PointF E(int n2) {
        float f2 = this.g(n2);
        float f3 = this.cg;
        float f4 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(f3) * f2;
        float f5 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(f3) * f2;
        bg.a(f4, f5);
        return bg;
    }


    public float q(int n2) {
        return 35.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.ar = Color.a(255, 150, 230, 40);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 80.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.u, 0.3f, this.eo, this.ep);
    }


    public float m() {
        return 140.0f;
    }


    public float b(int n2) {
        return 40.0f;
    }


    public float z() {
        if (this.eq < 15.0f) {
            return 0.0f;
        }
        return 1.4f;
    }


    public float A() {
        return 4.0f;
    }


    public float B() {
        return 0.4f;
    }


    public boolean bi() {
        return true;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean E() {
        return false;
    }


    public float C() {
        return 0.2f;
    }


    public float D() {
        return 0.1f;
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


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
