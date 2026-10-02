/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.gameFramework.l;

public class x
extends com.corrodinggames.rts.game.units.x {
    f a = new f();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.R;
    }

    public x() {
        super(true);
        this.bX = n.i;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        l.a("PlaceholderUnit was updated");
        this.ci();
    }

    @Override
    public strictfp boolean t() {
        return true;
    }

    @Override
    public strictfp boolean u() {
        return true;
    }

    @Override
    public f df() {
        return this.a;
    }

    public void a(f f2) {
        this.a = f2;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
