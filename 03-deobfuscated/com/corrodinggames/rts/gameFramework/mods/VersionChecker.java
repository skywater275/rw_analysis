/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.mods;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.mods.ModInfo;
import com.corrodinggames.rts.gameFramework.mods.ModLoadEntry;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

public class VersionChecker {
    public static String currentVersion;
    public static String latestVersion;
    public ModInfo checkTask = new ModInfo();
    Object d = new Object();
    ArrayList e = new ArrayList();
    ArrayList f = new ArrayList();

    public VersionChecker() {
        try {
            a(GlobalState.B().getVersion());
        }
        catch (bo bo2) {
            throw new RuntimeException(bo2);
        }
    }

    private static int a(String string, int n2) throws bo {
        String[] stringArray = GameUtils.c(string, '.');
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

    public static void a(String string) throws bo {
        String string2 = GlobalState.B().getVersion();
        a(string, string2);
    }

    public static String b(String string) {
        string = GameUtils.a(string, "v", "");
        string = string.trim();
        string = GameUtils.a(string, "a", "");
        string = GameUtils.a(string, "b", "");
        string = GameUtils.a(string, "c", "");
        string = GameUtils.a(string, "d", "");
        string = GameUtils.a(string, "e", "");
        string = GameUtils.a(string, "f", "");
        string = GameUtils.a(string, "g", "");
        string = GameUtils.a(string, "h1", "");
        string = GameUtils.a(string, "h2", "");
        string = GameUtils.a(string, "h3", "");
        string = GameUtils.a(string, "h4", "");
        return string;
    }

    public static void a(String string, String string2) throws bo {
        string2 = b(string2);
        String string3 = string = b(string);
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
                n7 = a(string2, 0);
                n6 = a(string, 0);
                n5 = a(string2, 1);
                n4 = a(string, 1);
                n3 = a(string2, 2);
                n2 = a(string, 2);
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
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (b2.isEnabled || b2.D || bl && b2.R != null) continue;
            ++n2;
        }
        return n2;
    }

