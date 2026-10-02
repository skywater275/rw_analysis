/*
 * Decompiled with CFR 0.152.
 */
package a.a.a;

import a.a.a.h;
import java.io.IOException;

public class g
extends h {
    private int a;
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

    protected g() {
    }

    public g(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10) {
        this.a(-128, n, 22);
        this.a = 1;
        this.b = n2;
        this.c = 1;
        this.d = n3;
        this.e = n4;
        this.f = n5;
        this.g = n6;
        this.h = n7;
        this.i = n8;
        this.j = n9;
        this.k = n10;
    }

    @Override
    public String a() {
        return "SYN";
    }

    public int c() {
        return this.b;
    }

    public int e() {
        return this.d;
    }

    public int f() {
        return this.e;
    }

    public int g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    public int j() {
        return this.i;
    }

    public int k() {
        return this.j;
    }

    public int l() {
        return this.k;
    }

    @Override
    public byte[] d() {
        byte[] byArray = super.d();
        byArray[4] = (byte)(this.a << 4 & 0xFF);
        byArray[5] = (byte)(this.b & 0xFF);
        byArray[6] = (byte)(this.c & 0xFF);
        byArray[7] = 0;
        byArray[8] = (byte)(this.d >>> 8 & 0xFF);
        byArray[9] = (byte)(this.d >>> 0 & 0xFF);
        byArray[10] = (byte)(this.e >>> 8 & 0xFF);
        byArray[11] = (byte)(this.e >>> 0 & 0xFF);
        byArray[12] = (byte)(this.f >>> 8 & 0xFF);
        byArray[13] = (byte)(this.f >>> 0 & 0xFF);
        byArray[14] = (byte)(this.g >>> 8 & 0xFF);
        byArray[15] = (byte)(this.g >>> 0 & 0xFF);
        byArray[16] = (byte)(this.h & 0xFF);
        byArray[17] = (byte)(this.i & 0xFF);
        byArray[18] = (byte)(this.j & 0xFF);
        byArray[19] = (byte)(this.k & 0xFF);
        return byArray;
    }

    @Override
    protected void a(byte[] byArray, int n, int n2) {
        super.a(byArray, n, n2);
        if (n2 < 22) {
            throw new IOException("Invalid SYN segment");
        }
        this.a = (byArray[n + 4] & 0xFF) >>> 4;
        if (this.a != 1) {
            throw new IOException("Invalid RUDP version:" + this.a);
        }
        this.b = byArray[n + 5] & 0xFF;
        this.c = byArray[n + 6] & 0xFF;
        this.d = (byArray[n + 8] & 0xFF) << 8 | (byArray[n + 9] & 0xFF) << 0;
        this.e = (byArray[n + 10] & 0xFF) << 8 | (byArray[n + 11] & 0xFF) << 0;
        this.f = (byArray[n + 12] & 0xFF) << 8 | (byArray[n + 13] & 0xFF) << 0;
        this.g = (byArray[n + 14] & 0xFF) << 8 | (byArray[n + 15] & 0xFF) << 0;
        this.h = byArray[n + 16] & 0xFF;
        this.i = byArray[n + 17] & 0xFF;
        this.j = byArray[n + 18] & 0xFF;
        this.k = byArray[n + 19] & 0xFF;
    }
}
