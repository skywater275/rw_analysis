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
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class ShieldBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    Rect e = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.q;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.mega_tank);
        a = l2.bO.a(R$drawable.mega_tank_dead);
        c = l2.bO.a(R$drawable.mega_tank_turret);
        d = com.corrodinggames.rts.game.PlayerState.a(b);
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

    public ShieldBuilding(boolean bl) {
        super(bl);
        this.T(20);
        this.U(25);
        this.cj = 12.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 550.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public float bN() {
        return 7000.0f;
    }


    public void a(UnitInstance am2, int n2) {
        if (!am2.i()) {
            PointF pointF = this.E(n2);
            MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
            f2.ar = Color.a(255, 150, 230, 40);
            f2.U = 50.0f;
            f2.l = am2;
            f2.h = 60.0f;
            f2.t = 3.0f;
            f2.x = 2.0f;
            f2.aQ = true;
            GlobalState l2 = GlobalState.B();
            l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
            l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.u, 0.3f, this.eo, this.ep);
        } else {
            MovementController f3 = com.corrodinggames.rts.game.MovementController.a(this, this.eo, this.ep);
            f3.ar = Color.a(255, 230, 230, 50);
            f3.U = 40.0f;
            f3.l = am2;
            f3.h = 190.0f;
            f3.t = 4.0f;
            f3.aH = true;
            f3.aI = 10.0f;
            f3.aJ = 15.0f;
            f3.aM = true;
            f3.aQ = true;
            GlobalState l3 = GlobalState.B();
            l3.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.2f, this.eo, this.ep);
        }
    }


    public float m() {
        return 140.0f;
    }


    public float b(int n2) {
        return 70.0f;
    }


    public float z() {
        return 0.8f;
    }


    public float A() {
        return 1.2f;
    }


    public float c(int n2) {
        return 2.0f;
    }


    public float C() {
        return 0.05f;
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
        return true;
    }


    public float g(int n2) {
        return 12.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
