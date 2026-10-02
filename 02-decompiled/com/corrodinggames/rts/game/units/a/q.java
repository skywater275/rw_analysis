/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.p;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.l;

public class q
extends p {
    public q() {
        super("c__cut_chat");
    }

    @Override
    public String b() {
        return "Team Chat";
    }

    @Override
    public String a() {
        return "Send a team chat message to your allies";
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        l2.bS.g.n();
        return true;
    }

    @Override
    public ad M() {
        l l2 = l.B();
        return l2.bT.u;
    }
}
