/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.filesystem;

import android.content.Context;
import android.os.Build;
import com.corrodinggames.rts.gameFramework.filesystem.FileAccessFlags;
import com.corrodinggames.rts.gameFramework.filesystem.StorageBackend;
import com.corrodinggames.rts.gameFramework.filesystem.PathStorage;
import com.corrodinggames.rts.gameFramework.filesystem.DualStorage;
import com.corrodinggames.rts.gameFramework.filesystem.NullStorage;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class FileLoader {
    public static final StorageBackend a;
    public static StorageBackend b;
    public static Boolean c;
    public static String d;
    public static String e;

    public static String reset() {
        return b.a();
    }

    public static boolean reset(String string) {
        return b.b(string);
    }

    public static String getString2(String string) {
        return b.f(string);
    }

    public static boolean isEnabled3(String string) {
        return b.a(string, false);
    }





    public static AssetStream k(String string) {
        return b.i(string);
    }

    public static AssetStream getj2(String string) {
        return b.j(string);
    }



    public static boolean g(String string) {  // 02b e/a.java L175: b.a(String,true) isDirectory (ag.java 链)
        return b.a(string, true);
    }


    public static boolean a(String string) {  // 02b e/a.java L54: b.p(String) (ModInfo 链)
        return b.p(string);
    }

    public static boolean f(String string) {  // 02b e/a.java L171: b.a(String,false) (ModInfo 链)
        return b.a(string, false);
    }
    public static boolean l(String string) {
        return b.k(string);
    }

    public static String d() {  // 02b e/a.java L215-217: d() = b.b() (SD 卡路径)
        return b.b();
    }

    public static String e(String string) {  // 02b e/a.java L167-169: e(String) = b.f(String)
        return b.f(string);
    }

    public static String[] a(String string, boolean bl) {  // 02b e/a.java L183-185: a(String,boolean) = b.b(String,boolean)
        return b.b(string, bl);
    }

    public static String getString3() {
        return b.c();
    }

    public static long m(String string) {
        return b.l(string);
    }

    public static String d(String string) {  // 02b e/a.java L163-165
        return b.e(string);
    }

    public static com.corrodinggames.rts.gameFramework.utility.AssetStream a(File file) {  // 02b e/a.java L195-197
        return b.j(file.getAbsolutePath());
    }

    public static String n(String string) {
        return b.m(string);
    }

    public static boolean isEnabled4() {
        return b.e();
    }

    public static String o(String string) {
        return b.n(string);
    }

    public static String p(String string) {
        return b.o(string);
    }

    public static void reset(File file) {
        b.a(file);
    }

    static {
        b = a = new StorageBackend();
    }



    protected static String a() {
        android.content.Context context = com.corrodinggames.rts.appFramework.c.a();
        java.io.File file = context.b((String) null);
        if (file != null) {
            return file.getAbsolutePath();
        }
        com.corrodinggames.rts.gameFramework.GlobalState.b("Failed to get an internal path.");
        return null;
    }

    public static void b() {
        e = null;
        if (GlobalState.at()) {
            if (Build.VERSION.SDK_INT < 19) {
                e = "Android version too old for new file system support";
                GlobalState.e("FileLoader: SDK too old, not changing FileLoader");
                return;
            }
            int n2 = GlobalState.B().bQ.storageType;
            GlobalState.e("FileLoader: storageBehaviour:" + n2);
            StorageBackend c2 = a(n2);
            GlobalState.e("Using file loader: " + c2.d());
            b = c2;
        }
    }



    public static FileAccessFlags a(boolean bl) {
        FileAccessFlags b2 = new FileAccessFlags();
        if (!GlobalState.at()) {
            b2.b = false;
            b2.c = true;
            return b2;
        }
        if (Build.VERSION.SDK_INT < 19) {
            b2.b = false;
            b2.c = true;
            return b2;
        }
        b2.b = true;
        b2.a = false;
        if (d != null) {
            b2.a = true;
        }
        if (c != null && !c.booleanValue()) {
            b2.c = true;
            b2.b = false;
            b2.a = false;
        }
        if (Build.VERSION.SDK_INT <= 28 && c == null) {
            GlobalState.b("FileLoader using direct external access due to sdk: " + Build.VERSION.SDK_INT);
            b2.c = true;
            b2.b = false;
            b2.a = false;
        }
        return b2;
    }



    public static StorageBackend a(int n2) {
        StorageBackend c2;
        if (!GlobalState.at()) {
            return new StorageBackend();
        }
        if (Build.VERSION.SDK_INT < 19) {
            GlobalState.e("FileLoader: SDK too old, not changing FileLoader");
            return new StorageBackend();
        }
        String string = a();
        PathStorage d2 = null;
        if (string == null) {
            e = "Failed to get internal app path (is it unmounted?).";
            n2 = 3;
        } else {
            d2 = new PathStorage(string, "internal");
            d2.i = "Internal: ";
        }
        FileAccessFlags b2 = a(false);
        if (!b2.a) {
            if (!b2.c) {
                GlobalState.b("Not using direct external backend: As direct reads will cause problems");
                c2 = null;
                n2 = 0;
            } else {
                GlobalState.b("FileLoader using direct external file access! SDK:" + Build.VERSION.SDK_INT);
                c2 = new StorageBackend();
            }
        } else {
            GlobalState.e("FileLoader using overriddenExternalPath:" + d);
            c2 = new PathStorage(d, "external");
        }
        NullStorage f2 = new NullStorage();
        if (n2 != 3 && d2 == null) {
            GlobalState.b("No available file backends!!");
            return f2;
        }
        DualStorage e2 = n2 == 1 ? new DualStorage(d2, "[INTERNAL-PATH]/", c2, "[EXTERNAL-PATH]/") : (n2 == 2 ? new DualStorage(c2, "[EXTERNAL-PATH]/", d2, "[INTERNAL-PATH]/") : (n2 == 3 ? new DualStorage(c2, "[EXTERNAL-PATH]/", f2, "[NULL-PATH]/") : new DualStorage(d2, "[INTERNAL-PATH]/", f2, "[NULL-PATH]/")));
        e2.h.d = true;
        return e2;
    }



    public static void b(String string) {
        b.a(string);
    }



    public static String a(String string, String string2) {  // 02b e/a.java: a(String,String)=b.a
        return b.a(string, string2);
    }



    public static String[] h(String string) {
        return b.b(string, false);
    }















    public static File a(String string, String string2, boolean bl) {  // 02b e/a.java L227-229
        return b.a(string, string2, bl);
    }

    /* 02b e/a.java L203: 调 b.c 抛 FileNotFoundException */
    public static OutputStream a(File file, boolean bl) throws FileNotFoundException {  // 02b e/a.java L203-205
        return b.c(file.getAbsolutePath(), bl);
    }

    public static boolean i(String string) {  // 02b e/a.java L187-189
        return b.g(string);
    }

    /* 02b e/a.java L207: 调 b.c 抛 FileNotFoundException */
    public static OutputStream b(String string, boolean bl) throws FileNotFoundException {
        return b.c(string, bl);
    }







    public static boolean a(File file, File file2) {
        if (GlobalState.av() && file2.exists()) {
            file2.delete();
        }
        return file.renameTo(file2);
    }



    public static boolean b(File file, File file2) {
        return b.a(file, file2);
    }



    public static boolean b(File file) {
        return b.b(file);
    }



    /* 02b e/a.java L263: throw IOException (R8 移除 throws) */
    public static File a(Context context, String string, String string2) throws IOException {
        try {
            File file = context.i();
            File file2 = File.createTempFile(string, string2, file);
            return file2;
        }
        catch (IOException iOException) {
            try {
                File file = context.j();
                File file3 = File.createTempFile(string, string2, file);
                return file3;
            }
            catch (IOException iOException2) {
                iOException.printStackTrace();
                throw iOException2;
            }
        }
    }
}
