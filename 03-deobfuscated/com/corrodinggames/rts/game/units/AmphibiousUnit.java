/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.MovableUnit;

public class AmphibiousUnit
extends MovableUnit {
    public static AmphibiousUnit a(com.corrodinggames.rts.game.PlayerState n2) {  // 02b t.a(n)
        AmphibiousUnit t2 = new AmphibiousUnit(true);
        t2.b(n2);
        t2.isDead = true;
        return t2;
    }

    AmphibiousUnit(boolean bl) {
        super(bl);
    }


    public UnitTypeHandle r() {
        return UnitRegistry.Z;
    }

    public static void b() {
    }


    public String c() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep;
        if (this.player != null) {
            string = string + " t:" + this.player.k;
        }
        string = string + ")";
        return string;
    }
}
