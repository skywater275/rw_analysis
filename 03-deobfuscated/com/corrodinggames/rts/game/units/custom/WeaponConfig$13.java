/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ax;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;

final class WeaponConfig$13
extends ax {
    WeaponConfig$13(int n, String string) {
        super(n, string);
    }


    public double a(CustomUnitType j2) {
        return j2.cB;
    }


    public void b(CustomUnitType j2, double d2) {
        j2.cB = (float)d2;
    }


    public void a(CustomUnitType j2, double d2) {
        super.a(j2, d2);
        j2.cB = (float)d2;
    }
}
