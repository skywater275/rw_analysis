/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.b;

import android.graphics.RectF;
import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.aa;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.d;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.z;
import com.corrodinggames.rts.gameFramework.m.ae;

public class y {
    aa a;
    float[] b;
    int c;
    int d;
    int e;
    n f;
    int g;
    d h;
    final z i;
    public z j;
    b k;
    ae l;
    boolean m;
    int n;
    float o;
    float p;
    float q;
    float r;

    public void a(ae ae2) {
        this.e = 0;
        this.c = 0;
        this.k = null;
        this.b(ae2);
        this.a();
        com.corrodinggames.rts.gameFramework.b.n.q();
        this.a.d();
    }

    public void a() {
        this.j.d.a(this.f.d);
    }

    public void b() {
        this.m = true;
    }

    void b(ae ae2) {
        if (ae2 != null) {
            this.f.a(ae2.n);
            this.f.c(ae2);
            this.m = false;
        } else {
            this.f.a(this.g);
        }
        this.l = ae2;
    }

    void a(b b2) {
        int n2;
        if (this.k == b2) {
            return;
        }
        if (this.k != null && (n2 = this.k.a()) == b2.a() && n2 != -1) {
            return;
        }
        this.c();
        GLES20.glActiveTexture((int)33984);
        com.corrodinggames.rts.gameFramework.b.n.q();
        b2.c(this.f);
        GLES20.glBindTexture((int)b2.g(), (int)b2.a());
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glUniform1i((int)this.j.e.a, (int)0);
        com.corrodinggames.rts.gameFramework.b.n.q();
        this.k = b2;
    }

    public void c() {
        if (this.e > 0) {
            com.corrodinggames.rts.gameFramework.b.n.q();
            this.a.a(this.b, 0, this.c);
            this.a.b();
            com.corrodinggames.rts.gameFramework.b.n.q();
            this.a.a(4, 0, this.e * 6);
            this.a.c();
            com.corrodinggames.rts.gameFramework.b.n.q();
            this.e = 0;
            this.c = 0;
        }
    }

    public void d() {
        this.c();
        this.k = null;
        this.a.e();
    }

    public void a(int n2) {
        if (this.n == n2) {
            return;
        }
        this.n = n2;
        float f2 = (float)(n2 >>> 24 & 0xFF) * 0.003921569f * 1.0f;
        float f3 = (float)(n2 >>> 16 & 0xFF) * 0.003921569f * f2;
        float f4 = (float)(n2 >>> 8 & 0xFF) * 0.003921569f * f2;
        float f5 = (float)(n2 & 0xFF) * 0.003921569f * f2;
        this.o = f3;
        this.p = f4;
        this.q = f5;
        this.r = f2;
    }

    public void a(b b2, RectF rectF, RectF rectF2, float[] fArray) {
        if (this.e == this.d) {
            this.c();
        }
        this.a(b2);
        if (this.m && this.l != null) {
            this.f.c(this.l);
            this.m = false;
        }
        float f2 = rectF2.a;
        float f3 = rectF2.d;
        float f4 = rectF2.c;
        float f5 = rectF2.b;
        float f6 = rectF.a;
        float f7 = rectF.b;
        float f8 = rectF.c;
        float f9 = rectF.d;
        float f10 = this.o;
        float f11 = this.p;
        float f12 = this.q;
        float f13 = this.r;
        float f14 = fArray[0];
        float f15 = fArray[4];
        float f16 = fArray[1];
        float f17 = fArray[5];
        float f18 = fArray[12];
        float f19 = fArray[13];
        float f20 = f2 * f14;
        float f21 = f4 * f14;
        float f22 = f2 * f16;
        float f23 = f4 * f16;
        float f24 = f3 * f15;
        float f25 = f3 * f17;
        float f26 = f5 * f15;
        float f27 = f5 * f17;
        int n2 = this.c;
        this.b[n2++] = f20 + f24 + f18;
        this.b[n2++] = f22 + f25 + f19;
        this.b[n2++] = f6;
        this.b[n2++] = f9;
        this.b[n2++] = f10;
        this.b[n2++] = f11;
        this.b[n2++] = f12;
        this.b[n2++] = f13;
        this.b[n2++] = f21 + f24 + f18;
        this.b[n2++] = f23 + f25 + f19;
        this.b[n2++] = f8;
        this.b[n2++] = f9;
        this.b[n2++] = f10;
        this.b[n2++] = f11;
        this.b[n2++] = f12;
        this.b[n2++] = f13;
        this.b[n2++] = f21 + f26 + f18;
        this.b[n2++] = f23 + f27 + f19;
        this.b[n2++] = f8;
        this.b[n2++] = f7;
        this.b[n2++] = f10;
        this.b[n2++] = f11;
        this.b[n2++] = f12;
        this.b[n2++] = f13;
        this.b[n2++] = f20 + f26 + f18;
        this.b[n2++] = f22 + f27 + f19;
        this.b[n2++] = f6;
        this.b[n2++] = f7;
        this.b[n2++] = f10;
        this.b[n2++] = f11;
        this.b[n2++] = f12;
        this.b[n2++] = f13;
        this.c = n2;
        ++this.e;
    }
}
