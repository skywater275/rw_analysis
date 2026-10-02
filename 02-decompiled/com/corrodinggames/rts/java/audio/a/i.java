/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.j;
import com.corrodinggames.rts.java.audio.a.m;
import java.util.Iterator;

public class i
implements Iterable {
    public int a;
    long[] b;
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
    private j n;
    private j o;

    public i() {
        this(51, 0.8f);
    }

    public i(int n, float f) {
        if (n < 0) {
            throw new IllegalArgumentException("initialCapacity must be >= 0: " + n);
        }
        if ((n = com.corrodinggames.rts.java.audio.a.m.b((int)Math.ceil((float)n / f))) > 0x40000000) {
            throw new IllegalArgumentException("initialCapacity is too large: " + n);
        }
        this.d = n;
        if (f <= 0.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0: " + f);
        }
        this.h = f;
        this.k = (int)((float)this.d * f);
        this.j = this.d - 1;
        this.i = 63 - Long.numberOfTrailingZeros(this.d);
        this.l = Math.max(3, (int)Math.ceil(Math.log(this.d)) * 2);
        this.m = Math.max(Math.min(this.d, 8), (int)Math.sqrt(this.d) / 8);
        this.b = new long[this.d + this.l];
        this.c = new Object[this.b.length];
    }

    public Object a(long l, Object object) {
        int n;
        if (l == 0L) {
            Object object2 = this.f;
            this.f = object;
            if (!this.g) {
                this.g = true;
                ++this.a;
            }
            return object2;
        }
        long[] lArray = this.b;
        int n2 = (int)(l & (long)this.j);
        long l2 = lArray[n2];
        if (l2 == l) {
            Object object3 = this.c[n2];
            this.c[n2] = object;
            return object3;
        }
        int n3 = this.f(l);
        long l3 = lArray[n3];
        if (l3 == l) {
            Object object4 = this.c[n3];
            this.c[n3] = object;
            return object4;
        }
        int n4 = this.g(l);
        long l4 = lArray[n4];
        if (l4 == l) {
            Object object5 = this.c[n4];
            this.c[n4] = object;
            return object5;
        }
        int n5 = n + this.e;
        for (n = this.d; n < n5; ++n) {
            if (lArray[n] != l) continue;
            Object object6 = this.c[n];
            this.c[n] = object;
            return object6;
        }
        if (l2 == 0L) {
            lArray[n2] = l;
            this.c[n2] = object;
            if (this.a++ >= this.k) {
                this.b(this.d << 1);
            }
            return null;
        }
        if (l3 == 0L) {
            lArray[n3] = l;
            this.c[n3] = object;
            if (this.a++ >= this.k) {
                this.b(this.d << 1);
            }
            return null;
        }
        if (l4 == 0L) {
            lArray[n4] = l;
            this.c[n4] = object;
            if (this.a++ >= this.k) {
                this.b(this.d << 1);
            }
            return null;
        }
        this.a(l, object, n2, l2, n3, l3, n4, l4);
        return null;
    }

    private void b(long l, Object object) {
        if (l == 0L) {
            this.f = object;
            this.g = true;
            return;
        }
        int n = (int)(l & (long)this.j);
        long l2 = this.b[n];
        if (l2 == 0L) {
            this.b[n] = l;
            this.c[n] = object;
            if (this.a++ >= this.k) {
                this.b(this.d << 1);
            }
            return;
        }
        int n2 = this.f(l);
        long l3 = this.b[n2];
        if (l3 == 0L) {
            this.b[n2] = l;
            this.c[n2] = object;
            if (this.a++ >= this.k) {
                this.b(this.d << 1);
            }
            return;
        }
        int n3 = this.g(l);
        long l4 = this.b[n3];
        if (l4 == 0L) {
            this.b[n3] = l;
            this.c[n3] = object;
            if (this.a++ >= this.k) {
                this.b(this.d << 1);
            }
            return;
        }
        this.a(l, object, n, l2, n2, l3, n3, l4);
    }

    private void a(long l, Object object, int n, long l2, int n2, long l3, int n3, long l4) {
        Object object2;
        long l5;
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n4 = this.j;
        int n5 = 0;
        int n6 = this.m;
        while (true) {
            switch (com.corrodinggames.rts.java.audio.a.m.a(2)) {
                case 0: {
                    l5 = l2;
                    object2 = objectArray[n];
                    lArray[n] = l;
                    objectArray[n] = object;
                    break;
                }
                case 1: {
                    l5 = l3;
                    object2 = objectArray[n2];
                    lArray[n2] = l;
                    objectArray[n2] = object;
                    break;
                }
                default: {
                    l5 = l4;
                    object2 = objectArray[n3];
                    lArray[n3] = l;
                    objectArray[n3] = object;
                }
            }
            n = (int)(l5 & (long)n4);
            l2 = lArray[n];
            if (l2 == 0L) {
                lArray[n] = l5;
                objectArray[n] = object2;
                if (this.a++ >= this.k) {
                    this.b(this.d << 1);
                }
                return;
            }
            n2 = this.f(l5);
            l3 = lArray[n2];
            if (l3 == 0L) {
                lArray[n2] = l5;
                objectArray[n2] = object2;
                if (this.a++ >= this.k) {
                    this.b(this.d << 1);
                }
                return;
            }
            n3 = this.g(l5);
            l4 = lArray[n3];
            if (l4 == 0L) {
                lArray[n3] = l5;
                objectArray[n3] = object2;
                if (this.a++ >= this.k) {
                    this.b(this.d << 1);
                }
                return;
            }
            if (++n5 == n6) break;
            l = l5;
            object = object2;
        }
        this.c(l5, object2);
    }

    private void c(long l, Object object) {
        if (this.e == this.l) {
            this.b(this.d << 1);
            this.a(l, object);
            return;
        }
        int n = this.d + this.e;
        this.b[n] = l;
        this.c[n] = object;
        ++this.e;
        ++this.a;
    }

    public Object a(long l) {
        if (l == 0L) {
            if (!this.g) {
                return null;
            }
            return this.f;
        }
        int n = (int)(l & (long)this.j);
        if (this.b[n] != l && this.b[n = this.f(l)] != l && this.b[n = this.g(l)] != l) {
            return this.d(l, null);
        }
        return this.c[n];
    }

    private Object d(long l, Object object) {
        int n;
        long[] lArray = this.b;
        int n2 = n + this.e;
        for (n = this.d; n < n2; ++n) {
            if (lArray[n] != l) continue;
            return this.c[n];
        }
        return object;
    }

    public Object b(long l) {
        if (l == 0L) {
            if (!this.g) {
                return null;
            }
            Object object = this.f;
            this.f = null;
            this.g = false;
            --this.a;
            return object;
        }
        int n = (int)(l & (long)this.j);
        if (this.b[n] == l) {
            this.b[n] = 0L;
            Object object = this.c[n];
            this.c[n] = null;
            --this.a;
            return object;
        }
        n = this.f(l);
        if (this.b[n] == l) {
            this.b[n] = 0L;
            Object object = this.c[n];
            this.c[n] = null;
            --this.a;
            return object;
        }
        n = this.g(l);
        if (this.b[n] == l) {
            this.b[n] = 0L;
            Object object = this.c[n];
            this.c[n] = null;
            --this.a;
            return object;
        }
        return this.c(l);
    }

    Object c(long l) {
        int n;
        long[] lArray = this.b;
        int n2 = n + this.e;
        for (n = this.d; n < n2; ++n) {
            if (lArray[n] != l) continue;
            Object object = this.c[n];
            this.a(n);
            --this.a;
            return object;
        }
        return null;
    }

    void a(int n) {
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
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n = this.d + this.e;
        while (n-- > 0) {
            lArray[n] = 0L;
            objectArray[n] = null;
        }
        this.a = 0;
        this.e = 0;
        this.f = null;
        this.g = false;
    }

    public boolean d(long l) {
        if (l == 0L) {
            return this.g;
        }
        int n = (int)(l & (long)this.j);
        if (this.b[n] != l && this.b[n = this.f(l)] != l && this.b[n = this.g(l)] != l) {
            return this.e(l);
        }
        return true;
    }

    private boolean e(long l) {
        int n;
        long[] lArray = this.b;
        int n2 = n + this.e;
        for (n = this.d; n < n2; ++n) {
            if (lArray[n] != l) continue;
            return true;
        }
        return false;
    }

    private void b(int n) {
        int n2 = this.d + this.e;
        this.d = n;
        this.k = (int)((float)n * this.h);
        this.j = n - 1;
        this.i = 63 - Long.numberOfTrailingZeros(n);
        this.l = Math.max(3, (int)Math.ceil(Math.log(n)) * 2);
        this.m = Math.max(Math.min(n, 8), (int)Math.sqrt(n) / 8);
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        this.b = new long[n + this.l];
        this.c = new Object[n + this.l];
        int n3 = this.a;
        this.a = this.g ? 1 : 0;
        this.e = 0;
        if (n3 > 0) {
            for (int k = 0; k < n2; ++k) {
                long l = lArray[k];
                if (l == 0L) continue;
                this.b(l, objectArray[k]);
            }
        }
    }

    private int f(long l) {
        return (int)(((l *= -1262997959L) ^ l >>> this.i) & (long)this.j);
    }

    private int g(long l) {
        return (int)(((l *= -825114047L) ^ l >>> this.i) & (long)this.j);
    }

    public int hashCode() {
        int n = 0;
        if (this.g && this.f != null) {
            n += this.f.hashCode();
        }
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n2 = this.d + this.e;
        for (int k = 0; k < n2; ++k) {
            long l = lArray[k];
            if (l == 0L) continue;
            n += (int)(l ^ l >>> 32) * 31;
            Object object = objectArray[k];
            if (object == null) continue;
            n += object.hashCode();
        }
        return n;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof i)) {
            return false;
        }
        i i2 = (i)object;
        if (i2.a != this.a) {
            return false;
        }
        if (i2.g != this.g) {
            return false;
        }
        if (this.g && (i2.f == null ? this.f != null : !i2.f.equals(this.f))) {
            return false;
        }
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n = this.d + this.e;
        for (int k = 0; k < n; ++k) {
            Object object2;
            long l = lArray[k];
            if (l == 0L || !((object2 = objectArray[k]) == null ? !i2.d(l) || i2.a(l) != null : !object2.equals(i2.a(l)))) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        long l;
        if (this.a == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        long[] lArray = this.b;
        Object[] objectArray = this.c;
        int n = lArray.length;
        while (n-- > 0) {
            l = lArray[n];
            if (l == 0L) continue;
            stringBuilder.append(l);
            stringBuilder.append('=');
            stringBuilder.append(objectArray[n]);
            break;
        }
        while (n-- > 0) {
            l = lArray[n];
            if (l == 0L) continue;
            stringBuilder.append(", ");
            stringBuilder.append(l);
            stringBuilder.append('=');
            stringBuilder.append(objectArray[n]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Iterator iterator() {
        return this.b();
    }

    public j b() {
        if (this.n == null) {
            this.n = new j(this);
            this.o = new j(this);
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
