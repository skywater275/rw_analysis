/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.b;

import com.corrodinggames.rts.java.Main;

public class a
extends com.corrodinggames.librocket.a {
    public Main f;
    boolean g = false;

    public static synchronized a p() {
        if (a != null) {
            throw new RuntimeException("CommonGuiEngine already exists");
        }
        a a2 = new a();
        a = a2;
        return a2;
    }

    @Override
    public void g() {
        this.f.i();
    }

    @Override
    public void h() {
        this.f.u = true;
    }

    @Override
    public int i() {
        return this.f.j.e();
    }

    @Override
    public void d(boolean bl) {
        this.f.a(bl);
    }
}
