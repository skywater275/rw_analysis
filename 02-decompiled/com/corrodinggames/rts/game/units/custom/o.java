/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.l;

public strictfp class o {
    String a;
    f b;
    final /* synthetic */ l c;

    public o(l l2) {
        this.c = l2;
    }

    public void a() {
        if (this.a != null && this.b() == null) {
            throw new RuntimeException("Failed to find animation:" + this.a);
        }
    }

    public f b() {
        if (this.a == null) {
            return null;
        }
        if (this.b != null) {
            return this.b;
        }
        for (f f2 : this.c.dr) {
            if (!f2.a.equalsIgnoreCase(this.a)) continue;
            this.b = f2;
            return f2;
        }
        return null;
    }
}
