/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.a;
import com.corrodinggames.rts.gameFramework.k.e;
import com.corrodinggames.rts.gameFramework.k.n;
import com.corrodinggames.rts.gameFramework.l;

public strictfp final class m {
    int a;
    int b;
    public static int c;
    final a d;
    final e e = new e();

    m() {
        int n2 = 1000;
        this.d = new a(n2 + 100);
        for (int i = 0; i < n2; ++i) {
            this.d.a(new n());
        }
    }

    n a() {
        if (this.d.b == 0) {
            ++c;
            return new n();
        }
        return this.d.b();
    }

    final void a(n n2) {
        if (n2 != null) {
            this.d.b(n2);
        }
    }

    void b() {
        if (this.d.size() > 50000) {
            l.e("PathOpenList: resetPool:memoryPool over 50000 clearing");
            this.d.clear();
        }
        this.e.a(this);
    }

    public void a(int n2, int n3) {
        this.b();
        this.a = n2;
        this.b = n3;
    }

    public final void a(int n2, short s2, short s3) {
        n n3 = this.a();
        n3.a(s2, s3);
        n3.a(n2, this.a, this.b);
        this.e.a(n3);
    }

    public final n c() {
        n n2 = this.e.a();
        if (n2 != null) {
            this.a(n2);
        }
        return n2;
    }
}
