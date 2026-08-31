/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.utility.v;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class u
extends AbstractList
implements Serializable,
Cloneable,
RandomAccess {
    public static final am[] a = new am[0];
    public int b;
    transient am[] c = a;

    public am[] a() {
        return this.c;
    }

    public boolean a(am am2) {
        int n2 = this.b;
        am[] amArray = this.c;
        if (n2 == amArray.length) {
            am[] amArray2 = new am[n2 + (n2 < 6 ? 12 : n2 >> 1)];
            System.arraycopy(amArray, 0, amArray2, 0, n2);
            amArray = amArray2;
            this.c = amArray2;
        }
        amArray[n2] = am2;
        this.b = n2 + 1;
        ++this.modCount;
        return true;
    }

    public final void b(am am2) {
        int n2 = this.b;
        am[] amArray = this.c;
        if (n2 == amArray.length) {
            am[] amArray2 = new am[n2 + (n2 < 6 ? 12 : n2 >> 1)];
            System.arraycopy(amArray, 0, amArray2, 0, n2);
            amArray = amArray2;
            this.c = amArray2;
        }
        amArray[n2] = am2;
        this.b = n2 + 1;
    }

    public void a(int n2, am am2) {
        am[] amArray = this.c;
        int n3 = this.b;
        if (n2 > n3 || n2 < 0) {
            u.a(n2, n3);
        }
        if (n3 < amArray.length) {
            System.arraycopy(amArray, n2, amArray, n2 + 1, n3 - n2);
        } else {
            am[] amArray2 = new am[u.c(n3)];
            System.arraycopy(amArray, 0, amArray2, 0, n2);
            System.arraycopy(amArray, n2, amArray2, n2 + 1, n3 - n2);
            amArray = amArray2;
            this.c = amArray2;
        }
        amArray[n2] = am2;
        this.b = n3 + 1;
        ++this.modCount;
    }

    private static int c(int n2) {
        int n3 = n2 < 6 ? 12 : n2 >> 1;
        return n2 + n3;
    }

    @Override
    public boolean addAll(Collection collection) {
        am[] amArray = (am[])collection.toArray();
        int n2 = amArray.length;
        if (n2 == 0) {
            return false;
        }
        int n3 = this.b;
        int n4 = n3 + n2;
        am[] amArray2 = this.c;
        if (n4 > amArray2.length) {
            int n5 = u.c(n4 - 1);
            am[] amArray3 = new am[n5];
            System.arraycopy(amArray2, 0, amArray3, 0, n3);
            amArray2 = amArray3;
            this.c = amArray3;
        }
        System.arraycopy(amArray, 0, amArray2, n3, n2);
        this.b = n4;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean addAll(int n2, Collection collection) {
        am[] amArray;
        int n3;
        int n4 = this.b;
        if (n2 > n4 || n2 < 0) {
            u.a(n2, n4);
        }
        if ((n3 = (amArray = (am[])collection.toArray()).length) == 0) {
            return false;
        }
        int n5 = n4 + n3;
        am[] amArray2 = this.c;
        if (n5 <= amArray2.length) {
            System.arraycopy(amArray2, n2, amArray2, n2 + n3, n4 - n2);
        } else {
            int n6 = u.c(n5 - 1);
            am[] amArray3 = new am[n6];
            System.arraycopy(amArray2, 0, amArray3, 0, n2);
            System.arraycopy(amArray2, n2, amArray3, n2 + n3, n4 - n2);
            amArray2 = amArray3;
            this.c = amArray3;
        }
        System.arraycopy(amArray, 0, amArray2, n2, n3);
        this.b = n5;
        ++this.modCount;
        return true;
    }

    static IndexOutOfBoundsException a(int n2, int n3) {
        throw new IndexOutOfBoundsException("Invalid index " + n2 + ", size is " + n3);
    }

    @Override
    public void clear() {
        if (this.b != 0) {
            Arrays.fill(this.c, 0, this.b, null);
            this.b = 0;
            ++this.modCount;
        }
    }

    public Object clone() {
        try {
            u u2 = (u)super.clone();
            u2.c = (am[])this.c.clone();
            return u2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    public am a(int n2) {
        if (n2 >= this.b) {
            u.a(n2, this.b);
        }
        return this.c[n2];
    }

    @Override
    public final int size() {
        return this.b;
    }

    @Override
    public final boolean isEmpty() {
        return this.b == 0;
    }

    @Override
    public boolean contains(Object object) {
        am[] amArray = this.c;
        int n2 = this.b;
        if (object != null) {
            for (int i = 0; i < n2; ++i) {
                if (!object.equals(amArray[i])) continue;
                return true;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (amArray[i] != null) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        am[] amArray = this.c;
        int n2 = this.b;
        if (object != null) {
            for (int i = 0; i < n2; ++i) {
                if (!object.equals(amArray[i])) continue;
                return i;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (amArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        am[] amArray = this.c;
        if (object != null) {
            for (int i = this.b - 1; i >= 0; --i) {
                if (!object.equals(amArray[i])) continue;
                return i;
            }
        } else {
            for (int i = this.b - 1; i >= 0; --i) {
                if (amArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    public am b(int n2) {
        am[] amArray = this.c;
        int n3 = this.b;
        if (n2 >= n3) {
            u.a(n2, n3);
        }
        am am2 = amArray[n2];
        System.arraycopy(amArray, n2 + 1, amArray, n2, --n3 - n2);
        amArray[n3] = null;
        this.b = n3;
        ++this.modCount;
        return am2;
    }

    @Override
    public boolean remove(Object object) {
        am[] amArray = this.c;
        int n2 = this.b;
        if (object != null) {
            for (int i = 0; i < n2; ++i) {
                if (!object.equals(amArray[i])) continue;
                System.arraycopy(amArray, i + 1, amArray, i, --n2 - i);
                amArray[n2] = null;
                this.b = n2;
                ++this.modCount;
                return true;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (amArray[i] != null) continue;
                System.arraycopy(amArray, i + 1, amArray, i, --n2 - i);
                amArray[n2] = null;
                this.b = n2;
                ++this.modCount;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void removeRange(int n2, int n3) {
        if (n2 == n3) {
            return;
        }
        Object[] objectArray = this.c;
        int n4 = this.b;
        if (n2 >= n4) {
            throw new IndexOutOfBoundsException("fromIndex " + n2 + " >= size " + this.b);
        }
        if (n3 > n4) {
            throw new IndexOutOfBoundsException("toIndex " + n3 + " > size " + this.b);
        }
        if (n2 > n3) {
            throw new IndexOutOfBoundsException("fromIndex " + n2 + " > toIndex " + n3);
        }
        System.arraycopy(objectArray, n3, objectArray, n2, n4 - n3);
        int n5 = n3 - n2;
        Arrays.fill(objectArray, n4 - n5, n4, null);
        this.b = n4 - n5;
        ++this.modCount;
    }

    public am b(int n2, am am2) {
        am[] amArray = this.c;
        if (n2 >= this.b) {
            u.a(n2, this.b);
        }
        am am3 = amArray[n2];
        amArray[n2] = am2;
        return am3;
    }

    @Override
    public Object[] toArray() {
        int n2 = this.b;
        Object[] objectArray = new Object[n2];
        System.arraycopy(this.c, 0, objectArray, 0, n2);
        return objectArray;
    }

    @Override
    public Object[] toArray(Object[] objectArray) {
        int n2 = this.b;
        if (objectArray.length < n2) {
            Object[] objectArray2 = (Object[])Array.newInstance(objectArray.getClass().getComponentType(), n2);
            objectArray = objectArray2;
        }
        System.arraycopy(this.c, 0, objectArray, 0, n2);
        if (objectArray.length > n2) {
            objectArray[n2] = null;
        }
        return objectArray;
    }

    @Override
    public Iterator iterator() {
        return new v(this, null);
    }

    @Override
    public int hashCode() {
        am[] amArray = this.c;
        int n2 = 1;
        int n3 = this.b;
        for (int i = 0; i < n3; ++i) {
            am am2 = amArray[i];
            n2 = 31 * n2 + (am2 == null ? 0 : am2.hashCode());
        }
        return n2;
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
        int n2 = this.b;
        if (list.size() != n2) {
            return false;
        }
        am[] amArray = this.c;
        if (list instanceof RandomAccess) {
            for (int i = 0; i < n2; ++i) {
                am am2 = amArray[i];
                Object e2 = list.get(i);
                if (!(am2 == null ? e2 != null : !am2.equals(e2))) continue;
                return false;
            }
        } else {
            Iterator iterator = list.iterator();
            for (int i = 0; i < n2; ++i) {
                am am3 = amArray[i];
                Object e3 = iterator.next();
                if (!(am3 == null ? e3 != null : !am3.equals(e3))) continue;
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
    public /* synthetic */ void add(int n2, Object object) {
        this.a(n2, (am)object);
    }

    @Override
    public /* synthetic */ Object set(int n2, Object object) {
        return this.b(n2, (am)object);
    }

    @Override
    public /* synthetic */ Object get(int n2) {
        return this.a(n2);
    }

    @Override
    public /* synthetic */ boolean add(Object object) {
        return this.a((am)object);
    }

    static /* synthetic */ int a(u u2) {
        return u2.modCount;
    }

    static /* synthetic */ int b(u u2) {
        return u2.modCount;
    }

    static /* synthetic */ int c(u u2) {
        return u2.modCount;
    }

    static /* synthetic */ int d(u u2) {
        return ++u2.modCount;
    }
}
