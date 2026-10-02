/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import java.util.Comparator;

strictfp final class h$15
implements Comparator {
    h$15() {
    }

    public int a(as as2, as as3) {
        Boolean bl;
        Boolean bl2;
        Boolean bl3;
        am am2 = am.c(as2);
        am am3 = am.c(as3);
        Boolean bl4 = am2.bP();
        int n2 = bl4.compareTo(bl3 = Boolean.valueOf(am3.bP()));
        if (n2 != 0) {
            return n2;
        }
        Boolean bl5 = as2.j();
        n2 = bl5.compareTo(bl2 = Boolean.valueOf(as3.j()));
        if (n2 != 0) {
            return n2;
        }
        Boolean bl6 = am2.bO();
        n2 = bl6.compareTo(bl = Boolean.valueOf(am3.bO()));
        if (n2 != 0) {
            return n2;
        }
        b b2 = as2.u();
        b b3 = as3.u();
        b b4 = as2.B();
        b b5 = as3.B();
        if (b4 != null) {
            b2 = b.a(b2, b4);
        }
        if (b5 != null) {
            b3 = b.a(b3, b5);
        }
        if ((n2 = b2.a(b3)) != 0) {
            return n2;
        }
        return 0;
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((as)object, (as)object2);
    }
}
