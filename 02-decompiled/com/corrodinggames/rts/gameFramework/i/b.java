/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.i;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.e.g;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.i.a;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class b
implements Comparable {
    public int a;
    public static int b = 1;
    public String c;
    public String d;
    public String e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public long k;
    boolean l;
    public boolean m;
    public String n;
    public String o;
    public String p;
    public String q;
    public boolean r;
    public String s;
    public String t;
    public String u;
    public String v;
    public int w;
    public int x;
    public boolean y;
    public boolean z;
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

    public b() {
        this.a = b++;
    }

    public String a() {
        if (this.s != null) {
            return this.s;
        }
        if (this.t != null) {
            return this.t;
        }
        return this.c;
    }

    public String b() {
        String string = this.a();
        return com.corrodinggames.rts.gameFramework.f.a(string, 25);
    }

    public String c() {
        String string = this.a();
        return com.corrodinggames.rts.gameFramework.f.a(string, 40);
    }

    public int d() {
        return this.L;
    }

    public String e() {
        return this.f();
    }

    public String f() {
        String string = "";
        if (this.u != null) {
            string = string + this.u;
        }
        String string2 = "RAM:" + this.s();
        if (this.n != null) {
            string2 = string2 + " Storage: slow external unpacked";
        }
        if (com.corrodinggames.rts.gameFramework.l.at() && this.q != null && com.corrodinggames.rts.gameFramework.e.a.a(this.q) && !this.j) {
            string2 = string2 + " Warning: slow external storage";
        }
        string = string + "\n (" + string2 + ")";
        return string;
    }

    public String g() {
        if (this.y) {
            return this.q;
        }
        return com.corrodinggames.rts.gameFramework.e.a.e(this.q);
    }

    public String h() {
        if (this.y) {
            return this.p;
        }
        return com.corrodinggames.rts.gameFramework.e.a.e(this.p);
    }

    public String i() {
        File file = new File(this.g());
        return file.getAbsolutePath();
    }

    public String j() {
        return this.q;
    }

    public String k() {
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
            for (String string2 : this.U) {
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
        return !this.f && this.R == null;
    }

    public void a(String string) {
        com.corrodinggames.rts.gameFramework.l.b("Adding error for mod: " + this.b() + (this.m() ? "" : "(disabled)") + ": " + string);
        if (this.R == null) {
            if (!this.f) {
                int n2;
                l l2 = com.corrodinggames.rts.gameFramework.l.B();
                String string2 = string;
                if (string2 != null && (!string2.contains(this.a()) || string2.contains(this.b()))) {
                    string2 = "Error loading mod '" + this.b() + "': " + string2;
                }
                if ((n2 = l2.bZ.d(this.b())) > 1) {
                    string2 = string2 + " (NOTE: You have " + n2 + " mods with the same title: '" + this.b() + "' this might make debugging tricky)";
                }
                l2.i(string2);
            }
            com.corrodinggames.rts.gameFramework.l.e("Disabling mod due to error: " + this.b() + " path:" + this.i());
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
        String[] stringArray = com.corrodinggames.rts.gameFramework.e.a.h(string);
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
            if (!com.corrodinggames.rts.gameFramework.e.a.f(string4) || (string3 = this.a(string4, n2 + 1)) == null) continue;
            return string3;
        }
        return null;
    }

    public void n() {
        if (com.corrodinggames.rts.gameFramework.l.B().d()) {
            com.corrodinggames.rts.gameFramework.l.e("SAFE MODE: skipping setSourceFolder");
            return;
        }
        String string = this.q + "/" + "mod-info.txt";
        if (!com.corrodinggames.rts.gameFramework.e.g.f("mods-info", string)) {
            String string2 = this.q;
            if (string2 == null) {
                com.corrodinggames.rts.gameFramework.l.e("setSourceFolder: sourceFolder==null");
                return;
            }
            String[] stringArray = com.corrodinggames.rts.gameFramework.e.g.d("mods-dir-search", string2);
            if (stringArray != null && stringArray.length == 1) {
                String string3 = stringArray[0];
                String string4 = string2 + "/" + string3;
                String string5 = string4 + "/" + "mod-info.txt";
                if (com.corrodinggames.rts.gameFramework.e.a.f(string4) && com.corrodinggames.rts.gameFramework.e.a.i(string5)) {
                    com.corrodinggames.rts.gameFramework.l.e("Changing mod sourceFolder to:" + string4);
                    this.q = string4;
                }
            }
        }
    }

    public ab o() {
        Object object;
        InputStream inputStream;
        if (this.q == null) {
            com.corrodinggames.rts.gameFramework.l.e("No source yet for mod: " + this.c);
            return null;
        }
        String string = this.q + "/" + "mod-info.txt";
        try {
            inputStream = this.j ? com.corrodinggames.rts.gameFramework.e.g.e("mods-info", string) : com.corrodinggames.rts.gameFramework.e.a.k(string);
        }
        catch (Exception exception) {
            com.corrodinggames.rts.gameFramework.l.e("Error loading mod info for: " + this.c + " at " + string);
            exception.printStackTrace();
            this.b("Error loading mod-info.txt: " + exception.getMessage());
            return null;
        }
        if (inputStream == null && (object = this.a(this.q, 1)) != null) {
            j j2 = com.corrodinggames.rts.gameFramework.e.a.k(string);
            if (j2 != null) {
                com.corrodinggames.rts.gameFramework.l.a("mod-info.txt cache seems to be invalid for: " + string);
                com.corrodinggames.rts.gameFramework.e.g.c("mods-info", string);
                inputStream = j2;
            } else {
                String string2 = "No mod info at " + com.corrodinggames.rts.gameFramework.e.a.d(string) + " but found one nested at: " + com.corrodinggames.rts.gameFramework.e.a.d((String)object) + " (Hint: This mod might have been extracted with an extra folder)";
                this.a(string2);
            }
        }
        if (inputStream == null) {
            com.corrodinggames.rts.gameFramework.l.e("No mod info for: " + this.c + " at " + string);
            return null;
        }
        try {
            object = new ab(inputStream, string);
        }
        catch (IOException iOException) {
            com.corrodinggames.rts.gameFramework.l.e("Error loading mod info for: " + this.c + " at " + string);
            iOException.printStackTrace();
            this.b("Error loading mod-info.txt: " + iOException.getMessage());
            return null;
        }
        return object;
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
        if (com.corrodinggames.rts.gameFramework.l.B().d()) {
            com.corrodinggames.rts.gameFramework.l.e("SAFE MODE: refreshData: Skipping mod read");
            this.u = "<< SAFE MODE ACTIVE: MOD DATA SKIPPED. RESTART IN NORMAL MODE. >>";
            return;
        }
        ab ab2 = this.o();
        if (ab2 != null) {
            String[] stringArray;
            object3 = ab2;
            object2 = "mod";
            object = "music";
            this.s = ((ab)object3).b((String)object2, "title", (String)null);
            this.u = ((ab)object3).b((String)object2, "description", (String)null);
            if (this.u != null && this.u.contains("\\n")) {
                this.u = this.u.replace("\\n", "\n");
            }
            this.v = ((ab)object3).b((String)object2, "minVersion", (String)null);
            if (this.v != null && !this.v.trim().equals("")) {
                try {
                    com.corrodinggames.rts.gameFramework.i.a.a(this.v);
                }
                catch (bo bo2) {
                    stringArray = bo2.getMessage();
                    this.a((String)stringArray);
                }
            }
            this.M = ((ab)object3).b((String)object, "sourceFolder", (String)null);
            this.N = ((ab)object3).a((String)object, "whenUsingUnitsFromThisMod_playExclusively", (Boolean)false);
            this.O = ((ab)object3).a((String)object, "addToNormalPlaylist", (Boolean)false);
            if (this.M != null && this.m()) {
                com.corrodinggames.rts.gameFramework.l.e("Loading music for: " + this.a());
                string = com.corrodinggames.rts.gameFramework.f.b(this.q, this.M);
                stringArray = com.corrodinggames.rts.gameFramework.e.g.d("mods-dir-music", string);
                if (stringArray == null) {
                    this.b("Could not read target music folder: " + com.corrodinggames.rts.gameFramework.e.a.e(string));
                } else {
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (String string2 : stringArray) {
                        if (!string2.toLowerCase().endsWith(".ogg")) continue;
                        String string3 = com.corrodinggames.rts.gameFramework.f.b(string, string2);
                        if (!this.Q.contains(string3)) {
                            com.corrodinggames.rts.gameFramework.l.e("Found music track: " + string2);
                        }
                        arrayList.add(string3);
                    }
                    this.Q = arrayList;
                    if (this.Q.size() == 0) {
                        this.b("Could not find any .ogg files in music folder: " + com.corrodinggames.rts.gameFramework.e.a.e(string));
                    }
                }
            }
            this.r = true;
        }
        if (((File)(object2 = new File((String)(object3 = this.w())))).exists() && !((File)object2).isDirectory()) {
            object = null;
            try {
                object = new ab((String)object3);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                this.b("IO error reading: " + com.corrodinggames.rts.gameFramework.e.a.e((String)object3));
            }
            if (object != null) {
                string = "steam";
                this.k = ((ab)object).a(string, "id", 0L);
            }
        }
    }

    private String w() {
        return this.g() + "/steam.dat";
    }

    public boolean a(long l2) {
        this.k = l2;
        String string = this.w();
        try {
            PrintWriter printWriter = new PrintWriter(string);
            printWriter.println("[steam]");
            printWriter.println("id: " + l2);
            printWriter.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            com.corrodinggames.rts.gameFramework.l.B().i("IO error: Failed to save workshop id for mod at: " + string);
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
        com.corrodinggames.rts.gameFramework.l.e("Mod: '" + this.a() + "' - Memory use:" + this.s() + " " + (this.m() ? "" : " (disabled)"));
    }

    public boolean u() {
        com.corrodinggames.rts.gameFramework.l.e("Trying to delete mod: '" + this.a() + "'");
        String string = this.h();
        com.corrodinggames.rts.gameFramework.l.e("sourceFolder: '" + string + "'");
        if (!this.v()) {
            com.corrodinggames.rts.gameFramework.l.e("Mod: '" + this.a() + "' - Cannot be deleted");
            return false;
        }
        File file = new File(string);
        if (!com.corrodinggames.rts.gameFramework.e.a.i(file.getAbsolutePath())) {
            com.corrodinggames.rts.gameFramework.l.e("Mod: '" + this.a() + "' - cannot delete: Not a file");
            return false;
        }
        boolean bl = com.corrodinggames.rts.gameFramework.e.a.b(file);
        com.corrodinggames.rts.gameFramework.l.e("Delete result: " + bl);
        return bl;
    }

    public boolean v() {
        if (this.z) {
            return false;
        }
        if (com.corrodinggames.rts.gameFramework.l.aZ && this.j) {
            return true;
        }
        return com.corrodinggames.rts.gameFramework.l.at() && this.j;
    }

    public int a(b b2) {
        b b3 = this;
        if (b2 == null) {
            return 0;
        }
        int n2 = b3.x;
        int n3 = b2.x;
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
        return this.a((b)object);
    }
}
