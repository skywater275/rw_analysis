/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bq;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;

public class bp {
    m a;

    public static bp a(String string, String string2, String string3) {
        return bp.b(null, string, string2, string3, false);
    }

    public static bp a(l l2, ab ab2, String string, String string2) {
        String string3 = ab2.b(string, string2, (String)null);
        return bp.a(l2, string3, string, string2, false);
    }

    public static bp b(l l2, ab ab2, String string, String string2) {
        String string3 = ab2.b(string, string2, (String)null);
        return bp.a(l2, string3, string, string2, true);
    }

    public static bp a(l l2, String string, String string2, String string3, boolean bl) {
        if (l2 == null) {
            throw new RuntimeException("meta==null");
        }
        return bp.b(l2, string, string2, string3, bl);
    }

    public static bp b(l l2, String string, String string2, String string3, boolean bl2) {
        int n2;
        bp bp2 = new bp();
        if (string == null || "".equals(string) || "NONE".equalsIgnoreCase(string)) {
            return bp2;
        }
        ArrayList arrayList = al.a(string, ",", false);
        for (String string4 : arrayList) {
            String[] stringArray;
            if ("".equals(string4 = string4.trim())) continue;
            String string5 = string4;
            String string6 = null;
            if (string4.contains("(") && string4.contains(")")) {
                stringArray = al.b(string4, "(");
                if (stringArray == null) {
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
            v v2 = new v();
            v2.a = string3;
            v2.b = string2;
            v2.c = string4;
            if (l2 != null) {
                l2.p.add(v2);
            } else {
                v2.a();
            }
            bq bq2 = new bq(v2);
            if (bp2.a == null) {
                bp2.a = new m();
            }
            bq2.d = n3;
            if (string6 != null) {
                if (!string6.endsWith(")")) {
                    throw new bo("[" + string2 + "]" + string3 + " UnitList: Expected ')' in '" + string5 + "' of " + string);
                }
                string6 = string6.substring(0, string6.length() - 1);
                ArrayList arrayList2 = al.a(string6, ",", false, false);
                for (String string7 : arrayList2) {
                    if (string7.trim().equals("")) continue;
                    String[] stringArray2 = al.b(string7, "=");
                    if (stringArray2 == null) {
                        throw new RuntimeException("[" + string2 + "]" + string3 + " UnitList: Unexpected key format for '" + string5 + "' of " + string);
                    }
                    String string8 = stringArray2[0].trim();
                    String string9 = stringArray2[1].trim();
                    if (string8.equalsIgnoreCase("neutralTeam")) {
                        bq2.e = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("setToTeamOfLastAttacker")) {
                        bq2.g = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("aggressiveTeam")) {
                        bq2.f = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("spawnChance")) {
                        bq2.h = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("maxSpawnLimit")) {
                        bq2.i = ab.i(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("techLevel")) {
                        bq2.m = ab.i(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("gridAlign")) {
                        bq2.j = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("skipIfOverlapping")) {
                        bq2.k = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("falling")) {
                        bq2.l = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("transportedUnitsToTransfer")) {
                        bq2.w = (short)ab.i(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("alwaysStartDirAtZero")) {
                        bq2.n = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("alwayStartDirAtZero")) {
                        bq2.n = ab.g(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetX")) {
                        bq2.o = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetY")) {
                        bq2.p = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomXY")) {
                        float f2;
                        bq2.s = f2 = ab.h(string2, string3, string9);
                        bq2.t = f2;
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomX")) {
                        bq2.s = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomY")) {
                        bq2.t = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetHeight")) {
                        bq2.q = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetRandomDir")) {
                        bq2.u = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("offsetDir")) {
                        bq2.r = ab.h(string2, string3, string9);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("addResources")) {
                        if (l2 == null) {
                            throw new bo("[" + string2 + "]" + string3 + " addResources not supported from here");
                        }
                        try {
                            bq2.v = b.b(l2, string9);
                            continue;
                        }
                        catch (bo bo2) {
                            bo2.printStackTrace();
                            throw new bo("[" + string2 + "]" + string3 + " addResources:" + bo2.getMessage());
                        }
                    }
                    if (string8.equalsIgnoreCase("spawnSource")) {
                        bq2.b = ab.a(string9, l2, string2, string3, null);
                        continue;
                    }
                    if (string8.equalsIgnoreCase("copyWaypointsFrom")) {
                        bq2.c = ab.a(string9, l2, string2, string3, null);
                        continue;
                    }
                    throw new bo("[" + string2 + "]" + string3 + " UnitList: Unknown parameter '" + string8 + "' for '" + string5 + "' of " + string);
                }
                if (bq2.g && bq2.e) {
                    throw new bo("[" + string2 + "]" + string3 + " Cannot set setToTeamOfLastAttacker and neutralTeam at same time in " + string);
                }
                if (bq2.f && bq2.e) {
                    throw new bo("[" + string2 + "]" + string3 + " Cannot set aggressiveTeam and neutralTeam at same time in " + string);
                }
                if (bq2.f && bq2.g) {
                    throw new bo("[" + string2 + "]" + string3 + " Cannot set aggressiveTeam and setToTeamOfLastAttacker at same time in " + string);
                }
            }
            bp2.a.add(bq2);
        }
        if (bl2 && (n2 = bp2.a()) > 1) {
            throw new bo("[" + string2 + "]" + string3 + " Too many units: " + n2 + ", only single unit is allowed here");
        }
        return bp2;
    }

    public int a() {
        if (this.a == null || this.a.size() == 0) {
            return 0;
        }
        int n2 = 0;
        for (bq bq2 : this.a) {
            n2 += bq2.d;
        }
        return n2;
    }

    public boolean b() {
        return this.a == null || this.a.size() == 0;
    }

    public void a(m m2, n n2, am am2, boolean bl2) {
        this.a(0.0f, 0.0f, 0.0f, 0.0f, n2, false, am2, m2, bl2);
    }

    public void a(float f2, float f3, float f4, float f5, n n2, boolean bl2, am am2) {
        this.a(f2, f3, f4, f5, n2, bl2, am2, null, false);
    }

    public void a(float f2, float f3, float f4, float f5, n n2, boolean bl2, am am2, m m2, boolean bl3) {
        if (this.a == null || this.a.size() == 0) {
            return;
        }
        boolean bl4 = false;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        int n3 = 0;
        int n4 = 0;
        for (bq bq2 : this.a) {
            Object object;
            n n5 = n2;
            Object object2 = am2;
            float f6 = f2;
            float f7 = f3;
            float f8 = f4;
            float f9 = f5;
            if (bq2.b != null) {
                if (!(object2 instanceof y)) {
                    com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: sourceUnit!=OrderableUnit is:" + am.A((am)object2));
                    continue;
                }
                object = bq2.b.readUnit((y)am2);
                if (object == null) {
                    com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: spawnSource==null");
                    continue;
                }
                n5 = ((am)object).bX;
                object2 = object;
                f6 = ((am)object).eo;
                f7 = ((am)object).ep;
                f8 = ((am)object).eq;
                f9 = ((am)object).cg;
                if (n5 == null) {
                    com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: newSpawnSource.team==null");
                    continue;
                }
            }
            if (!bl3) {
                if (n5.w() > n5.x() + 300) {
                    bl4 = true;
                }
            } else if (n5.a(true, false) > n5.x() + 20000) {
                bl4 = true;
            }
            if (bl4) {
                object = "";
                if (object2 != null) {
                    object = (String)object + "source:" + ((am)object2).cB();
                }
                com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: Skipping, too many units already on team:" + n5.k + " count:" + n5.w() + " " + (String)object);
                if (!com.corrodinggames.rts.gameFramework.l.B().bl) continue;
                n5.W();
                continue;
            }
            if (n5.s() > n5.x() + 25000) {
                object = "";
                if (object2 != null) {
                    object = (String)object + "source:" + ((am)object2).cB();
                }
                com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: Failsafe, too many units already on team (including ignored):" + n5.k + " total count:" + n5.s() + " " + (String)object);
                if (!com.corrodinggames.rts.gameFramework.l.B().bl) continue;
                n5.W();
                continue;
            }
            object = bq2.a.c();
            if (object == null) continue;
            for (int i2 = 0; i2 < bq2.d; ++i2) {
                am am3;
                float f10;
                n n6 = n5;
                if (bq2.h < 1.0f && (f10 = f.a((am)object2, 0.0f, 1.0f, ++n4)) > bq2.h) continue;
                if (bq2.g) {
                    if (object2 == null || ((am)object2).bt == null) continue;
                    n6 = ((am)object2).bt.bX;
                    if (n6 == null) {
                        throw new RuntimeException("setToTeamOfLastAttacker targetTeam==null");
                    }
                }
                if (n3 >= bq2.i) continue;
                am am4 = object.a();
                if (bq2.e) {
                    n6 = n.i;
                }
                if (bq2.f) {
                    n6 = n.h;
                }
                if (n6 == null) {
                    throw new RuntimeException("Team==null");
                }
                am4.f(n6);
                am4.B((am)object2);
                am4.eo = f6;
                am4.ep = f7;
                am4.eq = f8;
                if (!am4.bI() && !bq2.n) {
                    am4.cg = f9;
                }
                am4.eq += bq2.q;
                if (bq2.m != -1 && am4 instanceof y) {
                    ((y)am4).a(bq2.m);
                }
                float f11 = bq2.r;
                if (bq2.u != 0.0f) {
                    f11 += f.a((am)object2, -bq2.u, bq2.u, n4 * 4 + 3);
                }
                if (f11 != 0.0f) {
                    if (am4 instanceof y) {
                        ((y)am4).i(f11);
                    } else {
                        am4.cg += f11;
                    }
                }
                am4.eo += (float)i2;
                if (bq2.s != 0.0f) {
                    am4.eo += f.a((am)object2, -bq2.s, bq2.s, n4 * 2 + 1);
                }
                if (bq2.t != 0.0f) {
                    am4.ep += f.a((am)object2, -bq2.t, bq2.t, n4 * 3 + 2);
                }
                if (bq2.j) {
                    l2.bL.b(am4.eo, am4.ep);
                    am4.eo = l2.bL.T;
                    am4.ep = l2.bL.U;
                    am4.eo += am4.cZ();
                    am4.ep += am4.da();
                }
                am4.eo += bq2.o;
                am4.ep += bq2.p;
                ++n3;
                if (bq2.k && am4 instanceof y && !((y)am4).c((n)null)) {
                    am4.ci();
                    continue;
                }
                if (bq2.l && am4 instanceof y) {
                    am4.dc();
                }
                if (bq2.v != null) {
                    bq2.v.h(am4);
                }
                if (bq2.w > 0 && object2 != null && object2 instanceof j) {
                    am3 = (j)object2;
                    if (((j)am3).B != null) {
                        for (int i3 = bq2.w; i3 > 0; --i3) {
                            am am5;
                            int n7 = -1;
                            for (int i4 = ((j)am3).B.size() - 1; i4 >= 0; --i4) {
                                am5 = (am)((j)am3).B.get(i4);
                                if (!am4.c(am5, true)) continue;
                                n7 = i4;
                                break;
                            }
                            if (n7 == -1) break;
                            am am6 = (am)((j)am3).B.remove(n7);
                            com.corrodinggames.rts.gameFramework.utility.y.a(am6, (y)am3);
                            ((j)am3).D(am6);
                            am6.eo = am4.eo;
                            am6.ep = am4.ep;
                            am6.cg = am4.cg;
                            if (am6 instanceof y) {
                                am5 = (y)am6;
                                ((y)am5).az();
                            }
                            if (am4.e(am6, true)) continue;
                            com.corrodinggames.rts.gameFramework.l.b("transportedUnitsToTransfer failed for: " + am6.cB() + " to: " + am4.cB());
                            am6.ci();
                        }
                    }
                }
                n.c(am4);
                if (am4.bI() && am4 instanceof y) {
                    l2.bU.a((y)am4);
                }
                if (bl2 && !am4.u()) {
                    com.corrodinggames.rts.gameFramework.l.B().bS.k(am4);
                }
                if (bq2.c != null) {
                    if (!(am4 instanceof y)) {
                        com.corrodinggames.rts.gameFramework.l.b("copyWaypointsFrom: spawnedUnit!=OrderableUnit is:" + am.A((am)object2));
                    } else {
                        am3 = bq2.c.readUnit((y)am2);
                        if (am3 != null) {
                            if (!(am3 instanceof y)) {
                                com.corrodinggames.rts.gameFramework.l.b("copyWaypointsFrom: copyWaypointsFrom!=OrderableUnit is:" + am.A((am)object2));
                            } else {
                                y.a((y)am3, (y)am4);
                            }
                        }
                    }
                }
                if (m2 == null) continue;
                m2.add(am4);
            }
        }
    }

    @Deprecated
    public static bp a(k k2, boolean bl2) {
        int n2 = k2.f();
        if (bl2 && n2 == 0) {
            return null;
        }
        bp bp2 = new bp();
        for (int i2 = 0; i2 < n2; ++i2) {
            boolean bl3;
            bq bq2 = new bq(null);
            as as2 = k2.q();
            if (as2 != null) {
                if (bp2.a == null) {
                    bp2.a = new m();
                }
                bq2.a = l.a(as2);
            }
            if (k2.b() >= 75 && (bl3 = k2.e())) {
                bq2.d = k2.f();
                bq2.e = k2.e();
                bq2.g = k2.e();
                if (k2.b() >= 76) {
                    bq2.h = k2.g();
                }
            }
            if (as2 == null) continue;
            bp2.a.add(bq2);
        }
        return bp2;
    }
}
