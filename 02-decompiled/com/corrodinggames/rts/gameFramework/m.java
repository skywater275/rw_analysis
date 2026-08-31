/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;

strictfp class m {
    float a;
    Paint b;
    final /* synthetic */ l c;

    m(l l2) {
        this.c = l2;
    }

    void a() {
        float f2 = this.c.e(this.a);
        if (this.b.k() != f2) {
            if (this.b instanceof ag) {
                ((ag)this.b).c(f2);
            } else {
                this.b.b(f2);
            }
        }
    }
}
