/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.rendering.FontRenderer;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import java.util.concurrent.locks.Lock;

public class CanvasRenderer
implements Renderer {  // 方法名按 02b m/l 接口对齐 (v19.133c)
    public Canvas a;
    boolean b = false;

    public CanvasRenderer(Canvas canvas) {
        this.a = canvas;
    }


    public void a(Rect rect) {
        this.a.a(rect);
    }


    public void a(RectF rectF) {
        this.a.a(rectF);
    }


    public void a(Texture e2, float f, float f2, Paint paint) {
        this.a.a(e2.b(), f, f2, paint);
    }


    public void a(Texture e2, Rect rect, Rect rect2, Paint paint) {
        this.a.a(e2.b(), rect, rect2, paint);
    }


    public void a(Texture e2, Rect rect, RectF rectF, Paint paint) {
        this.a.a(e2.b(), rect, rectF, paint);
    }


    public void a(float f, float f2, float f3, Paint paint) {
        this.a.a(f, f2, f3, paint);
    }


    public void a(int n, PorterDuff.Mode mode) {
        this.a.a(n, mode);
    }


    public void a(int n) {
        this.a.a(n);
    }


    public void a(float f, float f2, float f3, float f4, Paint paint) {
        this.a.a(f, f2, f3, f4, paint);
    }


    public void a(float[] fArray, int n, int n2, Paint paint) {
        this.a.a(fArray, n, n2, paint);
    }


    public void a(Rect rect, Paint paint) {
        this.a.a(rect, paint);
    }


    public void a(RectF rectF, Paint paint) {
        this.a.a(rectF, paint);
    }


    public void a(String string, float f, float f2, Paint paint) {
        this.a.a(string, f, f2, paint);
    }

    public boolean equals(Object object) {
        return this.a.equals(object);
    }

    public int hashCode() {
        return this.a.hashCode();
    }


    public void a() {
        this.a.b();
    }


    public void a(float f, float f2, float f3) {
        this.a.a(f, f2, f3);
    }

    @Override
    public void b() {
        this.a.a();
    }


    public void a(float f, float f2, float f3, float f4) {
        this.a.a(f, f2, f3, f4);
    }


    public void a(float f, float f2) {
        this.a.b(f, f2);
    }


    public void a(Texture e2) {
        this.a.a(e2.b());
    }

    public String toString() {
        return this.a.toString();
    }

    @Override
    public void b(float f, float f2) {
        this.a.a(f, f2);
    }


    public void a(boolean bl) {
        this.b = bl;
    }


    public boolean c() {
        return this.b;
    }


    public void a(DrawCommand m2) {
        m2.a(com.corrodinggames.rts.gameFramework.GlobalState.B().bO);
    }


    public void a(Bitmap bitmap) {
    }


    public void a(Lock lock) {
    }

    @Override
    public void b(Lock lock) {
    }


    public boolean a(Shader ae2) {
        return false;
    }
}
