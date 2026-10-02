/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.m.e;

public class h
extends s {
    s a;
    b b = com.corrodinggames.rts.game.units.a.b.emptyActionFilter;
    boolean c;
    public int d = 0;
    public boolean e;
    public final int f = Color.a(255, 50, 50, 50);

    @Override
    public float m_() {
        return this.a.m_();
    }

    @Override
    public int a(s s2) {
        return this.a.a(s2);
    }

    @Override
    public String b() {
        return this.a.b();
    }

    @Override
    public String d(am am2) {
        return this.a.d(am2);
    }

    @Override
    public String a() {
        String string = this.a.a();
        return string;
    }

    @Override
    public String e(am am2) {
        return this.a.e(am2);
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        return this.a.b(am2, bl);
    }

    @Override
    public boolean n_() {
        return this.a.n_();
    }

    @Override
    public boolean a(am am2, boolean bl) {
        if (this.c) {
            return this.a.a(am2, bl);
        }
        return true;
    }

    @Override
    public int t() {
        return this.a.t();
    }

    @Override
    public void f(am am2) {
        this.a.f(am2);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof h) {
            return this.a.equals(((h)object).a);
        }
        return false;
    }

    @Override
    public boolean g(am am2) {
        return this.a.g(am2);
    }

    @Override
    public boolean b(am am2) {
        if (!this.b.isAvailable(this, am2)) {
            return false;
        }
        return this.a.b(am2);
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
        return this.a.d();
    }

    @Override
    public boolean h_() {
        return this.a.h_();
    }

    @Override
    public void a(am am2, ae ae2, Paint paint, Paint paint2) {
        this.a.a(am2, ae2, paint, paint2);
    }

    @Override
    public void a(am am2, ae ae2) {
        this.a.a(am2, ae2);
        as as2 = this.a.i();
        if (as2 != null && as2 instanceof l) {
            l l2 = (l)as2;
            if (l2.J != null) {
                String string = l2.J.a();
                string = com.corrodinggames.rts.gameFramework.f.a(string, 30);
                ae2.a("\n(mod: " + string + ")", this.f, true);
            }
        }
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
        return this.a.i(am2);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public h(s s2, b b2) {
        this(s2, b2, false);
    }

    public h(s s2, b b2, boolean bl2) {
        super(s2.N());
        this.a = s2;
        this.b = b2;
        this.e(this.a.N());
        this.g = this.a.g;
        this.c = bl2;
    }

    public s q_() {
        return this.a;
    }

    @Override
    public boolean x() {
        return true;
    }

    @Override
    public boolean s() {
        if (!this.b.isAvailable(this, null)) {
            return false;
        }
        if (this.c) {
            return this.a.s();
        }
        return true;
    }

    @Override
    public as y() {
        return this.a.y();
    }

    @Override
    public boolean c(am am2, boolean bl2) {
        return this.a.c(am2, bl2);
    }

    @Override
    public boolean a(am am2) {
        return this.a.a(am2);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((s)object);
    }
}
