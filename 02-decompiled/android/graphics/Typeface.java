/*
 * Decompiled with CFR 0.152.
 */
package android.graphics;

import android.util.SparseArray;

public class Typeface {
    public static final Typeface a;
    public static final Typeface b;
    public static final Typeface c;
    public static final Typeface d;
    public static final Typeface e;
    static Typeface[] f;
    private static final SparseArray i;
    int g;
    private int j = 0;
    String h;

    public final boolean a() {
        return (this.j & 1) != 0;
    }

    public static Typeface a(String string, int n) {
        Typeface typeface = new Typeface(0);
        typeface.j = n;
        typeface.h = string;
        return typeface;
    }

    public static Typeface a(Typeface typeface, int n) {
        Typeface typeface2;
        SparseArray sparseArray;
        int n2 = 0;
        if (typeface != null) {
            if (typeface.j == n) {
                return typeface;
            }
            n2 = typeface.g;
        }
        if ((sparseArray = (SparseArray)i.a(n2)) != null && (typeface2 = (Typeface)sparseArray.a(n)) != null) {
            return typeface2;
        }
        typeface2 = new Typeface(0);
        typeface2.j = n;
        typeface2.h = typeface.h;
        if (sparseArray == null) {
            sparseArray = new SparseArray(4);
            i.b(n2, sparseArray);
        }
        sparseArray.b(n, typeface2);
        return typeface2;
    }

    private Typeface(int n) {
        this.g = n;
        this.j = Typeface.b(n);
    }

    protected void finalize() {
        try {
            Typeface.a(this.g);
        }
        finally {
            super.finalize();
        }
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Typeface typeface = (Typeface)object;
        return this.j == typeface.j && this.g == typeface.g;
    }

    public int hashCode() {
        int n = this.g;
        n = 31 * n + this.j;
        return n;
    }

    private static void a(int n) {
    }

    private static int b(int n) {
        return 0;
    }

    static {
        i = new SparseArray(3);
        a = Typeface.a((String)null, 0);
        b = Typeface.a((String)null, 1);
        c = Typeface.a("sans-serif", 0);
        d = Typeface.a("serif", 0);
        e = Typeface.a("monospace", 0);
        f = new Typeface[]{a, b, Typeface.a((String)null, 2), Typeface.a((String)null, 3)};
    }
}
