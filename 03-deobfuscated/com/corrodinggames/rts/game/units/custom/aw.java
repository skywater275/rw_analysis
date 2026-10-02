/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

public abstract class aw
extends at {
    public aw(int n, String string) {
        super(n, string);
    }


    public double a(CustomUnitType j2, WeaponConfig as2) {
        return this.a(as2);
    }


    public void a(CustomUnitType j2, double d2) {
        j2.dJ();
        this.a(j2.y, d2);
    }

    public abstract double a(WeaponConfig var1);

    public abstract void a(WeaponConfig var1, double var2);


    public boolean b() {
        return false;
    }
}
