/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.d.r;

public strictfp class b
extends a {
    public static final a a = new b();

    @Override
    public void b(j j2, float f2) {
        j2.u += f2;
        if (j2.u > 40.0f && j2.aq()) {
            j2.u = 0.0f;
            r.a(j2, f2, 0.0f, false);
        }
    }
}
