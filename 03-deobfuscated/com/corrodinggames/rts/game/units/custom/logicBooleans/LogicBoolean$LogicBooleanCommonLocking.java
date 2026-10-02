/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public abstract class LogicBoolean$LogicBooleanCommonLocking
extends LogicBoolean {
    boolean locked = false;

    @Override
    public LogicBoolean createLocked() {
        LogicBoolean$LogicBooleanCommonLocking logicBoolean$LogicBooleanCommonLocking;
        try {
            logicBoolean$LogicBooleanCommonLocking = (LogicBoolean$LogicBooleanCommonLocking)this.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new RuntimeException(cloneNotSupportedException);
        }
        logicBoolean$LogicBooleanCommonLocking.locked = true;
        return logicBoolean$LogicBooleanCommonLocking;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }
}
