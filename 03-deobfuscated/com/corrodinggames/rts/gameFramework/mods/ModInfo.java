/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.mods;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.filesystem.FilePathSanitizer;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.mods.VersionChecker;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ModInfo
implements Comparable {
    public int modId;
    public String c;
    public String q;
    public long k;  // 02b i/b.java L30  // 02b i/b.java L36  // 02b i/b.java L22: 本地化名键
    public static int modVersion = 1;
    public String modName;
    public String modAuthor;
    public String modDescription;
    public boolean isEnabled;
    public boolean m;  // 02b i/b.java L32: 内置 mod 标记
    public boolean isLoaded;
    public boolean hasErrors;
    public boolean isBuiltinMod;
    public boolean isActiveMod;
    public long modFileSize;
    public boolean l;
    public boolean requiresRestart;
    public String modDirectory;
    public String modUrl;
    public String modImagePath;
    public String modVersionString;
    public boolean isDownloaded;
    public String workshopId;
    public String modTags;
    public String modCategory;
    public String modDependencies;
    public int priority;
    public int loadOrder;
    public boolean isCompatible;
    public boolean requiresUpdate;
    public boolean A = false;
    public boolean B = true;
    public boolean C;
    public boolean D;
    public int E;
    public int F;
    public long G;
    public long H;
    public int I;
    public int J;
    public static int K = 1;
    public int L = K++;
    public String M;
    public boolean N;
    public boolean O;
    public int P;
    ArrayList Q = new ArrayList();
    public String R;
    public String S;
    public String T;
    public ArrayList U = new ArrayList();
    public ArrayList V = new ArrayList();
    public boolean modInfoParsed;  // 02b i/b.r: mod 已解析标志
    public String minVersion;  // 02b i/b.v: mod-info.txt minVersion
    public int checkedVersion;  // 02b i/b.w: 已检查版本号

    public ModInfo() {
        this.modId = modVersion++;
    }

    public String a() {
        if (this.workshopId != null) {
            return this.workshopId;
        }
        if (this.modTags != null) {
            return this.modTags;
        }
        return this.modName;
    }

    public String b() {
        String string = this.a();
        return GameUtils.a(string, 25);
    }

    public String c() {
        String string = this.a();
        return GameUtils.a(string, 40);
    }

    public int d() {
        return this.L;
    }

    public String e() {
        return this.f();
    }

    public String f() {
        String string = "";
        if (this.modCategory != null) {
            string = string + this.modCategory;
        }
        String string2 = "RAM:" + this.s();
        if (this.modDirectory != null) {
            string2 = string2 + " Storage: slow external unpacked";
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.at() && this.modVersionString != null && com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(this.modVersionString) && !this.isActiveMod) {
            string2 = string2 + " Warning: slow external storage";
        }
        string = string + "\n (" + string2 + ")";
        return string;
    }

    public String g() {
        if (this.isCompatible) {
            return this.modVersionString;
        }
        return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(this.modVersionString);
    }

    public String h() {
        if (this.isCompatible) {
            return this.modImagePath;
        }
        return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(this.modImagePath);
    }

    public String i() {
        File file = new File(this.g());
        return file.getAbsolutePath();
    }

    public String j() {
        return this.modVersionString;
    }

    public String k() throws IOException {
        File file = new File(this.g());
        return file.getCanonicalPath();
    }

    public String l() {
        String string = this.S;
        if (this.T != null) {
            if (string == null) {
                string = "";
            }
            string = string + this.T;
        }
        if (string == null && this.U.size() > 0) {
            string = "";
            int n2 = 0;
            int n3 = 0;
            for (String string2 : (java.util.Collection<String>) (java.util.Collection) this.U) {
                if (n3 <= 2) {
                    string = string == null ? string2 : string + "\n" + string2;
                } else {
                    ++n2;
                }
                ++n3;
            }
            if (n2 > 0) {
                string = string + "\n" + n2 + " more warnings...";
            }
        }
        if (!(this.C && string == null || this.D || string != null)) {
            if (string == null) {
                string = "";
            }
            string = string + "Not yet loaded, reload needed";
        }
        return string;
    }

    public boolean m() {
        return !this.isEnabled && this.R == null;
    }

    public void a(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b("Adding error for mod: " + this.b() + (this.m() ? "" : "(disabled)") + ": " + string);
        if (this.R == null) {
            if (!this.isEnabled) {
                int n2;
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                String string2 = string;
                if (string2 != null && (!string2.contains(this.a()) || string2.contains(this.b()))) {
                    string2 = "Error loading mod '" + this.b() + "': " + string2;
                }
                if ((n2 = l2.bZ.d(this.b())) > 1) {
                    string2 = string2 + " (NOTE: You have " + n2 + " mods with the same title: '" + this.b() + "' this might make debugging tricky)";
                }
                l2.i(string2);
            }
            com.corrodinggames.rts.gameFramework.GlobalState.e("Disabling mod due to error: " + this.b() + " path:" + this.i());
            this.R = string;
        }
        this.V.add(string);
    }

    public void b(String string) {
        if (this.U.contains(string)) {
            return;
        }
        this.U.add(string);
    }

    public String a(String string, int n2) {
        if (n2 > 4) {
            return null;
        }
        String[] stringArray = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.h(string);
        if (stringArray == null) {
            return null;
        }
        for (String string2 : stringArray) {
            if (!string2.equalsIgnoreCase("mod-info.txt")) continue;
            return string + "/" + "mod-info.txt";
        }
        if (stringArray.length > 5) {
            return null;
        }
        for (String string2 : stringArray) {
            String string3;
            String string4 = string + "/" + string2;
            if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.f(string4) || (string3 = this.a(string4, n2 + 1)) == null) continue;
            return string3;
        }
        return null;
    }

    public void n() {
        if (com.corrodinggames.rts.gameFramework.GlobalState.B().d()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("SAFE MODE: skipping setSourceFolder");
            return;
        }
        String string = this.modVersionString + "/" + "mod-info.txt";
        if (!com.corrodinggames.rts.gameFramework.filesystem.FilePathSanitizer.f("mods-info", string)) {
            String string2 = this.modVersionString;
            if (string2 == null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("setSourceFolder: sourceFolder==null");
                return;
            }
            String[] stringArray = com.corrodinggames.rts.gameFramework.filesystem.FilePathSanitizer.d("mods-dir-search", string2);
            if (stringArray != null && stringArray.length == 1) {
                String string3 = stringArray[0];
                String string4 = string2 + "/" + string3;
                String string5 = string4 + "/" + "mod-info.txt";
                if (com.corrodinggames.rts.gameFramework.filesystem.FileLoader.f(string4) && com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(string5)) {
                    com.corrodinggames.rts.gameFramework.GlobalState.e("Changing mod sourceFolder to:" + string4);
                    this.modVersionString = string4;
                }
            }
        }
    }

    public ab o() {
        Object object;
        InputStream inputStream;
        if (this.modVersionString == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("No source yet for mod: " + this.modName);
            return null;
        }
        String string = this.modVersionString + "/" + "mod-info.txt";
        try {
            inputStream = this.isActiveMod ? com.corrodinggames.rts.gameFramework.filesystem.FilePathSanitizer.e("mods-info", string) : com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string);
        }
        catch (Exception exception) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Error loading mod info for: " + this.modName + " at " + string);
            exception.printStackTrace();
            this.b("Error loading mod-info.txt: " + exception.getMessage());
            return null;
        }
        if (inputStream == null && (object = this.a(this.modVersionString, 1)) != null) {
            com.corrodinggames.rts.gameFramework.utility.AssetStream j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string);
            if (j2 != null) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("mod-info.txt cache seems to be invalid for: " + string);
                com.corrodinggames.rts.gameFramework.filesystem.FilePathSanitizer.c("mods-info", string);
                inputStream = j2;
            } else {
                String string2 = "No mod info at " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.d(string) + " but found one nested at: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.d((String)object) + " (Hint: This mod might have been extracted with an extra folder)";
                this.a(string2);
            }
        }
        if (inputStream == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("No mod info for: " + this.modName + " at " + string);
            return null;
        }
        try {
            object = new ab(inputStream, string);
        }
        catch (IOException iOException) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Error loading mod info for: " + this.modName + " at " + string);
            iOException.printStackTrace();
            this.b("Error loading mod-info.txt: " + iOException.getMessage());
            return null;
        }
        return (ab)object;
    }

    public String p() {
        String string = this.c("thumbnail");
        if (string != null) {
            string = this.i() + "/" + string;
            return string;
        }
        return null;
    }

    public ArrayList q() {
        return this.Q;
    }

    public String c(String string) {
        String string2 = "mod";
        ab ab2 = this.o();
        if (ab2 == null) {
            return null;
        }
        return ab2.b(string2, string, (String)null);
    }

    public void r() {
        String string;
        Object object;
        Object object2;
        Object object3;
        if (com.corrodinggames.rts.gameFramework.GlobalState.B().d()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("SAFE MODE: refreshData: Skipping mod read");
            this.modCategory = "<< SAFE MODE ACTIVE: MOD DATA SKIPPED. RESTART IN NORMAL MODE. >>";
            return;
        }
        ab ab2 = this.o();
        if (ab2 != null) {
            String[] stringArray;
            object3 = ab2;
            object2 = "mod";
            object = "music";
            this.workshopId = ((ab)object3).b((String)object2, "title", (String)null);
            this.modCategory = ((ab)object3).b((String)object2, "description", (String)null);
            if (this.modCategory != null && this.modCategory.contains("\\n")) {
                this.modCategory = this.modCategory.replace("\\n", "\n");
            }
            this.modDependencies = ((ab)object3).b((String)object2, "minVersion", (String)null);
            if (this.modDependencies != null && !this.modDependencies.trim().equals("")) {
                try {
                    com.corrodinggames.rts.gameFramework.mods.VersionChecker.a(this.modDependencies);
                }
                catch (bo bo2) {
                    String string6 = bo2.getMessage();
                    this.a(string6);
                }
            }
            this.M = ((ab)object3).b((String)object, "sourceFolder", (String)null);
            this.N = ((ab)object3).a((String)object, "whenUsingUnitsFromThisMod_playExclusively", (Boolean)false);
            this.O = ((ab)object3).a((String)object, "addToNormalPlaylist", (Boolean)false);
            if (this.M != null && this.m()) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Loading music for: " + this.a());
                string = GameUtils.b(this.modVersionString, this.M);
                stringArray = com.corrodinggames.rts.gameFramework.filesystem.FilePathSanitizer.d("mods-dir-music", string);
                if (stringArray == null) {
                    this.b("Could not read target music folder: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(string));
                } else {
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (String string2 : stringArray) {
                        if (!string2.toLowerCase().endsWith(".ogg")) continue;
                        String string3 = GameUtils.b(string, string2);
                        if (!this.Q.contains(string3)) {
                            com.corrodinggames.rts.gameFramework.GlobalState.e("Found music track: " + string2);
                        }
                        arrayList.add(string3);
                    }
                    this.Q = arrayList;
                    if (this.Q.size() == 0) {
                        this.b("Could not find any .ogg files in music folder: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2(string));
                    }
                }
            }
            this.isDownloaded = true;
        }
        if (((File)(object2 = new File((String)(object3 = this.w())))).exists() && !((File)object2).isDirectory()) {
            object = null;
            try {
                object = new ab((String)object3);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                this.b("IO error reading: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString2((String)object3));
            }
            if (object != null) {
                string = "steam";
                this.modFileSize = ((ab)object).a(string, "id", 0L);
            }
        }
    }

    private String w() {
        return this.g() + "/steam.dat";
    }

    public boolean a(long l2) {
        this.modFileSize = l2;
        String string = this.w();
        try {
            PrintWriter printWriter = new PrintWriter(string);
            printWriter.println("[steam]");
            printWriter.println("id: " + l2);
            printWriter.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            com.corrodinggames.rts.gameFramework.GlobalState.B().i("IO error: Failed to save workshop id for mod at: " + string);
            return false;
        }
        return true;
    }

    public String s() {
        String string = "";
        string = string + String.format("%.2f", Float.valueOf((float)((double)(this.G + this.H) / 1000.0 / 1000.0))) + " mb" + (this.C ? " - disabled" : "");
        return string;
    }

    public void t() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Mod: '" + this.a() + "' - Memory use:" + this.s() + " " + (this.m() ? "" : " (disabled)"));
    }

    public boolean u() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Trying to delete mod: '" + this.a() + "'");
        String string = this.h();
        com.corrodinggames.rts.gameFramework.GlobalState.e("sourceFolder: '" + string + "'");
        if (!this.v()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Mod: '" + this.a() + "' - Cannot be deleted");
            return false;
        }
        File file = new File(string);
        if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(file.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Mod: '" + this.a() + "' - cannot delete: Not a file");
            return false;
        }
        boolean bl = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file);
        com.corrodinggames.rts.gameFramework.GlobalState.e("Delete result: " + bl);
        return bl;
    }

    public boolean v() {
        if (this.requiresUpdate) {
            return false;
        }
        if (com.corrodinggames.rts.gameFramework.GlobalState.aZ && this.isActiveMod) {
            return true;
        }
        return com.corrodinggames.rts.gameFramework.GlobalState.at() && this.isActiveMod;
    }

    public int a(ModInfo b2) {
        ModInfo b3 = this;
        if (b2 == null) {
            return 0;
        }
        int n2 = b3.loadOrder;
        int n3 = b2.loadOrder;
        if (n2 != n3) {
            return n2 - n3;
        }
        String string = b3.a();
        String string2 = b2.a();
        if (string == null) {
            string = "";
        }
        if (string2 == null) {
            string2 = "";
        }
        return string.compareTo(string2);
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((ModInfo) object);
    }
}
