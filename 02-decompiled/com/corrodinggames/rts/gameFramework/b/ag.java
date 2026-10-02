/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.b.b;

public class ag {
    public static void a(b b2, RectF rectF) {
        boolean bl = false;
        boolean bl2 = false;
        int n = b2.b();
        int n2 = b2.c();
        if (b2.f()) {
            bl = true;
            bl2 = true;
            --n;
            --n2;
        }
        rectF.a((float)bl, (float)bl2, n, n2);
    }
}
