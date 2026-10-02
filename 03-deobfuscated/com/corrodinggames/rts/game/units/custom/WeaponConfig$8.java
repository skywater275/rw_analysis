/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.aw;

final class WeaponConfig$8
extends aw {
    WeaponConfig$8(int n, String string) {
        super(n, string);
    }


    public double a(WeaponConfig as2) {
        return as2.maxAmmoCount;
    }


    public void a(WeaponConfig as2, double d) {
        as2.maxAmmoCount = (int)d;
    }
}
