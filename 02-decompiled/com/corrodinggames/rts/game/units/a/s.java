/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.a;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.o;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public abstract class s
implements Comparable {
    public float g = -999.0f;
    public a h = com.corrodinggames.rts.game.units.a.a.a;
    public static final c i = c.a;
    private c a;
    private b b;

    public float m_() {
        if (this instanceof o) {
            return -100.0f;
        }
        if (this.g != -999.0f) {
            return this.g;
        }
        as as2 = this.i();
        if (as2 != null && this.g()) {
            return as2.g();
        }
        return 1.0f;
    }

    public int a(s s2) {
        if (s2 == null) {
            return 0;
        }
        float f = this.m_() - s2.m_();
        if (f < 0.0f) {
            return -1;
        }
        if (f > 0.0f) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        s s2 = (s)object;
        return this.a.equals(s2.a);
    }

    public static final boolean b(c c2) {
        return c2 == null || c2 == i;
    }

    public static final boolean c(c c2) {
        return !s.b(c2);
    }

    public static boolean a(s s2, s s3) {
        return s2 == s3;
    }

    public final boolean d(c c2) {
        return this.a == c2;
    }

    public s(int n2) {
        this.a(String.valueOf(n2));
    }

    public s(String string) {
        this.a(string);
    }

    public s(c c2) {
        this.e(c2);
    }

    public final void a(String string) {
        this.a = c.a(string);
    }

    public final void e(c c2) {
        this.a = c2;
    }

    public final c N() {
        return this.a;
    }

    public c z() {
        return this.N();
    }

    public final String O() {
        if (this.a == null) {
            return "<null index>";
        }
        return this.a.a();
    }

    public abstract String b();

    public abstract String a();

    public h P() {
        return null;
    }

    public String d(am am2) {
        return this.b();
    }

    public String e(am am2) {
        return this.a();
    }

    public abstract int c();

    public b B() {
        b b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        int n2 = this.c();
        if (n2 == 0) {
            return com.corrodinggames.rts.game.units.custom.d.b.a;
        }
        if (this.b == null || this.b.a() != n2) {
            this.b = com.corrodinggames.rts.game.units.custom.d.b.a(n2);
        }
        return this.b;
    }

    public b r_() {
        if (this.h.b() != null) {
            return this.h.b();
        }
        return null;
    }

    public abstract int b(am var1, boolean var2);

    public boolean n_() {
        return false;
    }

    public boolean g(am am2) {
        return this.h.b(am2);
    }

    public String j(am am2) {
        return this.h.c(am2);
    }

    public void a(am am2, am am3) {
        this.h.a(am2, am3);
    }

    public boolean d(am am2, boolean bl) {
        return true;
    }

    public boolean k(am am2) {
        return false;
    }

    public boolean l(am am2) {
        return false;
    }

    public boolean a(am am2, boolean bl) {
        if (this.g(am2)) {
            return false;
        }
        if (com.corrodinggames.rts.game.units.g.e.a(am2, this.N()) > 0) {
            return false;
        }
        if (bl) {
            return this.B().c(am2, this.Q());
        }
        return this.B().b(am2);
    }

    public boolean r(am am2) {
        return this.b(am2);
    }

    public boolean u(am am2) {
        return this.h.a(am2);
    }

    public boolean b(am am2) {
        return this.h.a(am2, false);
    }

    public boolean a(am am2, n n2) {
        return false;
    }

    public boolean u() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public boolean C() {
        return false;
    }

    public boolean D() {
        return true;
    }

    public boolean A() {
        return false;
    }

    public abstract as i();

    public as y() {
        return null;
    }

    public as E() {
        return null;
    }

    public boolean F() {
        return false;
    }

    public int t() {
        return 1;
    }

    public abstract boolean g();

    public abstract u e();

    public boolean o() {
        return false;
    }

    public abstract t f();

    public boolean m(am am2) {
        return false;
    }

    public boolean n(am am2) {
        return false;
    }

    public e v(am am2) {
        return null;
    }

    public String d() {
        String string = null;
        l l2 = l.B();
        int n2 = 0;
        am[] amArray = l2.bS.bZ.a();
        int n3 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4;
            am am2 = amArray[i2];
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (string == null) {
                string = this.d(y2);
            }
            if ((n4 = this.b(y2, true)) == -1 || n4 == 0) continue;
            n2 += n4;
        }
        if (string == null) {
            string = this.b();
        }
        if (n2 != -1 && n2 != 0) {
            string = string + " (" + n2 + ")";
        }
        return string;
    }

    public boolean h_() {
        return true;
    }

    public String w(am am2) {
        return this.d(am2);
    }

    public void a(am am2, ae ae2, Paint paint, Paint paint2) {
        b b2;
        int n2;
        Object object;
        Paint paint3 = ae2.g;
        if (paint != null) {
            ae2.a(paint);
        }
        if (this.h_() && (object = this.w(am2)) != null && !((String)object).equals("")) {
            ae2.b((String)object);
        }
        if (paint != null) {
            ae2.a(paint3);
        }
        object = this.f();
        b b3 = this.B();
        if (!b3.c() && object != t.i) {
            boolean bl = true;
            ae2.b(" (");
            am am3 = null;
            n2 = 0;
            if (paint2 != null) {
                am3 = am2;
                n2 = paint2.e();
            }
            b3.a(ae2, false, true, 5, bl, am3, n2);
            ae2.b(")");
        }
        if ((b2 = this.r_()) != null && !b2.c() && object != t.i) {
            boolean bl = true;
            ae2.b(" (");
            n2 = 0;
            b2.a(ae2, false, true, 5, bl, null, n2);
            ae2.b(")");
        }
    }

    public void a(am am2, ae ae2) {
        String string;
        String string2 = com.corrodinggames.rts.gameFramework.f.a.a(this, false);
        if (string2 != null && !"".equals(string2)) {
            string2 = string2.trim();
            ae2.b("\n" + string2);
        }
        if ((string = this.e(am2)) != null && !"".equals(string)) {
            string = string.trim();
            ae2.b("\n" + string);
        }
    }

    public boolean c(am am2, boolean bl) {
        return false;
    }

    public void f(am am2) {
    }

    public com.corrodinggames.rts.gameFramework.m.e j() {
        if (this.f() == t.c) {
            return l.B().bS.bk;
        }
        return null;
    }

    public com.corrodinggames.rts.gameFramework.m.e h(am am2) {
        return null;
    }

    public int J() {
        return Color.a(100, 255, 255, 255);
    }

    public Rect v() {
        return null;
    }

    public am i(am am2) {
        return null;
    }

    public boolean s(am am2) {
        return true;
    }

    public boolean t(am am2) {
        return true;
    }

    public boolean a(am am2) {
        return this.h.d(am2);
    }

    public boolean s() {
        return false;
    }

    public boolean o(am am2) {
        return true;
    }

    public boolean G() {
        return false;
    }

    public void c(am am2) {
    }

    public float l() {
        return 1.0f;
    }

    public int m() {
        return -1;
    }

    public boolean H() {
        return false;
    }

    public boolean I() {
        return false;
    }

    public boolean x() {
        return false;
    }

    public float p(am am2) {
        return -1.0f;
    }

    public ArrayList q(am am2) {
        return null;
    }

    public ad M() {
        return null;
    }

    public boolean o_() {
        return false;
    }

    public boolean Q() {
        return false;
    }

    public void a(y y2) {
    }

    public boolean a(float f2, float f3) {
        return false;
    }

    public boolean p() {
        return false;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((s)object);
    }
}
