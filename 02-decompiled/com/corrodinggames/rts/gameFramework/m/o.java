/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.j;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.p;
import com.corrodinggames.rts.gameFramework.m.q;
import com.corrodinggames.rts.gameFramework.m.r;
import com.corrodinggames.rts.gameFramework.m.s;
import com.corrodinggames.rts.gameFramework.m.t;
import com.corrodinggames.rts.gameFramework.m.u;
import java.util.concurrent.locks.Lock;

public final class o
extends u {
    com.corrodinggames.rts.gameFramework.m.l a = new j(null);
    int b;
    final com.corrodinggames.rts.gameFramework.utility.m c = new com.corrodinggames.rts.gameFramework.utility.m();
    final q d = new q(Paint.class);
    final q e = new q(Rect.class);
    final q f = new q(RectF.class);
    final q g = new q(Matrix.class);
    final q h = new q(r.class);
    final t i = new t(200);
    int j = 0;
    volatile boolean k = false;

    public o() {
        this.c.add(this.d);
        this.c.add(this.e);
        this.c.add(this.f);
        this.c.add(this.g);
        this.c.add(this.h);
    }

    public final s a(p p2, Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.a(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        Object[] objectArray = s2.b;
        objectArray[0] = object;
        objectArray[1] = object2;
        objectArray[2] = object3;
        objectArray[3] = object4;
        objectArray[4] = object5;
        objectArray[5] = object6;
        objectArray[6] = object7;
        objectArray[7] = object8;
        ++this.j;
        return s2;
    }

    public final void a(p p2, Object object, Object object2, Object object3, Object object4) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.a(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        Object[] objectArray = s2.b;
        objectArray[0] = object;
        objectArray[1] = object2;
        objectArray[2] = object3;
        objectArray[3] = object4;
        ++this.j;
    }

    public final void a(p p2, Object object, Object object2) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.a(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        Object[] objectArray = s2.b;
        objectArray[0] = object;
        objectArray[1] = object2;
        ++this.j;
    }

    public final s a(p p2) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.a(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        ++this.j;
        return s2;
    }

    @Override
    public void a(boolean bl) {
        this.k = bl;
    }

    @Override
    public boolean c() {
        return this.k;
    }

    @Override
    public void a(Rect rect) {
        rect = this.e.a(rect);
        this.a(p.g, rect, null, null, null, null, null, null, null);
    }

    @Override
    public void a(RectF rectF) {
        rectF = this.f.a(rectF);
        this.a(p.i, rectF, null, null, null, null, null, null, null);
    }

    @Override
    public void a(e e2, float f, float f2, Paint paint) {
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        s s2 = this.a(p.m);
        s2.b[0] = e2;
        s2.b[1] = paint;
        s2.c = f;
        s2.d = f2;
    }

    @Override
    public void a(e e2, Rect rect, Rect rect2, Paint paint) {
        rect = this.e.a(rect);
        rect2 = this.e.a(rect2);
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.o, e2, rect, rect2, (Object)paint);
    }

    @Override
    public void a(e e2, Rect rect, RectF rectF, Paint paint) {
        rect = this.e.a(rect);
        rectF = this.f.a(rectF);
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.p, e2, rect, rectF, (Object)paint);
    }

    @Override
    public void a(float f, float f2, float f3, Paint paint) {
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.t, Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), (Object)paint);
    }

    @Override
    public void a(int n, PorterDuff.Mode mode) {
        this.a(p.u, (Object)n, mode);
    }

    @Override
    public void a(int n) {
        this.a(p.v, n, null, null, null, null, null, null, null);
    }

    @Override
    public void a(float f, float f2, float f3, float f4, Paint paint) {
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        s s2 = this.a(p.w);
        s2.c = f;
        s2.d = f2;
        s2.e = f3;
        s2.f = f4;
        s2.b[0] = paint;
    }

    @Override
    public void a(float[] fArray, int n, int n2, Paint paint) {
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.G, fArray, n, n2, paint, null, null, null, null);
    }

    @Override
    public void a(Rect rect, Paint paint) {
        rect = this.e.a(rect);
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.M, rect, paint);
    }

    @Override
    public void a(RectF rectF, Paint paint) {
        rectF = this.f.a(rectF);
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.N, rectF, paint);
    }

    @Override
    public void a(String string, float f, float f2, Paint paint) {
        if (!(paint instanceof ag)) {
            paint = this.d.a(paint);
        }
        this.a(p.R, string, Float.valueOf(f), Float.valueOf(f2), paint, null, null, null, null);
    }

    @Override
    public void a() {
        this.a(p.W);
        --this.b;
        if (this.b < 0) {
            l.g("saveStackSize: " + this.b);
        }
    }

    @Override
    public void a(float f2, float f3, float f4) {
        s s2 = this.a(p.Z);
        s2.c = f2;
        s2.d = f3;
        s2.e = f4;
    }

    @Override
    public void b() {
        this.a(p.aa);
        ++this.b;
        if (this.b <= 0) {
            l.g("saveStackSize (on save): " + this.b);
        }
    }

    @Override
    public void a(float f2, float f3) {
        s s2 = this.a(p.af);
        s2.c = f2;
        s2.d = f3;
    }

    @Override
    public void a(float f2, float f3, float f4, float f5) {
        s s2 = this.a(p.ag);
        s2.c = f2;
        s2.d = f3;
        s2.e = f4;
        s2.f = f5;
    }

    @Override
    public void a(e e2) {
        this.a(p.ah, e2, null, null, null, null, null, null, null);
    }

    @Override
    public void b(float f2, float f3) {
        s s2 = this.a(p.am);
        s2.c = f2;
        s2.d = f3;
    }

    @Override
    public void a(m m2) {
        this.a(p.an, this, m2);
    }

    @Override
    public void a(Bitmap bitmap) {
        this.a(p.ap, bitmap, null);
    }

    @Override
    public void a(Lock lock) {
        this.a(p.aq, lock, null);
    }

    @Override
    public void b(Lock lock) {
        this.a(p.ar, lock, null);
    }

    @Override
    public boolean a(ae ae2) {
        this.a(p.as, ae2, null);
        return true;
    }
}
