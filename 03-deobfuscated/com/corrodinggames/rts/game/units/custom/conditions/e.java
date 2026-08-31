/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.conditions;
import com.corrodinggames.rts.game.units.custom.RangeValue;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.conditions.a;
import com.corrodinggames.rts.game.units.custom.conditions.d;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public strictfp class e {
    a a;  // 02b custom/c/e: a a (v19.133f5 RangeValue 幻觉修正)
    CustomArrayList b = new CustomArrayList();  // 02b utility/m b (v19.133f5 DirectionConfig 幻觉修正)

    public e(a a2) {  // 02b e(a) (v19.133f5 修正)
        this.a = a2;
    }

    public d a(UnitInstance am2) {
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            d d2 = (d)objectArray[i];
            if (d2.a != am2) continue;
            return d2;
        }
        return null;
    }
}
