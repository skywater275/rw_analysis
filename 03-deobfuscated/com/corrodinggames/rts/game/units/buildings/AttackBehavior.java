/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.buildings.AbstractUnitBehavior;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class AttackBehavior
extends AbstractUnitBehavior {
    static com.corrodinggames.rts.gameFramework.rendering.Texture targetUnit = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture weaponIndex = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture burstCounter = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture d = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] e = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] f = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] g = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture o = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture p = null;
    float q;
    boolean r = true;
    boolean s = true;
    float t = 0.0f;
    float u = 0.0f;
    protected Paint v = new UniquePaint();
    PointF w = new PointF();
    Rect x = new Rect();
    public static final GameAction y = new AttackBehavior$1(151);
    public static final GameAction z = new AttackBehavior$2(152);
    static ArrayList A = new ArrayList();

    @Override
    /* 覆写链 super.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.r);
        as2.a(this.t);
        as2.a(this.u);
        super.a(as2);
    }

    @Override
    public void a(InputNetStream k2) {
        this.r = k2.e();
        boolean bl = this.s = !this.Q();
        if (k2.b() >= 21) {
            this.t = k2.readFloat();
        }
        if (k2.b() >= 22) {
            this.u = k2.readFloat();
        }
        this.M();
        super.a(k2);
    }

    @Override
    public boolean Q() {
        return this.eq < -1.0f;
    }

    public boolean b() {
        return !this.r || this.eq < 0.0f;
    }

    @Override
    public MovementTypeEnum h() {
        if (this.cp) {
            return com.corrodinggames.rts.game.units.MovementTypeEnum.d;
        }
        if (this.b()) {
            return com.corrodinggames.rts.game.units.MovementTypeEnum.e;
        }
        return com.corrodinggames.rts.game.units.MovementTypeEnum.d;
    }

    public UnitRegistry f() {
        return com.corrodinggames.rts.game.units.UnitRegistry.M;
    }

    public static void L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        weaponIndex = l2.bO.loadImageFromResource(R$drawable.amphibious_jet);
        burstCounter = l2.bO.loadImageFromResource(R$drawable.amphibious_jet_shadow);
        targetUnit = l2.bO.loadImageFromResource(R$drawable.amphibious_jet_dead);
        e = com.corrodinggames.rts.game.PlayerState.a(weaponIndex);
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.loadImageFromResource(R$drawable.amphibious_jet_p1);
        com.corrodinggames.rts.gameFramework.rendering.Texture e3 = l2.bO.loadImageFromResource(R$drawable.amphibious_jet_p2);
        f = com.corrodinggames.rts.game.PlayerState.a(e2);
        g = com.corrodinggames.rts.game.PlayerState.a(e3);
        o = com.corrodinggames.rts.game.units.UnitInstance.isVisibleTo(e2);
        p = com.corrodinggames.rts.game.units.UnitInstance.isVisibleTo(e3);
    }


    public boolean aQ() {
        if (super.aQ()) {
            this.f(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        if (this.isDead) {
            return true;
        }
        this.f(false);
        if (!this.isDead) {
            for (int i2 = 0; i2 < this.bl(); ++i2) {
                float f3;
                if (i2 == this.ds() || (f3 = this.cL[i2].maxRotationAngle / this.e(i2)) == 0.0f) continue;
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                PointF pointF = this.E(i2);
                l2.bO.i();
                l2.bO.b(pointF.a - l2.cw, pointF.b - l2.cx - this.eq);
                l2.bO.a(f3 * 0.7f, f3 * 0.7f);
                l2.bO.a(com.corrodinggames.rts.game.units.projectiles.RadarBuilding.e, 0.0f, 0.0f, null);
                l2.bO.j();
            }
        }
        return true;
    }

    public void f(boolean bl) {
        Paint paint;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!bl) {
            paint = this.aN();
        } else {
            this.v.a(50, 255, 255, 255);
            paint = this.v;
        }
        for (int i2 = 0; i2 <= 1; ++i2) {
            com.corrodinggames.rts.gameFramework.rendering.Texture e2;
            PointF pointF = this.a(i2, bl);
            float f2 = pointF.a - l2.cw;
            float f3 = pointF.b - l2.cx;
            float f4 = this.d(false) - 90.0f;
            if (!bl) {
                f3 -= this.eq;
            }
            if (i2 == 0) {
                e2 = bl ? p : g[this.player.getTeamIndex()];
                f4 += 0.0f;
            } else {
                e2 = bl ? o : f[this.player.getTeamIndex()];
                f4 -= 0.0f;
            }
            l2.bO.a(e2, f2, f3, f4, paint);
        }
    }

    @Override
    public int bl() {
        return 3;
    }


    public PointF G(int n2) {
        if (n2 == this.ds()) {
            return super.G(n2);
        }
        float f2 = this.d(false) - 90.0f;
        PointF pointF = this.a(n2, false);
        float f3 = pointF.a;
        float f4 = pointF.b;
        bh.a(f3 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(f2) * 5.0f, f4 += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(f2) * 5.0f);
        return bh;
    }

    public PointF a(int n2, boolean bl) {
        float f2 = this.d(false) - 90.0f;
        if (n2 == this.ds()) {
            throw new RuntimeException("index==2 is for base");
        }
        float f3 = this.eo;
        float f4 = this.ep;
        float f5 = this.u * 4.0f;
        f5 = com.corrodinggames.rts.gameFramework.GameUtils.b(f5, 0.0f, 1.0f);
        float f6 = this.u * 2.0f - 1.0f;
        f6 = com.corrodinggames.rts.gameFramework.GameUtils.b(f6, 0.0f, 1.0f);
        f3 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(f2) * (7.0f - 5.0f * f5);
        f4 += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(f2) * (7.0f - 5.0f * f5);
        float f7 = -90 + 180 * n2;
        this.w.a(f3 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(f2 + f7) * (12.0f - 5.0f * f6), f4 += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(f2 + f7) * (12.0f - 5.0f * f6));
        return this.w;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return targetUnit;
        }
        return e[this.player.getTeamIndex()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return burstCounter;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d(int n2) {
        return d;
    }

    @Override
    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = targetUnit;
        this.S(0);
        this.bT = false;
        return true;
    }

    public AttackBehavior(boolean bl) {
        super(bl);
        this.b(weaponIndex);
        this.cj = 12.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 530.0f;
        this.M = weaponIndex;
        this.N = burstCounter;
        this.eq = 0.0f;
        this.S(5);
    }

    @Override
    public boolean i() {
        return !this.b();
    }

    public void M() {
        if (!this.s) {
            this.S(1);
        } else {
            this.S(5);
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.q += 2.0f * f2;
        if (this.q > 360.0f) {
            this.q -= 360.0f;
        }
        float f3 = this.r ? 20.0f + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.q) * 1.5f : -8.0f;
        this.u = this.r && !this.Q() ? GameUtils.a(this.u, 0.0f, 0.018f * f2) : GameUtils.a(this.u, 1.0f, 0.018f * f2);
        if (com.corrodinggames.rts.gameFramework.GameUtils.c(this.eq - f3) > 3.0f) {
            float f4 = 0.6f;
            if (this.Q()) {
                f4 /= 6.0f;
            }
            this.t = com.corrodinggames.rts.gameFramework.GameUtils.b(this.t, f4);
            this.t = GameUtils.a(this.t, f4, 0.006f * f2);
        } else {
            this.t = GameUtils.a(this.t, 0.07f, 0.006f * f2);
        }
        this.eq = GameUtils.a(this.eq, f3, this.t * f2);
        boolean bl = false;
        if (this.s && this.Q()) {
            if (!this.cJ()) {
                this.r = true;
            } else {
                this.s = false;
                this.M();
                bl = true;
            }
        }
        if (!this.s && !this.Q()) {
            this.s = true;
            this.M();
            bl = true;
        }
        if (bl) {
            l2.bR.a(this.eo, this.ep, 0.0f, 0, 0.0f, 0.0f);
            for (int i2 = -180; i2 < 180; i2 += 45) {
                float f5;
                float f6 = this.cg + (float)i2;
                float f7 = (float)((double)this.eo + Math.cos(Math.toRadians(f6)) * -5.0);
                com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.b(f7, f5 = (float)((double)this.ep + Math.sin(Math.toRadians(f6)) * -5.0), 0.0f, f6);
                if (e2 == null) continue;
                e2.ar = 2;
                e2.s = true;
                e2.t = 7.0f;
            }
        }
    }


    public float q(int n2) {
        if (n2 == this.ds()) {
            return 0.0f;
        }
        return 45.0f;
    }


    public void a(UnitInstance am2, int n2) {
        if (n2 == this.ds()) {
            return;
        }
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        f2.ar = Color.a(255, 247, 212, 129);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 10.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        f2.aQ = false;
        f2.A = true;
        f2.M = true;
        f2.ai = 0.5f;
        f2.ak = 1.0f;
        f2.al = 0.1f;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118482);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.x, 0.2f, this.eo, this.ep);
    }


    public float m() {
        if (this.b()) {
            return 100.0f;
        }
        return 170.0f;
    }


    public float b(int n2) {
        return 110.0f;
    }


    public float e(int n2) {
        return 25 + n2 * 10;
    }


    public float f(int n2) {
        return 0.2f;
    }


    public float z() {
        if (!this.Q()) {
            return 1.4f;
        }
        return 0.4f;
    }


    public float A() {
        if (!this.Q()) {
            return 3.8f;
        }
        return 1.5f;
    }


    public float B() {
        return 0.3f;
    }


    public float c(int n2) {
        return 4.0f;
    }


    public float w(int n2) {
        return 0.35f;
    }


    public float y(int n2) {
        return 0.38f;
    }


    public boolean E() {
        return false;
    }


    public float C() {
        return 0.03f;
    }


    public float D() {
        return 0.1f;
    }


    public boolean l() {
        return true;
    }


    public boolean bi() {
        return true;
    }


    public boolean bj() {
        return true;
    }


    public void i(float f2) {
        if (!this.Z()) {
            super.i(f2);
            return;
        }
        this.cg += f2;
        if (this.cg > 180.0f) {
            this.cg -= 360.0f;
        }
        if (this.cg < -180.0f) {
            this.cg += 360.0f;
        }
    }

    public int ds() {
        return 2;
    }


    public float d(boolean bl) {
        return this.cL[this.ds()].turretAngle + 90.0f;
    }


    public boolean ah() {
        return !this.Q();
    }


    public boolean ae() {
        return this.Q();
    }


    public boolean af() {
        return !this.Q();
    }


    public boolean ag() {
        if (!this.Q()) {
            return true;
        }
        return true;
    }


    public void D(GameAction s2, boolean bl) {
        if (s2 == y) {
            this.r = true;
        }
        if (s2 == z) {
            this.r = false;
        }
    }


    public ArrayList N() {
        return A;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.f();
    }

    static {
        A.add(y);
        A.add(z);
    }
}
