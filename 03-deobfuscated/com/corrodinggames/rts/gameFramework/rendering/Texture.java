/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Bitmap;
import com.corrodinggames.rts.game.GameMode;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Shader;

public class Texture {
    public Texture[] a;
    public Texture[] b;
    public Texture[] c;
    private static int x;
    public int d = x++;
    public int e = 1;
    public int f;
    public String name;
    public Integer h;
    Shader i;
    public int[] j;
    protected Bitmap k;
    public boolean l = true;
    public boolean m;
    public boolean n;
    public boolean o = false;
    public int p;
    public int q;
    public int r;
    public int s;
    public float t;
    public float u;
    public boolean v;
    boolean keepInGpuMemory = false;

    public Texture[] setRenderTarget(GameMode o2) {
        if (o2 == com.corrodinggames.rts.game.GameMode.a) {
            return this.a;
        }
        if (o2 == com.corrodinggames.rts.game.GameMode.b) {
            return this.b;
        }
        if (o2 == com.corrodinggames.rts.game.GameMode.d) {
            return this.c;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.b("getTeamImageCache coloringMode:" + (Object)((Object)o2));
        return this.a;
    }

    public void setRenderTarget(GameMode o2, Texture[] eArray) {
        if (o2 == com.corrodinggames.rts.game.GameMode.a) {
            this.a = eArray;
            return;
        }
        if (o2 == com.corrodinggames.rts.game.GameMode.b) {
            this.b = eArray;
            return;
        }
        if (o2 == com.corrodinggames.rts.game.GameMode.d) {
            this.c = eArray;
            return;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.b("setTeamImageCache coloringMode:" + (Object)((Object)o2));
        this.a = eArray;
    }

    public void setRenderTarget(String string) {
        this.name = string;
    }

    public String setRenderTarget() {
        return this.name;
    }

    public Bitmap setKeepInGpuMemory() {
        return this.k;
    }

    public Bitmap b() {  // 02b m/e.java L73: 获取位图
        return this.k;
    }

    public Texture c() {
        return this;
    }

    public void setRenderTarget(boolean bl) {
        this.o = bl;
        this.e();
    }

    public void setKeepInGpuMemory(boolean bl) {
        this.keepInGpuMemory = bl;
    }

    public void a(boolean bl) {  // 02b m/e.java L81: 透明标志
        this.o = bl;
        this.e();
    }

    protected void e() {
    }



    public boolean d() {  // 02b m/e.java L90: return this.w (keepInGpuMemory)
        return this.keepInGpuMemory;
    }
    public boolean f() {
        return this.m;
    }

    public void setRenderTarget(Bitmap bitmap) {
        this.k = bitmap;
        this.p = this.k.b();
        this.q = this.k.c();
        this.g();
    }

    public void g() {
        this.r = this.p / 2;
        this.s = this.q / 2;
        this.t = (float)this.p / 2.0f;
        this.u = (float)this.q / 2.0f;
    }

    public void setRenderTarget(Texture e2) {
        e2.o = this.o;
        e2.p = this.p;
        e2.q = this.q;
        e2.r = this.r;
        e2.s = this.s;
        e2.t = this.t;
        e2.u = this.u;
    }

    public Texture setRenderTarget(int n2, int n3, boolean bl) {
        Texture e2 = new Texture();
        e2.o = this.o;
        if (this.k != null) {
            Bitmap k = Bitmap.a(n2, n3, this.k.d());
            e2.setRenderTarget(k);
            if (bl) {
                for (int i = 0; i < k.b(); ++i) {
                    for (int j = 0; j < k.c(); ++j) {
                        k.a(i, j, this.k.a(i, j));
                    }
                }
            }
        }
        return e2;
    }

    public boolean k() {
        return true;
    }

    public int setRenderTarget(int n2, int n3) {
        if (this.j != null) {
            return this.j[n2 + n3 * this.p];
        }
        return this.k.a(n2, n3);
    }

    public void setRenderTarget(int n2, int n3, int n4) {
        if (this.j != null) {
            this.j[n2 + n3 * this.p] = n4;
            return;
        }
        this.k.a(n2, n3, n4);
    }

    public void n() {
    }

    public void i() {  // 02b m/e.java L157-162: 缓冲懒初始化
        if (this.j == null) {
            this.j();
        }
    }

    public void j() {  // v19.113n: 02b m/e.java:164 位图缓冲初始化
        if (this.k != null || !GlobalState.aU || GlobalState.aX) {
            if (this.j == null) {
                this.j = new int[this.p * this.q];
            }
        }
    }

    public int l() {  // v19.113n: 02b m/e.java:190 return this.q (高度)
        return this.q;
    }

    public int m() {  // v19.113n: 02b m/e.java:194 return this.p (宽度)
        return this.p;
    }

    public void o() {
        if (this.k != null) {
            this.k = null;
        }
        if (this.keepInGpuMemory) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("remove with keepInGPUMemory=true");
        }
    }

    public void v() {
        this.a = null;
        this.b = null;
        this.c = null;
        ++this.e;
    }

    public void p() {
        if (this.k != null || !com.corrodinggames.rts.gameFramework.GlobalState.aU || com.corrodinggames.rts.gameFramework.GlobalState.aW) {
            if (this.j != null) {
                this.k.b(this.j, 0, this.p, 0, 0, this.p, this.q);
                this.j = null;
            }
            ++this.e;
        }
    }

    public void q() {
    }

    public void r() {
        this.j = null;
    }

    public void s() {
        this.r();
    }

    public void t() {
    }

    public void a(Bitmap bitmap) {  // 02b m/e.java L100-105
        this.k = bitmap;
        this.p = this.k.b();
        this.q = this.k.c();
        this.g();
    }

    public Texture a(int n2, int n3, boolean bl) {  // 02b m/e.java L139-154
        Texture texture = new Texture();
        texture.o = this.o;
        if (this.k != null) {
            Bitmap bitmap = Bitmap.a(n2, n3, this.k.d());
            texture.a(bitmap);
            if (bl) {
                for (int i2 = 0; i2 < bitmap.b(); ++i2) {
                    for (int i3 = 0; i3 < bitmap.c(); ++i3) {
                        bitmap.a(i2, i3, this.k.a(i2, i3));
                    }
                }
            }
        }
        return texture;
    }

    public int a(int n2, int n3) {  // 02b m/e.java L178-180
        return this.j != null ? this.j[n2 + n3 * this.p] : this.k.a(n2, n3);
    }

    public String a() {  // 02b m/e.java L69-71
        return this.name;
    }

    public void a(int n2, int n3, int n4) {
        // 02 m/e.java:185 铁证 — 像素写入 (j=像素数组 k=Bitmap)
        if (this.j != null) {
            this.j[n2 + n3 * this.p] = n4;
            return;
        }
        this.k.a(n2, n3, n4);
    }

    public int u() {
        return this.p * this.q * 8;
    }

    public void clearTeamVariants() {
        this.a = null;
        this.b = null;
        this.c = null;
        ++this.e;
    }

    public void w() {
    }

    public void x() {
    }

    public void y() {
    }

    public void z() {
    }

    public boolean A() {
        return false;
    }

    public Shader B() {
        return this.i;
    }

    public void setRenderTarget(Shader ae2) {
        this.i = ae2;
    }

    public Texture h() {
        Texture texture = new Texture();
        texture.o = this.o;
        if (this.k != null) {
            Bitmap bitmap = this.k.a(this.k.d(), true);
            if (bitmap == null) {
                throw new OutOfMemoryError("Failed to copy bitmap: " + this.k.d());
            }
            texture.setRenderTarget(bitmap);
        }
        return texture;
    }

    public /* synthetic */ Object clone() {
        return this.h();
    }

    // v19.115w 补缺: javap m.e a(String)/b(boolean) 铁证 (MapLayerRenderer 位图标签/更新) — 简化 TODO
    public void a(String var1) {
    }

    // v19.115w 补缺: javap m.e a(String)/b(boolean) 铁证 (MapLayerRenderer 位图标签/更新) — 简化 TODO
    public void b(boolean var1) {
    }
}
