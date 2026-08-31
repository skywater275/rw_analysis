/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public class g
extends s {
    public s a;
    public y b;
    public b c = com.corrodinggames.rts.game.units.a.b.emptyActionFilter;
    static com.corrodinggames.rts.gameFramework.utility.u d;
    static final com.corrodinggames.rts.gameFramework.utility.u e;

    private void K() {
        l l2 = l.B();
        if (d != null) {
            throw new RuntimeException("savedSelectedUnitsCache!=null");
        }
        d = l2.bS.bZ;
        e.clear();
        e.a(this.b);
        l2.bS.bZ = e;
    }

    private void L() {
        l l2 = l.B();
        if (d == null) {
            throw new RuntimeException("savedSelectedUnitsCache==null");
        }
        l2.bS.bZ = d;
        d = null;
        e.clear();
    }

    @Override
    public float m_() {
        return super.m_();
    }

    @Override
    public int a(s s2) {
        return super.a(s2);
    }

    @Override
    public String b() {
        return this.a.b();
    }

    @Override
    public String d(am am2) {
        return this.a.d(this.b);
    }

    @Override
    public String a() {
        String string = this.a.a();
        return string;
    }

    @Override
    public String e(am am2) {
        return this.a.e(this.b);
    }

    @Override
    public int c() {
        return this.a.c();
    }

    @Override
    public int b(am am2, boolean bl) {
        return this.a.b(this.b, bl);
    }

    @Override
    public boolean n_() {
        return this.a.n_();
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return this.a.a((am)this.b, bl);
    }

    @Override
    public int t() {
        return this.a.t();
    }

    @Override
    public void f(am am2) {
        this.a.f(this.b);
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public boolean g(am am2) {
        return this.a.g(this.b);
    }

    @Override
    public boolean u() {
        return this.a.u();
    }

    @Override
    public boolean h() {
        return this.a.h();
    }

    @Override
    public as i() {
        return this.a.i();
    }

    @Override
    public boolean g() {
        return this.a.g();
    }

    @Override
    public u e() {
        return this.a.e();
    }

    @Override
    public t f() {
        return this.a.f();
    }

    @Override
    public String d() {
        this.K();
        String string = this.a.d();
        this.L();
        return string;
    }

    @Override
    public boolean h_() {
        return this.a.h_();
    }

    @Override
    public void a(am am2, ae ae2, Paint paint, Paint paint2) {
        this.K();
        this.a.a(this.b, ae2, paint, paint2);
        this.L();
    }

    @Override
    public void a(am am2, ae ae2) {
        this.K();
        this.a.a((am)this.b, ae2);
        this.L();
    }

    @Override
    public e j() {
        return this.a.j();
    }

    @Override
    public e h(am am2) {
        return this.a.h(am2);
    }

    @Override
    public Rect v() {
        return this.a.v();
    }

    @Override
    public am i(am am2) {
        return this.a.i(this.b);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public g(s s2, y y2, c c2) {
        super(c2);
        this.a = s2;
        this.b = y2;
        this.g = this.a.g;
    }

    public s p_() {
        return this.a;
    }

    @Override
    public boolean x() {
        return this.a.x();
    }

    @Override
    public boolean s() {
        return this.a.s();
    }

    @Override
    public as y() {
        return this.a.y();
    }

    @Override
    public c z() {
        return this.a.N();
    }

    @Override
    public void a(am am2, am am3) {
        super.a(am2, am3);
    }

    @Override
    public boolean a(am am2, n n2) {
        return this.a.a((am)this.b, n2);
    }

    @Override
    public boolean A() {
        return this.a.A();
    }

    @Override
    public boolean a(am am2) {
        return this.a.a((am)this.b);
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b B() {
        return this.a.B();
    }

    @Override
    public String j(am am2) {
        return this.a.j(this.b);
    }

    @Override
    public boolean d(am am2, boolean bl) {
        return this.a.d(this.b, bl);
    }

    @Override
    public boolean k(am am2) {
        return this.a.k(this.b);
    }

    @Override
    public boolean l(am am2) {
        return this.a.l(this.b);
    }

    @Override
    public boolean C() {
        return this.a.C();
    }

    @Override
    public boolean D() {
        return this.a.D();
    }

    @Override
    public as E() {
        return this.a.E();
    }

    @Override
    public boolean F() {
        return this.a.F();
    }

    @Override
    public boolean m(am am2) {
        return this.a.m(this.b);
    }

    @Override
    public boolean n(am am2) {
        return this.a.n(this.b);
    }

    @Override
    public boolean c(am am2, boolean bl) {
        return this.a.c(this.b, bl);
    }

    @Override
    public boolean o(am am2) {
        return this.a.o(this.b);
    }

    @Override
    public boolean G() {
        return this.a.G();
    }

    @Override
    public void c(am am2) {
        this.a.c(this.b);
    }

    @Override
    public float l() {
        return this.a.l();
    }

    @Override
    public int m() {
        return this.a.m();
    }

    @Override
    public boolean H() {
        return this.a.H();
    }

    @Override
    public boolean I() {
        return this.a.I();
    }

    @Override
    public float p(am am2) {
        return this.a.p(this.b);
    }

    @Override
    public ArrayList q(am am2) {
        return this.a.q(this.b);
    }

    @Override
    public boolean r(am am2) {
        if (!this.c.isAvailable(this, am2)) {
            return false;
        }
        return this.a.r(this.b);
    }

    @Override
    public boolean b(am am2) {
        if (!this.c.isAvailable(this, am2)) {
            return false;
        }
        return this.a.b(this.b);
    }

    @Override
    public int J() {
        return this.a.J();
    }

    @Override
    public boolean s(am am2) {
        return this.a.s(this.b);
    }

    @Override
    public boolean t(am am2) {
        return this.a.t(this.b);
    }

    public boolean a(g g2) {
        return this.a == g2.a && this.b == g2.b && this.N() == g2.N() && this.c == g2.c;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((s)object);
    }

    static {
        e = new com.corrodinggames.rts.gameFramework.utility.u();
    }
}
