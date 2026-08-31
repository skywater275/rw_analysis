/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.TypedObjectList;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class TypedObjectListIterator
implements Iterator {
    private int currentIndex;
    private int listSize;
    private int d;
    final /* synthetic */ TypedObjectList parentList;

    TypedObjectListIterator(TypedObjectList s2) {
        this.parentList = s2;
        this.currentIndex = this.parentList.b;
        this.listSize = -1;
        this.d = TypedObjectList.a(this.parentList);
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex != 0;
    }

    public com.corrodinggames.rts.gameFramework.GameObject a() {  // 02 铁证: iterator 元素 w
        TypedObjectList s2 = this.parentList;
        int n = this.currentIndex;
        if (TypedObjectList.b(s2) != this.d) {
            throw new ConcurrentModificationException("on:" + this.parentList.d + " (modCount:" + TypedObjectList.c(this.parentList) + " expectedModCount:" + this.d + ")");
        }
        if (n == 0) {
            throw new NoSuchElementException();
        }
        this.currentIndex = n - 1;
        this.listSize = s2.b - n;
        return s2.c[this.listSize];
    }

    @Override
    public void remove() {
        com.corrodinggames.rts.gameFramework.GameObject[] wArray = this.parentList.c;
        int n = this.listSize;
        if (TypedObjectList.d(this.parentList) != this.d) {
            throw new ConcurrentModificationException("on:" + this.parentList.d + " (modCount:" + TypedObjectList.e(this.parentList) + " expectedModCount:" + this.d + ")");
        }
        if (n < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(wArray, n + 1, wArray, n, this.currentIndex);
        wArray[--this.parentList.b] = null;
        this.listSize = -1;
        this.d = TypedObjectList.f(this.parentList);
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
