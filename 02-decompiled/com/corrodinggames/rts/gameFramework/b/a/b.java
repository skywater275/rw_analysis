/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 *  android.opengl.Matrix
 */
package com.corrodinggames.rts.gameFramework.b.a;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Paint$FontMetrics;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.a.a.a;
import com.corrodinggames.rts.gameFramework.b.a.c;
import com.corrodinggames.rts.gameFramework.b.a.d;
import com.corrodinggames.rts.gameFramework.b.a.e;
import java.util.ArrayList;

public class b {
    AssetManager a;
    d b;
    int c;
    int d;
    float e;
    float f;
    float g;
    float h;
    float i;
    int j;
    int k;
    int l;
    int m;
    float n;
    float o;
    float p;
    boolean q = true;
    private com.corrodinggames.rts.gameFramework.b.a.a.b x;
    private int y;
    private int z;
    public Paint r;
    public ArrayList s = new ArrayList();
    c[][] t = new c[256][];
    boolean u;
    int v = Integer.MAX_VALUE;
    public static boolean w = true;

    public c a(char c2) {
        c c3 = this.b(c2);
        if (c3 == null && this.u) {
            com.corrodinggames.rts.gameFramework.b.a.b.b("Loading glyph:" + c2);
            this.c(c2);
            this.a();
        }
        return c3;
    }

    public c b(char c2) {
        if (c2 > '\uffff') {
            return null;
        }
        c[] cArray = this.t[c2 / 256];
        if (cArray != null) {
            c c3 = cArray[c2 & 0xFF];
            return c3;
        }
        return null;
    }

    public void a(char c2, c c3) {
        c[] cArray = this.t[c2 / 256];
        if (cArray == null) {
            cArray = new c[257];
            this.t[c2 / 256] = cArray;
        }
        cArray[c2 & 0xFF] = c3;
    }

    public void c(char c2) {
        if (c2 > '\uffff') {
            return;
        }
        if (this.s.size() == 0) {
            this.b();
        }
        if (!((e)this.s.get(this.s.size() - 1)).a()) {
            if (this.s.size() < this.v) {
                this.b();
            } else {
                return;
            }
        }
        c c3 = ((e)this.s.get(this.s.size() - 1)).a(c2, this.r);
        this.a(c2, c3);
    }

    public void a() {
        if (this.s.size() > 0) {
            ((e)this.s.get(this.s.size() - 1)).c();
        }
    }

    public void b() {
        this.a();
        int n = 512;
        e e2 = new e(n, this.s.size(), this.j, this.k, this.c, this.d);
        this.s.add(e2);
    }

    public b(com.corrodinggames.rts.gameFramework.b.a.a.b b2, AssetManager assetManager) {
        if (b2 == null) {
            b2 = new a();
            b2.a();
        }
        this.a = assetManager;
        this.b = new d(24, b2, this);
        this.c = 0;
        this.d = 0;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = 0.0f;
        this.x = b2;
        this.y = GLES20.glGetUniformLocation((int)this.x.b(), (String)"u_Color");
        this.z = GLES20.glGetUniformLocation((int)this.x.b(), (String)"u_Texture");
    }

    public void a(boolean bl) {
        this.u = bl;
    }

    public void a(int n) {
        this.v = n;
    }

    public b(AssetManager assetManager) {
        this(null, assetManager);
    }

    public boolean a(Paint paint, int n, int n2, int n3) {
        char c2;
        if (this.r != null) {
            throw new RuntimeException("Already loaded");
        }
        this.c = n2;
        this.d = n3;
        this.r = paint;
        this.r.a(true);
        this.r.b((float)n);
        this.r.b(-1);
        Paint$FontMetrics paint$FontMetrics = this.r.n();
        this.e = (float)Math.ceil(Math.abs(paint$FontMetrics.d) + Math.abs(paint$FontMetrics.a));
        this.f = (float)Math.ceil(Math.abs(paint$FontMetrics.b));
        this.g = (float)Math.ceil(Math.abs(paint$FontMetrics.c));
        char[] cArray = new char[2];
        this.i = 0.0f;
        this.h = 0.0f;
        float[] fArray = new float[2];
        int n4 = 0;
        for (c2 = ' '; c2 <= '~'; c2 = (char)((char)(c2 + 1))) {
            cArray[0] = c2;
            paint.a(cArray, 0, 1, fArray);
            float f = fArray[0];
            if (f > this.h) {
                this.h = f;
            }
            ++n4;
        }
        this.i = this.e;
        this.j = (int)this.h + 2 * this.c;
        this.k = (int)this.i + 2 * this.d;
        for (c2 = ' '; c2 <= '~'; c2 = (char)(c2 + '\u0001')) {
            this.c(c2);
        }
        this.a();
        return true;
    }

