/*
 * Decompiled with CFR 0.152.
 */
package a.a.a;

import a.a.a.a;

public class c
extends a {
    private int[] a;

    protected c() {
    }

    public c(int n, int n2, int[] nArray) {
        this.a(32, n, 6 + nArray.length);
        this.a(n2);
        this.a = nArray;
    }

    @Override
    public String a() {
        return "EAK";
    }

    public int[] c() {
        return this.a;
    }

    @Override
    public byte[] d() {
        byte[] byArray = super.d();
        for (int i = 0; i < this.a.length; ++i) {
            byArray[4 + i] = (byte)(this.a[i] & 0xFF);
        }
        return byArray;
    }

    @Override
    protected void a(byte[] byArray, int n, int n2) {
        super.a(byArray, n, n2);
        this.a = new int[n2 - 6];
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = byArray[n + 4 + i] & 0xFF;
        }
    }
}
