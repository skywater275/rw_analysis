/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;

public class ad
extends e {
    e x;

    public ad(e e2) {
        this.x = e2;
        this.k = e2.k;
    }

    @Override
    public String a() {
        return this.x.a();
    }

    @Override
    public Bitmap b() {
        return this.x.b();
    }

    @Override
    public e c() {
        return this.x.c();
    }

    @Override
    public void a(boolean bl) {
    }

    @Override
    public void a(Bitmap bitmap) {
    }

    @Override
    public void g() {
        this.x.g();
    }

    @Override
    public void a(e e2) {
        this.x.a(e2);
    }

    @Override
    public e h() {
        return this;
    }

    @Override
    public e a(int n, int n2, boolean bl) {
        return this;
    }

    @Override
    public void i() {
    }

    @Override
    public void j() {
    }

    @Override
    public int a(int n, int n2) {
        return this.x.a(n, n2);
    }

    @Override
    public void a(int n, int n2, int n3) {
    }

    @Override
    public int l() {
        return this.x.l();
    }

    @Override
    public int m() {
        return this.x.m();
    }

    @Override
    public void n() {
    }

    @Override
    public void o() {
    }

    @Override
    public void p() {
    }

    @Override
    public void r() {
    }

    @Override
    public void t() {
    }

    @Override
    public int u() {
        return this.x.u();
    }

    @Override
    public void v() {
    }

    @Override
    public void w() {
    }

    public String toString() {
        return "MutableBitmapOrTexture(" + this.x.toString() + ")";
    }

    @Override
    public ae B() {
        return this.x.i;
    }

    @Override
    public void a(ae ae2) {
    }

    @Override
    public /* synthetic */ Object clone() {
        return this.h();
    }
}
