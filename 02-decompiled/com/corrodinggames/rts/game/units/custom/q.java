/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;
import java.util.Comparator;

strictfp class q
implements Comparator {
    public int a(l l2, l l3) {
        if (l2.M == null || l3.M == null) {
            return 0;
        }
        return l2.M.compareTo(l3.M);
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((l)object, (l)object2);
    }
}
