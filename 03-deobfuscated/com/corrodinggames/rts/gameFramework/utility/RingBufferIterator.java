/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.RingBuffer;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class RingBufferIterator
implements Iterator {
    private int currentIndex;
    private int remainingCount;
    private int d;
    final /* synthetic */ RingBuffer bufferRef;

    private RingBufferIterator(RingBuffer g2) {
        this.bufferRef = g2;
        this.currentIndex = RingBuffer.a(this.bufferRef);
        this.remainingCount = RingBuffer.b(this.bufferRef);
        this.d = -1;
    }

    // javap 铁证: 官方 h.class 有合成构造器 h(g, g$1) 供外部调用
    RingBufferIterator(RingBuffer g2, RingBuffer$1 ringBuffer$1) {
        this(g2);
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex != this.remainingCount;
    }

    public Object next() {
        if (this.currentIndex == this.remainingCount) {
            throw new NoSuchElementException();
        }
        Object object = RingBuffer.c(this.bufferRef)[this.currentIndex];
        if (RingBuffer.b(this.bufferRef) != this.remainingCount || object == null) {
            throw new ConcurrentModificationException();
        }
        this.d = this.currentIndex;
        this.currentIndex = this.currentIndex + 1 & RingBuffer.c(this.bufferRef).length - 1;
        return object;
    }

    @Override
    public void remove() {
        if (this.d < 0) {
            throw new IllegalStateException();
        }
        if (RingBuffer.a(this.bufferRef, this.d)) {
            this.currentIndex = this.currentIndex - 1 & RingBuffer.c(this.bufferRef).length - 1;
            this.remainingCount = RingBuffer.b(this.bufferRef);
        }
        this.d = -1;
    }
}
