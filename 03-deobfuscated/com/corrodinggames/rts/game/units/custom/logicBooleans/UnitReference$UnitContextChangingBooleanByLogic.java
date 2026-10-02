/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.UnitType;
import java.util.ArrayList;

public class UnitReference$UnitContextChangingBooleanByLogic
extends LogicBoolean {
    LogicBoolean targetBoolean;
    LogicBoolean dynamicContext;
    LogicBoolean[] dynamicContextChain;

    public static UnitReference$UnitContextChangingBooleanByLogic create(LogicBoolean logicBoolean, LogicBoolean logicBoolean2) {
        UnitReference$UnitContextChangingBooleanByLogic unitReference$UnitContextChangingBooleanByLogic;
        ArrayList<LogicBoolean> arrayList = null;
        if (logicBoolean2 instanceof UnitReference$UnitContextChangingBooleanByLogic) {
            unitReference$UnitContextChangingBooleanByLogic = (UnitReference$UnitContextChangingBooleanByLogic)logicBoolean2;
            arrayList = new ArrayList<LogicBoolean>();
            arrayList.add(logicBoolean);
            if (unitReference$UnitContextChangingBooleanByLogic.dynamicContextChain != null) {
                LogicBoolean[] logicBooleanArray;
                for (LogicBoolean logicBoolean3 : logicBooleanArray = unitReference$UnitContextChangingBooleanByLogic.dynamicContextChain) {
                    arrayList.add(logicBoolean3);
                }
            } else {
                arrayList.add(unitReference$UnitContextChangingBooleanByLogic.dynamicContext);
            }
            logicBoolean2 = unitReference$UnitContextChangingBooleanByLogic.targetBoolean;
        }
        unitReference$UnitContextChangingBooleanByLogic = new UnitReference$UnitContextChangingBooleanByLogic();
        if (arrayList != null) {
            unitReference$UnitContextChangingBooleanByLogic.dynamicContextChain = arrayList.toArray(new LogicBoolean[0]);
        } else {
            unitReference$UnitContextChangingBooleanByLogic.dynamicContext = logicBoolean;
        }
        unitReference$UnitContextChangingBooleanByLogic.targetBoolean = logicBoolean2;
        if (logicBoolean == null) {
            throw new RuntimeException("dynamicContext==null");
        }
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.unit) {
            throw new RuntimeException("dynamicContext type!=unit. Got:" + (Object)((Object)logicBoolean.getReturnType()));
        }
        return unitReference$UnitContextChangingBooleanByLogic;
    }

    @Override
    public LogicBoolean setChild(LogicBoolean logicBoolean) {
        return super.setChild(logicBoolean);
    }

    public UnitType getUnitContext(UnitType y2) {
        if (this.dynamicContextChain != null) {
            UnitType y3 = y2;
            for (LogicBoolean logicBoolean : this.dynamicContextChain) {
                UnitInstance am2 = logicBoolean.readUnit(y3);
                if (!(am2 instanceof UnitType)) {
                    return null;
                }
                y3 = (UnitType)am2;
            }
            return y3;
        }
        UnitInstance am3 = this.dynamicContext.readUnit(y2);
        if (!(am3 instanceof UnitType)) {
            return null;
        }
        return (UnitType)am3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean read(UnitType y2) {
        LogicBoolean.setOuterUnitParameterContext(y2);
        try {
            UnitType y3 = this.getUnitContext(y2);
            if (y3 == null) {
                boolean bl = false;
                return bl;
            }
            boolean bl = this.targetBoolean.read(y3);
            return bl;
        }
        finally {
            LogicBoolean.clearOuterUnitParameterContext();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float readNumber(UnitType y2) {
        LogicBoolean.setOuterUnitParameterContext(y2);
        try {
            UnitType y3 = this.getUnitContext(y2);
            if (y3 == null) {
                float f2 = 0.0f;
                return f2;
            }
            float f3 = this.targetBoolean.readNumber(y3);
            return f3;
        }
        finally {
            LogicBoolean.clearOuterUnitParameterContext();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String readString(UnitType y2) {
        LogicBoolean.setOuterUnitParameterContext(y2);
        try {
            UnitType y3 = this.getUnitContext(y2);
            if (y3 == null) {
                String string = "<unit not found>";
                return string;
            }
            String string = this.targetBoolean.readString(y3);
            return string;
        }
        finally {
            LogicBoolean.clearOuterUnitParameterContext();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnitInstance readUnit(UnitType y2) {
        LogicBoolean.setOuterUnitParameterContext(y2);
        try {
            UnitType y3 = this.getUnitContext(y2);
            if (y3 == null) {
                UnitInstance am2 = null;
                return am2;
            }
            UnitInstance am3 = this.targetBoolean.readUnit(y3);
            return am3;
        }
        finally {
            LogicBoolean.clearOuterUnitParameterContext();
        }
    }

    @Override
    public void forMeta(ModUnitRegistry l2) {
    }

    @Override
    public UnitReference$UnitContextChangingBooleanByLogic with(ModUnitRegistry l2, String string, String string2) {
        return this;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return this.targetBoolean.getReturnType();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        if (this.dynamicContextChain != null) {
            String string = "";
            UnitType y3 = y2;
            LogicBoolean.setOuterUnitParameterContext(y2);
            try {
                for (LogicBoolean logicBoolean : this.dynamicContextChain) {
                    string = string + logicBoolean.getMatchFailReasonForPlayer(y3);
                    UnitInstance am2 = logicBoolean.readUnit(y3);
                    if (!(am2 instanceof UnitType)) {
                        String string2 = string = string + "<unit not found>";
                        return string2;
                    }
                    string = string + ".";
                    y3 = (UnitType)am2;
                }
                string = string + this.targetBoolean.getMatchFailReasonForPlayer(y3);
            }
            finally {
                LogicBoolean.clearOuterUnitParameterContext();
            }
            return string;
        }
        UnitInstance am3 = y2;
        LogicBoolean.setOuterUnitParameterContext(y2);
        try {
            am3 = this.dynamicContext.readUnit(y2);
            String string = !(am3 instanceof UnitType) ? "=<unit not found> (type:" + (Object)((Object)this.dynamicContext.getReturnType()) + ")" : "." + this.targetBoolean.getMatchFailReasonForPlayer((UnitType)am3);
            String string3 = this.dynamicContext.getMatchFailReasonForPlayer(y2) + string;
            return string3;
        }
        finally {
            LogicBoolean.clearOuterUnitParameterContext();
        }
    }
}
