/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.game.units;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.gameFramework.l;

public class e
extends d {
    static com.corrodinggames.rts.gameFramework.m.e a = null;
    float b;
    static PorterDuffColorFilter c = new PorterDuffColorFilter(Color.a(200, 200, 200), PorterDuff.Mode.MULTIPLY);

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.H;
    }

    public static void a_() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.crystal);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        return a;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public void a(int n2) {
    }

    public e(boolean bl) {
        super(bl);
        this.M = a;
        this.b(a);
        this.cj = 11.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 600.0f;
        this.S(1);
        this.n.a(0, -1, 0, 0);
        this.o.a(this.n);
    }

    @Override
    public Paint f() {
        Paint paint = super.f();
        return paint;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        this.b += 0.01f * f2;
        if (this.b > 1.0f) {
            this.b -= 1.0f;
            if (this.b > 1.0f) {
                this.b = 0.0f;
            }
        }
    }

    @Override
    public float g() {
        return 0.02f;
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.a;
    }

    @Override
    public boolean i() {
        return false;
    }

    @Override
    public boolean s_() {
        l l2 = l.B();
        du.a(this.cE());
        return RectF.a(l2.cM, du);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return null;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public float m() {
        return 0.0f;
    }

    @Override
    public float b(int n2) {
        return 0.0f;
    }

    @Override
    public float c(int n2) {
        return 0.0f;
    }

    @Override
    public void a(am am2, int n2) {
    }

    @Override
    public void n() {
        super.n();
        this.b = (this.ep * 5.0f + this.eo * 3.0f) % 1.0f;
    }

    @Override
    public boolean o() {
        return true;
    }

    @Override
    public boolean p() {
        return true;
    }

    @Override
    public boolean q() {
        return true;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
