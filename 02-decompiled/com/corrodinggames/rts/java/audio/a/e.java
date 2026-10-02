/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.f;
import com.corrodinggames.rts.java.audio.a.m;
import java.util.Iterator;

public class e
implements Iterable {
    public int a;
    int[] b;
    Object[] c;
    int d;
    int e;
    Object f;
    boolean g;
    private float h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private f n;
    private f o;

    public e() {
        this(51, 0.8f);
    }

    public e(int n, float f2) {
        if (n < 0) {
            throw new IllegalArgumentException("initialCapacity must be >= 0: " + n);
        }
        if ((n = com.corrodinggames.rts.java.audio.a.m.b((int)Math.ceil((float)n / f2))) > 0x40000000) {
            throw new IllegalArgumentException("initialCapacity is too large: " + n);
        }
        this.d = n;
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0: " + f2);
        }
        this.h = f2;
        this.k = (int)((float)this.d * f2);
        this.j = this.d - 1;
        this.i = 31 - Integer.numberOfTrailingZeros(this.d);
        this.l = Math.max(3, (int)Math.ceil(Math.log(this.d)) * 2);
        this.m = Math.max(Math.min(this.d, 8), (int)Math.sqrt(this.d) / 8);
        this.b = new int[this.d + this.l];
        this.c = new Object[this.b.length];
    }

    public Object a(int n, Object object) {
        int n2;
        if (n == 0) {
            Object object2 = this.f;
            this.f = object;
            if (!this.g) {
                this.g = true;
                ++this.a;
            }
            return object2;
        }
        int[] nArray = this.b;
        int n3 = n & this.j;
        int n4 = nArray[n3];
        if (n4 == n) {
            Object object3 = this.c[n3];
            this.c[n3] = object;
            return object3;
        }
        int n5 = this.h(n);
        int n6 = nArray[n5];
        if (n6 == n) {
            Object object4 = this.c[n5];
            this.c[n5] = object;
            return object4;
        }
        int n7 = this.i(n);
        int n8 = nArray[n7];
        if (n8 == n) {
            Object object5 = this.c[n7];
            this.c[n7] = object;
            return object5;
        }
        int n9 = n2 + this.e;
        for (n2 = this.d; n2 < n9; ++n2) {
            if (nArray[n2] != n) continue;
            Object object6 = this.c[n2];
            this.c[n2] = object;
            return object6;
        }
        if (n4 == 0) {
            nArray[n3] = n;
            this.c[n3] = object;
            if (this.a++ >= this.k) {
                this.g(this.d << 1);
            }
            return null;
        }
        if (n6 == 0) {
            nArray[n5] = n;
            this.c[n5] = object;
            if (this.a++ >= this.k) {
                this.g(this.d << 1);
            }
            return null;
        }
        if (n8 == 0) {
            nArray[n7] = n;
            this.c[n7] = object;
            if (this.a++ >= this.k) {
                this.g(this.d << 1);
            }
            return null;
        }
        this.a(n, object, n3, n4, n5, n6, n7, n8);
        return null;
    }

    private void b(int n, Object object) {
        if (n == 0) {
            this.f = object;
            this.g = true;
            return;
        }
        int n2 = n & this.j;
        int n3 = this.b[n2];
        if (n3 == 0) {
            this.b[n2] = n;
            this.c[n2] = object;
            if (this.a++ >= this.k) {
                this.g(this.d << 1);
            }
            return;
        }
        int n4 = this.h(n);
        int n5 = this.b[n4];
        if (n5 == 0) {
            this.b[n4] = n;
            this.c[n4] = object;
            if (this.a++ >= this.k) {
                this.g(this.d << 1);
            }
            return;
        }
        int n6 = this.i(n);
        int n7 = this.b[n6];
        if (n7 == 0) {
            this.b[n6] = n;
            this.c[n6] = object;
            if (this.a++ >= this.k) {
                this.g(this.d << 1);
            }
            return;
        }
        this.a(n, object, n2, n3, n4, n5, n6, n7);
    }

    private void a(int n, Object object, int n2, int n3, int n4, int n5, int n6, int n7) {
        Object object2;
        int n8;
        int[] nArray = this.b;
        Object[] objectArray = this.c;
        int n9 = this.j;
        int n10 = 0;
        int n11 = this.m;
        while (true) {
            switch (com.corrodinggames.rts.java.audio.a.m.a(2)) {
                case 0: {
                    n8 = n3;
                    object2 = objectArray[n2];
                    nArray[n2] = n;
                    objectArray[n2] = object;
                    break;
                }
                case 1: {
                    n8 = n5;
                    object2 = objectArray[n4];
                    nArray[n4] = n;
                    objectArray[n4] = object;
                    break;
                }
                default: {
                    n8 = n7;
                    object2 = objectArray[n6];
                    nArray[n6] = n;
                    objectArray[n6] = object;
                }
            }
            n2 = n8 & n9;
            n3 = nArray[n2];
            if (n3 == 0) {
                nArray[n2] = n8;
                objectArray[n2] = object2;
                if (this.a++ >= this.k) {
                    this.g(this.d << 1);
                }
                return;
            }
            n4 = this.h(n8);
            n5 = nArray[n4];
            if (n5 == 0) {
                nArray[n4] = n8;
                objectArray[n4] = object2;
                if (this.a++ >= this.k) {
                    this.g(this.d << 1);
                }
                return;
            }
            n6 = this.i(n8);
            n7 = nArray[n6];
            if (n7 == 0) {
                nArray[n6] = n8;
                objectArray[n6] = object2;
                if (this.a++ >= this.k) {
                    this.g(this.d << 1);
                }
                return;
            }
            if (++n10 == n11) break;
            n = n8;
            object = object2;
        }
        this.c(n8, object2);
    }

    private void c(int n, Object object) {
        if (this.e == this.l) {
            this.g(this.d << 1);
            this.a(n, object);
            return;
        }
        int n2 = this.d + this.e;
        this.b[n2] = n;
        this.c[n2] = object;
        ++this.e;
        ++this.a;
    }

    public Object a(int n) {
        if (n == 0) {
            if (!this.g) {
                return null;
            }
            return this.f;
        }
        int n2 = n & this.j;
        if (this.b[n2] != n && this.b[n2 = this.h(n)] != n && this.b[n2 = this.i(n)] != n) {
            return this.d(n, null);
        }
        return this.c[n2];
    }

    private Object d(int n, Object object) {
        int n2;
        int[] nArray = this.b;
        int n3 = n2 + this.e;
        for (n2 = this.d; n2 < n3; ++n2) {
            if (nArray[n2] != n) continue;
            return this.c[n2];
        }
        return object;
    }

    public Object b(int n) {
        if (n == 0) {
            if (!this.g) {
                return null;
            }
            Object object = this.f;
            this.f = null;
            this.g = false;
            --this.a;
            return object;
        }
        int n2 = n & this.j;
        if (this.b[n2] == n) {
            this.b[n2] = 0;
            Object object = this.c[n2];
            this.c[n2] = null;
            --this.a;
            return object;
        }
        n2 = this.h(n);
        if (this.b[n2] == n) {
            this.b[n2] = 0;
            Object object = this.c[n2];
            this.c[n2] = null;
            --this.a;
            return object;
        }
        n2 = this.i(n);
        if (this.b[n2] == n) {
            this.b[n2] = 0;
            Object object = this.c[n2];
            this.c[n2] = null;
            --this.a;
            return object;
        }
        return this.c(n);
    }

    Object c(int n) {
        int n2;
        int[] nArray = this.b;
        int n3 = n2 + this.e;
        for (n2 = this.d; n2 < n3; ++n2) {
            if (nArray[n2] != n) continue;
            Object object = this.c[n2];
            this.d(n2);
            --this.a;
            return object;
        }
        return null;
    }

    void d(int n) {
        --this.e;
        int n2 = this.d + this.e;
        if (n < n2) {
            this.b[n] = this.b[n2];
            this.c[n] = this.c[n2];
            this.c[n2] = null;
        } else {
            this.c[n] = null;
        }
    }

    public void a() {
        if (this.a == 0) {
            return;
        }
        int[] nArray = this.b;
        Object[] objectArray = this.c;
        int n = this.d + this.e;
        while (n-- > 0) {
            nArray[n] = 0;
            objectArray[n] = null;
        }
        this.a = 0;
        this.e = 0;
        this.f = null;
        this.g = false;
    }

    public boolean e(int n) {
        if (n == 0) {
            return this.g;
        }
        int n2 = n & this.j;
        if (this.b[n2] != n && this.b[n2 = this.h(n)] != n && this.b[n2 = this.i(n)] != n) {
            return this.f(n);
        }
        return true;
    }

    private boolean f(int n) {
        int n2;
        int[] nArray = this.b;
        int n3 = n2 + this.e;
        for (n2 = this.d; n2 < n3; ++n2) {
            if (nArray[n2] != n) continue;
            return true;
        }
        return false;
    }

    private void g(int n) {
        int n2 = this.d + this.e;
        this.d = n;
        this.k = (int)((float)n * this.h);
        this.j = n - 1;
        this.i = 31 - Integer.numberOfTrailingZeros(n);
        this.l = Math.max(3, (int)Math.ceil(Math.log(n)) * 2);
        this.m = Math.max(Math.min(n, 8), (int)Math.sqrt(n) / 8);
        int[] nArray = this.b;
        Object[] objectArray = this.c;
        this.b = new int[n + this.l];
        this.c = new Object[n + this.l];
        int n3 = this.a;
        this.a = this.g ? 1 : 0;
        this.e = 0;
        if (n3 > 0) {
            for (int i = 0; i < n2; ++i) {
                int n4 = nArray[i];
                if (n4 == 0) continue;
                this.b(n4, objectArray[i]);
            }
        }
    }

    private int h(int n) {
        return ((n *= -1262997959) ^ n >>> this.i) & this.j;
    }

    private int i(int n) {
        return ((n *= -825114047) ^ n >>> this.i) & this.j;
    }

    public int hashCode() {
        int n = 0;
        if (this.g && this.f != null) {
            n += this.f.hashCode();
        }
        int[] nArray = this.b;
        Object[] objectArray = this.c;
        int n2 = this.d + this.e;
        for (int i = 0; i < n2; ++i) {
            int n3 = nArray[i];
            if (n3 == 0) continue;
            n += n3 * 31;
            Object object = objectArray[i];
            if (object == null) continue;
            n += object.hashCode();
        }
        return n;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof e)) {
            return false;
        }
        e e2 = (e)object;
        if (e2.a != this.a) {
            return false;
        }
        if (e2.g != this.g) {
            return false;
        }
        if (this.g && (e2.f == null ? this.f != null : !e2.f.equals(this.f))) {
            return false;
        }
        int[] nArray = this.b;
        Object[] objectArray = this.c;
        int n = this.d + this.e;
        for (int i = 0; i < n; ++i) {
            Object object2;
            int n2 = nArray[i];
            if (n2 == 0 || !((object2 = objectArray[i]) == null ? !e2.e(n2) || e2.a(n2) != null : !object2.equals(e2.a(n2)))) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        int n;
        if (this.a == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        int[] nArray = this.b;
        Object[] objectArray = this.c;
        int n2 = nArray.length;
        if (this.g) {
            stringBuilder.append("0=");
            stringBuilder.append(this.f);
        } else {
            while (n2-- > 0) {
                n = nArray[n2];
                if (n == 0) continue;
                stringBuilder.append(n);
                stringBuilder.append('=');
                stringBuilder.append(objectArray[n2]);
                break;
            }
        }
        while (n2-- > 0) {
            n = nArray[n2];
            if (n == 0) continue;
            stringBuilder.append(", ");
            stringBuilder.append(n);
            stringBuilder.append('=');
            stringBuilder.append(objectArray[n2]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Iterator iterator() {
        return this.b();
    }

    public f b() {
        if (this.n == null) {
            this.n = new f(this);
            this.o = new f(this);
        }
        if (!this.n.e) {
            this.n.b();
            this.n.e = true;
            this.o.e = false;
            return this.n;
        }
        this.o.b();
        this.o.e = true;
        this.n.e = false;
        return this.o;
    }
}
