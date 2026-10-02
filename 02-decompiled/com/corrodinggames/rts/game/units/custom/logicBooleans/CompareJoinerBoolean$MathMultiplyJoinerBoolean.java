/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathJoinerBoolean;
import com.corrodinggames.rts.game.units.y;

public final class CompareJoinerBoolean$MathMultiplyJoinerBoolean
extends CompareJoinerBoolean$MathJoinerBoolean {
    @Override
    public String type() {
        return "*";
    }

    @Override
    public float readNumber(y y2) {
        float f = this.children[0].readNumber(y2);
        for (int i = 1; i < this.children.length; ++i) {
            f *= this.children[i].readNumber(y2);
        }
        return f;
    }
}
