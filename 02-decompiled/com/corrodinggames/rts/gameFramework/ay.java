/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.az;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class ay
extends az {
    public int es;
    public int et;
    public float eu;
    public float ev;
    public boolean ew;

    public void b(e e2) {
        this.T(e2.p);
        this.U(e2.q);
        this.ew = true;
    }

    public void a(e e2, int n) {
        this.T(e2.p / n);
        this.U(e2.q);
        this.ew = false;
    }

    public void T(int n) {
        this.es = n;
        this.eu = n / 2;
    }

    public void U(int n) {
        this.et = n;
        this.ev = n / 2;
    }

    public void V(int n) {
        this.es = n;
        this.eu = (float)n / 2.0f;
    }

    public void W(int n) {
        this.et = n;
        this.ev = (float)n / 2.0f;
    }

    protected ay(boolean bl) {
        super(bl);
    }

    @Override
    public void a() {
        super.a();
    }
}
