/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.utility.ab;

public strictfp class m {
    public boolean a;
    public float b;
    public int c;
    public int d;
    public float e;

    public static m a(l l2, ab ab2, String string, String string2, boolean bl) {
        m m2 = new m();
        m2.a = ab2.a(string, string2 + "direction_useMainTurret", (Boolean)false);
        m2.b = ab2.a(string, string2 + "direction_units", Float.valueOf(0.0f)).floatValue();
        m2.c = ab2.b(string, string2 + "direction_strideX", -1);
        m2.d = ab2.b(string, string2 + "direction_strideY", -1);
        m2.e = ab2.a(string, string2 + "direction_starting", Float.valueOf(0.0f)).floatValue();
        if (m2.b == 0.0f) {
            return null;
        }
        return m2;
    }
}
