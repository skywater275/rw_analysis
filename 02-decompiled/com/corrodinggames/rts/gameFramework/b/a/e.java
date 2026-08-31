/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 *  android.opengl.GLUtils
 */
package com.corrodinggames.rts.gameFramework.b.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint$FontMetrics;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.corrodinggames.rts.gameFramework.b.a.b;
import com.corrodinggames.rts.gameFramework.b.a.c;

public class e {
    int a;
    int b;
    Canvas c;
    public Bitmap d;
    int e;
    int f;
    int g = 0;
    int h = 0;
    int i = 0;
    int j;
    int k;
    int l;
    int m;
    int n;

    public boolean a() {
        return this.g < this.e * this.f;
    }

    public e(int n, int n2, int n3, int n4, int n5, int n6) {
        this.l = n2;
        this.b = n;
        this.e = n / n3;
        this.f = n / n4;
        this.j = n3;
        this.k = n4;
        this.m = n5;
        this.n = n6;
    }

    public void b() {
        this.d = Bitmap.a(this.b, this.b, Bitmap$Config.a);
        this.c = new Canvas();
        this.c.a(this.d);
        this.d.a(0);
    }

    public void c() {
        if (this.d == null) {
            return;
        }
        if (this.a == 0) {
            int[] nArray = new int[1];
            GLES20.glGenTextures((int)1, (int[])nArray, (int)0);
            this.a = nArray[0];
            if (this.a == 0) {
                com.corrodinggames.rts.gameFramework.b.a.b.b("Failed to gen texture page");
                return;
            }
        }
        GLES20.glBindTexture((int)3553, (int)this.a);
        GLES20.glTexParameteri((int)3553, (int)10241, (int)9729);
        GLES20.glTexParameteri((int)3553, (int)10240, (int)9729);
        GLES20.glTexParameterf((int)3553, (int)10242, (float)33071.0f);
        GLES20.glTexParameterf((int)3553, (int)10243, (float)33071.0f);
        GLUtils.texImage2D((int)3553, (int)0, (Bitmap)this.d, (int)0);
    }

    public c a(char c2, Paint paint) {
        boolean bl = true;
        if (this.c == null) {
            this.b();
        }
        int n = this.g / this.e;
        int n2 = this.g % this.e;
        int n3 = n2 * this.j;
        int n4 = n * this.k;
        Paint$FontMetrics paint$FontMetrics = paint.n();
        float f = (float)Math.ceil(Math.abs(paint$FontMetrics.c));
        float[] fArray = new float[2];
        char[] cArray = new char[2];
        cArray[0] = c2;
        paint.a(cArray, 0, 1, fArray);
        float f2 = fArray[0];
        f2 = (int)f2;
        if (f2 > (float)this.j) {
            com.corrodinggames.rts.gameFramework.b.a.b.b("Warning chr is larger then cellWidth: " + c2);
        }
        float f3 = n3 + this.m;
        float f4 = (float)(n4 + this.k) - f - (float)this.n;
        f3 = (int)f3;
        f4 = (int)f4;
        this.c.a(cArray, 0, 1, f3, f4, paint);
        c c3 = new c();
        c3.a = c2;
        c3.b = this.l;
        this.a(c3, n3, n4, this.j, this.k);
        c3.c = f2;
        ++this.g;
        return c3;
    }

    private void a(c c2, float f, float f2, float f3, float f4) {
        c2.d = f / (float)this.b;
        c2.e = f2 / (float)this.b;
        c2.f = c2.d + f3 / (float)this.b;
        c2.g = c2.e + f4 / (float)this.b;
    }
}
