/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContextWithDefault;

public final class LogicBooleanLoader$VoidContextReader
extends LogicBooleanLoader$LogicBooleanContextWithDefault {
    String debugType;

    LogicBooleanLoader$VoidContextReader(String string) {
        this.debugType = string;
    }

    @Override
    public LogicBoolean parseNextElementInChain(String string, l l2, String string2, boolean bl, String string3, String string4, LogicBoolean logicBoolean) {
        if (string4 != null) {
            if (this.debugType != null) {
                throw new RuntimeException("No field:'" + string2 + "' in '" + string4 + "' (" + this.debugType + ")");
            }
            throw new RuntimeException("No field:'" + string2 + "' in '" + string4 + "'");
        }
        throw new RuntimeException("No field:'" + string2 + "'");
    }
}
