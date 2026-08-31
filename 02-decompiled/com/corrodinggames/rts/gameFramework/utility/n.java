/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class n
implements Iterator {
    private int b;
    private int c;
    private int d;
    final /* synthetic */ m a;

    private n(m m2) {
        this.a = m2;
        this.b = this.a.a;
        this.c = -1;
        this.d = m.a(this.a);
    }

    @Override
    public boolean hasNext() {
        return this.b != 0;
    }

    public Object next() {
        m m2 = this.a;
        int n2 = this.b;
        if (m.b(m2) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n2 == 0) {
            throw new NoSuchElementException();
        }
        this.b = n2 - 1;
        this.c = m2.a - n2;
        return m2.b[this.c];
    }

    @Override
    public void remove() {
        Object[] objectArray = this.a.b;
        int n2 = this.c;
        if (m.c(this.a) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n2 < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(objectArray, n2 + 1, objectArray, n2, this.b);
        objectArray[--this.a.a] = null;
        this.c = -1;
        this.d = m.d(this.a);
    }
}
