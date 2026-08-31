/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.d;

import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;

class d {
    public final a a;
    public double b;
    public LogicBoolean c;

    public d(a a2, LogicBoolean logicBoolean) {
        this.a = a2;
        if (this.c instanceof LogicBoolean$StaticValueBoolean) {
            this.b = ((LogicBoolean$StaticValueBoolean)this.c).getStaticValue();
        } else {
            this.c = logicBoolean;
        }
    }
}
