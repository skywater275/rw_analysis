/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.f.au;
import com.corrodinggames.rts.gameFramework.h.a;

strictfp class as
extends au {
    private boolean a;

    public as(float f, float f2, boolean bl) {
        super(f, f2);
        this.a = bl;
    }

    @Override
    public boolean a(au au2) {
        if (super.a(au2) && au2 instanceof as) {
            as as2 = (as)au2;
            return as2.a == this.a;
        }
        return false;
    }

    @Override
    public void b(au au2) {
    }

    @Override
    protected long b() {
        return 20000L;
    }

    @Override
    public String a() {
        if (this.g == null) {
            this.g = this.a ? com.corrodinggames.rts.gameFramework.h.a.a("gui.log.baseDamaged", new Object[0]) : com.corrodinggames.rts.gameFramework.h.a.a("gui.log.unitDamaged", new Object[0]);
        }
        return this.g;
    }
}
