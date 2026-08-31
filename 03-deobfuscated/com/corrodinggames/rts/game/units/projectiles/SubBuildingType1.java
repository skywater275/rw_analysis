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
import com.corrodinggames.rts.gameFramework.effects.HUDManager;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class SubBuildingType1
extends AbstractSubBuilding {
    float a = 0.0f;
    static com.corrodinggames.rts.gameFramework.rendering.Texture b = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture c = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture d = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] e = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    Rect f = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.x;
    }

    public static void f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        c = l2.bO.loadImageFromResource(R$drawable.heavy_hover_tank);
        b = l2.bO.loadImageFromResource(R$drawable.heavy_hover_tank_dead);
        d = l2.bO.loadImageFromResource(R$drawable.heavy_hover_tank_shadow);
        e = com.corrodinggames.rts.game.PlayerState.a(c);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return b;
        }
        return e[this.player.getTeamIndex()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return d;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d(int n2) {
        return null;
    }


    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.c);
        return true;
    }

    public SubBuildingType1(boolean bl) {
        super(bl);
        this.T(24);
        this.U(36);
        this.cj = 11.0f;
        this.ck = this.cj + 2.0f;
        this.hp = this.maxHp = 450.0f;
        this.M = c;
        this.N = d;
    }

    @Override
    public void a(float f2) {
        super.a(f2);  // 02b e/e.java L70: super.a(var1)
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
        return 40.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.ar = Color.a(255, 230, 0, 50);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 95.0f;
        f2.t = 1.0f;
        f2.r = 7.0f;
        f2.s = 0.2f;
        f2.P = (short)7;
        f2.x = 1.0f;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(pointF.a, pointF.b, this.eq, -56798);
        if (e2 != null) {
            e2.E = 0.7f;
            e2.W = e2.V = 30.0f;
            com.corrodinggames.rts.gameFramework.effects.HUDManager.a(e2, this);
        }
        l2.bR.a(f2, -1179648);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.z, 0.3f, pointF.a, pointF.b);
    }


    public boolean E() {
        return false;
    }


    public float m() {
        return 160.0f;
    }


    public float b(int n2) {
        return 75.0f;
    }


    public float z() {
        return 0.7f;
    }


    public float A() {
        return 20.0f;
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
        return 0.06f;
    }


    public float D() {
        return 0.09f;
    }


    public float c(int n2) {
        return 2.4f;
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
        return 16.0f;
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
