/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.TypedObjectListIterator;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class TypedObjectList
extends AbstractList<com.corrodinggames.rts.gameFramework.GameObject>
implements Serializable,
Cloneable,
RandomAccess {
    public static final GameObject[] a = new GameObject[0];
    int b;
    transient GameObject[] c = a;
    String d;

    public TypedObjectList(String string) {
        this.d = string;
    }

    public GameObject[] a() {
        return this.c;
    }

    public boolean a(GameObject w2) {
        int n = this.b;
        GameObject[] wArray = this.c;
        if (n == wArray.length) {
            GameObject[] wArray2 = new GameObject[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(wArray, 0, wArray2, 0, n);
            wArray = wArray2;
            this.c = wArray2;
        }
        wArray[n] = w2;
        this.b = n + 1;
        ++this.modCount;
        return true;
    }

    public void a(int n, GameObject w2) {
        GameObject[] wArray = this.c;
        int n2 = this.b;
        if (n > n2 || n < 0) {
            TypedObjectList.a(n, n2);
        }
        if (n2 < wArray.length) {
            System.arraycopy(wArray, n, wArray, n + 1, n2 - n);
        } else {
            GameObject[] wArray2 = new GameObject[TypedObjectList.c(n2)];
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
        GameObject[] wArray = (GameObject[])collection.toArray();
        int n = wArray.length;
        if (n == 0) {
            return false;
        }
        int n2 = this.b;
        int n3 = n2 + n;
        GameObject[] wArray2 = this.c;
        if (n3 > wArray2.length) {
            int n4 = TypedObjectList.c(n3 - 1);
            GameObject[] wArray3 = new GameObject[n4];
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
        GameObject[] wArray;
        int n2;
        int n3 = this.b;
        if (n > n3 || n < 0) {
            TypedObjectList.a(n, n3);
        }
        if ((n2 = (wArray = (GameObject[])collection.toArray()).length) == 0) {
            return false;
        }
        int n4 = n3 + n2;
        GameObject[] wArray2 = this.c;
        if (n4 <= wArray2.length) {
            System.arraycopy(wArray2, n, wArray2, n + n2, n3 - n);
        } else {
            int n5 = TypedObjectList.c(n4 - 1);
            GameObject[] wArray3 = new GameObject[n5];
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
            TypedObjectList s2 = (TypedObjectList) super.clone();
            s2.c = (GameObject[])this.c.clone();
            return s2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    public GameObject a(int n) {
        if (n >= this.b) {
            TypedObjectList.a(n, this.b);
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
        GameObject[] wArray = this.c;
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
        GameObject[] wArray = this.c;
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
        GameObject[] wArray = this.c;
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

    public GameObject b(int n) {
        GameObject[] wArray = this.c;
        int n2 = this.b;
        if (n >= n2) {
            TypedObjectList.a(n, n2);
        }
        GameObject w2 = wArray[n];
        System.arraycopy(wArray, n + 1, wArray, n, --n2 - n);
        wArray[n2] = null;
        this.b = n2;
        ++this.modCount;
        return w2;
    }

    @Override
    public boolean remove(Object object) {
        GameObject[] wArray = this.c;
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

    public GameObject b(int n, GameObject w2) {
        GameObject[] wArray = this.c;
        if (n >= this.b) {
            TypedObjectList.a(n, this.b);
        }
        GameObject w3 = wArray[n];
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
        return new TypedObjectListIterator(this);
    }

    @Override
    public int hashCode() {
        GameObject[] wArray = this.c;
        int n = 1;
        int n2 = this.b;
        for (int i = 0; i < n2; ++i) {
            GameObject w2 = wArray[i];
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
        GameObject[] wArray = this.c;
        if (list instanceof RandomAccess) {
            for (int i = 0; i < n; ++i) {
                GameObject w2 = wArray[i];
                Object e = list.get(i);
                if (!(w2 == null ? e != null : !w2.equals(e))) continue;
                return false;
            }
        } else {
            Iterator iterator = list.iterator();
            for (int i = 0; i < n; ++i) {
                GameObject w3 = wArray[i];
                Object e = iterator.next();
                if (!(w3 == null ? e != null : !w3.equals(e))) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public GameObject remove(int n) {
        return this.b(n);
    }

    @Override
    public void add(int n, com.corrodinggames.rts.gameFramework.GameObject object) {  // 泛型对齐 (02 裸类型)
        this.a(n, (GameObject) object);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.GameObject set(int n, com.corrodinggames.rts.gameFramework.GameObject object) {
        return this.b(n, (GameObject) object);
    }

    @Override
    public GameObject get(int n) {
        return this.a(n);
    }

    @Override
    public boolean add(com.corrodinggames.rts.gameFramework.GameObject object) {
        return this.a((GameObject) object);
    }

    static /* synthetic */ int a(TypedObjectList s2) {
        return s2.modCount;
    }

    static /* synthetic */ int b(TypedObjectList s2) {
        return s2.modCount;
    }

    static /* synthetic */ int c(TypedObjectList s2) {
        return s2.modCount;
    }

    static /* synthetic */ int d(TypedObjectList s2) {
        return s2.modCount;
    }

    static /* synthetic */ int e(TypedObjectList s2) {
        return s2.modCount;
    }

    static /* synthetic */ int f(TypedObjectList s2) {
        return ++s2.modCount;
    }
}
