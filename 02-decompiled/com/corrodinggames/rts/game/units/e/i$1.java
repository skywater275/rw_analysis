/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.h.a;

final class i$1
extends x {
    i$1(int n2) {
        super(n2);
    }

    @Override
    public String a() {
        return "-Will unload all units when stopped";
    }

    @Override
    public String b() {
        return a.a("gui.actions.unload", new Object[0]);
    }

    @Override
    public int b(am am2, boolean bl) {
        return ((ak)((Object)am2)).bB();
    }

    @Override
    public boolean a(am am2, boolean bl) {
        if (((ak)((Object)am2)).bA()) {
            return false;
        }
        return ((ak)((Object)am2)).f() && ((ak)((Object)am2)).bB() > 0;
    }

    @Override
    public boolean b(am am2) {
        return ((ak)((Object)am2)).j();
    }
}
