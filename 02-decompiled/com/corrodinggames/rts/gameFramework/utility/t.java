/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.s;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class t
implements Iterator {
    private int b;
    private int c;
    private int d;
    final /* synthetic */ s a;

    private t(s s2) {
        this.a = s2;
        this.b = this.a.b;
        this.c = -1;
        this.d = s.a(this.a);
    }

    @Override
    public boolean hasNext() {
        return this.b != 0;
    }

    public w a() {
        s s2 = this.a;
        int n = this.b;
        if (s.b(s2) != this.d) {
            throw new ConcurrentModificationException("on:" + this.a.d + " (modCount:" + s.c(this.a) + " expectedModCount:" + this.d + ")");
        }
        if (n == 0) {
            throw new NoSuchElementException();
        }
        this.b = n - 1;
        this.c = s2.b - n;
        return s2.c[this.c];
    }

    @Override
    public void remove() {
        w[] wArray = this.a.c;
        int n = this.c;
        if (s.d(this.a) != this.d) {
            throw new ConcurrentModificationException("on:" + this.a.d + " (modCount:" + s.e(this.a) + " expectedModCount:" + this.d + ")");
        }
        if (n < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(wArray, n + 1, wArray, n, this.b);
        wArray[--this.a.b] = null;
        this.c = -1;
        this.d = s.f(this.a);
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
