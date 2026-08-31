/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap$Config;

class ai
implements Cloneable {
    public boolean a;
    public Bitmap$Config b;
    public int c;

    private ai() {
    }

    public int hashCode() {
        int n = this.b.hashCode() ^ this.c;
        return this.a ? n : -n;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ai)) {
            return false;
        }
        ai ai2 = (ai)object;
        return this.a == ai2.a && this.b == ai2.b && this.c == ai2.c;
    }

    public ai a() {
        try {
            return (ai)super.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError((Object)cloneNotSupportedException);
        }
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }
}
