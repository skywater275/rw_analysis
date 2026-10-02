/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b;

public class w {
    private int[] a;
    private int b;

    public void a(int n) {
        if (this.a.length == this.b) {
            int[] nArray = new int[this.b + this.b];
            System.arraycopy(this.a, 0, nArray, 0, this.b);
            this.a = nArray;
        }
        this.a[this.b++] = n;
    }
}
