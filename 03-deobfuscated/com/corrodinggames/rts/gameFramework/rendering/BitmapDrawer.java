/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.opengl.ac;
import com.corrodinggames.rts.gameFramework.opengl.f;
import com.corrodinggames.rts.gameFramework.opengl.DefaultBlendMode;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.opengl.LineStyle;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import java.util.concurrent.locks.Lock;

/*
 * v19.133c 渲染层战役: 按 02b m/k.java 对照修复
 * 字段类型: canvasTarget=Sprite->opengl.f (02b b/f a), paintObject=NullRenderer->GLRenderer (02b b/n b),
 *           matrixObject=ZipReader->LineStyle (02b b/v c)
 * 方法误名: this.paintObject(...)->this.b(...), this.canvasTarget(...)->this.a(...) (02b m/k 方法名)
 */
public class BitmapDrawer
implements Renderer {
    public f canvasTarget;  // 02b m/k.java: public b/f a
    public GLRenderer paintObject;  // 02b m/k.java: public b/n b
    LineStyle matrixObject;  // 02b m/k.java: b/v c
    public static boolean d = false;
    Rect e;
    RectF f;
    DefaultBlendMode g;
    RectF h;
    float[] i;
    static Texture j;
    static Rect k;
    static Paint l;
    static Rect m;
    Texture n;
    boolean o;

    public void b(Texture e2) {  // 02b m/k.java L40
        GLObject b2 = this.canvasTarget.a(e2.b(), e2, this.g);
        this.canvasTarget.a(b2);
    }

    public void d() {  // 02b L45
        this.canvasTarget.a();
    }

    public LineStyle a(Paint paint) {  // 02b L49
        this.paintObject.a((Shader) null);
        if (paint == null) {
            return null;
        }
        this.matrixObject.a(paint.d());
        this.matrixObject.a(paint.e());
        this.matrixObject.a(paint.g());
        return this.matrixObject;
    }

    public void a(Rect rect) {  // 02b L61
        this.canvasTarget.a(rect.a, rect.b, rect.c, rect.d);
    }

    public void a(RectF rectF) {  // 02b L65
        this.canvasTarget.a((int)rectF.a, (int)rectF.b, (int)rectF.c, (int)rectF.d);
    }

    public void a(Texture e2, float f2, float f3, Paint paint) {  // 02b L69
        this.e.a(0, 0, e2.m(), e2.l());
        this.f.a(f2, f3, f2 + (float)e2.m(), f3 + (float)e2.l());
        this.b(e2, this.e, this.f, paint);
    }

    public void a(Texture e2, Rect rect, Rect rect2, Paint paint) {  // 02b L75
        this.f.a(rect2);
        this.b(e2, rect, this.f, paint);
    }

    public GLObject a(Bitmap bitmap, Texture e2) {  // 02b L80
        GLObject b2;
        GLRenderer n2 = this.paintObject;
        if (n2.a == null) {
            n2.a = new ac(n2, 1024, 1024);
        }
        if (bitmap.b() < 450 && bitmap.c() < 100 && (b2 = n2.a.a(bitmap)) != null) {
            return b2;
        }
        b2 = this.canvasTarget.a(bitmap, e2, this.g);
        return b2;
    }

    public void a(Texture e2, Rect rect, RectF rectF, Paint paint) {  // 02b L98
        this.b(e2, rect, rectF, paint);
    }

    public void b(Texture e2, Rect rect, RectF rectF, Paint paint) {  // 02b L102
        Bitmap bitmap = e2.b();
        this.h.a(rect);
        if (bitmap == null) {
            throw new RuntimeException("bitmap==null. sourceImage: " + e2.a());
        }
        GLObject b2 = this.a(bitmap, e2);
        GLRenderer n2 = this.paintObject;
        boolean bl = true;
        if (paint == null) {
            n2.w = -1;
        } else {
            int n3 = paint.e();
            if (n3 != -1 && paint.h() == null) {
                n3 = Color.a(Color.a(n3), 255, 255, 255);
            }
            n2.w = n3;
            bl = paint instanceof UniquePaint ? ((UniquePaint) paint).p() : paint.c();
        }
        n2.a(b2, bl ? 9729 : 9728);
        Shader ae2 = null;
        if (paint instanceof UniquePaint) {
            ae2 = ((UniquePaint) paint).q();
        }
        if (e2 != null && ae2 == null) {
            ae2 = e2.B();
        }
        if (ae2 != null) {
            boolean bl2 = ae2.a(paint, e2);
            this.paintObject.a(ae2);
            if (bl2) {
                this.paintObject.e();
                this.paintObject.o();
            }
        } else {
            this.paintObject.a((Shader) null);
        }
        n2.a(b2, this.h, rectF, this.g, null);
    }

    public void a(Bitmap bitmap) {  // 02b L152
        this.canvasTarget.a(bitmap);
    }

    public void a(float f2, float f3, float f4, Paint paint) {  // 02b L156
        this.canvasTarget.a(f2, f3, f4, this.a(paint));
    }

    public void a(int n2, PorterDuff.Mode mode) {  // 02b L160
        this.paintObject.a(this.b(n2));
    }

    public void a(int n2) {  // 02b L164
        this.paintObject.a(this.b(n2));
    }

    float[] b(int n2) {  // 02b L168
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

    public void a(float f2, float f3, float f4, float f5, Paint paint) {  // 02b L180
        this.canvasTarget.a(f2, f3, f4, f5, this.a(paint));
    }

    public void a(float[] fArray, int n2, int n3, Paint paint) {  // 02b L184
        LineStyle v2 = this.a(paint);
        this.canvasTarget.a(fArray, n2, n3, v2);
    }

    public void b(float f2, float f3, float f4, float f5, Paint paint) {  // 02b L189
        if (j == null) {
            Bitmap bitmap = Bitmap.a(1, 1, Bitmap$Config.d);
            bitmap.a(0, 0, -1);
            Texture e2 = new Texture();
            e2.a(bitmap);
            j = e2;
            l.a(false);
            l.a((ColorFilter)(new LightingColorFilter(-1, -16777216)));
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

    public void a(Rect rect, Paint paint) {  // 02b L222
        this.b((float)rect.a, (float)rect.b, (float)rect.c, (float)rect.d, paint);
    }

    public void a(RectF rectF, Paint paint) {  // 02b L226
        this.b(rectF.a, rectF.b, rectF.c, rectF.d, paint);
    }

    public void a(String string, float f2, float f3, Paint paint) {  // 02b L230
        this.paintObject.b((Shader) null);
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.E = this;
        this.canvasTarget.a(string, f2, f3, paint);
    }

    public boolean equals(Object object) {
        return this.canvasTarget.equals(object);
    }

    public int hashCode() {
        return this.canvasTarget.hashCode();
    }

    public void a() {  // 02b L244
        this.paintObject.c();
    }

    public void a(float f2, float f3, float f4) {  // 02b L248
        GLRenderer n2 = this.paintObject;
        n2.a(f3, f4);
        n2.a(f2);
        n2.a(-f3, -f4);
    }

    public void b() {  // 02b L255
        this.paintObject.b();
    }

    public void a(float f2, float f3, float f4, float f5) {  // 02b L259
        GLRenderer n2 = this.paintObject;
        n2.a(f4, f5);
        n2.a(f2, f3, 1.0f);
        n2.a(-f4, -f5);
    }

    public void a(float f2, float f3) {  // 02b L266
        this.paintObject.a(f2, f3, 1.0f);
    }

    public void a(Texture e2) {  // 02b L270
        if (this.n != null) {
            this.d();
        }
        if (e2 != null) {
            this.b(e2);
        }
        this.n = e2;
    }

    public String toString() {
        return this.canvasTarget.toString();
    }

    public void b(float f2, float f3) {  // 02b L286
        this.paintObject.a(f2, f3);
    }

    public void a(boolean bl) {  // 02b L290
        this.o = bl;
    }

    public boolean c() {  // 02b L294
        return this.o;
    }

    public void a(DrawCommand m2) {  // 02b L298
        m2.a(com.corrodinggames.rts.gameFramework.GlobalState.B().bO);
    }

    public void a(Lock lock) {  // 02b L302
    }

    public void b(Lock lock) {  // 02b L304
    }

    public boolean a(Shader ae2) {  // 02b L306
        return this.paintObject.d(ae2);
    }

    static {
        k = new Rect(0, 0, 1, 1);
        l = new Paint();
        m = new Rect();
    }
}
