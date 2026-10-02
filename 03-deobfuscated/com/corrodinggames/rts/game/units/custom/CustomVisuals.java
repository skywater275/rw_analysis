/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.effects.HUDElement;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;

public strictfp class CustomVisuals {
    public String a;
    public ay[] dataValue;
    final /* synthetic */ ModUnitRegistry c;

    public boolean a() {
        return this.dataValue != null && this.dataValue.length != 0;
    }

    public boolean b() {
        return this.dataValue != null && (this.dataValue.length != 0 || this.dataValue == l.gf);
    }

    private CustomVisuals(ModUnitRegistry l2, String string) {
        this.c = l2;
        this.a = string;
        l2.gc.add(this);
    }

    CustomVisuals(ModUnitRegistry l2, String string, ModUnitRegistry$1 modUnitRegistry$1) {  // 02b z.java L85: z(l,String,l$1) 合成构造
        this(l2, string);
    }

    public void c() throws bo {
        String[] stringArray;
        if (this.a == null || this.a.equals("")) {
            this.dataValue = l.ge;
            return;
        }
        if (this.a.equalsIgnoreCase("NONE")) {
            this.dataValue = l.gf;
            return;
        }
        ArrayList<ay> arrayList = new ArrayList<ay>();
        for (String string : stringArray = this.a.split(",")) {
            string = string.trim();
            String[] stringArray2 = string.split("\\*");
            String string2 = stringArray2[0];
            int n2 = 1;
            if (stringArray2.length >= 2) {
                n2 = Integer.parseInt(stringArray2[1]);
            }
            ay ay2 = this.c.getCostForTechLevel(string2);
            for (int i = 0; i < n2; ++i) {
                arrayList.add(ay2);
            }
        }
        this.dataValue = arrayList.toArray(l.ge);
    }

    public com.corrodinggames.rts.gameFramework.effects.HUDElement a(float f2, float f3, float f4, float f5, com.corrodinggames.rts.gameFramework.GameObject w2) {  // 02b z.java L64: a(FFFF,w)→d.e
        return this.a(f2, f3, f4, f5, w2, 0, (short)0);
    }

    public com.corrodinggames.rts.gameFramework.effects.HUDElement a(float f2, float f3, float f4, float f5, com.corrodinggames.rts.gameFramework.GameObject w2, int n2, short s2) {
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = null;
        for (ay ay2 : this.dataValue) {
            com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = ay2.a(f2, f3, f4, f5, w2, n2, s2);
            if (e3 == null || e2 != null) continue;
            e2 = e3;
        }
        return e2;
    }
}
