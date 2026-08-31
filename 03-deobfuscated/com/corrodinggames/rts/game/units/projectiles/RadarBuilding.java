/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.gameFramework.effects.HUDManager;
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
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public strictfp class RadarBuilding
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture[] c = new Texture[10];
    static Texture d = null;
    public static Texture e = null;
    int f;
    float g;
    Rect h = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.E;
    }

    public static void f() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Texture e2 = l2.bO.a(R$drawable.mammoth_tank);
        c = PlayerState.a(e2);
        a = l2.bO.a(R$drawable.mammoth_tank_dead);
        b = l2.bO.a(R$drawable.mammoth_tank_turret);
        e = l2.bO.a(R$drawable.lighting_charge);
        d = UnitInstance.a(e2, e2.m() / 2, e2.l());
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return c[this.player.getTeamIndex()];
    }


    public Texture k() {
        return d;
    }


    public Texture d(int n2) {
        return b;
    }


    public boolean F() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && this.eq > -2.0f && !this.isDead;
    }


    public float G() {
        return 3.0f;
    }


    public float H() {
        return 3.0f;
    }


    public boolean e() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.e);
        return true;
    }

    public RadarBuilding(boolean bl) {
        super(bl);
        this.a(c[7], 2);
        this.cj = 21.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 2900.0f;
        this.M = c[7];
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.cK) {
            this.g += f2;
            if (this.g > 3.0f) {
                this.g = 0.0f;
                this.f = 1 - this.f;
            }
        }
    }


    public float bN() {
        return 14000.0f;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        f2.ar = Color.a(255, 247, 212, 129);
        f2.U = 260.0f;
        f2.l = am2;
        f2.h = 20.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        f2.aQ = true;
        f2.A = true;
        f2.M = true;
        f2.ai = 0.5f;
        f2.ak = 1.0f;
        f2.al = 0.0f;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118482);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.x, 0.2f, this.eo, this.ep);
    }


    public float m() {
        return 210.0f;
    }


    public float b(int n2) {
        return 140.0f;
    }


    public float z() {
        return 0.5f;
    }


    public float bc() {
        return 1.0f;
    }


    public float A() {
        return 1.0f;
    }


    public float B() {
        return 0.5f;
    }


    public float w(int n2) {
        return 0.08f;
    }


    public float c(int n2) {
        return 2.5f;
    }


    public float C() {
        return 0.04f;
    }


    public float D() {
        return 0.08f;
    }


    public Rect a_(boolean bl) {
        if (this.isDead && !bl) {
            return super.a_(bl);
        }
        return super.a(bl, this.f);
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this);
        float f3 = this.cL[0].maxRotationAngle / this.e(0);
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this, e, f3, 0);
        return true;
    }


    public boolean l() {
        return true;
    }


    public boolean af() {
        return true;
    }


    public float g(int n2) {
        return 22.0f;
    }


    public float e(int n2) {
        return 60.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
