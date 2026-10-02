/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.t;
import com.corrodinggames.rts.gameFramework.w;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class s
extends AbstractList
implements Serializable,
Cloneable,
RandomAccess {
    public static final w[] a = new w[0];
    int b;
    transient w[] c = a;
    String d;

    public s(String string) {
        this.d = string;
    }

    public w[] a() {
        return this.c;
    }

    public boolean a(w w2) {
        int n = this.b;
        w[] wArray = this.c;
        if (n == wArray.length) {
            w[] wArray2 = new w[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(wArray, 0, wArray2, 0, n);
            wArray = wArray2;
            this.c = wArray2;
        }
        wArray[n] = w2;
        this.b = n + 1;
        ++this.modCount;
        return true;
    }

    public void a(int n, w w2) {
        w[] wArray = this.c;
        int n2 = this.b;
        if (n > n2 || n < 0) {
            s.a(n, n2);
        }
        if (n2 < wArray.length) {
            System.arraycopy(wArray, n, wArray, n + 1, n2 - n);
        } else {
            w[] wArray2 = new w[s.c(n2)];
            System.arraycopy(wArray, 0, wArray2, 0, n);
            System.arraycopy(wArray, n, wArray2, n + 1, n2 - n);
            wArray = wArray2;
            this.c = wArray2;
        }
        wArray[n] = w2;
        this.b = n2 + 1;
        ++this.modCount;
    }

    private static int c(int n) {
        int n2 = n < 6 ? 12 : n >> 1;
        return n + n2;
    }

    @Override
    public boolean addAll(Collection collection) {
        w[] wArray = (w[])collection.toArray();
        int n = wArray.length;
        if (n == 0) {
            return false;
        }
        int n2 = this.b;
        int n3 = n2 + n;
        w[] wArray2 = this.c;
        if (n3 > wArray2.length) {
            int n4 = s.c(n3 - 1);
            w[] wArray3 = new w[n4];
            System.arraycopy(wArray2, 0, wArray3, 0, n2);
            wArray2 = wArray3;
            this.c = wArray3;
        }
        System.arraycopy(wArray, 0, wArray2, n2, n);
        this.b = n3;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean addAll(int n, Collection collection) {
        w[] wArray;
        int n2;
        int n3 = this.b;
        if (n > n3 || n < 0) {
            s.a(n, n3);
        }
        if ((n2 = (wArray = (w[])collection.toArray()).length) == 0) {
            return false;
        }
        int n4 = n3 + n2;
        w[] wArray2 = this.c;
        if (n4 <= wArray2.length) {
            System.arraycopy(wArray2, n, wArray2, n + n2, n3 - n);
        } else {
            int n5 = s.c(n4 - 1);
            w[] wArray3 = new w[n5];
            System.arraycopy(wArray2, 0, wArray3, 0, n);
            System.arraycopy(wArray2, n, wArray3, n + n2, n3 - n);
            wArray2 = wArray3;
            this.c = wArray3;
        }
        System.arraycopy(wArray, 0, wArray2, n, n2);
        this.b = n4;
        ++this.modCount;
        return true;
    }

    static IndexOutOfBoundsException a(int n, int n2) {
        throw new IndexOutOfBoundsException("Invalid index " + n + ", size is " + n2);
    }

    @Override
    public void clear() {
        if (this.b != 0) {
            Arrays.fill(this.c, 0, this.b, null);
            this.b = 0;
            ++this.modCount;
        }
    }

    public void b() {
        if (this.b != 0) {
            this.b = 0;
            ++this.modCount;
        }
    }

    public Object clone() {
        try {
            s s2 = (s)super.clone();
            s2.c = (w[])this.c.clone();
            return s2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    public w a(int n) {
        if (n >= this.b) {
            s.a(n, this.b);
        }
        return this.c[n];
    }

    @Override
    public int size() {
        return this.b;
    }

    @Override
    public boolean isEmpty() {
        return this.b == 0;
    }

    @Override
    public boolean contains(Object object) {
        w[] wArray = this.c;
        int n = this.b;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(wArray[i])) continue;
                return true;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (wArray[i] != null) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        w[] wArray = this.c;
        int n = this.b;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(wArray[i])) continue;
                return i;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (wArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        w[] wArray = this.c;
        if (object != null) {
            for (int i = this.b - 1; i >= 0; --i) {
                if (!object.equals(wArray[i])) continue;
                return i;
            }
        } else {
            for (int i = this.b - 1; i >= 0; --i) {
                if (wArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    public w b(int n) {
        w[] wArray = this.c;
        int n2 = this.b;
        if (n >= n2) {
            s.a(n, n2);
        }
        w w2 = wArray[n];
        System.arraycopy(wArray, n + 1, wArray, n, --n2 - n);
        wArray[n2] = null;
        this.b = n2;
        ++this.modCount;
        return w2;
    }

    @Override
    public boolean remove(Object object) {
        w[] wArray = this.c;
        int n = this.b;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(wArray[i])) continue;
                System.arraycopy(wArray, i + 1, wArray, i, --n - i);
                wArray[n] = null;
                this.b = n;
                ++this.modCount;
                return true;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (wArray[i] != null) continue;
                System.arraycopy(wArray, i + 1, wArray, i, --n - i);
                wArray[n] = null;
                this.b = n;
                ++this.modCount;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void removeRange(int n, int n2) {
        if (n == n2) {
            return;
        }
        Object[] objectArray = this.c;
        int n3 = this.b;
        if (n >= n3) {
            throw new IndexOutOfBoundsException("fromIndex " + n + " >= size " + this.b);
        }
        if (n2 > n3) {
            throw new IndexOutOfBoundsException("toIndex " + n2 + " > size " + this.b);
        }
        if (n > n2) {
            throw new IndexOutOfBoundsException("fromIndex " + n + " > toIndex " + n2);
        }
        System.arraycopy(objectArray, n2, objectArray, n, n3 - n2);
        int n4 = n2 - n;
        Arrays.fill(objectArray, n3 - n4, n3, null);
        this.b = n3 - n4;
        ++this.modCount;
    }

    public w b(int n, w w2) {
        w[] wArray = this.c;
        if (n >= this.b) {
            s.a(n, this.b);
        }
        w w3 = wArray[n];
        wArray[n] = w2;
        return w3;
    }

    @Override
    public Object[] toArray() {
        int n = this.b;
        Object[] objectArray = new Object[n];
        System.arraycopy(this.c, 0, objectArray, 0, n);
        return objectArray;
    }

    @Override
    public Object[] toArray(Object[] objectArray) {
        int n = this.b;
        if (objectArray.length < n) {
            Object[] objectArray2 = (Object[])Array.newInstance(objectArray.getClass().getComponentType(), n);
            objectArray = objectArray2;
        }
        System.arraycopy(this.c, 0, objectArray, 0, n);
        if (objectArray.length > n) {
            objectArray[n] = null;
        }
        return objectArray;
    }

    @Override
    public Iterator iterator() {
        return new t(this, null);
    }

    @Override
    public int hashCode() {
        w[] wArray = this.c;
        int n = 1;
        int n2 = this.b;
        for (int i = 0; i < n2; ++i) {
            w w2 = wArray[i];
            n = 31 * n + (w2 == null ? 0 : w2.hashCode());
        }
        return n;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof List)) {
            return false;
        }
        List list = (List)object;
        int n = this.b;
        if (list.size() != n) {
            return false;
        }
        w[] wArray = this.c;
        if (list instanceof RandomAccess) {
            for (int i = 0; i < n; ++i) {
                w w2 = wArray[i];
                Object e = list.get(i);
                if (!(w2 == null ? e != null : !w2.equals(e))) continue;
                return false;
            }
        } else {
            Iterator iterator = list.iterator();
            for (int i = 0; i < n; ++i) {
                w w3 = wArray[i];
                Object e = iterator.next();
                if (!(w3 == null ? e != null : !w3.equals(e))) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public /* synthetic */ Object remove(int n) {
        return this.b(n);
    }

    @Override
    public /* synthetic */ void add(int n, Object object) {
        this.a(n, (w)object);
    }

    @Override
    public /* synthetic */ Object set(int n, Object object) {
        return this.b(n, (w)object);
    }

    @Override
    public /* synthetic */ Object get(int n) {
        return this.a(n);
    }

    @Override
    public /* synthetic */ boolean add(Object object) {
        return this.a((w)object);
    }

    static /* synthetic */ int a(s s2) {
        return s2.modCount;
    }

    static /* synthetic */ int b(s s2) {
        return s2.modCount;
    }

    static /* synthetic */ int c(s s2) {
        return s2.modCount;
    }

    static /* synthetic */ int d(s s2) {
        return s2.modCount;
    }

    static /* synthetic */ int e(s s2) {
        return s2.modCount;
    }

    static /* synthetic */ int f(s s2) {
        return ++s2.modCount;
    }
}
