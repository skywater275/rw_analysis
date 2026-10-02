/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h$2$1;
import com.corrodinggames.rts.gameFramework.l;

final class h$2
extends x {
    h$2(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Start recording a replay to file";
    }

    @Override
    public String b() {
        return "Start Recording";
    }

    @Override
    public String d() {
        String string = "Start Recording";
        l l2 = l.B();
        boolean bl = l2.cb.k();
        string = !bl ? "Start Recording" : "Stop Recording";
        return string;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        l l2 = l.B();
        boolean bl2 = l2.cb.j();
        return !bl2;
    }

    @Override
    public boolean a(am am2) {
        l l2 = l.B();
        boolean bl = l2.cb.k();
        return bl;
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        l.e("Start recording clicked");
        if (l2.cb.j()) {
            l.e("Already in a replay");
            return false;
        }
        l2.a(new h$2$1(this));
        return false;
    }
}
