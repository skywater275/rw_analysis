/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.o;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.b;
import com.corrodinggames.rts.game.units.custom.a.a.a;
import com.corrodinggames.rts.game.units.custom.a.a.g;
import com.corrodinggames.rts.game.units.custom.a.a.h;
import com.corrodinggames.rts.game.units.custom.a.a.i;
import com.corrodinggames.rts.game.units.custom.a.a.j;
import com.corrodinggames.rts.game.units.custom.a.a.k;
import com.corrodinggames.rts.game.units.custom.a.a.l;
import com.corrodinggames.rts.game.units.custom.a.a.m;
import com.corrodinggames.rts.game.units.custom.a.c;
import com.corrodinggames.rts.game.units.custom.a.f;
import com.corrodinggames.rts.game.units.custom.aa;
import com.corrodinggames.rts.game.units.custom.ad;
import com.corrodinggames.rts.game.units.custom.ae;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.ah;
import com.corrodinggames.rts.game.units.custom.ai;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.e.d;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.p;
import com.corrodinggames.rts.game.units.custom.q;
import com.corrodinggames.rts.game.units.custom.r;
import com.corrodinggames.rts.game.units.custom.s;
import com.corrodinggames.rts.game.units.custom.t;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.utility.ac;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class ag {
    static boolean a = false;
    static int b;
    static int c;
    public static int d;
    static com.corrodinggames.rts.gameFramework.i.b e;
    static boolean f;
    public static HashMap g;
    public static HashMap h;
    static int i;
    static int j;
    static boolean k;
    static int l;
    public static com.corrodinggames.rts.gameFramework.utility.m m;
    static HashMap n;
    static final Object o;
    public static float p;
    public static float q;
    static com.corrodinggames.rts.gameFramework.i.b r;
    static String s;

    public static void a(int n) {
        if (e != null) {
            ag.e.G += (long)n;
        }
    }

    public static void a() {
        ag.i();
        ag.j();
    }

    public static void a(com.corrodinggames.rts.gameFramework.m.e e2) {
        if (e2 != null && !e2.v) {
            if (com.corrodinggames.rts.gameFramework.l.az() && e2 instanceof com.corrodinggames.rts.gameFramework.m.h) {
                return;
            }
            e2.v = true;
            ag.a(e2.u());
        }
    }

    public static void a(com.corrodinggames.rts.gameFramework.m.e[] eArray) {
        if (eArray != null) {
            com.corrodinggames.rts.gameFramework.m.e e2 = null;
            for (com.corrodinggames.rts.gameFramework.m.e e3 : eArray) {
                if (e3 != e2) {
                    ag.a(e3);
                }
                if (e2 != null) continue;
                e2 = e3;
            }
        }
    }

    public static void a(com.corrodinggames.rts.gameFramework.a.i i2) {
        if (!i2.g) {
            i2.g = true;
            if (e != null) {
                ag.e.H += (long)i2.a();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean a(com.corrodinggames.rts.gameFramework.utility.m m2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        ArrayList arrayList = new ArrayList(com.corrodinggames.rts.game.units.custom.l.c);
        ArrayList arrayList2 = new ArrayList(com.corrodinggames.rts.game.units.custom.l.d);
        com.corrodinggames.rts.gameFramework.utility.m m3 = new com.corrodinggames.rts.gameFramework.utility.m();
        String string = null;
        for (com.corrodinggames.rts.game.units.custom.l l3 : m2) {
            com.corrodinggames.rts.game.units.custom.l l4 = ag.a(l3);
            if (l4 == null) {
                com.corrodinggames.rts.gameFramework.l.e("Failed to apply changes to unit type: " + l3.M);
                bl2 = true;
                if (string != null || s == null) continue;
                string = s;
                continue;
            }
            com.corrodinggames.rts.gameFramework.l.e("Changes applied to unit type: " + l3.M);
            bl3 = true;
            m3.add(l4);
        }
        if (string != null && com.corrodinggames.rts.gameFramework.l.at()) {
            l2.c("Unit errors", string);
        }
        if (bl3 && !ag.c(false)) {
            bl2 = true;
        }
        if (bl3 && !bl2) {
            com.corrodinggames.rts.game.units.custom.l.e = null;
            ag.e();
            s = null;
            com.corrodinggames.rts.game.n.P();
            com.corrodinggames.rts.gameFramework.f.g.K();
            bl4 = true;
            if (!bl2) {
                for (com.corrodinggames.rts.game.units.custom.l l3 : m3) {
                    if (l3.gt.size() <= 0) continue;
                    l2.a(l3.gt.size() + " Warning(s) loading: " + l3.b() + " \n" + (String)l3.gt.get(0), 1);
                    l3.gt.clear();
                    bl4 = false;
                    break;
                }
            }
        }
        if (bl2) {
            com.corrodinggames.rts.gameFramework.l.e("Failed to load some units, keeping old config");
            ArrayList arrayList3 = com.corrodinggames.rts.game.units.custom.l.c;
            synchronized (arrayList3) {
                com.corrodinggames.rts.game.units.custom.l.c.clear();
                com.corrodinggames.rts.game.units.custom.l.c.addAll(arrayList);
            }
            com.corrodinggames.rts.game.units.custom.l.d = arrayList2;
        }
        return bl4;
    }

    public static void b() {
        com.corrodinggames.rts.gameFramework.utility.m m2 = new com.corrodinggames.rts.gameFramework.utility.m();
        b = 0;
        c = 0;
        d = 0;
        for (am am2 : am.bF()) {
            com.corrodinggames.rts.game.units.as as2 = am2.r();
            if (!(as2 instanceof com.corrodinggames.rts.game.units.custom.l) || m2.contains(as2)) continue;
            m2.add((com.corrodinggames.rts.game.units.custom.l)as2);
        }
        if (m2.size() > 0) {
            boolean bl2 = ag.a(m2);
        }
    }

    public static void c() {
        boolean bl2 = false;
        com.corrodinggames.rts.gameFramework.utility.m m2 = new com.corrodinggames.rts.gameFramework.utility.m();
        for (com.corrodinggames.rts.game.units.custom.l l2 : com.corrodinggames.rts.game.units.custom.l.c) {
            boolean bl3 = false;
            for (aa aa2 : l2.k) {
                long l3 = aa2.a(false);
                if (l3 == aa2.a) continue;
                bl3 = true;
                aa2.a = l3;
            }
            if (!bl3) continue;
            if (!bl2) {
                com.corrodinggames.rts.gameFramework.l.e("Detected unit changes");
                bl2 = true;
            }
            m2.add(l2);
        }
        if (m2.size() > 0) {
            ag.a(m2);
        }
    }

    public static void d() {
        if (com.corrodinggames.rts.game.units.custom.l.e != null) {
            com.corrodinggames.rts.gameFramework.l.e("applyPendingNetworkUnits: Applying new network units from server (" + com.corrodinggames.rts.game.units.custom.l.e.size() + " units)");
            com.corrodinggames.rts.game.units.custom.l.d = com.corrodinggames.rts.game.units.custom.l.e;
            com.corrodinggames.rts.game.units.custom.l.e = null;
            ag.e();
        } else {
            com.corrodinggames.rts.gameFramework.l.e("applyPendingNetworkUnits: no server units list found");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList a(boolean bl2) {
        ArrayList<com.corrodinggames.rts.game.units.custom.l> arrayList = new ArrayList<com.corrodinggames.rts.game.units.custom.l>();
        ArrayList arrayList2 = com.corrodinggames.rts.game.units.custom.l.c;
        synchronized (arrayList2) {
            for (com.corrodinggames.rts.game.units.custom.l l2 : com.corrodinggames.rts.game.units.custom.l.c) {
                if (l2.J != null && (!l2.J.m() || !bl2)) continue;
                arrayList.add(l2);
            }
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static com.corrodinggames.rts.gameFramework.utility.ab a(String string) {
        HashMap hashMap = n;
        synchronized (hashMap) {
            com.corrodinggames.rts.gameFramework.utility.ab ab2;
            com.corrodinggames.rts.gameFramework.utility.ab ab3 = (com.corrodinggames.rts.gameFramework.utility.ab)n.get(string);
            if (ab3 != null) {
                return ab3;
            }
            com.corrodinggames.rts.gameFramework.utility.j j2 = ag.b(string);
            if (j2 == null) {
                return null;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(j2);
            try {
                ab2 = new com.corrodinggames.rts.gameFramework.utility.ab(bufferedInputStream, string);
                ab2.a();
                ab2.f = j2.d();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                throw new bo("Load of '" + string + "' failed: " + iOException.getMessage());
            }
            n.put(string, ab2);
            return ab2;
        }
    }

    public static void a(com.corrodinggames.rts.game.units.custom.l l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, boolean bl2) {
        com.corrodinggames.rts.gameFramework.utility.ab ab3 = ag.a(string);
        if (ab3 == null) {
            if (bl2) {
                return;
            }
            throw new bo("[" + string2 + "] Could not find conf target:" + string);
        }
        l2.o(ab3.f);
        ab2.a(ab3);
        ag.a(l2, ab2, ab3, string, 1);
    }

    public static void a(com.corrodinggames.rts.game.units.custom.l l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, com.corrodinggames.rts.gameFramework.utility.ab ab3, String string, int n2) {
        if (n2 > 10) {
            throw new bo("copyFrom can only be 10 levels deep, maybe you have a loop?");
        }
        String string2 = ab3.b("core", "copyFrom", (String)null);
        if (string2 != null) {
            String[] stringArray = string2.split(",");
            Collections.reverse(Arrays.asList(stringArray));
            for (String string3 : stringArray) {
                String string4;
                Object object;
                if ((string3 = string3.trim()).equals("")) continue;
                if (string3.contains("..")) {
                    throw new bo("'..' not supported in copyFrom");
                }
                if (string3.startsWith("ROOT:")) {
                    string3 = string3.substring("ROOT:".length());
                    object = l2.J == null ? "units/common.ini" : l2.J.q + "/common.ini";
                    string4 = ag.a(com.corrodinggames.rts.gameFramework.f.h((String)object), string3);
                } else if (string3.startsWith("CORE:")) {
                    string3 = string3.substring("CORE:".length());
                    object = "units/common.ini";
                    string4 = ag.a(com.corrodinggames.rts.gameFramework.f.h((String)object), string3);
                } else {
                    string4 = ag.a(com.corrodinggames.rts.gameFramework.f.h(string), string3);
                }
                object = ag.a(string4);
                if (object == null) {
                    String string5 = "Could not find copyFrom target:" + string4;
                    if (n2 != 0) {
                        string5 = string5 + " (while loading: " + string + ")";
                    }
                    throw new bo(string5);
                }
                l2.o(((com.corrodinggames.rts.gameFramework.utility.ab)object).f);
                ab2.a((com.corrodinggames.rts.gameFramework.utility.ab)object);
                ag.a(l2, ab2, (com.corrodinggames.rts.gameFramework.utility.ab)object, string4, n2 + 1);
            }
        }
    }

    public static void a(com.corrodinggames.rts.game.units.custom.l l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, int n2) {
        if (n2 > 10) {
            throw new bo("@copyFromSection can only be 10 levels deep, maybe you have a loop?");
        }
        String string3 = ab2.b(string2, "@copyFromSection", (String)null);
        if (string3 == null || string3.equals("")) {
            return;
        }
        String[] stringArray = string3.split(",");
        Collections.reverse(Arrays.asList(stringArray));
        for (String string4 : stringArray) {
            if ((string4 = string4.trim()).equals("")) continue;
            com.corrodinggames.rts.gameFramework.utility.m m2 = ab2.k(string4, "");
            if (m2.size() == 0) {
                throw new bo("[" + string2 + "]@copyFromSection: Could not find keys in target section: " + string4);
            }
            for (String string5 : m2) {
                String string6 = ab2.b(string4, string5);
                if (string6 == null) continue;
                ab2.d(string, string5, string6);
            }
            ag.a(l2, ab2, string, string4, n2 + 1);
        }
    }

    public static bb a(com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, String string3) {
        return ab2.a(string, string2, string3, false);
    }

    public static aj a(com.corrodinggames.rts.game.units.custom.l l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, String string3) {
        return ab2.a(l2, string, string2, string3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static com.corrodinggames.rts.game.units.custom.l a(com.corrodinggames.rts.game.units.custom.l l2) {
        Cloneable cloneable;
        String string;
        com.corrodinggames.rts.game.units.custom.l l3;
        block14: {
            String string2 = l2.D;
            com.corrodinggames.rts.gameFramework.l l4 = com.corrodinggames.rts.gameFramework.l.B();
            l3 = null;
            string = null;
            if (l2.J != null) {
                string = l2.J.R;
            }
            cloneable = n;
            synchronized (cloneable) {
                n.clear();
            }
            s = null;
            try {
                l3 = ag.a(string2, l2.J, l2.K, l2.L);
            }
            catch (RuntimeException runtimeException) {
                runtimeException.printStackTrace();
                if (s != null) break block14;
                String string3 = "Error loading unit:" + ag.a(l2.J, string2, true) + "\n" + runtimeException.getMessage();
                l4.a(string3, 1);
            }
        }
        if (l3 == null && l2.J != null) {
            l2.J.R = string;
        }
        if (l3 != null) {
            cloneable = com.corrodinggames.rts.game.units.custom.l.c;
            synchronized (cloneable) {
                com.corrodinggames.rts.game.units.custom.l.c.remove(l2);
            }
            ag.a((com.corrodinggames.rts.game.units.as)l2, l3, true);
            if (com.corrodinggames.rts.game.units.custom.l.d.remove(l2)) {
                com.corrodinggames.rts.game.units.custom.l.d.add(l3);
                if (l2.H != l3.H) {
                    ++d;
                }
            } else {
                com.corrodinggames.rts.gameFramework.l.e("Changed unit was not enabled (original not found in customUnitTypes)");
            }
            com.corrodinggames.rts.game.n.P();
            com.corrodinggames.rts.gameFramework.f.g.K();
        }
        return l3;
    }

    public static void a(com.corrodinggames.rts.game.units.as as2, com.corrodinggames.rts.game.units.custom.l l2, boolean bl2) {
        for (am am2 : am.bF()) {
            if (!(am2 instanceof com.corrodinggames.rts.game.units.custom.j)) continue;
            com.corrodinggames.rts.game.units.custom.j j2 = (com.corrodinggames.rts.game.units.custom.j)am2;
            if (j2.x == as2) {
                com.corrodinggames.rts.game.n.b((am)j2);
                j2.a(l2, false, bl2);
                j2.S();
                if (j2.dg() != null) {
                    j2.dg().a(l2);
                }
                com.corrodinggames.rts.game.n.c(j2);
            }
            if (j2.z != as2) continue;
            j2.z = l2;
        }
    }

    public static String a(ArrayList arrayList) {
        Integer n2;
        HashMap<com.corrodinggames.rts.gameFramework.i.b, Integer> hashMap = new HashMap<com.corrodinggames.rts.gameFramework.i.b, Integer>();
        for (Object object : arrayList) {
            com.corrodinggames.rts.gameFramework.i.b b2 = ((com.corrodinggames.rts.game.units.custom.l)object).J;
            if (b2 == null) continue;
            n2 = (Integer)hashMap.get(b2);
            n2 = n2 == null ? Integer.valueOf(1) : Integer.valueOf(n2 + 1);
            hashMap.put(b2, n2);
        }
        Object object = "";
        for (com.corrodinggames.rts.gameFramework.i.b b2 : hashMap.keySet()) {
            n2 = (Integer)hashMap.get(b2);
            object = (String)object + b2.a() + "(unitCount: " + n2 + (b2.m() ? "" : "[disabled]") + "), ";
        }
        return object;
    }

    public static String b(boolean bl2) {
        ArrayList arrayList = ag.a(bl2);
        com.corrodinggames.rts.game.units.custom.l.e = null;
        com.corrodinggames.rts.game.units.custom.l.d = arrayList;
        s = null;
        com.corrodinggames.rts.gameFramework.l.e("enableAll: " + ag.a(com.corrodinggames.rts.game.units.custom.l.d));
        ag.e();
        return s;
    }

    public static boolean c(boolean bl2) {
        ArrayList arrayList = com.corrodinggames.rts.game.units.custom.l.d;
        ArrayList arrayList2 = bl2 ? ag.a(true) : com.corrodinggames.rts.game.units.custom.l.d;
        boolean bl3 = true;
        s = null;
        com.corrodinggames.rts.game.units.custom.l.d = arrayList2;
        ag.g();
        if (s != null) {
            bl3 = false;
        }
        com.corrodinggames.rts.game.units.custom.l.d = arrayList;
        ag.g();
        return bl3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void e() {
        Object object = o;
        synchronized (object) {
            ag.n();
        }
    }

    private static void n() {
        com.corrodinggames.rts.game.units.custom.l l2 = null;
        ArrayList<Object> arrayList = new ArrayList<Object>();
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        if (l3.as()) {
            for (ar ar2 : ar.values()) {
                arrayList.add(ar2);
            }
        }
        for (com.corrodinggames.rts.game.units.custom.l l4 : com.corrodinggames.rts.game.units.custom.l.d) {
            arrayList.add(l4);
            if (!l4.M.equals("missing") || l4.J != null) continue;
            l2 = l4;
        }
        ar.ae = arrayList;
        am.bL();
        ag.g();
        ag.f();
        com.corrodinggames.rts.game.units.custom.e.a.e();
        if (l2 == null) {
            com.corrodinggames.rts.gameFramework.l.e("missingPlaceHolder is not an active unit, searching for new target");
            for (com.corrodinggames.rts.game.units.custom.l l5 : com.corrodinggames.rts.game.units.custom.l.d) {
                if (!l5.M.equals("missing")) continue;
                com.corrodinggames.rts.gameFramework.l.e("Found a missing placeholder");
                l2 = l5;
            }
        }
        com.corrodinggames.rts.game.units.custom.l.b = l2;
    }

    public static void f() {
        float f2 = 50.0f;
        float f3 = 50.0f;
        for (com.corrodinggames.rts.game.units.custom.l l2 : com.corrodinggames.rts.game.units.custom.l.d) {
            float f4 = l2.cW;
            if (f4 > 250.0f) {
                f4 = 250.0f;
            }
            if (f2 < f4) {
                f2 = f4;
            }
            if (!l2.aH || !(f3 < f4)) continue;
            f3 = f4;
        }
        p = f2;
        q = f3;
    }

    public static com.corrodinggames.rts.gameFramework.utility.j b(String string) {
        String string2 = "" + string;
        return com.corrodinggames.rts.gameFramework.e.a.k(string2);
    }

    public static void b(ArrayList arrayList) {
        Collections.sort(arrayList);
    }

    public static void a(com.corrodinggames.rts.game.units.as as2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        try {
            Object object;
            Object object22;
            Object object3;
            as2.h();
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                object3 = (com.corrodinggames.rts.game.units.custom.l)as2;
                if (((com.corrodinggames.rts.game.units.custom.l)object3).fI != null) {
                    Object object4 = com.corrodinggames.rts.game.units.custom.l.s(((com.corrodinggames.rts.game.units.custom.l)object3).fI);
                    if (object4 == null) {
                        throw new bo("Could not find [ai]upgradedFrom target:" + ((com.corrodinggames.rts.game.units.custom.l)object3).fI);
                    }
                    ((com.corrodinggames.rts.game.units.custom.l)object3).b((com.corrodinggames.rts.game.units.as)object4);
                }
                for (Object object22 : ((com.corrodinggames.rts.game.units.custom.l)object3).p) {
                    ((v)object22).a();
                }
                if (((com.corrodinggames.rts.game.units.custom.l)object3).eH) {
                    com.corrodinggames.rts.game.units.custom.l.g.add(object3);
                }
            }
            for (Object object4 : com.corrodinggames.rts.game.units.custom.l.d) {
                if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                    object22 = (com.corrodinggames.rts.game.units.custom.l)as2;
                    if (((com.corrodinggames.rts.game.units.custom.l)object4).fI != null && ((com.corrodinggames.rts.game.units.custom.l)object4).fI.equalsIgnoreCase(((com.corrodinggames.rts.game.units.custom.l)object22).i())) {
                        ((com.corrodinggames.rts.game.units.custom.l)object22).b((com.corrodinggames.rts.game.units.as)object4);
                    }
                }
                for (p p2 : ((com.corrodinggames.rts.game.units.custom.l)object4).gg) {
                    if (!p2.a.equalsIgnoreCase(as2.i())) continue;
                    p2.e = true;
                    int n2 = ((com.corrodinggames.rts.game.units.custom.l)object4).cl;
                    boolean bl2 = false;
                    for (int i2 = n2; i2 <= 3; ++i2) {
                        Object object5;
                        boolean bl3;
                        object = as2.a(i2);
                        com.corrodinggames.rts.game.units.a.s s2 = ((com.corrodinggames.rts.game.units.custom.l)object4).aH || p2.c ? new com.corrodinggames.rts.game.units.a.v((com.corrodinggames.rts.game.units.as)object4) : new com.corrodinggames.rts.game.units.a.l((com.corrodinggames.rts.game.units.as)object4);
                        if (p2.b != -999.0f) {
                            s2.g = p2.b;
                        }
                        if (p2.f != null) {
                            bl3 = false;
                            if (!(as2 instanceof com.corrodinggames.rts.game.units.custom.l) && !((object5 = am.a(as2)) instanceof y)) {
                                bl3 = true;
                            }
                            if (!bl3) {
                                s2.h = com.corrodinggames.rts.game.units.custom.a.c.a(p2);
                            } else if (!bl2) {
                                bl2 = true;
                                ((com.corrodinggames.rts.game.units.custom.l)object4).r("builtFrom isLocked currently cannot be used when targeting old-style unit:" + as2.i());
                            }
                        }
                        bl3 = false;
                        object5 = ((ArrayList)object).iterator();
                        while (object5.hasNext()) {
                            com.corrodinggames.rts.game.units.a.s s3 = (com.corrodinggames.rts.game.units.a.s)object5.next();
                            if (!s2.equals(s3)) continue;
                            bl3 = true;
                        }
                        if (!bl3) {
                            ((ArrayList)object).add(s2);
                        }
                        ag.b((ArrayList)object);
                    }
                }
            }
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                object3 = (com.corrodinggames.rts.game.units.custom.l)as2;
                for (Object object22 : ((com.corrodinggames.rts.game.units.custom.l)object3).gh) {
                    int n3;
                    if (((com.corrodinggames.rts.game.units.custom.a.d)object22).k != null && ((com.corrodinggames.rts.game.units.custom.a.d)object22).k.equalsIgnoreCase("setRally")) {
                        for (int i3 = 1; i3 <= 3; ++i3) {
                            ArrayList arrayList = as2.a(i3);
                            com.corrodinggames.rts.game.units.a.o o2 = new com.corrodinggames.rts.game.units.a.o();
                            if (((com.corrodinggames.rts.game.units.custom.a.d)object22).p != -999.0f) {
                                o2.g = ((com.corrodinggames.rts.game.units.custom.a.d)object22).p;
                            }
                            arrayList.add(o2);
                            ((com.corrodinggames.rts.game.units.custom.l)object3).dc = true;
                            ag.b(arrayList);
                        }
                        continue;
                    }
                    if (((com.corrodinggames.rts.game.units.custom.a.d)object22).k != null && ((com.corrodinggames.rts.game.units.custom.a.d)object22).k.equalsIgnoreCase("reclaim")) {
                        for (int i4 = 1; i4 <= 3; ++i4) {
                            ArrayList arrayList = as2.a(i4);
                            com.corrodinggames.rts.game.units.a.m m2 = new com.corrodinggames.rts.game.units.a.m(true);
                            if (((com.corrodinggames.rts.game.units.custom.a.d)object22).p != -999.0f) {
                                m2.g = ((com.corrodinggames.rts.game.units.custom.a.d)object22).p;
                            }
                            arrayList.add(m2);
                            ag.b(arrayList);
                        }
                        continue;
                    }
                    if (((com.corrodinggames.rts.game.units.custom.a.d)object22).k != null && ((com.corrodinggames.rts.game.units.custom.a.d)object22).k.equalsIgnoreCase("repair")) {
                        for (int i5 = 1; i5 <= 3; ++i5) {
                            ArrayList arrayList = as2.a(i5);
                            com.corrodinggames.rts.game.units.a.n n4 = new com.corrodinggames.rts.game.units.a.n();
                            if (((com.corrodinggames.rts.game.units.custom.a.d)object22).p != -999.0f) {
                                n4.g = ((com.corrodinggames.rts.game.units.custom.a.d)object22).p;
                            }
                            arrayList.add(n4);
                            ag.b(arrayList);
                        }
                        continue;
                    }
                    com.corrodinggames.rts.game.units.as as3 = null;
                    if (((com.corrodinggames.rts.game.units.custom.a.d)object22).k != null) {
                        as3 = ar.a(((com.corrodinggames.rts.game.units.custom.a.d)object22).k);
                        if (as3 == null) {
                            throw new bo("Could not find canBuild target:" + ((com.corrodinggames.rts.game.units.custom.a.d)object22).k);
                        }
                    } else if (((com.corrodinggames.rts.game.units.custom.a.d)object22).aM != com.corrodinggames.rts.game.units.custom.a.f.b) {
                        throw new bo("'Target' required for action:" + ((com.corrodinggames.rts.game.units.custom.a.d)object22).a());
                    }
                    for (int i6 = n3 = 1; i6 <= 3; ++i6) {
                        ArrayList arrayList = as2.a(i6);
                        if (((com.corrodinggames.rts.game.units.custom.a.d)object22).aM == com.corrodinggames.rts.game.units.custom.a.f.a) {
                            if (as3.j() || ((com.corrodinggames.rts.game.units.custom.a.d)object22).aK) {
                                object = new com.corrodinggames.rts.game.units.a.v(as3, ((com.corrodinggames.rts.game.units.custom.a.d)object22).aJ, null);
                                ((com.corrodinggames.rts.game.units.a.s)object).h = com.corrodinggames.rts.game.units.custom.a.c.a((com.corrodinggames.rts.game.units.custom.a.d)object22);
                            } else {
                                object = new com.corrodinggames.rts.game.units.a.l(as3);
                                ((com.corrodinggames.rts.game.units.a.s)object).h = com.corrodinggames.rts.game.units.custom.a.c.a((com.corrodinggames.rts.game.units.custom.a.d)object22);
                            }
                        } else if (((com.corrodinggames.rts.game.units.custom.a.d)object22).aM == com.corrodinggames.rts.game.units.custom.a.f.b) {
                            object = new com.corrodinggames.rts.game.units.custom.a.g((com.corrodinggames.rts.game.units.custom.a.d)object22, com.corrodinggames.rts.game.units.custom.l.a(as3));
                        } else {
                            throw new bo("Could not find actionType:" + (Object)((Object)((com.corrodinggames.rts.game.units.custom.a.d)object22).aM));
                        }
                        if (((com.corrodinggames.rts.game.units.custom.a.d)object22).p != -999.0f) {
                            ((com.corrodinggames.rts.game.units.a.s)object).g = ((com.corrodinggames.rts.game.units.custom.a.d)object22).p;
                        }
                        boolean bl4 = false;
                        for (Object object5 : arrayList) {
                            if (!((com.corrodinggames.rts.game.units.a.s)object).equals(object5)) continue;
                            bl4 = true;
                        }
                        if (!bl4) {
                            arrayList.add(object);
                        }
                        ag.b(arrayList);
                    }
                }
            }
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                object3 = (com.corrodinggames.rts.game.units.custom.l)as2;
                ((com.corrodinggames.rts.game.units.custom.l)object3).fu = false;
                for (int i7 = 1; i7 <= 3; ++i7) {
                    object22 = as2.a(i7);
                    Iterator iterator = ((ArrayList)object22).iterator();
                    while (iterator.hasNext()) {
                        com.corrodinggames.rts.game.units.a.s s4 = (com.corrodinggames.rts.game.units.a.s)iterator.next();
                        if (s4 instanceof com.corrodinggames.rts.game.units.custom.a.g || s4.i() == null) continue;
                        ((com.corrodinggames.rts.game.units.custom.l)object3).fu = true;
                    }
                }
                for (Object object22 : ((com.corrodinggames.rts.game.units.custom.l)object3).p) {
                    ((v)object22).b();
                }
            }
            boolean bl5 = l2.O() && l2.bX.ay.k;
            for (int i8 = 1; i8 <= 3; ++i8) {
                object22 = as2.a(i8);
                Iterator iterator = ((ArrayList)object22).iterator();
                while (iterator.hasNext()) {
                    com.corrodinggames.rts.game.units.a.s s5 = (com.corrodinggames.rts.game.units.a.s)iterator.next();
                    if (s5.h instanceof com.corrodinggames.rts.game.units.custom.a.b) {
                        com.corrodinggames.rts.gameFramework.l.a("=== ChainedActionConfig already on: " + as2.i() + " action:" + s5.b());
                        s5.h = ((com.corrodinggames.rts.game.units.custom.a.b)s5.h).b;
                    }
                    if (!bl5) continue;
                    com.corrodinggames.rts.game.units.custom.d.b b2 = s5.B();
                    com.corrodinggames.rts.game.units.custom.d.b b3 = s5.r_();
                    if (b2.c() || b3 != null) continue;
                    s5.h = object = new com.corrodinggames.rts.game.units.custom.a.b(s5.h);
                    ((com.corrodinggames.rts.game.units.custom.a.b)object).c = com.corrodinggames.rts.game.units.custom.d.b.a;
                    ((com.corrodinggames.rts.game.units.custom.a.b)object).d = b2;
                }
            }
        }
        catch (bo bo2) {
            ag.a(as2.i(), (Exception)bo2, as2);
        }
        catch (RuntimeException runtimeException) {
            ag.a(as2.i(), (Exception)runtimeException, as2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void g() {
        Object object = o;
        synchronized (object) {
            ag.o();
        }
    }

    private static void o() {
        String[] stringArray;
        com.corrodinggames.rts.game.units.custom.l.g.clear();
        com.corrodinggames.rts.game.units.custom.l.f.clear();
        for (com.corrodinggames.rts.game.units.custom.l l2 : com.corrodinggames.rts.game.units.custom.l.d) {
            if (l2.J != null && (stringArray = l2.J.R) != null) {
                com.corrodinggames.rts.gameFramework.l.b(l2.i() + "(mod:" + l2.t() + "): Getting setup while mod has error: " + (String)stringArray);
            }
            stringArray = l2.gg.iterator();
            while (stringArray.hasNext()) {
                p object = (p)stringArray.next();
                object.e = false;
            }
            l2.fL.clear();
        }
        for (com.corrodinggames.rts.game.units.custom.l l2 : com.corrodinggames.rts.game.units.custom.l.d) {
            try {
                if (l2.Q == null) continue;
                for (String string : stringArray = l2.Q.split(",")) {
                    boolean bl2;
                    com.corrodinggames.rts.game.units.as as2 = com.corrodinggames.rts.game.units.custom.l.a(string = string.trim(), bl2 = false);
                    if (as2 == null) {
                        throw new bo("Could not find overrideAndReplace target:" + string);
                    }
                    if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                        com.corrodinggames.rts.gameFramework.l.e("Replacing:" + as2.i() + " with " + l2.i());
                    }
                    com.corrodinggames.rts.game.units.custom.l.f.put(as2, l2);
                }
            }
            catch (bo bo2) {
                ag.a(l2.i(), (Exception)bo2, (com.corrodinggames.rts.game.units.as)l2);
            }
        }
        for (ar ar2 : ar.values()) {
            ag.a(ar2);
        }
        for (com.corrodinggames.rts.game.units.custom.l l3 : com.corrodinggames.rts.game.units.custom.l.d) {
            ag.a((com.corrodinggames.rts.game.units.as)l3);
        }
        for (com.corrodinggames.rts.game.units.custom.l l4 : com.corrodinggames.rts.game.units.custom.l.d) {
            for (p p2 : l4.gg) {
                if (p2.e) continue;
                String string = p2.d + " failed to find target:" + p2.a;
                l4.q(string);
                if (l4.R < 1) continue;
                com.corrodinggames.rts.gameFramework.l.e("Converting warning to error (meta.strictLevel=" + l4.R + ")");
                l4.p(string);
            }
            if (l4.gp == null || l4.gp.size() <= 0) continue;
            for (u u2 : l4.gp) {
                try {
                    u2.b(l4);
                }
                catch (bo bo2) {
                    ag.a(l4.i(), (Exception)bo2, (com.corrodinggames.rts.game.units.as)l4);
                }
            }
        }
        for (com.corrodinggames.rts.game.units.custom.l l5 : com.corrodinggames.rts.game.units.custom.l.d) {
            l5.r();
        }
        Collections.sort(com.corrodinggames.rts.game.units.custom.l.g, new q());
    }

    public static com.corrodinggames.rts.game.units.custom.l a(String string, com.corrodinggames.rts.gameFramework.i.b b2, String string2, String string3) {
        try {
            long l2 = br.a();
            com.corrodinggames.rts.gameFramework.utility.j j2 = ag.b(string);
            if (j2 == null) {
                throw new RuntimeException("Failed to open unit config file:" + string);
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(j2);
            ag.a(l2, ah.g);
            ++b;
            if (b2 != null) {
                ++c;
            }
            com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
            String string4 = "core units";
            if (b2 != null) {
                string4 = b2.a();
            }
            l3.h("Loading units - " + b + " (" + string4 + ")");
            com.corrodinggames.rts.game.units.custom.l l4 = ag.a(string, bufferedInputStream, j2.c(), b2, j2, string2, string3);
            long l5 = br.a();
            try {
                bufferedInputStream.close();
                j2.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            ag.a(l5, ah.h);
            return l4;
        }
        catch (RuntimeException runtimeException) {
            ag.a(string, (Exception)runtimeException, b2);
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void h() {
        Object object22;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ArrayList arrayList = l2.bZ.k();
        j = 0;
        i = 0;
        l = 0;
        k = false;
        long l3 = br.a();
        for (Object object22 : g.values()) {
            ((com.corrodinggames.rts.gameFramework.m.e)object22).v = false;
            if (((com.corrodinggames.rts.gameFramework.m.e)object22).a != null) {
                for (com.corrodinggames.rts.gameFramework.m.e e2 : ((com.corrodinggames.rts.gameFramework.m.e)object22).a) {
                    e2.v = false;
                }
            }
            if (((com.corrodinggames.rts.gameFramework.m.e)object22).b != null) {
                for (com.corrodinggames.rts.gameFramework.m.e e3 : ((com.corrodinggames.rts.gameFramework.m.e)object22).b) {
                    e3.v = false;
                }
            }
            if (((com.corrodinggames.rts.gameFramework.m.e)object22).c == null) continue;
            for (com.corrodinggames.rts.gameFramework.m.e e4 : ((com.corrodinggames.rts.gameFramework.m.e)object22).c) {
                e4.v = false;
            }
        }
        for (Object object22 : h.values()) {
            ((com.corrodinggames.rts.gameFramework.a.i)object22).g = false;
        }
        Object object4 = null;
        object22 = null;
        Object[] objectArray = null;
        try {
            object4 = new byte[8000000];
            object4[0] = l2.dZ;
            l2.ea = (byte)object4[1];
            object22 = new byte[2][];
            object22[0] = new byte[3000000];
            object22[1] = new byte[3000000];
            object22[0][0] = l2.dZ;
            object22[1][0] = l2.dZ;
            if (!com.corrodinggames.rts.gameFramework.l.at()) {
                objectArray = new ByteBuffer[]{ByteBuffer.allocateDirect(5000000), ByteBuffer.allocateDirect(5000000), ByteBuffer.allocateDirect(5000000), ByteBuffer.allocateDirect(5000000)};
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
            com.corrodinggames.rts.gameFramework.l.e("Failed to reserve memory pre-mod load");
        }
        Object object5 = com.corrodinggames.rts.game.units.custom.l.c;
        synchronized (object5) {
            com.corrodinggames.rts.game.units.custom.l.c.clear();
        }
        com.corrodinggames.rts.game.units.custom.l.d.clear();
        com.corrodinggames.rts.game.units.custom.l.e = null;
        com.corrodinggames.rts.game.units.custom.l.f.clear();
        l2.bZ.n();
        b = 0;
        c = 0;
        object5 = n;
        synchronized (object5) {
            n.clear();
        }
        ag.a(com.corrodinggames.rts.gameFramework.e.a.p("units"), 1, false, null, com.corrodinggames.rts.gameFramework.e.a.p("units"), null);
        if (!com.corrodinggames.rts.gameFramework.l.aJ && !l2.ar) {
            String string;
            object5 = ag.m();
            if (!com.corrodinggames.rts.gameFramework.e.a.f((String)object5)) {
                com.corrodinggames.rts.gameFramework.l.e("Modded Custom '" + (String)object5 + "' directory not found");
            }
            for (com.corrodinggames.rts.gameFramework.i.b b2 : arrayList) {
                if (b2.y || b2.q == null) continue;
                string = b2.j();
                if (b2.m) {
                    string = com.corrodinggames.rts.gameFramework.e.a.p(string);
                }
                if (b2.f) {
                    com.corrodinggames.rts.gameFramework.l.e("Disabled mod at:" + string + " (name:" + b2.a() + ")");
                } else {
                    com.corrodinggames.rts.gameFramework.l.e("Loading mod at:" + string + " (name:" + b2.a() + ")");
                }
                ag.a(string, 2, true, b2, string, null);
            }
            for (com.corrodinggames.rts.gameFramework.i.b b3 : arrayList) {
                if (!b3.y || b3.q == null) continue;
                string = b3.i();
                if (b3.f) {
                    com.corrodinggames.rts.gameFramework.l.e("Disabled workshop mod at:" + string + " (name:" + b3.a() + ")");
                } else {
                    com.corrodinggames.rts.gameFramework.l.e("Loading workshop mod at:" + string + " (name:" + b3.a() + ")");
                }
                ag.a(string, 2, true, b3, string, null);
            }
        }
        ag.a();
        ag.b(true);
        com.corrodinggames.rts.gameFramework.l.e("Done loading custom units. image cacheHits:" + j + " image cacheMisses:" + i + " (in: " + br.a(l3) + "ms)");
        com.corrodinggames.rts.gameFramework.l.e("========= Mods data loaded ===========");
        com.corrodinggames.rts.gameFramework.l.e("Number of mods:" + arrayList.size());
        for (com.corrodinggames.rts.gameFramework.i.b b2 : arrayList) {
            b2.t();
        }
        com.corrodinggames.rts.gameFramework.l.e("================================");
        if (objectArray != null) {
            objectArray[0] = null;
            objectArray[1] = null;
            objectArray[2] = null;
            objectArray[3] = null;
            objectArray = null;
        }
        if (object22 != null) {
            object22[0] = null;
            object22[1] = null;
            object22 = null;
        }
        if (object4 != null) {
            object4[1] = l2.dZ;
            l2.ea = (byte)object4[1];
            object4 = null;
            System.gc();
            System.gc();
        }
    }

    public static void a(String string, int n2, boolean bl2, com.corrodinggames.rts.gameFramework.i.b b2, String string2, String string3) {
        boolean bl3 = bl2 && n2 == 1;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (b2 != null) {
            if (b2.f && !l2.bQ.loadDisabledModData) {
                b2.C = true;
                return;
            }
            b2.C = false;
        }
        if (b2 != null && b2.f) {
            com.corrodinggames.rts.gameFramework.l.e("Note: Loading disabled mod: " + string);
        }
        com.corrodinggames.rts.gameFramework.e.a.c();
        String[] stringArray = com.corrodinggames.rts.gameFramework.e.a.h(string);
        if (stringArray == null) {
            String string4 = com.corrodinggames.rts.gameFramework.e.a.c();
            com.corrodinggames.rts.gameFramework.l.b("readAllCustomUnitConfigs: ERROR");
            com.corrodinggames.rts.gameFramework.l.b("readAllCustomUnitConfigs: Failed to load:" + string);
            if (b2 != null) {
                if (!b2.D) {
                    b2.R = string4 == null ? "Failed to list directory, check file permissions" : "Failed to list directory: " + string4;
                } else {
                    b2.S = "Failed to list subdirectory: '" + string + "' check file permissions";
                    if (string4 != null) {
                        b2.S = b2.S + ": " + string4;
                    }
                }
            }
            return;
        }
        if (b2 != null) {
            b2.D = true;
        }
        if (!bl3) {
            for (String string5 : stringArray) {
                if (!string5.equalsIgnoreCase("all-units.template")) continue;
                string3 = string;
            }
        }
        for (String string5 : stringArray) {
            String string6;
            if (string5.equals("custom_units_here.txt") || string5.equals("mods_here_will_be_enabled_by_default.txt") || string5.equals("__MACOSX")) continue;
            boolean bl4 = false;
            com.corrodinggames.rts.gameFramework.i.b b3 = b2;
            if (bl2 && n2 == 1 && b3 == null) {
                b3 = l2.bZ.e(string5);
                if (b3 == null) {
                    com.corrodinggames.rts.gameFramework.l.b("readAllCustomUnitConfigs: Could not find linked mod:" + string5);
                    b3 = l2.bZ.c;
                }
                bl4 = true;
            }
            if (string5.toLowerCase(Locale.ENGLISH).endsWith(".ini") && !bl3) {
                string6 = string + "/" + string5;
                if (r != b3 && b3 != null) {
                    r = b3;
                    ag.a();
                    com.corrodinggames.rts.gameFramework.l.e("Loading units from mod: " + b3.c);
                }
                if (string5.equalsIgnoreCase("desktop.ini")) {
                    com.corrodinggames.rts.gameFramework.l.e("Skipping possible system file: " + string6);
                    continue;
                }
                long l3 = br.a();
                ag.a(string6, b3, string2, string3);
                ag.a(l3, ah.f);
                continue;
            }
            if (string5.toLowerCase(Locale.ENGLISH).endsWith(".tmx")) {
                string6 = string + "/" + string5;
                com.corrodinggames.rts.gameFramework.l.e("Found map: " + string6);
                if (b3 != null && b3.B) {
                    l2.bZ.a(string6, b3);
                    continue;
                }
                com.corrodinggames.rts.gameFramework.l.e("Skipping map due to mod settings");
                continue;
            }
            string6 = string + "/" + string5;
            if (n2 < 10) {
                if (!com.corrodinggames.rts.gameFramework.e.a.f(string6)) continue;
                String string7 = string2;
                if (string7 == null) {
                    string7 = string6;
                }
                long l4 = -1L;
                if (bl4) {
                    l4 = br.a();
                    com.corrodinggames.rts.gameFramework.l.e("============");
                    com.corrodinggames.rts.gameFramework.l.e(">>> Mod '" + b3.c() + "'" + (b3.m() ? "" : " (disabled)"));
                }
                ag.a(string6, n2 + 1, bl2, b3, string7, string3);
                if (!bl4 || b3 == null || !b3.m()) continue;
                double d2 = br.a(l4);
                com.corrodinggames.rts.gameFramework.l.e("Mod '" + b3.c() + "' load took:" + br.a(d2));
                continue;
            }
            com.corrodinggames.rts.gameFramework.l.e("Too many levels:" + string6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static com.corrodinggames.rts.game.units.custom.l a(String string, InputStream inputStream, long l2, com.corrodinggames.rts.gameFramework.i.b b2, com.corrodinggames.rts.gameFramework.utility.j j2, String string2, String string3) {
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        try {
            Object object;
            Object object22;
            Object object3;
            int n2;
            ArrayList arrayList5;
            Object object42;
            Object object52;
            int n3;
            int n4;
            Object object6;
            int n5;
            Object object72;
            Object object8;
            boolean bl2;
            Boolean bl3;
            Object object9;
            int n6;
            int n7;
            int n8;
            String string4;
            Object object11;
            String string5;
            Object object1222;
            Object object13;
            Object object14;
            float f2;
            float f3;
            float f4;
            float f5;
            com.corrodinggames.rts.gameFramework.m.e e2;
            String string6;
            Object object15;
            int n9;
            int n10;
            Object object16;
            String string7;
            Object object1722;
            com.corrodinggames.rts.gameFramework.utility.ab ab2;
            if (a) {
                String string8 = "CORE";
                if (b2 != null) {
                    string8 = b2.j();
                }
                com.corrodinggames.rts.gameFramework.l.e("Loading unit config: " + string + " [" + string8 + "]");
            }
            l3.bO.e();
            long l4 = br.a();
            try {
                ab2 = new com.corrodinggames.rts.gameFramework.utility.ab(inputStream, string);
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            ag.a(l4, ah.e);
            String string9 = "core";
            String string10 = "graphics";
            String string11 = "attack";
            String string12 = "movement";
            String string13 = "ai";
            com.corrodinggames.rts.game.units.custom.l l5 = new com.corrodinggames.rts.game.units.custom.l();
            if (ab2.a(string9, "dont_load", (Boolean)false).booleanValue()) {
                return null;
            }
            l5.D = string;
            l5.E = j2.d();
            l5.F = l5.D;
            l5.J = b2;
            l5.K = string2;
            l5.L = string3;
            e = b2;
            f = false;
            if (l5.J != null) {
                // empty if block
            }
            long l6 = br.a();
            ag.a(l5, ab2, ab2, string, 0);
            if (l5.L != null) {
                ag.a(l5, ab2, l5.L + "/" + "all-units.template", "AUTO units.template", true);
            }
            ab2.a("core", "copyFrom");
            l5.R = ab2.b(string9, "strictLevel", 0);
            if (l5.R < 0) {
                throw new bo("[core]strictLevel cannot be < 0");
            }
            if (l5.R > 1) {
                throw new bo("[core]strictLevel cannot yet be > 1");
            }
            l5.gs = ab2.a(string9, "logIfCreditResourceUsed", (Boolean)false);
            ab2.a(string9, "dont_load");
            String string14 = ab2.b(string9, "class", "CustomUnitMetadata");
            com.corrodinggames.rts.gameFramework.utility.m m2 = ab2.c("@copyFrom_skipThisSection");
            for (Object object1722 : m2) {
                ab2.a((String)object1722, "@copyFrom_skipThisSection");
            }
            com.corrodinggames.rts.gameFramework.utility.m m3 = ab2.c("@copyFromSection");
            object1722 = m3.iterator();
            while (object1722.hasNext()) {
                string7 = (String)object1722.next();
                ag.a(l5, ab2, string7, string7, 0);
            }
            com.corrodinggames.rts.game.units.custom.f.a.a(l5, ab2);
            object1722 = ab2.b(string9, "overrideResourceLoadPath", (String)null);
            if (object1722 != null) {
                l5.F = ag.a(l5, string, (String)object1722);
            }
            ag.a(l6, ah.i);
            l5.M = ab2.e(string9, "name");
            l5.H = ab2.c();
            if (l5.M.equals("self")) {
                throw new bo("Unit name: " + l5.M + " is reserved");
            }
            if (l5.M.startsWith("self.")) {
                throw new bo("Unit name cannot start with self.");
            }
            string7 = ab2.b(string9, "altNames", (String)null);
            if (string7 != null && !string7.equalsIgnoreCase("NONE")) {
                object16 = string7.split(",");
                n10 = ((String[])object16).length;
                for (n9 = 0; n9 < n10; ++n9) {
                    object15 = object16[n9];
                    object15 = ((String)object15).trim();
                    l5.N.add(object15);
                }
            }
            l5.O = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string9, "tags", (String)null));
            if (l5.R >= 1 && l5.O != null) {
                object16 = l5.O.a;
                n10 = ((Object[])object16).length;
                for (n9 = 0; n9 < n10; ++n9) {
                    object15 = object16[n9];
                    if (!((com.corrodinggames.rts.game.units.custom.g)object15).a.contains(" ")) continue;
                    throw new bo("(strictLevel 1) [core]tags: space in tag: '" + ((com.corrodinggames.rts.game.units.custom.g)object15).a + "'");
                }
            }
            l5.Q = ab2.b(string9, "overrideAndReplace", (String)null);
            if (l5.Q != null && l5.Q.equalsIgnoreCase("NONE")) {
                l5.Q = null;
            }
            if ((object16 = ab2.b(string9, "defineUnitMemory", (String)null)) != null) {
                l5.r.addDefineValue(l5, string9, "defineUnitMemory", (String)object16);
                if (l5.r.hasArrays()) {
                    l5.a("1.15p11", 115011, string9, "Memory arrays (in defineUnitMemory)");
                }
            }
            Object object18 = ab2.k(string9, "@memory ").iterator();
            while (object18.hasNext()) {
                String string15 = (String)object18.next();
                object15 = string15.substring("@memory ".length()).trim();
                string6 = ab2.b(string9, string15, (String)null);
                if (string6 == null) continue;
                if (string6.contains(",")) {
                    throw new bo("[" + string9 + "]" + string15 + ": Only a single variable can be defined per @memory");
                }
                l5.r.addSingleDefine(l5, (String)object15, string6, string9, string15);
                if (!l5.r.hasArrays()) continue;
                l5.a("1.15p11", 115011, string9, "Memory arrays (in " + string15 + ")");
            }
            l5.T = (ad)ab2.a(string9, "onNewMapSpawn", null, ad.class);
            l5.aG = ab2.a(string9, "globalScale", Float.valueOf(1.0f)).floatValue();
            l5.o(l5.E);
            if (l5.M.equals("missing")) {
                if (b2 == null) {
                    com.corrodinggames.rts.gameFramework.l.e("Setting missingPlaceHolder");
                    com.corrodinggames.rts.game.units.custom.l.b = l5;
                } else {
                    com.corrodinggames.rts.gameFramework.l.e("Not setting missingPlaceHolder, as we are in a mod");
                }
            }
            l5.aE = ab2.b(string9, "displayLocaleKey", (String)null);
            l5.aC = ag.a(ab2, string9, "displayText", null);
            l5.aD = ag.a(ab2, string9, "displayDescription", null);
            l5.eD = ab2.a(string9, "isBio", (Boolean)false);
            l5.eE = ab2.a(string9, "isBug", (Boolean)false);
            l5.eH = ab2.a(string9, "isPickableStartingUnit", (Boolean)false);
            l5.eI = ab2.a(string9, "startFallingWhenStartingUnit", (Boolean)false);
            l5.cy = ab2.a(string9, "stayNeutral", (Boolean)false);
            l5.cz = ab2.a(string9, "createNeutral", (Boolean)false);
            l5.cA = ab2.a(string9, "allowCaptureWhenNeutralByAI", (Boolean)false);
            if (ab2.a(string9, "createOnNeutralTeam", (Boolean)false).booleanValue()) {
                l5.cz = true;
            }
            l5.cB = ab2.a(string9, "whileNeutralTransportAnyTeam", (Boolean)false);
            l5.cC = ab2.a(string9, "whileNeutralConvertToTransportedTeam", (Boolean)false);
            l5.cD = ab2.a(string9, "convertToNeutralIfNotTransporting", (Boolean)false);
            if (l5.cD) {
                l5.cy = true;
            }
            l5.cE = ab2.a(string9, "createOnAggressiveTeam", (Boolean)false);
            l5.aF = ab2.a(string9, "showInEditor", (Boolean)true);
            l5.U = ab2.b(string10, "total_frames", 1);
            if (l5.U < 1) {
                throw new bo("TOTAL_FRAMES cannot be: " + l5.U + " (must be 1 or more)");
            }
            l5.W = ab2.b(string10, "frame_width", -1);
            l5.X = ab2.b(string10, "frame_height", -1);
            l5.Y = ab2.b(string10, "default_frame", 0);
            l5.ah = ab2.b(string10, "image_offsetX", 0);
            l5.ai = ab2.b(string10, "image_offsetY", 0);
            l5.aj = ab2.a(string10, "image_offsetH", Float.valueOf(0.0f)).floatValue();
            if (l5.ah != 0 || l5.ai != 0 || l5.aj != 0.0f) {
                l5.ak = true;
            }
            l5.ac = com.corrodinggames.rts.game.o.a;
            if (ab2.a(string10, "teamColorsUseHue", (Boolean)false).booleanValue()) {
                l5.ac = com.corrodinggames.rts.game.o.b;
            }
            if ((object18 = ab2.b(string10, "teamColoringMode", (String)null)) != null) {
                if (ab2.a(string10, "teamColorsUseHue", (Boolean)null) != null) {
                    throw new bo("Cannot use teamColoringMode and teamColorsUseHue at the same time");
                }
                if (((String)object18).equalsIgnoreCase("pureGreen")) {
                    l5.ac = com.corrodinggames.rts.game.o.a;
                } else if (((String)object18).equalsIgnoreCase("hueAdd")) {
                    l5.ac = com.corrodinggames.rts.game.o.b;
                } else if (((String)object18).equalsIgnoreCase("hueShift")) {
                    l5.ac = com.corrodinggames.rts.game.o.d;
                } else if (((String)object18).equalsIgnoreCase("disabled")) {
                    l5.ac = com.corrodinggames.rts.game.o.e;
                } else {
                    throw new bo("Unknown teamColoringMode:" + (String)object18);
                }
            }
            l5.ab = ab2.a(string10, "imageSmoothing", (Boolean)false);
            l5.aa = ab2.a(string10, "imageSmoothingWhenZoomedIn", (Boolean)false);
            l5.Z = ab2.a(l5, string10, "isVisible", (LogicBoolean)null);
            if (l5.Z == LogicBoolean.trueBoolean) {
                l5.Z = null;
            }
            l5.cL.m = ab2.a(string10, "isVisibleToEnemies", (Boolean)true);
            String string16 = ab2.e(string10, "image");
            l5.ad = l5.a(l5.F, string16, l5.ab, string10, "image");
            if (l5.ad == null) {
                throw new bo("Main unit image must be set on custom unit");
            }
            l5.ae = ab2.a(string10, "image_floatingPointSize", (Boolean)false);
            l5.af = l5.ad.m() / l5.U;
            l5.ag = l5.ad.l();
            if (l5.af < 1) {
                l5.af = 1;
            }
            if (l5.W > 0) {
                l5.af = l5.W;
            }
            if (l5.X > 0) {
                l5.ag = l5.X;
                if (l5.ag < l5.ad.l()) {
                    l5.V = l5.ad.m() / l5.af;
                    if (l5.V < 1) {
                        l5.V = 1;
                    }
                }
            }
            l5.al = l5.a(ab2, string10, "image_back");
            l5.am = ab2.a(string10, "image_back_always_use_full_image", (Boolean)false);
            l5.an = l5.a(ab2, string10, "image_wreak");
            l5.ao = l5.a(ab2, string10, "image_turret");
            l5.as = com.corrodinggames.rts.game.units.e.j.dN;
            object15 = ab2.b(string10, "image_shadow", "NONE");
            if (((String)object15).equalsIgnoreCase("AUTO")) {
                string6 = "[autoShadow:" + l5.af + "," + l5.ag + "]" + l5.ad.d + "-" + l5.ad.e;
                e2 = ag.c(string6);
                if (e2 != null) {
                    l5.ap = e2;
                } else {
                    l5.ap = am.a(l5.ad, l5.af, l5.ag);
                    ag.a(l5.ap);
                    if (l5.ap != null) {
                        ag.a(string6, l5.ap);
                    }
                }
            } else if (((String)object15).equalsIgnoreCase("AUTO_ANIMATED")) {
                string6 = "[autoShadowAnimated:" + l5.af + "," + l5.ag + "]" + l5.ad.d + "-" + l5.ad.e;
                e2 = ag.c(string6);
                if (e2 != null) {
                    l5.ap = e2;
                } else {
                    l5.ap = am.a(l5.ad, l5.ad.m(), l5.ad.l());
                    ag.a(l5.ap);
                    if (l5.ap != null) {
                        ag.a(string6, l5.ap);
                    }
                }
                l5.aq = true;
            } else {
                l5.ap = l5.a(l5.F, (String)object15, l5.ab, string10, "image_shadow");
            }
            if (ab2.a(string10, "image_shadow_frames", (Boolean)false).booleanValue()) {
                l5.aq = true;
            }
            l5.ar = l5.a(l5.ad, l5.ac);
            l5.s = ab2.a(string10, "teamColorsOnTurret", (Boolean)false);
            if (l5.s && l5.ao != null) {
                l5.at = l5.a(l5.ao, l5.ac);
            }
            if ((f5 = ab2.a(string10, "scaleImagesTo", Float.valueOf(-1.0f)).floatValue()) > 0.0f) {
                l5.bH = (f5 *= l5.aG) / (float)l5.af;
            }
            if ((f4 = ab2.a(string10, "imageScale", Float.valueOf(1.0f)).floatValue()) != 1.0f) {
                l5.bH *= f4;
            }
            if ((f3 = ab2.a(string10, "scaleTurretImagesTo", Float.valueOf(-1.0f)).floatValue()) > 0.0f) {
                f3 *= l5.aG;
                if (l5.ao == null) {
                    throw new RuntimeException("scaleTurretImagesTo needs image_turret set");
                }
                l5.bI = f3 / (float)l5.ao.p;
            }
            if ((f2 = ab2.a(string10, "turretImageScale", Float.valueOf(1.0f)).floatValue()) != 1.0f) {
                l5.bI *= f2;
            }
            l5.au = com.corrodinggames.rts.game.units.e.c.e;
            com.corrodinggames.rts.gameFramework.m.e e3 = l5.a(ab2, string10, "image_shield");
            if (e3 != null) {
                l5.au = e3;
                l5.av = true;
            }
            l5.aw = l5.a(ab2, string10, "icon_build", false);
            float f6 = (float)l5.ad.m() * l5.bH;
            float f7 = (float)l5.ad.l() * l5.bH;
            if (f6 / 2.0f > 90.0f || f7 / 2.0f > 90.0f) {
                l5.C = new Rect();
                l5.C.a = (int)(-f6 / 2.0f);
                l5.C.c = (int)(f6 / 2.0f);
                l5.C.b = (int)(-f7 / 2.0f);
                l5.C.d = (int)(f7 / 2.0f);
                l5.B = true;
            }
            for (Object object19 : ab2.m("resource_", "global_resource_")) {
                boolean bl32;
                if (((String)object19).startsWith("resource_")) {
                    object14 = ((String)object19).substring("resource_".length());
                    bl32 = false;
                } else {
                    object14 = ((String)object19).substring("global_resource_".length());
                    bl32 = true;
                }
                object14 = ((String)object14).trim();
                if (((String)object14).contains(" ")) {
                    throw new RuntimeException("[" + (String)object19 + "] resource codename cannot contain a space");
                }
                if (((String)object14).contains("=") || ((String)object14).contains("|") || ((String)object14).contains(":") || ((String)object14).contains(",") || ((String)object14).contains("(") || ((String)object14).contains(")") || ((String)object14).contains("<") || ((String)object14).contains(">") || ((String)object14).contains("$")) {
                    throw new RuntimeException("[" + (String)object19 + "] resource codename cannot contain the symbols: =|:,()<>$");
                }
                object13 = new d(bl32);
                ((d)object13).a(l5, ab2, (String)object19, (String)object14);
                if (l5.k(((d)object13).a) != null) {
                    throw new RuntimeException("[" + (String)object19 + "] resource with name:" + ((d)object13).a + " already exists in this file");
                }
                l5.j.add(object13);
            }
            for (Object object19 : l5.j) {
                ((d)object19).a(l5);
            }
            if (l3.p()) {
                com.corrodinggames.rts.game.units.custom.b.l.a(l5, ab2);
                com.corrodinggames.rts.game.units.custom.b.j.a(l5, ab2);
            }
            com.corrodinggames.rts.game.units.custom.b.m.a(l5, ab2);
            l5.ca = ab2.b(string9, "autoTriggerCooldownTime", Float.valueOf(60.0f)).floatValue();
            if (l5.ca < 0.0f) {
                throw new RuntimeException("autoTriggerCooldownTime cannot be < 0");
            }
            if (l5.ca > 120.0f) {
                throw new RuntimeException("autoTriggerCooldownTime cannot be more than 2 seconds");
            }
            if (!ab2.a(string9, "autoTriggerCooldownTime_allowDangerousHighCPU", (Boolean)false).booleanValue() && l5.ca < 5.0f) {
                throw new RuntimeException("autoTriggerCooldownTime cannot be this low (without override). Note this cooldown is only applied after triggering an action not for the detection.");
            }
            l5.cb = (s)ab2.a(string9, "autoTriggerCheckRate", com.corrodinggames.rts.game.units.custom.s.a, s.class);
            l5.cd = ab2.a(string9, "autoTriggerCheckWhileNotBuilt", (Boolean)false);
            l5.cL.b = ab2.g(string9, "mass");
            l5.ce = ab2.a(string9, "availableInDemo", (Boolean)true);
            l5.cf = ab2.a(string9, "isLocked", (Boolean)false);
            l5.cg = ab2.a(string9, "isLockedIfGameModeNoNuke", (Boolean)false);
            l5.ch = com.corrodinggames.rts.game.units.custom.d.b.a(l5, ab2, string9, "price", false);
            l5.ci = com.corrodinggames.rts.game.units.custom.d.b.a(l5, ab2, string9, "reclaimPrice", null);
            l5.cj = com.corrodinggames.rts.game.units.custom.d.b.b(l5, ab2, string9, "streamingCost", null);
            boolean bl4 = ab2.a(string9, "switchPriceWithStreamingCost", (Boolean)false);
            if (bl4) {
                if (l5.cj != null) {
                    throw new RuntimeException("[" + string9 + "]streamingCost and switchPriceWithStreamingCost=true cannot be used at the same time");
                }
                l5.cj = com.corrodinggames.rts.game.units.custom.d.b.b(l5, ab2, string9, "price", null);
                l5.ch = com.corrodinggames.rts.game.units.custom.d.b.a;
            }
            l5.ck = ab2.d(string9, "buildSpeed", Float.valueOf(1.0f)).floatValue();
            l5.cl = ab2.b(string9, "techLevel", 1);
            if (l5.cl > 3) {
                throw new RuntimeException("techLevel cannot be greater than max tech level of:3");
            }
            if (l5.cl < 1) {
                throw new RuntimeException("techLevel cannot be less than 1, it is:" + l5.cl);
            }
            l5.cm = ab2.a(string9, "experimental", (Boolean)false);
            l5.cv = com.corrodinggames.rts.game.units.custom.d.b.a(l5, ab2, string9, "borrowResourcesWhileAlive", true);
            l5.cw = com.corrodinggames.rts.game.units.custom.d.b.a(l5, ab2, string9, "borrowResourcesWhileBuilt", true);
            l5.co = com.corrodinggames.rts.game.units.custom.d.b.a(l5, ab2, string9, "generation_resources", true);
            int n11 = ab2.b(string9, "generation_credits", 0);
            if (n11 != 0) {
                l5.co = com.corrodinggames.rts.game.units.custom.d.b.a(l5.co, com.corrodinggames.rts.game.units.custom.d.b.a(n11));
            }
            l5.cr = ab2.b(string9, "generation_delay", 40);
            if (l5.cr == 0) {
                l5.cr = 1;
            }
            if (l5.cr < 0) {
                throw new RuntimeException("[" + string9 + "]generation_delay cannot be < 0");
            }
            l5.cs = 40.0f / (float)l5.cr;
            if (!l5.co.c()) {
                l5.cp = new com.corrodinggames.rts.game.units.custom.e.f();
                l5.cp.a(l5.co);
                l5.cp.a(l5.cs);
                l5.cn = true;
            }
            if (!l5.cp.c()) {
                for (e e4 : l5.cp.b) {
                    if (e4.a.c() || !e4.a.d()) continue;
                    if (l5.cq == com.corrodinggames.rts.game.units.custom.e.f.a) {
                        l5.cq = new com.corrodinggames.rts.game.units.custom.e.f();
                    }
                    l5.cq.b(e4.a, e4.b);
                }
            }
            l5.cx = ab2.a(l5, string9, "generation_active", LogicBoolean.trueBoolean);
            l5.a(l5.co);
            l5.cF = ab2.a(string9, "resourceRate", Float.valueOf(0.0f)).floatValue();
            if (bl4 && l5.cF != 0.0f) {
                throw new RuntimeException("To avoid mistakes [" + string9 + "]resourceRate cannot be used with switchPriceWithStreamingCost=true");
            }
            object14 = ab2.b(string9, "updateUnitMemory", (String)null);
            if (object14 != null) {
                l5.ct = VariableScope.createMemoryWriter((String)object14, l5, string9, "updateUnitMemory");
            }
            l5.cu = ab2.b(string9, "updateUnitMemoryRate", Float.valueOf(60.0f)).floatValue();
            l5.cG = ab2.b(string9, "resourceMaxConcurrentReclaimingThis", Integer.MAX_VALUE);
            l5.cH = ab2.a(l5, string9, "similarResourcesHaveTag", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.do = bl.a(l5, ab2.b(string9, "soundOnAttackOrder", (String)null));
            l5.dp = bl.a(l5, ab2.b(string9, "soundOnMoveOrder", (String)null));
            l5.dq = bl.a(l5, ab2.b(string9, "soundOnNewSelection", (String)null));
            String string17 = ab2.b(string10, "drawLayer", (String)null);
            if (string17 != null) {
                if (string17.equals("experimentals")) {
                    l5.cI = 4;
                } else if (string17.equals("underwater")) {
                    l5.cI = 1;
                } else if (string17.equals("bottom")) {
                    l5.cI = 1;
                } else if (string17.equals("ground")) {
                    l5.cI = 2;
                } else if (string17.equals("ground2")) {
                    l5.cI = 3;
                } else if (string17.equals("air")) {
                    l5.cI = 5;
                } else if (string17.equals("top")) {
                    l5.cI = 10;
                } else if (string17.equals("wreaks")) {
                    l5.cI = 0;
                } else {
                    throw new RuntimeException("unknown drawLayer:" + string17);
                }
            }
            l5.cJ = ab2.a(string10, "shadowOffsetX", Float.valueOf(0.0f)).floatValue();
            l5.cK = ab2.a(string10, "shadowOffsetY", Float.valueOf(0.0f)).floatValue();
            l5.dB = ab2.a(string10, "rotate_with_direction", (Boolean)true);
            l5.dC = ab2.a(string10, "lock_body_rotation_with_main_turret", (Boolean)false);
            l5.dD = ab2.a(string10, "lock_shadow_rotation_with_main_turret", (Boolean)l5.dC);
            l5.dE = ab2.a(string10, "lock_leg_rotation_with_main_turret", (Boolean)false);
            l5.dH = ab2.a(string10, "whenBeingBuiltMakeTransparentTill", Float.valueOf(1.0f)).floatValue();
            l5.dI = com.corrodinggames.rts.game.units.custom.m.a(l5, ab2, string10, "animation_", false);
            for (Object object1222 : ab2.e("effect_")) {
                string5 = ((String)object1222).substring("effect_".length());
                object11 = new ay(string5);
                ((ay)object11).a(l5, ab2, (String)object1222);
                l5.gd.add(object11);
            }
            for (Object object1222 : l5.gd) {
                if (((ay)object1222).alsoEmitEffects != null) {
                    ((ay)object1222).alsoEmitEffects.c();
                }
                if (((ay)object1222).alsoEmitEffectsOnDeath != null) {
                    ((ay)object1222).alsoEmitEffectsOnDeath.c();
                }
                if (((ay)object1222).ifSpawnFailsEmitEffects != null) {
                    ((ay)object1222).ifSpawnFailsEmitEffects.c();
                }
                if (((ay)object1222).trailEffect == null) continue;
                ((ay)object1222).trailEffect.c();
            }
            l5.bJ = ab2.a(string10, "splastEffect", (Boolean)false);
            l5.bM = ab2.a(string10, "dustEffect", (Boolean)false);
            l5.bK = ab2.a(string10, "splastEffectReverse", (Boolean)true);
            l5.bN = ab2.a(string10, "dustEffectReverse", (Boolean)true);
            l5.bL = l5.bM || l5.bJ;
            object13 = ab2.b(string10, "movementEffect", (String)null);
            if (object13 != null) {
                l5.bO = l5.a((String)object13, (z)null);
                if (l5.bO != null && l5.bO.a()) {
                    l5.bL = true;
                }
            }
            if ((object1222 = ab2.b(string10, "movementEffectReverse", (String)null)) != null) {
                l5.bP = l5.a((String)object1222, (z)null);
                if (l5.bP != null && l5.bP.a()) {
                    l5.bL = true;
                }
            }
            l5.bR = ab2.a(string10, "movementEffectRate", Float.valueOf(11.0f)).floatValue();
            l5.bQ = ab2.a(string10, "movementEffectReverseFlipEffects", (Boolean)false);
            l5.bT = ab2.a(string10, "repairEffectRate", Float.valueOf(5.0f)).floatValue();
            string5 = ab2.b(string10, "repairEffect", (String)null);
            if (string5 != null) {
                l5.bU = l5.a(string5, (z)null);
                if (l5.bU != null && l5.bU.b()) {
                    l5.bS = true;
                }
            }
            if ((object11 = ab2.b(string10, "repairEffectAtTarget", (String)null)) != null) {
                l5.bV = l5.a((String)object11, (z)null);
                if (l5.bV != null && l5.bV.b()) {
                    l5.bS = true;
                }
            }
            l5.bX = ab2.a(string10, "reclaimEffectRate", Float.valueOf(5.0f)).floatValue();
            String string18 = ab2.b(string10, "reclaimEffect", (String)null);
            if (string18 != null) {
                l5.bY = l5.a(string18, (z)null);
                if (l5.bY != null && l5.bY.b()) {
                    l5.bW = true;
                }
            }
            if ((string4 = ab2.b(string10, "reclaimEffectAtTarget", (String)null)) != null) {
                l5.bZ = l5.a(string4, (z)null);
                if (l5.bZ != null && l5.bZ.b()) {
                    l5.bW = true;
                }
            }
            l5.ds.a(l5, ab2, string10, "animation_" + l5.ds.a + "_");
            l5.dt.a(l5, ab2, string10, "animation_" + l5.dt.a + "_");
            l5.du.a(l5, ab2, string10, "animation_" + l5.du.a + "_");
            for (String string19 : ab2.e("animation_")) {
                String string20 = string19.substring("animation_".length());
                com.corrodinggames.rts.game.units.custom.f f8 = new com.corrodinggames.rts.game.units.custom.f(string20);
                f8.a(l5, ab2, string19, "");
                l5.dr.add(f8);
            }
            l5.ds = l5.a(com.corrodinggames.rts.game.units.custom.n.a, l5.ds, true);
            l5.dt = l5.a(com.corrodinggames.rts.game.units.custom.n.c, l5.dt, true);
            l5.du = l5.a(com.corrodinggames.rts.game.units.custom.n.b, l5.du, true);
            l5.dw = l5.a(com.corrodinggames.rts.game.units.custom.n.e);
            l5.dx = l5.a(com.corrodinggames.rts.game.units.custom.n.f);
            if (l5.dw != null && l5.dx != null) {
                throw new RuntimeException("Cannot use underConstruction and underConstructionWithLinkedBuiltTime animations at the same time");
            }
            l5.dv = l5.a(com.corrodinggames.rts.game.units.custom.n.d);
            l5.dy = l5.a(com.corrodinggames.rts.game.units.custom.n.g);
            if (l5.dy != null) {
                l5.bg = true;
            }
            l5.dz = l5.a(com.corrodinggames.rts.game.units.custom.n.h);
            l5.dA = l5.a(com.corrodinggames.rts.game.units.custom.n.i);
            l5.cL.c = ab2.g(string9, "maxHp");
            l5.cL.g = ab2.b(string9, "maxShield", 0);
            l5.cM = ab2.a(string9, "startShieldAtZero", (Boolean)false);
            l5.cL.h = ab2.a(string9, "shieldRegen", Float.valueOf(0.25f)).floatValue();
            l5.cU = ab2.a(string9, "shieldDisplayOnlyDeflection", (Boolean)false);
            l5.cV = ab2.a(string9, "shieldDeflectionDisplayRate", Float.valueOf(4.0f)).floatValue();
            l5.cL.l = ab2.a(string9, "armour", Float.valueOf(0.0f)).floatValue();
            l5.cN = ab2.a(string9, "armourMinDamageToKeep", Float.valueOf(1.0f)).floatValue();
            l5.cL.d = ab2.a(string9, "energyMax", Float.valueOf(0.0f)).floatValue();
            l5.cO = ab2.a(string9, "startEnergyAtZero", (Boolean)false);
            l5.cP = ab2.a(string9, "energyRegen", Float.valueOf(0.0f)).floatValue();
            l5.cS = ab2.a(string9, "energyStartingPercentage", Float.valueOf(1.0f)).floatValue();
            l5.cR = ab2.a(string9, "energyNeedsToRechargeToFull", (Boolean)false);
            l5.cQ = ab2.a(string9, "energyRegenWhenRecharging", Float.valueOf(l5.cP)).floatValue();
            l5.cT = ag.a(ab2, string9, "energyDisplayName", null);
            l5.cW = ab2.g(string9, "radius");
            l5.dd = ab2.b(string9, "displayRadius", l5.cW);
            float f9 = l5.cW;
            if (f9 < 6.0f) {
                f9 = 6.0f;
            }
            l5.de = ab2.a(string9, "uiTargetRadius", Float.valueOf(f9)).floatValue();
            l5.df = ab2.b(string9, "shieldRenderRadius", l5.cW);
            l5.dg = ab2.b(string9, "buildingSelectionOffset", 0);
            l5.cX = ab2.a(string9, "footprint", l5.cX);
            l5.cY = ab2.a(string9, "constructionFootprint", l5.cY);
            l5.cZ.a(l5.cX);
            l5.cZ = ab2.a(string9, "displayFootprint", l5.cZ);
            l5.da = ab2.a(string9, "buildingToFootprintOffsetX", Float.valueOf(10.0f)).floatValue();
            l5.db = ab2.a(string9, "buildingToFootprintOffsetY", Float.valueOf(10.0f)).floatValue();
            l5.cW = (int)((float)l5.cW * l5.aG);
            l5.dd = (int)((float)l5.dd * l5.aG);
            l5.cL.n = ab2.b(string9, "fogOfWarSightRange", 15);
            l5.dh = ab2.b(string9, "fogOfWarSightRangeWhileNotBuilt", -1);
            l5.di = ab2.a(string9, "exit_x", Float.valueOf(0.0f)).floatValue();
            l5.dj = ab2.a(string9, "exit_y", Float.valueOf(9.0f)).floatValue();
            l5.dk = ab2.a(string9, "exit_dirOffset", (Float)null);
            l5.dl = ab2.a(string9, "exit_heightOffset", Float.valueOf(0.0f)).floatValue();
            l5.dm = ab2.a(string9, "exitHeightIgnoreParent", (Boolean)false);
            l5.dn = ab2.a(string9, "exit_moveAwayAmount", Float.valueOf(70.0f));
            l5.eB = ab2.b(string9, "softCollisionOnAll", 0);
            l5.eC = ab2.a(string9, "disableAllUnitCollisions", (Boolean)false);
            if (l5.eC) {
                l5.cX.a(0, 0, -1, -1);
            }
            l5.eJ = ab2.a(string9, "hideScorchMark", (Boolean)false);
            l5.eK = ab2.a(string10, "disableLowHpFire", (Boolean)l5.eD);
            l5.eL = ab2.a(string10, "disableLowHpSmoke", (Boolean)l5.eD);
            l5.aH = ab2.a(string9, "isBuilding", (Boolean)false);
            l5.aI = ab2.a(string9, "ignoreInUnitCapCalculation", (Boolean)l5.aH);
            l5.aJ = ab2.a(string9, "placeOnlyOnResPool", (Boolean)false);
            l5.aK = ab2.a(string9, "isUnrepairableUnit", (Boolean)false);
            l5.aL = ab2.a(string9, "extraBuildRangeWhenBuildingThis", Float.valueOf(0.0f)).floatValue();
            l5.aM = ab2.a(string9, "isUnselectable", (Boolean)false);
            l5.aN = ab2.a(string9, "isUnselectableAsTarget", (Boolean)l5.aM);
            l5.fO = ab2.a(l5, string9, "showActionsWithMixedSelectionIfOtherUnitsHaveTag", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.aO = ab2.a(string9, "canNotBeDirectlyAttacked", (Boolean)false);
            l5.aP = ab2.a(string9, "canNotBeDamaged", (Boolean)l5.aO);
            l5.aQ = ab2.a(string9, "showOnMinimap", (Boolean)true);
            l5.aR = ab2.a(string9, "showOnMinimapToEnemies", (Boolean)l5.cL.m);
            l5.aS = ab2.a(l5, string9, "canOnlyBeAttackedByUnitsWithTags", (com.corrodinggames.rts.game.units.custom.h)null);
            if (l5.aO && l5.aS != null) {
                throw new RuntimeException("canNotBeDirectlyAttacked and canOnlyBeAttackedByUnitsWithTags cannot be used at the same time");
            }
            l5.aT = ab2.a(string9, "canNotBeGivenOrdersByPlayer", (Boolean)false);
            l5.aU = ab2.a(string9, "canRepairBuildings", (Boolean)false);
            l5.aV = ab2.a(string9, "canRepairUnits", (Boolean)false);
            l5.aW = ab2.a(string9, "autoRepair", (Boolean)false);
            if (l5.aW) {
                l5.a(com.corrodinggames.rts.game.units.custom.b.b.a);
            }
            l5.cL.o = ab2.b(string9, "nanoRange", -1);
            if (l5.cL.o != -1) {
                l5.cL.o = (int)((float)l5.cL.o * l5.aG);
            }
            l5.aY = ab2.a(string9, "nanoRangeForRepairIsMelee", (Boolean)false);
            if (l5.aY) {
                l5.aX = 5;
            }
            if ((n8 = ab2.b(string9, "nanoRangeForRepair", -1).intValue()) != -1) {
                l5.aX = n8;
                l5.aX = (int)((float)l5.aX * l5.aG);
            }
            l5.ba = ab2.a(string9, "nanoRangeForReclaimIsMelee", (Boolean)false);
            if (l5.ba) {
                l5.aZ = 5;
            }
            if ((n7 = ab2.b(string9, "nanoRangeForReclaim", -1).intValue()) != -1) {
                l5.aZ = n7;
                l5.aZ = (int)((float)l5.aZ * l5.aG);
            }
            l5.bb = ab2.a(string9, "nanoRepairSpeed", Float.valueOf(0.2f)).floatValue();
            float f10 = 5.1f;
            l5.bc = ab2.a(string9, "nanoReclaimSpeed", Float.valueOf(l5.bb * 5.1f)).floatValue();
            l5.bd = ab2.a(string9, "resourceReclaimMultiplier", Float.valueOf(1.0f)).floatValue();
            l5.be = ab2.a(string9, "nanoUnbuildSpeed", Float.valueOf(1.0f)).floatValue() * 0.001f * 5.1f;
            l5.bf = ab2.a(string9, "nanoBuildSpeed", Float.valueOf(1.0f)).floatValue();
            l5.cL.r = ab2.a(string9, "nanoFactorySpeed", Float.valueOf(1.0f)).floatValue();
            l5.cL.p = ab2.a(string9, "selfRegenRate", Float.valueOf(0.0f)).floatValue();
            l5.bh = ab2.d(string9, "selfBuildRate", Float.valueOf(0.0f)).floatValue();
            l5.bi = ab2.a(string9, "dieOnConstruct", (Boolean)false);
            l5.bk = ab2.a(string9, "dieOnZeroEnergy", (Boolean)false);
            int n12 = 4;
            if (l5.cL.b > 30000.0f) {
                n12 = 8;
            }
            if (l5.aH) {
                n12 = 7;
            }
            l5.bq = ab2.b(string9, "numBitsOnDeath", n12);
            l5.bn = ab2.a(string9, "nukeOnDeath", (Boolean)false);
            l5.bo = ab2.a(string9, "nukeOnDeathRange", Float.valueOf(250.0f)).floatValue();
            l5.bp = ab2.a(string9, "nukeOnDeathDamage", Float.valueOf(5400.0f)).floatValue();
            l5.br = ab2.a(string9, "nukeOnDeathDisableWhenNoNuke", (Boolean)false);
            l5.bm = ab2.b(string9, "fireOnDeath", 0);
            l5.bt = (ab)ab2.a(string9, "explodeTypeOnDeath", null, ab.class);
            l5.bu = ab2.a(string9, "explodeOnDeath", (Boolean)true);
            l5.bs = ab2.a(string9, "disableDeathOnZeroHp", (Boolean)false);
            boolean bl5 = ab2.a(string9, "explodeOnDeathGroundCollosion", (Boolean)true);
            l5.bv = bl5 = ab2.a(string9, "explodeOnDeathGroundCollision", (Boolean)bl5).booleanValue();
            l5.by = l5.a(ab2.b(string9, "effectOnDeath", (String)null), (z)null);
            l5.bx = l5.a(ab2.b(string9, "effectOnDeathIfUnbuilt", (String)null), (z)null);
            l5.bz = bl.a(l5, ab2.b(string9, "soundOnDeath", (String)null));
            String string21 = ab2.b(string9, "effectOnDeathGroundCollosion", (String)null);
            string21 = ab2.b(string9, "effectOnDeathGroundCollision", string21);
            l5.bw = l5.a(string21, (z)null);
            l5.bC = bp.a(l5, ab2, string9, "unitsSpawnedOnDeath");
            l5.bD = ab2.a(string9, "unitsSpawnedOnDeath_setToTeamOfLastAttacker", (Boolean)false);
            l5.fk = ab2.a(string9, "canReclaimResources", (Boolean)false);
            l5.fl = ab2.a(l5, string9, "canReclaimResourcesOnlyWithTags", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.fm = ab2.b(string9, "canReclaimResourcesNextSearchRange", 500);
            l5.fn = ab2.a(l5, string9, "canReclaimUnitsOnlyWithTags", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.fo = ab2.a(l5, string9, "canRepairUnitsOnlyWithTags", (com.corrodinggames.rts.game.units.custom.h)null);
            if (l5.fn != null && !l5.aV && !l5.aU) {
                throw new RuntimeException("canReclaimUnitsOnlyWithTags requires canRepairUnits:true or canRepairBuildings:true");
            }
            if (l5.fo != null && !l5.aV && !l5.aU) {
                throw new RuntimeException("canRepairUnitsOnlyWithTags requires canRepairUnits:true or canRepairBuildings:true");
            }
            l5.eM = ab2.b(string9, "maxTransportingUnits", 0);
            if (l5.eM < 0) {
                throw new RuntimeException("maxTransportingUnits cannot be < 0");
            }
            l5.eN = ab2.b(string9, "transportUnitsUnloadDelayBetweenEachUnit", Float.valueOf(30.0f)).floatValue();
            l5.eP = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string9, "transportUnitsRequireTag", (String)null));
            String string22 = ab2.b(string9, "transportUnitsRequireMovementType", (String)null);
            if (string22 != null) {
                for (String string8 : string22.split(",")) {
                    String object102 = string8.trim();
                    l5.eQ.add((Object)ao.a(object102, "transportUnitsRequireMovementType"));
                }
            }
            l5.eO = ab2.a(string9, "transportUnitsEachUnitAlwaysUsesSingleSlot", (Boolean)false);
            l5.eR = ab2.a(string9, "transportUnitsBlockAirAndWaterUnits", (Boolean)(l5.eQ.size() == 0 ? 1 : 0));
            l5.eS = ab2.a(string9, "transportUnitsBlockOtherTransports", (Boolean)true);
            l5.eU = ab2.a(l5, string9, "transportUnitsKeepBuiltUnits", LogicBoolean.falseBoolean);
            l5.eV = ab2.a(l5, string9, "transportUnitsKillOnDeath", LogicBoolean.trueBoolean);
            l5.eW = ab2.a(l5, string9, "transportUnitsKeepWaypoints", LogicBoolean.falseBoolean);
            l5.eY = ab2.a(string9, "transportUnitsHealBy", Float.valueOf(0.0f)).floatValue();
            l5.fc = ab2.a(l5, string9, "transportUnitsCanUnloadUnits", (LogicBoolean)null);
            if (l5.fc != null) {
                l5.fd = l5.fc;
            } else {
                l5.fc = com.corrodinggames.rts.game.units.custom.l.fa;
                l5.fd = com.corrodinggames.rts.game.units.custom.l.fb;
            }
            l5.eT = ab2.a(string9, "transportUnitsAddUnloadOption", (Boolean)(l5.fc != LogicBoolean.falseBoolean ? 1 : 0));
            l5.eX = ab2.a(string9, "transportUnitsOnTeamChangeKeepCurrentTeam", (Boolean)l5.eX);
            l5.eZ = ab2.b(string9, "transportSlotsNeeded", 1);
            for (n6 = -1; n6 <= 29; ++n6) {
                String string23;
                String string15;
                String string24 = "builtFrom_" + n6 + "_";
                if (n6 == -1) {
                    string24 = "builtFrom_";
                }
                if ((string15 = ab2.b(string9, string23 = string24 + "name", (String)null)) == null) continue;
                object9 = string15.split(",");
                for (String string19 : object9) {
                    if ((string19 = string19.trim()).equals("")) continue;
                    p p2 = new p();
                    p2.a = string19;
                    p2.b = ab2.a(string9, string24 + "pos", Float.valueOf(999.0f)).floatValue();
                    p2.c = ab2.a(string9, string24 + "forceNano", (Boolean)false);
                    p2.d = "[" + string9 + "]" + string23;
                    p2.f = ab2.a(l5, string9, string24 + "isLocked", (LogicBoolean)null);
                    p2.g = ag.a(ab2, string9, string24 + "isLockedMessage", null);
                    if (p2.f == LogicBoolean.falseBoolean) {
                        p2.f = null;
                    }
                    if ("NONE".equalsIgnoreCase(string19)) continue;
                    l5.gg.add(p2);
                }
            }
            for (n6 = 0; n6 <= 50; ++n6) {
                String string26 = ab2.b(string9, "canBuild_" + n6 + "_name", (String)null);
                if (string26 == null) continue;
                String string27 = "canBuild_" + n6 + "_";
                ag.b(l5, ab2, string9, string27, false);
            }
            for (String string28 : ab2.e("canBuild_")) {
                ag.b(l5, ab2, string28, "", true);
            }
            l5.ff = be.a(l5, ab2);
            String string29 = ab2.e(string12, "movementType");
            l5.fg = ao.a(string29, "movementType");
            l5.fh = !l5.aH ? l5.fg : ao.a;
            Boolean bl6 = ab2.a(string13, "useAsBuilder", (Boolean)null);
            l5.fs = ab2.a(string13, "useAsAttacker", (Boolean)true);
            Boolean bl7 = ab2.a(string9, "isBuilder", (Boolean)null);
            if (bl7 == null) {
                bl7 = bl6 == null ? Boolean.valueOf(false) : bl6;
            } else if (bl6 == null) {
                bl6 = bl7;
            }
            if (bl6 == null) {
                bl6 = false;
            }
            l5.fp = bl7;
            l5.fq = bl6;
            if (!l5.fp && l5.fq) {
                throw new RuntimeException("Cannot tell AI to use a non-builder as builder [ai]useAsBuilder:" + l5.fq + " [core]isBuilder:" + l5.fp);
            }
            if (l5.fk) {
                l5.fr = true;
            }
            if ((bl3 = ab2.a(string13, "useAsHarvester", (Boolean)null)) != null) {
                l5.fr = bl3;
            }
            if ((object9 = ab2.a(string13, "useAsTransport", (Boolean)null)) == null) {
                object9 = l5.eM > 0 && !l5.fq && !l5.aH;
                if (!l5.eT) {
                    object9 = false;
                }
            }
            l5.ft = (Boolean)object9;
            l5.as = l5.aH ? com.corrodinggames.rts.game.units.d.d.q : (l5.fg == ao.d ? com.corrodinggames.rts.game.units.b.b.n : (l5.fg == ao.e ? com.corrodinggames.rts.game.units.h.f.q : (l5.fg == ao.f ? (l5.cm ? com.corrodinggames.rts.game.units.e.j.dO : (l5.l() ? com.corrodinggames.rts.game.units.e.b.h : com.corrodinggames.rts.game.units.e.h.n)) : (l5.cm ? com.corrodinggames.rts.game.units.e.j.dO : (l5.l() ? com.corrodinggames.rts.game.units.e.b.h : com.corrodinggames.rts.game.units.e.j.dN)))));
            com.corrodinggames.rts.gameFramework.m.e e4 = l5.a(ab2, string10, "icon_zoomed_out", false);
            if (e4 != null) {
                l5.as = l5.a(e4, l5.ac);
            }
            if (ab2.a(string10, "icon_zoomed_out_neverShow", (Boolean)false).booleanValue()) {
                l5.as = null;
            }
            l5.t = ab2.a(string10, "showHealthBar", (Boolean)true);
            l5.u = ab2.a(string10, "showHealthBarChanges", (Boolean)true);
            l5.v = ab2.a(string10, "showEnergyBar", (Boolean)true);
            l5.w = ab2.a(string10, "showShotDelayBar", (Boolean)true);
            l5.x = ab2.a(string10, "showTransportBar", (Boolean)true);
            l5.y = ab2.a(string10, "showShieldBar", (Boolean)true);
            l5.z = ab2.a(string10, "showQueueBar", (Boolean)true);
            l5.A = ab2.a(string10, "showSelectionIndicator", (Boolean)true);
            l5.fi = ab2.a(string12, "slowDeathFall", (Boolean)false);
            l5.fj = ab2.a(string12, "slowDeathFallSmoke", (Boolean)true);
            l5.cL.j = ab2.a(string12, "moveSpeed", Float.valueOf(1.0f)).floatValue() * l5.aG;
            l5.dN = ab2.a(string12, "moveAccelerationSpeed", Float.valueOf(1.0f)).floatValue() * l5.aG;
            l5.dO = ab2.a(string12, "moveDecelerationSpeed", Float.valueOf(1.0f)).floatValue() * l5.aG;
            Boolean bl8 = ab2.a(string12, "ignoreMoveOrders", (Boolean)null);
            if (l5.aH) {
                l5.dP = true;
            }
            if (bl8 != null) {
                if (bl8.booleanValue()) {
                    l5.dP = true;
                    if (l5.cL.j > 0.0f) {
                        throw new RuntimeException("[movement]ignoreMoveOrders expects moveSpeed=0");
                    }
                } else if (l5.aH) {
                    throw new RuntimeException("[movement]ignoreMoveOrders=false not yet supported on buildings");
                }
            }
            l5.ej = ab2.a(string12, "moveYAxisScaling", Float.valueOf(1.0f)).floatValue();
            if (l5.ej <= 0.0f) {
                throw new RuntimeException("[movement]moveYAxisScaling must be > 0");
            }
            l5.ek = 1.0f / l5.ej;
            l5.el = ab2.a(string12, "reverseSpeedPercentage", Float.valueOf(0.6f)).floatValue();
            String string20 = ab2.b(string12, "landOnGround", "false");
            if (string20.equalsIgnoreCase("false")) {
                l5.dQ = false;
            } else if (string20.equalsIgnoreCase("onlyIdle")) {
                l5.dQ = true;
                l5.dR = true;
            } else if (string20.equalsIgnoreCase("true")) {
                l5.dQ = true;
            } else {
                throw new RuntimeException("landOnGround expected:true, false, onlyIdle, not:" + string20);
            }
            float f8 = 0.0f;
            float f11 = 0.0f;
            if (l5.fg == ao.d) {
                f8 = 35.0f;
                f11 = 1.5f;
            }
            l5.dS = ab2.a(string12, "startingHeightOffset", Float.valueOf(0.0f)).floatValue();
            l5.cL.q = ab2.a(string12, "targetHeight", Float.valueOf(f8)).floatValue();
            l5.dT = ab2.a(string12, "targetHeightDrift", Float.valueOf(f11)).floatValue();
            if (l5.cL.q > 80.0f) {
                l5.B = true;
            }
            l5.dU = ab2.a(string12, "heightChangeRate", Float.valueOf(l5.dU)).floatValue();
            l5.dV = ab2.a(string12, "fallingAcceleration", Float.valueOf(l5.dV)).floatValue();
            l5.dW = ab2.a(string12, "fallingAccelerationDead", Float.valueOf(l5.dW)).floatValue();
            l5.cL.k = ab2.a(string12, "maxTurnSpeed", Float.valueOf(1.0f)).floatValue();
            l5.eo = ab2.a(string12, "turnAcceleration", Float.valueOf(1.0f)).floatValue();
            l5.dX = ab2.a(string12, "moveSlidingMode", (Boolean)false);
            l5.dY = ab2.a(string12, "moveIgnoringBody", (Boolean)false);
            l5.dZ = ab2.b(string12, "moveSlidingDir", -1);
            l5.ei = ab2.a(string12, "joinsGroupFormations", (Boolean)true);
            l5.ea = ab2.a(string11, "turretSize", Float.valueOf(1.0f)).floatValue() * l5.aG;
            l5.eb = ab2.a(string11, "turretTurnSpeed", Float.valueOf(8.0f)).floatValue();
            l5.dL = ab2.a(string11, "turretRotateWithBody", (Boolean)true);
            String string23 = ab2.b(string11, "attackMovement", "normal");
            l5.ec = com.corrodinggames.rts.game.units.b.a;
            if (string23.equalsIgnoreCase("normal")) {
                l5.ec = com.corrodinggames.rts.game.units.b.a;
            }
            if (string23.equalsIgnoreCase("strafing")) {
                l5.ec = com.corrodinggames.rts.game.units.b.b;
            }
            if (string23.equalsIgnoreCase("bomber")) {
                l5.ec = com.corrodinggames.rts.game.units.b.d;
            }
            l5.ef = ab2.a(string11, "disablePassiveTargeting", (Boolean)false);
            l5.eg = ab2.a(string11, "stopTargetingAfterFiring", (Boolean)false);
            l5.eh = ab2.a(string11, "turretMultiTargeting", (Boolean)false);
            l5.ed = ab2.a(string11, "attackMovementSpeed", Float.valueOf(1.0f)).floatValue();
            l5.ee = ab2.a(string11, "attackMovementSpread", Float.valueOf(1.0f)).floatValue();
            Float f12 = ab2.a(string11, "maxAttackRange", (Float)null);
            if (f12 != null) {
                bl2 = true;
                l5.cL.i = f12.floatValue() * l5.aG;
            } else {
                bl2 = false;
                l5.cL.i = 100.0f * l5.aG;
            }
            l5.ez = ab2.a(string11, "aimOffsetSpread", Float.valueOf(0.6f)).floatValue();
            l5.dM = ab2.b(string11, "shootDelay", Float.valueOf(50.0f)).floatValue();
            l5.cL.e = ab2.a(string11, "shootDelayMultiplier", Float.valueOf(1.0f)).floatValue();
            l5.cL.f = ab2.a(string11, "shootDamageMultiplier", Float.valueOf(1.0f)).floatValue();
            l5.dK = ab2.a(string11, "showRangeUIGuide", (Boolean)null);
            l5.eF = ab2.a(string11, "isMelee", (Boolean)false);
            l5.eG = 0.0f;
            Float f13 = ab2.a(string11, "meleeEngangementDistance", (Float)null);
            if (l5.eF) {
                l5.eG = 250.0f;
                if (f13 != null) {
                    l5.eG = f13.floatValue();
                }
            } else if (f13 != null) {
                throw new RuntimeException("[attack]meleeEngangementDistance can only be used with isMelee:true");
            }
            ag.a(l4, ah.k);
            for (String string24 : ab2.e("projectile_")) {
                object8 = string24.substring("projectile_".length());
                if (l5.f((String)object8) != null) {
                    throw new RuntimeException("Two projectiles found with the same name:" + (String)object8);
                }
                object72 = new bh();
                ((bh)object72).bh = object8;
                ((bh)object72).bj = l5;
                bh.a((bh)object72, l5, ab2, string24);
            }
            int n13 = l5.fT.size();
            if (n13 < 1) {
                n13 = 1;
            }
            l5.fR = new bh[n13];
            for (n5 = 0; n5 < l5.fT.size(); ++n5) {
                object8 = (bh)l5.fT.get(n5);
                ((bh)object8).bi = n5;
                l5.fR[n5] = object8;
            }
            for (n5 = 0; n5 < l5.fR.length; ++n5) {
                object8 = l5.fR[n5];
                if (object8 == null) continue;
                ((bh)object8).w *= l5.aG;
                ((bh)object8).au *= l5.aG;
                ((bh)object8).aF *= l5.aG;
            }
            if (l5.fR[0] == null) {
                bh bh2 = new bh();
                bh2.bi = 0;
                bh2.bh = "1";
                bh2.b = 10;
                l5.fT.add(bh2);
                l5.fR[0] = bh2;
            }
            ArrayList arrayList2 = l5.fS;
            for (Object object72 : ab2.e("turret_")) {
                String string25 = ((String)object72).substring("turret_".length());
                if (l5.e(string25) != null) {
                    throw new RuntimeException("Two turrets found with the same name:" + string25);
                }
                object6 = new bn();
                ((bn)object6).a = string25;
                ((bn)object6).b = object72;
                arrayList2.add(object6);
            }
            for (Object object72 : arrayList2) {
                bn.a((bn)object72, l5, ab2, ((bn)object72).b);
            }
            if (arrayList2.size() == 0) {
                object8 = new bn();
                ((bn)object8).f = 0.0f;
                ((bn)object8).g = 0.0f;
                ((bn)object8).a = "1";
                ((bn)object8).m = l5.dM;
                arrayList2.add(object8);
            }
            for (n4 = arrayList2.size() - 1; n4 >= 0; --n4) {
                if (arrayList2.get(n4) == null) continue;
                ((bn)arrayList2.get((int)n4)).e = n4;
            }
            for (n4 = arrayList2.size() - 1; n4 >= 0; --n4) {
                if (arrayList2.get(n4) == null) continue;
                object72 = (bn)arrayList2.get(n4);
                if (((bn)object72).y != null) {
                    ((bn)object72).w = ((bn)object72).y.e;
                    if (((bn)object72).y.y != null) {
                        throw new RuntimeException(((bn)object72).a + ": Turret can not be attached to turret that is also attached to a turret");
                    }
                }
                if (((bn)object72).z != null) {
                    ((bn)object72).x = ((bn)object72).z.e;
                }
                if (!(((bn)object72).W < 0.0f)) continue;
                ((bn)object72).W = ((bn)object72).V;
            }
            if (arrayList2.size() > 31) {
                throw new RuntimeException("Turret max count per unit is: 31");
            }
            l5.fQ = arrayList2.toArray(new bn[0]);
            l5.dJ = l5.cL.i;
            float f14 = -1.0f;
            boolean bl9 = true;
            boolean bl10 = false;
            object6 = arrayList2.iterator();
            while (object6.hasNext()) {
                bn bn2 = (bn)object6.next();
                bn2.X *= l5.aG;
                bn2.f *= l5.aG;
                bn2.g *= l5.aG;
                bn2.Y *= l5.aG;
                bn2.Z *= l5.aG;
                boolean bl11 = false;
                if (bn2.B) {
                    if (bn2.ab >= 99999.0f) {
                        bl9 = false;
                    } else {
                        bl10 = true;
                        if (l5.dJ > bn2.ab) {
                            l5.dJ = bn2.ab;
                        }
                        if (f14 < bn2.ab) {
                            f14 = bn2.ab;
                        }
                        if (com.corrodinggames.rts.gameFramework.f.c(bn2.ab - l5.cL.i) > 5.0f) {
                            n3 = 0;
                            for (Object object52 : l5.o) {
                                if (!(com.corrodinggames.rts.gameFramework.f.c(bn2.ab - ((com.corrodinggames.rts.game.units.custom.y)object52).a) < 5.0f)) continue;
                                n3 = 1;
                            }
                            if (n3 == 0) {
                                bl11 = true;
                            }
                        }
                    }
                }
                if (bn2.ac != null) {
                    bl11 = bn2.ac;
                }
                if (!bl11) continue;
                com.corrodinggames.rts.game.units.custom.y y2 = new com.corrodinggames.rts.game.units.custom.y();
                y2.a = bn2.ab;
                l5.o.add(y2);
            }
            if (bl10 && bl9) {
                if (!bl2) {
                    l5.cL.i = f14;
                } else if (f14 < l5.cL.i) {
                    throw new RuntimeException("limitingRange as been applied to all turrets but is less than maxAttackRange (hint: unset maxAttackRange or a limitingRange, or make values match)");
                }
            }
            if ((object6 = ab2.b(string11, "setMainTurretAs", (String)null)) != null) {
                l5.dF = l5.e((String)object6);
                if (l5.dF == null) {
                    throw new RuntimeException("[attack] Could not find setMainTurretAs with name: " + (String)object6);
                }
            } else {
                l5.dF = l5.e("1");
                if (l5.dF == null) {
                    l5.dF = l5.fQ[0];
                }
            }
            l5.dG = l5.dF.e;
            ag.a(l4, ah.l);
            long l7 = br.a();
            if (ab2.l(string9, "action_")) {
                for (n3 = 0; n3 <= 50; ++n3) {
                    ag.a(l5, ab2, string9, "action_" + n3 + "_", "" + n3, false, false);
                }
            }
            for (Object object42 : ab2.e("action_")) {
                object52 = ((String)object42).substring("action_".length());
                if (l5.g((String)object52) != null) {
                    throw new RuntimeException("Two actions found with the same name:" + (String)object52);
                }
                ag.a(l5, ab2, (String)object42, "", (String)object52, true, false);
            }
            for (Object object42 : ab2.e("hiddenAction_")) {
                object52 = ((String)object42).substring("hiddenAction_".length());
                if (l5.g((String)object52) != null) {
                    throw new RuntimeException("Two actions found with the same name:" + (String)object52);
                }
                ag.a(l5, ab2, (String)object42, "", (String)object52, true, true);
            }
            ag.a(l7, ah.j);
            ArrayList arrayList3 = new ArrayList();
            object42 = new ArrayList();
            for (int i2 = 0; i2 <= 1; ++i2) {
                boolean bl12 = i2 == 0;
                arrayList5 = bl12 ? arrayList3 : object42;
                for (n2 = 1; n2 < 21; ++n2) {
                    Object object10 = object3 = bl12 ? "leg_" + n2 : "arm_" + n2;
                    if (ab2.g((String)object3)) {
                        Object arrayList6 = new ba();
                        ba.a((ba)arrayList6, l5, ab2, (String)object3, bl12, arrayList5);
                        arrayList5.add(arrayList6);
                        continue;
                    }
                    arrayList5.add(null);
                }
            }
            ArrayList<Object> arrayList4 = new ArrayList<Object>();
            Iterator iterator = arrayList3.iterator();
            while (iterator.hasNext()) {
                arrayList5 = (ba)iterator.next();
                if (arrayList5 == null) continue;
                arrayList4.add(arrayList5);
            }
            iterator = ((ArrayList)object42).iterator();
            while (iterator.hasNext()) {
                arrayList5 = (ba)iterator.next();
                if (arrayList5 == null) continue;
                arrayList4.add(arrayList5);
            }
            int n14 = arrayList4.size() - 1;
            while (n14 >= 0) {
                arrayList5 = (ba)arrayList4.get(n14);
                ((ba)((Object)arrayList5)).a = n14--;
            }
            l5.ax = arrayList4.toArray(new ba[0]);
            if (l5.ax.length > 0) {
                l5.a(com.corrodinggames.rts.game.units.custom.b.h.a);
            }
            for (ArrayList arrayList5 : l5.dr) {
                ((com.corrodinggames.rts.game.units.custom.f)((Object)arrayList5)).a(l5);
            }
            ag.b(l5);
            String string26 = ab2.b(string9, "fireTurretXAtSelfOnDeath", (String)null);
            if (string26 != null && !"NONE".equalsIgnoreCase(string26)) {
                arrayList5 = l5.e(string26);
                if (arrayList5 == null) {
                    throw new RuntimeException("Cannot find turret:" + (String)string26 + " for [" + string9 + "]fireTurretXAtSelfOnDeath");
                }
                l5.bB = ((bn)((Object)arrayList5)).e;
            }
            com.corrodinggames.rts.game.units.custom.b.c.a(l5, ab2);
            l5.bj = ab2.a(string11, "dieOnAttack", (Boolean)false);
            l5.bl = ab2.a(string11, "removeOnAttack", (Boolean)false);
            l5.ep = ab2.d(string11, "canAttack");
            if (l5.ep) {
                l5.eq = ab2.a(l5, string11, "canAttackFlyingUnits");
                l5.er = ab2.a(l5, string11, "canAttackLandUnits");
                l5.es = ab2.a(l5, string11, "canAttackUnderwaterUnits");
            } else {
                l5.eq = ab2.a(l5, string11, "canAttackFlyingUnits", LogicBoolean.falseBoolean);
                l5.er = ab2.a(l5, string11, "canAttackLandUnits", LogicBoolean.falseBoolean);
                l5.es = ab2.a(l5, string11, "canAttackUnderwaterUnits", LogicBoolean.falseBoolean);
            }
            l5.et = ab2.a(l5, string11, "canAttackNotTouchingWaterUnits", (LogicBoolean)null);
            if (LogicBoolean.isStaticTrue(l5.et)) {
                l5.et = null;
            }
            l5.ev = ab2.a(l5, string11, "canOnlyAttackUnitsWithTags", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.ew = ab2.a(l5, string11, "canOnlyAttackUnitsWithoutTags", (com.corrodinggames.rts.game.units.custom.h)null);
            if (l5.ev != null || l5.ew != null) {
                l5.eu = true;
            }
            boolean bl13 = false;
            n2 = 0;
            for (Object arrayList6 : arrayList2) {
                if (((bn)arrayList6).O != null && ((bn)arrayList6).O.a(l5.ev)) {
                    ((bn)arrayList6).O = null;
                }
                if (((bn)arrayList6).P != null && ((bn)arrayList6).P.a(l5.ew)) {
                    ((bn)arrayList6).P = null;
                }
                if (!((bn)arrayList6).B) continue;
                if (((bn)arrayList6).O != null || ((bn)arrayList6).P != null) {
                    bl13 = true;
                    continue;
                }
                n2 = 1;
            }
            if (bl13 && n2 == 0) {
                l5.ex = true;
                l5.eu = true;
            }
            l5.ey = ab2.a(string11, "isFixedFiring", (Boolean)false);
            l5.fM = ab2.a(string13, "lowPriorityTargetForOtherUnits", (Boolean)false);
            l5.fN = ab2.a(string13, "notPassivelyTargetedByOtherUnits", (Boolean)false);
            if (l5.ep && l5.fN) {
                throw new RuntimeException("[ai]notPassivelyTargetedByOtherUnits is cannot currently supported on units that can attack");
            }
            l5.fv = ab2.a(l5, string13, "aiTags", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.fw = ab2.a(string13, "disableUse", (Boolean)false);
            l5.fz = ab2.a(string13, "buildPriority", Float.valueOf(0.05f)).floatValue();
            l5.fA = ab2.b(string13, "recommendedInEachBaseNum", 0);
            l5.fB = ab2.a(string13, "recommendedInEachBasePriorityIfUnmet", Float.valueOf(0.5f)).floatValue();
            l5.fy = ab2.b(string13, "maxEachBase", com.corrodinggames.rts.gameFramework.f.b(2, l5.fA));
            l5.fx = ab2.b(string13, "maxGlobal", -1);
            if (l5.fy < l5.fA) {
                throw new RuntimeException("[ai]recommendedInEachBaseNum is smaller than maxEachBase");
            }
            if (!l5.aH) {
                if (ab2.n(string13, "recommendedInEachBaseNum")) {
                    throw new RuntimeException("[ai]recommendedInEachBaseNum currently only applies to buildings");
                }
                if (ab2.n(string13, "recommendedInEachBasePriorityIfUnmet")) {
                    throw new RuntimeException("[ai]recommendedInEachBasePriorityIfUnmet currently only applies to buildings");
                }
            }
            l5.fE = ab2.b(string13, "whenUsingAsHarvester_recommendedInEachBase", -1);
            l5.fF = ab2.b(string13, "whenUsingAsHarvester_recommendedGlobal", -1);
            l5.fG = ab2.a(string13, "whenUsingAsHarvester_includeOtherHarvesterCounts", (Boolean)false);
            l5.fH = ab2.a(l5, string13, "onlyUseAsHarvester_ifBaseHasUnitTagged", (com.corrodinggames.rts.game.units.custom.h)null);
            l5.fC = ab2.a(string13, "nonInBaseExtraPriority", Float.valueOf(0.04f)).floatValue();
            l5.fC = ab2.a(string13, "noneInBaseExtraPriority", Float.valueOf(l5.fC)).floatValue();
            l5.fD = ab2.a(string13, "nonGlobalExtraPriority", Float.valueOf(0.0f)).floatValue();
            l5.fD = ab2.a(string13, "noneGlobalExtraPriority", Float.valueOf(l5.fD)).floatValue();
            l5.fI = ab2.b(string13, "upgradedFrom", (String)null);
            object3 = ab2.a(string13, "ai_upgradePriority", (Float)null);
            if (object3 != null && ((Float)object3).floatValue() != -1.0f) {
                if (((Float)object3).floatValue() >= 0.0f && ((Float)object3).floatValue() <= 1.0f) {
                    l5.fK = ((Float)object3).floatValue() * 100.0f;
                } else {
                    throw new RuntimeException("[ai]ai_upgradePriority: " + l5.fK + " must be between 0-1 or -1 for default");
                }
            }
            if (l5.ep) {
                for (int i3 = 0; i3 < l5.fQ.length; ++i3) {
                    object22 = l5.fQ[i3];
                    if (!((bn)object22).B || ((bn)object22).ao != null || !l5.w) continue;
                    if (((bn)object22).m > 140.0f && (l5.em == -1 || l5.fQ[l5.em].m < ((bn)object22).m)) {
                        l5.em = i3;
                    }
                    if (!(((bn)object22).n > 80.0f)) continue;
                    l5.en = i3;
                }
            }
            if (l5.cI == -2) {
                l5.cI = l5.fg == ao.d ? 5 : (l5.j() ? (l5.al != null ? 3 : 2) : (l5.cL.q < -2.0f ? 1 : (l5.eM > 0 ? 3 : 2)));
            }
            if (l5.fW.size() > 0) {
                l5.fX = true;
                com.corrodinggames.rts.gameFramework.utility.m m4 = new com.corrodinggames.rts.gameFramework.utility.m();
                object22 = new com.corrodinggames.rts.gameFramework.utility.m();
                object = new com.corrodinggames.rts.gameFramework.utility.m();
                for (r r2 : l5.fW) {
                    if (r2.c == com.corrodinggames.rts.game.units.custom.s.a) {
                        m4.add(r2);
                        continue;
                    }
                    if (r2.c == com.corrodinggames.rts.game.units.custom.s.b) {
                        ((com.corrodinggames.rts.gameFramework.utility.m)object22).add(r2);
                        continue;
                    }
                    if (r2.c == com.corrodinggames.rts.game.units.custom.s.c) {
                        ((com.corrodinggames.rts.gameFramework.utility.m)object).add(r2);
                        continue;
                    }
                    throw new RuntimeException("Unknown check rate:" + (Object)((Object)r2.c));
                }
                l5.fY = (r[])m4.toArray(new r[0]);
                l5.fZ = (r[])((com.corrodinggames.rts.gameFramework.utility.m)object22).toArray(new r[0]);
                l5.ga = (r[])((com.corrodinggames.rts.gameFramework.utility.m)object).toArray(new r[0]);
            }
            if (l5.gp != null && l5.gp.size() > 0) {
                for (Object object22 : l5.gp) {
                    ((u)object22).a(l5);
                }
            }
            if (l5.gb.a > 0) {
                for (Object object22 : l5.gb) {
                    ((t)object22).a(l5);
                }
                l5.gb.clear();
            }
            ag.a(l4, ah.m);
            ab2.b();
            for (Object object22 : ab2.d) {
                if (((ac)object22).a() != null && (((ac)object22).a().startsWith("hiddenAction_") || ((ac)object22).a().startsWith("canBuild_"))) {
                    throw new RuntimeException("Error [" + ((ac)object22).a() + "]" + ((ac)object22).b() + " has been repeated");
                }
                object = "Repeated key " + object22;
                l5.r((String)object);
                if (l5.R < 1) continue;
                com.corrodinggames.rts.gameFramework.l.e("Converting warning to error (meta.strictLevel=" + l5.R + ")");
                throw new bo((String)object);
            }
            for (Object object22 : ab2.e) {
                object = "Skipping line, unexpected format: '" + (String)object22 + "'";
                l5.r((String)object);
                if (l5.R < 1) continue;
                com.corrodinggames.rts.gameFramework.l.e("Converting warning to error (meta.strictLevel=" + l5.R + ")");
                throw new bo((String)object);
            }
            if (b2 != null) {
                ++b2.E;
            }
            ArrayList arrayList6 = com.corrodinggames.rts.game.units.custom.l.c;
            synchronized (arrayList6) {
                com.corrodinggames.rts.game.units.custom.l.c.add(l5);
            }
            ag.a(l4, ah.n);
            return l5;
        }
        catch (RuntimeException runtimeException) {
            ag.a(string, (Exception)runtimeException, b2);
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            ++l;
            ag.a(string, (Exception)new RuntimeException(outOfMemoryError), b2);
            return null;
        }
        catch (bo bo2) {
            ag.a(string, (Exception)bo2, b2);
            return null;
        }
    }

    public static void a(String string, Exception exception, com.corrodinggames.rts.game.units.as as2) {
        com.corrodinggames.rts.gameFramework.i.b b2 = null;
        if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
            b2 = l2.J;
        }
        ag.a(string, exception, b2);
    }

    public static String a(com.corrodinggames.rts.gameFramework.i.b b2, String string, boolean bl2) {
        if (b2 != null) {
            String string2 = b2.q;
            string2 = com.corrodinggames.rts.gameFramework.e.a.o(string2);
            if ((string = com.corrodinggames.rts.gameFramework.e.a.o(string)).startsWith(string2)) {
                if ((string = string.substring(string2.length())).startsWith("/")) {
                    string = string.substring(1);
                }
                if (string.startsWith("\\")) {
                    string = string.substring(1);
                }
            }
            if (bl2) {
                string = string + " (in mod " + b2.a() + ")";
            }
        }
        return string;
    }

    public static void a(String string, Exception exception, com.corrodinggames.rts.gameFramework.i.b b2) {
        String string2;
        com.corrodinggames.rts.gameFramework.l.b("Error while loading unit:" + string);
        com.corrodinggames.rts.gameFramework.l.c(exception);
        if (string == null) {
            string = "<null>";
        }
        if ((string2 = exception instanceof bo ? exception.getMessage() : com.corrodinggames.rts.gameFramework.f.b(exception)) == null) {
            string2 = "<No error cause>";
        }
        if (!string2.contains("unit config file")) {
            string2 = string2.replace(string + ": ", "");
            string2 = string2.replace(string, "");
        }
        string = ag.a(b2, string, true);
        String string3 = b2 != null ? "Error loading unit: " + string + ": \n" + string2 : (string2.contains("Error loading core unit") ? string2 : "Error loading core unit: " + string + ": \n" + string2 + " (This might be from placing a mod in 'assets/', they should go under 'mods/')");
        if (exception instanceof bo) {
            bo bo2 = (bo)exception;
            if (bo2.c != null || bo2.d != null) {
                string3 = string3 + " (section:" + bo2.c + ", key:" + bo2.d + ")";
            }
        }
        boolean bl2 = false;
        if (b2 != null) {
            bl2 = b2.f;
        }
        if (!bl2) {
            // empty if block
        }
        if (s != null) {
            s = string3;
        }
        if (b2 == null) {
            try {
                Thread.sleep(2L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            throw new RuntimeException(string3, exception);
        }
        b2.a(string3);
    }

    public static void b(com.corrodinggames.rts.game.units.custom.l l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, boolean bl2) {
        String[] stringArray;
        String string3 = ab2.b(string, string2 + "name", (String)null);
        if (string3 == null) {
            return;
        }
        for (String string4 : stringArray = string3.split(",")) {
            com.corrodinggames.rts.game.units.custom.d.b b2;
            string4 = string4.trim();
            com.corrodinggames.rts.game.units.custom.a.d d2 = new com.corrodinggames.rts.game.units.custom.a.d();
            d2.k = string4;
            d2.o = ab2.a(string, string2 + "extraLagHidingInUI", (Boolean)false);
            d2.p = ab2.a(string, string2 + "pos", Float.valueOf(999.0f)).floatValue();
            d2.aJ = ab2.b(string, string2 + "tech", 1);
            d2.aK = ab2.a(string, string2 + "forceNano", (Boolean)false);
            d2.aL = ab2.b(string, string2 + "type", (String)null);
            d2.q = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "price", null);
            d2.aF = ab2.a(l2, string, string2 + "isGuiBlinking", (LogicBoolean)null);
            d2.v = ab2.a(l2, string, string2 + "isVisible", (LogicBoolean)null);
            d2.z = ab2.a(l2, string, string2 + "isLocked", (LogicBoolean)null);
            d2.A = ag.a(l2, ab2, string, string2 + "isLockedMessage", null);
            if (d2.z != null) {
                d2.y = true;
            }
            if (d2.z == LogicBoolean.falseBoolean) {
                d2.z = null;
            }
            d2.B = ab2.a(l2, string, string2 + "isLockedAlt", (LogicBoolean)null);
            d2.C = ag.a(l2, ab2, string, string2 + "isLockedAltMessage", null);
            if (d2.B != null) {
                d2.y = true;
            }
            if (d2.B == LogicBoolean.falseBoolean) {
                d2.B = null;
            }
            d2.D = ab2.a(l2, string, string2 + "isLockedAlt2", (LogicBoolean)null);
            d2.E = ag.a(l2, ab2, string, string2 + "isLockedAlt2Message", null);
            if (d2.D != null) {
                d2.y = true;
            }
            if (d2.D == LogicBoolean.falseBoolean) {
                d2.D = null;
            }
            if ((b2 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "addResources", true)) != null && b2.d()) {
                d2.ae = b2;
            }
            d2.aM = com.corrodinggames.rts.game.units.custom.a.f.a;
            if ("NONE".equalsIgnoreCase(string4)) continue;
            l2.gh.add(d2);
        }
    }

    public static void a(com.corrodinggames.rts.game.units.custom.l l2, com.corrodinggames.rts.gameFramework.utility.ab ab2, String string, String string2, String string3, boolean bl2, boolean bl3) {
        com.corrodinggames.rts.game.units.custom.a.d d2 = new com.corrodinggames.rts.game.units.custom.a.d();
        String string4 = ab2.b(string, string2 + "convertTo", (String)null);
        String string5 = ab2.b(string, string2 + "whenBuilding_temporarilyConvertTo", (String)null);
        at[] atArray = as.a(ab2, string, string2 + "whenBuilding_temporarilyConvertTo_keepFields", null);
        Float f2 = ab2.a(string, string2 + "addEnergy", (Float)null);
        com.corrodinggames.rts.game.units.custom.d.b b2 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "addResources", true);
        l2.a(b2);
        com.corrodinggames.rts.game.units.custom.d.b b3 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "addResourcesScaledByAIHandicaps", true);
        l2.a(b3);
        String string6 = ab2.b(string, string2 + "fireTurretXAtGround", (String)null);
        LogicBoolean logicBoolean = ab2.b(l2, string, string2 + "alsoTriggerOrQueueActionWithTarget", null);
        LogicBoolean logicBoolean2 = ab2.a(l2, string, string2 + "alsoTriggerOrQueueActionConditional", (LogicBoolean)null);
        String string7 = ab2.b(string, string2 + "alsoTriggerAction", (String)null);
        LogicBoolean logicBoolean3 = ab2.c(l2, string, string2 + "alsoTriggerActionRepeat", null);
        Object var19_19 = null;
        String string8 = ab2.b(string, string2 + "alsoQueueAction", (String)null);
        String string9 = ab2.b(string, string2 + "spawnEffects", (String)null);
        String string10 = ab2.b(string, string2 + "spawnEffectsOnQueue", (String)null);
        String string11 = ab2.b(string, string2 + "playSoundAtUnit", (String)null);
        String string12 = ab2.b(string, string2 + "playSoundGlobally", (String)null);
        String string13 = ab2.b(string, string2 + "playSoundToPlayer", (String)null);
        String string14 = ab2.b(string, string2 + "playSoundToPlayerOnQueue", (String)null);
        com.corrodinggames.rts.game.units.custom.a.a.o.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.e.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.h.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.a.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.k.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.b.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.d.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.l.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.g.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.m.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.f.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.j.a(l2, ab2, string, string2, d2, string3, bl2);
        com.corrodinggames.rts.game.units.custom.a.a.i.a(l2, ab2, string, string2, d2, string3, bl2);
        LogicBoolean logicBoolean4 = ab2.a(l2, string, string2 + "resetCustomTimer", (LogicBoolean)null);
        boolean bl4 = false;
        if (bl2) {
            bl4 = true;
        } else {
            if (string4 != null || string5 != null || f2 != null || string6 != null) {
                bl4 = true;
            }
            if (b2.d() || b3.d()) {
                bl4 = true;
            }
            if (string7 != null || string8 != null || string9 != null || var19_19 != null) {
                bl4 = true;
            }
            if (string11 != null || string12 != null || string13 != null || string14 != null) {
                bl4 = true;
            }
            if (d2.ac.size() > 0) {
                bl4 = true;
            }
        }
        if (bl4) {
            Object object;
            Object object2;
            Object object3;
            Object object4;
            Cloneable cloneable;
            Object object5;
            String string15;
            String string16;
            if ("NONE".equalsIgnoreCase(string4)) {
                string4 = null;
            }
            if ("NONE".equalsIgnoreCase(string5)) {
                string5 = null;
            }
            if (string6 != null && string6.equalsIgnoreCase("NONE")) {
                string6 = null;
            }
            d2.a = l2.gh.size();
            String string17 = ab2.b(string, string2 + "id", (String)null);
            if (string17 != null) {
                d2.b = "c" + string17;
                if (d2.b.contains(" ")) {
                    throw new RuntimeException("[" + string + "]id cannot contain space");
                }
                if (d2.b.contains(",")) {
                    throw new RuntimeException("[" + string + "]id cannot contain ,");
                }
                if (d2.b.contains(":")) {
                    throw new RuntimeException("[" + string + "]id cannot contain :");
                }
                if (d2.b.contains("(")) {
                    throw new RuntimeException("[" + string + "]id cannot contain (");
                }
                if (d2.b.contains("\u0000")) {
                    throw new RuntimeException("[" + string + "]id cannot contain null");
                }
                if (d2.b.length() > 15) {
                    throw new RuntimeException("[" + string + "]id cannot be longer than 15 characters");
                }
                for (com.corrodinggames.rts.game.units.custom.a.d d3 : l2.gh) {
                    if (!d2.b.equalsIgnoreCase(d3.b)) continue;
                    throw new RuntimeException("[" + string + "]id more than one action exists with id: " + string17);
                }
            }
            d2.c = string3;
            d2.o = ab2.a(string, string2 + "extraLagHidingInUI", (Boolean)false);
            d2.s = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string, string2 + "tags", (String)null));
            d2.p = ab2.a(string, string2 + "pos", Float.valueOf(999.0f)).floatValue();
            d2.q = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "price", true);
            d2.r = com.corrodinggames.rts.game.units.custom.d.b.b(l2, ab2, string, string2 + "streamingCost", null);
            boolean bl5 = ab2.a(string, string2 + "switchPriceWithStreamingCost", (Boolean)false);
            if (bl5) {
                if (d2.r != null) {
                    throw new RuntimeException("[" + string + "]streamingCost and switchPriceWithStreamingCost=true cannot be used at the same time");
                }
                d2.r = com.corrodinggames.rts.game.units.custom.d.b.b(l2, ab2, string, string2 + "price", null);
                d2.q = com.corrodinggames.rts.game.units.custom.d.b.a;
            }
            l2.a(d2.q);
            if (d2.r != null) {
                l2.a(d2.r);
            }
            d2.K = ab2.a(string, string2 + "highPriorityQueue", (Boolean)false);
            d2.L = ab2.a(string, string2 + "onlyOneUnitAtATime", (Boolean)false);
            d2.M = ab2.a(string, string2 + "canPlayerCancel", (Boolean)true);
            d2.O = ab2.a(string, string2 + "alwaysSinglePress", (Boolean)false);
            d2.N = ab2.a(string, string2 + "allowMultipleInQueue", (Boolean)true);
            if (!d2.M && !d2.N && d2.O) {
                d2.P = true;
            }
            d2.j = !d2.M ? com.corrodinggames.rts.game.units.a.u.a : com.corrodinggames.rts.game.units.a.u.c;
            d2.t = ab2.a(l2, string, string2 + "requireConditional", (LogicBoolean)null);
            d2.u = ab2.a(l2, string, string2 + "isActive", (LogicBoolean)null);
            d2.v = ab2.a(l2, string, string2 + "isVisible", (LogicBoolean)null);
            d2.x = ab2.a(string, string2 + "isAlsoViewableByEnemies", (Boolean)false);
            d2.w = ab2.a(string, string2 + "isAlsoViewableByAllies", (Boolean)d2.x);
            if (bl3) {
                if (d2.v != null && !LogicBoolean.isStaticFalse(d2.v)) {
                    throw new RuntimeException("[" + string + "]isVisible doesn't make sense to use in hidden actions");
                }
                d2.v = LogicBoolean.falseBoolean;
            }
            d2.z = ab2.a(l2, string, string2 + "isLocked", (LogicBoolean)null);
            d2.A = ag.a(l2, ab2, string, string2 + "isLockedMessage", null);
            if (d2.z != null) {
                d2.y = true;
            }
            if (d2.z == LogicBoolean.falseBoolean) {
                d2.z = null;
            }
            d2.B = ab2.a(l2, string, string2 + "isLockedAlt", (LogicBoolean)null);
            d2.C = ag.a(l2, ab2, string, string2 + "isLockedAltMessage", null);
            if (d2.B != null) {
                d2.y = true;
            }
            if (d2.B == LogicBoolean.falseBoolean) {
                d2.B = null;
            }
            d2.D = ab2.a(l2, string, string2 + "isLockedAlt2", (LogicBoolean)null);
            d2.E = ag.a(l2, ab2, string, string2 + "isLockedAlt2Message", null);
            if (d2.D != null) {
                d2.y = true;
            }
            if (d2.D == LogicBoolean.falseBoolean) {
                d2.D = null;
            }
            d2.F = LogicBoolean.create(l2, ab2.b(string, string2 + "ai_isHighPriority", (String)null), null);
            if (d2.F == LogicBoolean.falseBoolean) {
                d2.F = null;
            }
            if (d2.F != null) {
                l2.fJ = true;
            }
            d2.G = ab2.a(l2, string, string2 + "ai_isDisabled", LogicBoolean.falseBoolean);
            d2.aN = (com.corrodinggames.rts.game.units.custom.a.e)ab2.a(string, string2 + "aiUse", d2.aN, com.corrodinggames.rts.game.units.custom.a.e.class);
            d2.J = l2.a(ab2.b(string, string2 + "guiBuildUnit", (String)null), string2 + "guiBuildUnit", string);
            if (d2.J != null) {
                d2.j = com.corrodinggames.rts.game.units.a.u.b;
                if (string4 != null) {
                    throw new RuntimeException("[" + string + "]guiBuildUnit and convertTo cannot currently be used the same action");
                }
            }
            d2.I = l2.a(ab2.b(string, string2 + "ai_considerSameAsBuilding", (String)null), string2 + "ai_considerSameAsBuilding", string);
            d2.aF = ab2.a(l2, string, string2 + "isGuiBlinking", (LogicBoolean)null);
            d2.ay = ag.a(l2.F, ab2.b(string, string2 + "iconImage", "NONE"), l2.ab, l2, string, string2 + "iconImage");
            d2.aB = ab2.a(l2, string, string2 + "iconExtraIsVisible", (LogicBoolean)null);
            if (d2.aB == LogicBoolean.trueBoolean) {
                d2.aB = null;
            }
            d2.az = l2.a(ab2, string, string2 + "iconExtraImage");
            d2.aA = ab2.a(string, string2 + "iconExtraColor", (Integer)Color.a(100, 255, 255, 255));
            d2.aC = UnitReference.parseUnitTypeOrReferenceFromConf(l2, ab2, string, string2 + "unitShownInUI", null);
            if (d2.aC != null && d2.ay != null) {
                throw new RuntimeException("[" + string + "]unitShownInUI and iconImage: doesn't make sense to use both at the same time");
            }
            d2.aD = ab2.a(string, string2 + "unitShownInUIWithHpBar", (Boolean)true);
            d2.aE = ab2.a(string, string2 + "unitShownInUIWithProgressBar", (Boolean)true);
            d2.aG = (com.corrodinggames.rts.game.units.a.t)ab2.a(string, string2 + "displayType", d2.aG, com.corrodinggames.rts.game.units.a.t.class);
            d2.aI = ab2.a(string, string2 + "displayRemainingStockpile", (Boolean)false);
            d2.d = ag.a(l2, ab2, string, string2 + "text", "");
            d2.e = UnitReference.parseUnitTypeOrReferenceFromConf(l2, ab2, string, string2 + "textAddUnitName", null);
            d2.h = ag.a(ab2, string, string2 + "textPostFix", null);
            d2.f = UnitReference.parseUnitTypeOrReferenceFromConf(l2, ab2, string, string2 + "descriptionAddFromUnit", null);
            d2.g = UnitReference.parseUnitTypeOrReferenceFromConf(l2, ab2, string, string2 + "descriptionAddUnitStats", null);
            d2.i = ag.a(l2, ab2, string, string2 + "description", "");
            d2.S = ab2.d(string, string2 + "buildSpeed", Float.valueOf(d2.S)).floatValue();
            if (d2.S == 0.0f) {
                d2.S = 50.0f;
            }
            d2.T = ab2.a(string, string2 + "buildSpeed_ignoreFactorySpeedModifiers", (Boolean)d2.T);
            boolean bl6 = false;
            d2.U = ab2.a(string, string2 + "whenBuilding_cannotMove", (Boolean)d2.U);
            d2.V = l2.a(ab2.b(string, string2 + "whenBuilding_playAnimation", (String)null), d2.V);
            d2.W = ab2.a(string, string2 + "whenBuilding_rotateTo", d2.W);
            d2.X = ab2.a(string, string2 + "whenBuilding_rotateTo_orBackwards", (Boolean)d2.X);
            d2.Y = ab2.a(string, string2 + "whenBuilding_rotateTo_waitTillRotated", (Boolean)d2.Y);
            d2.Z = ab2.a(string, string2 + "whenBuilding_rotateTo_aimAtActionTarget", (Boolean)d2.Z);
            String string18 = ab2.b(string, string2 + "whenBuilding_rotateTo_rotateTurretX", (String)null);
            if (string18 != null) {
                d2.aa = l2.e(string18);
                if (d2.aa == null) {
                    throw new RuntimeException("Cannot find turret:" + string18 + " for [" + string + "]" + string2 + "whenBuilding_rotateTo_rotateTurretX");
                }
                if (d2.X) {
                    throw new RuntimeException("whenBuilding_rotateTo_orBackwards:true not supported with [" + string + "]" + string2 + "whenBuilding_rotateTo_rotateTurretX");
                }
            }
            if (d2.Z && d2.W == null) {
                d2.W = Float.valueOf(0.0f);
            }
            d2.ab = ab2.a(l2, string, string2 + "whenBuilding_triggerAction", (u)null);
            d2.Q = ab2.a(string, string2 + "convertTo_keepCurrentTags", (Boolean)d2.Q);
            d2.R = as.a(ab2, string, string2 + "convertTo_keepCurrentFields", null);
            if (string5 != null && !"NONE".equalsIgnoreCase(string5)) {
                d2.l = l2.a(string5, string2 + "whenBuilding_temporarilyConvertTo", string);
                d2.m = atArray;
                bl6 = true;
            }
            if (d2.U || d2.V != null || d2.W != null || d2.l != null || d2.ab != null) {
                l2.bg = true;
            }
            d2.aM = com.corrodinggames.rts.game.units.custom.a.f.b;
            if (string4 != null && !"NONE".equalsIgnoreCase(string4)) {
                d2.H = l2.a(string4, string2 + "convertTo", string);
                d2.k = string4;
                d2.N = false;
                bl6 = true;
            }
            if (f2 != null) {
                d2.ad = f2;
                bl6 = true;
            }
            if (b2 != null && b2.d()) {
                d2.ae = b2;
                bl6 = true;
            }
            if (b3 != null && b3.d()) {
                d2.af = b3;
                bl6 = true;
            }
            d2.ah = ab2.a(string, string2 + "fireTurretXAtGround_withOffset", (PointF)null);
            d2.ai = ab2.b(l2, string, string2 + "fireTurretXAtGround_withTarget", null);
            d2.ak = ab2.b(string, string2 + "fireTurretXAtGround_count", 1);
            d2.am = com.corrodinggames.rts.game.units.custom.b.c.a(l2, ab2.b(string, "fireTurretXAtGround_showGuideDecals", (String)null));
            if (d2.ai != null && d2.ah == null) {
                d2.ah = new PointF(0.0f, 0.0f);
            }
            if ((string16 = ab2.b(string, string2 + "fireTurretXAtGround_withProjectile", (String)null)) != null) {
                d2.aj = l2.f(string16);
                if (d2.aj == null) {
                    throw new RuntimeException("Cannot find projectile:" + string16 + " for [" + string + "]" + string2 + "fireTurretXAtGround_withProjectile");
                }
            }
            if ((string15 = ab2.b(string, string2 + "fireTurretXAtGround_onlyOverPassableTileOf", (String)null)) != null) {
                d2.al = ao.a(string15, string2 + "fireTurretXAtGround_overPassableTileOf");
            }
            if (string6 != null) {
                object5 = l2.e(string6);
                if (object5 == null) {
                    throw new RuntimeException("Cannot find turret:" + string6 + " for [" + string + "]" + string2 + "fireTurretXAtGround");
                }
                d2.ag = ((bn)object5).e;
                if (d2.ah == null) {
                    d2.j = com.corrodinggames.rts.game.units.a.u.g;
                    if (d2.J != null) {
                        throw new RuntimeException("[" + string + "]guiBuildUnit and fireTurretXAtGround (without withOffset) cannot be used in the same action");
                    }
                }
                bl6 = true;
            }
            d2.an = logicBoolean;
            d2.ao = logicBoolean2;
            if (string7 != null && !"NONE".equalsIgnoreCase(string7)) {
                d2.ap = l2.c(string7, "alsoTriggerAction", string);
                if (logicBoolean3 != null) {
                    if (LogicBoolean.isStaticNumber(logicBoolean3)) {
                        float f3 = LogicBoolean.getKnownStaticNumber(logicBoolean3);
                        if (f3 == 0.0f) {
                            d2.ap = null;
                        } else if (f3 != 1.0f) {
                            d2.ar = logicBoolean3;
                        }
                    } else {
                        d2.ar = logicBoolean3;
                    }
                }
                bl6 = true;
            }
            if (string8 != null && !"NONE".equalsIgnoreCase(string8)) {
                d2.aq = l2.c(string8, "alsoQueueAction", string);
                bl6 = true;
            }
            if (string9 != null) {
                d2.as = l2.a(string9, (z)null);
                bl6 = true;
            }
            if (string10 != null) {
                d2.at = l2.a(string10, (z)null);
                bl6 = true;
            }
            if (string11 != null) {
                d2.au = bl.a(l2, string11);
                bl6 = true;
            }
            if (string12 != null) {
                d2.av = bl.a(l2, string12);
                bl6 = true;
            }
            if (string13 != null) {
                d2.aw = bl.a(l2, string13);
                bl6 = true;
            }
            if (string14 != null) {
                d2.ax = bl.a(l2, string14);
                bl6 = true;
            }
            if (logicBoolean4 != null) {
                d2.aH = logicBoolean4;
                bl6 = true;
            }
            if (d2.ac.size() > 0) {
                bl6 = true;
            }
            object5 = null;
            String string19 = ab2.b(string, string2 + "autoTriggerOnEvent", (String)null);
            Integer n2 = ab2.b(string, string2 + "autoTriggerOnEventRecursionLimit", (Integer)null);
            if (n2 != null) {
                if (n2 < 0) {
                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEventRecursionLimit: Cannot be < 0");
                }
                if (n2 > 50) {
                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEventRecursionLimit: Cannot be > 100");
                }
            }
            if (string19 != null && (cloneable = ag.a(string, string2 + "autoTriggerOnEvent", string19)) != null) {
                if (((ArrayList)cloneable).size() < 1) {
                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: Expected 1 or more options, got:" + ((ArrayList)cloneable).size());
                }
                object4 = ((ArrayList)cloneable).iterator();
                while (object4.hasNext()) {
                    object3 = (ai)object4.next();
                    try {
                        object2 = (af)com.corrodinggames.rts.gameFramework.utility.ab.a(object3.a, null, af.class);
                    }
                    catch (bo bo2) {
                        throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: " + bo2.getMessage(), bo2);
                    }
                    if (object5 == null) {
                        object5 = new ArrayList();
                    }
                    object = new ae();
                    ((ae)object).a = object2;
                    if (n2 != null) {
                        ((ae)object).e = n2;
                    } else if (((ae)object).a == af.q) {
                        ((ae)object).e = 4;
                    }
                    if (object3.b != null) {
                        for (Object object6 : object3.b.keySet()) {
                            String string20 = (String)object3.b.get(object6);
                            boolean bl7 = false;
                            if (((String)object6).equalsIgnoreCase("withtag")) {
                                if (((ae)object).a != af.n && ((ae)object).a != af.q) {
                                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: " + ((ae)object).a.name() + " doesn't support parameter: " + (String)object6);
                                }
                                bl7 = true;
                            }
                            if (((String)object6).equalsIgnoreCase("withprojectiletag")) {
                                if (((ae)object).a != af.n) {
                                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: " + ((ae)object).a.name() + " doesn't support parameter: " + (String)object6);
                                }
                                bl7 = true;
                            }
                            if (((String)object6).equalsIgnoreCase("withactiontag")) {
                                if (((ae)object).a != af.f && ((ae)object).a != af.g) {
                                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: " + ((ae)object).a.name() + " doesn't support parameter: " + (String)object6);
                                }
                                bl7 = true;
                            }
                            if (bl7) {
                                String string21 = com.corrodinggames.rts.gameFramework.f.p(string20);
                                if (string21 == null) {
                                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: " + ((ae)object).a.name() + " expected quoted string, got: " + string20);
                                }
                                if (((ae)object).d != null) {
                                    throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: " + ((ae)object).a.name() + " tag was set twice");
                                }
                                ((ae)object).d = com.corrodinggames.rts.gameFramework.utility.ab.j(string, string2 + "autoTriggerOnEvent", string21);
                                continue;
                            }
                            throw new bo("[" + string + "]" + string2 + "autoTriggerOnEvent: Unknown parameter: " + (String)object6);
                        }
                    }
                    ((ArrayList)object5).add(object);
                }
            }
            cloneable = ab2.a(l2, string, string2 + "autoTrigger", (LogicBoolean)null);
            object4 = ab2.b(string, string2 + "autoTrigger", (String)null);
            object3 = (s)ab2.a(string, string2 + "autoTriggerCheckRate", l2.cb, s.class);
            d2.n = bl6;
            if (bl6 || d2.v != null) {
                if (cloneable != null && bl6) {
                    object2 = new r();
                    ((r)object2).a = cloneable;
                    ((r)object2).b = object4;
                    ((r)object2).c = object3;
                    ((r)object2).d = new com.corrodinggames.rts.game.units.custom.a.g(d2, l2.a(d2.k, "[" + string + "]" + string2, string));
                    l2.fW.add(object2);
                }
                if (object5 != null && bl6) {
                    object2 = new com.corrodinggames.rts.game.units.custom.a.g(d2, l2.a(d2.k, "[" + string + "]" + string2, string));
                    object = l2;
                    Iterator<Object> iterator = ((ArrayList)object5).iterator();
                    while (iterator.hasNext()) {
                        Object object6;
                        object6 = (ae)iterator.next();
                        ((ae)object6).b = object2;
                        ((ae)object6).c = object;
                        l2.gq.add(object6);
                    }
                }
                if (d2.k != null && d2.q != null && d2.q.b > 0) {
                    l2.gi = true;
                }
                l2.gh.add(d2);
            }
        }
    }

    public static String a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2) {
        if (string2.startsWith("SHARED:")) {
            string2 = string2.substring("SHARED:".length());
            string = "units/shared/common.ini";
        }
        if (string2.startsWith("CORE:")) {
            string2 = string2.substring("CORE:".length());
            string = "units/common.ini";
        }
        if (string2.startsWith("ROOT:")) {
            string2 = string2.substring("ROOT:".length());
            string = l2.J == null ? "units/common.ini" : l2.J.q + "/common.ini";
        }
        String string3 = com.corrodinggames.rts.gameFramework.f.h(string) + "/";
        while (string2.startsWith("/") || string2.startsWith("\\")) {
            string2 = string2.substring(1);
        }
        String string4 = string3 + string2;
        return string4;
    }

    public static void a(long l2, ah ah2) {
        double d2 = br.a(l2);
        ah2.o += d2;
    }

    public static void i() {
        com.corrodinggames.rts.gameFramework.l.e("==Timing==");
        for (ah ah2 : ah.values()) {
            com.corrodinggames.rts.gameFramework.l.e(ah2.name() + ": " + br.a(ah2.o));
        }
    }

    public static void j() {
        for (ah ah2 : ah.values()) {
            ah2.o = 0.0;
        }
    }

    public static com.corrodinggames.rts.gameFramework.m.e a(String string, String string2, boolean bl2, com.corrodinggames.rts.game.units.custom.l l2, String string3, String string4) {
        try {
            return ag.a(string, string2, bl2, l2);
        }
        catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            throw new RuntimeException("[" + string3 + "]" + string4 + ": " + runtimeException.getMessage(), runtimeException);
        }
    }

    public static com.corrodinggames.rts.gameFramework.m.e a(String string, String string2, boolean bl2, com.corrodinggames.rts.game.units.custom.l l2) {
        long l3 = br.a();
        com.corrodinggames.rts.gameFramework.m.e e2 = ag.b(string, string2, bl2, l2);
        ag.a(l3, ah.b);
        return e2;
    }

    public static com.corrodinggames.rts.gameFramework.m.e b(String string, String string2, boolean bl2, com.corrodinggames.rts.game.units.custom.l l2) {
        com.corrodinggames.rts.gameFramework.m.e e2;
        if (string2 == null) {
            return null;
        }
        if (string2.equalsIgnoreCase("NONE")) {
            return null;
        }
        if (string2.equals("")) {
            return null;
        }
        boolean bl3 = false;
        if (string2.startsWith("SHADOW:")) {
            string2 = string2.substring("SHADOW:".length());
            bl3 = true;
        }
        if (string2.startsWith("SHARED:")) {
            string2 = string2.substring("SHARED:".length());
            string = "units/shared/common.ini";
        }
        if (string2.startsWith("CORE:")) {
            string2 = string2.substring("CORE:".length());
            string = "units/common.ini";
        }
        if (string2.startsWith("ROOT:")) {
            string2 = string2.substring("ROOT:".length());
            string = l2.J == null ? "units/common.ini" : l2.J.q + "/common.ini";
        }
        if (string2.startsWith("SHADOW:")) {
            string2 = string2.substring("SHADOW:".length());
            bl3 = true;
        }
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        String string3 = com.corrodinggames.rts.gameFramework.f.h(string) + "/";
        String string4 = "[" + bl2 + "," + bl3 + "]" + string3 + string2;
        com.corrodinggames.rts.gameFramework.m.e e3 = ag.c(string4);
        if (e3 != null) {
            return e3;
        }
        com.corrodinggames.rts.gameFramework.utility.j j2 = ag.c(string3, string2, l2);
        int n2 = 0;
        if (e != null) {
            n2 = ag.e.I;
        }
        if (n2 > 5) {
            com.corrodinggames.rts.gameFramework.l.e("Fast failing to oom image for this mod");
            e2 = l3.bO.r();
        } else {
            long l4 = br.a();
            try {
                e2 = l3.bO.a(j2, true);
            }
            catch (RuntimeException runtimeException) {
                com.corrodinggames.rts.gameFramework.l.e("imageStream:" + j2);
                throw new RuntimeException("Error decode image from: " + com.corrodinggames.rts.gameFramework.e.a.d(string3 + string2), runtimeException);
            }
            ag.a(l4, ah.a);
            if (e2.A()) {
                com.corrodinggames.rts.gameFramework.l.e("oomErrors:" + l);
                ++l;
                if (e != null) {
                    ++ag.e.I;
                    ++ag.e.J;
                }
            } else if (e != null && !ag.e.z && com.corrodinggames.rts.gameFramework.l.aZ) {
                e2.z();
            }
        }
        try {
            ((InputStream)j2).close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (e2 == null) {
            throw new RuntimeException("Failed to decode image: " + com.corrodinggames.rts.gameFramework.e.a.e(string3 + string2));
        }
        e2.a(bl2);
        if (bl3) {
            com.corrodinggames.rts.gameFramework.m.e e4 = e2;
            e2 = am.a(e4, e2.p, e2.q);
        }
        ag.a(e2);
        ag.a(string4, e2);
        return e2;
    }

    public static void a(String string, com.corrodinggames.rts.gameFramework.m.e e2) {
        g.put(string, e2);
    }

    public static com.corrodinggames.rts.gameFramework.m.e c(String string) {
        com.corrodinggames.rts.gameFramework.m.e e2 = (com.corrodinggames.rts.gameFramework.m.e)g.get(string);
        if (e2 != null) {
            ++j;
            ag.a(e2);
            e2.t();
            return e2;
        }
        if (k) {
            com.corrodinggames.rts.gameFramework.l.e("loadImageInConf: cache miss: " + string);
        }
        ++i;
        return null;
    }

    public static com.corrodinggames.rts.gameFramework.a.i a(String string, String string2, com.corrodinggames.rts.game.units.custom.l l2) {
        long l3 = br.a();
        com.corrodinggames.rts.gameFramework.a.i i2 = ag.b(string, string2, l2);
        ag.a(l3, ah.d);
        return i2;
    }

    public static com.corrodinggames.rts.gameFramework.a.i b(String string, String string2, com.corrodinggames.rts.game.units.custom.l l2) {
        if (string2 == null) {
            return null;
        }
        if (string2.equalsIgnoreCase("NONE")) {
            return null;
        }
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        if (!string2.contains(".")) {
            com.corrodinggames.rts.gameFramework.a.i i2 = l3.bM.a(string2);
            return i2;
        }
        if (string2.startsWith("ROOT:")) {
            string2 = string2.substring("ROOT:".length());
            string = l2.J == null ? "units/common.ini" : l2.J.q + "/common.ini";
        }
        if (string2.startsWith("CORE:")) {
            string2 = string2.substring("CORE:".length());
            string = "units/common.ini";
        }
        if (string2.startsWith("SHARED:")) {
            string2 = string2.substring("SHARED:".length());
            string = "units/shared/common.ini";
        }
        boolean bl2 = false;
        String string3 = com.corrodinggames.rts.gameFramework.f.h(string) + "/";
        String string4 = string3 + string2;
        com.corrodinggames.rts.gameFramework.a.i i3 = (com.corrodinggames.rts.gameFramework.a.i)h.get(string4);
        if (i3 != null) {
            ag.a(i3);
            return i3;
        }
        if (!string2.toLowerCase(Locale.ROOT).endsWith(".ogg") && !string2.toLowerCase(Locale.ROOT).endsWith(".wav")) {
            throw new RuntimeException("Failed to open sound: " + string3 + "" + string2 + " only the ogg & wav sound formats are supported.");
        }
        com.corrodinggames.rts.gameFramework.utility.j j2 = ag.c(string3, string2, l2);
        long l4 = br.a();
        com.corrodinggames.rts.gameFramework.a.i i4 = l3.bM.a(string2, j2, bl2);
        try {
            j2.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        ag.a(l4, ah.c);
        if (i4 == null) {
            boolean bl3 = string2.toLowerCase(Locale.ROOT).endsWith(".ogg");
            String string5 = "Sound file found but failed to load: " + string4;
            if (bl3) {
                string5 = string5 + " - Check if this file is truly a ogg";
            }
            l2.r(string5);
            return l3.bM.b("Failed to load");
        }
        ag.a(i4);
        h.put(string4, i4);
        return i4;
    }

    public static boolean a(String string, String string2, String string3, com.corrodinggames.rts.gameFramework.i.b b2) {
        String string4;
        if (string2 == null) {
            return true;
        }
        if (!string2.contains("..")) {
            return true;
        }
        if (com.corrodinggames.rts.gameFramework.l.at()) {
            return true;
        }
        File file = new File(com.corrodinggames.rts.gameFramework.e.a.e(string3));
        String string5 = file.getCanonicalPath();
        if (string5.startsWith(string4 = new File(com.corrodinggames.rts.gameFramework.e.a.e("units")).getCanonicalPath())) {
            return true;
        }
        String string6 = b2.k();
        boolean bl2 = string5.startsWith(string6);
        if (!bl2) {
            com.corrodinggames.rts.gameFramework.l.b("File: '" + string5 + "' is not within mod: '" + string6 + "'");
        }
        return bl2;
    }

    public static String a(String string, String string2) {
        if (!string.endsWith("/")) {
            string = string + "/";
        }
        while (string2.startsWith("/") || string2.startsWith("\\")) {
            string2 = string2.substring(1);
        }
        return string + string2;
    }

    public static com.corrodinggames.rts.gameFramework.utility.j c(String string, String string2, com.corrodinggames.rts.game.units.custom.l l2) {
        String string3 = ag.a(string, string2);
        com.corrodinggames.rts.gameFramework.i.b b2 = null;
        if (l2 != null) {
            b2 = l2.J;
        } else {
            com.corrodinggames.rts.gameFramework.l.g("findAssetSteam meta==null");
        }
        try {
            if (b2 != null && !ag.a(string, string2, string3, b2)) {
                throw new RuntimeException("File is outside mod: " + string3);
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        com.corrodinggames.rts.gameFramework.utility.j j2 = com.corrodinggames.rts.gameFramework.e.a.k(string3);
        if (j2 == null) {
            com.corrodinggames.rts.gameFramework.l.e("Orginal path: " + string3);
            throw new RuntimeException("IO Error: Failed to open: " + ag.a(b2, string3, true));
        }
        return j2;
    }

    public static void b(com.corrodinggames.rts.game.units.custom.l l2) {
        ba[] baArray = l2.ax;
        for (int i2 = 0; i2 < baArray.length; ++i2) {
            int n2;
            ba ba2 = baArray[i2];
            float f2 = -1.0f;
            ba ba3 = null;
            float f3 = 1.0f;
            if (ba2.o) {
                f3 = 0.1f;
            }
            for (int i3 = 0; i3 < baArray.length; ++i3) {
                ba ba4 = baArray[i3];
                if (ba2 == ba4 || ba4.l) continue;
                float f4 = com.corrodinggames.rts.gameFramework.f.a(ba2.d * f3, ba2.e, ba4.d * f3, ba4.e);
                if (ba3 != null && !(f4 < f2)) continue;
                f2 = f4;
                ba3 = ba4;
            }
            f2 = com.corrodinggames.rts.gameFramework.f.a(f2) + 2.0f;
            f2 *= f2;
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (n2 = 0; n2 < baArray.length; ++n2) {
                float f5;
                ba ba5 = baArray[n2];
                if (ba2 == ba5 || ba5.l || !((f5 = com.corrodinggames.rts.gameFramework.f.a(ba2.d * f3, ba2.e, ba5.d * f3, ba5.e)) <= f2)) continue;
                arrayList.add(ba5.a);
            }
            ba2.S = new int[arrayList.size()];
            for (n2 = 0; n2 < arrayList.size(); ++n2) {
                ba2.S[n2] = (Integer)arrayList.get(n2);
            }
        }
    }

    public static String k() {
        return "builtin_mods";
    }

    public static String l() {
        return "builtin_mods_enabled";
    }

    public static String m() {
        String string = com.corrodinggames.rts.gameFramework.l.aU ? "/SD/mods/units" : "/SD/rustedWarfare/units";
        return string;
    }

    public static ArrayList a(String string, String string2, String string3) {
        if (string3 == null || "".equals(string3) || "NONE".equalsIgnoreCase(string3)) {
            return null;
        }
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        ArrayList arrayList2 = al.a(string3, ",", false);
        for (String string4 : arrayList2) {
            String[] stringArray;
            if ("".equals(string4 = string4.trim())) continue;
            String string5 = string4;
            String string6 = null;
            if (string4.contains("(") && string4.contains(")")) {
                stringArray = al.b(string4, "(");
                if (stringArray == null) {
                    throw new bo("[" + string + "]" + string2 + ": Unexpected format for '" + string5 + "' of " + string3);
                }
                string4 = stringArray[0];
                string6 = stringArray[1].trim();
            }
            stringArray = new ai();
            stringArray.a = string4;
            if (string6 != null) {
                if (!string6.endsWith(")")) {
                    throw new bo("[" + string + "]" + string2 + ": Expected ')' in '" + string5 + "' of " + string3);
                }
                string6 = string6.substring(0, string6.length() - 1);
                ArrayList arrayList3 = al.a(string6, ",", false, false);
                for (String string7 : arrayList3) {
                    if (string7.trim().equals("")) continue;
                    String[] stringArray2 = al.b(string7, "=");
                    if (stringArray2 == null) {
                        throw new RuntimeException("[" + string + "]" + string2 + ": Unexpected key format for '" + string5 + "' of " + string3);
                    }
                    String string8 = stringArray2[0].trim();
                    String string9 = stringArray2[1].trim();
                    if (stringArray.b == null) {
                        stringArray.b = new HashMap();
                    }
                    stringArray.b.put(string8, string9);
                }
            }
            arrayList.add(stringArray);
        }
        return arrayList;
    }

    static {
        g = new HashMap();
        h = new HashMap();
        m = new com.corrodinggames.rts.gameFramework.utility.m();
        n = new HashMap();
        o = new Object();
        p = 50.0f;
        q = 50.0f;
        r = null;
        s = null;
    }
}
