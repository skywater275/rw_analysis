/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class v
implements Iterator {
    private int b;
    private int c;
    private int d;
    final /* synthetic */ u a;

    private v(u u2) {
        this.a = u2;
        this.b = this.a.b;
        this.c = -1;
        this.d = u.a(this.a);
    }

    @Override
    public boolean hasNext() {
        return this.b != 0;
    }

    public am a() {
        u u2 = this.a;
        int n = this.b;
        if (u.b(u2) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n == 0) {
            throw new NoSuchElementException();
        }
        this.b = n - 1;
        this.c = u2.b - n;
        return u2.c[this.c];
    }

    @Override
    public void remove() {
        am[] amArray = this.a.c;
        int n = this.c;
        if (u.c(this.a) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(amArray, n + 1, amArray, n, this.b);
        amArray[--this.a.b] = null;
        this.c = -1;
        this.d = u.d(this.a);
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
