/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.utility.UnitInstanceList;
import java.util.Iterator;

public class QueryResult
implements Iterable,
Iterator {
    int a;
    UnitInstance[] b;

    @Override
    public boolean hasNext() {
        return this.a > 0;
    }

    public UnitInstance reset() {
        --this.a;
        return this.b[this.a];
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not supported");
    }

    public Iterator iterator() {
        return this;
    }

    public void reset(UnitInstanceList u2) {
        this.b = u2.a();
        this.a = u2.b;
    }

    public /* synthetic */ Object next() {
        return this.reset();
    }
}
