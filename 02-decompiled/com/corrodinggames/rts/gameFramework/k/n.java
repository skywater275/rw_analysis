/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.f;

public strictfp final class n
implements Comparable {
    public short a;
    public short b;
    public int c;

    public final void a(short s, short s2) {
        this.a = s;
        this.b = s2;
    }

    public final void a(int n2, int n3, int n4) {
        int n5;
        int n6 = n3 - this.a;
        int n7 = n4 - this.b;
        n6 = n6 > 0 ? n6 : -n6;
        n7 = n7 > 0 ? n7 : -n7;
        this.c = n5 = n2 + (n6 + n7) * 11 + f.c(n6, n7) * -7;
    }

    public final int a(n n2) {
        if (this.c == n2.c) {
            if (this.a - n2.a != 0) {
                return this.a - n2.a;
            }
            return this.b - n2.b;
        }
        return this.c - n2.c;
    }

    public String toString() {
        return "PathOpenListNode [x=" + this.a + ", y=" + this.b + ", score=" + this.c + "]";
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((n)object);
    }
}
