/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.d;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import java.util.ArrayList;

public class j
extends com.corrodinggames.rts.gameFramework.f.a.l {
    String a;
    Paint b = new ag();
    h c = com.corrodinggames.rts.gameFramework.f.a.h.l;
    ArrayList d;

    public j() {
        this.b.a(Paint$Align.b);
        this.b.b(-16777216);
        this.a(18.0f);
    }

    public void a(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.b(this.b, f2);
        this.e();
    }

    public void a(int n2) {
        this.b.b(n2);
    }

    @Override
    public String a() {
        return super.a() + " (text:" + this.a + ")";
    }

    @Override
    public void a(float f2, float f3) {
        super.a(f2, f3);
        y y2 = this.d();
        RectF rectF = this.a(new RectF(), f2, f3);
        this.c.a(y2, rectF);
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            y2.a(this.a, rectF.d(), rectF.d - this.l, this.b);
        } else {
            int n2 = 0;
            for (String string : this.d) {
                Paint paint = this.b;
                int n3 = com.corrodinggames.rts.gameFramework.f.d.a(paint);
                y2.a(string, rectF.d(), rectF.b + this.k + (float)n3 + (float)(n2 * n3), paint);
                ++n2;
            }
        }
    }

    public void a(String string) {
        this.a = string;
        this.e();
    }

    public Rect c() {
        RectF rectF = this.a(new RectF(), 0.0f, 0.0f);
        Rect rect = new Rect();
        rect.d = (int)rectF.d;
        rect.b = (int)rectF.b;
        rect.a = (int)rectF.a;
        rect.c = (int)rectF.c;
        rect.c = 10000;
        return rect;
    }

    @Override
    public void b() {
        super.b();
        y y2 = this.d();
        Rect rect = this.c();
        this.d = new ArrayList(com.corrodinggames.rts.gameFramework.f.d.a(this.a, rect, this.b, this.b, true));
        this.i = rect.b();
        this.j = rect.c();
        this.i += this.m + this.n;
        this.j += this.k + this.l;
    }
}
