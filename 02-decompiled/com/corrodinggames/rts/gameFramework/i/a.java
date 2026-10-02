/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.i;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.i.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

public class a {
    public static String a;
    public static String b;
    public b c = new b();
    Object d = new Object();
    ArrayList e = new ArrayList();
    ArrayList f = new ArrayList();

    public a() {
        try {
            com.corrodinggames.rts.gameFramework.i.a.a(l.B().u());
        }
        catch (bo bo2) {
            throw new RuntimeException(bo2);
        }
    }

    private static int a(String string, int n2) {
        String[] stringArray = com.corrodinggames.rts.gameFramework.f.c(string, '.');
        if (stringArray == null) {
            throw new bo("Unexpected version format (Missing " + n2 + ")");
        }
        if (stringArray.length > 3) {
            throw new bo("Unexpected version format (" + string + ")");
        }
        if (stringArray.length <= n2) {
            return 0;
        }
        try {
            return Integer.valueOf(stringArray[n2]);
        }
        catch (NumberFormatException numberFormatException) {
            throw new bo("Unexpected version format (Bad " + n2 + ")", numberFormatException);
        }
    }

    public static void a(String string) {
        String string2 = l.B().u();
        com.corrodinggames.rts.gameFramework.i.a.a(string, string2);
    }

    public static String b(String string) {
        string = com.corrodinggames.rts.gameFramework.f.a(string, "v", "");
        string = string.trim();
        string = com.corrodinggames.rts.gameFramework.f.a(string, "a", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "b", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "c", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "d", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "e", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "f", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "g", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "h1", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "h2", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "h3", "");
        string = com.corrodinggames.rts.gameFramework.f.a(string, "h4", "");
        return string;
    }

