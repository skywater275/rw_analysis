/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;
import com.corrodinggames.rts.gameFramework.network.SendWorker;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import com.corrodinggames.rts.gameFramework.opengl.GLBatchBase;
import com.corrodinggames.rts.gameFramework.opengl.BlendMode;
import com.corrodinggames.rts.gameFramework.opengl.GLTexture;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.BitmapTexture;
import com.corrodinggames.rts.gameFramework.opengl.ActionType$1;
import com.corrodinggames.rts.gameFramework.opengl.CircleShader;
import com.corrodinggames.rts.gameFramework.opengl.ShaderSource;
import com.corrodinggames.rts.gameFramework.opengl.BlurEffect;
import com.corrodinggames.rts.gameFramework.opengl.k;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.opengl.LineStyle;
import java.util.Map;

public class f {
    protected k a;
    private Map b;
    private GLBatchBase c;
    private CircleShader d;
    private BlendMode e;

    public void a(GLObject b2) {
        this.a.c(b2);
    }

    public void a() {
        this.a.d();
    }

    public k b() {
        return this.a;
    }

    public GLObject a(Bitmap bitmap, com.corrodinggames.rts.gameFramework.rendering.Texture e2, BlendMode af2) {
        this.e = af2;
        GLObject b2 = this.a(bitmap, e2);
        if (af2 instanceof BlurEffect) {
            BlurEffect i2 = (BlurEffect) af2;
            b2 = i2.a(b2, this.a, new f$1(this));
        }
        return b2;
    }

    public void a(Bitmap bitmap) {
        GLObject b2 = (GLObject) this.b.get(bitmap);
        if (b2 != null && b2 instanceof GLTexture) {
            ((GLTexture) b2).l();
        }
        this.b().a(bitmap);
    }

    public GLObject a(Bitmap bitmap, com.corrodinggames.rts.gameFramework.rendering.Texture e2) {
        GLObject b2 = (GLObject) this.b.get(bitmap);
        if (b2 == null) {
            this.a.e();
            this.c();
            b2 = new BitmapTexture(bitmap);
            b2.c(this.b());
            b2.j = e2.d();
            GLRenderer.b(b2.e, b2.f);
            this.b.put(bitmap, b2);
            this.d();
        }
        return b2;
    }

    public void a(float f2, float f3, float f4, LineStyle v2) {
        if (v2.c() == Paint$Style.a) {
            this.d.a(0.5f);
        } else {
            float f5 = v2.b();
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.d.a(f5 / (2.0f * f4));
        }
        this.a.a(f2 - f4, f3 - f4, f4, v2, (ShaderSource) this.d);
    }

    public void a(float f2, float f3, float f4, float f5, LineStyle v2) {
        this.a.a(f2, f3, f4, f5, v2, this.c);
    }

    public void c() {
        this.a.b();
    }

    public void d() {
        this.a.c();
    }

    public void e() {
        for (GLObject b2 : (java.util.Collection<GLObject>) (java.util.Collection) this.b.values()) {
            b2.pushTransform();
        }
        this.b.clear();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.e();
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.a.a(n2, n3, n4, n5);
    }

    public void a(String string, float f2, float f3, Paint paint) {
        this.a.a(string, f2, f3, paint);
    }

    public void a(float[] fArray, int n2, int n3, LineStyle v2) {
        this.a.a(fArray, n2, n3, v2, (ShaderSource) this.c);
    }
}
