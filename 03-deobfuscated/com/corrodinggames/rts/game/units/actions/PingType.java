/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.gameFramework.steam.Localization;

public enum PingType {
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
        return com.corrodinggames.rts.gameFramework.steam.Localization.a(this.c(), new Object[0]);
    }

    public String c() {
        return "menus.ingame.ping.type." + this.name();
    }
}
