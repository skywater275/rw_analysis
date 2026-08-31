/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.c;
import com.corrodinggames.rts.game.units.custom.b.d;
import com.corrodinggames.rts.game.units.custom.b.f;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.t;
import com.corrodinggames.rts.gameFramework.utility.m;

public strictfp class g
extends t {
    String a;
    m b = new m();

    public g(String string) {
        this.a = string;
    }

    @Override
    public void a(l l2) {
        if (this.a != null) {
            String[] stringArray;
            for (String string : stringArray = this.a.split(",")) {
                d d2 = c.b(l2, string = string.trim());
                if (d2 == null) {
                    throw new bo("Failed to find decal: " + string);
                }
                this.b.add(d2);
            }
            this.a = null;
        }
    }

    public void a(j j2, float f2, float f3) {
        c.i.a(f2, f3);
        c.a(j2, 0.0f, f.f, this.b, c.i);
    }
}
