/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareBooleanOnly;
import com.corrodinggames.rts.game.units.UnitType;

public final class CompareJoinerBoolean$CompareNotEqualBoolean
extends CompareJoinerBoolean$CompareBooleanOnly {
    @Override
    public String type() {
        return "!=";
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = this.children[0].read(y2);
        for (int i = 1; i < this.children.length; ++i) {
            boolean bl2 = this.children[i].read(y2);
            if (bl2 == bl) {
                return false;
            }
            bl = bl2;
        }
        return true;
    }
}
