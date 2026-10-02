/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 */
package com.corrodinggames.rts.gameFramework.e;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class c {
    public String a = "FileLoader: ";
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;
    String e;
    String f;

    public String a() {
        String string = this.e;
        this.e = null;
        return string;
    }

    public void a(String string) {
        this.e = string;
    }

    public String a(String string, String string2) {
        File file = new File(string);
        File[] fileArray = file.listFiles();
        if (fileArray == null) {
            l.e(this.a + "findFileExtension('" + string + "','" + string2 + "'): path is not a folder");
            return null;
        }
        for (File file2 : fileArray) {
            String string3 = file2.getName();
            if (string3.contains(".")) {
                string3 = string3.substring(0, string3.lastIndexOf(46));
            }
            if (!string3.equals(string2)) continue;
            return string + "/" + file2.getName();
        }
        l.e(this.a + "Could not find file with path: " + string + " file:" + string2);
        return null;
    }

    public boolean b(String string) {
        if (l.aU) {
            return false;
        }
        if (string.startsWith("/")) {
            return false;
        }
        return !string.startsWith("/SD/");
    }

    public boolean c(String string) {
        if (string.startsWith("/") || string.startsWith("\\")) {
            return true;
        }
        if (l.aU) {
            if (string.startsWith("mods")) {
                return true;
            }
            if (l.aZ && string.startsWith("converted-sounds")) {
                return true;
            }
        }
        return (string = string.split("\\\\")[0]).endsWith(":");
    }

    public String d(String string) {
        String string2;
        String string3;
        int n2;
        boolean bl;
        String string4;
        String[] stringArray;
        l l2 = l.B();
        if (string.contains("MOD|")) {
            stringArray = string.split("/");
            if (stringArray.length >= 2) {
                string4 = stringArray[stringArray.length - 1];
                bl = false;
                for (n2 = stringArray.length - 2; n2 >= 0; --n2) {
                    string3 = stringArray[n2];
                    if (string3.startsWith("MOD|")) {
                        string2 = string3.substring("MOD|".length());
                        b b2 = l2.bZ.c(string2);
                        if (b2 == null) {
                            l.e(this.a + "Failed to find mod with hash:" + string2);
                        } else {
                            string4 = com.corrodinggames.rts.gameFramework.f.b(b2.g(), string4);
                            l.e(this.a + "Path changed to mod path:" + string4);
                            bl = true;
                            break;
                        }
                    }
                    string4 = string3 + File.separator + string4;
                }
                if (bl) {
                    string = string4;
                }
            }
            if (string.contains("MOD|")) {
                l.e(this.a + "Path still contains prefix: " + string);
            }
        }
        if (string.contains("NEW_PATH|") && (stringArray = string.split("/")).length >= 2) {
            string4 = stringArray[stringArray.length - 1];
            bl = false;
            for (n2 = stringArray.length - 2; n2 >= 0; --n2) {
                string3 = stringArray[n2];
                if (string3.startsWith("NEW_PATH|") && (string2 = string3.substring("NEW_PATH|".length())).equals("maps2")) {
                    string4 = "/SD/rustedWarfare/maps" + File.separator + string4;
                    l.e(this.a + "Path changed to maps2 path:" + string4);
                    bl = true;
                    break;
                }
                string4 = string3 + File.separator + string4;
            }
            if (bl) {
                string = string4;
            }
        }
        return string;
    }

    public String e(String string) {
        boolean bl = false;
        if (string == null) {
            return "<null>";
        }
        af af2 = ae.b(string = this.f(string));
        if (af2 != null) {
            String string2 = af2.f(string);
            return string2;
        }
        return string;
    }

    public String f(String string) {
        string = this.d(string);
        if (l.aU) {
            if (string.startsWith("/SD/rusted_warfare_maps")) {
                string = "/SD/mods/maps" + string.substring("/SD/rusted_warfare_maps".length());
                l.e(this.a + "convertAbstractPath: Changing to:" + string);
            }
            if (string.startsWith("/SD/rustedWarfare/maps")) {
                string = "/SD/mods/maps" + string.substring("/SD/rustedWarfare/maps".length());
                l.e(this.a + "convertAbstractPath2: Changing to:" + string);
            }
            if (string.startsWith("/SD/") || string.startsWith("\\SD\\")) {
                String string2;
                String string3 = string.substring("/SD/".length());
                if (string3.startsWith(string2 = "rustedWarfare/")) {
                    string3 = string3.substring(string2.length());
                }
                string3 = this.b() + string3;
                return string3;
            }
            if (this.c(string)) {
                return string;
            }
            return "assets/" + string;
        }
        if (string.startsWith("/SD/")) {
            String string4;
            String string5 = string.substring("/SD/".length());
            if (string5.startsWith(string4 = "rustedWarfare/")) {
                string5 = string5.substring(string4.length());
            }
            return this.b() + string5;
        }
        return string;
    }

    private String f() {
        if (this.f == null) {
            this.f = Environment.getExternalStorageDirectory() + "";
        }
        return this.f;
    }

    public boolean a(String string, boolean bl) {
        String string2 = this.f(string);
        af af2 = bl ? ae.b(string2) : ae.a(string2);
        if (af2 != null) {
            return af2.d(string2);
        }
        if (this.b(string)) {
            if (this.d) {
                return false;
            }
            if (!l.B().bK.a(string2)) {
                l.e(this.a + "isDirectory: asset file doesn't exist:" + string2);
                return false;
            }
            String string3 = com.corrodinggames.rts.gameFramework.f.k(string);
            return !string3.contains(".");
        }
        File file = new File(string2);
        if (!file.exists()) {
            l.e(this.a + "isDirectory: file doesn't exist:" + string2);
            return false;
        }
        return file.isDirectory();
    }

    public boolean g(String string) {
        String string2 = this.f(string);
        af af2 = ae.a(string2);
        if (af2 != null) {
            boolean bl = af2.a(string2);
            if (this.c) {
                l.e("fileExists: " + bl + " with reader: " + af2 + " convertedDir:" + string2);
            }
            return bl;
        }
        if (this.b(string)) {
            if (this.d) {
                if (this.c) {
                    l.e("fileExists: false with disableAssets");
                }
                return false;
            }
            boolean bl = l.B().bK.a(string2);
            if (this.c) {
                l.e("fileExists: " + bl + " with abstractPathAsset convertedDir:" + string2);
            }
            return bl;
        }
        File file = new File(string2);
        if (file == null || !file.exists()) {
            if (this.c) {
                l.e("fileExists: false with normal file convertedDir:" + string2);
            }
            return false;
        }
        return true;
    }

    public String[] b(String string, boolean bl) {
        try {
            Serializable serializable;
            String[] stringArray;
            String string2 = this.f(string);
            af af2 = ae.a(string2);
            if (af2 != null) {
                stringArray = af2.b(string2);
            } else if (this.b(string)) {
                if (this.d) {
                    return null;
                }
                stringArray = l.B().bK.b(string2);
            } else {
                serializable = new File(string2);
                if (serializable == null || !((File)serializable).exists()) {
                    String string3 = "listDir: path doesn't exist:" + string2;
                    l.b(string3);
                    com.corrodinggames.rts.gameFramework.e.a.b(string3);
                    return null;
                }
                stringArray = ((File)serializable).list();
                if (stringArray == null) {
                    if (serializable != null && !((File)serializable).isDirectory()) {
                        com.corrodinggames.rts.gameFramework.e.a.b("path is not a directory, .rwmod or .zip");
                    }
                    return null;
                }
            }
            if (stringArray == null) {
                l.e(this.a + "listDir baseList==null:" + string + " (non folder?)");
                return null;
            }
            serializable = new ArrayList();
            if (bl) {
                for (String string4 : stringArray) {
                    if (!string4.toLowerCase(Locale.ENGLISH).endsWith(".tmx")) continue;
                    ((ArrayList)serializable).add(string4);
                }
            } else {
                for (String string5 : stringArray) {
                    ((ArrayList)serializable).add(string5);
                }
            }
            Collections.sort(serializable);
            return ((ArrayList)serializable).toArray(new String[0]);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.e.a.b(outOfMemoryError.getMessage());
            return null;
        }
    }

    public File h(String string) {
        File file;
        if (string.contains("\\")) {
            string = string.replace('\\', '/');
        }
        if ((file = new File(string)).exists()) {
            return file;
        }
        File file2 = file.getParentFile();
        if (!(file2 != null && file2.isDirectory() || (file2 = this.h(file2.getAbsolutePath())) != null && file2.isDirectory())) {
            l.e(this.a + "createFileCaseInsensitive: did not find parent for: " + string);
            return null;
        }
        File[] fileArray = file2.listFiles();
        if (fileArray == null) {
            l.e(this.a + "createFileCaseInsensitive: Failed to list files for: " + string + " in " + file2);
            return null;
        }
        for (File file3 : fileArray) {
            if (!file3.getName().equalsIgnoreCase(file.getName())) continue;
            return file3;
        }
        return null;
    }

    public j i(String string) {
        InputStream inputStream;
        if (string.startsWith("assets/") || string.startsWith("assets\\")) {
            string = string.substring("assets/".length());
        }
        String string2 = string;
        String string3 = "assets/" + string;
        Context context = com.corrodinggames.rts.appFramework.c.a();
        AssetManager assetManager = context.d();
        if (l.aY) {
            // empty if block
        }
        try {
            inputStream = assetManager.a(string2);
        }
        catch (IOException iOException) {
            l.e(this.a + "Could not find asset:" + string3);
            return null;
        }
        try {
            return new j(inputStream, string3, string2);
        }
        catch (FileNotFoundException fileNotFoundException) {
            return null;
        }
    }

    public j j(String string) {
        j j2;
        String string2 = this.f(string);
        af af2 = ae.a(string2);
        if (af2 != null && !string2.endsWith(".rwmod")) {
            return af2.b(string2, true);
        }
        if (string.startsWith("/SD/") || string.startsWith("\\SD\\")) {
            String string3;
            String string4 = string = string.substring("/SD/".length());
            if (string4.startsWith(string3 = "rustedWarfare/")) {
                string4 = string4.substring(string3.length());
            }
            string4 = this.b() + string4;
            if (this.b) {
                l.e(this.a + "openAssetSteam converted:" + string + " to: " + string4);
            }
            try {
                File file = this.h(string4);
                if (file == null) {
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                j2 = new j(fileInputStream, file.getAbsolutePath());
            }
            catch (FileNotFoundException fileNotFoundException) {
                return null;
            }
        } else if (this.c(string)) {
            try {
                File file = this.h(string);
                if (file == null) {
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                j2 = new j(fileInputStream, file.getAbsolutePath());
            }
            catch (FileNotFoundException fileNotFoundException) {
                return null;
            }
        } else {
            j2 = this.i(string);
        }
        return j2;
    }

    public OutputStream c(String string, boolean bl) {
        af af2 = ae.a(string = this.f(string));
        if (af2 != null && !string.endsWith(".rwmod")) {
            return af2.c(string, bl);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(string, bl);
        return fileOutputStream;
    }

    public boolean k(String string) {
        String string2 = this.f(string);
        af af2 = ae.a(string2);
        if (af2 != null && !string2.endsWith(".rwmod")) {
            boolean bl = af2.e(string2);
            if (!bl) {
                l.e("Failed to create directory: " + string2 + " using reader:" + af2);
            }
            return bl;
        }
        boolean bl = new File(string2).mkdirs();
        if (!bl) {
            l.e("Failed to create directory: " + string2);
        }
        return bl;
    }

    public String b() {
        if (l.aU) {
            return "";
        }
        return this.f() + "/rustedWarfare/";
    }

    public String c() {
        if (l.at()) {
            String string = com.corrodinggames.rts.appFramework.c.a().i().getAbsolutePath();
            if (!string.endsWith("/")) {
                string = string + "/";
            }
            return string;
        }
        String string = this.b();
        if (string.equals("")) {
            return "cache/";
        }
        return string + "/cache/";
    }

    public long l(String string) {
        af af2 = ae.a(string = this.f(string));
        if (af2 != null) {
            return af2.g(string);
        }
        File file = new File(string);
        if (!file.exists()) {
            // empty if block
        }
        return file.lastModified();
    }

    public void a(File file) {
        if (l.at()) {
            // empty if block
        }
    }

    public File a(String string, String string2, boolean bl) {
        String string3 = this.b();
        String string4 = string3 + string2 + string;
        File file = new File(string4);
        if (bl) {
            File file2 = file.getParentFile();
            if (!com.corrodinggames.rts.gameFramework.e.a.i(file2.getAbsolutePath())) {
                l.e("Making missing parent dir: " + file2.getAbsolutePath());
                if (!com.corrodinggames.rts.gameFramework.e.a.l(file2.getAbsolutePath())) {
                    l.b("getRWFile: Could not create parent directory");
                }
            }
            if (l.at()) {
                // empty if block
            }
        }
        return file;
    }

    public String d() {
        return "external";
    }

    public String m(String string) {
        return this.d();
    }

    public boolean e() {
        return true;
    }

    public String n(String string) {
        if (string == null) {
            return null;
        }
        String string2 = "[INTERNAL-PATH]/";
        String string3 = "[EXTERNAL-PATH]/";
        int n2 = string.indexOf("[INTERNAL-PATH]/");
        if (n2 != -1) {
            String string4 = string.substring(0, n2) + string.substring(n2 + "[INTERNAL-PATH]/".length());
            if (string4.contains("[INTERNAL-PATH]/") || string4.contains("[EXTERNAL-PATH]/")) {
                l.e("fixPath: double tag for: " + string);
            }
            return string4;
        }
        int n3 = string.indexOf("[EXTERNAL-PATH]/");
        if (n3 != -1) {
            String string5 = string.substring(0, n3) + string.substring(n3 + "[EXTERNAL-PATH]/".length());
            if (string5.contains("[INTERNAL-PATH]/") || string5.contains("[EXTERNAL-PATH]/")) {
                l.e("fixPath: double tag for: " + string);
            }
            return string5;
        }
        return string;
    }

    public String o(String string) {
        return string;
    }

    public boolean b(File file) {
        l.e("deleteFile: " + file.getAbsolutePath());
        af af2 = ae.b(file.getAbsolutePath());
        if (af2 != null) {
            l.e("Mapped delete");
            return af2.c(file.getAbsolutePath());
        }
        l.e("Native delete");
        return file.delete();
    }

    public boolean a(File file, File file2) {
        l.e("renameFile: " + file.getAbsolutePath() + " to:" + file2.getAbsolutePath());
        af af2 = ae.b(file.getAbsolutePath());
        if (af2 != null) {
            boolean bl;
            try {
                bl = af2.a(file.getAbsolutePath(), file2.getAbsolutePath());
            }
            catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
            ae.c(file2.getAbsolutePath());
            return bl;
        }
        boolean bl = file.renameTo(file2);
        ae.c(file2.getAbsolutePath());
        return bl;
    }

    public boolean p(String string) {
        af af2 = ae.b(string = this.f(string));
        return af2 != null && af2 instanceof com.corrodinggames.rts.gameFramework.utility.a.a;
    }
}
