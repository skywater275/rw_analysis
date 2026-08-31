/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;

public class NullSpriteBatchBackend
implements TextureManagerInterface {
    Texture a;
    Texture b;


    public TextureManagerInterface a(Texture e2) {
        return this.b(e2);
    }


    public TextureManagerInterface b(Texture e2) {
        return new NullSpriteBatchBackend();
    }


    public boolean a() {
        return false;
    }


    public void a(Context context) {
    }


    public void b() {
        this.a = new Texture();
        this.b = new Texture();
    }

    @Override
    public Renderer d() {
        return null;
    }


    public void a(Renderer l2) {
    }


    public void a(OpenGLRenderer a2) {
    }


    public Texture loadImageFromResource(int n) {
        return this.a(n, true);
    }

    public Texture a(int n) {  // v19.117: 02b m/y.java L35 原名
        return this.loadImageFromResource(n);
    }


    public Texture a(int n, boolean bl) {
        Texture e2 = new Texture();
        return e2;
    }


    public Texture a(InputStream inputStream, boolean bl) {
        Texture e2 = new Texture();
        return e2;
    }


    public Texture a(int n, int n2, boolean bl) {
        return this.b(n, n2, bl);
    }


    public Texture b(int n, int n2, boolean bl) {
        Texture e2 = new Texture();
        return e2;
    }

    @Override
    public void e() {
    }


    public void a(Texture e2, float f, float f2, float f3, Paint paint) {  // 02b m/y.a(e,float,float,float,Paint) (D 为误名 v19.133f8) {
    }


    public void A(Texture e2, Rect rect, float f, float f2, float f3, Paint paint) {
    }


    public void loadImageFromResource(Texture e2, Rect rect, Rect rect2, Paint paint) {
    }


    public void a(Texture e2, Rect rect, Rect rect2, Paint paint) {  // 02b m/y.java L51 — 简化委托 b
        this.b(e2, rect, rect2, paint);
    }

    public void b(Texture e2, Rect rect, Rect rect2, Paint paint) {
        this.loadImageFromResource(e2, rect, rect2, paint);
    }


    public void clearScreen(Rect rect, Paint paint) {
        this.b(rect, paint);
    }


    public void a(boolean bl) {
    }


    public void f() {
    }


    public void loadImageFromResource(Texture e2, Rect rect, RectF rectF, Paint paint) {
    }


    public void clearScreen(Texture e2, float f, float f2, Paint paint) {
    }


    public void loadImageFromResource(Texture e2, float f, float f2, Paint paint, float f3, float f4) {
    }

    public void a(Texture e2, float f, float f2, Paint paint, float f3, float f4) {  // 02b m/y.java L57 (v19.133d)
    }


    public void a(Texture e2, float f, float f2, Paint paint) {  // 02b m/z.java L96 空实现
    }


    public void a(Texture e2, Rect rect, RectF rectF, Paint paint) {  // 02b m/z.java L94 空实现
    }


    public void b(Texture e2, float f, float f2, Paint paint) {
    }


    public void a(Texture e2, Rect rect, Paint paint) {
    }


    public void a(Texture e2, Rect rect, Paint paint, int n, int n2, int n3, int n4) {
    }


    public void a(Texture e2, RectF rectF, Paint paint, float f, float f2, int n, int n2) {
    }


    public void clearScreen(int n) {
    }


    public void a(int n, PorterDuff.Mode mode) {
    }


    public void a(String string, float f, float f2, Paint paint, Paint paint2, float f3) {
    }


    public void a(String string, float f, float f2, Paint paint) {
    }


    public void b(Rect rect, Paint paint) {
    }


    public void a(RectF rectF, Paint paint) {
    }

    @Override
    public void g() {
    }


    public void h() {
    }


    public void c(Rect rect, Paint paint) {
    }


    public void a(Rect rect) {
    }


    public void a(RectF rectF) {
    }


    public void a(float f, float f2, float f3, Paint paint) {
    }


    public void b(float f, float f2, float f3, Paint paint) {
        this.a(f, f2, f3, paint);
    }


    public void a(float[] fArray, int n, int n2, Paint paint) {
    }

    @Override
    public void i() {
    }


    public void j() {
    }


    public void k() {
        this.i();
    }


    public void l() {
        this.j();
    }


    public void a(float f, float f2, float f3) {
    }


    public void D(float f, float f2) {
    }


    public void a(float f, float f2, float f3, float f4) {
    }


    public void b(float f, float f2) {
    }


    public void a(DrawCommand m2) {
        m2.a(this);
    }


    public void a(float f, float f2, float f3, float f4, Paint paint) {
    }

    @Override
    public int m() {
        return 0;
    }

    @Override
    public int n() {
        return 0;
    }


    public void a(int n, int n2) {
    }

    @Override
    public void o() {
    }


    public void a(Paint paint) {
    }


    public void a(Shader ae2) {
    }


    public void p() {
    }


    public void q() {
    }


    public int a(String string, Paint paint) {
        return 1;
    }


    public int b(String string, Paint paint) {
        return 1;
    }

    @Override
    public Texture r() {
        return this.b;
    }


    public void a(Texture e2, File file) {
        throw new RuntimeException("writeImageToFile not yet supported");
    }


    public void a(Lock lock) {
    }


    public void b(Lock lock) {
    }

    @Override
    public float s() {
        return 1.0f;
    }

    public void a(float f2, float f3) {  // 02b m/y.java L113 (v19.133d)
    }

    public void b(int n) {  // 02b m/y.java b(int) (v19.133d)
    }
}
