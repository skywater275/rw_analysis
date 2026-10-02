/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;

import network.reliableudp.ReliableSocket;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

class ReliableOutputStream
extends OutputStream {
    protected ReliableSocket a;
    protected byte[] b;
    protected int c;

    public ReliableOutputStream(ReliableSocket h2) throws SocketException {
        if (h2 == null) {
            throw new NullPointerException("sock");
        }
        this.a = h2;
        this.b = new byte[this.a.getSendBufferSize()];
        this.c = 0;
    }

    @Override
    public synchronized void write(int n) throws IOException {
        if (this.c >= this.b.length) {
            this.flush();
        }
        this.b[this.c++] = (byte)(n & 0xFF);
    }

    @Override
    public synchronized void write(byte[] byArray) throws IOException {
        this.write(byArray, 0, byArray.length);
    }

    @Override
    public synchronized void write(byte[] byArray, int n, int n2) throws IOException {
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
    public synchronized void flush() throws IOException {
        if (this.c > 0) {
            this.a.a(this.b, 0, this.c);
            this.c = 0;
        }
    }

    @Override
    public synchronized void close() throws IOException {
        this.flush();
        this.a.shutdownOutput();
    }
}
