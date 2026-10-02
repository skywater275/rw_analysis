/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.units.am;

public final class b {
    public static final am[] a = new am[0];
    public int b;
    transient am[] c = a;

    public boolean a(am am2) {
        int n = this.b;
        am[] amArray = this.c;
        if (n == amArray.length) {
            am[] amArray2 = new am[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(amArray, 0, amArray2, 0, n);
            amArray = amArray2;
            this.c = amArray2;
        }
        amArray[n] = am2;
        this.b = n + 1;
        return true;
    }

    public boolean b(am am2) {
        am[] amArray = this.c;
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

    public final am[] a() {
        return this.c;
    }
}
