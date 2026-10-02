/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.m;

public class LogicBooleanGameFunctions$TransportingCountBoolean
extends LogicBoolean$AbstractNumberBoolean {
    public g _withTag;
    public boolean filtered;
    @LogicBoolean.Parameter
    public int slot = -1;

    @Override
    public String getName() {
        return "TransportingCount";
    }

    @LogicBoolean.Parameter
    public void withTag(String string) {
        this._withTag = g.c(string);
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this._withTag != null || this.slot != -1) {
            this.filtered = true;
        }
    }

    @Override
    public float getValue(y y2) {
        int n2;
        if (!this.filtered) {
            n2 = y2.bB();
        } else {
            n2 = 0;
            m m2 = y2.bz();
            if (m2 != null) {
                Object[] objectArray = m2.a();
                for (int i = m2.a - 1; i >= 0; --i) {
                    h h2;
                    y y3 = (y)objectArray[i];
                    if (y3 == null || this.slot != -1 && i != this.slot || this._withTag != null && !g.a(this._withTag, h2 = y3.de())) continue;
                    ++n2;
                }
            }
        }
        return n2;
    }

    @Override
    public float getMaxValue(y y2) {
        return 2.14748365E9f;
    }
}
