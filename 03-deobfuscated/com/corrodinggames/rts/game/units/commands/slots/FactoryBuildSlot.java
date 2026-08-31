/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

strictfp abstract class FactoryBuildSlot {
    final /* synthetic */ BuildActionSlot a;  // 02b units/d/a/c.java: final b a (b=BuildActionSlot)

    FactoryBuildSlot(BuildActionSlot b2) {
        this.a = b2;
    }

    abstract float a();

    public abstract float a(int var1);

    public abstract float b(int var1);

    public PointF c(int n2) {
        return BuildActionSlot.a(this.a, n2);  // 02b c.java: b.a(this.a, var1)
    }

    public abstract void a(UnitInstance var1, int var2);

    public abstract Texture d(int var1);

    public abstract int b();

    public abstract String c();

    public abstract void a(FactoryBuildSlot var1);

    public boolean a(String string) {
        return this.c().equals(string);
    }

    public void a(float f2) {
    }

    public float e(int n2) {
        return 5.0f;
    }

    public float f(int n2) {
        return 0.5f;
    }

    public float g(int n2) {
        return 23.0f;
    }

    public float h(int n2) {
        return -1.0f;
    }

    public abstract int d();
}
