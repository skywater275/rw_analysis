/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class CompareJoinerBoolean$CompareEqualStrings
extends CompareJoinerBoolean {
    @Override
    public String type() {
        return "==";
    }

    @Override
    public boolean read(UnitType y2) {
        LogicBoolean[] logicBooleanArray = this.children;
        String string = logicBooleanArray[0].readString(y2);
        if (string == null) {
            string = "";
        }
        for (int i = 1; i < logicBooleanArray.length; ++i) {
            String string2 = logicBooleanArray[i].readString(y2);
            if (string2 == null) {
                string2 = "";
            }
            if (!string.equals(string2)) {
                return false;
            }
            string = string2;
        }
        return true;
    }
}
