/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.slots.FactoryBuildSlot;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class f
extends FactoryBuildSlot {
    final /* synthetic */ BuildActionSlot b;  // 02b f.java L14: final b b

    f(BuildActionSlot b2) {
        super(b2);
        this.b = b2;
    }

    @Override
    public String c() {
        return this.b.u;  // 02b f.java L23: b.u
    }

    @Override
    public int d() {
        return UnitRegistry.f.c() + this.b.dL.getResourceCost();  // 02b f.java L27: b.dL.c() (c()=getResourceCost)
    }

    @Override
    public Texture d(int n2) {
        return this.b.dE();  // 02b f.java L31: b.dE()
    }

    @Override
    float a() {
        return 185.0f;
    }

    @Override
    public float a(int n2) {
        return 20.0f;
    }

    @Override
    public float b(int n2) {
        return 44.0f;
    }

    @Override
    public float g(int n2) {
        return 21.0f;
    }

    @Override
    public PointF c(int n2) {
        PointF pointF = BuildActionSlot.b(this.b, n2);  // 02b f.java L51: b.b(this.b, var1)
        float f2 = this.b.E() ? this.b.cg : this.b.cL[n2].turretAngle;  // 02b f.java L52 (ap.a = turretAngle)
        pointF.a += GameUtils.cosFast(f2 += (float)(this.b.k == 1 ? -90 : 90)) * 4.0f;  // 02b f.k
        pointF.b += GameUtils.sinFast(f2) * 4.0f;  // 02b f.j
        return pointF;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.c(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this.b, pointF.a, pointF.b);
        PointF pointF2 = this.b.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 6.0f;
        f2.ar = Color.a(255, 40, 30, 110);
        f2.U = this.b(n2);
        f2.P = (short)5;
        f2.x = 1.0f;
        GlobalState l2 = GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.b.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].turretAngle);  // 02b ap.a = turretAngle
        float f3 = 1.0f + GameUtils.c(-0.07f, 0.07f);
        l2.bM.a(SoundRegistry.t, 0.3f, f3, pointF.a, pointF.b);  // 02b f.java L75: a/e.t = SoundRegistry.t
        this.b.k = this.b.k == 1 ? 0 : 1;
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void a(FactoryBuildSlot c2) {
        this.b.cv += 400.0f;
        this.b.cu += 400.0f;
    }

    @Override
    public void a(float f2) {
        this.b.s(f2);
    }
}
