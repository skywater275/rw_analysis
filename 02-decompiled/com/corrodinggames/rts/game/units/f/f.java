/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.util.Iterator;

public class f
implements Iterable,
Iterator {
    int a;
    am[] b;

    @Override
    public boolean hasNext() {
        return this.a > 0;
    }

    public am a() {
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

    public void a(u u2) {
        this.b = u2.a();
        this.a = u2.b;
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
