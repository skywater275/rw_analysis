/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.d;

import com.corrodinggames.rts.game.units.am;

public abstract class a {
    public abstract void a(am var1);

    public abstract boolean b(am var1);

    public abstract void a(am var1, double var2);

    public abstract boolean b(am var1, double var2);

    public boolean c(am am2) {
        if (this.b(am2)) {
            this.a(am2);
            return true;
        }
        return false;
    }

    public boolean c(am am2, double d) {
        if (this.b(am2, d)) {
            this.a(am2, d);
            return true;
        }
        return false;
    }
}
