/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.a.i;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.y;

public class h {
    public static final h j = new h();
    public static final h k = new h();
    public static final h l = new h();
    public static final h m = new h();
    public static final h n = new h();
    Paint o = new ag();
    e p;
    Paint q = new ag();
    public h r;
    public int s = 3;
    public int t = 3;
    public int u;
    public h v;
    static Rect w = new Rect();
    static Rect x = new Rect();
    static Rect y = new Rect();

    public void a(e e2) {
        this.p = e2;
    }

    public void a(h h2) {
        this.p = h2.p;
        this.o = h2.o != null ? new Paint(h2.o) : null;
        this.q = h2.q != null ? new Paint(h2.q) : null;
    }

    public static void b() {
        h h2 = j;
        h2.o.b(Color.a(140, 100, 100, 100));
        h2.q.b(-16777216);
        h2.q.a(Paint$Style.b);
        h2 = k;
        h2.o.b(Color.a(180, 100, 100, 190));
        h2.q.b(-16777216);
        h2.q.a(Paint$Style.b);
        h2 = l;
        h2.o = null;
        h2.q = null;
        h2 = m;
        h2.o = null;
        h2.q.b(-65536);
        h2.q.c(127);
        h2.q.a(Paint$Style.b);
        h2 = n;
        h2.o.c(255);
        h2.p = com.corrodinggames.rts.gameFramework.l.B().bS.bl;
        h2.q.b(-7829368);
        h2.q.c(255);
        h2.q.a(Paint$Style.b);
    }

    public void a(y y2, RectF rectF) {
        h.x.a = (int)rectF.a;
        h.x.b = (int)rectF.b;
        h.x.c = (int)rectF.c;
        h.x.d = (int)rectF.d;
        this.a(y2, x, i.a);
    }

    public void c(y y2, Rect rect) {
        this.a(y2, rect, i.a);
    }

    public void a(y y2, Rect rect, i i2) {
        if (this.u > 0) {
            y.a(rect);
            rect = y;
            f.a(rect, (float)this.u);
        }
        if (this.r != null) {
            w.a(rect);
            w.a(this.s, this.t);
            this.r.a(y2, w);
        }
        if (i2 == i.b && this.v != null) {
            this.v.a(y2, rect);
            return;
        }
        this.a(y2, rect);
    }

    public void a(y y2, Rect rect) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.p != null) {
            l2.bO.a(this.p, rect, this.o, 0, 0, 0, 0);
        } else if (this.o != null) {
            y2.b(rect, this.o);
        }
        if (this.q != null) {
            y2.b(rect, this.q);
        }
    }
}
