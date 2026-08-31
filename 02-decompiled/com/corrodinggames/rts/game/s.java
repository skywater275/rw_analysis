/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.p;
import com.corrodinggames.rts.game.t;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.gameFramework.utility.m;

public final class s {
    public int a = 5;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public f h = new f();
    public f i = new f();
    public f j = new f();
    public f k = new f();
    public f l = new f();
    public boolean m;
    public int n;
    public int o;
    public t p = new t();
    public t q = new t();

    public void a(am am2) {
        f f2;
        b b2;
        ++this.d;
        if (am2.cm < 1.0f) {
            ++this.f;
        } else {
            ++this.c;
        }
        as as2 = am2.r();
        if (!as2.k()) {
            ++this.b;
        }
        if (!this.m && !am2.u() && am2.r().y()) {
            this.m = true;
        }
        if ((b2 = am2.dq()) != null) {
            this.k.a(b2, 0.0, Double.MAX_VALUE);
            this.l.a(b2, -1.7976931348623157E308, 0.0);
        }
        if (am2 instanceof l) {
            l l2 = (l)((Object)am2);
            int n2 = l2.f(false);
            this.b += n2;
            this.e += n2;
            if (n2 != 0) {
                this.a(l2);
            }
        }
        this.c(am2);
        float f3 = am2.cy();
        if (f3 != 0.0f && am2.cm >= 1.0f) {
            this.g = (int)((float)this.g + f3);
        }
        if (!(f2 = am2.cA()).c() && am2.cm >= 1.0f) {
            this.h.b(f2);
            this.i.a(f2, 0.0, Double.MAX_VALUE);
            this.j.a(f2, -1.7976931348623157E308, 0.0);
        }
        if (am2.cU()) {
            int n3 = am2.cM().b();
            b b3 = as2.B();
            if (b3 != null) {
                n3 += b3.b();
            }
            if (as2.j()) {
                this.o += n3;
            } else {
                this.n += n3;
            }
        }
    }

    public void b(am am2) {
        f f2;
        b b2;
        --this.d;
        if (am2.cm < 1.0f) {
            --this.f;
        } else {
            --this.c;
        }
        as as2 = am2.r();
        if (!as2.k()) {
            --this.b;
        }
        if ((b2 = am2.dq()) != null) {
            this.k.b(b2, 0.0, Double.MAX_VALUE);
            this.l.b(b2, -1.7976931348623157E308, 0.0);
        }
        if (am2 instanceof l) {
            l l2 = (l)((Object)am2);
            int n2 = l2.f(false);
            this.b -= n2;
            this.e -= n2;
            if (n2 != 0) {
                this.b(l2);
            }
        }
        this.d(am2);
        float f3 = am2.cy();
        if (f3 != 0.0f && am2.cm >= 1.0f) {
            this.g = (int)((float)this.g - f3);
        }
        if (!(f2 = am2.cA()).c() && am2.cm >= 1.0f) {
            this.h.c(f2);
            this.i.b(f2, 0.0, Double.MAX_VALUE);
            this.j.b(f2, -1.7976931348623157E308, 0.0);
        }
        if (am2.cU()) {
            int n3 = am2.cM().b();
            b b3 = as2.B();
            if (b3 != null) {
                n3 += b3.b();
            }
            if (as2.j()) {
                this.o -= n3;
            } else {
                this.n -= n3;
            }
        }
    }

    private final void c(am am2) {
        h h2 = am2.de();
        if (h2 != null) {
            for (g g2 : h2.a) {
                p p2 = this.a(g2);
                if (am2.cm < 1.0f) {
                    ++p2.c;
                    continue;
                }
                ++p2.b;
            }
        }
    }

    private final void d(am am2) {
        h h2 = am2.de();
        if (h2 != null) {
            for (g g2 : h2.a) {
                p p2 = this.a(g2);
                if (am2.cm < 1.0f) {
                    --p2.c;
                    continue;
                }
                --p2.b;
            }
        }
    }

    private final void a(l l2) {
        m m2 = l2.dx();
        if (m2.a != 0) {
            for (j j2 : m2) {
                h h2;
                as as2;
                if (!j2.f || (as2 = j2.g) == null || (h2 = as2.x()) == null) continue;
                for (g g2 : h2.a) {
                    p p2 = this.a(g2);
                    p2.d += j2.a;
                }
            }
        }
    }

    private final void b(l l2) {
        m m2 = l2.dx();
        if (m2.a != 0) {
            for (j j2 : m2) {
                h h2;
                as as2;
                if (!j2.f || (as2 = j2.g) == null || (h2 = as2.x()) == null) continue;
                for (g g2 : h2.a) {
                    p p2 = this.a(g2);
                    p2.d -= j2.a;
                }
            }
        }
    }

    public final p a(g g2) {
        p[] pArray = this.q.b;
        int n2 = this.q.c;
        for (int i = 0; i < n2; ++i) {
            p p2 = pArray[i];
            if (p2.a == g2) {
                return p2;
            }
            if (p2.a != null) continue;
            p2.a = g2;
            return p2;
        }
        p p3 = new p();
        p3.a = g2;
        this.q.a(p3);
        return p3;
    }
}
