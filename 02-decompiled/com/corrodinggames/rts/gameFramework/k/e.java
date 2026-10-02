/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.a;
import com.corrodinggames.rts.gameFramework.k.j;
import com.corrodinggames.rts.gameFramework.k.m;
import com.corrodinggames.rts.gameFramework.k.n;

public strictfp final class e
extends j {
    boolean a;
    int b;
    final a c = new a(100);
    final a d = new a(900);

    @Override
    public void a(n n2) {
        int n3 = n2.c;
        if (n3 <= this.b) {
            if (n3 == this.b) {
                this.c.b(n2);
                return;
            }
            this.c();
            this.b = n3;
            this.c.a(n2);
            return;
        }
        this.d.b(n2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public n a() {
        n n2;
        block5: {
            block4: {
                n n3;
                long l = -1L;
                if (this.c.b <= 0) break block4;
                n n4 = n3 = this.c.b();
                return n4;
            }
            if (this.d.b != 0) break block5;
            this.b = Integer.MAX_VALUE;
            n n5 = null;
            return n5;
        }
        this.d();
        n n6 = n2 = this.c.b();
        return n6;
    }

    @Override
    public void b() {
        this.a((m)null);
    }

    public void a(m m2) {
        if (m2 != null) {
            n[] nArray = this.c.a();
            for (int i = this.c.b - 1; i >= 0; --i) {
                n n2 = nArray[i];
                m2.a(n2);
            }
            n[] nArray2 = this.d.a();
            for (int i = this.d.b - 1; i >= 0; --i) {
                n n3 = nArray2[i];
                m2.a(n3);
            }
        }
        this.c.clear();
        this.d.clear();
        this.b = Integer.MAX_VALUE;
        this.a = true;
    }

    private void c() {
        n[] nArray = this.c.a();
        int n2 = this.c.b;
        for (int i = 0; i < n2; ++i) {
            n n3 = nArray[i];
            this.d.a(n3);
        }
        this.c.clear();
    }

    private void d() {
        int n2;
        n n3;
        int n4;
        long l = -1L;
        int n5 = Integer.MAX_VALUE;
        a a2 = this.d;
        n[] nArray = a2.a();
        for (n4 = a2.b - 1; n4 >= 0; --n4) {
            n3 = nArray[n4];
            n2 = n3.c;
            if (n2 >= n5) continue;
            n5 = n2;
        }
        for (n4 = a2.b - 1; n4 >= 0; --n4) {
            n3 = nArray[n4];
            if (n3.c != n5) continue;
            this.c.a(n3);
            n2 = a2.b - 1;
            nArray[n4] = nArray[n2];
            nArray[n2] = null;
            a2.b = n2;
        }
        this.b = n5;
    }
}
