/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

import com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.utility.ab;

public strictfp class j
extends AnimationCurveBase {
    LogicBoolean a;
    float b;
    float c;
    UnitConfig d;  // 02b custom/b/j.java L19: custom.h d (h=UnitConfig)
    boolean e;

    public static void a(ModUnitRegistry l2, ab ab2) {
        String string = "movement_repelFromUnits";
        if (ab2.g(string)) {
            j j2 = new j();  // 02b custom/b/j.java L25: new j()
            j2.a(l2, ab2, string, string);
            if (!LogicBoolean.isStaticFalse(j2.a)) {
                l2.a(j2);
            }
        }
    }

    public void a(ModUnitRegistry l2, ab ab2, String string, String string2) {
        this.a = ab2.a(l2, string, "enabled");
        this.b = ab2.i(string, "speed");
        this.c = ab2.a(string, "maxSpeed", Float.valueOf(5.0f)).floatValue();
        this.d = TeamTag.a(ab2.b(string, "otherUnitHasTag", (String)null), (UnitConfig)null);  // 02b custom/b/j.java L37: custom/g.a(String,(custom.h)null)
        this.e = ab2.a(string, "onlySameTeam", (Boolean)false);
    }

    @Override
    public void b(CustomUnitType j2, float f2) {
        if (!this.a.read(j2)) {
            return;
        }
    }
}
