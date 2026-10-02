/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Iterator;

public strictfp class c {
    static final boolean a = false;
    public ArrayList b = new ArrayList();
    public ArrayList c = new ArrayList();
    public ArrayList d = new ArrayList();
    static int e;

    public static void a(String string) {
        if (++e == 5) {
            l.e("(Rate Limiting...)");
        }
        if (e >= 5) {
            return;
        }
        l.e(string);
    }

    public void a() {
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public e b() {
        e e2 = new e(this);
        if (a) {
            l.e("Tracing source");
            e2.b = l.a(new Exception("Test"));
        }
        return e2;
    }

    public e a(n n2) {
        return this.b(n2);
    }

    public e b(n n2) {
        if (n2 == null) {
            throw new RuntimeException("team==null");
        }
        l l2 = l.B();
        e e2 = new e(this);
        e2.i = n2;
        e2.d = l2.by;
        if (a) {
            l.e("Tracing source");
            e2.b = l.a(new Exception("Test"));
        }
        if (!l2.bX.B) {
            if (!e2.l()) {
                l.b("Command failed prepareAndCheckOnServer()");
            }
            this.b.add(e2);
        } else {
            this.d.add(e2);
        }
        return e2;
    }

    public void c() {
        l l2 = l.B();
        e = 0;
        if (!l2.bX.B) {
            this.d();
        } else {
            this.e();
        }
    }

    public void d() {
        l l2 = l.B();
        int n2 = l2.bx;
        int n3 = 0;
        for (e e2 : this.b) {
            l2.cb.a(e2, n2);
            e2.k();
            ++n3;
        }
        this.b.clear();
        if (n3 > 0) {
            l2.cb.c();
        }
    }

    public void e() {
        l l2 = l.B();
        int n2 = l2.bx;
        int n3 = 0;
        Iterator iterator = this.b.iterator();
        while (iterator.hasNext()) {
            e e2 = (e)iterator.next();
            if (e2.c != n2) continue;
            l2.cb.a(e2, n2);
            e2.k();
            iterator.remove();
            ++n3;
        }
        if (n3 > 0) {
            l2.cb.c();
        }
    }
}
