/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.h;
import java.io.OutputStream;

class q
extends OutputStream {
    protected h a;
    protected byte[] b;
    protected int c;

    public q(h h2) {
        if (h2 == null) {
            throw new NullPointerException("sock");
        }
        this.a = h2;
        this.b = new byte[this.a.getSendBufferSize()];
        this.c = 0;
    }

    @Override
    public synchronized void write(int n) {
        if (this.c >= this.b.length) {
            this.flush();
        }
        this.b[this.c++] = (byte)(n & 0xFF);
    }

    @Override
    public synchronized void write(byte[] byArray) {
        this.write(byArray, 0, byArray.length);
    }

    @Override
    public synchronized void write(byte[] byArray, int n, int n2) {
        int n3;
        if (byArray == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n2 < 0 || n + n2 > byArray.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int j = 0; j < n2; j += n3) {
            n3 = Math.min(this.b.length, n2 - j);
            if (n3 > this.b.length - this.c) {
                this.flush();
            }
            System.arraycopy(byArray, n + j, this.b, this.c, n3);
            this.c += n3;
        }
    }

    @Override
    public synchronized void flush() {
        if (this.c > 0) {
            this.a.a(this.b, 0, this.c);
            this.c = 0;
        }
    }

    @Override
    public synchronized void close() {
        this.flush();
        this.a.shutdownOutput();
    }
}
