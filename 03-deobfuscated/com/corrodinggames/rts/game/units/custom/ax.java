/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

public abstract class ax
extends at {
    public ax(int n, String string) {
        super(n, string);
    }


    public double a(CustomUnitType j2, WeaponConfig as2) {
        return this.a(j2);
    }


    public void a(CustomUnitType j2, double d2) {
        j2.dJ();
        this.b(j2, d2);
    }

    public abstract double a(CustomUnitType var1);

    public abstract void b(CustomUnitType var1, double var2);


    public boolean b() {
        return true;
    }
}
