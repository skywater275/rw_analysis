/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$AbstractMemoryLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.game.units.UnitType;

public class VariableScope$KnownMemoryReadLogicBoolean
extends VariableScope$AbstractMemoryLogicBoolean {
    VariableScope$VariableName name;
    LogicBoolean$ReturnType type;

    public VariableScope$KnownMemoryReadLogicBoolean(VariableScope$VariableDefinition variableScope$VariableDefinition) {
        this.name = variableScope$VariableDefinition.name;
        this.type = variableScope$VariableDefinition.type;
    }

    @Override
    public boolean read(UnitType y2) {
        if (y2.bw == null) {
            return false;
        }
        return y2.bw.getBoolean(this.name);
    }

    @Override
    public float readNumber(UnitType y2) {
        if (y2.bw == null) {
            return 0.0f;
        }
        return (float)y2.bw.getNumber(this.name);
    }

    @Override
    public String readString(UnitType y2) {
        if (y2.bw == null) {
            return "";
        }
        return y2.bw.getString(this.name);
    }

    @Override
    public UnitInstance readUnit(UnitType y2) {
        if (y2.bw == null) {
            return null;
        }
        return y2.bw.getUnit(this.name);
    }

    public LogicBoolean readAsLogicBoolean(UnitType y2) {
        if (y2.bw == null) {
            return null;
        }
        return y2.bw.getAsLogicBoolean(this.name);
    }

    @Override
    public int getArraySize(UnitType y2) {
        if (y2.bw == null) {
            return 0;
        }
        return y2.bw.getDataObjectRaw(this.name).getArraySize(y2);
    }

    @Override
    public LogicBoolean readArrayElement(UnitType y2, int n2) {
        if (y2.bw == null) {
            return null;
        }
        return y2.bw.getDataObjectRaw(this.name).readArrayElement(y2, n2);
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return this.type;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        LogicBoolean logicBoolean = this.readAsLogicBoolean(y2);
        if (logicBoolean != null) {
            String string = "";
            if (this.type != logicBoolean.getReturnType() && logicBoolean.getReturnType() != LogicBoolean$ReturnType.voidReturn) {
                string = "(TYPE MISMATCH GOT: " + logicBoolean.getReturnType().name() + ")";
            }
            return "memory(" + VariableScope$VariableName.access$000(this.name) + "=" + logicBoolean.getMatchFailReasonForPlayer(y2) + string + ")";
        }
        return "memory(" + VariableScope$VariableName.access$000(this.name) + "=null)";
    }
}
