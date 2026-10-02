/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Shader;

public class UniquePaint
extends Paint {
    public static final UniquePaint r = new UniquePaint();
    boolean s = false;
    Shader t;
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
            GlobalState.b("UniquePaint changed when locked down:");
            GlobalState.b("from:" + this.k() + " to: " + f2);
            GlobalState.T();
        }
        super.b(f2);
    }

    @Override
    public Typeface a(Typeface typeface) {
        if (this.u) {
            GlobalState.b("UniquePaint changed when locked down:");
            GlobalState.T();
        }
        return super.a(typeface);
    }

    public static void b(Paint paint) {
        ((UniquePaint) paint).o();
    }

    public boolean p() {
        return this.s;
    }

    @Override
    public void a(boolean bl) {
        this.s = bl;
        super.a(bl);
    }

    public void d(boolean bl) {  // 02b y.a(boolean) 字节码引用 m/ag.d(Z)V
        this.u = bl;
        super.a(bl);
    }

    public void b(boolean bl) {  // 02b y.a(boolean) 字节码引用 m/ag.b(Z)V
        this.s = bl;
        super.a(bl);
    }

    public Shader q() {
        return this.t;
    }

    public void a(Shader ae2) {
        this.t = ae2;
    }

    static {
        r.b(-1);
        r.o();
    }
}
