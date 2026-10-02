/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.gameFramework.k.d;
import com.corrodinggames.rts.gameFramework.k.e;
import com.corrodinggames.rts.gameFramework.k.j;

public class k
extends l {
    public com.corrodinggames.rts.gameFramework.k.n a(int n2) {
        com.corrodinggames.rts.gameFramework.k.n n3 = new com.corrodinggames.rts.gameFramework.k.n();
        n3.a((short)n2, (short)0);
        n3.a(0, 0, 0);
        return n3;
    }

    public void a() {
        com.corrodinggames.rts.gameFramework.l.e("== Testing FastNodeQueue ==");
        d d2 = new d();
        this.a(d2);
        com.corrodinggames.rts.gameFramework.l.e("== Testing FastNodeQueue2 ==");
        e e2 = new e();
        this.a(e2);
    }

    public void a(j j2) {
        int n2;
        com.corrodinggames.rts.gameFramework.k.n n3 = this.a(1);
        com.corrodinggames.rts.gameFramework.k.n n4 = this.a(2);
        com.corrodinggames.rts.gameFramework.k.n n5 = this.a(3);
        com.corrodinggames.rts.gameFramework.k.n n6 = this.a(4);
        com.corrodinggames.rts.gameFramework.l.e("sequential");
        j2.b();
        j2.a(n3);
        j2.a(n4);
        j2.a(n5);
        j2.a(n6);
        n.a(j2.a(), n3);
        n.a(j2.a(), n4);
        n.a(j2.a(), n5);
        n.a(j2.a(), n6);
        com.corrodinggames.rts.gameFramework.l.e("reverse sequence");
        j2.b();
        j2.a(n6);
        j2.a(n5);
        j2.a(n4);
        j2.a(n3);
        n.a(j2.a(), n3);
        n.a(j2.a(), n4);
        n.a(j2.a(), n5);
        n.a(j2.a(), n6);
        com.corrodinggames.rts.gameFramework.l.e("sequential with noise");
        j2.b();
        j2.a(n3);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        n.a(j2.a(), n3);
        j2.a(n4);
        j2.a(n5);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        n.a(j2.a(), n4);
        j2.a(n6);
        n.a(j2.a(), n5);
        n.a(j2.a(), n6);
        com.corrodinggames.rts.gameFramework.l.e("reverse sequence with noise");
        j2.b();
        j2.a(n6);
        j2.a(n5);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        j2.a(n4);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        j2.a(n3);
        n.a(j2.a(), n3);
        n.a(j2.a(), n4);
        n.a(j2.a(), n5);
        n.a(j2.a(), n6);
    }
}
