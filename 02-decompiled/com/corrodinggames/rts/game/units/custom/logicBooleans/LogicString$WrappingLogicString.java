/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicString$WrappingLogicString
extends LogicString {
    LogicBoolean[] children;

    @Override
    public void setArgumentsRaw(String string, l l2, String string2) {
        if (string == null || "".equals(string)) {
            this.validateNumberOfArguments(0);
            return;
        }
        ArrayList arrayList = al.a(string, ",", false);
        this.validateNumberOfArguments(arrayList.size());
        this.children = new LogicBoolean[arrayList.size()];
        for (int i = 0; i < arrayList.size(); ++i) {
            this.children[i] = LogicBooleanLoader.parseBooleanBlock(l2, (String)arrayList.get(i), false);
            if (this.children != null) continue;
            throw new BooleanParseException("Expected non-null argument");
        }
    }

    public void validateNumberOfArguments(int n) {
        if (n != 1) {
            throw new BooleanParseException("Expected 1 argument");
        }
    }
}
