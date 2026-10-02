/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;

import com.corrodinggames.rts.game.units.UnitInstance;

public final class UnitArrayList {
    public static final UnitInstance[] a = new UnitInstance[0];
    public int b;
    transient UnitInstance[] c = a;

    public boolean a(UnitInstance am2) {
        int n = this.b;
        UnitInstance[] amArray = this.c;
        if (n == amArray.length) {
            UnitInstance[] amArray2 = new UnitInstance[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(amArray, 0, amArray2, 0, n);
            amArray = amArray2;
            this.c = amArray2;
        }
        amArray[n] = am2;
        this.b = n + 1;
        return true;
    }

    public boolean b(UnitInstance am2) {
        UnitInstance[] amArray = this.c;
        int n2 = this.b;
        if (am2 != null) {
            for (int i = 0; i < n2; ++i) {
                if (!am2.equals(amArray[i])) continue;
                System.arraycopy(amArray, i + 1, amArray, i, --n2 - i);
                amArray[n2] = null;
                this.b = n2;
                return true;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (amArray[i] != null) continue;
                System.arraycopy(amArray, i + 1, amArray, i, --n2 - i);
                amArray[n2] = null;
                this.b = n2;
                return true;
            }
        }
        return false;
    }

    public final UnitInstance[] a() {
        return this.c;
    }
}
