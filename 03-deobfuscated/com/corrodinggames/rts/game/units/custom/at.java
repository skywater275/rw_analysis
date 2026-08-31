/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.Locale;

public abstract class at {
    int a;
    String b;

    public at(int n, String string) {
        this.a = n;
        this.b = string.toLowerCase(Locale.ROOT);
    }

    public LogicBoolean$ReturnType a() {
        return LogicBoolean$ReturnType.number;
    }

    public abstract double a(CustomUnitType var1, WeaponConfig var2);

    public abstract void a(CustomUnitType var1, double var2);

    public final void a(CustomUnitType j2, LogicBoolean logicBoolean, VariableScope$CachedWriter$Operator variableScope$CachedWriter$Operator) {
        j2.dJ();
        double d2 = logicBoolean.readNumber(j2);
        if (variableScope$CachedWriter$Operator == VariableScope$CachedWriter$Operator.set) {
            this.a(j2, d2);
        } else if (variableScope$CachedWriter$Operator == VariableScope$CachedWriter$Operator.add) {
            double d3 = this.a(j2, j2.y);
            this.a(j2, d3 + d2);
        } else if (variableScope$CachedWriter$Operator == VariableScope$CachedWriter$Operator.subtract) {
            double d4 = this.a(j2, j2.y);
            this.a(j2, d4 - d2);
        } else {
            ModUnitRegistry.n("setUnitDataFromLogic: unsupported operator");
        }
    }

    public abstract boolean b();
}
