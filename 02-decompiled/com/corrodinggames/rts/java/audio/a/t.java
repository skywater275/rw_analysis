/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.a;

import java.io.ByteArrayOutputStream;

public class t
extends ByteArrayOutputStream {
    public t(int n) {
        super(n);
    }

    @Override
    public synchronized byte[] toByteArray() {
        if (this.count == this.buf.length) {
            return this.buf;
        }
        return super.toByteArray();
    }
}
