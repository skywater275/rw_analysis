/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.k.c;
import com.corrodinggames.rts.gameFramework.k.g;
import com.corrodinggames.rts.gameFramework.k.h;
import com.corrodinggames.rts.gameFramework.k.k;
import com.corrodinggames.rts.gameFramework.k.l;
import java.util.LinkedList;

public class f
extends k {
    c a = new h(this);
    g b;
    static Paint c = new Paint();
    static Point d = new Point();

    public f(l l2, boolean bl) {
        super(l2, bl);
    }

    @Override
    public c a(am am2) {
        if (this.a() != null) {
            return this.a;
        }
        return null;
    }

    @Override
    public LinkedList a() {
        return super.a();
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public boolean a(k k2) {
        if (this == k2) {
            return true;
        }
        if (!(k2 instanceof f)) {
            return false;
        }
        f f2 = (f)k2;
        if (this.l != f2.l || this.m != f2.m) {
            return false;
        }
        return this.o == f2.o;
    }

    @Override
    protected boolean c() {
        return this.x != null;
    }

    public final byte a(int n, int n2) {
        if (this.b == null) {
            return -1;
        }
        return this.b.a(n, n2);
    }

    public static final void a(byte by, Point point) {
        int n = 0;
        int n2 = 0;
        if ((by = (byte)(by - 1)) == 0) {
            ++n;
        }
        if (by == 1) {
            ++n;
            ++n2;
        }
        if (by == 2) {
            ++n2;
        }
        if (by == 3) {
            ++n2;
            --n;
        }
        if (by == 4) {
            --n;
        }
        if (by == 5) {
            --n;
            --n2;
        }
        if (by == 6) {
            --n2;
        }
        if (by == 7) {
            --n2;
            ++n;
        }
        point.a = n;
        point.b = n2;
    }

    public void d() {
        int n2;
        int n3;
        int n4;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        Rect rect = new Rect();
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = l2.cA;
        float f5 = l2.cB;
        e e2 = l2.bL.u;
        int n5 = (int)(f2 * b2.r - 1.0f);
        if (n5 < 0) {
            n5 = 0;
        }
        if ((n4 = (int)(f3 * b2.s - 1.0f)) < 0) {
            n4 = 0;
        }
        if ((n3 = (int)((f2 + f4) * b2.r + 1.0f)) > b2.C - 1) {
            n3 = b2.C - 1;
        }
        if ((n2 = (int)((f3 + f5) * b2.s + 1.0f)) > b2.D - 1) {
            n2 = b2.D - 1;
        }
        boolean bl = false;
        for (int i2 = n5; i2 < n3 + 1; ++i2) {
            for (int i3 = n4; i3 < n2 + 1; ++i3) {
                com.corrodinggames.rts.game.b.g g2 = e2.a(i2, i3);
                if (g2 == null) continue;
                int n6 = i2 * b2.n;
                int n7 = i3 * b2.o;
                rect.a(n6, n7, n6 + b2.n, n7 + b2.o);
                rect.a((int)(-f2), (int)(-f3));
                boolean bl2 = rect.b((int)(l2.bS.x / l2.cX), (int)(l2.bS.y / l2.cX));
                int n8 = 50;
                int n9 = 0;
                int n10 = 0;
                n8 = n8 == -1 ? 255 : (n8 *= 2);
                n9 = n9 == -1 ? 255 : (n9 *= 2);
                if (n10 == -1) {
                    n10 = 255;
                } else {
                    if (n10 != 0) {
                        n10 += 30;
                    }
                    n10 *= 2;
                }
                c.a(128, n8, n9, n10);
                byte by = this.a(i2, i3);
                com.corrodinggames.rts.gameFramework.k.f.a(by, d);
                float f6 = (float)(n6 + b2.p) - f2;
                float f7 = (float)(n7 + b2.q) - f3;
                l2.bO.a(f6, f7, f6 + (float)(com.corrodinggames.rts.gameFramework.k.f.d.a * (b2.n - 3)) + 1.0f, f7 + (float)(com.corrodinggames.rts.gameFramework.k.f.d.b * (b2.o - 3)) + 1.0f, c);
                if (!bl2) continue;
            }
        }
    }
}
