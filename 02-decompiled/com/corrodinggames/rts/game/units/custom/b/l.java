/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.ab;

public strictfp class l
extends a {
    LogicBoolean a;
    float b;
    float c;
    int d;

    public static void a(com.corrodinggames.rts.game.units.custom.l l2, ab ab2) {
        String string = "movement_random";
        if (ab2.g(string)) {
            l l3 = new l();
            l3.a(l2, ab2, string, string);
            if (!LogicBoolean.isStaticFalse(l3.a)) {
                l2.a(l3);
            }
        }
    }

    public void a(com.corrodinggames.rts.game.units.custom.l l2, ab ab2, String string, String string2) {
        this.a = ab2.a(l2, string, "enabled");
        this.b = ab2.i(string, "speed");
        this.c = ab2.a(string, "maxSpeed", Float.valueOf(5.0f)).floatValue();
        this.d = ab2.b(string, "awayFromEdge", 75);
    }

    @Override
    public void b(j j2, float f2) {
        if (!this.a.read(j2)) {
            return;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (j2.bi()) {
            if (f.c(j2.cc) < this.c) {
                j2.cc += f.b(j2, -this.b, this.b, 1);
            }
            if (f.c(j2.cd) < this.c) {
                j2.cd += f.b(j2, -this.b, this.b, 2);
            }
        } else {
            if (f.c(j2.cf) < this.c) {
                j2.cf += f.b(j2, -this.b, this.b, 1);
            }
            j2.cg += f.b(j2, -1.0f, 1.0f, 2);
        }
        if (this.d > 0) {
            if (j2.ep > l2.bL.j() - (float)this.d) {
                j2.cd -= f.b(j2, 0.0f, this.b * 0.25f, 10);
            }
            if (j2.ep < (float)this.d) {
                j2.cd += f.b(j2, 0.0f, this.b * 0.25f, 11);
            }
            if (j2.eo > l2.bL.i() - (float)this.d) {
                j2.cc -= f.b(j2, 0.0f, this.b * 0.25f, 12);
            }
            if (j2.eo < (float)this.d) {
                j2.cc += f.b(j2, 0.0f, this.b * 0.25f, 13);
            }
        }
        j2.ay = true;
    }
}
