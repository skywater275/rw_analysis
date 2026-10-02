/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

public class AudioClip {
    public float[] a;
    public int b;
    public boolean c;

    public AudioClip() {
        this(true, 16);
    }

    public AudioClip(int n) {
        this(true, n);
    }

    public AudioClip(boolean bl, int n) {
        this.c = bl;
        this.a = new float[n];
    }

    public void a(float f) {
        float[] fArray = this.a;
        if (this.b == fArray.length) {
            fArray = this.a(Math.max(8, (int)((float)this.b * 1.75f)));
        }
        fArray[this.b++] = f;
    }

    public void a(int n, float f) {
        if (n >= this.b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + n + " >= " + this.b);
        }
        this.a[n] = f;
    }

    public void b(int n, float f) {
        if (n > this.b) {
            throw new IndexOutOfBoundsException("index can't be > size: " + n + " > " + this.b);
        }
        float[] fArray = this.a;
        if (this.b == fArray.length) {
            fArray = this.a(Math.max(8, (int)((float)this.b * 1.75f)));
        }
        if (this.c) {
            System.arraycopy(fArray, n, fArray, n + 1, this.b - n);
        } else {
            fArray[this.b] = fArray[n];
        }
        ++this.b;
        fArray[n] = f;
    }

    public float a() {
        return this.a[--this.b];
    }

    public float b() {
        if (this.b == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.a[0];
    }

    public void c() {
        this.b = 0;
    }

    protected float[] a(int n) {
        float[] fArray = new float[n];
        float[] fArray2 = this.a;
        System.arraycopy(fArray2, 0, fArray, 0, Math.min(this.b, fArray.length));
        this.a = fArray;
        return fArray;
    }

    public int hashCode() {
        if (!this.c) {
            return super.hashCode();
        }
        float[] fArray = this.a;
        int n = 1;
        int n2 = this.b;
        for (int i = 0; i < n2; ++i) {
            n = n * 31 + Float.floatToIntBits(fArray[i]);
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
        if (!(object instanceof AudioClip)) {
            return false;
        }
        AudioClip b2 = (AudioClip) object;
        if (!b2.c) {
            return false;
        }
        int n = this.b;
        if (n != b2.b) {
            return false;
        }
        float[] fArray = this.a;
        float[] fArray2 = b2.a;
        for (int i = 0; i < n; ++i) {
            if (fArray[i] == fArray2[i]) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.b == 0) {
            return "[]";
        }
        float[] fArray = this.a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(fArray[0]);
        for (int i = 1; i < this.b; ++i) {
            stringBuilder.append(", ");
            stringBuilder.append(fArray[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
