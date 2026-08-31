/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.slots.FactoryBuildSlot;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

strictfp class d
extends FactoryBuildSlot {
    final /* synthetic */ BuildActionSlot b;  // 02b d.java L14: final b b (b=BuildActionSlot)

    d(BuildActionSlot b2) {
        super(b2);
        this.b = b2;
    }

    @Override
    public String c() {
        return this.b.w;  // 02b d.java L23: b.w
    }

    @Override
    public int d() {
        return UnitRegistry.f.c() + this.b.dN.getResourceCost();  // 02b d.java L27: b.dN.c() (c()=getResourceCost)
    }

    @Override
    public Texture d(int n2) {
        return this.b.dG();  // 02b d.java L31: b.dG()
    }

    @Override
    float a() {
        return 350.0f;
    }

    @Override
    public float a(int n2) {
        return 220.0f;
    }

    @Override
    public float b(int n2) {
        return 100.0f;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.c(n2);
        MovementController f2 = MovementController.a(this.b, pointF.a, pointF.b);  // 02b d.java L48: game/f.a(b,f,f) = MovementController.a(UnitInstance,f,f)
        PointF pointF2 = this.b.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.h = 150.0f;
        f2.t = 4.0f;
        f2.aQ = true;
        f2.ar = Color.a(255, 190, 190, 80);
        f2.R = (short)2;
        f2.P = 0;
        f2.x = 0.9f;
        PointF pointF3 = am2.a(pointF.a, pointF.b, f2.t, f2.h, this.a());
        f2.aC = true;
        f2.m = true;
        f2.n = pointF3.a;
        f2.o = pointF3.b;
        f2.Y = this.b(n2);
        f2.Z = 55.0f;
        f2.aa = true;
        GlobalState l2 = GlobalState.B();
        l2.bM.a(SoundRegistry.r, 0.3f, pointF.a, pointF.b);  // 02b d.java L68: gameFramework/a/e.r = SoundRegistry.r
        l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].turretAngle);  // 02b ap.a = turretAngle
        l2.bR.a(f2, -1118482);
        HUDElement e2 = l2.bR.a(pointF.a, pointF.b, this.b.eq, -1118482);
        if (e2 != null) {
            e2.W = e2.V = 15.0f;
        }
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void a(FactoryBuildSlot c2) {
        this.b.cv += 300.0f;
        this.b.cu += 300.0f;
    }

    @Override
    public float e(int n2) {
        return 2.5f;
    }

    @Override
    public float f(int n2) {
        return 0.2f;
    }

    @Override
    public float h(int n2) {
        return -2.0f;
    }
}
