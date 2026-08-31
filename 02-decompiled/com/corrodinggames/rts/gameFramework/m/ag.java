/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;

public class ag
extends Paint {
    public static final ag r = new ag();
    boolean s = false;
    ae t;
    boolean u = false;

    public void o() {
        this.u = true;
    }

    public void c(float f) {
        super.b(f);
    }

    @Override
    public void b(float f2) {
        if (this.u) {
            l.b("UniquePaint changed when locked down:");
            l.b("from:" + this.k() + " to: " + f2);
            l.T();
        }
        super.b(f2);
    }

    @Override
    public Typeface a(Typeface typeface) {
        if (this.u) {
            l.b("UniquePaint changed when locked down:");
            l.T();
        }
        return super.a(typeface);
    }

    public static void b(Paint paint) {
        ((ag)paint).o();
    }

    public boolean p() {
        return this.s;
    }

    @Override
    public void a(boolean bl) {
        this.s = bl;
        super.a(bl);
    }

    public ae q() {
        return this.t;
    }

    public void a(ae ae2) {
        this.t = ae2;
    }

    static {
        r.b(-1);
        r.o();
    }
}
