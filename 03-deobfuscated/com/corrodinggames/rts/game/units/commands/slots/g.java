/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTurret;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.slots.FactoryBuildSlot;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class g
extends FactoryBuildSlot {
    final /* synthetic */ BuildActionSlot b;  // 02b d/a/g.java L15: final b (b=02b units/d/a/b=BuildActionSlot)

    g(BuildActionSlot b2) {
        super(b2);
        this.b = b2;
    }

    @Override
    public String c() {
        return BuildActionSlot.v;
    }

    @Override
    public int d() {
        return UnitRegistry.f.c() + BuildActionSlot.dL.getResourceCost() + BuildActionSlot.dM.getResourceCost();  // 02b d/a/g L28: ar.f.c() + b.dL.c() + b.dM.c() (ar.c=UnitRegistry.c, s.c=GameAction.getResourceCost)
    }

    @Override
    public Texture d(int n2) {
        return BuildActionSlot.dF();
    }

    @Override
    float a() {
        return 320.0f;
    }

    @Override
    public float a(int n2) {
        return 13.0f;
    }

    @Override
    public float b(int n2) {
        return 40.0f;
    }

    @Override
    public PointF c(int n2) {
        PointF pointF = BuildActionSlot.c(this.b, n2);
        float f2 = this.b.E() ? this.b.cg : this.b.cL[n2].turretAngle;
        pointF.a += GameUtils.cosFast(f2 += (float)(this.b.k == 1 ? -90 : 90)) * 4.0f;
        pointF.b += GameUtils.sinFast(f2) * 4.0f;
        return pointF;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.c(n2);
        MovementController f2 = MovementController.a(this.b, pointF.a, pointF.b);  // 02b d/a/g L58: game.f var4
        PointF pointF2 = this.b.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 9.0f;
        f2.ar = Color.a(255, 180, 30, 30);
        f2.U = this.b(n2);
        f2.P = (short)5;
        f2.x = 1.0f;
        GlobalState l2 = GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.b.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].turretAngle);
        float f3 = 1.0f + GameUtils.c(-0.07f, 0.07f);
        l2.bM.a(SoundRegistry.t, 0.15f, f3, pointF.a, pointF.b);
        this.b.k = this.b.k == 1 ? 0 : 1;
    }

    @Override
    public void a(float f2) {
        if (this.b.cu < this.b.cv) {
            this.b.cu += 0.1f * f2;
            if (this.b.cu > this.b.cv) {
                this.b.cu = this.b.cv;
            }
        }
        this.b.s(f2);
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public void a(FactoryBuildSlot c2) {
        if (!(c2 instanceof com.corrodinggames.rts.game.units.commands.slots.f)) {
            this.b.cv += 400.0f;
            this.b.cu += 400.0f;
        }
        this.b.cv += 2800.0f;
        this.b.cu += 2800.0f;
    }
}
