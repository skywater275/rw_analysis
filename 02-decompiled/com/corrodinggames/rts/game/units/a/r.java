/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.p;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.l;

public class r
extends p {
    public r() {
        super("c__cut_ping");
    }

    @Override
    public String b() {
        return "Map Ping";
    }

    @Override
    public String a() {
        return "Send a map ping to your allies";
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        l2.bS.I();
        return true;
    }

    @Override
    public ad M() {
        l l2 = l.B();
        return l2.bT.v;
    }
}
