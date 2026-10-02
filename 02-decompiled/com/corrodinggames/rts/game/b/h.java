/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.y;

public class h {
    int a = 0;
    public e b;
    y c;
    Paint d = new Paint();
    int e;
    int f;
    int g;
    int h;
    float i = 1.0f;
    boolean j;
    h k;
    static final Rect l = new Rect();
    static final Rect m = new Rect();
    static final Rect n = new Rect();
    Rect o = new Rect();
    int p = -1;

    public h(float f, boolean bl) {
        this.i = f;
        this.j = bl;
        this.e = (int)(20.0f * f);
        this.f = (int)(20.0f * f);
        this.g = this.e + 2;
        this.h = this.f + 2;
        if (!bl) {
            this.k = new h(f, true);
        }
    }

    public void a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.b = l2.bO.b(20 * this.g, 20 * this.h, this.j);
        this.c = l2.bO.b(this.b);
        if (this.j) {
            this.b.m = true;
        }
        if (this.k != null) {
            this.k.a();
        }
    }

    public void b() {
        this.a = 0;
        this.c.b(-16777216);
        this.c.o();
        this.b.r();
        if (this.k != null) {
            this.k.b();
        }
    }

    public int a(j j2, int n2) {
        int n3;
        j2.a(n2, l);
        if (this.a >= 400) {
            return -1;
        }
        boolean bl = true;
        if (!this.j) {
            boolean bl2 = bl = !com.corrodinggames.rts.game.b.h.a(j2.b, l);
        }
        if (bl) {
            int n4 = this.a++;
            this.a(n4, j2.b, l);
            return n4;
        }
        if (this.k != null && (n3 = this.k.a(j2, n2)) != -1) {
            return n3 + 500;
        }
        return -1;
    }

    public static boolean a(e e2, Rect rect) {
        int n2 = rect.a;
        int n3 = rect.c;
        int n4 = rect.b;
        int n5 = rect.d;
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n2 > e2.m()) {
            n2 = e2.m();
        }
        if (n3 > e2.m()) {
            n3 = e2.m();
        }
        if (n4 > e2.l()) {
            n4 = e2.l();
        }
        if (n5 > e2.l()) {
            n5 = e2.l();
        }
        if (!e2.k()) {
            com.corrodinggames.rts.gameFramework.l.e("hasImageAlpha: Cannot get pixel data for: " + e2.a());
            return true;
        }
        e2.x();
        for (int i2 = n4; i2 < n5; ++i2) {
            for (int i3 = n2; i3 < n3; ++i3) {
                int n6 = e2.a(i3, i2);
                int n7 = Color.a(n6);
                if (n7 == 255) continue;
                return true;
            }
        }
        e2.y();
        return false;
    }

    public void c() {
        this.c.p();
        if (this.k != null) {
            this.k.c();
        }
    }

    public void a(int n2, e e2, Rect rect) {
        Rect rect2 = new Rect();
        this.a(n2, rect2);
        boolean bl = false;
        if (this.i < 1.0f) {
            bl = true;
        }
        this.d.a(bl);
        this.d.d(bl);
        this.d.b(bl);
        com.corrodinggames.rts.game.b.h.a(this.c, e2, rect, rect2, this.d);
    }

    public static void a(y y2, e e2, Rect rect, Rect rect2, Paint paint) {
        int n2;
        for (n2 = 0; n2 <= 3; ++n2) {
            com.corrodinggames.rts.game.b.h.a(rect, m, n2, 0);
            com.corrodinggames.rts.game.b.h.a(rect2, n, n2, 1);
            y2.a(e2, m, n, paint);
        }
        for (n2 = 0; n2 <= 3; ++n2) {
            com.corrodinggames.rts.game.b.h.a(rect, m, n2, 1, -1);
            com.corrodinggames.rts.game.b.h.a(rect2, n, n2, 1, 0);
            y2.a(e2, m, n, paint);
        }
        y2.a(e2, rect, rect2, paint);
    }

    public static Rect a(Rect rect, Rect rect2, int n2, int n3, int n4) {
        int n5 = 0;
        if (n2 == 0) {
            rect2.a = rect.a - n5;
            rect2.c = rect.c + n5;
            rect2.b = rect.b - n3 - n4;
            rect2.d = rect.b - n4;
        } else if (n2 == 1) {
            rect2.a = rect.c + n4;
            rect2.c = rect.c + n3 + n4;
            rect2.b = rect.b - n5;
            rect2.d = rect.d + n5;
        } else if (n2 == 2) {
            rect2.a = rect.a - n5;
            rect2.c = rect.c + n5;
            rect2.b = rect.d + n4;
            rect2.d = rect.d + n3 + n4;
        } else {
            rect2.a = rect.a - n4;
            rect2.c = rect.a - n3 - n4;
            rect2.b = rect.b - n5;
            rect2.d = rect.d + n5;
        }
        return rect2;
    }

    public static Rect a(Rect rect, Rect rect2, int n2, int n3) {
        if (n2 == 0) {
            rect2.a = rect.c - 1 + n3;
            rect2.b = rect.b - n3;
        } else if (n2 == 1) {
            rect2.a = rect.c - 1 + n3;
            rect2.b = rect.d - 1 + n3;
        } else if (n2 == 2) {
            rect2.a = rect.a - n3;
            rect2.b = rect.d - 1 + n3;
        } else {
            rect2.a = rect.a - n3;
            rect2.b = rect.b - n3;
        }
        rect2.c = rect2.a + 1;
        rect2.d = rect2.b + 1;
        return rect2;
    }

    public final e a(int n2) {
        if (n2 >= 500) {
            return this.k.a(n2 - 500);
        }
        return this.b;
    }

    public final Rect b(int n2) {
        if (n2 >= 500) {
            return this.k.b(n2 - 500);
        }
        if (this.p == n2) {
            return this.o;
        }
        this.p = n2;
        this.a(n2, this.o);
        return this.o;
    }

    public void a(int n2, Rect rect) {
        int n3 = n2 % 20;
        int n4 = (int)((float)n2 * 0.05f);
        int n5 = n3 * this.g + 1;
        int n6 = n4 * this.h + 1;
        rect.a = n5;
        rect.b = n6;
        rect.c = n5 + this.e;
        rect.d = n6 + this.f;
    }
}
