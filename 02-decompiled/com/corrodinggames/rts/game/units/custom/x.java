/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.w;

public strictfp class x
extends w {
    String g;
    bh h;

    @Override
    public void a() {
    }

    @Override
    public void b() {
        super.a();
        l l2 = this.e();
        this.h = l2.f(this.g);
        if (this.h == null) {
            throw new bo("Could not find projectile:" + this.g + " on unit target:" + this.d() + " used on:" + this.a + " in section:" + this.b);
        }
    }

    public bh f() {
        return this.h;
    }
}
