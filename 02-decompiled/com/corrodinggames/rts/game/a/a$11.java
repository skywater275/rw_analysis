/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;

class a$11
extends d {
    final /* synthetic */ a a;

    a$11(a a2, String string) {
        this.a = a2;
        super(a2, string);
    }

    @Override
    public boolean a(as as2) {
        return this.a.bw.a(as2) && as2.o() == ao.d;
    }
}
