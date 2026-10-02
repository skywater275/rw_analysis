/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 */
package com.corrodinggames.rts.gameFramework.filesystem;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.mods.ModInfo;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
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

public class StorageBackend {
    public String a = "FileLoader: ";
    public boolean b = false;
    public boolean canWrite = false;
    public boolean canDelete = false;
    String e;
    String f;

    public boolean b(String string) {
        if (GlobalState.aU) {
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
        if (GlobalState.aU) {
            if (string.startsWith("mods")) {
                return true;
            }
            if (GlobalState.aZ && string.startsWith("converted-sounds")) {
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
        GlobalState l2 = GlobalState.B();
        if (string.contains("MOD|")) {
            stringArray = string.split("/");
            if (stringArray.length >= 2) {
                string4 = stringArray[stringArray.length - 1];
                bl = false;
                for (n2 = stringArray.length - 2; n2 >= 0; --n2) {
                    string3 = stringArray[n2];
                    if (string3.startsWith("MOD|")) {
                        string2 = string3.substring("MOD|".length());
                        com.corrodinggames.rts.gameFramework.mods.ModInfo b2 = l2.bZ.c(string2);  // 02b i.a.c(String)=ModInfo (b 为幻觉)
                        if (b2 == null) {
                            GlobalState.e(this.a + "Failed to find mod with hash:" + string2);
                        } else {
                            string4 = com.corrodinggames.rts.gameFramework.GameUtils.b(b2.g(), string4);
                            GlobalState.e(this.a + "Path changed to mod path:" + string4);
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
                GlobalState.e(this.a + "Path still contains prefix: " + string);
            }
        }
        if (string.contains("NEW_PATH|") && (stringArray = string.split("/")).length >= 2) {
            string4 = stringArray[stringArray.length - 1];
            bl = false;
            for (n2 = stringArray.length - 2; n2 >= 0; --n2) {
                string3 = stringArray[n2];
                if (string3.startsWith("NEW_PATH|") && (string2 = string3.substring("NEW_PATH|".length())).equals("maps2")) {
                    string4 = "/SD/rustedWarfare/maps" + File.separator + string4;
                    GlobalState.e(this.a + "Path changed to maps2 path:" + string4);
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
        string = this.d(string);  // 02b e/c.java f(String): this.d(var1) (canDelete 为字段误当方法)
        if (GlobalState.aU) {
            if (string.startsWith("/SD/rusted_warfare_maps")) {
                string = "/SD/mods/maps" + string.substring("/SD/rusted_warfare_maps".length());
                GlobalState.e(this.a + "convertAbstractPath: Changing to:" + string);
            }
            if (string.startsWith("/SD/rustedWarfare/maps")) {
                string = "/SD/mods/maps" + string.substring("/SD/rustedWarfare/maps".length());
                GlobalState.e(this.a + "convertAbstractPath2: Changing to:" + string);
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
            if (this.c(string)) {  // 02b e/c.java f(String): this.c(var1) (canWrite 为字段误当方法)
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

    public boolean g(String string) {
        String string2 = this.f(string);
        af af2 = ae.a(string2);
        if (af2 != null) {
            boolean bl = af2.a(string2);
            if (this.canWrite) {
                GlobalState.e("fileExists: " + bl + " with reader: " + af2 + " convertedDir:" + string2);
            }
            return bl;
        }
        if (this.b(string)) {
            if (this.canDelete) {
                if (this.canWrite) {
                    GlobalState.e("fileExists: false with disableAssets");
                }
                return false;
            }
            boolean bl = GlobalState.B().bK.a(string2);
            if (this.canWrite) {
                GlobalState.e("fileExists: " + bl + " with abstractPathAsset convertedDir:" + string2);
            }
            return bl;
        }
        File file = new File(string2);
        if (file == null || !file.exists()) {
            if (this.canWrite) {
                GlobalState.e("fileExists: false with normal file convertedDir:" + string2);
            }
            return false;
        }
        return true;
    }

    public String[] b(String string, boolean bl) {
        try {
            Object serializable;  // 02b: File/ArrayList 双变量合并 (Object 承载)
            String[] stringArray;
            String string2 = this.f(string);
            af af2 = ae.a(string2);
            if (af2 != null) {
                stringArray = af2.b(string2);
            } else if (this.b(string)) {
                if (this.canDelete) {
                    return null;
                }
                stringArray = GlobalState.B().bK.b(string2);
            } else {
                serializable = new File(string2);
                if (serializable == null || !((File)serializable).exists()) {
                    String string3 = "listDir: path doesn't exist:" + string2;
                    GlobalState.b(string3);
                    com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(string3);
                    return null;
                }
                stringArray = ((File)serializable).list();
                if (stringArray == null) {
                    if (serializable != null && !((File)serializable).isDirectory()) {
                        com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b("path is not FileLoader directory, .rwmod or .zip");
                    }
                    return null;
                }
            }
            if (stringArray == null) {
                GlobalState.e(this.a + "listDir baseList==null:" + string + " (non folder?)");
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
            Collections.sort((ArrayList) serializable);  // 02b: sort(ArrayList)
            return (String[]) ((ArrayList) serializable).toArray(new String[0]);  // raw ArrayList 泛型擦除 cast
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(outOfMemoryError.getMessage());
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
            GlobalState.e(this.a + "createFileCaseInsensitive: did not find parent for: " + string);
            return null;
        }
        File[] fileArray = file2.listFiles();
        if (fileArray == null) {
            GlobalState.e(this.a + "createFileCaseInsensitive: Failed to list files for: " + string + " in " + file2);
            return null;
        }
        for (File file3 : fileArray) {
            if (!file3.getName().equalsIgnoreCase(file.getName())) continue;
            return file3;
        }
        return null;
    }

    public AssetStream i(String string) {
        InputStream inputStream;
        if (string.startsWith("assets/") || string.startsWith("assets\\")) {
            string = string.substring("assets/".length());
        }
        String string2 = string;
        String string3 = "assets/" + string;
        Context context = com.corrodinggames.rts.appFramework.AndroidUIHelper.a();
        AssetManager assetManager = context.d();
        if (GlobalState.aY) {
            // empty if block
        }
        try {
            inputStream = assetManager.a(string2);
        }
        catch (RuntimeException iOException) {
            GlobalState.e(this.a + "Could not find asset:" + string3);
            return null;
        }
        try {
            return new AssetStream(inputStream, string3, string2);
        }
        catch (FileNotFoundException fileNotFoundException) {
            return null;
        }
    }

    public AssetStream j(String string) {
        AssetStream j2;
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
                GlobalState.e(this.a + "openAssetSteam converted:" + string + " to: " + string4);
            }
            try {
                File file = this.h(string4);
                if (file == null) {
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                j2 = new AssetStream(fileInputStream, file.getAbsolutePath());
            }
            catch (FileNotFoundException fileNotFoundException) {
                return null;
            }
        } else if (this.c(string)) {  // 02b e/c: this.c(var1)
            try {
                File file = this.h(string);
                if (file == null) {
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                j2 = new AssetStream(fileInputStream, file.getAbsolutePath());
            }
            catch (FileNotFoundException fileNotFoundException) {
                return null;
            }
        } else {
            j2 = this.i(string);
        }
        return j2;
    }

    /* 02b e/c.java 对应: FileOutputStream 抛 FileNotFoundException */
    public OutputStream c(String string, boolean bl) throws FileNotFoundException {
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
                GlobalState.e("Failed to create directory: " + string2 + " using reader:" + af2);
            }
            return bl;
        }
        boolean bl = new File(string2).mkdirs();
        if (!bl) {
            GlobalState.e("Failed to create directory: " + string2);
        }
        return bl;
    }

    public String b() {
        if (GlobalState.aU) {
            return "";
        }
        return this.f() + "/rustedWarfare/";
    }

    public String c() {
        if (GlobalState.at()) {
            String string = com.corrodinggames.rts.appFramework.AndroidUIHelper.a().i().getAbsolutePath();
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

    public String d() {
        return "external";
    }

    public String m(String string) {
        return this.d();  // 02b e/c.java m(String): return this.d() (canDelete 为字段误当方法)
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
                GlobalState.e("fixPath: double tag for: " + string);
            }
            return string4;
        }
        int n3 = string.indexOf("[EXTERNAL-PATH]/");
        if (n3 != -1) {
            String string5 = string.substring(0, n3) + string.substring(n3 + "[EXTERNAL-PATH]/".length());
            if (string5.contains("[INTERNAL-PATH]/") || string5.contains("[EXTERNAL-PATH]/")) {
                GlobalState.e("fixPath: double tag for: " + string);
            }
            return string5;
        }
        return string;
    }

    public String o(String string) {
        return string;
    }

    public boolean b(File file) {
        GlobalState.e("deleteFile: " + file.getAbsolutePath());
        af af2 = ae.b(file.getAbsolutePath());
        if (af2 != null) {
            GlobalState.e("Mapped delete");
            return af2.c(file.getAbsolutePath());
        }
        GlobalState.e("Native delete");
        return file.delete();
    }

    public boolean p(String string) {
        af af2 = ae.b(string = this.f(string));
        return af2 != null && af2 instanceof com.corrodinggames.rts.gameFramework.utility.filesystem.a;
    }

    public boolean c = false;

    public boolean d = false;

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
            GlobalState.e(this.a + "findFileExtension('" + string + "','" + string2 + "'): path is not a folder");
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
        GlobalState.e(this.a + "Could not find file with path: " + string + " file:" + string2);
        return null;
    }
    public boolean a(String string, boolean bl) {  // 02b e/c.java L228-: isDirectory (简化: 仅文件系统分支)
        String string2 = this.f(string);
        java.io.File file = new java.io.File(string2);
        return file.isDirectory();
    }


    public void a(File file) {
        if (GlobalState.at()) {
            // empty if block
        }
    }

    public File a(String string, String string2, boolean bl) {
        String string3 = this.b();
        String string4 = string3 + string2 + string;
        File file = new File(string4);
        if (bl) {
            File file2 = file.getParentFile();
            if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(file2.getAbsolutePath())) {
                GlobalState.e("Making missing parent dir: " + file2.getAbsolutePath());
                if (!com.corrodinggames.rts.gameFramework.filesystem.FileLoader.l(file2.getAbsolutePath())) {
                    GlobalState.b("getRWFile: Could not create parent directory");
                }
            }
            if (GlobalState.at()) {
                // empty if block
            }
        }
        return file;
    }

    public boolean a(File file, File file2) {
        GlobalState.e("renameFile: " + file.getAbsolutePath() + " to:" + file2.getAbsolutePath());
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
}
