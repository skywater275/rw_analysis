/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;

import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.utility.ab;

public strictfp class l
extends AnimationCurveBase {
    LogicBoolean a;
    float b;
    float c;
    int d;

    public static void a(ModUnitRegistry l2, ab ab2) {
        String string = "movement_random";
        if (ab2.g(string)) {
            l l3 = new l();
            l3.a(l2, ab2, string, string);
            if (!LogicBoolean.isStaticFalse(l3.a)) {
                l2.a(l3);
            }
        }
    }

    public void a(ModUnitRegistry l2, ab ab2, String string, String string2) {
        this.a = ab2.a(l2, string, "enabled");
        this.b = ab2.i(string, "speed");
        this.c = ab2.a(string, "maxSpeed", Float.valueOf(5.0f)).floatValue();
        this.d = ab2.b(string, "awayFromEdge", 75);
    }

    @Override
    public void b(CustomUnitType j2, float f2) {
        if (!this.a.read(j2)) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (j2.bi()) {
            if (GameUtils.c(j2.cc) < this.c) {
                j2.cc += GameUtils.b(j2, -this.b, this.b, 1);
            }
            if (GameUtils.c(j2.cd) < this.c) {
                j2.cd += GameUtils.b(j2, -this.b, this.b, 2);
            }
        } else {
            if (GameUtils.c(j2.cf) < this.c) {
                j2.cf += GameUtils.b(j2, -this.b, this.b, 1);
            }
            j2.cg += GameUtils.b(j2, -1.0f, 1.0f, 2);
        }
        if (this.d > 0) {
            if (j2.ep > l2.bL.j() - (float)this.d) {
                j2.cd -= GameUtils.b(j2, 0.0f, this.b * 0.25f, 10);
            }
            if (j2.ep < (float)this.d) {
                j2.cd += GameUtils.b(j2, 0.0f, this.b * 0.25f, 11);
            }
            if (j2.eo > l2.bL.i() - (float)this.d) {
                j2.cc -= GameUtils.b(j2, 0.0f, this.b * 0.25f, 12);
            }
            if (j2.eo < (float)this.d) {
                j2.cc += GameUtils.b(j2, 0.0f, this.b * 0.25f, 13);
            }
        }
        j2.ay = true;
    }
}
