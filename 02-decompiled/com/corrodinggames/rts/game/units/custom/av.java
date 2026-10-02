/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;

public class av
extends VariableScope$CachedWriter$WriterElement {
    public at a;
    public LogicBoolean b;
    public VariableScope.CachedWriter.Operator c;

    @Override
    public void writeToUnit(y y2) {
        if (!(y2 instanceof j)) {
            l.n("Cannot change data on non custom unit:" + am.A(y2));
            return;
        }
        j j2 = (j)y2;
        this.a.a(j2, this.b, this.c);
    }
}
