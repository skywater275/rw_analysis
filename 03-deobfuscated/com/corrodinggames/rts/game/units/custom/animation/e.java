/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

import com.corrodinggames.rts.game.units.custom.animation.AnimationResourceCurve;

public strictfp class e {
    public Texture a;
    public Texture[] frameTextures;
    public int frameWidth;
    public int frameHeight;
    public int spriteColumns = 1;
    public int spriteRows = 1;

    public void a(AnimationResourceCurve d2) {
        e e2 = this;
        int n = e2.a.p;
        int n2 = e2.a.q;
        e2.frameWidth = n;
        e2.frameHeight = n2;
        if (d2.K > 0) {
            e2.frameWidth = d2.K;
        } else if (d2.J > 0) {
            e2.frameWidth = n / d2.J;
        }
        if (d2.L > 0) {
            e2.frameHeight = d2.L;
        }
        if (e2.frameWidth > 0) {
            e2.spriteRows = n / e2.frameWidth;  // 02b b/e.java L30-32: this.f = var3 / this.c
        }
        if (e2.frameHeight > 0) {
            e2.spriteColumns = n2 / e2.frameHeight;  // 02b b/e.java L34-36: this.e = var4 / this.d
        }
        if (e2.spriteRows <= 0) {
            e2.spriteRows = 1;  // 02b L38-40: this.f = 1
        }
        if (e2.spriteColumns <= 0) {
            e2.spriteColumns = 1;  // 02b L42-44: this.e = 1
        }
    }
}
