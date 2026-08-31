/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class CustomArrayListIterator
implements Iterator {
    private int currentIndex;
    private int c;
    private int d;
    final /* synthetic */ CustomArrayList listRef;

    public CustomArrayListIterator(CustomArrayList m2) {
        this.listRef = m2;
        this.currentIndex = this.listRef.a;
        this.c = -1;
        this.d = CustomArrayList.a(this.listRef);
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex != 0;
    }

    public Object next() {
        CustomArrayList m2 = this.listRef;
        int n2 = this.currentIndex;
        if (CustomArrayList.b(m2) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n2 == 0) {
            throw new NoSuchElementException();
        }
        this.currentIndex = n2 - 1;
        this.c = m2.a - n2;
        return m2.b[this.c];
    }

    @Override
    public void remove() {
        Object[] objectArray = this.listRef.b;
        int n2 = this.c;
        if (CustomArrayList.c(this.listRef) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n2 < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(objectArray, n2 + 1, objectArray, n2, this.currentIndex);
        objectArray[--this.listRef.a] = null;
        this.c = -1;
        this.d = CustomArrayList.d(this.listRef);
    }
}
