/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.opengl.GLTexture;

public class BitmapTexture
extends GLTexture {
    protected Bitmap l;

    public BitmapTexture(Bitmap bitmap) {
        this(bitmap, false);
    }

    public BitmapTexture(Bitmap bitmap, boolean bl) {
        super(bl);
        this.l = bitmap;
        this.m = this.k();
        int n = this.m.b() + 0;
        int n2 = this.m.c() + 0;
        this.a(n, n2);
    }


    protected void a(Bitmap bitmap) {
    }


    protected Bitmap k() {
        return this.l;
    }
}
