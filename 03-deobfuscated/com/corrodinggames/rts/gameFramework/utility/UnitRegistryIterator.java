/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.utility.UnitRegistry;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class UnitRegistryIterator
implements Iterator {
    private int b;
    private int c;
    private int d;
    final /* synthetic */ UnitRegistry a;

    UnitRegistryIterator(UnitRegistry u2, UnitRegistry$1 u3) {  // 02b v: synthetic v(u, u$1) 合成构造
        this(u2);
    }

    private UnitRegistryIterator(UnitRegistry u2) {
        this.a = u2;
        this.b = this.a.b;
        this.c = -1;
        this.d = UnitRegistry.a(this.a);  // 02b v: u.a(this.a) (u=utility/u 璇缓鍚?
    }

    @Override
    public boolean hasNext() {
        return this.b != 0;
    }

    public UnitInstance a() {
        UnitRegistry u2 = this.a;
        int n = this.b;
        if (UnitRegistry.b(u2) != this.d) {  // 02b v: u.b(var1)
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
        UnitInstance[] amArray = this.a.c;
        int n = this.c;
        if (UnitRegistry.c(this.a) != this.d) {  // 02b v: u.c(var1)
            throw new ConcurrentModificationException();
        }
        if (n < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(amArray, n + 1, amArray, n, this.b);
        amArray[--this.a.b] = null;
        this.c = -1;
        this.d = UnitRegistry.d(this.a);  // 02b v: u.d(var1)
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}


