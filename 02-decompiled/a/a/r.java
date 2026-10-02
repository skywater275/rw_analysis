/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import java.io.IOException;

public class r {
    public static final r a = new r();
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;

    public r() {
        try {
            this.a(32, 32, 300, 70, 0, 3, 3, 3, 2000, 600, 300);
        }
        catch (IOException iOException) {
            throw new RuntimeException("IOException on ReliableSocketProfile default:" + iOException);
        }
    }

    public r(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
        this.a(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);
    }

    private void a(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
        this.a("maxSendQueueSize", n, 1, 255);
        this.a("maxRecvQueueSize", n2, 1, 255);
        this.a("maxSegmentSize", n3, 22, 6535);
        this.a("maxOutstandingSegs", n4, 1, 255);
        this.a("maxRetrans", n5, 0, 255);
        this.a("maxCumulativeAcks", n6, 0, 255);
        this.a("maxOutOfSequence", n7, 0, 255);
        this.a("maxAutoReset", n8, 0, 255);
        this.a("nullSegmentTimeout", n9, 0, 65535);
        this.a("retransmissionTimeout", n10, 100, 65535);
        this.a("cumulativeAckTimeout", n11, 100, 65535);
        this.b = n;
        this.c = n2;
        this.d = n3;
        this.e = n4;
        this.f = n5;
        this.g = n6;
        this.h = n7;
        this.i = n8;
        this.j = n9;
        this.k = n10;
        this.l = n11;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    public int e() {
        return this.h;
    }

    public int f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.b).append(", ");
        stringBuilder.append(this.c).append(", ");
        stringBuilder.append(this.d).append(", ");
        stringBuilder.append(this.e).append(", ");
        stringBuilder.append(this.f).append(", ");
        stringBuilder.append(this.g).append(", ");
        stringBuilder.append(this.h).append(", ");
        stringBuilder.append(this.i).append(", ");
        stringBuilder.append(this.j).append(", ");
        stringBuilder.append(this.k).append(", ");
        stringBuilder.append(this.l);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void a(String string, int n, int n2, int n3) {
        if (n < n2 || n > n3) {
            throw new IOException(string + " out of range");
        }
    }
}
