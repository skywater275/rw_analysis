/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Point;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.units.af;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.k.c;
import com.corrodinggames.rts.gameFramework.k.f;
import com.corrodinggames.rts.gameFramework.l;

public class h
extends c {
    f a;
    af b = new af();
    static Point c = new Point();

    public h(f f2) {
        this.a = f2;
    }

    @Override
    public af a(am am2) {
        af af2 = this.a(am2.eo, am2.ep);
        if (af2 == null) {
            return null;
        }
        af af3 = this.a(af2.a, af2.b);
        if (af3 == null) {
            return af2;
        }
        af af4 = this.a(af3.a, af3.b);
        if (af4 == null) {
            return af3;
        }
        return af4;
    }

    @Override
    public void d(am am2) {
        af af2;
        float f2;
        if (this.a != null) {
            this.a.d();
        }
        l l2 = l.B();
        float f3 = l2.cw;
        float f4 = l2.cx;
        af af3 = this.e(am2);
        if (af3 != null) {
            float f5 = af3.a;
            f2 = af3.b;
            f.c.b(-16776961);
            l2.bO.a(am2.eo - f3, am2.ep - f4, f5 - f3, f2 - f4, f.c);
            af af4 = this.b(am2);
            if (af4 != null) {
                f.c.b(-7829368);
                l2.bO.a(f5 - f3, f2 - f4, af4.a - f3, af4.b - f4, f.c);
            }
        }
        if ((af2 = this.a(am2)) != null) {
            f2 = af2.a;
            float f6 = af2.b;
            f.c.b(-256);
            l2.bO.a(am2.eo - f3, am2.ep - f4, f2 - f3, f6 - f4, f.c);
        }
    }

    public af e(am am2) {
        return this.a(am2.eo, am2.ep);
    }

    @Override
    public af b(am am2) {
        af af2 = this.a(am2.eo, am2.ep);
        if (af2 == null) {
            return null;
        }
        return this.a(af2.a, af2.b);
    }

    @Override
    public void c(am am2) {
    }

    public af a(float f2, float f3) {
        if (this.a.b == null) {
            return null;
        }
        l l2 = l.B();
        b b2 = l2.bL;
        int n2 = (int)(f2 * b2.r);
        int n3 = (int)(f3 * b2.s);
        if (!b2.c(n2, n3)) {
            return null;
        }
        byte by = this.a.a(n2, n3);
        if (by == 0) {
            return null;
        }
        f.a(by, c);
        int n4 = n2 - h.c.a;
        int n5 = n3 - h.c.b;
        this.b.a = n4 * b2.n + b2.p;
        this.b.b = n5 * b2.o + b2.q;
        return this.b;
    }
}
