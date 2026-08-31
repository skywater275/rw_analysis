/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.backend.m;
import com.corrodinggames.rts.java.audio.backend.p;
import java.util.Iterator;

public class o
implements Iterable {
    public int a;
    Object[] b;
    Object[] c;
    int d;
    int e;
    private float f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private p l;
    private p m;

    public o() {
        this(51, 0.8f);
    }

    public o(int n, float f) {
        if (n < 0) {
            throw new IllegalArgumentException("initialCapacity must be >= 0: " + n);
        }
        if ((n = com.corrodinggames.rts.java.audio.backend.m.b((int)Math.ceil((float)n / f))) > 0x40000000) {
            throw new IllegalArgumentException("initialCapacity is too large: " + n);
        }
        this.d = n;
        if (f <= 0.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0: " + f);
        }
        this.f = f;
        this.i = (int)((float)this.d * f);
        this.h = this.d - 1;
        this.g = 31 - Integer.numberOfTrailingZeros(this.d);
        this.j = Math.max(3, (int)Math.ceil(Math.log(this.d)) * 2);
        this.k = Math.max(Math.min(this.d, 8), (int)Math.sqrt(this.d) / 8);
        this.b = new Object[this.d + this.j];
        this.c = new Object[this.b.length];
    }

    public Object a(Object object, Object object2) {
        if (object == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        return this.b(object, object2);
    }

    private Object b(Object object, Object object2) {
        int n = this.d;  // 02b a/o.java L85: var11 = this.d
        Object[] objectArray = this.b;
        int n2 = object.hashCode();
        int n3 = n2 & this.h;
        Object object3 = objectArray[n3];
        if (object.equals(object3)) {
            Object object4 = this.c[n3];
            this.c[n3] = object2;
            return object4;
        }
        int n4 = this.c(n2);
        Object object5 = objectArray[n4];
        if (object.equals(object5)) {
            Object object6 = this.c[n4];
            this.c[n4] = object2;
            return object6;
        }
        int n5 = this.d(n2);
        Object object7 = objectArray[n5];
        if (object.equals(object7)) {
            Object object8 = this.c[n5];
            this.c[n5] = object2;
            return object8;
        }
        int n6 = n + this.e;
        for (n = this.d; n < n6; ++n) {
            if (!object.equals(objectArray[n])) continue;
            Object object9 = this.c[n];
            this.c[n] = object2;
            return object9;
        }
        if (object3 == null) {
            objectArray[n3] = object;
            this.c[n3] = object2;
            if (this.a++ >= this.i) {
                this.b(this.d << 1);
            }
            return null;
        }
        if (object5 == null) {
            objectArray[n4] = object;
            this.c[n4] = object2;
            if (this.a++ >= this.i) {
                this.b(this.d << 1);
            }
            return null;
        }
        if (object7 == null) {
            objectArray[n5] = object;
            this.c[n5] = object2;
            if (this.a++ >= this.i) {
                this.b(this.d << 1);
            }
            return null;
        }
        this.a(object, object2, n3, object3, n4, object5, n5, object7);
        return null;
    }

    private void c(Object object, Object object2) {
        int n = object.hashCode();
        int n2 = n & this.h;
        Object object3 = this.b[n2];
        if (object3 == null) {
            this.b[n2] = object;
            this.c[n2] = object2;
            if (this.a++ >= this.i) {
                this.b(this.d << 1);
            }
            return;
        }
        int n3 = this.c(n);
        Object object4 = this.b[n3];
        if (object4 == null) {
            this.b[n3] = object;
            this.c[n3] = object2;
            if (this.a++ >= this.i) {
                this.b(this.d << 1);
            }
            return;
        }
        int n4 = this.d(n);
        Object object5 = this.b[n4];
        if (object5 == null) {
            this.b[n4] = object;
            this.c[n4] = object2;
            if (this.a++ >= this.i) {
                this.b(this.d << 1);
            }
            return;
        }
        this.a(object, object2, n2, object3, n3, object4, n4, object5);
    }

    private void a(Object object, Object object2, int n, Object object3, int n2, Object object4, int n3, Object object5) {
        Object object6;
        Object object7;
        Object[] objectArray = this.b;
        Object[] objectArray2 = this.c;
        int n4 = this.h;
        int n5 = 0;
        int n6 = this.k;
        while (true) {
            switch (com.corrodinggames.rts.java.audio.backend.m.a(2)) {
                case 0: {
                    object7 = object3;
                    object6 = objectArray2[n];
                    objectArray[n] = object;
                    objectArray2[n] = object2;
                    break;
                }
                case 1: {
                    object7 = object4;
                    object6 = objectArray2[n2];
                    objectArray[n2] = object;
                    objectArray2[n2] = object2;
                    break;
                }
                default: {
                    object7 = object5;
                    object6 = objectArray2[n3];
                    objectArray[n3] = object;
                    objectArray2[n3] = object2;
                }
            }
            int n7 = object7.hashCode();
            n = n7 & n4;
            object3 = objectArray[n];
            if (object3 == null) {
                objectArray[n] = object7;
                objectArray2[n] = object6;
                if (this.a++ >= this.i) {
                    this.b(this.d << 1);
                }
                return;
            }
            n2 = this.c(n7);
            object4 = objectArray[n2];
            if (object4 == null) {
                objectArray[n2] = object7;
                objectArray2[n2] = object6;
                if (this.a++ >= this.i) {
                    this.b(this.d << 1);
                }
                return;
            }
            n3 = this.d(n7);
            object5 = objectArray[n3];
            if (object5 == null) {
                objectArray[n3] = object7;
                objectArray2[n3] = object6;
                if (this.a++ >= this.i) {
                    this.b(this.d << 1);
                }
                return;
            }
            if (++n5 == n6) break;
            object = object7;
            object2 = object6;
        }
        this.d(object7, object6);
    }

    private void d(Object object, Object object2) {
        if (this.e == this.j) {
            this.b(this.d << 1);
            this.b(object, object2);
            return;
        }
        int n = this.d + this.e;
        this.b[n] = object;
        this.c[n] = object2;
        ++this.e;
        ++this.a;
    }

    public Object a(Object object) {
        int n = object.hashCode();
        int n2 = n & this.h;
        if (!(object.equals(this.b[n2]) || object.equals(this.b[n2 = this.c(n)]) || object.equals(this.b[n2 = this.d(n)]))) {
            return this.e(object, null);
        }
        return this.c[n2];
    }

    private Object e(Object object, Object object2) {
        int n = this.d;  // 02b a/o.java L275: var4 = this.d
        Object[] objectArray = this.b;
        int n2 = n + this.e;
        for (n = this.d; n < n2; ++n) {
            if (!object.equals(objectArray[n])) continue;
            return this.c[n];
        }
        return object2;
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

    public boolean b(Object object) {
        int n = object.hashCode();
        int n2 = n & this.h;
        if (!(object.equals(this.b[n2]) || object.equals(this.b[n2 = this.c(n)]) || object.equals(this.b[n2 = this.d(n)]))) {
            return this.c(object);
        }
        return true;
    }

    private boolean c(Object object) {
        int n = this.d;  // 02b a/o.java L317: var3 = this.d
        Object[] objectArray = this.b;
        int n2 = n + this.e;
        for (n = this.d; n < n2; ++n) {
            if (!object.equals(objectArray[n])) continue;
            return true;
        }
        return false;
    }

    private void b(int n) {
        int n2 = this.d + this.e;
        this.d = n;
        this.i = (int)((float)n * this.f);
        this.h = n - 1;
        this.g = 31 - Integer.numberOfTrailingZeros(n);
        this.j = Math.max(3, (int)Math.ceil(Math.log(n)) * 2);
        this.k = Math.max(Math.min(n, 8), (int)Math.sqrt(n) / 8);
        Object[] objectArray = this.b;
        Object[] objectArray2 = this.c;
        this.b = new Object[n + this.j];
        this.c = new Object[n + this.j];
        int n3 = this.a;
        this.a = 0;
        this.e = 0;
        if (n3 > 0) {
            for (int i = 0; i < n2; ++i) {
                Object object = objectArray[i];
                if (object == null) continue;
                this.c(object, objectArray2[i]);
            }
        }
    }

    private int c(int n) {
        return ((n *= -1262997959) ^ n >>> this.g) & this.h;
    }

    private int d(int n) {
        return ((n *= -825114047) ^ n >>> this.g) & this.h;
    }

    public int hashCode() {
        int n = 0;
        Object[] objectArray = this.b;
        Object[] objectArray2 = this.c;
        int n2 = this.d + this.e;
        for (int i = 0; i < n2; ++i) {
            Object object = objectArray[i];
            if (object == null) continue;
            n += object.hashCode() * 31;
            Object object2 = objectArray2[i];
            if (object2 == null) continue;
            n += object2.hashCode();
        }
        return n;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof o)) {
            return false;
        }
        o o2 = (o)object;
        if (o2.a != this.a) {
            return false;
        }
        Object[] objectArray = this.b;
        Object[] objectArray2 = this.c;
        int n = this.d + this.e;
        for (int i = 0; i < n; ++i) {
            Object object2;
            Object object3 = objectArray[i];
            if (object3 == null || !((object2 = objectArray2[i]) == null ? !o2.b(object3) || o2.a(object3) != null : !object2.equals(o2.a(object3)))) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        return this.a(", ", true);
    }

    private String a(String string, boolean bl) {
        Object object;
        if (this.a == 0) {
            return bl ? "{}" : "";
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        if (bl) {
            stringBuilder.append('{');
        }
        Object[] objectArray = this.b;
        Object[] objectArray2 = this.c;
        int n = objectArray.length;
        while (n-- > 0) {
            object = objectArray[n];
            if (object == null) continue;
            stringBuilder.append(object);
            stringBuilder.append('=');
            stringBuilder.append(objectArray2[n]);
            break;
        }
        while (n-- > 0) {
            object = objectArray[n];
            if (object == null) continue;
            stringBuilder.append(string);
            stringBuilder.append(object);
            stringBuilder.append('=');
            stringBuilder.append(objectArray2[n]);
        }
        if (bl) {
            stringBuilder.append('}');
        }
        return stringBuilder.toString();
    }

    public p a() {
        return this.b();
    }

    public p b() {
        if (this.l == null) {
            this.l = new p(this);
            this.m = new p(this);
        }
        if (!this.l.f) {
            this.l.c();
            this.l.f = true;
            this.m.f = false;
            return this.l;
        }
        this.m.c();
        this.m.f = true;
        this.l.f = false;
        return this.m;
    }

    public /* synthetic */ Iterator iterator() {
        return this.a();
    }
}
