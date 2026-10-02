/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class z
extends s {
    as a;
    ArrayList b = new ArrayList();
    int c = 0;
    boolean d;
    y e = null;
    int f;

    public z(as as2) {
        super("s_" + as2.v());
        this.g = -9999.0f;
        this.a = as2;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public as i() {
        return this.a;
    }

    @Override
    public u e() {
        return u.i;
    }

    @Override
    public t f() {
        if (l.at() && !com.corrodinggames.rts.gameFramework.f.g.bO) {
            return t.h;
        }
        return t.g;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        if (!bl) {
            if (l2.bS.q() == 1) {
                return false;
            }
            boolean bl2 = false;
            for (am am3 : am.bE) {
                if (!am3.cG || am3.r() == this.a) continue;
                l2.bS.l(am3);
                bl2 = true;
            }
            if (!bl2) {
                return false;
            }
        } else {
            for (am am4 : am.bE) {
                if (!am4.cG || am4.r() != this.a) continue;
                l2.bS.l(am4);
            }
        }
        return true;
    }

    @Override
    public String d() {
        String string = "UnitInfo";
        l l2 = l.B();
        if (this.e instanceof h) {
            return "Editor";
        }
        string = "" + this.a.e() + " x" + this.c;
        return string;
    }

    @Override
    public String b() {
        return "UnitInfo";
    }

    @Override
    public String w(am am2) {
        if (this.e instanceof h) {
            return "Editor";
        }
        return this.a.e();
    }

    @Override
    public boolean h_() {
        return true;
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public String a() {
        String string = "";
        if (this.e instanceof h) {
            return "";
        }
        if (this.d) {
            string = "(Left click to exclusively select / Right click to unselect)\n";
        }
        return string + this.a.f();
    }

    public void K() {
        l l2 = l.B();
        if (this.f == l2.bS.Y) {
            return;
        }
        this.f = l2.bS.Y;
        this.c = 0;
        this.d = false;
        this.e = null;
        am[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG) continue;
            if (y2.r() == this.a) {
                ++this.c;
                if (this.e != null) continue;
                this.e = y2;
                continue;
            }
            this.d = true;
        }
    }

    @Override
    public float m_() {
        return this.g - (float)this.c;
    }

    @Override
    public boolean G() {
        return true;
    }

    @Override
    public boolean o_() {
        return true;
    }
}
