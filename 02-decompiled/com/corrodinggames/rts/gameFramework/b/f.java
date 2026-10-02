/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import com.corrodinggames.rts.gameFramework.b.a;
import com.corrodinggames.rts.gameFramework.b.af;
import com.corrodinggames.rts.gameFramework.b.ah;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.e;
import com.corrodinggames.rts.gameFramework.b.f$1;
import com.corrodinggames.rts.gameFramework.b.g;
import com.corrodinggames.rts.gameFramework.b.h;
import com.corrodinggames.rts.gameFramework.b.i;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.v;
import java.util.Map;

public class f {
    protected final k a;
    private Map b;
    private a c;
    private g d;
    private af e;

    public void a(b b2) {
        this.a.c(b2);
    }

    public void a() {
        this.a.d();
    }

    public k b() {
        return this.a;
    }

    public b a(Bitmap bitmap, com.corrodinggames.rts.gameFramework.m.e e2, af af2) {
        this.e = af2;
        b b2 = this.a(bitmap, e2);
        if (af2 instanceof i) {
            i i2 = (i)af2;
            b2 = i2.a(b2, this.a, new f$1(this));
        }
        return b2;
    }

    public void a(Bitmap bitmap) {
        b b2 = (b)this.b.get(bitmap);
        if (b2 != null && b2 instanceof ah) {
            ((ah)b2).l();
        }
        this.b().a(bitmap);
    }

    public b a(Bitmap bitmap, com.corrodinggames.rts.gameFramework.m.e e2) {
        b b2 = (b)this.b.get(bitmap);
        if (b2 == null) {
            this.a.e();
            this.c();
            b2 = new e(bitmap);
            b2.c(this.b());
            b2.j = e2.d();
            n.b(b2.e, b2.f);
            this.b.put(bitmap, b2);
            this.d();
        }
        return b2;
    }

    public void a(float f2, float f3, float f4, v v2) {
        if (v2.c() == Paint$Style.a) {
            this.d.a(0.5f);
        } else {
            float f5 = v2.b();
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.d.a(f5 / (2.0f * f4));
        }
        this.a.a(f2 - f4, f3 - f4, f4, v2, (h)this.d);
    }

    public void a(float f2, float f3, float f4, float f5, v v2) {
        this.a.a(f2, f3, f4, f5, v2, this.c);
    }

    public void c() {
        this.a.b();
    }

    public void d() {
        this.a.c();
    }

    public void e() {
        for (b b2 : this.b.values()) {
            b2.j();
        }
        this.b.clear();
    }

    protected void finalize() {
        super.finalize();
        this.e();
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.a.a(n2, n3, n4, n5);
    }

    public void a(String string, float f2, float f3, Paint paint) {
        this.a.a(string, f2, f3, paint);
    }

    public void a(float[] fArray, int n2, int n3, v v2) {
        this.a.a(fArray, n2, n3, v2, (h)this.c);
    }
}