    public int b() {
        int n2 = 0;
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (b2.isEnabled || b2.R == null) continue;
            ++n2;
        }
        return n2;
    }

    public int c() {
        int n2 = 0;
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (b2.requiresUpdate) continue;
            ++n2;
        }
        return n2;
    }

    public void d() {
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            b2.requiresRestart = b2.isEnabled;
            b2.isLoaded = false;
        }
    }

    public void e() {
        GlobalState l2 = GlobalState.B();
        String string = "";
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            String string2 = b2.modName;
            string2 = string2.replace(",", " ");
            if ((string2 = string2.replace("|", " ")).length() > 15) {
                string2 = string2.substring(12) + "...";
            }
            if (string.length() != 0) {
                string = string + ",";
            }
            String string3 = b2.isEnabled ? "disabled" : "enabled";
            string = string + string2 + "|" + b2.modDescription + "|" + string3;
        }
        l2.bQ.modSettingsVersion = 1;
        l2.bQ.modSettings = string;
    }

    public void f() {
        String[] stringArray;
        GlobalState.e("Loading mod selection");
        GlobalState l2 = GlobalState.B();
        String string = l2.bQ.modSettings;
        for (String string2 : stringArray = string.split(",")) {
            boolean bl;
            String[] stringArray2 = string2.split("\\|");
            if (stringArray2.length != 3) {
                GlobalState.e("loadSelection: wrong count (" + stringArray2.length + "):" + string2);
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
                GlobalState.e("loadSelection: Unknown option:" + string2);
                continue;
            }
            ModInfo b2 = this.c(string4);
            if (b2 == null) {
                GlobalState.e("loadSelection: Did not find mod in settings:" + string3);
                continue;
            }
            b2.isEnabled = bl;
            b2.hasErrors = true;
        }
    }

    public ModInfo c(String string) {
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (!b2.modDescription.equals(string)) continue;
            return b2;
        }
        return null;
    }

    public int d(String string) {
        if (string == null) {
            return 0;
        }
        int n2 = 0;
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (!string.equals(b2.c())) continue;
            ++n2;
        }
        return n2;
    }

    public ModInfo a(int n2) {
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (b2.L != n2) continue;
            return b2;
        }
        return null;
    }

    public void g() {
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            b2.isEnabled = true;
        }
    }

    public int h() {
        int n2 = 0;
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (b2.isEnabled && !b2.D) continue;
            ++n2;
        }
        return n2;
    }

    public ModInfo e(String string) {
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (!b2.modAuthor.equals(string)) continue;
            return b2;
        }
        return null;
    }

    public ModInfo f(String string) {
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (!b2.a().equals(string)) continue;
            return b2;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ModInfo a(String string, String string2, String string3, String string4, boolean bl, boolean bl2, boolean bl3, int n2) {
        ModInfo b2 = this.c(string4);
        if (b2 == null) {
            b2 = new ModInfo();
            b2.modName = string;
            b2.modAuthor = string2;
            b2.modDescription = string4;
            boolean bl4 = b2.isEnabled = !bl;
        }
        if (b2.modVersionString == null && string3 != null) {
            b2.modImagePath = b2.modVersionString = string3;
            b2.n();
            if (b2.modVersionString != null && b2.modVersionString.toLowerCase(Locale.ROOT).contains("rwmod")) {
                b2.isBuiltinMod = true;
            }
        }
        b2.loadOrder = n2;
        b2.l = true;
        b2.isCompatible = bl2;
        b2.requiresUpdate = bl3;
        if (!b2.requiresUpdate) {
            b2.modUrl = "Storage: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b.e(b2.modVersionString);
        }
        b2.r();
        Object object = this.d;
        synchronized (object) {
            if (!this.e.contains(b2)) {
                ArrayList<ModInfo> arrayList = new ArrayList<ModInfo>();
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
    public void a(ModInfo b2) {
        Object object = this.d;
        synchronized (object) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.e);
            arrayList.remove(b2);
            this.e = arrayList;
        }
    }

    public void a(String string, boolean bl, boolean bl2) {
        GlobalState.e("loading mod custom units at:" + string);
        String[] stringArray = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b.b(string, false);
        if (stringArray == null) {
            GlobalState.b("getAllModList: ERROR");
            GlobalState.b("getAllModList: Failed to load:" + string);
            return;
        }
        for (String string2 : stringArray) {
            String string3 = string + "/" + string2;
            if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled3(string3) && !string2.endsWith(".ini")) continue;
            String string4 = GameUtils.e(string2);
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
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (!b2.m()) continue;
            arrayList.addAll(b2.q());
        }
        return arrayList;
    }

    public ArrayList j() {
        ArrayList<ModInfo> arrayList = new ArrayList<ModInfo>();
        for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
            if (!b2.m()) continue;
            arrayList.add(b2);
        }
        return arrayList;
    }

    public ArrayList k() {
        Object object2;
        for (Object object2_402 : this.e) {
            ((ModInfo) object2_402).l = false;
            if (!((ModInfo) object2_402).m) continue;
            ((ModInfo) object2_402).l = true;
        }
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine a2 = com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a();
        if (a2 != null) {
            a2.l();
        } else {
            GlobalState.e("getAllModList: SteamEngine==null");
        }
        object2 = com.corrodinggames.rts.game.units.custom.ModLoader.getModsDirectoryPath();
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled3((String)object2)) {
            GlobalState.e("Modded Custom '" + (String)object2 + "' directory not found");
        } else {
            boolean bl = false;
            this.a((String)object2, true, bl);
        }
        String string = com.corrodinggames.rts.game.units.custom.ModLoader.k();
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled3(string)) {
            GlobalState.e("Modded Custom '" + string + "' directory not found");
        } else {
            boolean bl = true;
            this.a(string, false, bl);
        }
        String string2 = com.corrodinggames.rts.game.units.custom.ModLoader.l();
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.isEnabled3(string2)) {
            GlobalState.e("Modded Custom '" + string2 + "' directory not found");
        } else {
            boolean bl = true;
            this.a(string2, true, bl);
        }
        for (Iterator iterator : (java.util.Collection<Iterator>) (java.util.Collection) this.e) {
            if (((ModInfo) ((Object)iterator)).l) continue;
            GlobalState.e("Removing mod no longer found on system: " + ((ModInfo) ((Object)iterator)).a());
            this.a((ModInfo) ((Object)iterator));
        }
        GlobalState.e("========= Mods ===========");
        GlobalState.e("Number of mods:" + this.e.size());
        for (Iterator iterator : (java.util.Collection<Iterator>) (java.util.Collection) this.e) {
            GlobalState.e("Mod: '" + ((ModInfo) ((Object)iterator)).a());
        }
        GlobalState.e("================================");
        GlobalState l2 = GlobalState.B();
        if (l2.bQ.lastModCount == -1 || l2.bQ.modSettingsVersion < 1) {
            GlobalState.e("Disabling all new mods for first/new load");
            for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
                b2.isEnabled = true;
            }
            this.e();
            l2.bQ.save();
        } else if (this.e.size() > l2.bQ.lastModCount + 4) {
            GlobalState.e("Too many new mods found, not enabling new mods");
            GlobalState.e("Number of mods:" + this.e.size() + " vs " + l2.bQ.lastModCount);
            for (ModInfo b3 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
                if (b3.hasErrors) continue;
                b3.isEnabled = true;
            }
            this.e();
            l2.bQ.save();
        }
        l2.bQ.lastModCount = this.e.size();
        return this.e;
    }

    public void l() {
        GlobalState l2 = GlobalState.B();
        try {
            l2.isStepFrame = true;  // 02b l.br (L119) 保序位
            l2.e();
            this.a(false, false);
        }
        finally {
            l2.isStepFrame = false;
        }
        l2.x();
    }

    public void a(boolean bl2, boolean bl3) {
        GlobalState l2 = GlobalState.B();
        ae.b();
        if (!bl3) {
            for (ModInfo b2 : (java.util.Collection<ModInfo>) (java.util.Collection) this.e) {
                if (b2.R != null) {
                    GlobalState.e("re-enabling mod: " + b2.a());
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
                b2.priority = 0;
            }
        }
        this.k();
        ArrayList arrayList = new ArrayList(com.corrodinggames.rts.game.units.custom.l.d);
        if (!bl3) {
            com.corrodinggames.rts.game.units.custom.ModLoader.h();
        } else {
            com.corrodinggames.rts.game.units.custom.ModLoader.b();
        }
        if (bl2) {
            int n2 = 0;
            Iterator iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                com.corrodinggames.rts.game.units.custom.l l3 = (com.corrodinggames.rts.game.units.custom.l)iterator.next();
                if (l3.J == null || l3.J.f || l3.J.R == null || com.corrodinggames.rts.game.units.custom.l.a(l3) != null) continue;
                GlobalState.e("Was missing: " + l3.M);
                com.corrodinggames.rts.game.units.custom.l.d.add(l3);
                ++n2;
            }
            if (n2 > 0) {
                com.corrodinggames.rts.game.units.custom.ModLoader.e();
            }
        }
        com.corrodinggames.rts.game.units.custom.l.A();
        com.corrodinggames.rts.game.PlayerState.P();
    }

    public void m() {
        GlobalState l2 = GlobalState.B();
        if (l2.dH != null) {
            l2.dH.d();
        } else {
            GlobalState.e("No active callbacks");
        }
    }

    public String[] a(String[] stringArray, String string) {
        String[] object;
        GlobalState.e("addExtraMapsForPath: " + string);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        if (stringArray != null) {
            object = stringArray;
            int n2 = ((String[])object).length;
            for (int i2 = 0; i2 < n2; ++i2) {
                Object object2 = object[i2];
                arrayList.add(object2);
            }
        }
        if (GlobalState.at() && "/SD/rusted_warfare_maps".equals(string) && (object = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a("/SD/rustedWarfare/maps", true)) != null) {
            for (Object object3 : object) {
                arrayList.add("NEW_PATH|maps2/" + (String)object3);
            }
        }
        for (ModLoadEntry c2 : (java.util.Collection<ModLoadEntry>) (java.util.Collection) this.g(string)) {
            arrayList.add("MOD|" + c2.c.modDescription + "/" + c2.b);
        }
        if (stringArray == null && arrayList.size() == 0) {
            return null;
        }
        return arrayList.toArray(new String[0]);
    }

    public ArrayList g(String string) {
        ArrayList<ModLoadEntry> arrayList = new ArrayList<ModLoadEntry>();
        for (ModLoadEntry c2 : (java.util.Collection<ModLoadEntry>) (java.util.Collection) this.f) {
            boolean bl2 = false;
            if (string.startsWith("mod/") && string.startsWith("mod/" + c2.c.modDescription)) {
                bl2 = true;
            }
            if (!c2.c.isEnabled && string.startsWith("/SD/rusted_warfare_maps")) {
                bl2 = true;
            }
            if (!bl2) continue;
            GlobalState.e("Adding extra map:" + c2.a);
            arrayList.add(c2);
        }
        return arrayList;
    }

    public void n() {
        this.f.clear();
    }

    public void a(String string, ModInfo b2) {
        ModLoadEntry c2 = new ModLoadEntry(this);
        c2.a = string;
        c2.c = b2;
        if (b2.modVersionString == null) {
            GlobalState.a("Skipping:" + string + " as mod sourceFolder is null");
            return;
        }
        String string2 = string;
        String string3 = b2.modVersionString;
        if (string2.startsWith(string3)) {
            string2 = string2.substring(string3.length());
        } else {
            String string4 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.o(string2);
            if (string4.startsWith(string3)) {
                string2 = string4.substring(string3.length());
                GlobalState.e("Mod path:" + b2.modVersionString + " in map path without tag:" + string2);
            } else {
                GlobalState.a("Mod path:" + b2.modVersionString + " not in map path:" + string2);
            }
        }
        c2.b = string2;
        b2.A = true;
        ++b2.F;
        this.f.add(c2);
    }

    public ModInfo h(String string) {
        String[] stringArray;
        if (string.contains("MOD|") && (stringArray = string.split("/")).length >= 2) {
            for (int i2 = stringArray.length - 2; i2 >= 0; --i2) {
                String string2 = stringArray[i2];
                if (!string2.startsWith("MOD|")) continue;
                String string3 = string2.substring("MOD|".length());
                ModInfo b2 = this.c(string3);
                if (b2 == null) {
                    GlobalState.e("getLinkedModForFile: Failed to find mod with hash:" + string3);
                    return null;
                }
                return b2;
            }
        }
        return null;
    }
}
