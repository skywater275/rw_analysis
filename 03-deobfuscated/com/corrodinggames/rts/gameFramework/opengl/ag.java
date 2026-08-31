/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;

public class ag {
    public static void a(GLObject b2, RectF rectF) {
        byte bl = 0;  // 02b b/ag L9: byte var2
        byte bl2 = 0;  // 02b b/ag L10: byte var3
        int n = b2.b();
        int n2 = b2.c();
        if (b2.f()) {
            bl = 1;
            bl2 = 1;
            --n;
            --n2;
        }
        rectF.a((float)bl, (float)bl2, n, n2);
    }
}
