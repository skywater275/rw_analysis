/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.PointF;
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

public strictfp class ExtractorBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.k;
    }

    public static void f() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.artillery2);
        b = l2.bO.a(R$drawable.artillery1_dead);
        d = PlayerState.a(a);
        c = UnitInstance.a(a);
    }


    public Texture d() {
        if (this.isDead) {
            return b;
        }
        return d[this.player.getTeamIndex()];
    }


    public Texture k() {
        return c;
    }


    public boolean F() {
        return GlobalState.B().bQ.renderExtraShadows && !this.isDead;
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
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.c);
        return true;
    }

    public ExtractorBuilding(boolean bl) {
        super(bl);
        this.T(28);
        this.U(50);
        this.ck = this.cj = 18.0f;
        this.hp = this.maxHp = 140.0f;
        this.M = a;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.h = 150.0f;
        f2.t = 4.0f;
        f2.aQ = true;
        f2.ar = Color.a(255, 190, 190, 80);
        f2.R = (short)2;
        f2.P = 1;
        f2.x = 0.9f;
        PointF pointF3 = am2.a(pointF.a, pointF.b, f2.t, f2.h, this.m());
        f2.aC = true;
        f2.m = true;
        f2.n = pointF3.a;
        f2.o = pointF3.b;
        f2.Y = 80.0f;
        f2.Z = 45.0f;
        f2.aa = true;
        GlobalState l2 = GlobalState.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.r, 0.3f, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(pointF.a, pointF.b, this.eq, -1118482);
        if (e2 != null) {
            e2.W = e2.V = 15.0f;
        }
    }


    public float bW() {
        if (this.cL[0].shootCooldown > 0.0f) {
            return 1.0f - this.cL[0].shootCooldown / this.b(0);
        }
        return super.bW();
    }


    public float m() {
        return 290.0f;
    }


    public float b(int n2) {
        return 240.0f;
    }


    public float z() {
        return 0.9f;
    }


    public float A() {
        return 1.7f;
    }


    public float B() {
        return 0.05f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return false;
    }


    public boolean E() {
        return true;
    }


    public float g(int n2) {
        return 20.0f;
    }


    public float C() {
        return 0.05f;
    }


    public float D() {
        return 0.12f;
    }


    public void e(float f2) {
        super.isRenderable(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public float bN() {
        return 14000.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
