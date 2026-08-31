/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.j;

public abstract class ax
extends at {
    public ax(int n, String string) {
        super(n, string);
    }

    @Override
    public double a(j j2, as as2) {
        return this.a(j2);
    }

    @Override
    public void a(j j2, double d2) {
        j2.dJ();
        this.b(j2, d2);
    }

    public abstract double a(j var1);

    public abstract void b(j var1, double var2);

    @Override
    public boolean b() {
        return true;
    }
}
