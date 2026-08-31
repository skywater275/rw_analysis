/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.appFramework.f;
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.appFramework.m;
import com.corrodinggames.rts.gameFramework.m.a;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.n;
import com.corrodinggames.rts.java.h;

public class d
implements f {
    public int a;
    public int b;
    h c;
    m d;
    public Object e = new Object();
    public Object f = new Object();
    n g = new n();

    public d() {
        this.c = new h();
    }

    @Override
    public void j() {
    }

    @Override
    public void a() {
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public a d() {
        return null;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean f() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2 != null && l2.bQ.slick2dFullScreen;
    }

    @Override
    public Object g() {
        return null;
    }

    public int o() {
        return this.a;
    }

    public int p() {
        return this.b;
    }

    @Override
    public void h() {
    }

    @Override
    public g i() {
        return this.c;
    }

    @Override
    public m k() {
        return this.d;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(float f2, int n2) {
        Object object = this.f;
        synchronized (object) {
            this.f.notifyAll();
        }
    }

    @Override
    public void b(float f2, int n2) {
    }

    @Override
    public void l() {
    }

    @Override
    public l b(boolean bl) {
        return this.g;
    }

    @Override
    public void a(l l2, boolean bl) {
    }

    @Override
    public void m() {
    }

    @Override
    public boolean n() {
        return true;
    }

    @Override
    public void a(boolean bl) {
    }
}
