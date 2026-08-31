/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;
import com.corrodinggames.rts.game.units.custom.RangeValue;

import com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.commands.RepairBayUnit;

public strictfp class b
extends AnimationCurveBase {
    public static final AnimationCurveBase a = new b();  // 02b b/b.java L8: static final a(AnimationCurveBase)

    public void b(CustomUnitType j2, float f2) {  // 02b b/b.java L11: 方法非构造器
        j2.u += f2;
        if (j2.u > 40.0f && j2.aq()) {
            j2.u = 0.0f;
            RepairBayUnit.a(j2, f2, 0.0f, false);  // 02b custom/b/b.java L19: units/d/r.a (r=RepairBayUnit)
        }
    }
}
