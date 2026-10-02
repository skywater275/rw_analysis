/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.gameFramework.f.a;
import com.corrodinggames.rts.gameFramework.j.f;
import com.corrodinggames.rts.gameFramework.l;
import java.io.IOException;

public class y
extends s {
    public boolean a;

    public y(boolean bl) {
        super("c_5");
        this.g = -9990.0f;
        this.a = bl;
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
        return null;
    }

    @Override
    public u e() {
        return u.i;
    }

    @Override
    public t f() {
        return t.g;
    }

    @Override
    public boolean g() {
        return false;
    }

    public com.corrodinggames.rts.game.units.y K() {
        l l2 = l.B();
        am[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
            com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
            if (!y2.cG) continue;
            return y2;
        }
        return null;
    }

    public boolean L() {
        l l2 = l.B();
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof h) {
                return true;
            }
            return l2.bs == y2.bX;
        }
        return false;
    }

    @Override
    public String d() {
        String string = "UnitInfo";
        l l2 = l.B();
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof h) {
                return "Editor";
            }
            if (!this.a) {
                string = l2.bS.g.a((am)y2, false);
            } else {
                n n2 = y2.bX;
                string = l2.bS.g.a(n2);
            }
        }
        return string;
    }

    @Override
    public boolean h_() {
        return true;
    }

    @Override
    public String b() {
        return "UnitInfo";
    }

    @Override
    public String d(am am2) {
        if (this.a) {
            return "";
        }
        if (am2 != null) {
            return am2.r().e();
        }
        return "UnitInfo";
    }

    @Override
    public boolean s() {
        if (this.a) {
            return !this.L();
        }
        return true;
    }

    @Override
    public boolean u() {
        return !this.a;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public String a() {
        if (this.a) {
            return "";
        }
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
            boolean bl = false;
            String string = com.corrodinggames.rts.gameFramework.f.a.a(y2, false, true, bl);
            boolean bl2 = false;
            if (bl2) {
                f f2 = new f();
                try {
                    y2.a(f2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                string = string + "\n" + f2.a;
            }
            return string;
        }
        return "";
    }

    @Override
    public boolean G() {
        return true;
    }
}
