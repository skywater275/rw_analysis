/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.h;

public class i
extends ae {
    int a = -99;
    boolean b;

    public i(String string, boolean bl) {
        super(string);
    }

    @Override
    public boolean a() {
        return this.b;
    }

    @Override
    public boolean b() {
        boolean bl = false;
        int n = -16711936;
        if (n != this.a) {
            this.a("teamColor", n);
            bl = true;
            this.a = n;
        }
        return bl;
    }

    @Override
    public boolean a(Paint paint, e e2) {
        boolean bl = false;
        if (e2 instanceof h) {
            h h2 = (h)e2;
            if (h2.D != this.a) {
                this.a("teamColor", h2.D);
                bl = true;
                this.a = h2.D;
            }
        }
        super.a(paint, e2);
        return bl;
    }

    @Override
    public void c() {
        super.c();
    }
}
