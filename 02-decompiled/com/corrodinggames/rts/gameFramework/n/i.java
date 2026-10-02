/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.n.j;
import com.corrodinggames.rts.gameFramework.utility.m;

class i {
    boolean a;
    m b = new m();
    final /* synthetic */ com.corrodinggames.rts.gameFramework.n.f c;

    i(com.corrodinggames.rts.gameFramework.n.f f2) {
        this.c = f2;
    }

    public void a(as as2, int n2) {
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
        }
        this.b(as2, n2);
    }

    public void b(as as2, int n2) {
        for (j j2 : this.b) {
            if (j2.a != as2) continue;
            j2.b += n2;
            return;
        }
        j j3 = new j(this);
        j3.a = as2;
        j3.b = n2;
        this.b.add(j3);
    }

    public void a(float f2, float f3) {
        l l2 = l.B();
        int n2 = 0;
        n n3 = n.k(1);
        if (n3 == null) {
            l.e("Warning: Creating missing wave team AI");
            n3 = new a(1);
            n3.r = 100;
            n3.U = true;
        }
        for (j j2 : this.b) {
            for (int i2 = 0; i2 < j2.b; ++i2) {
                am am2 = j2.a.a();
                int n4 = 85;
                am2.eo = f2 + (float)f.a(-n4, n4, n2 + 0);
                am2.ep = f3 + (float)f.a(-n4, n4, n2 + 1);
                am2.cg = f.a(-180, 180, n2 + 2);
                n2 += 3;
                am2.b(n3);
                if (am2.eo < 0.0f) {
                    am2.eo = 0.0f;
                }
                if (am2.ep < 0.0f) {
                    am2.ep = 0.0f;
                }
                if (am2.eo > l2.bL.i()) {
                    am2.eo = l2.bL.i();
                }
                if (am2.ep > l2.bL.j()) {
                    am2.ep = l2.bL.j();
                }
                if (i2 != 0) continue;
                l2.bW.a(am2);
            }
        }
    }

    public String toString() {
        if (this.b.size() == 0) {
            return "No units";
        }
        String string = "";
        boolean bl = true;
        for (j j2 : this.b) {
            if (!bl) {
                string = string + ", ";
            }
            bl = false;
            string = string + j2.b + "x ";
            string = string + j2.a.e();
        }
        return string;
    }
}
