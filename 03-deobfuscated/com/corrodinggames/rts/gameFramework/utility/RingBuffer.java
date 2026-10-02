/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.RingBufferIterator;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class RingBuffer
extends AbstractCollection
implements Serializable,
Cloneable {
    private transient Object[] headIndex = new Object[16];
    private transient int elementCount;
    private transient int d;

    private void c() {
        assert (this.elementCount == this.d);
        int n = this.elementCount;
        int n2 = this.headIndex.length;
        int n3 = n2 - n;
        int n4 = n2 << 1;
        if (n4 < 0) {
            throw new IllegalStateException("Sorry, deque too big");
        }
        Object[] objectArray = new Object[n4];
        System.arraycopy(this.headIndex, n, objectArray, 0, n3);
        System.arraycopy(this.headIndex, 0, objectArray, n3, n);
        this.headIndex = objectArray;
        this.elementCount = 0;
        this.d = n2;
    }

    private Object[] a(Object[] objectArray) {
        if (this.elementCount < this.d) {
            System.arraycopy(this.headIndex, this.elementCount, objectArray, 0, this.size());
        } else if (this.elementCount > this.d) {
            int n = this.headIndex.length - this.elementCount;
            System.arraycopy(this.headIndex, this.elementCount, objectArray, 0, n);
            System.arraycopy(this.headIndex, 0, objectArray, n, this.d);
        }
        return objectArray;
    }

    public void a(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        this.headIndex[this.d] = object;
        this.d = this.d + 1 & this.headIndex.length - 1;
        if (this.d == this.elementCount) {
            this.c();
        }
    }

    public Object a() {
        int n = this.elementCount;
        Object object = this.headIndex[n];
        if (object == null) {
            return null;
        }
        this.headIndex[n] = null;
        this.elementCount = n + 1 & this.headIndex.length - 1;
        return object;
    }

    public boolean b(Object object) {
        Object object2;
        if (object == null) {
            return false;
        }
        int n = this.headIndex.length - 1;
        int n2 = this.elementCount;
        while ((object2 = this.headIndex[n2]) != null) {
            if (object.equals(object2)) {
                this.a(n2);
                return true;
            }
            n2 = n2 + 1 & n;
        }
        return false;
    }

    @Override
    public boolean add(Object object) {
        this.a(object);
        return true;
    }

    private void d() {
        assert (this.headIndex[this.d] == null);
        assert (this.elementCount != this.d ? this.headIndex[this.elementCount] != null && this.headIndex[this.d - 1 & this.headIndex.length - 1] != null : this.headIndex[this.elementCount] == null);
        assert (this.headIndex[this.elementCount - 1 & this.headIndex.length - 1] == null);
    }

    private boolean a(int n) {
        this.d();
        Object[] objectArray = this.headIndex;
        int n2 = objectArray.length - 1;
        int n3 = this.elementCount;
        int n4 = this.d;
        int n5 = n - n3 & n2;
        int n6 = n4 - n & n2;
        if (n5 >= (n4 - n3 & n2)) {
            throw new ConcurrentModificationException();
        }
        if (n5 < n6) {
            if (n3 <= n) {
                System.arraycopy(objectArray, n3, objectArray, n3 + 1, n5);
            } else {
                System.arraycopy(objectArray, 0, objectArray, 1, n);
                objectArray[0] = objectArray[n2];
                System.arraycopy(objectArray, n3, objectArray, n3 + 1, n2 - n3);
            }
            objectArray[n3] = null;
            this.elementCount = n3 + 1 & n2;
            return false;
        }
        if (n < n4) {
            System.arraycopy(objectArray, n + 1, objectArray, n, n6);
            this.d = n4 - 1;
        } else {
            System.arraycopy(objectArray, n + 1, objectArray, n, n2 - n);
            objectArray[n2] = objectArray[0];
            System.arraycopy(objectArray, 1, objectArray, 0, n4);
            this.d = n4 - 1 & n2;
        }
        return true;
    }

    @Override
    public int size() {
        return this.d - this.elementCount & this.headIndex.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return this.elementCount == this.d;
    }

    @Override
    public Iterator iterator() {
        return new RingBufferIterator(this, null);  // javap 铁证: h.<init>(g;g$1)V
    }

    @Override
    public boolean contains(Object object) {
        Object object2;
        if (object == null) {
            return false;
        }
        int n = this.headIndex.length - 1;
        int n2 = this.elementCount;
        while ((object2 = this.headIndex[n2]) != null) {
            if (object.equals(object2)) {
                return true;
            }
            n2 = n2 + 1 & n;
        }
        return false;
    }

    @Override
    public boolean remove(Object object) {
        return this.b(object);
    }

    @Override
    public void clear() {
        int n = this.elementCount;
        int n2 = this.d;
        if (n != n2) {
            this.d = 0;
            this.elementCount = 0;
            int n3 = n;
            int n4 = this.headIndex.length - 1;
            do {
                this.headIndex[n3] = null;
            } while ((n3 = n3 + 1 & n4) != n2);
        }
    }

    @Override
    public Object[] toArray() {
        return this.a(new Object[this.size()]);
    }

    @Override
    public Object[] toArray(Object[] objectArray) {
        int n = this.size();
        if (objectArray.length < n) {
            objectArray = (Object[])Array.newInstance(objectArray.getClass().getComponentType(), n);
        }
        this.a(objectArray);
        if (objectArray.length > n) {
            objectArray[n] = null;
        }
        return objectArray;
    }

    public RingBuffer b() {
        try {
            RingBuffer g2 = (RingBuffer) super.clone();
            g2.headIndex = (Object[])this.headIndex.clone();
            return g2;
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError();
        }
    }

    public /* synthetic */ Object clone() {
        return this.b();
    }

    static /* synthetic */ int a(RingBuffer g2) {
        return g2.elementCount;
    }

    static /* synthetic */ int b(RingBuffer g2) {
        return g2.d;
    }

    static /* synthetic */ Object[] c(RingBuffer g2) {
        return g2.headIndex;
    }

    static /* synthetic */ boolean a(RingBuffer g2, int n) {
        return g2.a(n);
    }
}
