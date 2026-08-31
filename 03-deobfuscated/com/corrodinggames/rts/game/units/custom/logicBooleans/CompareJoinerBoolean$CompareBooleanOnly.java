/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualStrings;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualUnits;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualStrings;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualUnits;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$JoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class CompareJoinerBoolean$CompareBooleanOnly
extends CompareJoinerBoolean {
    @Override
    public boolean requireBooleanChildren() {
        return false;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        if (this.children.length < 2) {
            throw new BooleanParseException("Expected 2 or more elements while using " + this.type() + " have: " + this.children.length);
        }
        boolean bl2 = false;
        LogicBoolean$ReturnType logicBoolean$ReturnType = null;
        for (LogicBoolean logicBoolean : this.children) {
            if (!LogicBoolean.isStaticNull(logicBoolean)) {
                logicBoolean$ReturnType = logicBoolean.getReturnType();
                break;
            }
            bl2 = true;
        }
        if (logicBoolean$ReturnType == null) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.unit;
        }
        if (bl2 && logicBoolean$ReturnType != LogicBoolean$ReturnType.string && logicBoolean$ReturnType != LogicBoolean$ReturnType.unit) {
            throw new BooleanParseException((Object)((Object)logicBoolean$ReturnType) + " cannot be compared to null using " + this.type());
        }
        for (LogicBoolean logicBoolean : this.children) {
            LogicBoolean$ReturnType logicBoolean$ReturnType2 = logicBoolean.getReturnType();
            if (logicBoolean$ReturnType == logicBoolean$ReturnType2 || LogicBoolean.isStaticNull(logicBoolean)) continue;
            throw new BooleanParseException("Mixing types: " + (Object)((Object)logicBoolean$ReturnType) + " and " + (Object)((Object)logicBoolean$ReturnType2) + " while using " + this.type());
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number) {
            if (this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                CompareJoinerBoolean$CompareEqualNumbers compareJoinerBoolean$CompareEqualNumbers = new CompareJoinerBoolean$CompareEqualNumbers();
                compareJoinerBoolean$CompareEqualNumbers.children = this.children;
                return ((LogicBoolean$JoinerBoolean)compareJoinerBoolean$CompareEqualNumbers).validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
            if (this instanceof CompareJoinerBoolean$CompareNotEqualBoolean) {
                CompareJoinerBoolean$CompareNotEqualNumbers compareJoinerBoolean$CompareNotEqualNumbers = new CompareJoinerBoolean$CompareNotEqualNumbers();
                compareJoinerBoolean$CompareNotEqualNumbers.children = this.children;
                return ((LogicBoolean$JoinerBoolean)compareJoinerBoolean$CompareNotEqualNumbers).validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
            throw new BooleanParseException("Unhandled compare type: " + this.type() + " while using numbers");
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.bool) {

            Object var8_12 = null;
            for (LogicBoolean logicBoolean : this.children) {
                if (!(logicBoolean instanceof LogicBoolean$StaticBoolean)) continue;
                LogicBoolean$StaticBoolean logicBoolean$StaticBoolean = (LogicBoolean$StaticBoolean)logicBoolean;
            }
            if (var8_12 == null || this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                // empty if block
            }
            return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.string) {
            if (this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                CompareJoinerBoolean$CompareEqualStrings compareJoinerBoolean$CompareEqualStrings = new CompareJoinerBoolean$CompareEqualStrings();
                compareJoinerBoolean$CompareEqualStrings.children = this.children;
                return compareJoinerBoolean$CompareEqualStrings.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
            if (this instanceof CompareJoinerBoolean$CompareNotEqualBoolean) {
                CompareJoinerBoolean$CompareNotEqualStrings compareJoinerBoolean$CompareNotEqualStrings = new CompareJoinerBoolean$CompareNotEqualStrings();
                compareJoinerBoolean$CompareNotEqualStrings.children = this.children;
                return compareJoinerBoolean$CompareNotEqualStrings.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
            throw new BooleanParseException("Unhandled compare type: " + this.type() + " while using numbers");
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
            if (this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                CompareJoinerBoolean$CompareEqualUnits compareJoinerBoolean$CompareEqualUnits = new CompareJoinerBoolean$CompareEqualUnits();
                compareJoinerBoolean$CompareEqualUnits.children = this.children;
                return compareJoinerBoolean$CompareEqualUnits.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
            if (this instanceof CompareJoinerBoolean$CompareNotEqualBoolean) {
                CompareJoinerBoolean$CompareNotEqualUnits compareJoinerBoolean$CompareNotEqualUnits = new CompareJoinerBoolean$CompareNotEqualUnits();
                compareJoinerBoolean$CompareNotEqualUnits.children = this.children;
                return compareJoinerBoolean$CompareNotEqualUnits.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
            throw new BooleanParseException("Unhandled compare type: " + this.type() + " while using numbers");
        }
        throw new BooleanParseException("Unhandled type: " + (Object)((Object)logicBoolean$ReturnType) + " while using " + this.type());
    }
}