    public void a(float f, float f2, float f3, float f4, float[] fArray) {
        this.a(f, f2, f3, f4);
        this.b.a(fArray);
    }

    public static void c() {
        int n;
        if (w && (n = GLES20.glGetError()) != 0) {
            Throwable throwable = new Throwable();
            Log.b("GLTEXT", "GL error: " + n, throwable);
        }
    }

    void a(float f, float f2, float f3, float f4) {
        GLES20.glUseProgram((int)this.x.b());
        float[] fArray = new float[]{f, f2, f3, f4};
        GLES20.glUniform4fv((int)this.y, (int)1, (float[])fArray, (int)0);
        GLES20.glActiveTexture((int)33984);
        GLES20.glUniform1i((int)this.z, (int)0);
        com.corrodinggames.rts.gameFramework.b.a.b.c();
    }

    public void d() {
        this.b.a();
    }

    public void a(String string, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = (float)this.k * this.o;
        float f8 = (float)this.j * this.n;
        int n = string.length();
        float f9 = f8 / 2.0f - (float)this.c * this.n;
        float f10 = f7 / 2.0f - (float)this.d * this.o - this.g * this.o;
        if (this.q) {
            f9 = (int)f9;
            f10 = (int)f10;
        }
        f += f9;
        f2 += f10;
        float[] fArray = null;
        boolean bl = false;
        if (f3 == 0.0f && f6 == 0.0f && f4 == 0.0f && f5 == 0.0f) {
            bl = true;
        } else {
            fArray = new float[16];
            Matrix.setIdentityM((float[])fArray, (int)0);
            Matrix.translateM((float[])fArray, (int)0, (float)f, (float)f2, (float)f3);
            if (f6 != 0.0f || f4 != 0.0f || f5 != 0.0f) {
                Matrix.rotateM((float[])fArray, (int)0, (float)f6, (float)0.0f, (float)0.0f, (float)1.0f);
                Matrix.rotateM((float[])fArray, (int)0, (float)f4, (float)1.0f, (float)0.0f, (float)0.0f);
                Matrix.rotateM((float[])fArray, (int)0, (float)f5, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        float f11 = 0.0f;
        float f12 = 0.0f;
        for (int i = 0; i < n; ++i) {
            c c2;
            char c3 = string.charAt(i);
            int n2 = c3 - 32;
            if (n2 < 0 || n2 >= 96) {
                n2 = 95;
            }
            float f13 = f12;
            float f14 = f11;
            if (bl) {
                f13 += f;
                f14 += f2;
            }
            if ((c2 = this.a(c3)) == null) continue;
            this.b.a(f13, f14, f8, f7, c2);
            float f15 = (c2.c + this.p) * this.n;
            if (this.q) {
                f15 = (int)(f15 + 0.95f);
            }
            f12 += f15;
        }
    }

    public void a(String string, float f, float f2, float f3, float f4) {
        this.a(string, f, f2, f3, 0.0f, 0.0f, f4);
    }

    public void a(String string, float f, float f2, float f3) {
        this.a(string, f, f2, 0.0f, f3);
    }

    public void a(String string, float f, float f2) {
        this.a(string, f, f2, 0.0f, 0.0f);
    }

    public void a(float f) {
        this.n = this.o = f;
    }

    public float a(String string) {
        float f = 0.0f;
        int n = string.length();
        for (int i = 0; i < n; ++i) {
            char c2 = string.charAt(i);
            c c3 = this.b(c2);
            if (c3 == null) continue;
            f += c3.c * this.n;
        }
        return f += n > 1 ? (float)(n - 1) * this.p * this.n : 0.0f;
    }

    public static void b(String string) {
        Log.b("GLTEXT", "debug:" + string);
    }
}
