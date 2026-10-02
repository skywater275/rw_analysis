/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.o;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class p
implements Iterator {
    private int b;
    private int c;
    private int d;
    final /* synthetic */ o a;

    private p(o o2) {
        this.a = o2;
        this.b = this.a.c;
        this.c = -1;
        this.d = o.a(this.a);
    }

    @Override
    public boolean hasNext() {
        return this.b != 0;
    }

    public Object next() {
        o o2 = this.a;
        int n = this.b;
        if (o.b(o2) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n == 0) {
            throw new NoSuchElementException();
        }
        this.b = n - 1;
        this.c = o2.c - n;
        return o2.d[this.c];
    }

    @Override
    public void remove() {
        Object[] objectArray = this.a.d;
        int n = this.c;
        if (o.c(this.a) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(objectArray, n + 1, objectArray, n, this.b);
        objectArray[--this.a.c] = null;
        this.c = -1;
        this.d = o.d(this.a);
    }
}
