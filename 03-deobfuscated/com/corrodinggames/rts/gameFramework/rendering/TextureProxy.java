/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class TextureProxy
extends Texture {
    Texture x;

    public TextureProxy(Texture e2) {
        this.x = e2;
        this.k = e2.k;
    }


    public String a() {
        return this.x.setRenderTarget();
    }


    public Bitmap b() {
        return this.x.setKeepInGpuMemory();
    }

    @Override
    public Texture c() {
        return this.x.c();
    }


    public void a(boolean bl) {
    }


    public void a(Bitmap bitmap) {
    }

    @Override
    public void g() {
        this.x.g();
    }


    public void a(Texture e2) {
        this.x.setRenderTarget(e2);
    }


    public Texture h() {
        return this;
    }


    public Texture a(int n, int n2, boolean bl) {
        return this;
    }


    public void i() {
    }


    public void j() {
    }


    public int a(int n, int n2) {
        return this.x.setRenderTarget(n, n2);
    }


    public void a(int n, int n2, int n3) {
    }


    public int l() {
        return this.x.l();
    }


    public int m() {
        return this.x.m();
    }

    @Override
    public void n() {
    }

    @Override
    public void o() {
    }


    public void p() {
    }


    public void r() {
    }

    @Override
    public void t() {
    }

    @Override
    public int u() {
        return this.x.u();
    }


    public void v() {
    }

    @Override
    public void w() {
    }

    public String toString() {
        return "MutableBitmapOrTexture(" + this.x.toString() + ")";
    }

    @Override
    public Shader B() {
        return this.x.i;
    }


    public void a(Shader ae2) {
    }

    @Override
    public /* synthetic */ Object clone() {
        return this.h();
    }
}
