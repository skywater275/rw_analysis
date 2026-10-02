/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.DequeList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class DequeListIterator
implements Iterator {
    private int currentNode;
    private int remainingCount;
    private int d;
    final /* synthetic */ DequeList listRef;

    DequeListIterator(DequeList o2) {
        this.listRef = o2;
        this.currentNode = this.listRef.elementCount;
        this.remainingCount = -1;
        this.d = DequeList.a(this.listRef);
    }

    @Override
    public boolean hasNext() {
        return this.currentNode != 0;
    }

    public Object next() {
        DequeList o2 = this.listRef;
        int n = this.currentNode;
        if (DequeList.b(o2) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n == 0) {
            throw new NoSuchElementException();
        }
        this.currentNode = n - 1;
        this.remainingCount = o2.elementCount - n;
        return o2.d[this.remainingCount];
    }

    @Override
    public void remove() {
        Object[] objectArray = this.listRef.d;
        int n = this.remainingCount;
        if (DequeList.c(this.listRef) != this.d) {
            throw new ConcurrentModificationException();
        }
        if (n < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(objectArray, n + 1, objectArray, n, this.currentNode);
        objectArray[--this.listRef.elementCount] = null;
        this.remainingCount = -1;
        this.d = DequeList.d(this.listRef);
    }
}
