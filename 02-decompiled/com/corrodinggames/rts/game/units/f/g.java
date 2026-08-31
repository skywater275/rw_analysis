/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.e;

public final class g
extends e {
    public RectF a = new RectF();
    public float b;
    public float c;
    public float d;
    public float e;

    public void a(float f, float f2, float f3, float f4) {
        this.b = f;
        this.c = f3;
        this.d = f2;
        this.e = f4;
        this.a.a(f, f2, f3, f4);
    }

    @Override
    public final boolean a(am am2) {
        float f2 = am2.eo;
        float f3 = am2.ep;
        return this.b <= f2 && f2 <= this.c && this.d <= f3 && f3 <= this.e;
    }
}
