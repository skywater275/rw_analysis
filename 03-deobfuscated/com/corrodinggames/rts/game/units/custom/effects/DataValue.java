/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;

import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;

public strictfp final class DataValue {
    public final LogicBoolean resourceTypeRef;
    public double amountValue;

    public DataValue(LogicBoolean a2) {
        this.resourceTypeRef = a2;
    }

    public DataValue(LogicBoolean a2, double d) {
        this.resourceTypeRef = a2;
        this.amountValue = d;
    }
}
