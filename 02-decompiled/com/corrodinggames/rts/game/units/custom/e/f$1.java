/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.e.f;
import java.util.Comparator;

strictfp class f$1
implements Comparator {
    final /* synthetic */ f a;

    f$1(f f2) {
        this.a = f2;
    }

    public int a(e e2, e e3) {
        if (e2.a == null || e3.a == null) {
            return 0;
        }
        return Float.compare(e2.a.x, e3.a.x);
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((e)object, (e)object2);
    }
}
