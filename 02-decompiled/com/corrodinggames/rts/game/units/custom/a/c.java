/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a;

import com.corrodinggames.rts.game.units.a.a;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.p;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.l;

public class c
extends a {
    public LogicBoolean b;
    public LogicBoolean c;
    public aj d;
    public LogicBoolean e;
    public aj f;
    public LogicBoolean g;
    public aj h;
    public boolean i;
    public LogicBoolean j;
    public b k;
    public b l;
    public b m;

    public static a a(d d2) {
        boolean bl2 = false;
        if (d2.z != null && d2.z != LogicBoolean.falseBoolean) {
            bl2 = true;
        }
        if (d2.B != null && d2.B != LogicBoolean.falseBoolean) {
            bl2 = true;
        }
        if (d2.D != null && d2.D != LogicBoolean.falseBoolean) {
            bl2 = true;
        }
        if (d2.v != null && d2.v != LogicBoolean.trueBoolean) {
            bl2 = true;
        }
        if (d2.aF != null && d2.aF != LogicBoolean.falseBoolean) {
            bl2 = true;
        }
        if (d2.ae != null) {
            bl2 = true;
        }
        if (d2.q != null) {
            bl2 = true;
        }
        if (!bl2) {
            return com.corrodinggames.rts.game.units.a.a.a;
        }
        c c2 = new c();
        c2.c = d2.z;
        c2.d = d2.A;
        c2.e = d2.B;
        c2.f = d2.C;
        c2.g = d2.D;
        c2.h = d2.E;
        c2.b = d2.v;
        c2.j = d2.aF;
        c2.l = d2.ae;
        c2.k = d2.q;
        c2.m = d2.r;
        c2.i = d2.y;
        return c2;
    }

    public static a a(p p2) {
        boolean bl2 = false;
        if (p2.f != null && p2.f != LogicBoolean.falseBoolean) {
            bl2 = true;
        }
        if (!bl2) {
            return com.corrodinggames.rts.game.units.a.a.a;
        }
        c c2 = new c();
        c2.c = p2.f;
        c2.d = aj.a(p2.g);
        return c2;
    }

    @Override
    public boolean a(am am2) {
        return this.i;
    }

    @Override
    public boolean b(am am2) {
        return this.a(am2, -1);
    }

    public boolean a(am am2, int n2) {
        if (this.c != null && (n2 == -1 || n2 == 1)) {
            if (!(am2 instanceof y)) {
                com.corrodinggames.rts.gameFramework.l.n("CustomActionConfig lockedInGame:" + am2.r().i() + " is not a OrderableUnit unit");
                return false;
            }
            if (this.c.read((y)am2)) {
                return true;
            }
        }
        if (this.e != null && (n2 == -1 || n2 == 2)) {
            if (!(am2 instanceof y)) {
                com.corrodinggames.rts.gameFramework.l.n("CustomActionConfig lockedInGame:" + am2.r().i() + " is not a OrderableUnit unit");
                return false;
            }
            if (this.e.read((y)am2)) {
                return true;
            }
        }
        if (this.g != null && (n2 == -1 || n2 == 3)) {
            if (!(am2 instanceof y)) {
                com.corrodinggames.rts.gameFramework.l.n("CustomActionConfig lockedInGame:" + am2.r().i() + " is not a OrderableUnit unit");
                return false;
            }
            if (this.g.read((y)am2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String c(am am2) {
        if (this.a(am2, 1) && this.d != null) {
            return this.d.b(am2);
        }
        if (this.a(am2, 2) && this.f != null) {
            return this.f.b(am2);
        }
        if (this.a(am2, 3) && this.h != null) {
            return this.h.b(am2);
        }
        return null;
    }

    @Override
    public boolean a(am am2, boolean bl2) {
        if (this.b != null) {
            if (!(am2 instanceof y)) {
                com.corrodinggames.rts.gameFramework.l.n("CustomActionConfig isAvailable:" + am2.r().i() + " is not a OrderableUnit unit");
                return true;
            }
            if (bl2) {
                return an.a(this.b, (y)am2);
            }
            return this.b.read((y)am2);
        }
        return true;
    }

    @Override
    public boolean d(am am2) {
        if (this.j != null) {
            if (!(am2 instanceof y)) {
                com.corrodinggames.rts.gameFramework.l.n("CustomActionConfig isGuiBlinking:" + am2.r().i() + " is not a OrderableUnit unit");
                return true;
            }
            return this.j.read((y)am2);
        }
        return false;
    }

    @Override
    public void a(am am2, am am3) {
        if (this.l != null) {
            this.l.h(am2);
        }
    }

    @Override
    public b a() {
        return this.k;
    }

    @Override
    public b b() {
        return this.m;
    }
}
