/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.opengl.ImageData
 */
package com.corrodinggames.rts.java;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.java.SlickTexture;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.ImageData;

public class TextureProxy
extends SlickTexture {
    SlickTexture x;

    public TextureProxy(SlickTexture s2) {
        this.x = s2;
    }

    @Override
    public Image C() {
        return this.x.C();
    }


    public Bitmap b() {
        return this.x.setKeepInGpuMemory();
    }


    public Texture c() {
        return this.x.c();
    }

    @Override
    public String a() {
        return this.x.a();
    }


    public int l() {
        return this.x.l();
    }


    public int m() {
        return this.x.m();
    }

    @Override
    public int u() {
        return this.x.u();
    }

    @Override
    public void w() {
    }


    public Texture a(int n, int n2, boolean bl) {
        return this;
    }

    @Override
    public int a(int n, int n2) {
        return this.x.a(n, n2);
    }

    @Override
    public void p() {
    }

    @Override
    public void r() {
    }

    @Override
    public void n() {
    }


    public Texture h() {
        return this;
    }

    @Override
    public void t() {
    }

    @Override
    public long c(boolean bl) {
        return this.x.c(bl);
    }


    public void a(boolean bl) {
    }


    public void a(Bitmap bitmap) {
    }

    @Override
    public void g() {
    }


    public void a(Texture e2) {
        this.x.setRenderTarget(e2);
    }

    @Override
    public void D() {
    }

    @Override
    public void a(Image image, String string) {
    }

    @Override
    public void a(ImageData imageData, String string, boolean bl) {
    }


    public void v() {
    }

    @Override
    public void i() {
    }

    @Override
    public void j() {
    }

    @Override
    public void a(int n, int n2, int n3) {
    }

    @Override
    public void o() {
    }

    @Override
    public void E() {
    }

    @Override
    public void F() {
    }

    @Override
    public void G() {
        this.x.G();
    }

    public String toString() {
        return this.x.toString();
    }

    @Override
    public boolean A() {
        return true;
    }

    @Override
    public /* synthetic */ Object clone() {
        return this.h();
    }
}
