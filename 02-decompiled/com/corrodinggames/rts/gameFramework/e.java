/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a;
import com.corrodinggames.rts.game.units.a.j;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.g;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.ab;
import com.corrodinggames.rts.gameFramework.c;
import com.corrodinggames.rts.gameFramework.d;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.w;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Iterator;

public strictfp class e {
    public boolean a;
    public String b;
    public int c;
    public int d;
    public boolean e = false;
    public boolean f = false;
    public boolean g = false;
    public boolean h = false;
    public n i;
    public au j;
    public com.corrodinggames.rts.game.units.a.c k = com.corrodinggames.rts.game.units.a.s.i;
    public PointF l;
    public am m;
    public a n;
    private PointF z;
    public boolean o = false;
    public n p;
    public short q;
    public boolean r;
    public float s;
    public float t;
    public int u;
    private m A = new m();
    m v = new m();
    m w = new m();
    public boolean x = false;
    final /* synthetic */ c y;

    public e(c c2) {
        this.y = c2;
    }

    public boolean a() {
        for (d d2 : this.w) {
            if (d2.a.a() != null) continue;
            return false;
        }
        return true;
    }

    public void b() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.x = true;
        ab ab2 = l2.bV.c();
        for (y y2 : this.v) {
            ab2.a.add(y2);
        }
        if (this.j != null) {
            float f2 = this.j.g();
            float f3 = this.j.h();
            if (this.j.d() != av.a && this.j.d() != av.h && this.j.d() != av.b) {
                return;
            }
            m m2 = ab2.a(f2, f3, this.j.j);
            for (y y3 : m2) {
                if (y3.aK() || !y3.I() || this.e && y3.ar() != null) continue;
                float f4 = f2;
                float f5 = f3;
                int n2 = 0;
                if (this.j.d() == av.b) {
                    n2 = y3.q(this.j.i());
                }
                boolean bl = true;
                d d2 = new d();
                d2.b = y3.eh;
                d2.c = y3.eo;
                d2.d = y3.ep;
                d2.e = f4;
                d2.f = f5;
                d2.g = l2.bx;
                d2.h = y3.h();
                boolean bl2 = false;
                boolean bl3 = false;
                d2.a = y3.a(f4, f5, n2, bl, bl2, bl3);
                d2.a.s = d2.a.t = 120.0f;
                d2.a.u = true;
                this.w.add(d2);
            }
        }
    }

    public n c() {
        return this.i;
    }

    public int d() {
        return this.A.size() + this.v.size();
    }

    public boolean e() {
        if (com.corrodinggames.rts.game.units.a.s.c(this.k)) {
            return false;
        }
        return this.d() == 0;
    }

    public synchronized e f() {
        try {
            as as2 = new as();
            this.a(as2);
            k k2 = new k(as2.d());
            e e2 = new e(this.y);
            e2.c = this.c;
            e2.a(k2);
            return e2;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void g() {
        if (this.j != null) {
            for (y y2 : this.v) {
                this.A.add((Object)y2.eh);
            }
            this.v.clear();
            this.j.k();
        }
    }

    public synchronized void a(as as2) {
        as2.e("c");
        as2.c(this.i.k);
        as2.a(this.j != null);
        if (this.j != null) {
            this.j.a(as2);
        }
        as2.a(this.e);
        as2.a(this.g);
        as2.a(-1);
        as2.a(this.n);
        as2.a(this.z != null);
        if (this.z != null) {
            as2.a(this.z.a);
            as2.a(this.z.b);
        }
        as2.a(this.o);
        as2.a(this.v.size() + this.A.size());
        for (y y2 : this.v) {
            as2.a(y2.eh);
        }
        Iterator iterator = this.A.iterator();
        while (iterator.hasNext()) {
            long l2 = (Long)iterator.next();
            as2.a(l2);
        }
        as2.a(this.p != null);
        if (this.p != null) {
            as2.a(this.p);
        }
        as2.a(this.l != null);
        if (this.l != null) {
            as2.a(this.l.a);
            as2.a(this.l.b);
        }
        as2.a(this.m);
        as2.c(this.k.a());
        as2.a(this.f);
        as2.a(this.q);
        as2.a(this.r);
        if (this.r) {
            as2.c(0);
            as2.a(this.s);
            as2.a(this.t);
            as2.a(this.u);
        }
        as2.a(this.w.size());
        for (int i2 = 0; i2 < this.w.size(); ++i2) {
            d d2 = (d)this.w.get(i2);
            d2.a(as2);
        }
        as2.a(this.h);
        as2.a("c");
    }

    public void a(k k2) {
        int n2;
        k2.b("c");
        this.i = com.corrodinggames.rts.game.n.k(k2.d());
        if (this.i == null) {
            throw new IOException("team==null");
        }
        boolean bl = k2.e();
        if (bl) {
            this.j = new au();
            this.j.a(k2);
        }
        this.e = k2.e();
        this.g = k2.e();
        this.k = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(k2.f()));
        this.n = (a)k2.b(a.class);
        boolean bl2 = k2.e();
        if (bl2) {
            this.z = new PointF();
            this.z.a = k2.g();
            this.z.b = k2.g();
        }
        this.o = k2.e();
        int n3 = k2.f();
        for (n2 = 0; n2 < n3; ++n2) {
            this.A.add((Object)k2.n());
        }
        if (k2.b() >= 16) {
            this.p = null;
            if (k2.e()) {
                this.p = k2.s();
            }
        }
        if (k2.b() >= 29) {
            n2 = k2.e() ? 1 : 0;
            if (n2 != 0) {
                this.l = new PointF();
                this.l.a = k2.g();
                this.l.b = k2.g();
            }
            this.m = k2.o();
        }
        if (k2.b() >= 33) {
            this.k = com.corrodinggames.rts.game.units.a.c.a(k2.l());
        }
        if (k2.b() >= 37) {
            this.f = k2.e();
        }
        if (k2.b() >= 52) {
            this.q = k2.v();
        }
        if (k2.b() >= 53) {
            this.r = k2.e();
            if (this.r) {
                k2.d();
                this.s = k2.g();
                this.t = k2.g();
                this.u = k2.f();
            }
            n2 = k2.f();
            this.w.clear();
            for (int i2 = 0; i2 < n2; ++i2) {
                d d2 = new d();
                d2.a(k2);
                this.w.add(d2);
            }
        }
        if (k2.b() >= 80) {
            this.h = k2.e();
        }
        k2.d("c");
    }

    public void a(AbstractList abstractList) {
        for (y y2 : abstractList) {
            this.a(y2);
        }
    }

    public void a(y y2) {
        if (y2 == null) {
            throw new RuntimeException("unit cannot be null");
        }
        if (y2.bX != this.i) {
            // empty if block
        }
        if (this.i.w) {
            if (y2.bX != this.i && com.corrodinggames.rts.gameFramework.l.B().bs != this.i) {
                com.corrodinggames.rts.gameFramework.l.b("CommandController", "Warning AI: " + this.i.k + " gave an order to unit with team:" + y2.bX.k + " type:" + y2.r().i());
                com.corrodinggames.rts.gameFramework.l.g("");
            }
            if (y2.cW()) {
                com.corrodinggames.rts.gameFramework.l.b("CommandController", "Warning AI: " + this.i.k + " gave an order to unit with canNotBeGivenOrdersByPlayer: " + y2.r().i());
            }
        }
        this.v.add(y2);
    }

    public void h() {
        this.o = true;
    }

    public void a(float f2, float f3) {
        this.j = new au();
        this.j.a(f2, f3);
    }

    public void b(float f2, float f3) {
        this.j = new au();
        this.j.b(f2, f3);
    }

    public void a(am am2) {
        this.j = new au();
        this.j.a(am2);
    }

    public void a(float f2, float f3, boolean bl) {
        this.j = new au();
        this.j.b(f2, f3);
        this.j.j = bl;
    }

    public void a(am am2, boolean bl) {
        this.j = new au();
        this.j.a(am2);
        this.j.j = bl;
    }

    public void a(float f2, float f3, com.corrodinggames.rts.game.units.as as2, int n2) {
        this.j = new au();
        this.j.a(f2, f3, as2, n2);
    }

    public void b(am am2) {
        this.j = new au();
        this.j.b(am2);
    }

    public void c(am am2) {
        this.j = new au();
        this.j.c(am2);
    }

    public void c(float f2, float f3) {
        this.j = new au();
        this.j.c(f2, f3);
    }

    public void d(am am2) {
        this.j = new au();
        this.j.f(am2);
    }

    public void e(am am2) {
        this.j = new au();
        this.j.g(am2);
    }

    public void f(am am2) {
        this.j = new au();
        this.j.h(am2);
    }

    public void a(com.corrodinggames.rts.game.units.a.c c2) {
        this.k = c2;
    }

    public void a(com.corrodinggames.rts.game.units.a.c c2, PointF pointF, am am2) {
        this.k = c2;
        this.l = pointF;
        this.m = am2;
    }

    public void a(a a2) {
        this.n = a2;
    }

    public void a(PointF pointF) {
        this.z = pointF;
    }

    public synchronized void i() {
        for (Object object : this.A) {
            y y2 = com.corrodinggames.rts.gameFramework.w.b((Long)object, true);
            if (y2 == null) continue;
            this.v.add(y2);
        }
        this.A.clear();
        Iterator iterator = this.v.iterator();
        while (iterator.hasNext()) {
            Object object;
            object = (y)iterator.next();
            if (!((y)object).bV) continue;
            iterator.remove();
        }
    }

    public void j() {
        if (com.corrodinggames.rts.game.units.a.s.c(this.k)) {
            for (y y2 : this.v) {
                y2.b(y2.a(this.k), this.g);
            }
        }
    }

    public void k() {
        Object object;
        Object object2;
        Object object3;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.cb.j() && !this.a) {
            return;
        }
        this.i();
        if (this.r) {
            if (this.s != 0.0f) {
                com.corrodinggames.rts.gameFramework.l.e("issueCommand: changeStepRate:" + this.s);
                l2.bX.a(this.s, "command");
                return;
            }
            if (this.u != 0) {
                com.corrodinggames.rts.gameFramework.l.e("system action:" + this.u);
                if (this.u == 1) {
                    com.corrodinggames.rts.gameFramework.l.e("new DebugDesyncDetector");
                    g g2 = new g(false);
                    ((am)g2).b(com.corrodinggames.rts.game.n.i);
                    return;
                }
                if (this.u == 2) {
                    com.corrodinggames.rts.gameFramework.l.e("new DebugDesyncDetector (stress test)");
                    g g3 = new g(false);
                    g3.b(com.corrodinggames.rts.game.n.i);
                    g3.a = true;
                    return;
                }
                if (this.u == 100) {
                    com.corrodinggames.rts.gameFramework.l.e("team surrender");
                    if (this.i == null) {
                        com.corrodinggames.rts.gameFramework.l.e("team not found");
                        return;
                    }
                    if (l2.bX.C) {
                        l2.bX.j("'" + this.i.v + "' has surrendered");
                    }
                    this.i.E = true;
                    for (am am2 : am.bE) {
                        if (am2.bX != this.i || !(am2 instanceof y)) continue;
                        y y2 = (y)am2;
                        y2.c(false);
                    }
                    return;
                }
                if (this.u == 200) {
                    com.corrodinggames.rts.gameFramework.l.e("queue quick resync");
                    l2.bX.N = true;
                    return;
                }
                if (this.u == 5) {
                    com.corrodinggames.rts.gameFramework.l.e("system command spawn");
                    if (this.j == null || this.j.d() != av.c || this.j.a() == null) {
                        com.corrodinggames.rts.gameFramework.l.e("system command spawn - failed");
                        return;
                    }
                    int n2 = this.j.b();
                    com.corrodinggames.rts.game.units.as as2 = this.j.a();
                    boolean bl = false;
                    if (this.i != null && this.i == l2.bs && l2.bs.a(false, false) == 0) {
                        bl = true;
                    }
                    am am3 = as2.a();
                    am3.eo = this.j.g();
                    am3.ep = this.j.h();
                    if (this.i != null) {
                        am3.f(this.i);
                    } else {
                        am3.f(com.corrodinggames.rts.game.n.i);
                    }
                    am3.B(null);
                    if (n2 != 1 && am3 instanceof y) {
                        ((y)am3).a(n2);
                    }
                    am3.cP();
                    if (am3 instanceof y) {
                        y y3 = (y)am3;
                        y3.br();
                        if (am3.bI()) {
                            l2.bU.a(y3);
                        }
                    }
                    com.corrodinggames.rts.game.n.c(am3);
                    if (l2.bs == am3.bX && am3.bX != com.corrodinggames.rts.game.n.i && !am3.u() && bl) {
                        l2.b(am3.eo, am3.ep);
                        l2.bS.j(am3);
                    }
                    return;
                }
                com.corrodinggames.rts.gameFramework.l.e("issueCommand: unknown system action:" + this.u);
                return;
            }
            com.corrodinggames.rts.gameFramework.l.e("issueCommand: Null System action");
            return;
        }
        if (this.p != null) {
            this.p.Y = System.currentTimeMillis();
            this.p.Z = l2.by;
        }
        if (this.p != null) {
            object3 = null;
            Object object4 = null;
            object2 = this.v.iterator();
            while (object2.hasNext()) {
                object = (y)object2.next();
                if (((y)object).bX != this.p && !this.a(this.p, ((y)object).bX)) {
                    object3 = object3 == null ? "" : (String)object3 + ", ";
                    if (object4 == null) {
                        object4 = object;
                    }
                    object3 = (String)object3 + ((y)object).eh;
                    object2.remove();
                    continue;
                }
                if (!((am)object).cW()) continue;
                com.corrodinggames.rts.gameFramework.c.a("Warning unit: " + ((y)object).eh + " has canNotBeGivenOrdersByPlayer set");
                object2.remove();
            }
            if (object3 != null) {
                ad.a("Player(" + this.p.k + ") " + this.p.v + " cannot control units: " + (String)object3, true);
                if (object4 != null) {
                    object = "";
                    if (((am)object4).bX != null) {
                        object = (String)object + " targetUnitTeamId: " + ((am)object4).bX.k + " targetUnitTeamName: " + ((am)object4).bX.v;
                    }
                    com.corrodinggames.rts.gameFramework.c.a((String)object);
                }
            }
        }
        if (this.o) {
            for (Object object4 : this.v) {
                ((y)object4).az();
                ((y)object4).R = null;
            }
        }
        if (this.j != null) {
            this.j.c();
            object3 = l2.bV.b();
            ((ab)object3).g = this.w;
            for (int i2 = 0; i2 <= 1; ++i2) {
                boolean bl = i2 == 1;
                for (y y4 : this.v) {
                    au au2;
                    if (y4.ae != bl) continue;
                    if (this.f) {
                        y4.aA();
                        continue;
                    }
                    if (!this.e) {
                        y4.az();
                        continue;
                    }
                    if (!this.h || this.j == null || (au2 = y4.at()) == null || !this.j.a(au2) || au2.d() != av.h && au2.d() != av.a || this.j.d() != av.h && this.j.d() != av.a) continue;
                    y4.au();
                }
            }
            for (y y5 : this.v) {
                if (!y5.a(this.j, com.corrodinggames.rts.gameFramework.c.e < 5)) {
                    object = "";
                    if (this.p != null) {
                        object = "Player(" + this.p.k + ") " + this.p.v + ": ";
                    }
                    com.corrodinggames.rts.gameFramework.c.a((String)object + "isValidNewWaypoint==false on: " + y5.c());
                    continue;
                }
                object = y5.d(this.j);
                ((ab)object3).a(y5, (au)object);
                y5.a((au)object);
            }
            ((ab)object3).b();
            return;
        }
        if (com.corrodinggames.rts.game.units.a.s.c(this.k)) {
            for (Object object4 : this.v) {
                object2 = ((am)object4).a(this.k);
                if (object2 == null) {
                    com.corrodinggames.rts.gameFramework.c.a("Could not find specialAction:" + this.k.a() + " on " + ((am)object4).r().i());
                    continue;
                }
                if (!((s)object2).b((am)object4)) {
                    com.corrodinggames.rts.gameFramework.c.a("!isAvailable specialAction:" + this.k.a() + " on " + ((am)object4).r().i() + " (action being skipped)");
                    if (!com.corrodinggames.rts.gameFramework.c.a) continue;
                    com.corrodinggames.rts.gameFramework.c.a("Command source:" + this.b);
                    continue;
                }
                ((am)object4).a((s)object2);
                an.a((y)object4, (s)object2);
                ((am)object4).a((s)object2, this.g, this.l, this.m);
            }
            object3 = com.corrodinggames.rts.game.units.a.j.a(this.k);
            if (object3 != null) {
                if (l2.bs != null && this.i != null) {
                    if (this.i.d(l2.bs)) {
                        l2.bS.a(this.l.a, this.l.b, this.i, (j)object3);
                    }
                } else {
                    com.corrodinggames.rts.gameFramework.c.a("PingMapAction failed: game.playerTeam==null or this.team==null");
                }
            }
        }
        if (this.n != null) {
            for (Object object4 : this.v) {
                ((y)object4).P = this.n;
            }
        }
        if (this.z != null) {
            for (Object object4 : this.v) {
                if (!(object4 instanceof com.corrodinggames.rts.game.units.d.l)) continue;
                object2 = (com.corrodinggames.rts.game.units.d.l)object4;
                object2.a(this.z);
            }
        }
    }

    public boolean a(n n2, n n3) {
        if (n2 == null || n3 == null) {
            return false;
        }
        if (!n3.d(n2)) {
            return false;
        }
        return (this.q & 1 << n3.k) != 0;
    }

    public boolean l() {
        am am2;
        Object object;
        this.q = 0;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            object = com.corrodinggames.rts.game.n.k(i2);
            if (object == null || !((n)object).p()) continue;
            this.q = (short)(this.q | 1 << i2);
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.c(true) < 127 && this.j != null && this.j.d() == av.c && (object = this.j.a()) != null && (am2 = am.a((com.corrodinggames.rts.game.units.as)object)) != null && !(am2 instanceof y)) {
            com.corrodinggames.rts.gameFramework.l.e("Rejecting non OrderableUnit build order: " + object.i());
            return false;
        }
        if (this.j != null && this.j.n) {
            com.corrodinggames.rts.gameFramework.l.e("Rejecting waypoint with addedByAction true");
            return false;
        }
        return true;
    }
}
