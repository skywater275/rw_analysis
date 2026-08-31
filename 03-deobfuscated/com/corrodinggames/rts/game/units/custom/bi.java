/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bj;
import com.corrodinggames.rts.game.units.custom.bk;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.CustomPhysics;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ArrayList;
import java.util.Iterator;

public class bi {
    CustomArrayList a;
    public static final bj b = new bj();

    public static bi a(ModUnitRegistry l2, ab ab2, String string, String string2, bi bi2) throws bo {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return bi2;
        }
        return bi.a(l2, string3, string, string2, false);
    }

    public static bi a(ModUnitRegistry l2, String string, String string2, String string3, boolean bl) throws bo {
        if (l2 == null) {
            throw new RuntimeException("meta==null");
        }
        return bi.b(l2, string, string2, string3, bl);
    }

    public static bi b(ModUnitRegistry l2, String string, String string2, String string3, boolean bl) throws bo {
        int n2;
        bi bi2 = new bi();
        if (string == null || "".equals(string) || "NONE".equalsIgnoreCase(string)) {
            return bi2;
        }
        if (l2 == null) {
            throw new bo("meta required");
        }
        ArrayList arrayList = al.a(string, ",", false);
        for (String string4 : (java.util.Collection<String>) (java.util.Collection) arrayList) {
            String[] stringArray;
            if ("".equals(string4 = string4.trim())) continue;
            String string5 = string4;
            String string6 = null;
            if (string4.contains("(") && string4.contains(")")) {
                stringArray = string4.split("\\(");
                if (stringArray.length != 2) {
                    throw new bo("[" + string2 + "]" + string3 + " UnitList: Unexpected format for '" + string5 + "' of " + string);
                }
                string4 = stringArray[0];
                string6 = stringArray[1].trim();
            }
            stringArray = string4.split("\\*");
            string4 = stringArray[0];
            int n3 = 1;
            if (stringArray.length >= 2) {
                n3 = Integer.parseInt(stringArray[1]);
            }
            CustomPhysics x2 = l2.getUnitNameWithoutModPrefix(string4, string3, string2);
            bk bk2 = new bk(x2);
            if (bi2.a == null) {
                bi2.a = new CustomArrayList();
            }
            bk2.b = n3;
            if (string6 != null) {
                String[] stringArray2;
                if (!string6.endsWith(")")) {
                    throw new bo("[" + string2 + "]" + string3 + " UnitList: Expected ')' in '" + string5 + "' of " + string);
                }
                string6 = string6.substring(0, string6.length() - 1);
                for (String string7 : stringArray2 = string6.split("\\,")) {
                    if (string7.trim().equals("")) continue;
                    String[] stringArray3 = string7.split("\\=");
                    if (stringArray3.length != 2) {
                        throw new RuntimeException("[" + string2 + "]" + string3 + " UnitList: Unexpected key format for '" + string5 + "' of " + string);
                    }
                    String string8 = stringArray3[0].trim();
                    String string9 = stringArray3[1].trim();
                    if (string8.equalsIgnoreCase("spawnChance")) {
                        bk2.c = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("maxSpawnLimit")) {
                        bk2.d = ab.i(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("recursionLimit")) {
                        bk2.n = ab.i(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetX") || string8.equalsIgnoreCase("xOffsetAbsolute")) {
                        bk2.e = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetY") || string8.equalsIgnoreCase("yOffsetAbsolute")) {
                        bk2.f = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("xOffsetRelative")) {
                        bk2.i = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("yOffsetRelative")) {
                        bk2.j = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomXY")) {
                        float f2;
                        bk2.k = f2 = ab.h(string2, string3, string9);
                        bk2.l = f2;
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomX")) {
                        bk2.k = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomY")) {
                        bk2.l = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetHeight")) {
                        bk2.g = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomDir")) {
                        bk2.m = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetDir")) {
                        bk2.h = ab.h(string2, string3, string9);
                        continue;
                    }
                    throw new bo("[" + string2 + "]" + string3 + " ProjectileList: Unknown parameter '" + string8 + "' for '" + string5 + "' of " + string);
                }
            }
            bi2.a.add(bk2);
        }
        if (bl && (n2 = bi2.a()) > 1) {
            throw new bo("[" + string2 + "]" + string3 + " Too many units: " + n2 + ", only single unit is allowed here");
        }
        return bi2;
    }

    public int a() {
        if (this.a == null || this.a.size() == 0) {
            return 0;
        }
        int n2 = 0;
        for (Iterator iterator = this.a.iterator(); iterator.hasNext(); ) {
            bk bk2 = (bk) iterator.next();
            n2 += bk2.b;
        }
        return n2;
    }

    public void a(float f2, float f3, float f4, float f5, UnitInstance am2, com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2, boolean bl, int n2, com.corrodinggames.rts.game.MovementController f6, UnitInstance am3) {  // 02b bi.java L166: a(float,float,float,float,am,utility.m,boolean,int,game.f,am) 铁证
        if (this.a == null || this.a.size() == 0) {
            return;
        }
        int n3 = 0;
        int n4 = 0;
        if (am2 == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("projectile spawn At: Skipping, source unit required");
            return;
        }
        for (Iterator iterator = this.a.iterator(); iterator.hasNext(); ) {
            bk bk2 = (bk) iterator.next();
            bh bh2 = bk2.a.f();
            if (bh2 == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("projectile spawn At: Skipping, projectileType==null");
                continue;
            }
            for (int i = 0; i < bk2.b; ++i) {
                float f7;
                if (bk2.c < 1.0f && (f7 = GameUtils.a(am2, 0.0f, 1.0f, ++n4)) > bk2.c || n3 >= bk2.d || n2 > bk2.n) continue;
                f7 = f2 + bk2.e;
                float f8 = f3 + bk2.f;
                float f9 = f4 + bk2.g;
                float f10 = f5 + bk2.h;
                if (bk2.m != 0.0f) {
                    f10 += GameUtils.a(am2, -bk2.m, bk2.m, n4 * 4 + 3);
                }
                if (bk2.k != 0.0f) {
                    f7 += GameUtils.a(am2, -bk2.k, bk2.k, n4 * 2 + 1);
                }
                if (bk2.l != 0.0f) {
                    f8 += GameUtils.a(am2, -bk2.l, bk2.l, n4 * 3 + 2);
                }
                if (bk2.i != 0.0f || bk2.j != 0.0f) {
                    float f11 = GameUtils.cosFast(f5);
                    float f12 = GameUtils.sinFast(f5);
                    float f13 = bk2.i;
                    float f14 = bk2.j;
                    f7 += f11 * f14 - f12 * f13;
                    f8 += f12 * f14 + f11 * f13;
                }
                int n5 = -1;
                com.corrodinggames.rts.game.MovementController f15 = CustomUnitType.a(am2, n5, bh2, f7, f8, f9, f10);
                f15.aD = n2;
                if (f6 != null && am2 != null) {
                    bh2.a(am2, f15, f6.l, f6.n, f6.o, -1.0f);
                }
                this.a(f15, bk2, am2, f6, am3);
                ++n3;
                if (m2 == null) continue;
                m2.add(f15);
            }
        }
    }

    public void a(com.corrodinggames.rts.game.MovementController f2, bk bk2, UnitInstance am2, com.corrodinggames.rts.game.MovementController f3, UnitInstance am3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        bi.b.a = f2;
        bi.b.b = bk2;
        bi.b.c = am2;
        bi.b.d = f3;
        bi.b.e = am3;
        float f4 = f2.eo;
        float f5 = f2.ep;
        float f6 = 100.0f;
        l2.cc.a(f4, f5, f6, null, 0.0f, b);
    }
}
