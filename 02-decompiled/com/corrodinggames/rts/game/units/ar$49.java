/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import java.util.Comparator;

final class ar$49
implements Comparator {
    ar$49() {
    }

    public int a(as as2, as as3) {
        b b2 = as2.u();
        b b3 = as3.u();
        int n = b2.a(b3);
        return n;
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((as)object, (as)object2);
    }
}
