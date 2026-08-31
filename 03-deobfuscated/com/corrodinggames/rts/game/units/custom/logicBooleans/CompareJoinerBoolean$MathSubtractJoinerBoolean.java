/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathJoinerBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class CompareJoinerBoolean$MathSubtractJoinerBoolean
extends CompareJoinerBoolean$MathJoinerBoolean {
    @Override
    public String type() {
        return "-";
    }

    @Override
    public float readNumber(UnitType y2) {
        float f = this.children[0].readNumber(y2);
        for (int i = 1; i < this.children.length; ++i) {
            f -= this.children[i].readNumber(y2);
        }
        return f;
    }
}
