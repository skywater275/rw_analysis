/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.y;

public final class CompareJoinerBoolean$CompareNotEqualNumbers
extends CompareJoinerBoolean$CompareNumbers {
    @Override
    public String type() {
        return "!=";
    }

    @Override
    public boolean read(y y2) {
        LogicBoolean[] logicBooleanArray = this.children;
        float f = logicBooleanArray[0].readNumber(y2);
        for (int i = 1; i < logicBooleanArray.length; ++i) {
            float f2 = logicBooleanArray[i].readNumber(y2);
            if (!(f2 - 1.0E-4f > f) && !(f2 + 1.0E-4f < f)) {
                return false;
            }
            f = f2;
        }
        return true;
    }
}
