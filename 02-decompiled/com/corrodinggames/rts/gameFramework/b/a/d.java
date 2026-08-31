/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.b.a;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.a.a.b;
import com.corrodinggames.rts.gameFramework.b.a.c;
import com.corrodinggames.rts.gameFramework.b.a.e;
import com.corrodinggames.rts.gameFramework.b.a.g;

public class d {
    com.corrodinggames.rts.gameFramework.b.a.b a;
    g b;
    float[] c;
    int d;
    int e;
    int f;
    private float[] h;
    private int i;
    int g;

    public d(int n, b b2, com.corrodinggames.rts.gameFramework.b.a.b b3) {
        this.a = b3;
        this.c = new float[n * 4 * 5];
        this.b = new g(n * 4, n * 6);
        this.d = 0;
        this.e = n;
        this.f = 0;
        short[] sArray = new short[n * 6];
        int n2 = sArray.length;
        int n3 = 0;
        int n4 = 0;
        while (n4 < n2) {
            sArray[n4 + 0] = (short)(n3 + 0);
            sArray[n4 + 1] = (short)(n3 + 1);
            sArray[n4 + 2] = (short)(n3 + 2);
            sArray[n4 + 3] = (short)(n3 + 2);
            sArray[n4 + 4] = (short)(n3 + 3);
            sArray[n4 + 5] = (short)(n3 + 0);
            n4 += 6;
            n3 = (short)(n3 + 4);
        }
        this.b.a(sArray, 0, n2);
        this.i = GLES20.glGetUniformLocation((int)b2.b(), (String)"u_MVPMatrix");
    }

    public void a(float[] fArray) {
        this.f = 0;
        this.d = 0;
        this.h = fArray;
        this.g = -1;
    }

    void a(c c2) {
        int n = c2.b;
        if (this.g == n) {
            return;
        }
        this.a();
        e e2 = (e)this.a.s.get(n);
        GLES20.glBindTexture((int)3553, (int)e2.a);
        this.g = n;
    }

    public void a() {
        if (this.f > 0) {
            GLES20.glUniformMatrix4fv((int)this.i, (int)1, (boolean)false, (float[])this.h, (int)0);
            this.b.a(this.c, 0, this.d);
            this.b.a();
            this.b.a(4, 0, this.f * 6);
            this.b.b();
            this.f = 0;
            this.d = 0;
        }
    }

    public void a(float f, float f2, float f3, float f4, c c2) {
        if (this.f == this.e) {
            this.a();
        }
        this.a(c2);
        float f5 = f3 / 2.0f;
        float f6 = f4 / 2.0f;
        float f7 = f - f5;
        float f8 = f2 - f6;
        float f9 = f + f5;
        float f10 = f2 + f6;
        this.c[this.d++] = f7;
        this.c[this.d++] = f8;
        this.c[this.d++] = c2.d;
        this.c[this.d++] = c2.g;
        this.c[this.d++] = f9;
        this.c[this.d++] = f8;
        this.c[this.d++] = c2.f;
        this.c[this.d++] = c2.g;
        this.c[this.d++] = f9;
        this.c[this.d++] = f10;
        this.c[this.d++] = c2.f;
        this.c[this.d++] = c2.e;
        this.c[this.d++] = f7;
        this.c[this.d++] = f10;
        this.c[this.d++] = c2.d;
        this.c[this.d++] = c2.e;
        ++this.f;
    }
}
