/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.opengl.FramebufferTexture;
import com.corrodinggames.rts.gameFramework.opengl.ae;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.k;
import java.util.ArrayList;
import java.util.HashMap;

public class ac {
    public k a;
    public FramebufferTexture b;
    public static Bitmap c = Bitmap.a(64, 64, Bitmap$Config.d);
    HashMap d = new HashMap();
    ArrayList e = new ArrayList();
    int f = 0;
    boolean g;
    boolean h = false;
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 1;

    public ac(k k2, int n, int n2) {
        this.a = k2;
        this.b = new FramebufferTexture(k2, n, n2);
    }

    public GLObject a(Bitmap bitmap) {
        ae ae2 = (ae)this.d.get(bitmap);
        if (ae2 != null) {
            if (this.h) {
                this.e.add(bitmap);
            }
            return ae2;
        }
        int n = bitmap.b();
        int n2 = bitmap.c();
        int n3 = this.b.b();
        int n4 = this.b.c();
        if (this.i + n > n3) {
            this.i = 0;
            this.j += this.k + this.l;
            this.k = 0;
        }
        if (this.j + n2 > n4) {
            if (!this.g) {
                this.g = true;
            }
            return null;
        }
        ae2 = new ae();
        ae2.a = this.b.a;
        ae2.l = this.b;
        int n5 = this.i;
        int n6 = this.j;
        this.i += n + this.l;
        if (this.k < n2) {
            this.k = n2;
        }
        this.b.a(this.a, bitmap, n5, n6);
        ae2.o = n5;
        ae2.p = n6;
        ae2.m = (float)n5 / (float)this.b.e;
        ae2.n = (float)n6 / (float)this.b.f;
        ae2.e = this.b.e;
        ae2.f = this.b.f;
        ae2.g = this.b.g;
        ae2.h = this.b.h;
        ae2.c = n;
        ae2.d = n2;
        ++this.f;
        this.d.put(bitmap, ae2);
        return ae2;
    }

    public void b(Bitmap bitmap) {
        ae ae2 = (ae)this.d.get(bitmap);
        if (ae2 != null) {
            this.d.remove(bitmap);
        }
    }
}
