/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import java.util.HashMap;

public class c {
    private static final HashMap c = new HashMap();
    public static final c a = com.corrodinggames.rts.game.units.a.c.a("-1");
    String b;

    public static c a(String string) {
        c c2 = (c)c.get(string);
        if (c2 != null) {
            return c2;
        }
        c c3 = new c(string);
        c.put(string, c3);
        return c3;
    }

    public String a() {
        return this.b;
    }

    private c(String string) {
        this.b = string;
    }

    public static void a(as as2, c c2) {
        String string = null;
        if (c2 != null) {
            string = c2.b;
        }
        as2.b(string);
    }

    public static c a(k k2) {
        String string = k2.j();
        if (string != null) {
            return com.corrodinggames.rts.game.units.a.c.a(string);
        }
        return null;
    }

    public boolean equals(Object object) {
        return this == object;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        return "ActionId(" + this.b + ")";
    }

    public final boolean a(c c2) {
        return this == c2;
    }
}
