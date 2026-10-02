/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.av;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterFactory;

public class au
extends VariableScope$CachedWriter$WriterFactory {
    ModUnitRegistry a;

    public au(ModUnitRegistry l2) {
        this.a = l2;
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement createWriterElement(String string, String string2, String string3, String string4) throws bo {
        LogicBoolean logicBoolean;
        if (!(string2.equals("=") || string2.equals("+=") || string2.equals("-="))) {
            throw new bo("Only '=','+=','-='  is supported here, got:" + string2);
        }
        if (string3 == null) {
            throw new bo("Expected a value for: " + string + " (likely missing '=')");
        }
        VariableScope$CachedWriter$Operator variableScope$CachedWriter$Operator = VariableScope$CachedWriter$Operator.set;
        if (string2.equals("+=")) {
            variableScope$CachedWriter$Operator = VariableScope$CachedWriter$Operator.add;
        }
        if (string2.equals("-=")) {
            variableScope$CachedWriter$Operator = VariableScope$CachedWriter$Operator.subtract;
        }
        try {
            logicBoolean = LogicBooleanLoader.parseBooleanBlock(this.a, string3, false);
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("LogicBoolean - Error: " + runtimeException.getMessage() + ", [parsing: '" + string3 + "']", runtimeException);
        }
        if (string4 != null) {
            throw new RuntimeException("Unexpected array [] index operator on " + string);
        }
        at at2 = (at)WeaponConfig.s.get(string);
        if (at2 == null) {
            at2 = (at)WeaponConfig.s.get("core." + string);
        }
        if (at2 == null) {
            throw new bo("Unknown key: " + string + "");
        }
        av av2 = new av();
        av2.barColorNormal = at2;
        av2.barColorDamaged = logicBoolean;
        av2.barColorCritical = variableScope$CachedWriter$Operator;
        if (logicBoolean.getReturnType() != at2.a()) {
            throw new bo("Field: " + string + " expects " + (Object)((Object)at2.a()) + " type getting: " + (Object)((Object)logicBoolean.getReturnType()) + " from: " + string3);
        }
        return av2;
    }
}
