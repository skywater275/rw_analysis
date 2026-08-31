/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.x;

public class t
extends x {
    public static t a(n n2) {
        t t2 = new t(true);
        t2.b(n2);
        t2.bV = true;
        return t2;
    }

    t(boolean bl) {
        super(bl);
    }

    @Override
    public as r() {
        return com.corrodinggames.rts.game.units.ar.Z;
    }

    public static void b() {
    }

    @Override
    public String c() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep;
        if (this.bX != null) {
            string = string + " t:" + this.bX.k;
        }
        string = string + ")";
        return string;
    }
}
