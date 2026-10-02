/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitContextChangingBooleanByLogic;
import com.corrodinggames.rts.gameFramework.l;

public abstract class VariableScope$AbstractMemoryLogicBoolean
extends LogicBoolean {
    @Override
    public LogicBooleanLoader.LogicBooleanContext createContext() {
        LogicBoolean$ReturnType logicBoolean$ReturnType = this.getReturnType();
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
            return UnitReference.unitContextChangingContext;
        }
        if (LogicBoolean$ReturnType.isArrayType(logicBoolean$ReturnType)) {
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.numberArray) {
                return LogicBooleanLoader.numberArrayContextReader;
            }
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.boolArray) {
                return LogicBooleanLoader.boolArrayContextReader;
            }
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unitArray) {
                return LogicBooleanLoader.unitArrayContextReader;
            }
            l.b("Unhandled array context type: " + (Object)((Object)logicBoolean$ReturnType));
            return LogicBooleanLoader.voidContextReader;
        }
        return super.createContext();
    }

    @Override
    public LogicBoolean setChild(LogicBoolean logicBoolean) {
        LogicBoolean$ReturnType logicBoolean$ReturnType = this.getReturnType();
        if (LogicBoolean$ReturnType.isArrayType(logicBoolean$ReturnType)) {
            return logicBoolean;
        }
        UnitReference$UnitContextChangingBooleanByLogic unitReference$UnitContextChangingBooleanByLogic = UnitReference$UnitContextChangingBooleanByLogic.create(this, logicBoolean);
        return unitReference$UnitContextChangingBooleanByLogic;
    }
}
