/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b.a.a;

import com.corrodinggames.rts.gameFramework.b.a.a;
import com.corrodinggames.rts.gameFramework.b.a.f;

public abstract class b {
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
