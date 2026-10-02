/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayStream
extends OutputStream {
    public byte[] a;
    protected int b;

    public ByteArrayStream() {
        this.a = new byte[32];
    }

    public ByteArrayStream(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("size < 0");
        }
        this.a = new byte[n];
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    private void a(int n) {
        if (this.b + n <= this.a.length) {
            return;
        }
        byte[] byArray = new byte[(this.b + n) * 2];
        System.arraycopy(this.a, 0, byArray, 0, this.b);
        this.a = byArray;
    }

    public synchronized void a() {
        this.b = 0;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return new String(this.a, 0, this.b);
    }

    public static void a(int n, int n2, int n3) {
        if ((n2 | n3) < 0 || n2 > n || n - n2 < n3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public synchronized void write(byte[] byArray, int n, int n2) {
        ByteArrayStream.a(byArray.length, n, n2);
        if (n2 == 0) {
            return;
        }
        this.a(n2);
        System.arraycopy(byArray, n, this.a, this.b, n2);
        this.b += n2;
    }

    @Override
    public synchronized void write(int n) {
        if (this.b == this.a.length) {
            this.a(1);
        }
        this.a[this.b++] = (byte)n;
    }
}
