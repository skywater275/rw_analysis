/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;


import com.corrodinggames.rts.gameFramework.pathfinding.UnitListIterator;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class UnitList
extends AbstractList
implements Serializable,
Cloneable,
RandomAccess {
    public static final AStarNode[] a = new AStarNode[0];
    public int b;
    transient AStarNode[] c;

    public UnitList(int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("capacity < 0: " + n2);
        }
        this.c = n2 == 0 ? a : new AStarNode[n2];
    }

    public UnitList() {
        this.c = a;
    }

    public AStarNode[] a() {
        return this.c;
    }

    public boolean a(AStarNode n2) {
        int n3 = this.b;
        AStarNode[] nArray = this.c;
        if (n3 == nArray.length) {
            AStarNode[] nArray2 = new AStarNode[n3 + (n3 < 6 ? 12 : n3 >> 1)];
            System.arraycopy(nArray, 0, nArray2, 0, n3);
            nArray = nArray2;
            this.c = nArray2;
        }
        nArray[n3] = n2;
        this.b = n3 + 1;
        ++this.modCount;
        return true;
    }

    public void b(AStarNode n2) {
        int n3 = this.b;
        AStarNode[] nArray = this.c;
        if (n3 == nArray.length) {
            AStarNode[] nArray2 = new AStarNode[n3 + (n3 < 6 ? 12 : n3 >> 1)];
            System.arraycopy(nArray, 0, nArray2, 0, n3);
            nArray = nArray2;
            this.c = nArray2;
        }
        nArray[n3] = n2;
        this.b = n3 + 1;
    }

    public void a(int n2, AStarNode n3) {
        AStarNode[] nArray = this.c;
        int n4 = this.b;
        if (n2 > n4 || n2 < 0) {
            UnitList.a(n2, n4);
        }
        if (n4 < nArray.length) {
            System.arraycopy(nArray, n2, nArray, n2 + 1, n4 - n2);
        } else {
            AStarNode[] nArray2 = new AStarNode[UnitList.c(n4)];
            System.arraycopy(nArray, 0, nArray2, 0, n2);
            System.arraycopy(nArray, n2, nArray2, n2 + 1, n4 - n2);
            nArray = nArray2;
            this.c = nArray2;
        }
        nArray[n2] = n3;
        this.b = n4 + 1;
        ++this.modCount;
    }

    private static int c(int n2) {
        int n3 = n2 < 6 ? 12 : n2 >> 1;
        return n2 + n3;
    }

    @Override
    public boolean addAll(Collection collection) {
        AStarNode[] nArray = (AStarNode[])collection.toArray();
        int n2 = nArray.length;
        if (n2 == 0) {
            return false;
        }
        int n3 = this.b;
        int n4 = n3 + n2;
        AStarNode[] nArray2 = this.c;
        if (n4 > nArray2.length) {
            int n5 = UnitList.c(n4 - 1);
            AStarNode[] nArray3 = new AStarNode[n5];
            System.arraycopy(nArray2, 0, nArray3, 0, n3);
            nArray2 = nArray3;
            this.c = nArray3;
        }
        System.arraycopy(nArray, 0, nArray2, n3, n2);
        this.b = n4;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean addAll(int n2, Collection collection) {
        AStarNode[] nArray;
        int n3;
        int n4 = this.b;
        if (n2 > n4 || n2 < 0) {
            UnitList.a(n2, n4);
        }
        if ((n3 = (nArray = (AStarNode[])collection.toArray()).length) == 0) {
            return false;
        }
        int n5 = n4 + n3;
        AStarNode[] nArray2 = this.c;
        if (n5 <= nArray2.length) {
            System.arraycopy(nArray2, n2, nArray2, n2 + n3, n4 - n2);
        } else {
            int n6 = UnitList.c(n5 - 1);
            AStarNode[] nArray3 = new AStarNode[n6];
            System.arraycopy(nArray2, 0, nArray3, 0, n2);
            System.arraycopy(nArray2, n2, nArray3, n2 + n3, n4 - n2);
            nArray2 = nArray3;
            this.c = nArray3;
        }
        System.arraycopy(nArray, 0, nArray2, n2, n3);
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
            UnitList a2 = (UnitList) super.clone();
            a2.c = (AStarNode[])this.c.clone();
            return a2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    public AStarNode a(int n2) {
        if (n2 >= this.b) {
            UnitList.a(n2, this.b);
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
        AStarNode[] nArray = this.c;
        int n2 = this.b;
        if (object != null) {
            for (int i = 0; i < n2; ++i) {
                if (!object.equals(nArray[i])) continue;
                return true;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (nArray[i] != null) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        AStarNode[] nArray = this.c;
        int n2 = this.b;
        if (object != null) {
            for (int i = 0; i < n2; ++i) {
                if (!object.equals(nArray[i])) continue;
                return i;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (nArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        AStarNode[] nArray = this.c;
        if (object != null) {
            for (int i = this.b - 1; i >= 0; --i) {
                if (!object.equals(nArray[i])) continue;
                return i;
            }
        } else {
            for (int i = this.b - 1; i >= 0; --i) {
                if (nArray[i] != null) continue;
                return i;
            }
        }
        return -1;
    }

    public AStarNode b(int n2) {
        AStarNode[] nArray = this.c;
        int n3 = this.b;
        if (n2 >= n3) {
            UnitList.a(n2, n3);
        }
        AStarNode n4 = nArray[n2];  // 02b k/a: 元素 AStarNode
        System.arraycopy(nArray, n2 + 1, nArray, n2, --n3 - n2);
        nArray[n3] = null;
        this.b = n3;
        ++this.modCount;
        return n4;
    }

    public AStarNode b() {
        AStarNode[] nArray = this.c;
        int n2 = this.b - 1;
        AStarNode n3 = nArray[n2];
        nArray[n2] = null;
        this.b = n2;
        return n3;
    }

    @Override
    public boolean remove(Object object) {
        AStarNode[] nArray = this.c;
        int n2 = this.b;
        if (object != null) {
            for (int i = 0; i < n2; ++i) {
                if (!object.equals(nArray[i])) continue;
                System.arraycopy(nArray, i + 1, nArray, i, --n2 - i);
                nArray[n2] = null;
                this.b = n2;
                ++this.modCount;
                return true;
            }
        } else {
            for (int i = 0; i < n2; ++i) {
                if (nArray[i] != null) continue;
                System.arraycopy(nArray, i + 1, nArray, i, --n2 - i);
                nArray[n2] = null;
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

    public AStarNode b(int n2, AStarNode n3) {
        AStarNode[] nArray = this.c;
        if (n2 >= this.b) {
            UnitList.a(n2, this.b);
        }
        AStarNode n4 = nArray[n2];  // 02b k/a: 元素 AStarNode
        nArray[n2] = n3;
        return n4;
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
        return new UnitListIterator(this, null);
    }

    @Override
    public int hashCode() {
        AStarNode[] nArray = this.c;
        int n2 = 1;
        int n3 = this.b;
        for (int i = 0; i < n3; ++i) {
            AStarNode n4 = nArray[i];
            n2 = 31 * n2 + (n4 == null ? 0 : n4.hashCode());
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
        AStarNode[] nArray = this.c;
        if (list instanceof RandomAccess) {
            for (int i = 0; i < n2; ++i) {
                AStarNode n3 = nArray[i];
                Object e = list.get(i);
                if (!(n3 == null ? e != null : !n3.equals(e))) continue;
                return false;
            }
        } else {
            Iterator iterator = list.iterator();
            for (int i = 0; i < n2; ++i) {
                AStarNode n4 = nArray[i];
                Object e = iterator.next();
                if (!(n4 == null ? e != null : !n4.equals(e))) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public /* synthetic */ Object remove(int n2) {
        return this.b(n2);
    }

    @Override
    public /* synthetic */ void add(int n2, Object object) {
        this.a(n2, (AStarNode) object);
    }

    @Override
    public /* synthetic */ Object set(int n2, Object object) {
        return this.b(n2, (AStarNode) object);
    }

    @Override
    public /* synthetic */ Object get(int n2) {
        return this.a(n2);
    }

    @Override
    public /* synthetic */ boolean add(Object object) {
        return this.a((AStarNode) object);
    }

    static /* synthetic */ int a(UnitList a2) {
        return a2.modCount;
    }

    static /* synthetic */ int b(UnitList a2) {
        return a2.modCount;
    }

    static /* synthetic */ int c(UnitList a2) {
        return a2.modCount;
    }

    static /* synthetic */ int d(UnitList a2) {
        return ++a2.modCount;
    }
}
