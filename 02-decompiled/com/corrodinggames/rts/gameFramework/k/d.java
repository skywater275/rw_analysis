/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.j;
import com.corrodinggames.rts.gameFramework.k.m;
import com.corrodinggames.rts.gameFramework.k.n;
import com.corrodinggames.rts.gameFramework.l;
import java.util.PriorityQueue;

public strictfp final class d
extends j {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static double h;
    public static double i;
    int j;
    int k;
    int l;
    int m;
    int n;
    n[] o = new n[975];
    com.corrodinggames.rts.gameFramework.utility.m p = new com.corrodinggames.rts.gameFramework.utility.m(100);
    final PriorityQueue q = new PriorityQueue();
    final com.corrodinggames.rts.gameFramework.utility.m r = new com.corrodinggames.rts.gameFramework.utility.m(300);
    int s;
    int t;
    public static int u;

    private void c() {
        int n2;
        int n3;
        if (this.k == this.m) {
            this.d();
            return;
        }
        int n4 = this.n;
        n[] nArray = this.o;
        if (this.j == -2) {
            for (n3 = 0; n3 <= n4; ++n3) {
                n n5 = nArray[n3];
                n2 = n5.c;
                if (this.k != n2) continue;
                this.j = n3;
                this.k = n2;
                return;
            }
        }
        n3 = -1;
        int n6 = Integer.MAX_VALUE;
        for (n2 = 0; n2 <= n4; ++n2) {
            n n7 = nArray[n2];
            int n8 = n7.c;
            if (n6 <= n8) continue;
            n3 = n2;
            n6 = n8;
        }
        if (this.k != n6) {
            ++g;
        }
        this.j = n3;
        this.k = n6;
    }

    private void a(int n2, n n3) {
        this.o[n2] = n3;
        int n4 = n3.c;
        if (this.j == -1 || this.k >= n4) {
            if (this.k > n4) {
                // empty if block
            }
            if (this.k != n4) {
                ++g;
            }
            this.j = n2;
            this.k = n4;
        }
        if (this.l == -1 || this.m < n4) {
            this.l = n2;
            this.m = n4;
        }
    }

    private void d() {
        this.j = -1;
        this.k = Integer.MAX_VALUE;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        for (int i = 0; i <= this.n; ++i) {
            n n2 = this.o[i];
            if (n2 == null) {
                com.corrodinggames.rts.gameFramework.l.e("n:" + i);
                com.corrodinggames.rts.gameFramework.l.e("lowestBufferLastIndex:" + this.n);
                throw new RuntimeException("null with n:" + i + ", lowestBufferLastIndex:" + this.n);
            }
            int n3 = n2.c;
            if (this.k > n3) {
                this.j = i;
                this.k = n3;
            }
            if (this.m >= n3) continue;
            this.l = i;
            this.m = n3;
        }
    }

    private void e() {
        if (this.n < 30) {
            n n2;
            n n3 = (n)this.q.poll();
            if (n3 != null) {
                this.b(n3);
            }
            if ((n2 = (n)this.q.peek()) != null) {
                this.s = n2.c;
            }
            return;
        }
        this.s = Integer.MAX_VALUE;
        n n4 = (n)this.q.peek();
        if (n4 != null) {
            this.s = n4.c;
        }
    }

    public d() {
        this.f();
    }

    private void b(n n2) {
        ++this.n;
        this.a(this.n, n2);
        if (this.n > a) {
            a = this.n;
        }
    }

    private void c(n n2) {
        this.q.offer(n2);
        if (n2.c < this.s) {
            this.s = n2.c;
        }
        if (this.q.size() > b) {
            b = this.q.size();
        }
    }

    @Override
    public void a(n n2) {
        ++d;
        boolean bl = false;
        if (this.n < this.o.length - 1) {
            bl = true;
        }
        if (bl) {
            if (n2.c <= this.s) {
                this.b(n2);
                return;
            }
            this.c(n2);
            return;
        }
        if (n2.c < this.m) {
            n n3 = this.o[this.l];
            this.o[this.l] = n2;
            this.d();
            this.c(n3);
            return;
        }
        this.c(n2);
    }

    @Override
    public n a() {
        if (this.j == -2) {
            int n2 = this.k;
            this.c();
            ++this.t;
            if (u < this.t) {
                u = this.t;
            }
            ++e;
            if (n2 == this.k) {
                ++f;
            }
        } else {
            this.t = 0;
        }
        if (this.k < this.s && this.j != -1) {
            n[] nArray = this.o;
            n n3 = nArray[this.j];
            if (this.n != this.j) {
                nArray[this.j] = nArray[this.n];
                nArray[this.n] = null;
            } else {
                nArray[this.n] = null;
            }
            --this.n;
            this.j = -2;
            return n3;
        }
        n n4 = (n)this.q.poll();
        this.e();
        return n4;
    }

    @Override
    public void b() {
        this.a((m)null);
    }

    public void a(m m2) {
        for (int i = 0; i < this.o.length; ++i) {
            if (this.o[i] == null) continue;
            if (m2 != null) {
                m2.a(this.o[i]);
            }
            this.o[i] = null;
        }
        this.n = -1;
        for (n n2 : this.q) {
            if (m2 == null) continue;
            m2.a(n2);
        }
        this.q.clear();
        this.f();
    }

    private void f() {
        this.j = -1;
        this.k = Integer.MAX_VALUE;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.s = Integer.MAX_VALUE;
    }
}
