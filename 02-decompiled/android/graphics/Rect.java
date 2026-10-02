/*
 * Decompiled with CFR 0.152.
 */
package android.graphics;

public final class Rect {
    public int a;
    public int b;
    public int c;
    public int d;

    public Rect() {
    }

    public Rect(int n, int n2, int n3, int n4) {
        this.a = n;
        this.b = n2;
        this.c = n3;
        this.d = n4;
    }

    public Rect(Rect rect) {
        this.a = rect.a;
        this.b = rect.b;
        this.c = rect.c;
        this.d = rect.d;
    }

    public boolean equals(Object object) {
        Rect rect = (Rect)object;
        if (rect != null) {
            return this.a == rect.a && this.b == rect.b && this.c == rect.c && this.d == rect.d;
        }
        return false;
    }

    public String toString() {
        return "Rect(" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ")";
    }

    public final boolean a() {
        return this.a >= this.c || this.b >= this.d;
    }

    public final int b() {
        return this.c - this.a;
    }

    public final int c() {
        return this.d - this.b;
    }

    public final int d() {
        return this.a + this.c >> 1;
    }

    public final int e() {
        return this.b + this.d >> 1;
    }

    public final float f() {
        return (float)(this.a + this.c) * 0.5f;
    }

    public final float g() {
        return (float)(this.b + this.d) * 0.5f;
    }

    public void h() {
        this.d = 0;
        this.b = 0;
        this.c = 0;
        this.a = 0;
    }

    public void a(int n, int n2, int n3, int n4) {
        this.a = n;
        this.b = n2;
        this.c = n3;
        this.d = n4;
    }

    public void a(Rect rect) {
        this.a = rect.a;
        this.b = rect.b;
        this.c = rect.c;
        this.d = rect.d;
    }

    public void a(int n, int n2) {
        this.a += n;
        this.b += n2;
        this.c += n;
        this.d += n2;
    }

    public boolean b(int n, int n2) {
        return this.a < this.c && this.b < this.d && n >= this.a && n < this.c && n2 >= this.b && n2 < this.d;
    }

    public boolean b(Rect rect) {
        return this.a < this.c && this.b < this.d && this.a <= rect.a && this.b <= rect.b && this.c >= rect.c && this.d >= rect.d;
    }

    public boolean b(int n, int n2, int n3, int n4) {
        return this.a < n3 && n < this.c && this.b < n4 && n2 < this.d;
    }
}
