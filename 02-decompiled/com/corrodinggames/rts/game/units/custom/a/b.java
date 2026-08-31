/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a;

import com.corrodinggames.rts.game.units.a.a;
import com.corrodinggames.rts.game.units.am;

public class b
extends a {
    public a b;
    public com.corrodinggames.rts.game.units.custom.d.b c;
    public com.corrodinggames.rts.game.units.custom.d.b d;

    public b(a a2) {
        this.b = a2;
    }

    @Override
    public boolean b(am am2) {
        return this.b.b(am2);
    }

    @Override
    public String c(am am2) {
        return this.b.c(am2);
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return this.b.a(am2, bl);
    }

    @Override
    public boolean d(am am2) {
        return this.b.d(am2);
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b a() {
        if (this.c != null) {
            return this.c;
        }
        return this.b.a();
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b b() {
        if (this.d != null) {
            return this.d;
        }
        return this.b.b();
    }

    @Override
    public void a(am am2, am am3) {
        this.b.a(am2, am3);
    }
}
