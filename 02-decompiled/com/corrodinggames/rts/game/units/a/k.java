/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.gameFramework.h.a;

public enum k {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i,
    j,
    k;


    public String a() {
        return " - " + this.b();
    }

    public String b() {
        return com.corrodinggames.rts.gameFramework.h.a.a(this.c(), new Object[0]);
    }

    public String c() {
        return "menus.ingame.ping.type." + this.name();
    }
}
