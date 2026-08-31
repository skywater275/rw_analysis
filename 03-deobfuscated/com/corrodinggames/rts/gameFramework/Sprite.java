/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.SpriteBase;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp abstract class Sprite
extends SpriteBase {
    public int es;
    public int et;
    public float eu;
    public float ev;
    public boolean ew;

    public void b(com.corrodinggames.rts.gameFramework.rendering.Texture e2) {
        this.T(e2.p);
        this.U(e2.q);
        this.ew = true;
    }

    public void a(com.corrodinggames.rts.gameFramework.rendering.Texture e2, int n) {
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

    protected Sprite(boolean bl) {
        super(bl);
    }


    public void a() {
        super.a();
    }
}
