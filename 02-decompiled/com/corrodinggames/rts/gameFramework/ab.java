/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.aa;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.Collection;

public class ab {
    m a = new m();
    boolean b;
    float c;
    float d;
    int e;
    boolean f;
    public m g;
    final /* synthetic */ aa h;

    public ab(aa aa2) {
        this.h = aa2;
    }

    public void a(y y2, au au2) {
        au2.i = this;
        this.f = au2.j;
    }

    public void a(au au2) {
        for (y y2 : this.a) {
            au au3;
            if (y2.bV || (au3 = y2.ar()) == null || !au3.b(au2)) continue;
            y2.ay();
        }
    }

    public void a() {
        this.a.clear();
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            au au2;
            y y2;
            am am2 = amArray[i2];
            if (!(am2 instanceof y) || !(y2 = (y)am2).I() || (au2 = y2.ar()) == null || au2.i != this || !y2.bg()) continue;
            this.a.add(y2);
            this.c = au2.g();
            this.d = au2.h();
        }
    }

    public void a(y y2) {
        y2.ac = this.e;
        au au2 = y2.ar();
        if (au2 != null) {
            au2.i = this;
        }
    }

    public void b() {
        long l2 = br.a();
        this.c();
    }

    public y a(m m2, float f2, float f3, boolean bl) {
        float f4 = -1.0f;
        y y2 = null;
        for (y y3 : m2) {
            if (!bl && (y3.ad != null || y3.ae)) continue;
            float f5 = com.corrodinggames.rts.gameFramework.f.b(f2, f3, y3.eo, y3.ep);
            if (y3.af) {
                f5 -= 160.0f;
            }
            if (f4 != -1.0f && !(f5 < f4)) continue;
            f4 = f5;
            y2 = y3;
        }
        return y2;
    }

    public m a(float f2, float f3, boolean bl) {
        y y2;
        m m2 = new m(1);
        m m3 = new m();
        m3.clear();
        m3.addAll((Collection)this.a);
        while ((y2 = this.a(m3, f2, f3, true)) != null) {
            m2.add(y2);
            m3.remove(y2);
            m m4 = this.a(m3, y2, true, bl);
            m3.removeAll(m4);
        }
        return m2;
    }

    public m a(m m2, y y2, boolean bl, boolean bl2) {
        int n2;
        m m3 = new m(1);
        m3.clear();
        int n3 = 0;
        boolean bl3 = false;
        int n4 = 25;
        Object[] objectArray = m2.a();
        int n5 = m2.size();
        for (n2 = 0; n2 < n5; ++n2) {
            y y3 = (y)objectArray[n2];
            y3.ap = false;
        }
        for (n2 = 0; n2 <= 2; ++n2) {
            int n6 = m2.size();
            for (n5 = 0; n5 < n6; ++n5) {
                y y4 = (y)objectArray[n5];
                if (y4.ap || y4 == y2 || !bl && (y4.ad != null || y4.ae) || y4.h() != y2.h()) continue;
                float f2 = com.corrodinggames.rts.gameFramework.f.a(y4.eo, y4.ep, y2.eo, y2.ep);
                if (n2 == 0 && f2 > 3600.0f || n2 == 1 && f2 > 14400.0f || !(bl2 && f2 < 160000.0f) && (!(f2 < 40000.0f) || n3 >= 25) || !bl2 && !(com.corrodinggames.rts.gameFramework.f.c(y4.z() - y2.z()) < 0.4f)) continue;
                y4.ap = true;
                m3.add(y4);
                ++n3;
            }
        }
        return m3;
    }

    public void c() {
        l l2 = l.B();
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        long l3 = br.a();
        this.a();
        this.h.b.a(0.0f, 0.0f);
        for (Object object : this.a) {
            this.h.b.b(((y)object).eo, ((y)object).ep);
        }
        this.h.b.a(this.h.b.a / (float)this.a.size(), this.h.b.b / (float)this.a.size());
        float f6 = com.corrodinggames.rts.gameFramework.f.d(this.h.b.a, this.h.b.b, this.c, this.d);
        for (y y2 : this.a) {
            float f7;
            y2.af = y2.ah > 1 ? y2.ae : false;
            if (y2.af && y2.ah > 7 && com.corrodinggames.rts.gameFramework.f.c(f7 = com.corrodinggames.rts.gameFramework.f.c(y2.am, f6, 360.0f)) > 80.0f) {
                y2.af = false;
            }
            y2.aB();
            y2.ah = 0;
            y2.an = l2.by;
            y2.ac = this.e;
        }
        int n2 = 0;
        while (true) {
            Object[] objectArray2;
            y y2;
            y2 = null;
            long l4 = br.a();
            y2 = this.a(this.a, this.c, this.d, false);
            if (y2 == null) {
                return;
            }
            y2.ae = true;
            ab ab2 = null;
            if (n2 > 0) {
                ab2 = this.h.b();
            }
            if (ab2 != null) {
                ab2.g = this.g;
                ab2.a(y2);
            }
            m m2 = this.a(this.a, y2, false, this.f);
            int n3 = 0;
            float f8 = 0.0f;
            for (Object[] objectArray2 : m2) {
                if (objectArray2.cj > f8) {
                    f8 = objectArray2.cj;
                }
                objectArray2.a(y2);
                if (ab2 != null) {
                    ab2.a((y)objectArray2);
                }
                ++n3;
            }
            if (y2 != null) {
                y2.ah = (short)(n3 + 1);
            }
            m m3 = new m();
            objectArray2 = this.a.a();
            int n4 = this.a.size();
            for (int i2 = 0; i2 < n4; ++i2) {
                y y3 = (y)objectArray2[i2];
                if (y3.ad != y2) continue;
                m3.add(y3);
            }
            m m4 = this.h.a(n3, f8, f6);
            long l5 = br.a();
            this.h.a(m3, y2, m4, f6, n3);
            long l6 = br.a();
            this.h.a(m3, y2);
            ++n2;
        }
    }
}
