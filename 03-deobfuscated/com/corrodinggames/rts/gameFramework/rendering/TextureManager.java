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
package com.corrodinggames.rts.gameFramework.rendering;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer;
import com.corrodinggames.rts.gameFramework.rendering.FontRenderer;
import com.corrodinggames.rts.gameFramework.rendering.TextureProxy;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.CanvasRenderer;
import com.corrodinggames.rts.gameFramework.rendering.BitmapDrawer;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.DrawContext;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import java.io.File;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.concurrent.locks.Lock;

public class TextureManager
implements TextureManagerInterface {
    static TextureManager a;
    static Texture b;
    static int[] c;
    static Bitmap d;
    static IntBuffer e;
    static IntBuffer f;
    boolean g = false;
    private Context x;
    private Renderer y;
    private Renderer z;
    private OpenGLRenderer A;
    public boolean h;
    public Bitmap i;
    public Texture activeTexture;
    public int k;
    public int l;
    private TextureManager B;
    final Rect m = new Rect();
    final Rect n = new Rect();
    final RectF o = new RectF();
    final RectF p = new RectF();
    final Matrix q = new Matrix();
    final RectF r = new RectF();
    Texture s;
    static final RectF t;
    static final RectF u;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList v = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    static Rect w;


    public TextureManagerInterface a(Texture e2) {
        TextureManager x2 = (TextureManager) this.b(e2);
        x2.h = true;
        return x2;
    }


    public TextureManagerInterface b(Texture e2) {
        if (this.B != null) {
            return this.B.b(e2);
        }
        TextureManager x2 = new TextureManager();
        x2.a(this.x);
        Canvas canvas = new Canvas();
        Bitmap bitmap = e2.setKeepInGpuMemory();
        canvas.a(bitmap);
        x2.z = x2.y = new CanvasRenderer(canvas);
        x2.i = bitmap;
        x2.activeTexture = e2;
        x2.B = this;
        if (e2 != null) {
            x2.k = e2.m();
            x2.l = e2.l();
        }
        return x2;
    }


    public boolean a() {
        return false;
    }


    public void a(Context context) {
        this.x = context;
    }


    public void b() {
        this.s = new TextureProxy(this.loadImageFromResource(R$drawable.error_outmem));
    }

    private void t() {
        if (a != this) {
            a = this;
            TextureManager x2 = this.B != null ? this.B : this;
            boolean bl = x2.c();
            if (bl) {
                Renderer l2 = this.y;
                if (this.B != null) {
                    l2 = this.B.y;
                }
                if (!this.h) {
                    if (b != null) {
                        l2.a((Texture) null);
                        b = null;
                    }
                    if (this.B != null) {
                        this.y = this.z;
                    }
                } else {
                    if (this.B != null) {
                        this.y = l2;
                    }
                    if (this.activeTexture != b) {
                        this.y.a(this.activeTexture);
                        b = this.activeTexture;
                    }
                }
            } else {
                if (b != null) {
                    Renderer l3 = this.y;
                    if (this.B != null) {
                        l3 = this.B.y;
                    }
                    l3.a((Texture) null);
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

    private void a(Paint paint, Texture e2) {
        this.a(paint, false, null, e2);
    }

    public boolean c() {
        if (this.y instanceof DrawContext) {
            return true;
        }
        return this.y instanceof BitmapDrawer;
    }

    private void a(Paint paint, boolean bl, String string, Texture e2) {
        this.t();
    }

    @Override
    public Renderer d() {
        return this.y;
    }


    public void a(Renderer l2) {
        this.y = l2;
    }


    public void a(OpenGLRenderer a2) {
        this.A = a2;
    }


    public Texture loadImageFromResource(int n) {
        return this.a(n, true);
    }

    public Texture a(int n) {  // v19.117: 02b m/y.java L35 原名 (调用点 119 处)
        return this.loadImageFromResource(n);
    }


    public Texture a(int n, boolean bl) {
        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource((Resources)this.x.e(), (int)n, (BitmapFactory.Options)options);
        if (bitmap == null) {
            throw new RuntimeException("Could not load image with resId:" + n);
        }
        return this.a(bitmap);
    }


    public Texture a(InputStream inputStream, boolean bl) {
        Bitmap bitmap;
        if (inputStream == null) {
            throw new RuntimeException("loadImage: steam is null");
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)inputStream, null, (BitmapFactory.Options)options);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.GlobalState.a(com.corrodinggames.rts.gameFramework.ResourceDomainEnum.a, (Throwable)outOfMemoryError);
            if (this.s == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return this.s;
        }
        if (bitmap == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.g("Could not load image from steam");
            return null;
        }
        Log.d("RustedWarfare", "load a:" + bl + " as " + (Object)((Object)bitmap.d()));
        return this.a(bitmap);
    }

    public Texture a(Bitmap bitmap) {
        Texture e2 = new Texture();
        e2.setRenderTarget(bitmap);
        return e2;
    }


    public Texture a(int n2, int n3, boolean bl) {
        return this.b(n2, n3, bl);
    }


    public Texture b(int n2, int n3, boolean bl) {
        Bitmap bitmap;
        try {
            bitmap = Bitmap.a(n2, n3, null);
            if (bitmap != null && !bl && Build.VERSION.SDK_INT >= 12) {
                bitmap.a(false);
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.GlobalState.a(com.corrodinggames.rts.gameFramework.ResourceDomainEnum.b, (Throwable)outOfMemoryError);
            if (this.s == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return this.s;
        }
        if (bitmap == null) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("createBitmap returned null, possible out of memory");
            com.corrodinggames.rts.gameFramework.GlobalState.a(com.corrodinggames.rts.gameFramework.ResourceDomainEnum.b, (Throwable)outOfMemoryError);
            if (this.s == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return this.s;
        }
        return this.a(bitmap);
    }


    public void e() {
    }


    public void a(Texture e2, float f2, float f3, float f4, Paint paint) {  // 02b m/y.a(e,float,float,float,Paint) (D 为误名 v19.133f8) {
        Renderer l2 = this.y;
        l2.b();
        l2.a(f4 + 90.0f, f2, f3);
        this.b(e2, f2 - e2.t, f3 - e2.u, paint);
        l2.a();
    }


    public void A(Texture e2, Rect rect, float f2, float f3, float f4, Paint paint) {
        Renderer l2 = this.y;
        l2.b();
        int n2 = rect.b() >> 1;
        int n3 = rect.c() >> 1;
        this.p.a(f2 - (float)n2, f3 - (float)n3, f2 + (float)n2, f3 + (float)n3);
        l2.a(f4 + 90.0f, f2, f3);
        this.loadImageFromResource(e2, rect, this.p, paint);
        l2.a();
    }


    public void loadImageFromResource(Texture e2, Rect rect, Rect rect2, Paint paint) {
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.y.a(e2, rect, rect2, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }

    public Bitmap c(Texture e2) {
        Bitmap bitmap = e2.setKeepInGpuMemory();
        if (e2.f != e2.e) {
            e2.f = e2.e;
            this.d().a(bitmap);
        }
        return bitmap;
    }


    public void a(Texture e2, Rect rect, Rect rect2, Paint paint) {  // 02b m/y.java L51 — 简化委托 b
        this.b(e2, rect, rect2, paint);
    }

    public void b(Texture e2, Rect rect, Rect rect2, Paint paint) {
        if (!this.g) {
            this.loadImageFromResource(e2, rect, rect2, paint);
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
        e2.j();
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


    public void clearScreen(Rect rect, Paint paint) {
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
        int n2 = n5 * n3;  // 02b m/x.java L535: var10 = var6 * var3
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


    public void f() {
        this.g = false;
        if (d == this.i) {
            int n2 = this.i.b();
            int n3 = this.i.c();
            this.i.b(c, 0, n2, 0, 0, n2, n3);
            d = null;
        }
    }


    public void loadImageFromResource(Texture e2, Rect rect, RectF rectF, Paint paint) {
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.a(paint, e2);
            this.y.a(e2, rect, rectF, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }


    public void clearScreen(Texture e2, float f2, float f3, Paint paint) {
        this.b(e2, f2 - e2.t, f3 - e2.u, paint);
    }


    public void loadImageFromResource(Texture e2, float f2, float f3, Paint paint, float f4, float f5) {
        Renderer l2 = this.y;
        l2.b();
        if (f4 != 0.0f) {
            l2.a(f4 + 90.0f, f2, f3);
        }
        l2.a(f5, f5, f2, f3);
        Bitmap bitmap = this.c(e2);
        l2.a(e2, f2, f3, paint);
        l2.a();
    }

    public void a(Texture e2, float f2, float f3, Paint paint, float f4, float f5) {  // 02b m/y.java L57: a(e,float,float,Paint,float,float) (v19.133d)
        this.loadImageFromResource(e2, f2, f3, paint, f4, f5);
    }


    public void a(Texture e2, float f2, float f3, Paint paint) {  // 02b m/x.java L625: this.b(var1, var2-var1.t, var3-var1.u, var4)
        this.b(e2, f2 - e2.t, f3 - e2.u, paint);
    }


    public void a(Texture e2, Rect rect, RectF rectF, Paint paint) {  // 02b m/x.java L615: a(e,Rect,RectF,Paint)
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.a(paint, e2);
            this.y.a(e2, rect, rectF, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }


    public void b(Texture e2, float f2, float f3, Paint paint) {
        Bitmap bitmap = this.c(e2);
        if (bitmap != null) {
            this.y.a(e2, f2, f3, paint);
            return;
        }
        throw new RuntimeException("bitmap was not drawn");
    }


    public void a(Texture e2, Rect rect, Paint paint) {
        this.a(e2, rect, paint, 0, 0, 0, 0);
    }


    public void a(Texture e2, Rect rect, Paint paint, int n2, int n3, int n4, int n5) {
        FontRenderer.a((TextureManagerInterface) this, e2, rect, paint, n2, n3, n4, n5);
    }


    public void a(Texture e2, RectF rectF, Paint paint, float f2, float f3, int n2, int n3) {
        FontRenderer.a((TextureManagerInterface) this, e2, rectF, paint, f2, f3, n2, n3);
    }


    public void clearScreen(int n2) {
        this.t();
        this.y.a(n2);
    }
    public void b(int n) {  // 02b m/y.java b(int) (v19.133d)
        this.clearScreen(n);
    }



    public void a(int n2, PorterDuff.Mode mode) {
        this.t();
        this.y.a(n2, mode);
    }


    public void a(String string, float f2, float f3, Paint paint, Paint paint2, float f4) {
        float f5 = paint.a(string);
        u.a(f2, f3, f2 + f5, f3 + (float)this.a(string, paint));
        t.a(u);
        if (paint.j() == Paint$Align.b) {
            t.a(-(f5 / 2.0f), 0.0f);
        }
        GameUtils.a(t, f4);
        this.b(paint2);
        this.y.a(t, paint2);
        this.a(string, u.a + f4 / 2.0f, u.d - f4 / 2.0f, paint);
    }


    public void a(String string, float f2, float f3, Paint paint) {
        this.a(paint, string);
        this.y.a(string, f2, f3, paint);
    }


    public void b(Rect rect, Paint paint) {
        this.b(paint);
        this.y.a(rect, paint);
    }


    public void a(RectF rectF, Paint paint) {
        this.b(paint);
        this.y.a(rectF, paint);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void g() {
        if (this.v.size() > 0) {
            com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = this.v;
            synchronized (m2) {
                for (Shader ae2 : (java.util.Collection<Shader>) (java.util.Collection) this.v) {
                    this.y.a(ae2);
                }
                this.v.clear();
            }
        }
    }


    public void h() {
        this.t();
    }


    public void c(Rect rect, Paint paint) {
        this.n.a(rect.a, rect.b, rect.a + rect.c, rect.b + rect.d);
        this.b(paint);
        this.d().a(this.n, paint);
    }


    public void a(Rect rect) {
        if (this.i != null) {
            rect = new Rect(rect);
            int n2 = this.i.c() - this.l;
        }
        this.d().a(rect);
    }


    public void a(RectF rectF) {
        if (this.i != null) {
            rectF = new RectF(rectF);
            int n2 = this.i.c() - this.l;
        }
        this.d().a(rectF);
    }


    public void a(float f2, float f3, float f4, Paint paint) {
        if (f4 < 50.0f) {
            float f5 = 1.0f;
            FontRenderer.a(this, f2, f3, f4, paint, f5);
        } else {
            this.b(paint);
            this.y.a(f2, f3, f4, paint);
        }
    }


    public void b(float f2, float f3, float f4, Paint paint) {
        this.b(paint);
        this.y.a(f2, f3, f4, paint);
    }


    public void a(float[] fArray, int n2, int n3, Paint paint) {
        this.b(paint);
        this.d().a(fArray, n2, n3, paint);
    }


    public void i() {
        this.t();
        this.y.b();
    }


    public void j() {
        this.t();
        this.y.a();
    }


    public void k() {
        this.t();
        this.y.b();
    }


    public void l() {
        this.t();
        this.y.a();
    }


    public void a(float f2, float f3, float f4) {
        this.y.a(f2, f3, f4);
    }


    public void D(float f2, float f3) {
        this.y.a(f2, f3);
    }
    public void a(float f2, float f3) {  // 02b m/y.java L113: a(FF) (v19.133d)
        this.D(f2, f3);
    }



    public void a(float f2, float f3, float f4, float f5) {
        this.y.a(f2, f3, f4, f5);
    }


    public void b(float f2, float f3) {
        this.y.b(f2, f3);
    }


    public void a(float f2, float f3, float f4, float f5, Paint paint) {
        this.b(paint);
        this.d().a(f2, f3, f4, f5, paint);
    }


    public void a(DrawCommand m2) {
        this.d().a(m2);
    }


    public int m() {
        if (this.i != null) {
            return this.k;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return (int)l2.cl;
    }

    @Override
    public int n() {
        if (this.i != null) {
            return this.l;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return (int)l2.cm;
    }


    public void a(int n2, int n3) {
        this.k = n2;
        this.l = n3;
    }

    @Override
    public void o() {
        this.b((Paint)null);
        this.a(0, PorterDuff.Mode.CLEAR);
    }


    public void a(Paint paint) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void a(Shader ae2) {
        if (ae2 != null) {
            com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = this.v;
            synchronized (m2) {
                this.v.add(ae2);
            }
        }
    }


    public void p() {
        this.t();
        if (this.i != null && this.y instanceof CanvasRenderer) {
            this.d().a(this.i);
        }
    }


    public void q() {
    }


    public int a(String string, Paint paint) {
        return (int)paint.k();
    }


    public int b(String string, Paint paint) {
        paint.a(string, 0, string.length(), w);
        return w.a + w.b();
    }

    @Override
    public Texture r() {
        return this.s;
    }


    public void a(Texture e2, File file) {
        throw new RuntimeException("writeImageToFile not yet supported");
    }


    public void a(Lock lock) {
        this.t();
        this.y.a(lock);
    }


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
