/*
 * Decompiled with CFR 0.152.
 */
package android.util;

import android.util.a;

public class SparseArray
implements Cloneable {
    private static final Object a = new Object();
    private boolean b = false;
    private int[] c;
    private Object[] d;
    private int e;

    public SparseArray() {
        this(10);
    }

    public SparseArray(int n) {
        if (n == 0) {
            this.c = android.util.a.b;
            this.d = android.util.a.d;
        } else {
            n = com.a.a.a.a.c(n);
            this.c = new int[n];
            this.d = new Object[n];
        }
        this.e = 0;
    }

    public SparseArray a() {
        SparseArray sparseArray = null;
        try {
            sparseArray = (SparseArray)super.clone();
            sparseArray.c = (int[])this.c.clone();
            sparseArray.d = (Object[])this.d.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            // empty catch block
        }
        return sparseArray;
    }

    public Object a(int n) {
        return this.a(n, null);
    }

    public Object a(int n, Object object) {
        int n2 = android.util.a.a(this.c, this.e, n);
        if (n2 < 0 || this.d[n2] == a) {
            return object;
        }
        return this.d[n2];
    }

    private void c() {
        int n = this.e;
        int n2 = 0;
        int[] nArray = this.c;
        Object[] objectArray = this.d;
        for (int i = 0; i < n; ++i) {
            Object object = objectArray[i];
            if (object == a) continue;
            if (i != n2) {
                nArray[n2] = nArray[i];
                objectArray[n2] = object;
                objectArray[i] = null;
            }
            ++n2;
        }
        this.b = false;
        this.e = n2;
    }

    public void b(int n, Object object) {
        int n2 = android.util.a.a(this.c, this.e, n);
        if (n2 >= 0) {
            this.d[n2] = object;
        } else {
            if ((n2 ^= 0xFFFFFFFF) < this.e && this.d[n2] == a) {
                this.c[n2] = n;
                this.d[n2] = object;
                return;
            }
            if (this.b && this.e >= this.c.length) {
                this.c();
                n2 = ~android.util.a.a(this.c, this.e, n);
            }
            if (this.e >= this.c.length) {
                int n3 = com.a.a.a.a.c(this.e + 1);
                int[] nArray = new int[n3];
                Object[] objectArray = new Object[n3];
                System.arraycopy(this.c, 0, nArray, 0, this.c.length);
                System.arraycopy(this.d, 0, objectArray, 0, this.d.length);
                this.c = nArray;
                this.d = objectArray;
            }
            if (this.e - n2 != 0) {
                System.arraycopy(this.c, n2, this.c, n2 + 1, this.e - n2);
                System.arraycopy(this.d, n2, this.d, n2 + 1, this.e - n2);
            }
            this.c[n2] = n;
            this.d[n2] = object;
            ++this.e;
        }
    }

    public int b() {
        if (this.b) {
            this.c();
        }
        return this.e;
    }

    public int b(int n) {
        if (this.b) {
            this.c();
        }
        return this.c[n];
    }

    public Object c(int n) {
        if (this.b) {
            this.c();
        }
        return this.d[n];
    }

    public void c(int n, Object object) {
        int n2;
        if (this.e != 0 && n <= this.c[this.e - 1]) {
            this.b(n, object);
            return;
        }
        if (this.b && this.e >= this.c.length) {
            this.c();
        }
        if ((n2 = this.e) >= this.c.length) {
            int n3 = com.a.a.a.a.c(n2 + 1);
            int[] nArray = new int[n3];
            Object[] objectArray = new Object[n3];
            System.arraycopy(this.c, 0, nArray, 0, this.c.length);
            System.arraycopy(this.d, 0, objectArray, 0, this.d.length);
            this.c = nArray;
            this.d = objectArray;
        }
        this.c[n2] = n;
        this.d[n2] = object;
        this.e = n2 + 1;
    }

    public String toString() {
        if (this.b() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.e; ++i) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            int n = this.b(i);
            stringBuilder.append(n);
            stringBuilder.append('=');
            Object object = this.c(i);
            if (object != this) {
                stringBuilder.append(object);
                continue;
            }
            stringBuilder.append("(this Map)");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }
}
