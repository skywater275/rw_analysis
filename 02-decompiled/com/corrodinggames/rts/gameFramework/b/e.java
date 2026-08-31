/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.b.ah;

public class e
extends ah {
    protected Bitmap l;

    public e(Bitmap bitmap) {
        this(bitmap, false);
    }

    public e(Bitmap bitmap, boolean bl) {
        super(bl);
        this.l = bitmap;
        this.m = this.k();
        int n = this.m.b() + 0;
        int n2 = this.m.c() + 0;
        this.a(n, n2);
    }

    @Override
    protected void a(Bitmap bitmap) {
    }

    @Override
    protected Bitmap k() {
        return this.l;
    }
}
