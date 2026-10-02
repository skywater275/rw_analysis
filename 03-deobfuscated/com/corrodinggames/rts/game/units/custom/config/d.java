/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.config;

import java.util.HashMap;

public class d {
    HashMap a = new HashMap();

    public void a(String string, String string2) {
        this.a.put(string, string2);
    }

    public String a(String string) {
        return (String)this.a.get(string);
    }

    public void a() {
        this.a.clear();
    }
}
