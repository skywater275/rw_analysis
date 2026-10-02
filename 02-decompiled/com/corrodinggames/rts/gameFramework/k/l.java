/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.k.f;
import com.corrodinggames.rts.gameFramework.k.i;
import com.corrodinggames.rts.gameFramework.k.k;
import com.corrodinggames.rts.gameFramework.k.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public strictfp final class l {
    static final boolean a = false;
    static boolean b = !com.corrodinggames.rts.gameFramework.l.as;
    static boolean c = false;
    static boolean d = false;
    public static f e;
    static boolean f;
    static boolean g;
    static boolean h;
    static int i;
    static boolean j;
    static ArrayList k;
    static boolean l;
    public static final boolean m;
    public boolean n = true;
    o o = new o(this);
    boolean p = true;
    b q;
    int r;
    short s;
    short t;
    ArrayList u = new ArrayList();
    i[] v = new i[0];
    public Paint w = new Paint();
    public i x;
    public i y;
    public i z;
    public i A;
    public i B;
    public i C;
    public i D;
    public i E;
    Paint F = new Paint();
    Object G = new Object();
    ArrayList H;
    LinkedList I = new LinkedList();
    LinkedList J = new LinkedList();
    Object K = new Object();

    public i a(ao ao2) {
        for (i i2 : this.v) {
            if (i2.a != ao2) continue;
            return i2;
        }
        return null;
    }

    public boolean a(ao ao2, int n, int n2) {
        i i2 = this.a(ao2);
        return this.a(i2, n, n2);
    }

    public boolean b(ao ao2, int n, int n2) {
        i i2 = this.a(ao2);
        return this.a(i2, n, n2, true);
    }

    public boolean a(i i2, int n, int n2) {
        return this.a(i2, n, n2, false);
    }

    public boolean a(i i2, int n2, int n3, boolean bl) {
        if (!this.q.c(n2, n3)) {
            return true;
        }
        if (i2.a == ao.d) {
            return false;
        }
        int n4 = n2 * this.t + n3;
        if (!bl && i2.e[n4] == -1) {
            return true;
        }
        return i2.d[n4] == -1 || i2.f[n4] == -1;
    }

    public final int b(i i2, int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return -1;
        }
        if (i2.a == ao.d) {
            return 0;
        }
        int n4 = n2 * this.t + n3;
        if (i2.d[n4] == -1 || i2.e[n4] == -1 || i2.f[n4] == -1) {
            return -1;
        }
        return i2.d[n4] + i2.e[n4] + i2.f[n4] * 10;
    }

    public final int c(i i2, int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return -1;
        }
        if (i2.a == ao.d) {
            return 4;
        }
        if (i2.j == null) {
            return -1;
        }
        int n4 = n2 * this.t + n3;
        return i2.j[n4];
    }

    public boolean a(int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return true;
        }
        int n4 = n2 * this.t + n3;
        if (this.D.d[n4] != -1) {
            return false;
        }
        return this.A.d[n4] != -1;
    }

    public boolean b(int n2, int n3) {
        if (!this.q.c(n2, n3)) {
            return true;
        }
        int n4 = n2 * this.t + n3;
        if (this.C.d[n4] != -1) {
            return false;
        }
        return this.E.d[n4] != -1;
    }

    public synchronized void a(b b2, boolean bl) {
        this.d();
        com.corrodinggames.rts.gameFramework.l.e("PathEngine: Setting up map costs");
        boolean bl2 = false;
        if (bl && this.q != null && this.q == b2 && this.s == b2.u.n && this.t == b2.u.o) {
            if (this.r == com.corrodinggames.rts.gameFramework.k.i.a(b2)) {
                com.corrodinggames.rts.gameFramework.l.e("PathEngine: Keeping existing map costs");
                bl2 = true;
            } else {
                com.corrodinggames.rts.gameFramework.l.e("PathEngine: Error: Map checksum does not match!!!");
            }
        }
        if (bl2) {
            // empty if block
        }
        this.q = b2;
        this.r = com.corrodinggames.rts.gameFramework.k.i.a(b2);
        this.s = (short)b2.u.n;
        this.t = (short)b2.u.o;
        e = null;
        this.u.clear();
        this.v = new i[0];
        this.x = new i(this, ao.a, this.s, this.t);
        this.y = new i(this, ao.b, this.s, this.t);
        this.y.b();
        this.y.a(null);
        this.z = new i(this, ao.c, this.s, this.t);
        this.A = new i(this, ao.e, this.s, this.t);
        this.A.b();
        this.A.a(null);
        this.B = new i(this, ao.d, this.s, this.t);
        this.C = new i(this, ao.f, this.s, this.t);
        this.C.b();
        this.C.a(null);
        this.D = new i(this, ao.g, this.s, this.t);
        this.D.b();
        this.D.a(null);
        this.E = new i(this, ao.h, this.s, this.t);
        this.E.b();
        this.E.a(null);
        for (o o2 : this.H) {
            o2.a(b2);
        }
        this.o.a(b2);
        com.corrodinggames.rts.gameFramework.l.e("PathEngine: Ready");
    }

    public void a() {
        int n2;
        int n3;
        int n4;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        i i2 = this.y;
        Rect rect = new Rect();
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = l2.cA;
        float f5 = l2.cB;
        e e2 = l2.bL.u;
        int n5 = (int)(f2 * this.q.r - 1.0f);
        if (n5 < 0) {
            n5 = 0;
        }
        if ((n4 = (int)(f3 * this.q.s - 1.0f)) < 0) {
            n4 = 0;
        }
        if ((n3 = (int)((f2 + f4) * this.q.r + 1.0f)) > this.s - 1) {
            n3 = this.s - 1;
        }
        if ((n2 = (int)((f3 + f5) * this.q.s + 1.0f)) > this.t - 1) {
            n2 = this.t - 1;
        }
        for (int i3 = n5; i3 < n3 + 1; ++i3) {
            for (int i4 = n4; i4 < n2 + 1; ++i4) {
                g g2 = e2.a(i3, i4);
                if (g2 == null) continue;
                int n6 = i3 * this.q.n;
                int n7 = i4 * this.q.o;
                rect.a(n6, n7, n6 + this.q.n, n7 + this.q.o);
                rect.a((int)(-f2), (int)(-f3));
                boolean bl = rect.b((int)(l2.bS.x / l2.cX), (int)(l2.bS.y / l2.cX));
                if (g && !bl) continue;
                int n8 = i2.d[i3 * this.t + i4];
                int n9 = i2.e[i3 * this.t + i4];
                int n10 = i2.f[i3 * this.t + i4];
                n8 = n8 == -1 ? 255 : (n8 *= 2);
                n9 = n9 == -1 ? 255 : (n9 *= 2);
                if (n10 == -1) {
                    n10 = 255;
                } else {
                    if (n10 != 0) {
                        n10 += 30;
                    }
                    n10 *= 2;
                }
                this.F.a(128, n8, n9, n10);
                l2.bO.b(rect, this.F);
                if (!bl || i2.f == null) continue;
                l2.bO.a("o:" + i2.f[i3 * this.t + i4], (float)rect.d(), (float)rect.e(), l2.dp);
            }
        }
    }

    public void a(y y2) {
        if (y2 != null) {
            com.corrodinggames.rts.game.n.b(y2);
        }
        for (i i2 : this.v) {
            i2.c(y2);
        }
        this.y.a(y2);
        this.C.a(y2);
        this.D.a(y2);
        this.E.a(y2);
    }

    public void b() {
        for (i i2 : this.v) {
            i2.e();
        }
    }

    public l() {
        this.H = new ArrayList();
        this.H.add(new o(this));
        int n2 = com.corrodinggames.rts.gameFramework.f.c();
        if (n2 > 1) {
            com.corrodinggames.rts.gameFramework.l.b("PathEngine", "We have " + n2 + " cores, creating extra solvers");
            this.H.add(new o(this));
        } else {
            com.corrodinggames.rts.gameFramework.l.b("PathEngine", "We only have one core, using single solver");
        }
        for (o o2 : this.H) {
            o2.c();
        }
    }

    public void c() {
        for (k k2 : this.I) {
            k2.w = true;
        }
        this.I.clear();
        this.h();
    }

    public void d() {
        for (k k2 : this.I) {
            this.a(k2);
        }
        this.I.clear();
        this.h();
    }

    public void a(i i2, boolean bl) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!bl) {
            if (i2.k + 50 < l2.bx) {
                i2.k = l2.bx - 40;
                i2.e();
            }
            i2.a(bl);
        } else {
            if (i2.k + 30 < l2.bx) {
                i2.k = l2.bx;
                i2.e();
            }
            i2.a(bl);
        }
    }

    public k a(boolean bl) {
        k k2 = com.corrodinggames.rts.game.units.y.L ? new f(this, bl) : new k(this, bl);
        return k2;
    }

    public void a(k k2, boolean bl) {
        this.a(k2, bl, false);
    }

    public void a(k k2, boolean bl, boolean bl2) {
        if (!this.p) {
            com.corrodinggames.rts.gameFramework.l.b("PathEngine", "Cannot start new path, not running");
            return;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        i i2 = this.a(k2.o);
        this.a(i2, bl);
        k2.e();
        k2.t = 300.0f;
        int n2 = com.corrodinggames.rts.gameFramework.f.d(k2.h - k2.l);
        int n3 = com.corrodinggames.rts.gameFramework.f.d(k2.i - k2.m);
        if (n2 < 15 && n3 < 15) {
            k2.t = 12.0f;
        } else if (n2 < 50 && n3 < 50) {
            k2.t = 16.0f;
        } else if (n2 < 200 && n3 < 200) {
            k2.t = 24.0f;
        } else if (n2 < 400 && n3 < 400) {
            k2.t = 50.0f;
        } else if (n2 < 1000 && n3 < 1000) {
            k2.t = 100.0f;
        } else if (n2 < 2000 && n3 < 2000) {
            k2.t = 200.0f;
        }
        if (!l2.bX.B && !l2.cb.i()) {
            k2.t = n2 < 1000 && n3 < 1000 ? 180.0f : 360.0f;
        }
        if (k2.r) {
            k2.t *= 2.0f;
            k2.t += 50.0f;
        }
        k2.s = k2.t;
        if (!this.n || bl2) {
            this.o.a(k2);
            this.o.b();
            this.I.add(k2);
        } else {
            this.b(k2);
            this.I.add(k2);
        }
    }

    public void a(float f2) {
        this.i();
    }

    public void b(float f2) {
        for (i i2 : this.v) {
            i2.p = 0;
            if (!i2.o) continue;
            i2.o = false;
            i2.c(null);
        }
        this.i();
        this.d(f2);
    }

    public void c(float f2) {
        if (j) {
            for (Object object : k) {
                ((k)object).h();
                ((k)object).g();
            }
        }
        if (d) {
            am am2;
            Object object;
            boolean bl = true;
            object = com.corrodinggames.rts.gameFramework.l.B();
            if (((com.corrodinggames.rts.gameFramework.l)object).bS.bZ.b > 0 && (am2 = ((com.corrodinggames.rts.gameFramework.l)object).bS.bZ.a(0)) instanceof y) {
                y y2 = (y)am2;
                if (y2.au != null) {
                    y2.au.d(y2);
                    bl = false;
                }
            }
            if (bl) {
                // empty if block
            }
        }
        if (f) {
            this.a();
        }
        if (h) {
            // empty if block
        }
    }

    public boolean e() {
        for (k k2 : this.I) {
            if (!(k2.t <= 0.0f) || k2.c()) continue;
            return true;
        }
        return false;
    }

    public String f() {
        Object object;
        Iterator iterator = this.I.iterator();
        String string = null;
        int n2 = 0;
        while (iterator.hasNext()) {
            object = (k)iterator.next();
            if (!(((k)object).t <= 0.0f) || ((k)object).c()) continue;
            if (string == null) {
                float f2 = com.corrodinggames.rts.gameFramework.f.b((float)((k)object).h, (float)((k)object).i, (float)((k)object).l, (float)((k)object).m);
                string = "[distance:" + f2 + ", allowedDelay:" + ((k)object).s + " lowPriority:" + ((k)object).r + "]";
            }
            ++n2;
        }
        object = "(total:" + n2 + ") ";
        if (string != null) {
            object = (String)object + string;
        }
        return object;
    }

    private void d(float f2) {
        Iterator iterator = this.I.iterator();
        while (iterator.hasNext()) {
            k k2 = (k)iterator.next();
            if (k2.t <= 0.0f) {
                k2.t = 0.0f;
                k2.u = true;
                if (j) {
                    k.add(k2);
                    if (k.size() > 10) {
                        k.remove(0);
                    }
                }
                if (!k2.c()) {
                    if (com.corrodinggames.rts.gameFramework.l.B().ay()) {
                        com.corrodinggames.rts.gameFramework.l.b("PathEngine", "updateUnfinishedPaths: path wasn't solved, isGoingToBlockThisFrame did not protect");
                    }
                    this.a(k2);
                }
                if (!k2.c()) continue;
                iterator.remove();
                continue;
            }
            k2.t -= f2;
        }
    }

    private k g() {
        k k2 = null;
        for (k k3 : this.J) {
            if (k2 != null && !(k2.t > k3.t)) continue;
            k2 = k3;
        }
        if (k2 == null) {
            throw new RuntimeException("Failed to find any paths");
        }
        if (!this.J.remove(k2)) {
            throw new RuntimeException("Failed remove found path");
        }
        return k2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void b(k k2) {
        Object object = this.K;
        synchronized (object) {
            this.J.add(k2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void h() {
        Object object = this.K;
        synchronized (object) {
            this.J.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void i() {
        LinkedList linkedList = this.J;
        if (linkedList.size() > 0) {
            Object object = this.K;
            synchronized (object) {
                o o2;
                while (linkedList.size() > 0 && (o2 = this.j()) != null) {
                    k k2 = this.g();
                    if (k2.v) continue;
                    this.a(o2, k2);
                }
            }
        }
    }

    private o j() {
        for (o o2 : this.H) {
            if (!o2.s) continue;
            return o2;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(k k2) {
        if (!k2.v) {
            while (true) {
                Object object = this.G;
                synchronized (object) {
                    o o2 = this.j();
                    if (o2 != null) {
                        this.a(o2, k2);
                        break;
                    }
                    try {
                        this.G.wait(2000L);
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                }
            }
        }
        boolean bl = false;
        long l2 = com.corrodinggames.rts.gameFramework.l.V();
        while (true) {
            Object object = this.G;
            synchronized (object) {
                if (k2.c()) {
                    break;
                }
                bl = true;
                this.i();
                try {
                    this.G.wait(2000L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
        }
        if (bl && b) {
            com.corrodinggames.rts.gameFramework.l.b("PathEngine", "We were blocked path(" + k2.e + ") for:" + (com.corrodinggames.rts.gameFramework.l.V() - l2));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(o o2, k k2) {
        k k3 = k2;
        synchronized (k3) {
            if (!k2.v) {
                o2.a(k2);
                o2.a();
            }
        }
    }

    static {
        f = false;
        g = false;
        h = false;
        i = 20;
        j = false;
        k = new ArrayList();
        l = false;
        m = false;
    }
}
