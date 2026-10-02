/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.gameFramework.g.f;

public abstract class d
implements Comparable {
    private int a;

    public void b(f f2) {
        this.a = this.a(f2);
    }

    public abstract String b();

    public abstract boolean a();

    public abstract int c();

    public abstract int d();

    public abstract int a(f var1);

    public int a(d d2) {
        if (this.a == d2.a) {
            return this.b().compareTo(d2.b());
        }
        return d2.a - this.a;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((d)object);
    }

    static /* synthetic */ int b(d d2) {
        return d2.a;
    }
}
