/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.java.audio.backend.c;
import com.corrodinggames.rts.java.audio.backend.AudioManager;
import com.corrodinggames.rts.java.audio.backend.k;
import com.corrodinggames.rts.java.audio.backend.l;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class j
extends l
implements Iterable,
Iterator {
    private k f = new k();

    public j(AudioManager i2) {
        super(i2);
    }

    public k a() {
        if (!this.a) {
            throw new NoSuchElementException();
        }
        if (!this.e) {
            throw new c("#iterator() cannot be used nested.");
        }
        long[] lArray = this.b.b;
        if (this.c == -1) {
            this.f.a = 0L;
            this.f.b = this.b.f;
        } else {
            this.f.a = lArray[this.c];
            this.f.b = this.b.c[this.c];
        }
        this.d = this.c;
        this.c();
        return this.f;
    }

    @Override
    public boolean hasNext() {
        if (!this.e) {
            throw new c("#iterator() cannot be used nested.");
        }
        return this.a;
    }

    public Iterator iterator() {
        return this;
    }

    @Override
    public void remove() {
        super.remove();
    }

    public /* synthetic */ Object next() {
        return this.a();
    }
}
