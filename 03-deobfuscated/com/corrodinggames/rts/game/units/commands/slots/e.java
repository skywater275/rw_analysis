/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.slots.FactoryBuildSlot;
import com.corrodinggames.rts.gameFramework.GlobalState;

public strictfp class e
extends FactoryBuildSlot {
    final /* synthetic */ BuildActionSlot b;  // 02b e.java L14: final b b

    e(BuildActionSlot b2) {
        super(b2);
        this.b = b2;
    }

    @Override
    public String c() {
        return this.b.x;  // 02b e.java L23: b.x
    }

    @Override
    public int d() {
        return UnitRegistry.f.c() + this.b.dO.getResourceCost();  // 02b e.java L27: b.dO.c() (c()=getResourceCost)
    }

    @Override
    public Texture d(int n2) {
        return this.b.dH();  // 02b e.java L31: b.dH()
    }

    @Override
    float a() {
        return 155.0f;
    }

    @Override
    public float a(int n2) {
        return 5.0f;
    }

    @Override
    public float b(int n2) {
        return 4.0f;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.c(n2);
        MovementController f2 = MovementController.a(this.b, pointF.a, pointF.b);  // 02b e.java L48: game/f.a(b,f,f)
        f2.h = 60.0f;
        f2.t = 3.0f + (float)(this.b.k * 13) % 2.0f;
        f2.aR = false;
        f2.G = true;
        f2.ar = Color.a(105, 255, 255, 255);
        f2.P = (short)3;
        f2.x = 1.3f;
        PointF pointF2 = am2.a(pointF.a, pointF.b, f2.t, f2.h, this.a());
        f2.aC = true;
        f2.m = true;
        f2.n = pointF2.a;
        f2.o = pointF2.b;
        f2.n += (float)(-15 + this.b.k * 13 % 30);
        f2.o += (float)(-15 + (63 + this.b.k * 33) % 30);
        f2.em = 3;
        f2.Y = this.b(n2);
        f2.Z = 65.0f;
        f2.aa = true;
        f2.C = true;
        GlobalState l2 = GlobalState.B();
        ++this.b.k;
        if (this.b.k > 10) {
            this.b.k = 0;
            l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].turretAngle);  // 02b ap.a = turretAngle
        }
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void a(FactoryBuildSlot c2) {
        this.b.cv += 900.0f;
        this.b.cu += 900.0f;
    }

    @Override
    public void a(float f2) {
        if (this.b.cu < this.b.cv) {
            this.b.cu += 0.15f * f2;
            if (this.b.cu > this.b.cv) {
                this.b.cu = this.b.cv;
            }
        }
    }

    @Override
    public float e(int n2) {
        return 11.0f;
    }

    @Override
    public float f(int n2) {
        return 2.0f;
    }

    @Override
    public float g(int n2) {
        return 18.0f;
    }

    @Override
    public float h(int n2) {
        return 0.0f;
    }
}
