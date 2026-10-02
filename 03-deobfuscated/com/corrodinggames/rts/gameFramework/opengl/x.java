/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.util.Log;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.k;
import com.corrodinggames.rts.gameFramework.opengl.TextureIdProvider;

public class x
extends GLObject {
    private final boolean m;
    private int n = 3553;
    protected boolean l;

    public x(int n, int n2, boolean bl) {
        this(n, n2, bl, 3553);
    }

    public x(int n, int n2, boolean bl, int n3) {
        this.m = bl;
        this.a(n, n2);
        this.n = n3;
    }


    public void b(k k2) {
        TextureIdProvider u2 = k2.a();
        this.a = u2.a();
        if (this.n == 3553) {
            k2.a(this, 6408, 5121, 6408);
        }
        k2.d(this);
        this.b = 1;
        this.a(k2);
    }


    public boolean c(k k2) {
        if (this.i()) {
            return true;
        }
        Log.c("RawTexture", "lost the content due to context change");
        return false;
    }


    public int g() {
        return this.n;
    }

    public boolean k() {
        return this.l;
    }


    public void b(int n) {
    }


    public int h() {
        return 9729;
    }
}
