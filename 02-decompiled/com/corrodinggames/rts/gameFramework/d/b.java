/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Paint;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;

public class b {
    boolean a = false;
    e b = null;
    Paint c = new ag();
    RectF d = new RectF();
    float e = 0.0f;
    float f = 0.0f;

    public boolean a() {
        l l2 = l.B();
        return l2.bQ.renderClouds;
    }

    public void b() {
        l l2 = l.B();
        this.b = l2.bO.a(R$drawable.noise, false);
        this.a = true;
    }

    public void a(float f2) {
        if (!this.a()) {
            return;
        }
        if (!this.a) {
            this.b();
        }
        this.e += 0.2f * f2;
        this.f += 0.07f * f2;
        this.e %= (float)this.b.m();
        this.f %= (float)this.b.l();
    }

    public void b(float f2) {
        if (!this.a()) {
            return;
        }
        if (!this.a) {
            this.b();
        }
        l l2 = l.B();
        l2.bO.i();
        float f3 = 3.0f;
        l2.bO.a(f3, f3);
        float f4 = (int)com.corrodinggames.rts.gameFramework.f.f(-l2.cw / f3, 0.0f);
        float f5 = (int)com.corrodinggames.rts.gameFramework.f.f(-l2.cx / f3, 0.0f);
        this.d.a(f4, f5, (int)(l2.cA / f3) + 1, (int)(l2.cB / f3) + 1);
        this.c.b(-16777216);
        this.c.c(40);
        float f6 = l2.cw / f3 + f4;
        float f7 = l2.cx / f3 + f5;
        l2.bO.a(this.b, this.d, this.c, f6 += this.e, f7 += this.f, 0, 0);
        l2.bO.j();
    }
}
