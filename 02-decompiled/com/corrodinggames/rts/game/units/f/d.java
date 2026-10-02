/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.e;
import com.corrodinggames.rts.gameFramework.f;

public final class d
extends e {
    public RectF a = new RectF();
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;

    @Override
    public final boolean a(am am2) {
        float f2 = am2.eo;
        float f3 = am2.ep;
        if (this.b <= f2 && f2 <= this.c && this.d <= f3 && f3 <= this.e) {
            float f4 = com.corrodinggames.rts.gameFramework.f.a(this.f, this.g, f2, f3);
            return f4 < this.h;
        }
        return false;
    }
}
