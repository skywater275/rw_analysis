/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.ai;

public class ag
extends ai {
    public Paint a;
    public int b;
    final /* synthetic */ ae c;

    ag(ae ae2, String string, Paint paint) {
        this.c = ae2;
        super(ae2, string);
        this.b = 0;
        this.a = paint;
    }

    ag(ae ae2, String string, Paint paint, int n) {
        this.c = ae2;
        super(ae2, string);
        this.b = 0;
        this.a = paint;
        this.b = n;
    }

    @Override
    public Paint b(Paint paint) {
        if (this.a == null) {
            if (this.b != 0) {
                ae.f.a(paint);
                ae.f.b(this.b);
                return ae.f;
            }
            return paint;
        }
        if (this.b != 0) {
            ae.f.a(this.a);
            ae.f.b(this.b);
            return ae.f;
        }
        return this.a;
    }

    public ag a(String string) {
        ag ag2 = new ag(this.c, string, this.a, this.b);
        return ag2;
    }

    @Override
    public /* synthetic */ ai b(String string) {
        return this.a(string);
    }
}
