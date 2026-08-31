/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.effects;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public final class HUDElementRenderer {
    public String a;
    public int b = 25;
    public int c = 25;
    public int d = 1;
    public int e = 1;
    public int f = 26;
    public int g = 26;
    public int h = Integer.MAX_VALUE;
    public Texture i = null;
    public Texture j = null;
    public boolean k = false;
    static final Rect l = new Rect();
    static final RectF m = new RectF();

    public void setValue() {
        this.j = this.i.h();
        this.j.j();
        for (int i = 0; i < this.j.m(); ++i) {
            for (int j = 0; j < this.j.l(); ++j) {
                int n = this.j.a(i, j);
                this.j.a(i, j, Color.a(Color.a(n), 0, 0, 0));
            }
        }
        this.j.p();
        this.j.s();
    }

    public void setValue(int n2, float f2, float f3, Paint paint) {
        Rect l = HUDElementRenderer.l;
        RectF rectF = m;
        boolean bl = true;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n3 = n2;
        int n4 = 0;
        if (n3 >= this.h) {
            n4 += n3 / this.h;
            n3 %= this.h;
        }
        int n5 = this.d + n3 * this.f;
        int n6 = this.e + n4 * this.g;
        l.a(n5, n6, n5 + this.b, n6 + this.c);
        rectF.a(f2, f3, f2 + (float)l.b(), f3 + (float)l.c());
        if (bl) {
            rectF.a(-rectF.b() / 2.0f, -rectF.c() / 2.0f);
        }
        l2.bO.a(this.i, l, rectF, paint);
    }
}
