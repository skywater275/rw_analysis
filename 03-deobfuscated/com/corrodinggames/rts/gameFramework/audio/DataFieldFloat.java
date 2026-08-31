/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.audio;

import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;

public abstract class DataFieldFloat
implements Comparable {
    private int a;

    public void b(DataFieldProvider f2) {
        this.a = this.a(f2);
    }

    public abstract String b();

    public abstract boolean a();

    public abstract int c();

    public abstract int d();

    public abstract int a(DataFieldProvider var1);

    public int a(DataFieldFloat d2) {
        if (this.a == d2.a) {
            return this.b().compareTo(d2.b());
        }
        return d2.a - this.a;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((DataFieldFloat) object);
    }

    static /* synthetic */ int b(DataFieldFloat d2) {
        return d2.a;
    }
}
