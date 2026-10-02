/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.aw;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

final class WeaponConfig$3
extends aw {
    WeaponConfig$3(int n, String string) {
        super(n, string);
    }


    public double a(WeaponConfig as2) {
        return as2.damageMultiplier;
    }


    public void a(WeaponConfig as2, double d) {
        as2.damageMultiplier = (float)d;
    }


    public void a(CustomUnitType j2, double d2) {
        super.a(j2, d2);
        j2.aW();
    }
}
