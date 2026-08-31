/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.PorterDuff$Mode
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.m;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.a;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ad;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.j;
import com.corrodinggames.rts.gameFramework.m.k;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.o;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.u;
import java.io.File;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.concurrent.locks.Lock;

public class x
implements y {
    static x a;
    static e b;
    static int[] c;
    static Bitmap d;
    static IntBuffer e;
    static IntBuffer f;
    boolean g = false;
    private Context x;
    private com.corrodinggames.rts.gameFramework.m.l y;
    private com.corrodinggames.rts.gameFramework.m.l z;
    private a A;
    public boolean h;
    public Bitmap i;
    public e j;
    public int k;
    public int l;
    private x B;
    final Rect m = new Rect();
    final Rect n = new Rect();
    final RectF o = new RectF();
    final RectF p = new RectF();
    final Matrix q = new Matrix();
    final RectF r = new RectF();
    e s;
    static final RectF t;
    static final RectF u;
    com.corrodinggames.rts.gameFramework.utility.m v = new com.corrodinggames.rts.gameFramework.utility.m();
    static Rect w;

    @Override
    public y a(e e2) {
        x x2 = (x)this.b(e2);
        x2.h = true;
        return x2;
    }

    @Override
    public y b(e e2) {
        if (this.B != null) {
            return this.B.b(e2);
        }
        x x2 = new x();
        x2.a(this.x);
        Canvas canvas = new Canvas();
        Bitmap bitmap = e2.b();
        canvas.a(bitmap);
        x2.z = x2.y = new j(canvas);
        x2.i = bitmap;
        x2.j = e2;
        x2.B = this;
        if (e2 != null) {
            x2.k = e2.m();
            x2.l = e2.l();
        }
        return x2;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public void a(Context context) {
        this.x = context;
    }

    @Override
    public void b() {
        this.s = new ad(this.a(R$drawable.error_outmem));
    }

    private void t() {
        if (a != this) {
            a = this;
            x x2 = this.B != null ? this.B : this;
            boolean bl = x2.c();
            if (bl) {
                com.corrodinggames.rts.gameFramework.m.l l2 = this.y;
                if (this.B != null) {
                    l2 = this.B.y;
                }
                if (!this.h) {
                    if (b != null) {
                        l2.a((e)null);
                        b = null;
                    }
                    if (this.B != null) {
                        this.y = this.z;
                    }
                } else {
                    if (this.B != null) {
                        this.y = l2;
                    }
                    if (this.j != b) {
                        this.y.a(this.j);
                        b = this.j;
                    }
                }
            } else {
                if (b != null) {
                    com.corrodinggames.rts.gameFramework.m.l l3 = this.y;
                    if (this.B != null) {
                        l3 = this.B.y;
                    }
                    l3.a((e)null);
                    b = null;
                }
                if (this.B != null) {
                    this.y = this.z;
                }
            }
        }
    }

    private void a(Paint paint, String string) {
        this.a(paint, true, string, null);
    }

    private void b(Paint paint) {
        this.a(paint, false, null, null);
    }

    private void a(Paint paint, e e2) {
        this.a(paint, false, null, e2);
    }

    public boolean c() {
        if (this.y instanceof o) {
            return true;
        }
        return this.y instanceof k;
    }

    private void a(Paint paint, boolean bl, String string, e e2) {
        this.t();
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.l d() {
        return this.y;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.l l2) {
        this.y = l2;
    }

    @Override
    public void a(a a2) {
        this.A = a2;
    }

    @Override
    public e a(int n) {
        return this.a(n, true);
    }

    @Override
    public e a(int n, boolean bl) {
        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();
        options.inPreferredConfig = bl ? Bitmap$Config.d : Bitmap$Config.b;
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource((Resources)this.x.e(), (int)n, (BitmapFactory.Options)options);
        if (bitmap == null) {
            throw new RuntimeException("Could not load image with resId:" + n);
        }
        return this.a(bitmap);
    }

    @Override
    public e a(InputStream inputStream, boolean bl) {
        Bitmap bitmap;
        if (inputStream == null) {
            throw new RuntimeException("loadImage: steam is null");
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = bl ? Bitmap$Config.d : Bitmap$Config.b;
        options.inScaled = false;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)inputStream, null, (BitmapFactory.Options)options);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.a, (Throwable)outOfMemoryError);
            if (this.s == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return this.s;
        }
        if (bitmap == null) {
            com.corrodinggames.rts.gameFramework.l.g("Could not load image from steam");
            return null;
        }
        Log.d("RustedWarfare", "load a:" + bl + " as " + (Object)((Object)bitmap.d()));
        return this.a(bitmap);
    }

    public e a(Bitmap bitmap) {
        e e2 = new e();
        e2.a(bitmap);
        return e2;
    }

    @Override
    public e a(int n2, int n3, boolean bl) {
        return this.b(n2, n3, bl);
    }

    @Override
    public e b(int n2, int n3, boolean bl) {
        Bitmap bitmap;
        Bitmap$Config bitmap$Config = bl ? Bitmap$Config.d : Bitmap$Config.d;
        try {
            bitmap = Bitmap.a(n2, n3, bitmap$Config);
            if (bitmap != null && !bl && bitmap$Config == Bitmap$Config.d && Build.VERSION.SDK_INT >= 12) {
                bitmap.a(false);
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.b, (Throwable)outOfMemoryError);
            if (this.s == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return this.s;
        }
        if (bitmap == null) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("createBitmap returned null, possible out of memory");
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.b, (Throwable)outOfMemoryError);
            if (this.s == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return this.s;
        }
        return this.a(bitmap);
    }

    @Override
    public void e() {
    }

    @Override
    public void a(e e2, float f2, float f3, float f4, Paint paint) {
        com.corrodinggames.rts.gameFramework.m.l l2 = this.y;
        l2.b();
        l2.a(f4 + 90.0f, f2, f3);
        this.b(e2, f2 - e2.t, f3 - e2.u, paint);
        l2.a();
    }

    @Override
    public void a(e e2, Rect rect, float f2, float f3, float f4, Paint paint) {
        com.corrodinggames.rts.gameFramework.m.l l2 = this.y;
        l2.b();
        int n2 = rect.b() >> 1;
        int n3 = rect.c() >> 1;
        this.p.a(f2 - (float)n2, f3 - (float)n3, f2 + (float)n2, f3 + (float)n3);
        l2.a(f4 + 90.0f, f2, f3);
        this.a(e2, rect, this.p, paint);
        l2.a();
    }

    @Override
    public void a(e e2, Rect rect, Rect rect2, Paint paint) {
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.y.a(e2, rect, rect2, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }

    public Bitmap c(e e2) {
        Bitmap bitmap = e2.b();
        if (e2.f != e2.e) {
            e2.f = e2.e;
            this.d().a(bitmap);
        }
        return bitmap;
    }

    @Override
    public void b(e e2, Rect rect, Rect rect2, Paint paint) {
        if (!this.g) {
            this.a(e2, rect, rect2, paint);
            return;
        }
        Bitmap bitmap = this.c(e2);
        boolean bl = e2.f();
        if (paint.f() < 255) {
            bl = true;
        }
        int n2 = this.i.b();
        int n3 = this.i.c();
        int n4 = bitmap.b();
        int n5 = bitmap.c();
        e2.i();
        int[] nArray = e2.j;
        if (d != this.i) {
            this.a(false);
        }
        int[] nArray2 = c;
        int n6 = rect2.b;
        int n7 = rect2.d;
        int n8 = rect2.a;
        int n9 = rect2.c;
        int n10 = rect.b;
        int n11 = rect.d;
        int n12 = rect.a;
        int n13 = rect.c;
        float f2 = 1.0f;
        float f3 = 1.0f;
        int n14 = n13 - n12;
        int n15 = n11 - n10;
        int n16 = n9 - n8;
        int n17 = n7 - n6;
        if (n17 != 0) {
            f2 = (float)n15 / (float)n17;
        }
        if (n16 != 0) {
            f3 = (float)n14 / (float)n16;
        }
        if (n6 < 0) {
            n10 = (int)((float)n10 + (float)(-n6) * f2);
            n6 = 0;
        }
        if (n7 > n3 - 1) {
            n7 = n3 - 1;
        }
        if (n6 > n7) {
            return;
        }
        if (n8 < 0) {
            n12 = (int)((float)n12 + (float)(-n8) * f3);
            n8 = 0;
        }
        if (n9 > n2 - 1) {
            n9 = n2 - 1;
        }
        if (n8 > n9) {
            return;
        }
        n14 = n6 * n2;
        n15 = n10 * n4;
        n16 = n7 * n2;
        float f4 = 0.0f;
        int n18 = n8;
        int n19 = n12;
        int n20 = n9;
        if (!bl) {
            float f5 = f3;
            while (n14 < n16) {
                int n21;
                int n22;
                int n23 = n15 + (int)f4 * n4;
                int n24 = n23 + n19;
                float f6 = 0.0f;
                int n25 = n14 + n20;
                int n26 = n25 - 4;
                for (n22 = n14 + n18; n22 < n26; ++n22) {
                    n21 = n24 + (int)f6;
                    nArray2[n22] = nArray[n21];
                    n21 = n24 + (int)(f6 += f3);
                    nArray2[++n22] = nArray[n21];
                    n21 = n24 + (int)(f6 += f3);
                    nArray2[++n22] = nArray[n21];
                    n21 = n24 + (int)(f6 += f3);
                    nArray2[++n22] = nArray[n21];
                    f6 += f3;
                }
                while (n22 < n25) {
                    n21 = n24 + (int)f6;
                    nArray2[n22] = nArray[n21];
                    ++n22;
                    f6 += f3;
                }
                n14 += n2;
                f4 += f2;
            }
        } else {
            int n27 = paint.e();
            int n28 = n27 >>> 24;
            int n29 = -16777216;
            while (n14 < n16) {
                int n30 = n14 + n18;
                int n31 = n15 + (int)f4 * n4;
                int n32 = n31 + n19;
                float f7 = 0.0f;
                int n33 = n14 + n20;
                int n34 = -1;
                int n35 = 0;
                int n36 = 0;
                int n37 = 0;
                int n38 = 0;
                while (n30 < n33) {
                    int n39;
                    int n40 = n32 + (int)f7;
                    if (n40 != n34) {
                        n35 = nArray[n40];
                        n36 = (n35 >>> 24) * n28 >> 8;
                        n37 = n35 & 0xFF00FF;
                        n38 = n35 & 0xFF00;
                        if (n36 == 0) {
                            ++n30;
                            f7 += f3;
                            continue;
                        }
                        n34 = n40;
                    }
                    int n41 = nArray2[n30];
                    int n42 = n41 & 0xFF00FF;
                    int n43 = n41 & 0xFF00;
                    n42 += (n37 - n42) * n36 >> 8;
                    n43 += (n38 - n43) * n36 >> 8;
                    nArray2[n30] = n39 = 0xFF000000 | n42 & 0xFF00FF | n43 & 0xFF00;
                    ++n30;
                    f7 += f3;
                }
                n14 += n2;
                f4 += f2;
            }
        }
    }

    @Override
    public void a(Rect rect, Paint paint) {
        int n2;
        boolean bl;
        if (!this.g) {
            this.b(rect, paint);
            return;
        }
        if (d != this.i) {
            this.a(false);
        }
        int n3 = this.i.b();
        int n4 = this.i.c();
        int[] nArray = c;
        int n5 = rect.b;
        int n6 = rect.d;
        int n7 = rect.a;
        int n8 = rect.c;
        if (n5 < 0) {
            n5 = 0;
        }
        if (n6 > n4 - 1) {
            n6 = n4 - 1;
        }
        if (n5 > n6) {
            return;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        if (n8 > n3 - 1) {
            n8 = n3 - 1;
        }
        if (n7 > n8) {
            return;
        }
        int n9 = n6 * n3;
        int n10 = n7;
        int n11 = n8;
        int n12 = paint.e();
        int n13 = n12 >>> 24;
        float f2 = 0.003921569f;
        boolean bl2 = bl = n13 < 255;
        if (!bl) {
            for (n2 = n5 * n3; n2 < n9; n2 += n3) {
                int n14 = n2 + n11;
                for (int i = n2 + n10; i < n14; ++i) {
                    nArray[i] = n12;
                }
            }
        } else {
            int n15 = n12 >> 16 & 0xFF;
            int n16 = n12 >> 8 & 0xFF;
            int n17 = n12 & 0xFF;
            float f3 = (float)n13 * 0.003921569f;
            n15 = (int)((float)n15 * f3);
            n16 = (int)((float)n16 * f3);
            n17 = (int)((float)n17 * f3);
            int n18 = 255 - n13;
            int n19 = -16777216;
            int n20 = 0xFF000000 | n15 << 16 | n16 << 8 | n17;
            while (n2 < n9) {
                int n21 = n2 + n11;
                for (int i = n2 + n10; i < n21; ++i) {
                    int n22;
                    int n23 = nArray[i];
                    int n24 = n23 & 0xFF00FF;
                    int n25 = n23 & 0xFF00;
                    n24 = n24 * n18 >> 8;
                    n25 = n25 * n18 >> 8;
                    nArray[i] = n22 = (n24 & 0xFF00FF | n25 & 0xFF00) + n20;
                }
                n2 += n3;
            }
        }
    }

    @Override
    public void a(boolean bl) {
        this.g = true;
        if (d == this.i) {
            return;
        }
        int n2 = this.i.b();
        int n3 = this.i.c();
        int n4 = n2 * n3;
        if (c == null || c.length < n4) {
            c = new int[n4];
        }
        if (!bl) {
            this.i.a(c, 0, n2, 0, 0, n2, n3);
        }
        d = this.i;
    }

    @Override
    public void f() {
        this.g = false;
        if (d == this.i) {
            int n2 = this.i.b();
            int n3 = this.i.c();
            this.i.b(c, 0, n2, 0, 0, n2, n3);
            d = null;
        }
    }

    @Override
    public void a(e e2, Rect rect, RectF rectF, Paint paint) {
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.a(paint, e2);
            this.y.a(e2, rect, rectF, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }

    @Override
    public void a(e e2, float f2, float f3, Paint paint) {
        this.b(e2, f2 - e2.t, f3 - e2.u, paint);
    }

    @Override
    public void a(e e2, float f2, float f3, Paint paint, float f4, float f5) {
        com.corrodinggames.rts.gameFramework.m.l l2 = this.y;
        l2.b();
        if (f4 != 0.0f) {
            l2.a(f4 + 90.0f, f2, f3);
        }
        l2.a(f5, f5, f2, f3);
        Bitmap bitmap = this.c(e2);
        l2.a(e2, f2, f3, paint);
        l2.a();
    }

    @Override
    public void b(e e2, float f2, float f3, Paint paint) {
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.y.a(e2, f2, f3, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }

    @Override
    public void a(e e2, Rect rect, Paint paint) {
        this.a(e2, rect, paint, 0, 0, 0, 0);
    }

    @Override
    public void a(e e2, Rect rect, Paint paint, int n2, int n3, int n4, int n5) {
        aa.a((y)this, e2, rect, paint, n2, n3, n4, n5);
    }

    @Override
    public void a(e e2, RectF rectF, Paint paint, float f2, float f3, int n2, int n3) {
        aa.a((y)this, e2, rectF, paint, f2, f3, n2, n3);
    }

    @Override
    public void b(int n2) {
        this.t();
        this.y.a(n2);
    }

    @Override
    public void a(int n2, PorterDuff.Mode mode) {
        this.t();
        this.y.a(n2, mode);
    }

    @Override
    public void a(String string, float f2, float f3, Paint paint, Paint paint2, float f4) {
        float f5 = paint.a(string);
        u.a(f2, f3, f2 + f5, f3 + (float)this.a(string, paint));
        t.a(u);
        if (paint.j() == Paint$Align.b) {
            t.a(-(f5 / 2.0f), 0.0f);
        }
        com.corrodinggames.rts.gameFramework.f.a(t, f4);
        this.b(paint2);
        this.y.a(t, paint2);
        this.a(string, com.corrodinggames.rts.gameFramework.m.x.u.a + f4 / 2.0f, com.corrodinggames.rts.gameFramework.m.x.u.d - f4 / 2.0f, paint);
    }

    @Override
    public void a(String string, float f2, float f3, Paint paint) {
        this.a(paint, string);
        this.y.a(string, f2, f3, paint);
    }

    @Override
    public void b(Rect rect, Paint paint) {
        this.b(paint);
        this.y.a(rect, paint);
    }

    @Override
    public void a(RectF rectF, Paint paint) {
        this.b(paint);
        this.y.a(rectF, paint);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void g() {
        if (this.v.size() > 0) {
            com.corrodinggames.rts.gameFramework.utility.m m2 = this.v;
            synchronized (m2) {
                for (ae ae2 : this.v) {
                    this.y.a(ae2);
                }
                this.v.clear();
            }
        }
    }

    @Override
    public void h() {
        this.t();
    }

    @Override
    public void c(Rect rect, Paint paint) {
        this.n.a(rect.a, rect.b, rect.a + rect.c, rect.b + rect.d);
        this.b(paint);
        this.d().a(this.n, paint);
    }

    @Override
    public void a(Rect rect) {
        if (this.i != null) {
            rect = new Rect(rect);
            int n2 = this.i.c() - this.l;
        }
        this.d().a(rect);
    }

    @Override
    public void a(RectF rectF) {
        if (this.i != null) {
            rectF = new RectF(rectF);
            int n2 = this.i.c() - this.l;
        }
        this.d().a(rectF);
    }

    @Override
    public void a(float f2, float f3, float f4, Paint paint) {
        if (f4 < 50.0f) {
            float f5 = 1.0f;
            aa.a(this, f2, f3, f4, paint, f5);
        } else {
            this.b(paint);
            this.y.a(f2, f3, f4, paint);
        }
    }

    @Override
    public void b(float f2, float f3, float f4, Paint paint) {
        this.b(paint);
        this.y.a(f2, f3, f4, paint);
    }

    @Override
    public void a(float[] fArray, int n2, int n3, Paint paint) {
        this.b(paint);
        this.d().a(fArray, n2, n3, paint);
    }

    @Override
    public void i() {
        this.t();
        this.y.b();
    }

    @Override
    public void j() {
        this.t();
        this.y.a();
    }

    @Override
    public void k() {
        this.t();
        this.y.b();
    }

    @Override
    public void l() {
        this.t();
        this.y.a();
    }

    @Override
    public void a(float f2, float f3, float f4) {
        this.y.a(f2, f3, f4);
    }

    @Override
    public void a(float f2, float f3) {
        this.y.a(f2, f3);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5) {
        this.y.a(f2, f3, f4, f5);
    }

    @Override
    public void b(float f2, float f3) {
        this.y.b(f2, f3);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, Paint paint) {
        this.b(paint);
        this.d().a(f2, f3, f4, f5, paint);
    }

    @Override
    public void a(m m2) {
        this.d().a(m2);
    }

    @Override
    public int m() {
        if (this.i != null) {
            return this.k;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return (int)l2.cl;
    }

    @Override
    public int n() {
        if (this.i != null) {
            return this.l;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return (int)l2.cm;
    }

    @Override
    public void a(int n2, int n3) {
        this.k = n2;
        this.l = n3;
    }

    @Override
    public void o() {
        this.b((Paint)null);
        this.a(0, PorterDuff.Mode.CLEAR);
    }

    @Override
    public void a(Paint paint) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(ae ae2) {
        if (ae2 != null) {
            com.corrodinggames.rts.gameFramework.utility.m m2 = this.v;
            synchronized (m2) {
                this.v.add(ae2);
            }
        }
    }

    @Override
    public void p() {
        this.t();
        if (this.i != null && this.y instanceof j) {
            this.d().a(this.i);
        }
    }

    @Override
    public void q() {
    }

    @Override
    public int a(String string, Paint paint) {
        return (int)paint.k();
    }

    @Override
    public int b(String string, Paint paint) {
        paint.a(string, 0, string.length(), w);
        return com.corrodinggames.rts.gameFramework.m.x.w.a + w.b();
    }

    @Override
    public e r() {
        return this.s;
    }

    @Override
    public void a(e e2, File file) {
        throw new RuntimeException("writeImageToFile not yet supported");
    }

    @Override
    public void a(Lock lock) {
        this.t();
        this.y.a(lock);
    }

    @Override
    public void b(Lock lock) {
        this.t();
        this.y.b(lock);
    }

    @Override
    public float s() {
        return 1.0f;
    }

    static {
        e = IntBuffer.allocate(0);
        f = IntBuffer.allocate(0);
        t = new RectF();
        u = new RectF();
        w = new Rect();
    }
}
