/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.b.ac;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.c;
import com.corrodinggames.rts.gameFramework.b.f;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.v;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.m;
import java.util.concurrent.locks.Lock;

public class k
implements com.corrodinggames.rts.gameFramework.m.l {
    public f a;
    public n b;
    v c;
    public static boolean d = false;
    Rect e;
    RectF f;
    c g;
    RectF h;
    float[] i;
    static e j;
    static Rect k;
    static Paint l;
    static Rect m;
    e n;
    boolean o;

    public void b(e e2) {
        b b2 = this.a.a(e2.b(), e2, this.g);
        this.a.a(b2);
    }

    public void d() {
        this.a.a();
    }

    public v a(Paint paint) {
        this.b.a((ae)null);
        if (paint == null) {
            return null;
        }
        this.c.a(paint.d());
        this.c.a(paint.e());
        this.c.a(paint.g());
        return this.c;
    }

    @Override
    public void a(Rect rect) {
        this.a.a(rect.a, rect.b, rect.c, rect.d);
    }

    @Override
    public void a(RectF rectF) {
        this.a.a((int)rectF.a, (int)rectF.b, (int)rectF.c, (int)rectF.d);
    }

    @Override
    public void a(e e2, float f2, float f3, Paint paint) {
        this.e.a(0, 0, e2.m(), e2.l());
        this.f.a(f2, f3, f2 + (float)e2.m(), f3 + (float)e2.l());
        this.b(e2, this.e, this.f, paint);
    }

    @Override
    public void a(e e2, Rect rect, Rect rect2, Paint paint) {
        this.f.a(rect2);
        this.b(e2, rect, this.f, paint);
    }

    public b a(Bitmap bitmap, e e2) {
        b b2;
        n n2 = this.b;
        if (n2.a == null) {
            n2.a = new ac(n2, 1024, 1024);
        }
        if (bitmap.b() < 450 && bitmap.c() < 100 && (b2 = n2.a.a(bitmap)) != null) {
            return b2;
        }
        b2 = this.a.a(bitmap, e2, this.g);
        return b2;
    }

    @Override
    public void a(e e2, Rect rect, RectF rectF, Paint paint) {
        this.b(e2, rect, rectF, paint);
    }

    public void b(e e2, Rect rect, RectF rectF, Paint paint) {
        Bitmap bitmap = aa.a(e2);
        this.h.a(rect);
        if (bitmap == null) {
            throw new RuntimeException("bitmap==null. sourceImage: " + e2.a());
        }
        b b2 = this.a(bitmap, e2);
        n n2 = this.b;
        boolean bl = true;
        if (paint == null) {
            n2.w = -1;
        } else {
            int n3 = paint.e();
            if (n3 != -1 && paint.h() == null) {
                n3 = Color.a(Color.a(n3), 255, 255, 255);
            }
            n2.w = n3;
            bl = paint instanceof ag ? ((ag)paint).p() : paint.c();
        }
        n2.a(b2, bl ? 9729 : 9728);
        ae ae2 = null;
        if (paint instanceof ag) {
            ae2 = ((ag)paint).q();
        }
        if (e2 != null && ae2 == null) {
            ae2 = e2.B();
        }
        if (ae2 != null) {
            boolean bl2 = ae2.a(paint, e2);
            this.b.a(ae2);
            if (bl2) {
                this.b.e();
                this.b.o();
            }
        } else {
            this.b.a((ae)null);
        }
        n2.a(b2, this.h, rectF, this.g, null);
    }

    @Override
    public void a(Bitmap bitmap) {
        this.a.a(bitmap);
    }

    @Override
    public void a(float f2, float f3, float f4, Paint paint) {
        this.a.a(f2, f3, f4, this.a(paint));
    }

    @Override
    public void a(int n2, PorterDuff.Mode mode) {
        this.b.a(this.b(n2));
    }

    @Override
    public void a(int n2) {
        this.b.a(this.b(n2));
    }

    float[] b(int n2) {
        float f2 = (float)(n2 >>> 24 & 0xFF) * 0.003921569f * 1.0f;
        float f3 = (float)(n2 >>> 16 & 0xFF) * 0.003921569f * f2;
        float f4 = (float)(n2 >>> 8 & 0xFF) * 0.003921569f * f2;
        float f5 = (float)(n2 & 0xFF) * 0.003921569f * f2;
        this.i[0] = f2;
        this.i[1] = f3;
        this.i[2] = f4;
        this.i[3] = f5;
        return this.i;
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, Paint paint) {
        this.a.a(f2, f3, f4, f5, this.a(paint));
    }

    @Override
    public void a(float[] fArray, int n2, int n3, Paint paint) {
        v v2 = this.a(paint);
        this.a.a(fArray, n2, n3, v2);
    }

    public void b(float f2, float f3, float f4, float f5, Paint paint) {
        if (j == null) {
            Bitmap bitmap = Bitmap.a(1, 1, Bitmap$Config.d);
            bitmap.a(0, 0, -1);
            e e2 = new e();
            e2.a(bitmap);
            j = e2;
            l.a(false);
            l.a(new LightingColorFilter(-1, -16777216));
        }
        l.b(paint.e());
        if (paint.d() == Paint$Style.b) {
            float f6 = paint.g();
            if (f6 == 0.0f) {
                f6 = 1.0f;
            }
            this.f.a(f2, f3, f4, f3 + f6);
            this.b(j, k, this.f, l);
            this.f.a(f2, f5, f4, f5 + f6);
            this.b(j, k, this.f, l);
            this.f.a(f2, f3, f2 + f6, f5);
            this.b(j, k, this.f, l);
            this.f.a(f4, f3, f4 + f6, f5);
            this.b(j, k, this.f, l);
        } else {
            this.f.a(f2, f3, f4, f5);
            this.b(j, k, this.f, l);
        }
    }

    @Override
    public void a(Rect rect, Paint paint) {
        this.b(rect.a, rect.b, rect.c, rect.d, paint);
    }

    @Override
    public void a(RectF rectF, Paint paint) {
        this.b(rectF.a, rectF.b, rectF.c, rectF.d, paint);
    }

    @Override
    public void a(String string, float f2, float f3, Paint paint) {
        this.b.b((ae)null);
        com.corrodinggames.rts.gameFramework.b.n.E = this;
        this.a.a(string, f2, f3, paint);
    }

    public boolean equals(Object object) {
        return this.a.equals(object);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override
    public void a() {
        this.b.c();
    }

    @Override
    public void a(float f2, float f3, float f4) {
        n n2 = this.b;
        n2.a(f3, f4);
        n2.a(f2);
        n2.a(-f3, -f4);
    }

    @Override
    public void b() {
        this.b.b();
    }

    @Override
    public void a(float f2, float f3, float f4, float f5) {
        n n2 = this.b;
        n2.a(f4, f5);
        n2.a(f2, f3, 1.0f);
        n2.a(-f4, -f5);
    }

    @Override
    public void a(float f2, float f3) {
        this.b.a(f2, f3, 1.0f);
    }

    @Override
    public void a(e e2) {
        if (this.n != null) {
            this.d();
        }
        if (e2 != null) {
            this.b(e2);
        }
        this.n = e2;
    }

    public String toString() {
        return this.a.toString();
    }

    @Override
    public void b(float f2, float f3) {
        this.b.a(f2, f3);
    }

    @Override
    public void a(boolean bl) {
        this.o = bl;
    }

    @Override
    public boolean c() {
        return this.o;
    }

    @Override
    public void a(m m2) {
        m2.a(com.corrodinggames.rts.gameFramework.l.B().bO);
    }

    @Override
    public void a(Lock lock) {
    }

    @Override
    public void b(Lock lock) {
    }

    @Override
    public boolean a(ae ae2) {
        return this.b.d(ae2);
    }

    static {
        k = new Rect(0, 0, 1, 1);
        l = new Paint();
        m = new Rect();
    }
}
