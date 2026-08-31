/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a.a;

import com.corrodinggames.rts.game.a.a.a;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.u;

public abstract class c
extends a {
    u a = new u();

    @Override
    public void a(k k2) {
        super.a(k2);
        int n2 = k2.f();
        for (int i = 0; i < n2; ++i) {
            y y2 = k2.p();
            if (y2 == null) continue;
            this.a.a(y2);
        }
    }

    @Override
    public void a(as as2) {
        super.a(as2);
        int n2 = this.a.size();
        as2.a(n2);
        for (y y2 : this.a) {
            as2.a(y2);
        }
    }

    public abstract boolean c(com.corrodinggames.rts.game.a.a var1, y var2);

    @Override
    public void a(com.corrodinggames.rts.game.a.a a2, y y2) {
        if (this.c(a2, y2) && !this.a.contains(y2)) {
            this.a.a(y2);
        }
    }

    @Override
    public void b(com.corrodinggames.rts.game.a.a a2, y y2) {
    }
}
