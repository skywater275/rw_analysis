/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.resources;

import com.corrodinggames.rts.game.units.UnitInstance;

public abstract class CommandSlotBase {
    public abstract void a(UnitInstance var1);

    public boolean c(UnitInstance am2) {  // 02b custom.d.a.c(am): 应用并返回 (重复副本已删 v19.133f8)
        if (this.b(am2)) {
            this.a(am2);
            return true;
        }
        return false;
    }

    public abstract boolean b(UnitInstance var1);

    public abstract void a(UnitInstance var1, double var2);

    public abstract boolean b(UnitInstance var1, double var2);

    public boolean c(UnitInstance am2, double d) {
        if (this.b(am2, d)) {
            this.a(am2, d);
            return true;
        }
        return false;
    }
}
