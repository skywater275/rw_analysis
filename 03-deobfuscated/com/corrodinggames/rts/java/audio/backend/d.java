/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

public class d {
    public int[] a;
    public int b;
    public boolean c;

    public d() {
        this(true, 16);
    }

    public d(boolean bl, int n) {
        this.c = bl;
        this.a = new int[n];
    }

    public d(d d2) {
        this.c = d2.c;
        this.b = d2.b;
        this.a = new int[this.b];
        System.arraycopy(d2.a, 0, this.a, 0, this.b);
    }

    public void a(int n) {
        int[] nArray = this.a;
        if (this.b == nArray.length) {
            nArray = this.d(Math.max(8, (int)((float)this.b * 1.75f)));
        }
        nArray[this.b++] = n;
    }

    public int b(int n) {
        if (n >= this.b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + n + " >= " + this.b);
        }
        return this.a[n];
    }

    public int c(int n) {
        if (n >= this.b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + n + " >= " + this.b);
        }
        int[] nArray = this.a;
        int n2 = nArray[n];
        --this.b;
        if (this.c) {
            System.arraycopy(nArray, n + 1, nArray, n, this.b - n);
        } else {
            nArray[n] = nArray[this.b];
        }
        return n2;
    }

    protected int[] d(int n) {
        int[] nArray = new int[n];
        int[] nArray2 = this.a;
        System.arraycopy(nArray2, 0, nArray, 0, Math.min(this.b, nArray.length));
        this.a = nArray;
        return nArray;
    }

    public int hashCode() {
        if (!this.c) {
            return super.hashCode();
        }
        int[] nArray = this.a;
        int n = 1;
        int n2 = this.b;
        for (int i = 0; i < n2; ++i) {
            n = n * 31 + nArray[i];
        }
        return n;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!this.c) {
            return false;
        }
        if (!(object instanceof d)) {
            return false;
        }
        d d2 = (d)object;
        if (!d2.c) {
            return false;
        }
        int n = this.b;
        if (n != d2.b) {
            return false;
        }
        int[] nArray = this.a;
        int[] nArray2 = d2.a;
        for (int i = 0; i < n; ++i) {
            if (this.a[i] == d2.a[i]) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.b == 0) {
            return "[]";
        }
        int[] nArray = this.a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(nArray[0]);
        for (int i = 1; i < this.b; ++i) {
            stringBuilder.append(", ");
            stringBuilder.append(nArray[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
