/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.a;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.l;

public class g
extends w {
    public d a;
    public v b;
    public e c = e.b;

    public g(d d2, v v2) {
        super((String)null);
        String string = "";
        if (d2.k != null) {
            string = string + d2.k;
        }
        string = string + "_" + d2.a;
        if (d2.b != null) {
            string = d2.b;
        }
        this.a(string);
        this.a = d2;
        this.b = v2;
        if (d2.J != null) {
            this.b = d2.J;
        }
        this.c = d2.aN;
        if (this.c == e.a) {
            boolean bl2 = false;
            boolean bl3 = false;
            if (d2.ag != null && d2.ah == null) {
                bl3 = true;
            }
            if (d2.q.d()) {
                bl2 = true;
                this.c = e.c;
            }
            this.c = bl2 && !bl3 ? e.c : e.d;
            if (d2.I != null) {
                this.c = e.e;
            }
        }
    }

    @Override
    public h P() {
        return this.a.s;
    }

    @Override
    public boolean F() {
        return true;
    }

    @Override
    public boolean d(am am2, boolean bl2) {
        return this.a.M;
    }

    @Override
    public boolean k(am am2) {
        return this.a.O;
    }

    @Override
    public boolean l(am am2) {
        return this.a.P;
    }

    @Override
    public boolean u() {
        return super.u();
    }

    @Override
    public boolean a(am am2, boolean bl2) {
        j j2 = (j)am2;
        if (!this.a.N && j2.a(this.N(), bl2) > 0) {
            return false;
        }
        if (this.a.u != null && (bl2 && this.Q() ? !an.a(this.a.u, j2) : !this.a.u.read(j2))) {
            return false;
        }
        return super.a(am2, bl2);
    }

    @Override
    public boolean g(am am2) {
        if (this.a(am2, -1)) {
            return true;
        }
        return super.g(am2);
    }

    public boolean a(am am2, int n2) {
        if (this.a.z != null && (n2 == -1 || n2 == 1)) {
            if (!(am2 instanceof j)) {
                l.n("CustomActionConfig lockedInGame:" + am2.r().i() + " is not a custom unit");
                return false;
            }
            if (this.a.z.read((j)am2)) {
                return true;
            }
        }
        if (this.a.B != null && (n2 == -1 || n2 == 2)) {
            if (!(am2 instanceof j)) {
                l.n("CustomActionConfig lockedInGame:" + am2.r().i() + " is not a custom unit");
                return false;
            }
            if (this.a.B.read((j)am2)) {
                return true;
            }
        }
        if (this.a.D != null && (n2 == -1 || n2 == 3)) {
            if (!(am2 instanceof j)) {
                l.n("CustomActionConfig lockedInGame:" + am2.r().i() + " is not a custom unit");
                return false;
            }
            if (this.a.D.read((j)am2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String j(am am2) {
        if (this.a(am2, 1) && this.a.A != null) {
            return this.a.A.b(am2);
        }
        if (this.a(am2, 2) && this.a.C != null) {
            return this.a.C.b(am2);
        }
        if (this.a(am2, 3) && this.a.E != null) {
            return this.a.E.b(am2);
        }
        return super.j(am2);
    }

    @Override
    public boolean r(am am2) {
        j j2 = (j)am2;
        if (this.a.v != null) {
            if (this.Q()) {
                return an.a(this.a.v, j2);
            }
            return this.a.v.read(j2);
        }
        return super.b(am2);
    }

    @Override
    public boolean b(am am2) {
        j j2 = (j)am2;
        if (this.a.v != null) {
            return this.a.v.read(j2);
        }
        return super.b(am2);
    }

    @Override
    public boolean a(am am2, n n2) {
        if (!this.a.w && !this.a.x) {
            return false;
        }
        if (am2.bX.d(n2)) {
            return this.a.w;
        }
        return this.a.x;
    }

    @Override
    public b r_() {
        b b2 = this.h.b();
        if (b2 != null) {
            return b2;
        }
        return this.a.r;
    }

    @Override
    public int b(am am2, boolean bl2) {
        if (this.a.aI) {
            return this.a.q.a(am2, true);
        }
        return super.b(am2, bl2);
    }

    @Override
    public String d() {
        return super.d();
    }

    @Override
    public String b() {
        String string = null;
        if (this.a.d != null) {
            string = this.a.d.b();
        }
        return string;
    }

    @Override
    public String d(am am2) {
        as as2;
        String string = null;
        if (this.a.d != null) {
            string = this.a.d.b(am2);
        }
        if (this.a.e != null && (as2 = this.a.e.getTypeOrNull(am2)) != null) {
            if (string == null) {
                string = "";
            } else if (!string.equals("")) {
                string = string + " ";
            }
            string = string + as2.e();
        }
        if (this.a.h != null) {
            if (string == null) {
                string = "";
            } else if (!string.equals("")) {
                string = string + " ";
            }
            string = string + this.a.h.b();
        }
        return string;
    }

    @Override
    public String a() {
        String string = null;
        if (this.a.i != null) {
            string = this.a.i.b();
        }
        return string;
    }

    @Override
    public String e(am am2) {
        Object object;
        String string = null;
        if (this.a.i != null) {
            string = this.a.i.b(am2);
        }
        if (this.a.f != null && (object = this.a.f.getTypeOrNull(am2)) != null) {
            if (string == null) {
                string = "";
            } else if (!string.equals("")) {
                string = string + " ";
            }
            string = string + object.f();
        }
        if (this.a.g != null) {
            object = this.a.g.getUnitReferenceOrNull(am2);
            if (object != null) {
                if (string == null) {
                    string = "";
                } else if (!string.equals("")) {
                    string = string + "\n\n";
                }
                boolean bl2 = false;
                String string2 = com.corrodinggames.rts.gameFramework.f.a.a((am)object, false, false, bl2);
                string = string + string2;
            } else {
                am am3 = this.a.g.getUnitOrSharedUnit(am2);
                if (am3 != null) {
                    if (string == null) {
                        string = "";
                    } else if (!string.equals("")) {
                        string = string + "\n\n";
                    }
                    boolean bl3 = true;
                    String string3 = com.corrodinggames.rts.gameFramework.f.a.a(am3, false, false, bl3);
                    string = string + string3;
                }
            }
        }
        return string;
    }

    public boolean L() {
        return this.a.U;
    }

    @Override
    public float K() {
        if (this.a.S >= 1.0f) {
            return 1000.0f;
        }
        return this.a.S;
    }

    @Override
    public u e() {
        return this.a.j;
    }

    @Override
    public b B() {
        b b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        return this.a.q;
    }

    @Override
    public int c() {
        return this.B().a();
    }

    @Override
    public as i() {
        if (this.b == null) {
            return null;
        }
        return this.b.c();
    }

    @Override
    public as y() {
        if (this.a.J != null) {
            return this.a.J.c();
        }
        return null;
    }

    @Override
    public as E() {
        if (this.a.I != null) {
            return this.a.I.c();
        }
        return null;
    }

    @Override
    public boolean A() {
        return true;
    }

    @Override
    public boolean g() {
        return this.a.J != null;
    }

    @Override
    public t f() {
        return this.a.aG;
    }

    @Override
    public boolean m(am am2) {
        return this.a.G.read((j)am2);
    }

    @Override
    public boolean n(am am2) {
        if (this.a.F == null) {
            return false;
        }
        if (!(am2 instanceof j)) {
            l.b("ai_isHighPriority non customUnit:" + am2.r().i());
            return false;
        }
        return this.a.F.read((j)am2);
    }

    @Override
    public e v(am am2) {
        return this.c;
    }

    @Override
    public boolean H() {
        return this.a.K;
    }

    @Override
    public boolean I() {
        return this.a.L;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e j() {
        return this.a.ay;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e h(am am2) {
        if (this.a.aB != null && am2 instanceof j && !an.a(this.a.aB, (j)am2)) {
            return null;
        }
        return this.a.az;
    }

    @Override
    public int J() {
        return this.a.aA;
    }

    @Override
    public am i(am am2) {
        if (this.a.aC != null) {
            am am3 = this.a.aC.getUnitOrSharedUnit(am2);
            return am3;
        }
        return null;
    }

    @Override
    public boolean s(am am2) {
        return this.a.aD;
    }

    @Override
    public boolean t(am am2) {
        return this.a.aE;
    }

    @Override
    public boolean a(am am2) {
        if (this.a.aF != null) {
            return an.a(this.a.aF, (j)am2);
        }
        return false;
    }

    @Override
    public boolean Q() {
        return this.a.o;
    }

    @Override
    public void a(y y2) {
        if (this.a.ae != null) {
            an.b(y2, this.a.ae);
        }
    }
}
