/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.af;
import com.corrodinggames.rts.gameFramework.l;

public class ai
extends af {
    String d;
    final /* synthetic */ ae e;

    @Override
    public int a(Paint paint) {
        l l2 = l.B();
        Paint paint2 = this.b(paint);
        int n2 = l2.bO.b(this.d, paint2);
        if (l.at()) {
            // empty if block
        }
        return n2;
    }

    public Paint b(Paint paint) {
        return paint;
    }

    ai(ae ae2, String string) {
        this.e = ae2;
        this.d = string;
    }

    public ai b(String string) {
        ai ai2 = new ai(this.e, string);
        return ai2;
    }
}
