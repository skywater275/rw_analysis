/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import com.corrodinggames.rts.game.o;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;

public class e {
    public e[] a;
    public e[] b;
    public e[] c;
    private static int x;
    public int d = x++;
    public int e = 1;
    public int f;
    public String g;
    public Integer h;
    ae i;
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
    boolean w = false;

    public e[] a(o o2) {
        if (o2 == com.corrodinggames.rts.game.o.a) {
            return this.a;
        }
        if (o2 == com.corrodinggames.rts.game.o.b) {
            return this.b;
        }
        if (o2 == com.corrodinggames.rts.game.o.d) {
            return this.c;
        }
        com.corrodinggames.rts.gameFramework.l.b("getTeamImageCache coloringMode:" + (Object)((Object)o2));
        return this.a;
    }

    public void a(o o2, e[] eArray) {
        if (o2 == com.corrodinggames.rts.game.o.a) {
            this.a = eArray;
            return;
        }
        if (o2 == com.corrodinggames.rts.game.o.b) {
            this.b = eArray;
            return;
        }
        if (o2 == com.corrodinggames.rts.game.o.d) {
            this.c = eArray;
            return;
        }
        com.corrodinggames.rts.gameFramework.l.b("setTeamImageCache coloringMode:" + (Object)((Object)o2));
        this.a = eArray;
    }

    public void a(String string) {
        this.g = string;
    }

    public String a() {
        return this.g;
    }

    public Bitmap b() {
        return this.k;
    }

    public e c() {
        return this;
    }

    public void a(boolean bl) {
        this.o = bl;
        this.e();
    }

    public void b(boolean bl) {
        this.w = bl;
    }

    public boolean d() {
        return this.w;
    }

    protected void e() {
    }

    public boolean f() {
        return this.m;
    }

    public void a(Bitmap bitmap) {
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

    public void a(e e2) {
        e2.o = this.o;
        e2.p = this.p;
        e2.q = this.q;
        e2.r = this.r;
        e2.s = this.s;
        e2.t = this.t;
        e2.u = this.u;
    }

    public e h() {
        e e2 = new e();
        e2.o = this.o;
        if (this.k != null) {
            Bitmap bitmap = this.k.a(this.k.d(), true);
            if (bitmap == null) {
                throw new OutOfMemoryError("Failed to copy bitmap: " + (Object)((Object)this.k.d()));
            }
            e2.a(bitmap);
        }
        return e2;
    }

    public e a(int n2, int n3, boolean bl) {
        e e2 = new e();
        e2.o = this.o;
        if (this.k != null) {
            Bitmap bitmap = Bitmap.a(n2, n3, this.k.d());
            e2.a(bitmap);
            if (bl) {
                for (int i = 0; i < bitmap.b(); ++i) {
                    for (int j = 0; j < bitmap.c(); ++j) {
                        bitmap.a(i, j, this.k.a(i, j));
                    }
                }
            }
        }
        return e2;
    }

    public void i() {
        if (this.j == null) {
            this.j();
        }
    }

    public void j() {
        if (this.k == null && com.corrodinggames.rts.gameFramework.l.aU && !com.corrodinggames.rts.gameFramework.l.aX) {
            return;
        }
        if (this.j == null) {
            this.j = new int[this.p * this.q];
        }
        this.k.a(this.j, 0, this.p, 0, 0, this.p, this.q);
    }

    public boolean k() {
        return true;
    }

    public int a(int n2, int n3) {
        if (this.j != null) {
            return this.j[n2 + n3 * this.p];
        }
        return this.k.a(n2, n3);
    }

    public void a(int n2, int n3, int n4) {
        if (this.j != null) {
            this.j[n2 + n3 * this.p] = n4;
            return;
        }
        this.k.a(n2, n3, n4);
    }

    public int l() {
        return this.q;
    }

    public int m() {
        return this.p;
    }

    public void n() {
    }

    public void o() {
        if (this.k != null) {
            this.k = null;
        }
        if (this.w) {
            com.corrodinggames.rts.gameFramework.l.b("remove with keepInGPUMemory=true");
        }
    }

    public void p() {
        if (this.k == null && com.corrodinggames.rts.gameFramework.l.aU && !com.corrodinggames.rts.gameFramework.l.aW) {
            return;
        }
        if (this.j != null) {
            this.k.b(this.j, 0, this.p, 0, 0, this.p, this.q);
            this.j = null;
        }
        ++this.e;
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

    public int u() {
        return this.p * this.q * 8;
    }

    public void v() {
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

    public ae B() {
        return this.i;
    }

    public void a(ae ae2) {
        this.i = ae2;
    }

    public /* synthetic */ Object clone() {
        return this.h();
    }
}
