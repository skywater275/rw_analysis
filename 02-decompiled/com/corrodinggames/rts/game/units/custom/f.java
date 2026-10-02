/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.c;
import com.corrodinggames.rts.game.units.custom.d;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.m;
import com.corrodinggames.rts.game.units.custom.n;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.ArrayList;
import java.util.Collection;

public class f {
    public String a;
    public int b;
    public int c;
    public float d;
    public float e;
    public float f;
    public boolean g;
    public float h;
    public float i;
    public LogicBoolean j;
    public m k;
    public com.corrodinggames.rts.gameFramework.utility.m l = new com.corrodinggames.rts.gameFramework.utility.m();
    public boolean m = true;
    public float n;
    public boolean o;
    public ArrayList p = new ArrayList();
    public float q;

    public f(String string) {
        this.a = string;
    }

    public void a(l l2) {
        for (c c2 : this.l) {
            if (c2.a == com.corrodinggames.rts.game.units.custom.d.c || c2.a == com.corrodinggames.rts.game.units.custom.d.d || c2.a == com.corrodinggames.rts.game.units.custom.d.f || c2.a == com.corrodinggames.rts.game.units.custom.d.e || c2.a == com.corrodinggames.rts.game.units.custom.d.j) {
                boolean bl2 = false;
                for (ba ba2 : l2.ax) {
                    if (!c2.c.equals(ba2.b)) continue;
                    c2.b = ba2.a;
                    bl2 = true;
                    break;
                }
                if (!bl2) {
                    throw new bo("Cannot find leg:" + c2.c + " for animation:" + this.a);
                }
            }
            if (c2.b >= 0) continue;
            throw new bo("Cannot find target for:" + c2.c + " for animation:" + this.a);
        }
    }

    public boolean a(n n2) {
        for (n n3 : this.p) {
            if (n3 != n2) continue;
            return true;
        }
        return false;
    }

    public boolean a() {
        return this.o;
    }

