/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareGreaterThanNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareGreaterThanOrEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareLessThanNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareLessThanOrEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathAddJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathDivideJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathModulusJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathMultiplyJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathSubtractJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AndBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$OrBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;

public abstract class LogicBoolean$JoinerBoolean
extends LogicBoolean {
    LogicBoolean[] children;

    public abstract String type();

    public static LogicBoolean$JoinerBoolean getNewJoiner(String string) {
        LogicBoolean$JoinerBoolean logicBoolean$JoinerBoolean;
        if (string.equals("or")) {
            logicBoolean$JoinerBoolean = new LogicBoolean$OrBoolean();
        } else if (string.equals("and")) {
            logicBoolean$JoinerBoolean = new LogicBoolean$AndBoolean();
        } else if (string.equals("==")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$CompareEqualBoolean();
        } else if (string.equals("!=")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$CompareNotEqualBoolean();
        } else if (string.equals(">")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$CompareGreaterThanNumbers();
        } else if (string.equals(">=")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$CompareGreaterThanOrEqualNumbers();
        } else if (string.equals("<")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$CompareLessThanNumbers();
        } else if (string.equals("<=")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$CompareLessThanOrEqualNumbers();
        } else if (string.equals("%")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$MathModulusJoinerBoolean();
        } else if (string.equals("+")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$MathAddJoinerBoolean();
        } else if (string.equals("-")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$MathSubtractJoinerBoolean();
        } else if (string.equals("*")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$MathMultiplyJoinerBoolean();
        } else if (string.equals("/")) {
            logicBoolean$JoinerBoolean = new CompareJoinerBoolean$MathDivideJoinerBoolean();
        } else {
            throw new BooleanParseException("Unknown joiner:'" + string + "'");
        }
        return logicBoolean$JoinerBoolean;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = "(";
        boolean bl = true;
        for (LogicBoolean logicBoolean : this.children) {
            if (bl) {
                bl = false;
            } else {
                string = string + " " + this.type() + " ";
            }
            string = string + logicBoolean.getMatchFailReasonForPlayer(y2);
        }
        string = string + ")";
        return string;
    }

    @Override
    public String getDebugDetails(CustomUnitType j2) {
        String string = "(";
        boolean bl = true;
        for (LogicBoolean logicBoolean : this.children) {
            if (bl) {
                bl = false;
            } else {
                string = string + " " + this.type() + " ";
            }
            string = string + logicBoolean.getDebugDetails(j2);
        }
        string = string + ")";
        return string;
    }

    @Override
    public final void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
    }

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        return this;
    }

    public boolean requireBooleanChildren() {
        return true;
    }
}
