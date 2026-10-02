/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.opengl.ab;
import com.corrodinggames.rts.gameFramework.opengl.k;
import java.util.WeakHashMap;

public abstract class GLObject
implements ab {
    protected int a = -1;
    protected int b;
    protected int c = -1;
    protected int d = -1;
    protected int e;
    protected int f;
    protected float g;
    protected float h;
    private boolean l;
    private boolean m;
    public int i;
    public boolean j;
    protected k renderer = null;
    private static WeakHashMap n = new WeakHashMap();
    private static ThreadLocal o = new ThreadLocal();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected GLObject(k k2, int n, int n2) {
        this.a(k2);
        this.a = n;
        this.b = n2;
        WeakHashMap weakHashMap = GLObject.n;
        synchronized (weakHashMap) {
            GLObject.n.put(this, null);
        }
    }

    protected GLObject() {
        this(null, 0, 0);
    }

    public int reset4() {
        return this.c;
    }

    public int getint() {
        return this.e;
    }

    public int getint2() {
        return this.f;
    }

    public boolean isEnabled2() {
        return this.l;
    }

    // === 02b b/b.java 方法对齐 (v19.133c: 渲染层战役) ===
    public int a() {  // 02b b/b.java L87: 纹理 id
        return this.a;
    }

    public int b() {  // 02b b/b.java L91: 宽
        return this.c;
    }

    public int c() {  // 02b b/b.java L95: 高
        return this.d;
    }

    public int d() {  // 02b b/b.java L99: 2 幂宽
        return this.e;
    }

    public int e() {  // 02b b/b.java L103: 2 幂高
        return this.f;
    }

    public boolean f() {  // 02b b/b.java L107: 启用标志
        return this.l;
    }

    public abstract void b(k var1);  // 02b b/b.java L115 (public: 03 跨包调用, 02b 同包 protected)

    protected abstract boolean c(k var1);  // 02b b/b.java L117

    protected abstract int g();  // 02b b/b.java L119

    public abstract void b(int var1);  // 02b b/b.java L121

    public abstract int h();  // 02b b/b.java L123

    public void a(RectF var1) {  // 02b b/b.java L151: 纹理坐标归一化
        int var2 = this.b();
        int var3 = this.c();
        int var4 = this.d();
        int var5 = this.e();
        var1.a /= (float) var4;
        var1.c /= (float) var4;
        var1.b /= (float) var5;
        var1.d /= (float) var5;
        float var6 = (float) var2 / (float) var4;
        if (var1.c > var6) {
            var1.c = var6;
        }
        float var7 = (float) var3 / (float) var5;
        if (var1.d > var7) {
            var1.d = var7;
        }
    }

    public void a(RectF var1, RectF var2) {  // 02b b/b.java L172
        float var4 = (float) this.c() / (float) this.e();
        float var5 = (float) this.b() / (float) this.d();
        if (var1.c > var5) {
            var2.c = var2.a + var2.b() * (var5 - var1.a) / var1.b();
        }
        if (var1.d > var4) {
            var2.d = var2.b + var2.c() * (var4 - var1.b) / var1.c();
        }
    }

    protected void a(k var1) {  // 02b b/b.java L55: 设置 renderer
        this.renderer = var1;
    }

    public static int a(int n2) {  // 02b b/b.java L87: 取整到 2 的幂
        if (n2 > 0 && n2 <= 1073741824) {
            --n2;
            n2 |= n2 >> 16;
            n2 |= n2 >> 8;
            n2 |= n2 >> 4;
            n2 |= n2 >> 2;
            n2 |= n2 >> 1;
            return n2 + 1;
        }
        throw new IllegalArgumentException("n is invalid: " + n2);
    }

    public void a(int n2, int n3) {  // 02b b/b.java L50: 设置纹理尺寸
        this.c = n2;
        this.d = n3;
        this.e = n2 > 0 ? a(n2) : 0;
        this.f = n3 > 0 ? a(n3) : 0;
        if (this.e == 0) {
            this.g = 0.0f;
        } else {
            this.g = 1.0f / (float) this.e;
        }
        if (this.f == 0) {
            this.h = 0.0f;
        } else {
            this.h = 1.0f / (float) this.f;
        }
    }

    protected void a(boolean bl) {  // 02b b/b.java L111: 设置启用标志
        this.l = bl;
    }


    public boolean i() {  // 02b b/b.java L107: 已绑定判断
        return this.b == 1;
    }

    public void j() {  // 02b b/b.java L88: j() = m=true + k()
        this.m = true;
        this.k();
    }

    public void pushTransform() {  // 02b b/b.java L88: j() = m=true + k() 释放
        this.m = true;
        this.k();
    }

    private void k() {
        k k2 = this.renderer;
        if (k2 != null && this.a != -1) {
            k2.a(this);
            this.a = -1;
        }
        this.b = 0;
        this.a((k)null);
    }

    protected void finalize() {
        o.set(GLObject.class);
        this.pushTransform();
        o.set(null);
    }
}
