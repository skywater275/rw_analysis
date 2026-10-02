/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.z;

public class h {
    public com.corrodinggames.rts.game.units.custom.h a;
    public com.corrodinggames.rts.game.units.custom.h b;
    public float c;
    public float d;
    public b e;
    public b f;
    public z g;

    public boolean a(am am2) {
        if (this.b != null && com.corrodinggames.rts.game.units.custom.g.a(this.b, am2.de())) {
            return false;
        }
        return this.a == null || com.corrodinggames.rts.game.units.custom.g.a(this.a, am2.de());
    }
}
