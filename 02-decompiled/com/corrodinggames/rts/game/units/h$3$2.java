/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h$3;
import com.corrodinggames.rts.gameFramework.f.a.c;
import com.corrodinggames.rts.gameFramework.f.a.f;
import com.corrodinggames.rts.gameFramework.f.a.k;
import com.corrodinggames.rts.gameFramework.l;

class h$3$2
extends k {
    final /* synthetic */ f a;
    final /* synthetic */ l b;
    final /* synthetic */ Runnable c;
    final /* synthetic */ h.3 d;

    h$3$2(h.3 var1_1, f f2, l l2, Runnable runnable) {
        this.d = var1_1;
        this.a = f2;
        this.b = l2;
        this.c = runnable;
    }

    @Override
    public boolean a(c c2) {
        this.a.i();
        this.b.a(this.c);
        return true;
    }
}
