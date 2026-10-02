/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.m;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;
import com.corrodinggames.rts.gameFramework.w;

public strictfp class l
extends w {
    int a;
    int b;
    int c = 50;
    int d = 40;
    m e;
    int f;
    int g = -1;
    static final Rect h = new Rect();
    static final Rect i = new Rect();
    static final Paint j = y.b();
    static e k = null;
    static e l = null;
    static e m = null;
    static final RectF n = new RectF();

    public static void b() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        k = l2.bO.a(R$drawable.scorch_mark, true);
        com.corrodinggames.rts.game.l.k.m = true;
        l = l2.bO.a(R$drawable.scorch_mark_nuke, true);
        com.corrodinggames.rts.game.l.l.m = true;
        m = l2.bO.a(R$drawable.blood_mark, true);
        com.corrodinggames.rts.game.l.m.m = true;
    }

    public l() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.S(-1);
        this.f = l2.by;
    }

    public static void a(float f2, float f3) {
        com.corrodinggames.rts.game.l.a(f2, f3, com.corrodinggames.rts.game.m.a);
    }

    public static void a(float f2, float f3, m m2) {
        if (!com.corrodinggames.rts.game.l.b(f2, f3, m2)) {
            return;
        }
        l l2 = new l();
        l2.eo = f2;
        l2.ep = f3;
        if (m2 == com.corrodinggames.rts.game.m.a) {
            l2.a = 0;
            l2.b = com.corrodinggames.rts.gameFramework.f.a(l2, 0, 3, 0);
        } else {
            l2.a = 2;
        }
        if (l2.a == 2) {
            l2.c = l.m();
            l2.d = l.l();
        }
        l2.e = m2;
        l2.d();
    }

    public static void a(com.corrodinggames.rts.game.units.y y2, int n2) {
        if (!y2.cJ()) {
            m m2;
            m m3 = m2 = n2 == 2 ? com.corrodinggames.rts.game.m.b : com.corrodinggames.rts.game.m.a;
            if (!com.corrodinggames.rts.game.l.b(y2.eo, y2.ep, m2)) {
                return;
            }
            l l2 = new l();
            l2.a = n2;
            if (l2.a == 2) {
                l2.c = l.m();
                l2.d = l.l();
            }
            l2.eo = y2.eo;
            l2.ep = y2.ep;
            l2.e = m2;
            l2.b = com.corrodinggames.rts.gameFramework.f.a(l2, 0, 3, 0);
            l2.d();
        }
    }

    public static boolean b(float f2, float f3, m m2) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 5;
        int n5 = 25;
        if (m2 == com.corrodinggames.rts.game.m.b) {
            n5 = 45;
        }
        w[] wArray = w.er.a();
        int n6 = w.er.size();
        for (int i2 = 0; i2 < n6; ++i2) {
            w w2 = wArray[i2];
            if (!(w2 instanceof l)) continue;
            l l2 = (l)w2;
            if (!(com.corrodinggames.rts.gameFramework.f.c(l2.eo - f2) < (float)n5) || !(com.corrodinggames.rts.gameFramework.f.c(l2.ep - f3) < (float)n5) || l2.e != m2) continue;
            ++n2;
            if (!(com.corrodinggames.rts.gameFramework.f.c(l2.eo - f2) < (float)n4) || !(com.corrodinggames.rts.gameFramework.f.c(l2.ep - f3) < (float)n4)) continue;
            ++n3;
        }
        if (n2 >= 3) {
            return false;
        }
        return n3 < true;
    }

    @Override
    public boolean a(com.corrodinggames.rts.gameFramework.l l2) {
        return false;
    }

    @Override
    public boolean f(float f2) {
        return false;
    }

    @Override
    public boolean c(float f2) {
        return true;
    }

    public RectF c() {
        com.corrodinggames.rts.game.l.n.a = this.eo - (float)this.c * 0.5f;
        com.corrodinggames.rts.game.l.n.c = this.eo + (float)this.c * 0.5f;
        com.corrodinggames.rts.game.l.n.b = this.ep - (float)this.d * 0.5f;
        com.corrodinggames.rts.game.l.n.d = this.ep + (float)this.d * 0.5f;
        return n;
    }

    public void a(com.corrodinggames.rts.gameFramework.m.y y2, int n2, int n3, float f2) {
        int n4 = this.b * this.c;
        int n5 = 0;
        e e2 = null;
        int n6 = this.c;
        int n7 = this.d;
        if (this.a == 0) {
            e2 = k;
        } else if (this.a == 1) {
            e2 = m;
        } else if (this.a == 2) {
            e2 = l;
        }
        Rect rect = h;
        Rect rect2 = i;
        rect2.a = n4;
        rect2.b = n5;
        rect2.c = n4 + n6;
        rect2.d = n5 + n7;
        int n8 = (int)this.eo;
        int n9 = (int)this.ep;
        int n10 = n6 >> 1;
        int n11 = n7 >> 1;
        float f3 = (n8 -= n2) - n10;
        float f4 = (n9 -= n3) - n11;
        float f5 = n8 + n10;
        float f6 = n9 + n11;
        rect.a = (int)(f3 * f2);
        rect.b = (int)(f4 * f2);
        rect.c = (int)(f5 * f2);
        rect.d = (int)(f6 * f2);
        y2.b(e2, rect2, rect, j);
    }

    private void d() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bL.a(this);
    }

    @Override
    public void e(float f2) {
    }

    @Override
    public void a(float f2, boolean bl) {
    }

    @Override
    public void d(float f2) {
    }

    @Override
    public void a(float f2) {
    }

    @Override
    public void a(as as2) {
        as2.a(this.eo);
        as2.a(this.ep);
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.eo = k2.g();
        this.ep = k2.g();
        this.a = k2.f();
        this.b = k2.f();
        this.c = k2.f();
        this.d = k2.f();
        if (k2.b() >= 87) {
            this.e = (m)k2.b(m.class);
            this.f = k2.f();
        } else {
            m m2 = this.e = this.a == 2 ? com.corrodinggames.rts.game.m.b : com.corrodinggames.rts.game.m.a;
            if (this.a == 2) {
                this.c = l.m();
                this.d = l.l();
            }
        }
        super.a(k2);
    }
}
