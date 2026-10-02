/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.a;
import com.corrodinggames.rts.gameFramework.k.n;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class b
implements Iterator {
    private int b;
    private int c;
    private int d;
    final /* synthetic */ a a;

    private b(a a2) {
        this.a = a2;
        this.b = this.a.b;
        this.c = -1;
        this.d = com.corrodinggames.rts.gameFramework.k.a.a(this.a);
    }

    @Override
    public boolean hasNext() {
        return this.b != 0;
    }

    public n a() {
        a a2 = this.a;
        int n2 = this.b;
        if (com.corrodinggames.rts.gameFramework.k.a.b(a2) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n2 == 0) {
            throw new NoSuchElementException();
        }
        this.b = n2 - 1;
        this.c = a2.b - n2;
        return a2.c[this.c];
    }

    @Override
    public void remove() {
        n[] nArray = this.a.c;
        int n2 = this.c;
        if (com.corrodinggames.rts.gameFramework.k.a.c(this.a) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n2 < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(nArray, n2 + 1, nArray, n2, this.b);
        nArray[--this.a.b] = null;
        this.c = -1;
        this.d = com.corrodinggames.rts.gameFramework.k.a.d(this.a);
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
