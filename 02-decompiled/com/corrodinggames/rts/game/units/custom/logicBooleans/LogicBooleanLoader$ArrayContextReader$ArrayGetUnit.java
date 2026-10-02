/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayGet;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitContextChangingBooleanByLogic;

public class LogicBooleanLoader$ArrayContextReader$ArrayGetUnit
extends LogicBooleanLoader$ArrayContextReader$ArrayGet {
    @Override
    public LogicBooleanLoader.LogicBooleanContext createContext() {
        return UnitReference.unitContextChangingContext;
    }

    @Override
    public LogicBoolean setChild(LogicBoolean logicBoolean) {
        UnitReference$UnitContextChangingBooleanByLogic unitReference$UnitContextChangingBooleanByLogic = UnitReference$UnitContextChangingBooleanByLogic.create(this, logicBoolean);
        return unitReference$UnitContextChangingBooleanByLogic;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.unit;
    }
}
