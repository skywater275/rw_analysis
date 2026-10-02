/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.b;

public class a {
    public final b a = new b();
    public final b[] b = new b[n.e];
    public final b c = new b();
    public final b d = new b();
    float e;

    public a() {
        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = new b();
        }
    }

    public void a(am am2) {
        this.a.a(am2);
        int n2 = am2.dn;
        if (n2 >= 0) {
            this.b[n2].a(am2);
        } else if (n2 == -1) {
            this.d.a(am2);
        } else if (n2 == -2) {
            this.c.a(am2);
        }
        if (am2.cj > this.e) {
            this.e = am2.cj;
        }
    }

    public void b(am am2) {
        this.a.b(am2);
        int n2 = am2.dn;
        if (n2 >= 0) {
            this.b[n2].b(am2);
        } else if (n2 == -1) {
            this.d.b(am2);
        } else if (n2 == -2) {
            this.c.b(am2);
        }
        if (this.a.b == 0) {
            this.e = 0.0f;
        }
    }
}
