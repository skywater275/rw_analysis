/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.o;
import com.corrodinggames.rts.java.audio.a.q;
import com.corrodinggames.rts.java.audio.a.r;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class p
extends r {
    q a = new q();

    public p(o o2) {
        super(o2);
    }

    public q a() {
        if (!this.b) {
            throw new NoSuchElementException();
        }
        if (!this.f) {
            throw new c("#iterator() cannot be used nested.");
        }
        Object[] objectArray = this.c.b;
        this.a.a = objectArray[this.d];
        this.a.b = this.c.c[this.d];
        this.e = this.d;
        this.d();
        return this.a;
    }

    @Override
    public boolean hasNext() {
        if (!this.f) {
            throw new c("#iterator() cannot be used nested.");
        }
        return this.b;
    }

    public p b() {
        return this;
    }

    public /* synthetic */ Iterator iterator() {
        return this.b();
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
