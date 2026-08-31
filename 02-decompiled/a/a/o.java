/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.h;
import java.io.InputStream;

class o
extends InputStream {
    protected h a;
    protected byte[] b;
    protected int c;
    protected int d;

    public o(h h2) {
        if (h2 == null) {
            throw new NullPointerException("sock");
        }
        this.a = h2;
        this.b = new byte[this.a.getReceiveBufferSize()];
        this.d = 0;
        this.c = 0;
    }

    @Override
    public synchronized int read() {
        if (this.a() < 0) {
            return -1;
        }
        return this.b[this.c++] & 0xFF;
    }

    @Override
    public synchronized int read(byte[] byArray) {
        return this.read(byArray, 0, byArray.length);
    }

    @Override
    public synchronized int read(byte[] byArray, int n, int n2) {
        if (byArray == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n2 < 0 || n + n2 > byArray.length) {
            throw new IndexOutOfBoundsException();
        }
        if (this.a() < 0) {
            return -1;
        }
        int n3 = Math.min(this.available(), n2);
        System.arraycopy(this.b, this.c, byArray, n, n3);
        this.c += n3;
        return n3;
    }

    @Override
    public synchronized int available() {
        return this.d - this.c;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void close() {
        this.a.shutdownInput();
    }

    private int a() {
        if (this.available() == 0) {
            this.d = this.a.b(this.b, 0, this.b.length);
            this.c = 0;
        }
        return this.d;
    }
}
