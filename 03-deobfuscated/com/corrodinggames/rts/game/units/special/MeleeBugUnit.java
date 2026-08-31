/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.special;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.Projectile;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class MeleeBugUnit
extends AbstractBuildingBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    int e = 0;
    float f = 0.0f;
    Rect g = new Rect();
    Rect h = new Rect();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.t;
    }

    public static void f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        b = l2.bO.a(R$drawable.ladybug);
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
        return null;
    }


    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.effects.SoundEffect.i, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
        if (e2 != null) {
            // empty if block
        }
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.A, 0.8f, this.eo, this.ep);
        com.corrodinggames.rts.game.Projectile.a(this, 1);
        return false;
    }

    public MeleeBugUnit(boolean bl) {
        super(bl);
        this.T(17);
        this.U(26);
        this.cj = 5.0f;
        this.ck = this.cj + 3.0f;
        this.hp = this.maxHp = 130.0f;
        this.M = b;
        this.P = com.corrodinggames.rts.game.units.UnitFlag.a;
    }


    public Rect a_(boolean bl) {
        int n2 = this.e * this.es;
        int n3 = 0;
        this.g.a(n2, n3, n2 + this.es, n3 + this.et);
        return this.g;
    }


    public boolean bP() {
        return true;
    }


    public boolean bO() {
        return true;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.cK) {
            this.e = this.e == 0 ? 1 : 0;
        }
        if (this.f != 0.0f) {
            this.f = GameUtils.a(this.f, f2);
            this.e = 2;
        }
    }


    public void a(UnitInstance am2, int n2) {
        com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, am2, 14.0f, null, false);
        this.f = 4.0f;
        PointF pointF = this.E(n2);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.B, 0.3f, pointF.a, pointF.b);
    }


    public float m() {
        return 43.0f;
    }


    public float b(int n2) {
        return 17.0f;
    }


    public float z() {
        return 1.7f;
    }


    public float A() {
        return 5.5f;
    }


    public float c(int n2) {
        return 99.0f;
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
        return 7.0f;
    }


    public boolean E() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
