/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.FileWatcher;
import com.corrodinggames.rts.gameFramework.GlobalState;

strictfp class aa {
    public long a;
    public String maxCapacity;

    public aa(String string) {
        this.maxCapacity = string;
        this.a = this.a(true);
    }

    public long a(boolean bl) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.au()) {
            return 0L;
        }
        long l2 = com.corrodinggames.rts.gameFramework.FileWatcher.a(this.maxCapacity, bl);
        if (bl && l2 == 0L) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Failed to watch: " + this.maxCapacity);
        }
        return l2;
    }
}
