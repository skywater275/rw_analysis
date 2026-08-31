/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.WaterUnit;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Paint;
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
import com.corrodinggames.rts.game.units.projectiles.RadarBuilding;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.io.IOException;

public strictfp class Building
extends AbstractSubBuilding {
    static Texture buildPosition = null;  // 02b e/c.a
    static Texture buildRotation = null;  // 02b e/c.b
    static Texture isConstructed = null;  // 02b e/c.c
    public static Texture buildResourceType = null;  // 02b e/c.d
    public static Texture repairResourceType = null;  // 02b e/c.e
    static Texture[] f = new Texture[10];
    int g;
    float h = 0.0f;
    com.corrodinggames.rts.game.MovementController i;
    Rect j = new Rect();
    Paint k = com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a();

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.O;
    }

    public static void f() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.loadImageFromResource(R$drawable.experimental_hovertank);
        f = com.corrodinggames.rts.game.PlayerState.a(e2);
        buildPosition = l2.bO.loadImageFromResource(R$drawable.experimental_hovertank_dead);
        buildRotation = l2.bO.loadImageFromResource(R$drawable.experimental_hovertank_turret);
        isConstructed = com.corrodinggames.rts.game.units.UnitInstance.a(e2, e2.m() / 1, e2.l());  // 02b e/c.java L41: am.a(m.e,int,int)
        buildResourceType = l2.bO.loadImageFromResource(R$drawable.experimental_hovertank_shield);
        repairResourceType = l2.bO.loadImageFromResource(R$drawable.shield_mid);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        if (this.i != null && this.i.ej) {
            this.i = null;
        }
        as2.a(this.i);
        super.a(as2);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        this.i = (com.corrodinggames.rts.game.MovementController) k2.a(com.corrodinggames.rts.game.MovementController.class);
        super.a(k2);
    }


    public Texture d() {
        if (this.isDead) {
            return buildPosition;
        }
        return f[this.player.getTeamIndex()];
    }


    public Texture k() {
        return isConstructed;
    }


    public boolean F() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && this.eq > -2.0f;
    }


    public float G() {
        return 4.0f;
    }


    public float H() {
        return 4.0f;
    }


    public Texture d(int n2) {
        return buildRotation;
    }


    public Texture T() {
        return buildResourceType;
    }


    public boolean e() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.M = buildPosition;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.e);
        return true;
    }

    public Building(boolean bl) {
        super(bl);
        this.a(f[7], 1);
        this.cj = 30.0f;
        this.ck = this.cj + 1.0f;
        this.hp = this.maxHp = 3500.0f;
        this.cx = this.cA = 5000.0f;
        this.M = f[7];
    }


    public float bW() {
        if (this.cA > 0.0f && this.cx < this.cA) {
            return this.cx / this.cA;
        }
        return super.bW();
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead || !this.bT()) {
            return;
        }
        if (!this.isDead) {
            if (this.cl != 0.0f) {
                this.S(2);
            } else {
                this.S(4);
            }
        }
        if (this.cK) {
            // empty if block
        }
        this.h += 1.0f * f2;
        if (this.h > 360.0f) {
            this.h -= 360.0f;
        }
        this.eq = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, 4.0f + com.corrodinggames.rts.gameFramework.GameUtils.j(this.h) * 2.0f, 0.1f * f2);
        this.cx = com.corrodinggames.rts.gameFramework.GameUtils.a(this.cx, this.cA, 0.25f * f2);
        this.cy = com.corrodinggames.rts.gameFramework.GameUtils.a(this.cy, 0.0f, 4.0f * f2);
        if (this.cy > 50.0f) {
            this.cy = 50.0f;
        }
        if (this.i != null) {
            PointF pointF = this.E(0);
            this.i.eo = pointF.a;
            this.i.ep = pointF.b;
            this.i.eq = this.eq;
            if (this.i.ej) {
                this.i = null;
            }
        }
    }


    public float bN() {
        return 80000.0f;
    }


    public float L(int n2) {
        return 0.0f;
    }

    @Override
    public PointF K(int n2) {
        PointF pointF = super.K(n2);
        if (this.i != null) {
            pointF.a += this.i.K;
            pointF.b += this.i.L;
        }
        return pointF;
    }


    public float q(int n2) {
        return 0.0f;
    }


    public void a(UnitInstance am2, int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        PointF pointF = this.E(n2);
        if (this.i != null) {
            boolean bl = false;
            if (this.i.ej) {
                bl = true;
            }
            if (this.i.l != am2) {
                bl = true;
            }
            if (bl) {
                this.i = null;
            }
        }
        float f2 = this.b(n2) + this.e(n2) + 5.0f;
        if (this.i != null) {
            this.i.h = f2;
        } else {
            MovementController f3 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
            f3.U = 380.0f;
            f3.l = am2;
            f3.h = f2;
            f3.B = true;
            f3.A = true;
            f3.aQ = true;
            f3.E = true;
            f3.J = 70.0f;
            f3.F = 230.0f;
            f3.ak = 0.75f;
            f3.em = this.em;
            this.i = f3;
        }
    }


    public float m() {
        return 180.0f;
    }


    public float b(int n2) {
        return 8.0f;
    }


    public float e(int n2) {
        return 8.0f;
    }


    public float z() {
        return 0.6f;
    }


    public float bc() {
        return 1.0f;
    }


    public float A() {
        return 1.1f;
    }


    public float B() {
        return 0.03f;
    }


    public float c(int n2) {
        return 1.5f;
    }


    public float C() {
        return 0.02f;
    }


    public float D() {
        return 0.02f;
    }


    public Rect a_(boolean bl) {
        if (this.isDead && !bl) {
            return super.a_(bl);
        }
        if (bl) {
            return super.a_(bl);
        }
        int n2 = 0;
        int n3 = 0;
        this.j.a(n2 += this.g * this.es, n3, n2 + this.es, n3 + this.et);
        return this.j;
    }

    @Override
    public boolean c(float f2) {
        Texture e2;
        if (!super.c(f2)) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this);
        if (!this.isDead) {
            float f3 = 0.0f;
            if (this.i != null) {
                f3 = com.corrodinggames.rts.gameFramework.GameUtils.b(this.i.e(), 0.25f) * 3.0f;
            }
            com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(this, com.corrodinggames.rts.game.units.projectiles.RadarBuilding.e, f3, 0);
        }
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.isDead && this.cx > 0.0f && this.cz == 0.0f && (e2 = this.T()) != null) {
            float f4 = 0.09f;
            f4 += this.cx / this.cA * 0.4f;
            this.k.a((int)((f4 += com.corrodinggames.rts.gameFramework.GameUtils.b(this.cy, 50.0f) / 50.0f * 0.5f) * 255.0f), 255, 255, 255);
            float f5 = this.eo - l2.cw;
            float f6 = this.ep - l2.cx - this.eq;
            l2.bO.a(e2, f5, f6, this.d(false) - 90.0f, this.k);
        }
        return true;
    }


    public boolean l() {
        return true;
    }


    public boolean bi() {
        return true;
    }


    public boolean af() {
        return true;
    }


    public float g(int n2) {
        return 8.0f;
    }


    public PointF G(int n2) {
        float f2 = this.eo;
        float f3 = this.ep;
        bh.a(f2, f3);
        return bh;
    }

    @Override
    public int bl() {
        return 1;
    }


    public boolean bj() {
        return true;
    }


    public int cw() {
        return 5;
    }


    public boolean dd() {
        return true;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
