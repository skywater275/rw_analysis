/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicString$Select
extends LogicString {
    LogicBoolean$ReturnType commonType;
    LogicBoolean selector;
    LogicBoolean childA;
    LogicBoolean childB;

    @Override
    public void setArgumentsRaw(String string, ModUnitRegistry l2, String string2) {
        if (string == null || "".equals(string)) {
            this.validateNumberOfArguments(0);
            return;
        }
        ArrayList arrayList = al.a(string, ",", false);
        this.validateNumberOfArguments(arrayList.size());
        this.selector = LogicBooleanLoader.parseBooleanBlock(l2, (String)arrayList.get(0), true);
        if (this.selector == null) {
            throw new BooleanParseException("Expected non-null argument");
        }
        this.childA = LogicBooleanLoader.parseBooleanBlock(l2, (String)arrayList.get(1), false);
        if (this.childA == null) {
            throw new BooleanParseException("Expected non-null argument");
        }
        this.childB = LogicBooleanLoader.parseBooleanBlock(l2, (String)arrayList.get(2), false);
        if (this.childB == null) {
            throw new BooleanParseException("Expected non-null argument");
        }
        this.commonType = this.childA.getReturnType();
        if (this.commonType != this.childB.getReturnType()) {
            throw new BooleanParseException("Select() expected 2 and 3 argument to be the same type, got: " + this.commonType.name() + " and " + this.childB.getReturnType().name());
        }
    }

    public void validateNumberOfArguments(int n) {
        if (n != 3) {
            throw new BooleanParseException("Expected 3 arguments");
        }
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return this.commonType;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "(selector if:(" + this.selector.getMatchFailReasonForPlayer(y2) + ") then:(" + this.childA.getMatchFailReasonForPlayer(y2) + ") ) else:(" + this.childB.getMatchFailReasonForPlayer(y2) + ") )";
    }

    @Override
    public String readString(UnitType y2) {
        boolean bl = this.selector.read(y2);
        if (bl) {
            return this.childA.readString(y2);
        }
        return this.childB.readString(y2);
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = this.selector.read(y2);
        if (bl) {
            return this.childA.read(y2);
        }
        return this.childB.read(y2);
    }

    @Override
    public float readNumber(UnitType y2) {
        boolean bl = this.selector.read(y2);
        if (bl) {
            return this.childA.readNumber(y2);
        }
        return this.childB.readNumber(y2);
    }

    @Override
    public UnitInstance readUnit(UnitType y2) {
        boolean bl = this.selector.read(y2);
        if (bl) {
            return this.childA.readUnit(y2);
        }
        return this.childB.readUnit(y2);
    }
}
