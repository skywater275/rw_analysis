/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.effects;

import android.graphics.Paint;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class CloudRenderer {
    boolean a = false;
    Texture b = null;
    Paint c = new UniquePaint();
    RectF d = new RectF();
    float e = 0.0f;
    float f = 0.0f;

    public boolean reset() {
        GlobalState l2 = GlobalState.B();
        return l2.bQ.renderClouds;
    }

    public void reset2() {
        GlobalState l2 = GlobalState.B();
        this.b = l2.bO.a(R$drawable.noise, false);
        this.a = true;
    }

    public void reset(float f2) {
        if (!this.reset()) {
            return;
        }
        if (!this.a) {
            this.reset();
        }
        this.e += 0.2f * f2;
        this.f += 0.07f * f2;
        this.e %= (float)this.b.m();
        this.f %= (float)this.b.l();
    }

    public void b(float f2) {  // 02b d.b.b(float) L43-65: 云层绘制
        if (!this.reset()) {
            return;
        }
        if (!this.a) {
            this.reset2();
        }
        GlobalState l2 = GlobalState.B();
        l2.bO.i();
        float f3 = 3.0f;
        l2.bO.a(f3, f3);
        float f4 = (float)((int)com.corrodinggames.rts.gameFramework.GameUtils.f(-l2.cw / f3, 0.0f));
        float f5 = (float)((int)com.corrodinggames.rts.gameFramework.GameUtils.f(-l2.cx / f3, 0.0f));
        this.d.a(f4, f5, (float)((int)(l2.cA / f3) + 1), (float)((int)(l2.cB / f3) + 1));
        this.c.b(-16777216);
        this.c.c(40);
        float f6 = l2.cw / f3 + f4;
        float f7 = l2.cx / f3 + f5;
        f6 += this.e;
        f7 += this.f;
        l2.bO.a(this.b, this.d, this.c, f6, f7, 0, 0);
        l2.bO.j();
    }

}
