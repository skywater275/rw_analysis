/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.gameFramework.utility.m;

public class i {
    public m a = new m();

    public i() {
    }

    public i(h h2) {
        if (h2 == null) {
            return;
        }
        for (g g2 : h2.a) {
            this.a.add(g2);
        }
    }

    public boolean a(h h2) {
        if (h2 == null) {
            return false;
        }
        boolean bl = false;
        for (g g2 : h2.a) {
            if (!this.a(g2)) continue;
            bl = true;
        }
        return bl;
    }

    public boolean a(g g2) {
        if (!this.a.contains(g2)) {
            this.a.add(g2);
            return true;
        }
        return false;
    }

    public boolean b(h h2) {
        if (h2 == null) {
            return false;
        }
        boolean bl = false;
        for (g g2 : h2.a) {
            if (!this.a.remove(g2)) continue;
            bl = true;
        }
        return bl;
    }

    public h a() {
        if (this.a.size() == 0) {
            return g.d;
        }
        return new h((g[])this.a.toArray(g.c));
    }
}
