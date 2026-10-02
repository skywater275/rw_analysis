/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.l;

public class m {
    private int a;
    private float[] b = new float[10];
    private float[] c = new float[10];
    private float[] d = new float[10];
    private int[] e = new int[10];
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r;

    public m() {
        int n;
        for (n = 0; n < this.b.length; ++n) {
            this.b[n] = 40.0f;
        }
        for (n = 0; n < this.c.length; ++n) {
            this.c[n] = 40.0f;
        }
    }

    public int a() {
        return this.n;
    }

    public boolean b() {
        return this.m;
    }

    public void c() {
        this.m = this.k;
        this.n = this.a;
    }

    public void a(float f, float f2) {
        this.b[0] = f;
        this.c[0] = f2;
        this.f = this.b[0];
        this.g = this.c[0];
        this.j = 0.0f;
        this.i = 0.0f;
    }

    public void a(float f, float f2, boolean bl, int n) {
        this.r = 0;
        int n2 = this.a = bl ? 1 : 0;
        if (n != -1) {
            com.corrodinggames.rts.appFramework.l.a()[0] = n;
        }
        this.b[0] = f;
        this.c[0] = f2;
        this.d[0] = 0.0f;
        this.e[0] = 0;
        this.k = bl;
        this.l = false;
        if (this.k) {
            this.m = this.k;
        }
        if (this.a > 0) {
            this.n = this.a;
        }
        this.f = this.b[0];
        this.g = this.c[0];
        this.h = this.d[0];
        this.j = 0.0f;
        this.i = 0.0f;
        this.q = false;
        this.p = false;
        this.o = false;
    }

    public float[] d() {
        return this.b;
    }

    public int[] e() {
        return com.corrodinggames.rts.appFramework.l.a();
    }

    public float[] f() {
        return this.c;
    }
}