    public static void a(String string, String string2) {
        string2 = com.corrodinggames.rts.gameFramework.i.a.b(string2);
        String string3 = string = com.corrodinggames.rts.gameFramework.i.a.b(string);
        String string4 = string2;
        try {
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7;
            String[] stringArray;
            int n8 = 1000;
            int n9 = 1000;
            if (string2.contains("p")) {
                stringArray = al.b(string2, "p");
                try {
                    n8 = Integer.valueOf(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    throw new bo("Unexpected min version:" + string3 + " (Bad build number)", numberFormatException);
                }
                string2 = stringArray[0];
            }
            if (string.contains("p")) {
                stringArray = al.b(string, "p");
                try {
                    n9 = Integer.valueOf(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    throw new bo("Unexpected min version:" + string3 + "(Bad build number)", numberFormatException);
                }
                string = stringArray[0];
            }
            try {
                n7 = com.corrodinggames.rts.gameFramework.i.a.a(string2, 0);
                n6 = com.corrodinggames.rts.gameFramework.i.a.a(string, 0);
                n5 = com.corrodinggames.rts.gameFramework.i.a.a(string2, 1);
                n4 = com.corrodinggames.rts.gameFramework.i.a.a(string, 1);
                n3 = com.corrodinggames.rts.gameFramework.i.a.a(string2, 2);
                n2 = com.corrodinggames.rts.gameFramework.i.a.a(string, 2);
            }
            catch (bo bo2) {
                throw new bo("Requires version: " + string3 + " or higher. " + bo2.getMessage(), bo2);
            }
            if (n6 < 1) {
                throw new bo("Min version cannot be less than v1.10");
            }
            if (n6 > n7) {
                throw new bo("Requires version: " + string3 + " or higher. (You have: " + string4 + ")");
            }
            if (n7 > n6) {
                return;
            }
            if (n4 < 10 && n6 == 1) {
                throw new bo("Min version cannot be less than v1.10");
            }
            if (n4 > n5) {
                throw new bo("Requires version: " + string3 + " or higher. (You have: " + string4 + ")");
            }
            if (n5 > n4) {
                return;
            }
            if (n2 > n3) {
                throw new bo("Requires version: " + string3 + " or higher. (You have: " + string4 + ")");
            }
            if (n3 > n2) {
                return;
            }
            if (n9 > n8) {
                throw new bo("Requires newer build: " + string3 + " or higher. (You have: " + string4 + ")");
            }
        }
        catch (RuntimeException runtimeException) {
            throw new bo("Requires version: " + string3 + " or higher." + runtimeException.getMessage(), runtimeException);
        }
    }

    public void a() {
        this.k();
        this.f();
    }

    public int a(boolean bl) {
        int n2 = 0;
        for (b b2 : this.e) {
            if (b2.f || b2.D || bl && b2.R != null) continue;
            ++n2;
        }
        return n2;
    }

    public int b() {
        int n2 = 0;
        for (b b2 : this.e) {
            if (b2.f || b2.R == null) continue;
            ++n2;
        }
        return n2;
    }

    public int c() {
        int n2 = 0;
        for (b b2 : this.e) {
            if (b2.z) continue;
            ++n2;
        }
        return n2;
    }

    public void d() {
        for (b b2 : this.e) {
            b2.g = b2.f;
            b2.h = false;
        }
    }

    public void e() {
        l l2 = l.B();
        String string = "";
        for (b b2 : this.e) {
            String string2 = b2.c;
            string2 = string2.replace(",", " ");
            if ((string2 = string2.replace("|", " ")).length() > 15) {
                string2 = string2.substring(12) + "...";
            }
            if (string.length() != 0) {
                string = string + ",";
            }
            String string3 = b2.f ? "disabled" : "enabled";
            string = string + string2 + "|" + b2.e + "|" + string3;
        }
        l2.bQ.modSettingsVersion = 1;
        l2.bQ.modSettings = string;
    }

    public void f() {
        String[] stringArray;
        l.e("Loading mod selection");
        l l2 = l.B();
        String string = l2.bQ.modSettings;
        for (String string2 : stringArray = string.split(",")) {
            boolean bl;
            String[] stringArray2 = string2.split("\\|");
            if (stringArray2.length != 3) {
                l.e("loadSelection: wrong count (" + stringArray2.length + "):" + string2);
                continue;
            }
            String string3 = stringArray2[0];
            String string4 = stringArray2[1];
            String string5 = stringArray2[2];
            if (string5.equals("enabled")) {
                bl = false;
            } else if (string5.equals("disabled")) {
                bl = true;
            } else {
                l.e("loadSelection: Unknown option:" + string2);
                continue;
            }
            b b2 = this.c(string4);
            if (b2 == null) {
                l.e("loadSelection: Did not find mod in settings:" + string3);
                continue;
            }
            b2.f = bl;
            b2.i = true;
        }
    }

    public b c(String string) {
        for (b b2 : this.e) {
            if (!b2.e.equals(string)) continue;
            return b2;
        }
        return null;
    }

    public int d(String string) {
        if (string == null) {
            return 0;
        }
        int n2 = 0;
        for (b b2 : this.e) {
            if (!string.equals(b2.c())) continue;
            ++n2;
        }
        return n2;
    }

    public b a(int n2) {
        for (b b2 : this.e) {
            if (b2.L != n2) continue;
            return b2;
        }
        return null;
    }

    public void g() {
        for (b b2 : this.e) {
            b2.f = true;
        }
    }

    public int h() {
        int n2 = 0;
        for (b b2 : this.e) {
            if (b2.f && !b2.D) continue;
            ++n2;
        }
        return n2;
    }

    public b e(String string) {
        for (b b2 : this.e) {
            if (!b2.d.equals(string)) continue;
            return b2;
        }
        return null;
    }

    public b f(String string) {
        for (b b2 : this.e) {
            if (!b2.a().equals(string)) continue;
            return b2;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public b a(String string, String string2, String string3, String string4, boolean bl, boolean bl2, boolean bl3, int n2) {
        b b2 = this.c(string4);
        if (b2 == null) {
            b2 = new b();
            b2.c = string;
            b2.d = string2;
            b2.e = string4;
            boolean bl4 = b2.f = !bl;
        }
        if (b2.q == null && string3 != null) {
            b2.p = b2.q = string3;
            b2.n();
            if (b2.q != null && b2.q.toLowerCase(Locale.ROOT).contains("rwmod")) {
                b2.j = true;
            }
        }
        b2.x = n2;
        b2.l = true;
        b2.y = bl2;
        b2.z = bl3;
        if (!b2.z) {
            b2.o = "Storage: " + com.corrodinggames.rts.gameFramework.e.a.d(b2.q);
        }
        b2.r();
        Object object = this.d;
        synchronized (object) {
            if (!this.e.contains(b2)) {
                ArrayList<b> arrayList = new ArrayList<b>();
                arrayList.addAll(this.e);
                arrayList.add(b2);
                Collections.sort(arrayList);
                this.e = arrayList;
            }
        }
        return b2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(b b2) {
        Object object = this.d;
        synchronized (object) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.e);
            arrayList.remove(b2);
            this.e = arrayList;
        }
    }

    public void a(String string, boolean bl, boolean bl2) {
        l.e("loading mod custom units at:" + string);
        String[] stringArray = com.corrodinggames.rts.gameFramework.e.a.h(string);
        if (stringArray == null) {
            l.b("getAllModList: ERROR");
            l.b("getAllModList: Failed to load:" + string);
            return;
        }
        for (String string2 : stringArray) {
            String string3 = string + "/" + string2;
            if (!com.corrodinggames.rts.gameFramework.e.a.f(string3) && !string2.endsWith(".ini")) continue;
            String string4 = com.corrodinggames.rts.gameFramework.f.e(string2);
            String string5 = string2;
            if (string5.contains("/")) {
                string5 = string5.substring(string2.lastIndexOf("/") + 1);
            }
            boolean bl3 = false;
            this.a(string5, string2, string3, string4, bl, bl3, bl2, 0);
        }
    }

    public ArrayList i() {
        ArrayList arrayList = new ArrayList();
        for (b b2 : this.e) {
            if (!b2.m()) continue;
            arrayList.addAll(b2.q());
        }
        return arrayList;
    }

    public ArrayList j() {
        ArrayList<b> arrayList = new ArrayList<b>();
        for (b b2 : this.e) {
            if (!b2.m()) continue;
            arrayList.add(b2);
        }
        return arrayList;
    }

    public ArrayList k() {
        Object object2;
        for (Object object2 : this.e) {
            ((b)object2).l = false;
            if (!((b)object2).m) continue;
            ((b)object2).l = true;
        }
        com.corrodinggames.rts.gameFramework.o.a a2 = com.corrodinggames.rts.gameFramework.o.a.a();
        if (a2 != null) {
            a2.l();
        } else {
            l.e("getAllModList: SteamEngine==null");
        }
        object2 = ag.m();
        if (!com.corrodinggames.rts.gameFramework.e.a.f((String)object2)) {
            l.e("Modded Custom '" + (String)object2 + "' directory not found");
        } else {
            boolean bl = false;
            this.a((String)object2, true, bl);
        }
        String string = ag.k();
        if (!com.corrodinggames.rts.gameFramework.e.a.f(string)) {
            l.e("Modded Custom '" + string + "' directory not found");
        } else {
            boolean bl = true;
            this.a(string, false, bl);
        }
        String string2 = ag.l();
        if (!com.corrodinggames.rts.gameFramework.e.a.f(string2)) {
            l.e("Modded Custom '" + string2 + "' directory not found");
        } else {
            boolean bl = true;
            this.a(string2, true, bl);
        }
        for (Iterator iterator : this.e) {
            if (((b)((Object)iterator)).l) continue;
            l.e("Removing mod no longer found on system: " + ((b)((Object)iterator)).a());
            this.a((b)((Object)iterator));
        }
        l.e("========= Mods ===========");
        l.e("Number of mods:" + this.e.size());
        for (Iterator iterator : this.e) {
            l.e("Mod: '" + ((b)((Object)iterator)).a());
        }
        l.e("================================");
        l l2 = l.B();
        if (l2.bQ.lastModCount == -1 || l2.bQ.modSettingsVersion < 1) {
            l.e("Disabling all new mods for first/new load");
            for (b b2 : this.e) {
                b2.f = true;
            }
            this.e();
            l2.bQ.save();
        } else if (this.e.size() > l2.bQ.lastModCount + 4) {
            l.e("Too many new mods found, not enabling new mods");
            l.e("Number of mods:" + this.e.size() + " vs " + l2.bQ.lastModCount);
            for (b b3 : this.e) {
                if (b3.i) continue;
                b3.f = true;
            }
            this.e();
            l2.bQ.save();
        }
        l2.bQ.lastModCount = this.e.size();
        return this.e;
    }

    public void l() {
        l l2 = l.B();
        try {
            l2.br = true;
            l2.e();
            this.a(false, false);
        }
        finally {
            l2.br = false;
        }
        l2.x();
    }

    public void a(boolean bl2, boolean bl3) {
        l l2 = l.B();
        ae.b();
        if (!bl3) {
            for (b b2 : this.e) {
                if (b2.R != null) {
                    l.e("re-enabling mod: " + b2.a());
                }
                b2.R = null;
                b2.V.clear();
                b2.S = null;
                b2.U.clear();
                b2.C = false;
                b2.D = false;
                b2.E = 0;
                b2.F = 0;
                b2.G = 0L;
                b2.H = 0L;
                b2.I = 0;
                b2.J = 0;
                b2.w = 0;
            }
        }
        this.k();
        ArrayList arrayList = new ArrayList(com.corrodinggames.rts.game.units.custom.l.d);
        if (!bl3) {
            ag.h();
        } else {
            ag.b();
        }
        if (bl2) {
            int n2 = 0;
            Iterator iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                com.corrodinggames.rts.game.units.custom.l l3 = (com.corrodinggames.rts.game.units.custom.l)iterator.next();
                if (l3.J == null || l3.J.f || l3.J.R == null || com.corrodinggames.rts.game.units.custom.l.a(l3) != null) continue;
                l.e("Was missing: " + l3.M);
                com.corrodinggames.rts.game.units.custom.l.d.add(l3);
                ++n2;
            }
            if (n2 > 0) {
                ag.e();
            }
        }
        com.corrodinggames.rts.game.units.custom.l.A();
        n.P();
        g.K();
    }

    public void m() {
        l l2 = l.B();
        if (l2.dH != null) {
            l2.dH.d();
        } else {
            l.e("No active callbacks");
        }
    }

    public String[] a(String[] stringArray, String string) {
        Object object;
        l.e("addExtraMapsForPath: " + string);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        if (stringArray != null) {
            object = stringArray;
            int n2 = ((String[])object).length;
            for (int i2 = 0; i2 < n2; ++i2) {
                Object object2 = object[i2];
                arrayList.add(object2);
            }
        }
        if (l.at() && "/SD/rusted_warfare_maps".equals(string) && (object = com.corrodinggames.rts.gameFramework.e.a.a("/SD/rustedWarfare/maps", true)) != null) {
            for (Object object3 : object) {
                arrayList.add("NEW_PATH|maps2/" + (String)object3);
            }
        }
        for (c c2 : this.g(string)) {
            arrayList.add("MOD|" + c2.c.e + "/" + c2.b);
        }
        if (stringArray == null && arrayList.size() == 0) {
            return null;
        }
        return arrayList.toArray(new String[0]);
    }

    public ArrayList g(String string) {
        ArrayList<c> arrayList = new ArrayList<c>();
        for (c c2 : this.f) {
            boolean bl2 = false;
            if (string.startsWith("mod/") && string.startsWith("mod/" + c2.c.e)) {
                bl2 = true;
            }
            if (!c2.c.f && string.startsWith("/SD/rusted_warfare_maps")) {
                bl2 = true;
            }
            if (!bl2) continue;
            l.e("Adding extra map:" + c2.a);
            arrayList.add(c2);
        }
        return arrayList;
    }

    public void n() {
        this.f.clear();
    }

    public void a(String string, b b2) {
        c c2 = new c(this);
        c2.a = string;
        c2.c = b2;
        if (b2.q == null) {
            l.a("Skipping:" + string + " as mod sourceFolder is null");
            return;
        }
        String string2 = string;
        String string3 = b2.q;
        if (string2.startsWith(string3)) {
            string2 = string2.substring(string3.length());
        } else {
            String string4 = com.corrodinggames.rts.gameFramework.e.a.o(string2);
            if (string4.startsWith(string3)) {
                string2 = string4.substring(string3.length());
                l.e("Mod path:" + b2.q + " in map path without tag:" + string2);
            } else {
                l.a("Mod path:" + b2.q + " not in map path:" + string2);
            }
        }
        c2.b = string2;
        b2.A = true;
        ++b2.F;
        this.f.add(c2);
    }

    public b h(String string) {
        String[] stringArray;
        if (string.contains("MOD|") && (stringArray = string.split("/")).length >= 2) {
            for (int i2 = stringArray.length - 2; i2 >= 0; --i2) {
                String string2 = stringArray[i2];
                if (!string2.startsWith("MOD|")) continue;
                String string3 = string2.substring("MOD|".length());
                b b2 = this.c(string3);
                if (b2 == null) {
                    l.e("getLinkedModForFile: Failed to find mod with hash:" + string3);
                    return null;
                }
                return b2;
            }
        }
        return null;
    }
}
