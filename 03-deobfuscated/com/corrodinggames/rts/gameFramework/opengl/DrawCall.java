/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.opengl.ShaderHandles;
import com.corrodinggames.rts.gameFramework.opengl.DrawCallBuffer;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.rendering.Shader;

public class DrawCall {
    DrawCallBuffer a;  // 02b b/aj.java: al a
    float[] b;
    int c;
    int d;
    int e;
    GLRenderer f;  // 02b b/aj.java: n f
    int g;
    public ShaderHandles h;
    GLObject i;  // 02b b/aj.java: b i
    Shader j;
    boolean k;
    int l;
    int m;
    float n;
    float o;
    float p;
    float q;
    float r;
    float s;
    float t;

    public void a(Shader ae2) {
        this.c = 0;
        this.d = 0;
        this.i = null;
        this.b(ae2);
        this.a();
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
        this.a.d();
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
    }

    public void a() {
        this.h.c.a(this.f.d);
    }

    void b(Shader ae2) {
        if (ae2 != null) {
            this.f.a(ae2.n);
            this.f.c(ae2);
            this.k = false;
        } else {
            this.f.a(this.g);
        }
        this.j = ae2;
    }

    public void b() {
        if (this.c > 0) {
            com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
            this.a.a(this.b, 0, this.c);
            this.a.b();
            com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
            this.a.a(this.l, 0, this.c);
            this.a.c();
            com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
            this.c = 0;
            this.d = 0;
        }
    }

    public void c() {
        this.b();
        this.i = null;
        this.a.e();
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
    }

    public void a(int n2) {
        if (this.m == n2) {
            return;
        }
        this.m = n2;
        float f2 = (float)(n2 >>> 24 & 0xFF) * 0.003921569f * 1.0f;
        float f3 = (float)(n2 >>> 16 & 0xFF) * 0.003921569f * f2;
        float f4 = (float)(n2 >>> 8 & 0xFF) * 0.003921569f * f2;
        float f5 = (float)(n2 & 0xFF) * 0.003921569f * f2;
        this.n = f3;
        this.o = f4;
        this.p = f5;
        this.q = f2;
    }

    public void b(int n2) {
        if (this.l != n2) {
            this.b();
            this.l = n2;
        }
    }

    public void a(float f2) {
        if (this.t != f2) {
            this.b();
            GLES20.glLineWidth((float)f2);
            this.t = f2;
        }
    }

    public void a(float f2, float f3) {
        if (this.c + 8 + 24 >= this.e && this.l == 1 && this.d % 2 == 0) {
            this.b();
        }
        if (this.c + 8 >= this.e) {
            this.b();
        }
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
        if (this.k && this.j != null) {
            this.f.c(this.j);
            this.k = false;
        }
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
        this.b[this.c++] = f2;
        this.b[this.c++] = f3;
        this.b[this.c++] = this.r;
        this.b[this.c++] = this.s;
        this.b[this.c++] = this.n;
        this.b[this.c++] = this.o;
        this.b[this.c++] = this.p;
        this.b[this.c++] = this.q;
        ++this.d;
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
    }

    public void a(float f2, float f3, float[] fArray) {
        float f4 = fArray[0];
        float f5 = fArray[4];
        float f6 = fArray[1];
        float f7 = fArray[5];
        float f8 = fArray[12];
        float f9 = fArray[13];
        float f10 = f2 * f4 + f3 * f5 + f8;
        float f11 = f2 * f6 + f3 * f7 + f9;
        this.a(f10, f11);
    }
}
