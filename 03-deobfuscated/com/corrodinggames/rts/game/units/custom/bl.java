/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.bm;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import java.util.ArrayList;

public class bl {
    ArrayList a = new ArrayList();
    int b;

    public void a(float f) {
        for (bm bm2 : (java.util.Collection<bm>) (java.util.Collection) this.a) {
            bm2.b = f;
        }
    }

    public boolean a() {
        return this.a(0.0f, 0.0f, 1.0f, true);
    }

    public boolean a(float f, float f2, float f3) {
        return this.a(f, f2, f3, false);
    }

    public boolean a(float f2, float f3, float f4, boolean bl2) {
        if (this.a.size() == 0) {
            return false;
        }
        if (this.b >= this.a.size()) {
            this.b = 0;
        }
        bm bm2 = (bm)this.a.get(this.b);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (bl2) {
            l2.bM.c(bm2.a, bm2.b);
        } else {
            l2.bM.a(bm2.a, bm2.b, f2, f3);
        }
        ++this.b;
        return true;
    }

    public static bl a(ModUnitRegistry l2, String string) {
        return bl.a(l2, string, null);
    }

    public static bl a(ModUnitRegistry l2, String string, bl bl2) {
        if ((string == null || string.equals("")) && bl2 != null) {
            return bl2;
        }
        bl bl3 = new bl(l2, string);
        return bl3;
    }

    public bl() {
    }

    public bl(ModUnitRegistry l2, String string) {
        String[] stringArray;
        if (string == null || string.equals("") || string.equalsIgnoreCase("NONE")) {
            return;
        }
        for (String string2 : stringArray = string.split(",")) {
            bm bm2 = new bm(this);
            string2 = string2.trim();
            String string3 = "";
            if (string2.startsWith("ROOT:")) {
                string2 = string2.substring("ROOT:".length());
                string3 = string3 + "ROOT:";
            }
            if (string2.startsWith("SHARED:")) {
                string2 = string2.substring("SHARED:".length());
                string3 = string3 + "SHARED:";
            }
            String[] stringArray2 = string2.split(":");
            String string4 = null;
            String string5 = stringArray2[0].trim();
            if (stringArray2.length != 1) {
                if (stringArray2.length == 2) {
                    string4 = stringArray2[1].trim();
                } else {
                    throw new RuntimeException("Unknown sound format:" + string2);
                }
            }
            if (string4 != null) {
                try {
                    bm2.b = Float.parseFloat(string4);
                }
                catch (NumberFormatException numberFormatException) {
                    throw new RuntimeException("Failed to parse volume float: '" + string4 + "' of sound: '" + string2 + "'");
                }
            }
            string5 = string3 + string5;
            bm2.a = ModLoader.a(l2.F, string5, l2);
            if (bm2.a == null) continue;
            this.a.add(bm2);
        }
    }
}
