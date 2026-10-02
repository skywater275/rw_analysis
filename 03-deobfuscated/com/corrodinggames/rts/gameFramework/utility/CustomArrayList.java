/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.CustomArrayListIterator;
import com.corrodinggames.rts.gameFramework.utility.EmptyArrays;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class CustomArrayList<T>
extends AbstractList<T>
implements Serializable,
Cloneable,
RandomAccess {
    public int a;
    transient Object[] b;

    public CustomArrayList(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("capacity < 0: " + n);
        }
        this.b = n == 0 ? x.g : new Object[n];
    }

    public CustomArrayList() {
        this.b = x.g;
    }

    public Object[] a() {
        return this.b;
    }

    public CustomArrayList(Collection collection) {
        Object[] objectArray = collection.toArray();
        if (objectArray.getClass() != Object[].class) {
            Object[] objectArray2 = new Object[objectArray.length];
            System.arraycopy(objectArray, 0, objectArray2, 0, objectArray.length);
            objectArray = objectArray2;
        }
        this.b = objectArray;
        this.a = objectArray.length;
    }

    @Override
    public boolean add(Object object) {
        int n = this.a;
        Object[] objectArray = this.b;
        if (n == objectArray.length) {
            Object[] objectArray2 = new Object[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(objectArray, 0, objectArray2, 0, n);
            objectArray = objectArray2;
            this.b = objectArray2;
        }
        objectArray[n] = object;
        this.a = n + 1;
        ++this.modCount;
        return true;
    }

    @Override
    public void add(int n, Object object) {
        Object[] objectArray = this.b;
        int n2 = this.a;
        if (n > n2 || n < 0) {
            CustomArrayList.a(n, n2);
        }
        if (n2 < objectArray.length) {
            System.arraycopy(objectArray, n, objectArray, n + 1, n2 - n);
        } else {
            Object[] objectArray2 = new Object[CustomArrayList.b(n2)];
            System.arraycopy(objectArray, 0, objectArray2, 0, n);
            System.arraycopy(objectArray, n, objectArray2, n + 1, n2 - n);
            objectArray = objectArray2;
            this.b = objectArray2;
        }
        objectArray[n] = object;
        this.a = n2 + 1;
        ++this.modCount;
    }

    private static int b(int n) {
        int n2 = n < 6 ? 12 : n >> 1;
        return n + n2;
    }

    @Override
    public boolean addAll(Collection collection) {
        Object[] objectArray = collection.toArray();
        int n = objectArray.length;
        if (n == 0) {
            return false;
        }
        int n2 = this.a;
        int n3 = n2 + n;
        Object[] objectArray2 = this.b;
        if (n3 > objectArray2.length) {
            int n4 = CustomArrayList.b(n3 - 1);
            Object[] objectArray3 = new Object[n4];
            System.arraycopy(objectArray2, 0, objectArray3, 0, n2);
            objectArray2 = objectArray3;
            this.b = objectArray3;
        }
        System.arraycopy(objectArray, 0, objectArray2, n2, n);
        this.a = n3;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean addAll(int n, Collection collection) {
        Object[] objectArray;
        int n2;
        int n3 = this.a;
        if (n > n3 || n < 0) {
            CustomArrayList.a(n, n3);
        }
        if ((n2 = (objectArray = collection.toArray()).length) == 0) {
            return false;
        }
        int n4 = n3 + n2;
        Object[] objectArray2 = this.b;
        if (n4 <= objectArray2.length) {
            System.arraycopy(objectArray2, n, objectArray2, n + n2, n3 - n);
        } else {
            int n5 = CustomArrayList.b(n4 - 1);
            Object[] objectArray3 = new Object[n5];
            System.arraycopy(objectArray2, 0, objectArray3, 0, n);
            System.arraycopy(objectArray2, n, objectArray3, n + n2, n3 - n);
            objectArray2 = objectArray3;
            this.b = objectArray3;
        }
        System.arraycopy(objectArray, 0, objectArray2, n, n2);
        this.a = n4;
        ++this.modCount;
        return true;
    }

    static IndexOutOfBoundsException a(int n, int n2) {
        throw new IndexOutOfBoundsException("Invalid index " + n + ", size is " + n2);
    }

    @Override
    public void clear() {
        if (this.a != 0) {
            Arrays.fill(this.b, 0, this.a, null);
            this.a = 0;
            ++this.modCount;
        }
    }

    public Object clone() {
        try {
            CustomArrayList m2 = (CustomArrayList) super.clone();
            m2.b = (Object[])this.b.clone();
            return m2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    @Override
    public T get(int n) {
        if (n >= this.a) {
            CustomArrayList.a(n, this.a);
        }
        return (T)this.b[n];
    }

    public final Object a(int n) {
        return this.b[n];
    }

    @Override
    public int size() {
        return this.a;
    }

    @Override
    public boolean isEmpty() {
        return this.a == 0;
    }

    @Override
    public boolean contains(Object object) {
        Object[] objectArray = this.b;
        int n = this.a;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(objectArray[i])) continue;
                return true;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (objectArray[i] != null) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        Object[] objectArray = this.b;
        int n = this.a;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(objectArray[i])) continue;
                return i;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (objectArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        Object[] objectArray = this.b;
        if (object != null) {
            for (int i = this.a - 1; i >= 0; --i) {
                if (!object.equals(objectArray[i])) continue;
                return i;
            }
        } else {
            for (int i = this.a - 1; i >= 0; --i) {
                if (objectArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int n) {
        Object[] objectArray = this.b;
        int n2 = this.a;
        if (n >= n2) {
            CustomArrayList.a(n, n2);
        }
        Object object = objectArray[n];
        System.arraycopy(objectArray, n + 1, objectArray, n, --n2 - n);
        objectArray[n2] = null;
        this.a = n2;
        ++this.modCount;
        return (T)object;
    }

    public Object b() {
        Object[] objectArray = this.b;
        int n = this.a;
        int n2 = n - 1;
        if (n == 0) {
            CustomArrayList.a(n2, n);
        }
        Object object = objectArray[n2];
        objectArray[--n] = null;
        this.a = n;
        ++this.modCount;
        return object;
    }

    public Object c() {
        Object[] objectArray = this.b;
        int n = this.a;
        int n2 = n - 1;
        Object object = objectArray[n2];
        objectArray[--n] = null;
        this.a = n;
        return object;
    }

    @Override
    public boolean remove(Object object) {
        Object[] objectArray = this.b;
        int n = this.a;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(objectArray[i])) continue;
                System.arraycopy(objectArray, i + 1, objectArray, i, --n - i);
                objectArray[n] = null;
                this.a = n;
                ++this.modCount;
                return true;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (objectArray[i] != null) continue;
                System.arraycopy(objectArray, i + 1, objectArray, i, --n - i);
                objectArray[n] = null;
                this.a = n;
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
        Object[] objectArray = this.b;
        int n3 = this.a;
        if (n >= n3) {
            throw new IndexOutOfBoundsException("fromIndex " + n + " >= size " + this.a);
        }
        if (n2 > n3) {
            throw new IndexOutOfBoundsException("toIndex " + n2 + " > size " + this.a);
        }
        if (n > n2) {
            throw new IndexOutOfBoundsException("fromIndex " + n + " > toIndex " + n2);
        }
        System.arraycopy(objectArray, n2, objectArray, n, n3 - n2);
        int n4 = n2 - n;
        Arrays.fill(objectArray, n3 - n4, n3, null);
        this.a = n3 - n4;
        ++this.modCount;
    }

    @Override
    public Object set(int n, Object object) {
        Object[] objectArray = this.b;
        if (n >= this.a) {
            CustomArrayList.a(n, this.a);
        }
        Object object2 = objectArray[n];
        objectArray[n] = object;
        return object2;
    }

    @Override
    public Object[] toArray() {
        int n = this.a;
        Object[] objectArray = new Object[n];
        System.arraycopy(this.b, 0, objectArray, 0, n);
        return objectArray;
    }

    @Override
    public Object[] toArray(Object[] objectArray) {
        int n = this.a;
        if (objectArray.length < n) {
            Object[] objectArray2 = (Object[])Array.newInstance(objectArray.getClass().getComponentType(), n);
            objectArray = objectArray2;
        }
        System.arraycopy(this.b, 0, objectArray, 0, n);
        if (objectArray.length > n) {
            objectArray[n] = null;
        }
        return objectArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomArrayListIterator(this);
    }

    @Override
    public int hashCode() {
        Object[] objectArray = this.b;
        int n2 = 1;
        int n3 = this.a;
        for (int i = 0; i < n3; ++i) {
            Object object = objectArray[i];
            n2 = 31 * n2 + (object == null ? 0 : object.hashCode());
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
        int n2 = this.a;
        if (list.size() != n2) {
            return false;
        }
        Object[] objectArray = this.b;
        if (list instanceof RandomAccess) {
            for (int i = 0; i < n2; ++i) {
                Object object2 = objectArray[i];
                Object e = list.get(i);
                if (!(object2 == null ? e != null : !object2.equals(e))) continue;
                return false;
            }
        } else {
            Iterator iterator = list.iterator();
            for (int i = 0; i < n2; ++i) {
                Object object3 = objectArray[i];
                Object e = iterator.next();
                if (!(object3 == null ? e != null : !object3.equals(e))) continue;
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ int a(CustomArrayList m2) {
        return m2.modCount;
    }

    static /* synthetic */ int b(CustomArrayList m2) {
        return m2.modCount;
    }

    static /* synthetic */ int c(CustomArrayList m2) {
        return m2.modCount;
    }

    static /* synthetic */ int d(CustomArrayList m2) {
        return ++m2.modCount;
    }
}
