/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.DequeListIterator;
import com.corrodinggames.rts.gameFramework.utility.ResultState;
import com.corrodinggames.rts.gameFramework.utility.Result;
import com.corrodinggames.rts.gameFramework.utility.EmptyArrays;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class DequeList
extends AbstractList
implements Serializable,
Cloneable,
RandomAccess {
    public CustomArrayList headNode = new CustomArrayList();
    public CustomArrayList tailNode = new CustomArrayList();
    public int elementCount;
    transient Object[] d = x.g;

    public void a(Object object) {
        Result r2 = this.tailNode.a != 0 ? (Result) this.tailNode.b() : new Result();
        r2.a = ResultState.a;
        r2.b = object;
        this.headNode.add(r2);
    }

    public void b(Object object) {
        Result r2 = this.tailNode.a != 0 ? (Result) this.tailNode.b() : new Result();
        r2.a = ResultState.b;
        r2.b = object;
        this.headNode.add(r2);
    }

    public void a() {
        ++this.modCount;
        if (this.headNode.a != 0) {
            for (Result r2 : (java.util.Collection<Result>) (java.util.Collection) this.headNode) {
                if (r2.a == ResultState.a) {
                    Object object = r2.b;
                    if (object == null) {
                        throw new RuntimeException("Trying to insert null into array");
                    }
                    this.add(object);
                } else if (r2.a == ResultState.b) {
                    this.remove(r2.b);
                } else {
                    throw new RuntimeException("Unknown operationType:" + (Object)((Object)r2.a));
                }
                r2.b = null;
            }
            if (this.headNode.size() < 100) {
                this.tailNode.addAll((Collection)this.headNode);
            }
            this.headNode.clear();
        }
    }

    public Object[] b() {
        return this.d;
    }

    @Override
    public boolean add(Object object) {
        int n = this.elementCount;
        Object[] objectArray = this.d;
        if (n == objectArray.length) {
            Object[] objectArray2 = new Object[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(objectArray, 0, objectArray2, 0, n);
            objectArray = objectArray2;
            this.d = objectArray2;
        }
        objectArray[n] = object;
        this.elementCount = n + 1;
        ++this.modCount;
        return true;
    }

    @Override
    public void add(int n, Object object) {
        Object[] objectArray = this.d;
        int n2 = this.elementCount;
        if (n > n2 || n < 0) {
            a(n, n2);
        }
        if (n2 < objectArray.length) {
            System.arraycopy(objectArray, n, objectArray, n + 1, n2 - n);
        } else {
            Object[] objectArray2 = new Object[a(n2)];
            System.arraycopy(objectArray, 0, objectArray2, 0, n);
            System.arraycopy(objectArray, n, objectArray2, n + 1, n2 - n);
            objectArray = objectArray2;
            this.d = objectArray2;
        }
        objectArray[n] = object;
        this.elementCount = n2 + 1;
        ++this.modCount;
    }

    private static int a(int n) {
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
        int n2 = this.elementCount;
        int n3 = n2 + n;
        Object[] objectArray2 = this.d;
        if (n3 > objectArray2.length) {
            int n4 = a(n3 - 1);
            Object[] objectArray3 = new Object[n4];
            System.arraycopy(objectArray2, 0, objectArray3, 0, n2);
            objectArray2 = objectArray3;
            this.d = objectArray3;
        }
        System.arraycopy(objectArray, 0, objectArray2, n2, n);
        this.elementCount = n3;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean addAll(int n, Collection collection) {
        Object[] objectArray;
        int n2;
        int n3 = this.elementCount;
        if (n > n3 || n < 0) {
            a(n, n3);
        }
        if ((n2 = (objectArray = collection.toArray()).length) == 0) {
            return false;
        }
        int n4 = n3 + n2;
        Object[] objectArray2 = this.d;
        if (n4 <= objectArray2.length) {
            System.arraycopy(objectArray2, n, objectArray2, n + n2, n3 - n);
        } else {
            int n5 = a(n4 - 1);
            Object[] objectArray3 = new Object[n5];
            System.arraycopy(objectArray2, 0, objectArray3, 0, n);
            System.arraycopy(objectArray2, n, objectArray3, n + n2, n3 - n);
            objectArray2 = objectArray3;
            this.d = objectArray3;
        }
        System.arraycopy(objectArray, 0, objectArray2, n, n2);
        this.elementCount = n4;
        ++this.modCount;
        return true;
    }

    static IndexOutOfBoundsException a(int n, int n2) {
        throw new IndexOutOfBoundsException("Invalid index " + n + ", size is " + n2);
    }

    @Override
    public synchronized void clear() {
        this.headNode.clear();
        if (this.elementCount != 0) {
            Arrays.fill(this.d, 0, this.elementCount, null);
            this.elementCount = 0;
            ++this.modCount;
        }
    }

    public Object clone() {
        try {
            DequeList o2 = (DequeList) super.clone();
            o2.d = (Object[])this.d.clone();
            return o2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    @Override
    public Object get(int n) {
        if (n >= this.elementCount) {
            a(n, this.elementCount);
        }
        return this.d[n];
    }

    @Override
    public int size() {
        return this.elementCount;
    }

    @Override
    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    @Override
    public boolean contains(Object object) {
        Object[] objectArray = this.d;
        int n = this.elementCount;
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
        Object[] objectArray = this.d;
        int n = this.elementCount;
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
        Object[] objectArray = this.d;
        if (object != null) {
            for (int i = this.elementCount - 1; i >= 0; --i) {
                if (!object.equals(objectArray[i])) continue;
                return i;
            }
        } else {
            for (int i = this.elementCount - 1; i >= 0; --i) {
                if (objectArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object remove(int n) {
        Object[] objectArray = this.d;
        int n2 = this.elementCount;
        if (n >= n2) {
            a(n, n2);
        }
        Object object = objectArray[n];
        System.arraycopy(objectArray, n + 1, objectArray, n, --n2 - n);
        objectArray[n2] = null;
        this.elementCount = n2;
        ++this.modCount;
        return object;
    }

    @Override
    public boolean remove(Object object) {
        Object[] objectArray = this.d;
        int n = this.elementCount;
        if (object != null) {
            for (int i = 0; i < n; ++i) {
                if (!object.equals(objectArray[i])) continue;
                System.arraycopy(objectArray, i + 1, objectArray, i, --n - i);
                objectArray[n] = null;
                this.elementCount = n;
                ++this.modCount;
                return true;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (objectArray[i] != null) continue;
                System.arraycopy(objectArray, i + 1, objectArray, i, --n - i);
                objectArray[n] = null;
                this.elementCount = n;
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
        Object[] objectArray = this.d;
        int n3 = this.elementCount;
        if (n >= n3) {
            throw new IndexOutOfBoundsException("fromIndex " + n + " >= size " + this.elementCount);
        }
        if (n2 > n3) {
            throw new IndexOutOfBoundsException("toIndex " + n2 + " > size " + this.elementCount);
        }
        if (n > n2) {
            throw new IndexOutOfBoundsException("fromIndex " + n + " > toIndex " + n2);
        }
        System.arraycopy(objectArray, n2, objectArray, n, n3 - n2);
        int n4 = n2 - n;
        Arrays.fill(objectArray, n3 - n4, n3, null);
        this.elementCount = n3 - n4;
        ++this.modCount;
    }

    @Override
    public Object set(int n, Object object) {
        Object[] objectArray = this.d;
        if (n >= this.elementCount) {
            a(n, this.elementCount);
        }
        Object object2 = objectArray[n];
        objectArray[n] = object;
        return object2;
    }

    @Override
    public Object[] toArray() {
        int n = this.elementCount;
        Object[] objectArray = new Object[n];
        System.arraycopy(this.d, 0, objectArray, 0, n);
        return objectArray;
    }

    @Override
    public Object[] toArray(Object[] objectArray) {
        int n = this.elementCount;
        if (objectArray.length < n) {
            Object[] objectArray2 = (Object[])Array.newInstance(objectArray.getClass().getComponentType(), n);
            objectArray = objectArray2;
        }
        System.arraycopy(this.d, 0, objectArray, 0, n);
        if (objectArray.length > n) {
            objectArray[n] = null;
        }
        return objectArray;
    }

    @Override
    public Iterator iterator() {
        return new DequeListIterator(this);
    }

    @Override
    public int hashCode() {
        Object[] objectArray = this.d;
        int n = 1;
        int n2 = this.elementCount;
        for (int i = 0; i < n2; ++i) {
            Object object = objectArray[i];
            n = 31 * n + (object == null ? 0 : object.hashCode());
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
        int n = this.elementCount;
        if (list.size() != n) {
            return false;
        }
        Object[] objectArray = this.d;
        if (list instanceof RandomAccess) {
            for (int i = 0; i < n; ++i) {
                Object object2 = objectArray[i];
                Object e = list.get(i);
                if (!(object2 == null ? e != null : !object2.equals(e))) continue;
                return false;
            }
        } else {
            Iterator iterator = list.iterator();
            for (int i = 0; i < n; ++i) {
                Object object3 = objectArray[i];
                Object e = iterator.next();
                if (!(object3 == null ? e != null : !object3.equals(e))) continue;
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ int a(DequeList o2) {
        return o2.modCount;
    }

    static /* synthetic */ int b(DequeList o2) {
        return o2.modCount;
    }

    static /* synthetic */ int c(DequeList o2) {
        return o2.modCount;
    }

    static /* synthetic */ int d(DequeList o2) {
        return ++o2.modCount;
    }
}
