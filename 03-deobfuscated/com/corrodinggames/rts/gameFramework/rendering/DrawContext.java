/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;
import com.corrodinggames.rts.gameFramework.GameTimerScheduler;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.CanvasRenderer;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.EffectConfig;
import com.corrodinggames.rts.gameFramework.rendering.ObjectPool;
import com.corrodinggames.rts.gameFramework.rendering.r;
import com.corrodinggames.rts.gameFramework.rendering.s;
import com.corrodinggames.rts.gameFramework.rendering.t;
import com.corrodinggames.rts.gameFramework.rendering.RenderBatch;
import java.util.concurrent.locks.Lock;

public final class DrawContext
extends RenderBatch {
    Renderer a = new CanvasRenderer(null);
    int b;
    final com.corrodinggames.rts.gameFramework.utility.CustomArrayList c = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
    final ObjectPool d = new ObjectPool(Paint.class);  // 02b m/o.java L29: final q d
    final ObjectPool e = new ObjectPool(Rect.class);
    final ObjectPool f = new ObjectPool(RectF.class);
    final ObjectPool g = new ObjectPool(Matrix.class);
    final ObjectPool h = new ObjectPool(r.class);
    final t i = new t(200);
    int j = 0;
    volatile boolean k = false;

    public DrawContext() {
        this.c.add(this.d);
        this.c.add(this.e);
        this.c.add(this.f);
        this.c.add(this.g);
        this.c.add(this.h);
    }

    public final s a(EffectConfig p2, Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.isEnabled(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        Object[] objectArray = s2.b;
        objectArray[0] = object;
        objectArray[1] = object2;
        objectArray[2] = object3;
        objectArray[3] = object4;
        objectArray[4] = object5;
        objectArray[5] = object6;
        objectArray[6] = object7;
        objectArray[7] = object8;
        ++this.j;
        return s2;
    }

    public final void a(EffectConfig p2, Object object, Object object2, Object object3, Object object4) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.isEnabled(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        Object[] objectArray = s2.b;
        objectArray[0] = object;
        objectArray[1] = object2;
        objectArray[2] = object3;
        objectArray[3] = object4;
        ++this.j;
    }

    public final void a(EffectConfig p2, Object object, Object object2) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.isEnabled(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        Object[] objectArray = s2.b;
        objectArray[0] = object;
        objectArray[1] = object2;
        ++this.j;
    }

    public final s a(EffectConfig p2) {
        t t2 = this.i;
        int n = this.j;
        if (n >= t2.a) {
            t2.isEnabled(new s(this));
        }
        s s2 = t2.b[n];
        s2.a = p2;
        ++this.j;
        return s2;
    }

    public void isEnabled6() {  // 02b m/o.java a() void (v19.132 补; RenderBatch 无此抽象, 非覆写)
        this.a(EffectConfig.W);
        --this.b;
        if (this.b < 0) {
            GlobalState.g("saveStackSize: " + this.b);
        }
    }

    public void a(boolean bl) {
        this.k = bl;
    }


    public boolean c() {
        return this.k;
    }

    @Override
    public void a(Rect rect) {
        rect = this.e.a(rect);
        this.a(EffectConfig.g, rect, null, null, null, null, null, null, null);
    }

    @Override
    public void a(RectF rectF) {
        rectF = this.f.a(rectF);
        this.a(EffectConfig.i, rectF, null, null, null, null, null, null, null);
    }

    @Override
    public void a(Texture e2, float f, float f2, Paint paint) {
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        s s2 = this.a(EffectConfig.m);
        s2.b[0] = e2;
        s2.b[1] = paint;
        s2.c = f;
        s2.d = f2;
    }

    @Override
    public void a(Texture e2, Rect rect, Rect rect2, Paint paint) {
        rect = this.e.a(rect);
        rect2 = this.e.a(rect2);
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.o, e2, rect, rect2, (Object)paint);
    }

    @Override
    public void a(Texture e2, Rect rect, RectF rectF, Paint paint) {
        rect = this.e.a(rect);
        rectF = this.f.a(rectF);
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.effectTypeP, e2, rect, rectF, (Object)paint);
    }

    @Override
    public void a(float f, float f2, float f3, Paint paint) {
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.t, Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), (Object)paint);
    }

    @Override
    public void a(int n, PorterDuff.Mode mode) {
        this.a(EffectConfig.u, (Object)n, mode);
    }

    @Override
    public void a(int n) {
        this.a(EffectConfig.v, n, null, null, null, null, null, null, null);
    }

    @Override
    public void a(float f, float f2, float f3, float f4, Paint paint) {
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        s s2 = this.a(EffectConfig.effectTypeW);
        s2.c = f;
        s2.d = f2;
        s2.e = f3;
        s2.f = f4;
        s2.b[0] = paint;
    }

    @Override
    public void a(float[] fArray, int n, int n2, Paint paint) {
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.G, fArray, n, n2, paint, null, null, null, null);
    }

    @Override
    public void a(Rect rect, Paint paint) {
        rect = this.e.a(rect);
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.M, rect, paint);
    }

    @Override
    public void a(RectF rectF, Paint paint) {
        rectF = this.f.a(rectF);
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.N, rectF, paint);
    }

    @Override
    public void a(String string, float f, float f2, Paint paint) {
        if (!(paint instanceof UniquePaint)) {
            paint = this.d.a(paint);
        }
        this.a(EffectConfig.R, string, Float.valueOf(f), Float.valueOf(f2), paint, null, null, null, null);
    }


    public void a() {
        this.a(EffectConfig.W);
        --this.b;
        if (this.b < 0) {
            GlobalState.isKeyJustPressed("saveStackSize: " + this.b);
        }
    }

    @Override
    public void a(float f2, float f3, float f4) {
        s s2 = this.a(EffectConfig.Z);
        s2.c = f2;
        s2.d = f3;
        s2.e = f4;
    }

    @Override
    public void b() {
        this.a(EffectConfig.aa);
        ++this.b;
        if (this.b <= 0) {
            GlobalState.isKeyJustPressed("saveStackSize (on save): " + this.b);
        }
    }

    @Override
    public void a(float f2, float f3) {
        s s2 = this.a(EffectConfig.af);
        s2.c = f2;
        s2.d = f3;
    }

    @Override
    public void a(float f2, float f3, float f4, float f5) {
        s s2 = this.a(EffectConfig.ag);
        s2.c = f2;
        s2.d = f3;
        s2.e = f4;
        s2.f = f5;
    }

    @Override
    public void a(Texture e2) {
        this.a(EffectConfig.ah, e2, null, null, null, null, null, null, null);
    }

    @Override
    public void b(float f2, float f3) {
        s s2 = this.a(EffectConfig.am);
        s2.c = f2;
        s2.d = f3;
    }

    @Override
    public void a(DrawCommand m2) {
        this.a(EffectConfig.an, this, m2);
    }

    @Override
    public void a(Bitmap bitmap) {
        this.a(EffectConfig.ap, bitmap, null);
    }

    @Override
    public void a(Lock lock) {
        this.a(EffectConfig.aq, lock, null);
    }

    @Override
    public void b(Lock lock) {
        this.a(EffectConfig.ar, lock, null);
    }

    @Override
    public boolean a(Shader ae2) {
        this.a(EffectConfig.as, ae2, null);
        return true;
    }
}
