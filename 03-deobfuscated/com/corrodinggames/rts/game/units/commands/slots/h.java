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
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.slots.FactoryBuildSlot;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class h
extends FactoryBuildSlot {
    final /* synthetic */ BuildActionSlot b;  // 02b h.java L14: final b b

    h(BuildActionSlot b2) {
        super(b2);
        this.b = b2;
    }

    @Override
    public String c() {
        return this.b.t;  // 02b h.java L23: b.t
    }

    @Override
    public int d() {
        return UnitRegistry.f.c();
    }

    @Override
    public Texture d(int n2) {
        return this.b.dD();  // 02b h.java L31: b.dD()
    }

    @Override
    float a() {
        return 165.0f;
    }

    @Override
    public float b(int n2) {
        return 41.0f;
    }

    @Override
    public float a(int n2) {
        return 30.0f;
    }

    @Override
    public float g(int n2) {
        return 21.0f;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.c(n2);
        MovementController f2 = MovementController.a(this.b, pointF.a, pointF.b);  // 02b h.java L48: game/f.a(b,f,f)
        PointF pointF2 = this.b.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 5.0f;
        f2.ar = Color.a(255, 100, 30, 30);
        f2.U = this.b(n2);
        f2.P = (short)5;
        f2.x = 1.0f;
        GlobalState l2 = GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.b.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].turretAngle);  // 02b ap.a = turretAngle
        float f3 = 1.0f + GameUtils.c(-0.07f, 0.07f);
        l2.bM.a(SoundRegistry.t, 0.3f, f3, pointF.a, pointF.b);  // 02b h.java L75: a/e.t = SoundRegistry.t
    }

    @Override
    public int b() {
        return 1;
    }

    @Override
    public void a(FactoryBuildSlot c2) {
    }

    @Override
    public void a(float f2) {
        this.b.s(f2);
    }
}
