/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.d.f;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public strictfp class k {
    y a;
    public PointF b = null;
    public final m c = new m();
    final m d = new m();
    public float e;
    j f;

    public k(y y2) {
        this.a = y2;
    }

    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.e);
        as2.a(this.c.size());
        for (bq bq2 : this.c) {
            bq2.a(as2);
        }
        as2.a(this.b != null);
        if (this.b != null) {
            as2.a(this.b.a);
            as2.a(this.b.b);
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.j.k k2) {
        int n2;
        this.e = k2.g();
        int n3 = k2.f();
        this.c.clear();
        for (n2 = 0; n2 < n3; ++n2) {
            j j2 = new j();
            j2.a(k2);
            if (s.c(j2.j)) {
                s s2 = this.a.a(j2.j);
                if (s2 == null) {
                    com.corrodinggames.rts.gameFramework.l.b("Factory", this.a.r() + " no longer has the action:" + j2.j);
                    continue;
                }
                this.c.add(j2);
                continue;
            }
            com.corrodinggames.rts.gameFramework.l.b("Factory", "buildQueue has uIndex of -1, skipping");
        }
        if (k2.b() >= 5) {
            n2 = k2.e() ? 1 : 0;
            if (n2 != 0) {
                if (this.b == null) {
                    this.b = new PointF();
                }
                this.b.a = k2.g();
                this.b.b = k2.g();
            } else {
                this.b = null;
            }
        }
    }

    public am a(j j2, float f2, boolean bl, float f3) {
        s s2 = this.a.a(j2.j);
        if (s2 == null) {
            ad.a("specialAction=null on completeQueueItem for item.uIndex:" + j2.j + " id:" + this.a.eh, true);
            return null;
        }
        as as2 = s2.i();
        if (as2 == null) {
            ad.a("unitType=null on completeQueueItem for item.uIndex:" + j2.j + " id:" + this.a.eh, false);
            return null;
        }
        return this.a(as2, f2, bl, f3);
    }

    public void a(am am2, float f2, boolean bl) {
        am2.cl = 30.0f;
        if (this.a instanceof f) {
            am2.cl += 40.0f;
        }
        if (am2 instanceof y) {
            y y2 = (y)am2;
            y2.j(90.0f);
            if ((double)y2.z() < 0.75) {
                am2.cl += 30.0f;
            }
            if ((double)y2.z() < 0.55) {
                am2.cl += 20.0f;
            }
            float f3 = bl ? 0.0f : 1.0f;
            float f4 = f2;
            float f5 = this.a.eo + com.corrodinggames.rts.gameFramework.f.k(am2.cg) * f4;
            float f6 = this.a.ep + com.corrodinggames.rts.gameFramework.f.j(am2.cg) * f4;
            if (this.b != null) {
                if (f4 != 0.0f) {
                    y2.d(f5, f6);
                }
                y2.d(this.b.a + f3, this.b.b);
            } else {
                f5 -= com.corrodinggames.rts.gameFramework.f.j(am2.cg) * f3;
                f6 += com.corrodinggames.rts.gameFramework.f.k(am2.cg) * f3;
                if (f4 != 0.0f) {
                    y2.d(f5, f6);
                }
            }
        }
    }

    public am a(as as2, float f2, boolean bl, float f3) {
        am am2 = null;
        am2 = as2.a();
        am2.eo = this.a.eo;
        am2.ep = this.a.ep + 5.0f;
        am2.cg = 90.0f + f3;
        am2.f(this.a.bX);
        am2.B(this.a);
        this.a(am2, f2, bl);
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (am2.bX == l2.bs) {
            l2.bS.i.a(am2);
        }
        return am2;
    }

    public final boolean a() {
        return this.c.a == 0;
    }

    public j a(w w2, boolean bl) {
        return this.a(w2, bl, null, null);
    }

    public j a(w w2, boolean bl, PointF pointF, am am2) {
        j j2 = new j();
        j2.j = w2.N();
        j2.h = pointF;
        j2.i = am2;
        if (j2.j == null) {
            throw new RuntimeException("item.uIndex==null??");
        }
        j2.a = 1;
        j2.b = w2.K();
        j2.c = w2.B();
        j2.d = w2.r_();
        j2.e = w2.P();
        j2.f = w2.g();
        j2.g = w2.i();
        j2.l = w2.H();
        if (!bl) {
            n.b((am)this.a);
            if (j2.l) {
                int n2 = 0;
                for (int i2 = 0; i2 < this.c.size() && ((j)this.c.get((int)i2)).l; ++i2) {
                    n2 = i2 + 1;
                }
                if (n2 != 0 || this.c.size() != 0) {
                    // empty if block
                }
                this.c.add(n2, j2);
            } else {
                this.c.add(j2);
            }
            n.c(this.a);
        } else {
            this.d.add(j2);
        }
        return j2;
    }

    public j b(w w2, boolean bl) {
        if (bl) {
            if (this.a(w2.N(), true) > 0) {
                j j2 = this.a(w2, true);
                j2.k = true;
                return j2;
            }
            return null;
        }
        m m2 = this.c;
        ListIterator listIterator = m2.listIterator(m2.size());
        while (listIterator.hasPrevious()) {
            j j3 = (j)listIterator.previous();
            if (!j3.j.equals(w2.N())) continue;
            n.b((am)this.a);
            listIterator.remove();
            n.c(this.a);
            return j3;
        }
        return null;
    }

    public void a(j j2) {
        this.f = j2;
        this.a.bC();
    }

    public j b() {
        return this.f;
    }

    public b c() {
        if (this.f == null) {
            return null;
        }
        if (this.f.d == null) {
            return null;
        }
        float f2 = this.f.b * this.a.cx() * 60.0f;
        return com.corrodinggames.rts.game.units.custom.d.b.a(this.f.d, -f2);
    }

    public s d() {
        if (this.f != null) {
            s s2 = this.a.a(this.f.j);
            return s2;
        }
        return null;
    }

    public void a(float f2) {
        if (!this.a()) {
            j j2 = (j)this.f().get(0);
            if (this.f != j2) {
                if (j2.m < 0.0f) {
                    j2.m = 0.0f;
                    ((l)((Object)this.a)).b(j2);
                }
                if (this.f != null) {
                    this.e = j2.m;
                }
                this.a(j2);
            }
            float f3 = j2.b * this.a.cx() * f2;
            boolean bl = false;
            if (j2.d != null) {
                if (this.e + f3 > 1.0f) {
                    f3 = 1.0f - this.e;
                    bl = true;
                }
                double d2 = (double)(this.e + f3) - j2.n;
                double d3 = 0.0;
                if (bl) {
                    d3 = 1.0 - j2.n;
                } else {
                    double d4 = 0.01f;
                    if (d2 >= d4) {
                        int n2 = (int)(d2 / d4);
                        d3 = (double)n2 * d4;
                    }
                }
                boolean bl2 = false;
                if (d3 > 0.0 && this.a.bX.am.a(j2.d)) {
                    bl2 = true;
                }
                if (!bl2 && (d3 <= 0.0 || j2.d.c((am)this.a, d3))) {
                    j2.n += d3;
                } else {
                    if (!bl2) {
                        this.a.bX.am.a(j2.d, (am)this.a, d3);
                    }
                    f3 = 0.0f;
                    bl = false;
                }
            }
            this.e += f3;
            if (bl) {
                this.e = 1.0f;
            }
            j2.m = this.e;
            if (this.e >= 1.0f) {
                if (j2.f && ((l)((Object)this.a)).dA()) {
                    this.e = 1.0f;
                } else {
                    n.b((am)this.a);
                    this.e = 0.0f;
                    --j2.a;
                    if (j2.a <= 0) {
                        List list = this.f();
                        if (list.size() == 0) {
                            com.corrodinggames.rts.gameFramework.l.b("-------------buildQueue empty for:" + j2.j);
                            com.corrodinggames.rts.gameFramework.l.b("-------------");
                        } else {
                            list.remove(0);
                        }
                    }
                    n.c(this.a);
                    ((l)((Object)this.a)).a(j2);
                }
            }
        } else {
            this.a((j)null);
            this.e = 0.0f;
            if (this.d.a > 0) {
                j j3 = (j)this.d.get(0);
                if (j3.b > 10.0f && j3.m <= 0.0f) {
                    j3.m = 1.0f;
                    s s2 = this.a.a(j3.j);
                    if (s2 != null && s2.Q()) {
                        s2.a(this.a);
                    }
                }
            }
        }
    }

    public void e() {
        Iterator iterator = this.c.iterator();
        while (iterator.hasNext()) {
            j j2 = (j)iterator.next();
            s s2 = this.a.a(j2.j);
            if (s2 != null) continue;
            this.b(j2);
            this.c(j2);
            iterator.remove();
        }
    }

    public void a(boolean bl) {
        Iterator iterator = this.c.iterator();
        while (iterator.hasNext()) {
            j j2 = (j)iterator.next();
            if (bl) {
                this.b(j2);
            }
            this.c(j2);
            iterator.remove();
        }
    }

    private void b(j j2) {
        if (((l)((Object)this.a)).c(j2)) {
            if (j2.d != null && j2.n > 0.0) {
                j2.d.a((am)this.a, j2.n, true);
            }
            j2.c.h(this.a);
        }
    }

    private void c(j j2) {
    }

    public int a(as as2) {
        int n2 = 0;
        int n3 = this.c.a;
        if (n3 != 0) {
            Object[] objectArray = this.c.a();
            for (int i2 = 0; i2 < n3; ++i2) {
                as as3;
                j j2 = (j)objectArray[i2];
                if (!j2.f || (as3 = j2.g) != as2) continue;
                n2 += j2.a;
            }
        }
        return n2;
    }

    public int a(c c2, boolean bl) {
        return this.a(c2, bl, false);
    }

    public int a(g g2) {
        if (g2 == null) {
            return this.c.a;
        }
        int n2 = 0;
        for (j j2 : this.c) {
            if (!g.a(g2, j2.e)) continue;
            ++n2;
        }
        return n2;
    }

    public int a(c c2, boolean bl, boolean bl2) {
        int n2 = 0;
        if (this.c.a != 0) {
            for (j j2 : this.c) {
                if (s.i != c2 && !j2.j.equals(c2) || bl2 && !j2.f) continue;
                n2 += j2.a;
            }
        }
        if (bl && this.d.a != 0) {
            for (j j2 : this.d) {
                if (s.i != c2 && !j2.j.equals(c2) || bl2 && !j2.f) continue;
                if (!j2.k) {
                    n2 += j2.a;
                    continue;
                }
                n2 -= j2.a;
            }
        }
        return n2;
    }

    public s b(as as2) {
        ArrayList arrayList = this.a.N();
        int n2 = arrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            w w2;
            s s2 = (s)arrayList.get(i2);
            if (s2 == null || !(s2 instanceof w) || (w2 = (w)s2).i() != as2) continue;
            return w2;
        }
        return null;
    }

    public j a(s s2, boolean bl, PointF pointF, am am2) {
        if (s2 instanceof w) {
            w w2 = (w)s2;
            if (!bl) {
                if (s2.a((am)this.a, false) && s2.b(this.a) && (!w2.g() || this.a.bX.w() < this.a.bX.x()) && w2.B().c(this.a)) {
                    return this.a(w2, false, pointF, am2);
                }
            } else {
                j j2 = this.b(w2, false);
                if (j2 != null) {
                    this.b(j2);
                    this.c(j2);
                    return j2;
                }
            }
        }
        return null;
    }

    public void a(s s2, boolean bl) {
        if (s2 instanceof w) {
            w w2 = (w)s2;
            if (!bl) {
                if (s2.a((am)this.a, true) && (!w2.g() || this.a.bX.w() < this.a.bX.x()) && w2.B().b((am)this.a, s2.Q())) {
                    this.a(w2, true);
                }
            } else if (this.b(w2, true) != null) {
                w2.B().e(this.a, s2.Q());
            }
        }
    }

    public void a(s s2) {
        if (this.d.size() != 0) {
            j j2 = null;
            for (j j3 : this.d) {
                if (!j3.j.equals(s2.N())) continue;
                j2 = j3;
            }
            if (j2 != null) {
                if (!j2.k) {
                    j2.c.e(this.a, s2.Q());
                } else {
                    j2.c.d(this.a, s2.Q());
                }
                this.d.remove(j2);
            }
        }
    }

    public List f() {
        return this.c;
    }

    public m g() {
        return this.c;
    }
}
