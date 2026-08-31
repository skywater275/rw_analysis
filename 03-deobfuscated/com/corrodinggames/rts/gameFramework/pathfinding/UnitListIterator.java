/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;


import com.corrodinggames.rts.gameFramework.pathfinding.UnitList;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class UnitListIterator
implements Iterator {
    private int categoryIndex;
    private int unitIndex;
    private int totalUnits;
    final /* synthetic */ UnitList a;  // 02b k/b.java L16: final a a

    private UnitListIterator(UnitList a2) {
        this.a = a2;
        this.categoryIndex = this.a.b;
        this.unitIndex = -1;
        this.totalUnits = com.corrodinggames.rts.gameFramework.pathfinding.UnitList.a(this.a);
    }

    @Override
    public boolean hasNext() {
        return this.categoryIndex != 0;
    }

    public AStarNode a() {
        UnitList a2 = this.a;  // 02b k/b.java L31: a var1
        int n2 = this.categoryIndex;
        if (com.corrodinggames.rts.gameFramework.pathfinding.UnitList.b(a2) != this.totalUnits) {
            throw new ConcurrentModificationException();
        }
        if (n2 == 0) {
            throw new NoSuchElementException();
        }
        this.categoryIndex = n2 - 1;
        this.unitIndex = a2.b - n2;
        return a2.c[this.unitIndex];
    }

    @Override
    public void remove() {
        AStarNode[] nArray = this.a.c;
        int n2 = this.unitIndex;
        if (com.corrodinggames.rts.gameFramework.pathfinding.UnitList.c(this.a) != this.totalUnits) {
            throw new ConcurrentModificationException();
        }
        if (n2 < 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(nArray, n2 + 1, nArray, n2, this.categoryIndex);
        nArray[--this.a.b] = null;
        this.unitIndex = -1;
        this.totalUnits = com.corrodinggames.rts.gameFramework.pathfinding.UnitList.d(this.a);
    }

    public /* synthetic */ Object next() {
        return this.a();
    }

    /* synthetic */ UnitListIterator(UnitList a2, UnitList$1 a3) {  // 02b k/b.java L64-66
        this(a2);
    }
}
