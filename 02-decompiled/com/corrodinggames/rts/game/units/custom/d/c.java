/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.d.a;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.d.d;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;

public class c
extends a {
    public final m a = new m();
    boolean b;
    public int c;
    public int d;
    public int e;
    public int f;

    public static c a(l l2, ab ab2, String string, String string2, c c2) {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return c2;
        }
        try {
            c c3 = com.corrodinggames.rts.game.units.custom.d.c.a(l2, string3);
            return c3;
        }
        catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            throw new bo("[" + string + "]" + string2 + ": " + runtimeException.getMessage());
        }
    }

    public static c a(l l2, String string) {
        return com.corrodinggames.rts.game.units.custom.d.c.a(l2, string, false);
    }

    public static c a(l l2, String string, boolean bl) {
        c c2 = new c();
        ArrayList arrayList = al.a(string, ",", "|", false);
        for (String string2 : arrayList) {
            String string3;
            String string4;
            int n2 = al.a(string2, "=", ":");
            if (n2 == -1) {
                if (!bl) {
                    throw new bo("Unknown price format:" + string);
                }
                string4 = "credits";
                string3 = string2;
            } else {
                string4 = string2.substring(0, n2).trim();
                string3 = string2.substring(n2 + 1);
            }
            if (string4.equals("hasFlag")) {
                c2.e = com.corrodinggames.rts.game.units.custom.d.b.a(c2.e, string3);
                continue;
            }
            if (string4.equals("hasMissingFlag")) {
                c2.f = com.corrodinggames.rts.game.units.custom.d.b.a(c2.f, string3);
                continue;
            }
            if (string4.equals("setFlag")) {
                c2.c = com.corrodinggames.rts.game.units.custom.d.b.a(c2.c, string3);
                continue;
            }
            if (string4.equals("unsetFlag")) {
                c2.d = com.corrodinggames.rts.game.units.custom.d.b.a(c2.d, string3);
                continue;
            }
            com.corrodinggames.rts.game.units.custom.e.a a2 = l2.j(string4);
            if (a2 == null) {
                throw new bo("Could not find resource type:" + string4 + " from [" + string + "]");
            }
            LogicBoolean logicBoolean = LogicBooleanLoader.parseNumberBlock(l2, string3);
            if (logicBoolean == null) {
                throw new bo("Value missing for:" + string4 + " from [" + string + "]");
            }
            if (!(logicBoolean instanceof LogicBoolean.StaticValueBoolean)) {
                c2.b = true;
            }
            d d2 = new d(a2, logicBoolean);
            c2.a.add(d2);
        }
        return c2;
    }

    @Override
    public boolean b(am am2) {
        return this.b(am2, 1.0);
    }

    @Override
    public boolean b(am am2, double d2) {
        if (!(am2 instanceof y)) {
            return false;
        }
        y y2 = (y)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            double d3;
            d d4 = (d)objectArray[i];
            double d5 = d4.c != null ? (double)d4.c.readNumber(y2) * d2 : d4.b * d2;
            if (!(d5 > 0.0) || !((d3 = d4.a.a(y2)) < d5)) continue;
            return false;
        }
        return this.g(y2);
    }

    public void d(am am2) {
        if (!(am2 instanceof y)) {
            com.corrodinggames.rts.gameFramework.l.n("DynamicResourcePrice doesn't work on: " + am2.c());
            return;
        }
        y y2 = (y)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            d d2 = (d)objectArray[i];
            double d3 = d2.c != null ? (double)d2.c.readNumber(y2) : d2.b;
            d2.a.a(y2, d3);
        }
        this.f(y2);
        com.corrodinggames.rts.game.units.custom.d.b.d(y2);
    }

    @Override
    public void a(am am2) {
        this.a(am2, 1.0);
    }

    @Override
    public void a(am am2, double d2) {
        if (!(am2 instanceof y)) {
            com.corrodinggames.rts.gameFramework.l.n("DynamicResourcePrice doesn't work on: " + am2.c());
            return;
        }
        y y2 = (y)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            d d3 = (d)objectArray[i];
            double d4 = d3.c != null ? (double)d3.c.readNumber(y2) : d3.b;
            d3.a.b(y2, -d4 * d2);
        }
        this.f(y2);
        com.corrodinggames.rts.game.units.custom.d.b.d(y2);
    }

    public void e(am am2) {
        if (!(am2 instanceof y)) {
            com.corrodinggames.rts.gameFramework.l.n("DynamicResourcePrice doesn't work on: " + am2.c());
            return;
        }
        y y2 = (y)am2;
        int n2 = this.a.a;
        Object[] objectArray = this.a.a();
        for (int i = 0; i < n2; ++i) {
            d d2 = (d)objectArray[i];
            double d3 = d2.c != null ? (double)d2.c.readNumber(y2) : d2.b;
            d2.a.b(y2, d3);
        }
        this.f(y2);
        com.corrodinggames.rts.game.units.custom.d.b.d(y2);
    }

    public void f(am am2) {
        if (this.d != 0) {
            am2.cF &= ~this.d;
        }
        if (this.c != 0) {
            am2.cF |= this.c;
        }
    }

    public boolean g(am am2) {
        if (this.e != 0 && !com.corrodinggames.rts.game.units.custom.d.c.a(am2.cF, this.e)) {
            return false;
        }
        return this.f == 0 || !com.corrodinggames.rts.game.units.custom.d.c.b(am2.cF, this.f);
    }

    public static boolean a(int n2, int n3) {
        return (n3 & n2) == n3;
    }

    public static boolean b(int n2, int n3) {
        return (n3 & n2) != 0;
    }
}
