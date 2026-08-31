/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.b.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.y;

public strictfp class d {
    public y a;
    int b;
    int c;
    public e d;
    public e e;
    public y f;
    public float g;
    public Paint h = new ag();
    public int i;
    public int j;
    public boolean k = true;
    public boolean l = true;
    public int m = 0;
    public boolean n = false;
    public final Rect o = new Rect();
    public final Rect p = new Rect();
    public final RectF q = new RectF();
    public final Rect r = new Rect();
    final /* synthetic */ c s;

    public void a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.e = l2.bO.a(this.d.p, this.d.q, true);
        if (this.e != null && !this.e.A()) {
            this.e.a("fadeOutBitmap");
        }
        if (this.e == null || this.e.A()) {
            throw new OutOfMemoryError("Failed to create fade out bitmap");
        }
        this.e.b(true);
        this.f = l2.bO.b(this.e);
    }

    public Rect b() {
        this.r.a(this.c(), this.d(), this.c() + this.s.i, this.d() + this.s.i);
        return this.r;
    }

    public d(c c2, int n2, int n3) {
        this.s = c2;
        this.i = n2;
        this.j = n3;
    }

    public int c() {
        return this.s.f + this.i * this.s.k;
    }

    public int d() {
        return this.s.g + this.j * this.s.k;
    }
}
