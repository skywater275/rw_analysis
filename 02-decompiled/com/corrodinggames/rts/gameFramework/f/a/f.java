/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Color;
import com.corrodinggames.rts.gameFramework.f.a.b;
import com.corrodinggames.rts.gameFramework.f.a.f$1;
import com.corrodinggames.rts.gameFramework.f.a.g;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.a.j;
import com.corrodinggames.rts.gameFramework.f.a.k;
import com.corrodinggames.rts.gameFramework.f.a.m;
import com.corrodinggames.rts.gameFramework.f.a.n;
import com.corrodinggames.rts.gameFramework.h.a;
import com.corrodinggames.rts.gameFramework.m.y;

public class f
extends n {
    g a;

    public static f a(String string, boolean bl) {
        f f2 = new f();
        f2.b = com.corrodinggames.rts.gameFramework.f.a.h.n;
        f2.i = 200.0f;
        f2.j = 200.0f;
        j j2 = new j();
        j2.a(string);
        j2.e(5.0f);
        j2.f(5.0f);
        j2.a(-1);
        f2.a(j2);
        f2.a = new g(com.corrodinggames.rts.gameFramework.f.a.m.c);
        f2.a(f2.a);
        if (bl) {
            b b2 = f2.b(com.corrodinggames.rts.gameFramework.h.a.a("menus.common.cancel", new Object[0]));
            b2.a(new f$1(f2));
        }
        return f2;
    }

    public b a(String string) {
        b b2 = new b();
        b2.a(string);
        b2.e(5.0f);
        b2.f(5.0f);
        b2.a(Color.a(255, 30, 240, 30));
        return b2;
    }

    public b b(String string) {
        return this.a(string, null);
    }

    public b a(String string, k k2) {
        b b2 = this.a(string);
        b2.a(k2);
        this.a.a(b2);
        return b2;
    }

    public void u_() {
        if (!this.s) {
            return;
        }
        this.b();
    }

    @Override
    public void b() {
        super.b();
        y y2 = this.d();
        this.i = this.z;
        this.j = this.y;
        this.i += this.m + this.n;
        this.j += this.k + this.l;
    }
}
