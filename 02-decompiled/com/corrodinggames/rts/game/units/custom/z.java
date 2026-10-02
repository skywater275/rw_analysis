/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.d.e;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ArrayList;

public strictfp class z {
    public String a;
    public ay[] b;
    final /* synthetic */ l c;

    public boolean a() {
        return this.b != null && this.b.length != 0;
    }

    public boolean b() {
        return this.b != null && (this.b.length != 0 || this.b == l.gf);
    }

    private z(l l2, String string) {
        this.c = l2;
        this.a = string;
        l2.gc.add(this);
    }

    public void c() {
        String[] stringArray;
        if (this.a == null || this.a.equals("")) {
            this.b = l.ge;
            return;
        }
        if (this.a.equalsIgnoreCase("NONE")) {
            this.b = l.gf;
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
            ay ay2 = this.c.d(string2);
            for (int i = 0; i < n2; ++i) {
                arrayList.add(ay2);
            }
        }
        this.b = arrayList.toArray(l.ge);
    }

    public e a(float f2, float f3, float f4, float f5, w w2) {
        return this.a(f2, f3, f4, f5, w2, 0, (short)0);
    }

    public e a(float f2, float f3, float f4, float f5, w w2, int n2, short s2) {
        e e2 = null;
        for (ay ay2 : this.b) {
            e e3 = ay2.a(f2, f3, f4, f5, w2, n2, s2);
            if (e3 == null || e2 != null) continue;
            e2 = e3;
        }
        return e2;
    }
}
