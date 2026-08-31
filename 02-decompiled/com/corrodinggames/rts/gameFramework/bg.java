/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.bl;
import com.corrodinggames.rts.gameFramework.bn;
import com.corrodinggames.rts.gameFramework.bo;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public strictfp class bg {
    public static boolean a = true;
    bo b = new bo();
    bo[] c = new bo[n.e];
    int d;
    boolean e;
    public static bl f = new bl();

    public void a(as as2) {
        as2.e("stats");
        as2.c(0);
        int n2 = n.c;
        as2.a(n2);
        for (int i = 0; i < n2; ++i) {
            this.c[i].a(as2);
        }
        as2.a("stats");
    }

    public void a(k k2, boolean bl2) {
        k2.b("stats");
        byte by = k2.d();
        int n2 = k2.f();
        this.c = new bo[n.e];
        for (int i = 0; i < n2; ++i) {
            this.c[i] = new bo();
            this.c[i].a(k2);
        }
        k2.d("stats");
    }

    public void a() {
        this.b = new bo();
        this.c = new bo[n.e];
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = new bo();
        }
        this.d = 0;
        this.e = a;
    }

    public void b() {
        int n2 = l.B().by;
        if (this.e && this.d <= n2) {
            int n3 = 5000;
            if (n2 < 60000) {
                n3 = 1000;
            }
            if (n2 > 1800000) {
                n3 = 15000;
            }
            if (n2 > 3600000) {
                n3 = 30000;
            }
            n3 += n3;
            this.a(n2, false, false);
        }
    }

    private void a(int n2, boolean bl2, boolean bl3) {
        for (int j = 0; j < n.c; ++j) {
            n n3 = n.k(j);
            if (n3 == null) continue;
            bn bn2 = this.c[j].l;
            if (bl2 && !bn2.c()) continue;
            bn2.a(n3, n2, bl3);
            bn2.a(j);
        }
    }

    public void c() {
        this.e = false;
        this.a(l.B().by, true, true);
    }

    public ArrayList d() {
        ArrayList<bo> arrayList = new ArrayList<bo>();
        for (int j = 0; j < n.c; ++j) {
            if (!this.c[j].l.c()) continue;
            arrayList.add(this.c[j]);
        }
        return arrayList;
    }

    public bo a(am am2) {
        return this.a(am2.bX);
    }

    public bo a(n n2) {
        int n3 = n2.k;
        if (n3 < 0 || n3 >= this.c.length) {
            return this.b;
        }
        bo bo2 = this.c[n3];
        if (bo2 == null) {
            return this.b;
        }
        return bo2;
    }

    public void a(am am2, am am3, float f2) {
        if (am2 != null) {
            boolean bl2 = am3.bV;
            bo bo2 = this.a(am2);
            bo bo3 = this.a(am3);
            if (bl2) {
                f.a(am2, am3);
                if (am3.bI()) {
                    ++bo2.d;
                    ++bo3.g;
                } else if (am3.dd()) {
                    ++bo2.e;
                    ++bo3.h;
                } else {
                    ++bo2.c;
                    ++bo3.f;
                }
            }
        }
        l l2 = l.B();
        if (am3.bX == l2.bs) {
            l2.a(am3, f2);
        }
    }
}
