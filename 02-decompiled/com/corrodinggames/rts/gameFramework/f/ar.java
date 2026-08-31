/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.f.au;
import com.corrodinggames.rts.gameFramework.h.a;

strictfp class ar
extends au {
    as a;
    int b;

    public ar(float f, float f2, as as2) {
        super(f, f2);
        this.a = as2;
        this.b = 1;
    }

    @Override
    public boolean a(au au2) {
        if (super.a(au2) && au2 instanceof ar) {
            ar ar2 = (ar)au2;
            return ar2.a == this.a;
        }
        return false;
    }

    @Override
    public void b(au au2) {
        this.c = au2.c;
        ++this.b;
        this.g = null;
        this.h = false;
    }

    @Override
    public String a() {
        if (this.g == null) {
            String string = "gui.log.unitCreated";
            if (this.a.j()) {
                string = "gui.log.buildingConstructed";
            }
            this.g = String.format(com.corrodinggames.rts.gameFramework.h.a.a(string, new Object[0]), this.a.e(), this.b);
        }
        return this.g;
    }
}
