/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.a;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.z;

class b
extends a {
    public z e;
    public float f;
    public float g;
    public boolean h;

    public b(float f, float f2) {
        super(f, f2);
    }

    public void a(l l2, String string, String string2) {
        try {
            if (string.equalsIgnoreCase("x")) {
                this.f = Float.parseFloat(string2);
                return;
            }
            if (string.equalsIgnoreCase("y")) {
                this.g = Float.parseFloat(string2);
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw new bo("Failed to parse float:" + string2);
        }
        if (string.equalsIgnoreCase("name")) {
            this.e = l2.a(string2, (z)null);
            return;
        }
        throw new bo("Unknown event key:" + string + " on animation");
    }

    public void finalize() {
        this.h = true;
        if (this.e == null) {
            throw new bo("Animation effect missing key 'name'");
        }
    }

    public void a(j j2) {
        if (this.e != null) {
            float f2 = j2.eo;
            float f3 = j2.ep;
            this.e.a(f2 += this.f, f3 += this.g, j2.eq, j2.cg, j2);
        }
    }
}
