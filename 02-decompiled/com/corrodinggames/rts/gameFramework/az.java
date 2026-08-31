/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.w;

public strictfp abstract class az
extends w {
    public int ex = 0;

    protected az(boolean bl) {
        super(bl);
    }

    @Override
    public void a(as as2) {
        as2.d("xy is:");
        as2.a(this.eo);
        as2.a(this.ep);
        as2.a(this.eq);
        as2.a(this.ex);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.eo = k2.g();
        this.ep = k2.g();
        this.eq = k2.g();
        this.ex = k2.f();
        super.a(k2);
    }
}
