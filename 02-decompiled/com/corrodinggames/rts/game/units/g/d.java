/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.g;

import com.corrodinggames.rts.game.units.g.a;
import com.corrodinggames.rts.game.units.g.b;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public class d
extends a {
    float b;

    @Override
    public b b() {
        return com.corrodinggames.rts.game.units.g.b.a;
    }

    @Override
    public void a(y y2, as as2) {
        as2.a(this.b);
        super.a(y2, as2);
    }

    @Override
    public void a(y y2, k k2) {
        this.b = k2.g();
        super.a(y2, k2);
    }
}
