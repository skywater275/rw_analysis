/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import com.corrodinggames.rts.gameFramework.m.e;

public class af {
    public String a;
    public int b = -1;
    public boolean c;
    public boolean d;
    public float[] e = new float[1];
    public e f;
    public boolean g;

    public void a(float f) {
        if (this.e.length != 1) {
            this.e = new float[1];
        }
        if (this.e[0] == f) {
            return;
        }
        this.e[0] = f;
        this.c = true;
    }

    public void a(float f, float f2) {
        if (this.e.length != 2) {
            this.e = new float[2];
        }
        if (this.e[0] == f && this.e[1] == f2) {
            return;
        }
        this.e[0] = f;
        this.e[1] = f2;
        this.c = true;
    }

    public void a(float f, float f2, float f3, float f4) {
        if (this.e.length != 4) {
            this.e = new float[4];
        }
        if (this.e[0] == f && this.e[1] == f2 && this.e[2] == f3 && this.e[3] == f4) {
            return;
        }
        this.e[0] = f;
        this.e[1] = f2;
        this.e[2] = f3;
        this.e[3] = f4;
        this.c = true;
    }

    public void a(e e2) {
        if (this.f != e2) {
            this.f = e2;
            this.c = true;
        }
    }

    public void b(e e2) {
        this.g = true;
        if (this.f != e2) {
            this.f = e2;
            this.c = true;
        }
    }
}
