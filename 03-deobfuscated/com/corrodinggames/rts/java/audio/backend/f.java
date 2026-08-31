/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.gameFramework.MusicPlayer;
import com.corrodinggames.rts.java.audio.backend.c;
import com.corrodinggames.rts.java.audio.backend.e;
import com.corrodinggames.rts.java.audio.backend.g;
import com.corrodinggames.rts.java.audio.backend.h;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class f
extends h
implements Iterable,
Iterator {
    private g f = new g();

    public f(e e2) {  // 02b a/f.java: public f(e var1) (AudioManager 幻觉)
        super(e2);
    }

    public g a() {
        if (!this.a) {
            throw new NoSuchElementException();
        }
        if (!this.e) {
            throw new c("#iterator() cannot be used nested.");
        }
        int[] nArray = this.b.b;
        if (this.c == -1) {
            this.f.a = 0;
            this.f.b = this.b.f;
        } else {
            this.f.a = nArray[this.c];
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
