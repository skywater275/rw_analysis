/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.aw;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

final class WeaponConfig$7
extends aw {
    WeaponConfig$7(int n, String string) {
        super(n, string);
    }


    public double a(WeaponConfig as2) {
        return as2.ammoPerBurst;
    }


    public void a(WeaponConfig as2, double d) {
        as2.ammoPerBurst = (int)d;
    }


    public void a(CustomUnitType j2, double d2) {
        int n2 = j2.s();
        super.a(j2, d2);
        if (j2.s() > n2 && !j2.ax) {
            j2.c(false);
        }
    }
}
