/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.buildings.AbstractUnitBehavior;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.io.IOException;

public strictfp class RepairBehavior
extends AbstractUnitBehavior {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture d = null;
    static Texture e = null;
    static Texture[] f = new Texture[10];
    boolean g = false;
    float o;
    float p = 0.0f;
    float q;
    Rect r = new Rect();
    Rect s = new Rect();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.p);
        as2.a(this.o);
        super.a(as2);
    }

    @Override
    public void a(InputNetStream k2) {
        if (k2.b() >= 9) {
            this.p = k2.readFloat();
            this.o = k2.readFloat();
            if (k2.b() == 8) {
                this.g = k2.e();
            }
        } else {
            this.o = 0.5f;
        }
        super.a(k2);
    }

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.l;
    }

    public static void f() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        b = l2.bO.loadImageFromResource(R$drawable.helicopter);
        c = l2.bO.loadImageFromResource(R$drawable.helicopter_blades);
        d = l2.bO.loadImageFromResource(R$drawable.helicopter_shadow);
        e = l2.bO.loadImageFromResource(R$drawable.helicopter_shadow_blades);
        a = l2.bO.loadImageFromResource(R$drawable.helicopter_dead);
        f = com.corrodinggames.rts.game.PlayerState.a(b);
    }


    public Texture d() {
        if (this.isDead) {
            return a;
        }
        return f[this.player.getTeamIndex()];
    }


    public Texture k() {
        return d;
    }


    public Texture d(int n2) {
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

    public RepairBehavior(boolean bl) {
        super(bl);
        this.T(26);
        this.U(46);
        this.cj = 13.0f;
        this.ck = this.cj + 2.0f;
        this.hp = this.maxHp = 150.0f;
        this.M = b;
        this.N = d;
        this.eq = 0.0f;
        this.o = 0.14f;
        this.q = 0.0f;
        this.S(5);
    }


    public void n() {
        super.n();
        this.eq = 20.0f;
        this.o = 0.5f;
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
        this.o = GameUtils.a(this.o, 0.5f, 0.003f * f2);
        this.q += 70.0f * this.o * f2;
        if (this.q >= 360.0f) {
            this.q -= 360.0f;
            this.q += (float)com.corrodinggames.rts.gameFramework.GameUtils.a(this, 0, 4);
        }
        if (this.o > 0.4f) {
            this.p += 2.0f * f2;
            if (this.p > 360.0f) {
                this.p -= 360.0f;
            }
            this.eq = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, 20.0f + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.p) * 1.5f, 0.1f * f2);
        }
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        com.corrodinggames.rts.game.MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = 17.0f;
        f2.l = am2;
        f2.h = 30.0f;
        f2.t = 8.0f;
        f2.S = false;
        f2.ar = Color.a(255, 180, 180, 0);
        f2.A = true;
        f2.aR = false;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.GameUtils.c(-0.08f, 0.08f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.s, 0.2f, f3, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118720);
    }


    public float m() {
        return 130.0f;
    }


    public float b(int n2) {
        return 60.0f;
    }


    public float z() {
        if (this.eq < 15.0f) {
            return 0.0f;
        }
        return 2.2f;
    }


    public float bc() {
        return 0.1f;
    }


    public float A() {
        return 6.0f;
    }


    public float B() {
        return 0.4f;
    }


    public boolean bi() {
        return true;
    }


    public boolean bj() {
        return true;
    }


    public float c(int n2) {
        return 16.0f;
    }


    public Rect a_(boolean bl) {
        return super.a_(bl);
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        if (!this.isDead) {
            Paint paint = this.aN();
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            this.s.a(0, 0, c.m(), c.l());
            float f3 = this.q;
            if (this.co) {
                // empty if block
            }
            l2.bO.A(c, this.s, this.eo - com.corrodinggames.rts.gameFramework.GlobalState.B().cw, this.ep - com.corrodinggames.rts.gameFramework.GlobalState.B().cx - this.eq, f3, paint);
        }
        return true;
    }


    public float C() {
        return 0.07f;
    }


    public float D() {
        return 0.1f;
    }


    public boolean l() {
        return true;
    }


    public float g(int n2) {
        return 7.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
