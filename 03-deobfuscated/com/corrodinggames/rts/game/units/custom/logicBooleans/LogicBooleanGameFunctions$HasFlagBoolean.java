/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$HasFlagBoolean
extends LogicBoolean {
    public int flagMask = 0;
    public int flagId = -1;

    @LogicBoolean$Parameter(positional=0)
    public void id(int n) {
        if (n < 0 || n > 31) {
            throw new BooleanParseException("Flag id must be between 0-31");
        }
        this.flagId = n;
        this.flagMask = 1 << n;
    }

    public static boolean isFlagSet(int n, int n2) {
        return (n2 & n) == n2;
    }

    public static byte setFlag(int n, int n2) {
        return (byte)(n2 | n);
    }

    public static byte unsetFlag(int n, int n2) {
        return (byte)(n2 & ~n);
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = "HasFlag";
        string = string + "(id:" + this.flagId + ")";
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = true;
        if (this.flagMask != 0 && !LogicBooleanGameFunctions$HasFlagBoolean.isFlagSet(y2.cF, this.flagMask)) {
            bl = false;
        }
        return bl;
    }
}
