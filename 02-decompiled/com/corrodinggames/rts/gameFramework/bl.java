/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.bk;
import com.corrodinggames.rts.gameFramework.utility.m;

public strictfp class bl {
    m a = new m();

    public void a(am am2, am am3) {
        if (this.a.a > 0) {
            for (bk bk2 : this.a) {
                bk2.a(am2, am3, null);
            }
        }
    }
}