    public void a(l l2, ab ab2, String string, String string2) {
        Object object;
        boolean bl2 = false;
        String string3 = null;
        String string4 = ab2.b(string, string2 + "onActions", (String)null);
        if (string4 != null) {
            for (String string5 : string4.split(",")) {
                String object22 = string5.trim();
                if (object22.equals("")) continue;
                n n2 = com.corrodinggames.rts.game.units.custom.n.a(object22);
                if (n2 == null) {
                    throw new bo("Unknown action type: " + object22 + " on animation:" + this.a);
                }
                object = l2.a(n2);
                if (object != null) {
                    throw new bo("Cannot add action: " + object22 + " to:" + this.a + " it already exists on:" + ((f)object).a);
                }
                this.p.add(n2);
            }
        }
        this.q = ab2.a(string, string2 + "onActionsQueuedUnitPlayAt", Float.valueOf(0.0f)).floatValue();
        this.b = ab2.b(string, string2 + "start", 0);
        this.c = ab2.b(string, string2 + "end", -1);
        if (this.c != -1 && this.c < this.b) {
            throw new RuntimeException("animationEnd cannot before animationStart on animation:" + this.a);
        }
        this.k = com.corrodinggames.rts.game.units.custom.m.a(l2, ab2, string, "", true);
        this.h = ab2.d(string, string2 + "blendIn", Float.valueOf(-1.0f)).floatValue();
        this.i = ab2.d(string, string2 + "blendOut", Float.valueOf(-1.0f)).floatValue();
        this.j = ab2.a(l2, string, string2 + "playbackRate", null, LogicBoolean$ReturnType.number);
        this.d = ab2.a(string, string2 + "scale_start", Float.valueOf(1.0f)).floatValue();
        this.e = ab2.a(string, string2 + "scale_end", Float.valueOf(1.0f)).floatValue();
        Float f2 = ab2.a(string, string2 + "speed", (Float)null);
        if (f2 != null) {
            this.f = f2.floatValue();
            bl2 = true;
            string3 = "speed";
        } else {
            this.f = 40.0f;
        }
        this.g = ab2.a(string, string2 + "pingPong", (Boolean)false);
        float f3 = 1.0f * this.f;
        float f4 = ab2.a(string, string2 + "KeyframeTimeScale", Float.valueOf(1.0f)).floatValue();
        if (this.c != -1) {
            bl2 = true;
            string3 = "animationEnd";
            c c2 = new c();
            c2.a = com.corrodinggames.rts.game.units.custom.d.a;
            this.l.add(c2);
            int n3 = this.c - this.b + 1;
            c2.a(0.0f, this.b);
            c2.a(f3 *= (float)n3, (float)this.c + 0.99f);
        }
        if (this.d != 1.0f || this.e != 1.0f) {
            bl2 = true;
            string3 = "animationScaleX";
            c c3 = new c();
            c3.a = com.corrodinggames.rts.game.units.custom.d.b;
            this.l.add(c3);
            c3.a(0.0f, this.d);
            c3.a(f3, this.e);
        }
        if (bl2) {
            this.n = f3;
        }
        String string6 = string2 + "leg";
        String string7 = string2 + "arm";
        object = ab2.f(string, string6, string7);
        ((com.corrodinggames.rts.gameFramework.utility.m)object).addAll((Collection)ab2.k(string, string2 + "turret"));
        ((com.corrodinggames.rts.gameFramework.utility.m)object).addAll((Collection)ab2.k(string, string2 + "body"));
        ((com.corrodinggames.rts.gameFramework.utility.m)object).addAll((Collection)ab2.k(string, string2 + "effect"));
        Object object2 = ((com.corrodinggames.rts.gameFramework.utility.m)object).iterator();
        while (object2.hasNext()) {
            String string8 = (String)object2.next();
            boolean bl3 = false;
            if (bl3) continue;
            if (bl2) {
                throw new bo("Cannot mix new (" + (String)string8 + ") and old style (" + string3 + ") animations on:" + this.a);
            }
            this.a(l2, ab2, string, string2, string8);
        }
        object2 = new com.corrodinggames.rts.gameFramework.utility.m();
        this.m = false;
        for (c c4 : this.l) {
            c4.a(f4);
            c4.c();
            if (this.n < c4.d) {
                this.n = c4.d;
            }
            if (c4.e.length <= 0) continue;
            this.o = true;
            if (c4.a != com.corrodinggames.rts.game.units.custom.d.a && c4.a != com.corrodinggames.rts.game.units.custom.d.b) {
                this.m = true;
            }
            ((com.corrodinggames.rts.gameFramework.utility.m)object2).add(c4);
        }
        this.l = object2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public c a(String string, String string2) {
        d d2;
        int n2;
        Object var5_3 = null;
        if (string2.startsWith("leg") || string2.startsWith("arm")) {
            n2 = -1;
            if (string.equalsIgnoreCase("x")) {
                d2 = com.corrodinggames.rts.game.units.custom.d.c;
            } else if (string.equalsIgnoreCase("y")) {
                d2 = com.corrodinggames.rts.game.units.custom.d.d;
            } else if (string.equalsIgnoreCase("dir")) {
                d2 = com.corrodinggames.rts.game.units.custom.d.e;
            } else if (string.equalsIgnoreCase("height")) {
                d2 = com.corrodinggames.rts.game.units.custom.d.f;
            } else {
                if (!string.equalsIgnoreCase("alpha")) throw new bo("Unknown leg/arm animation type:" + string + " on animation:" + this.a);
                d2 = com.corrodinggames.rts.game.units.custom.d.j;
            }
        } else if (string2.startsWith("turret")) {
            n2 = Integer.parseInt(string2.substring("turret".length()));
            --n2;
            if (string.equalsIgnoreCase("x")) {
                d2 = com.corrodinggames.rts.game.units.custom.d.g;
            } else {
                if (!string.equalsIgnoreCase("y")) throw new bo("Unknown turret animation type:" + string + " on animation:" + this.a);
                d2 = com.corrodinggames.rts.game.units.custom.d.h;
            }
        } else if (string2.startsWith("body")) {
            n2 = 0;
            if (string.equalsIgnoreCase("scale")) {
                d2 = com.corrodinggames.rts.game.units.custom.d.b;
            } else {
                if (!string.equalsIgnoreCase("frame")) throw new bo("Unknown body animation type:" + string + " on animation:" + this.a);
                d2 = com.corrodinggames.rts.game.units.custom.d.a;
            }
        } else {
            if (!string2.startsWith("effect")) throw new bo("Unknown animation target:" + string2 + " on animation:" + this.a);
            n2 = 0;
            d2 = com.corrodinggames.rts.game.units.custom.d.i;
            string2 = "event";
        }
        for (c c2 : this.l) {
            if (c2.a != d2 || !string2.equals(c2.c)) continue;
            return c2;
        }
        c c3 = new c();
        c3.a = d2;
        c3.b = n2;
        c3.c = string2;
        this.l.add(c3);
        return c3;
    }

    public void a(l l2, ab ab2, String string, String string2, String string3) {
        float f2;
        String string4 = string3.substring(string2.length());
        String string5 = string4.split("_")[0];
        String string6 = string2 + string5 + "_";
        String string7 = string3;
        String string8 = string7.substring(string6.length());
        try {
            f2 = ab.a(string8, false, string, string7);
        }
        catch (NumberFormatException numberFormatException) {
            throw new bo("Failed to read time:" + string8 + " in key:" + string7 + " section:" + string + " expected a float with optional 's' or 'ms' postfix");
        }
        String string9 = ab2.e(string, string7);
        if (!string9.startsWith("{") || !string9.endsWith("}")) {
            throw new bo("Unknown format:" + string9, string, string7);
        }
        string9 = string9.substring(1, string9.length() - 1);
        String[] stringArray = string9.split(",");
        c c2 = null;
        for (String string10 : stringArray) {
            String[] stringArray2 = string10.split(":");
            if (stringArray2.length != 2) {
                throw new bo("Unknown format on part:" + string10 + " of: " + string9, string, string7);
            }
            String string11 = stringArray2[0].trim();
            String string12 = stringArray2[1].trim();
            c c3 = this.a(string11, string5);
            if (c2 != c3) {
                if (c2 != null) {
                    c2.b();
                }
                c2 = c3;
            }
            try {
                c3.a(l2, f2, string11, string12);
            }
            catch (bo bo2) {
                throw new bo(bo2.getMessage() + " (as part of key:" + string7 + " section:" + string + ")", bo2);
            }
        }
        if (c2 != null) {
            c2.b();
        }
    }
}
