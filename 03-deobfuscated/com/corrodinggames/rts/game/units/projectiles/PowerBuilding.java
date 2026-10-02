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
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class PowerBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    Rect e = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.v;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.tank2);
        a = l2.bO.a(R$drawable.tank2_dead);
        c = l2.bO.a(R$drawable.tank2_turret);
        d = PlayerState.a(b);
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return d[this.player.getTeamIndex()];
    }


    public Texture k() {
        return null;
    }


    public Texture d(int n2) {
        return c;
    }


    public boolean e() {
        GlobalState l2 = GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.o, 0.8f, this.eo, this.ep);
        this.bq();
        return true;
    }

    public PowerBuilding(boolean bl) {
        super(bl);
        this.T(16);
        this.U(30);
        this.cj = 11.0f;
        this.ck = this.cj + 2.0f;
        this.hp = this.maxHp = 350.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        f2.U = 35.0f;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 3.0f;
        GlobalState l2 = GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.q, 0.3f, pointF.a, pointF.b);
    }


    public float m() {
        return 150.0f;
    }


    public float b(int n2) {
        return 70.0f;
    }


    public float z() {
        return 1.0f;
    }


    public float A() {
        return 1.9f;
    }


    public float c(int n2) {
        return 3.0f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
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
        return false;
    }


    public float g(int n2) {
        return 10.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
