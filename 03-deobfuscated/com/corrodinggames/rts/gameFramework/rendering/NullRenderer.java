/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import java.util.concurrent.locks.Lock;

public class NullRenderer
implements Renderer {
    boolean a = false;

    @Override
    public void a(Rect rect) {
    }

    @Override
    public void a(RectF rectF) {
    }

    @Override
    public void a(Texture e2, float f, float f2, Paint paint) {
    }

    @Override
    public void a(Texture e2, Rect rect, Rect rect2, Paint paint) {
    }

    @Override
    public void a(Texture e2, Rect rect, RectF rectF, Paint paint) {
    }

    @Override
    public void a(float f, float f2, float f3, Paint paint) {
    }

    @Override
    public void a(int n2, PorterDuff.Mode mode) {
    }

    @Override
    public void a(int n2) {
    }

    @Override
    public void a(float f, float f2, float f3, float f4, Paint paint) {
    }

    @Override
    public void a(float[] fArray, int n2, int n3, Paint paint) {
    }

    @Override
    public void a(Rect rect, Paint paint) {
    }

    @Override
    public void a(RectF rectF, Paint paint) {
    }

    @Override
    public void a(String string, float f, float f2, Paint paint) {
    }


    public void a() {
    }

    @Override
    public void a(float f, float f2, float f3) {
    }

    @Override
    public void b() {
    }

    @Override
    public void a(float f, float f2) {
    }

    @Override
    public void a(float f, float f2, float f3, float f4) {
    }

    @Override
    public void a(Texture e2) {
    }

    @Override
    public void b(float f, float f2) {
    }

    @Override
    public void a(boolean bl) {
        this.a = bl;
    }


    public boolean c() {
        return this.a;
    }

    @Override
    public void a(DrawCommand m2) {
    }

    @Override
    public void a(Bitmap bitmap) {
    }

    @Override
    public void a(Lock lock) {
    }

    @Override
    public void b(Lock lock) {
    }

    @Override
    public boolean a(Shader ae2) {
        return false;
    }
}
