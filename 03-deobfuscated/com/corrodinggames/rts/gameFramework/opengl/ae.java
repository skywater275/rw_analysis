/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.opengl.FramebufferTexture;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.k;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class ae
extends GLObject {
    FramebufferTexture l;  // 02b b/ae.java: ad l (b/ad=FramebufferTexture)
    public float m;
    public float n;
    public int o;
    public int p;

    public boolean c(k k2) {  // 02b b/ae.java: protected c(k)
        return false;
    }

    public int g() {  // 02b b/ae.java: protected g()
        return 3553;
    }

    public void b(int n) {
        this.l.b(n);
    }

    public int h() {
        return this.l.h();
    }

    public void a(RectF rectF) {
        float f = this.g;
        float f2 = this.h;
        rectF.a = rectF.a * f + this.m;
        rectF.c = rectF.c * f + this.m;
        rectF.b = rectF.b * f2 + this.n;
        rectF.d = rectF.d * f2 + this.n;
    }

    public void a(RectF rectF, RectF rectF2) {
    }

    public void b(k k2) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("SubTexture prepare TODO");
    }
}
