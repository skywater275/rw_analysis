/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl.batch.resources;

import com.corrodinggames.rts.gameFramework.opengl.batch.a;
import com.corrodinggames.rts.gameFramework.opengl.batch.f;

public abstract class GLTexture {
    private int a;
    private int b;
    private int c;
    private boolean d = false;

    public void a() {
        this.a(null, null, null);
    }

    public void a(String string, String string2, a[] aArray) {
        this.b = f.a(35633, string);
        this.c = f.a(35632, string2);
        this.a = f.a(this.b, this.c, aArray);
        this.d = true;
    }

    public int b() {
        return this.a;
    }
}
