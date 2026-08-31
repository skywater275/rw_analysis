/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  com.corrodinggames.rts.appFramework.android.AndroidSAF
 */
package com.corrodinggames.rts.gameFramework.utility.a;

import android.net.Uri;
import com.corrodinggames.rts.appFramework.android.AndroidSAF;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.a.b;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class a
extends af {
    static AndroidSAF a = AndroidSAF.getInstance();
    static HashMap b = new HashMap();
    public static int c = 1;

    public static void h(String string) {
        l.e("Saf: " + string);
    }

    public static void i(String string) {
    }

    public static void j(String string) {
        l.e("Saf: " + string);
    }

    public static void k(String string) {
    }

    public static boolean l(String string) {
        return string.contains(".[saflink]/") || string.contains(".[saflink]\\") || string.endsWith(".[saflink]");
    }

    public static String m(String string) {
        int n2 = string.indexOf(".[saflink]/");
        int n3 = string.indexOf(".[saflink]\\");
        if (n3 != -1 && (n3 < n2 || n2 == -1)) {
            n2 = n3;
        }
        if (n2 == -1 && string.endsWith(".[saflink]")) {
            n2 = string.length() - ".[saflink]".length();
        }
        if (n2 == -1) {
            throw new RuntimeException("Could not find saf link in path: " + string);
        }
        return string.substring(0, n2 + ".[saflink]".length());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static b d(String string, boolean bl) {
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.m(string);
        HashMap hashMap = b;
        synchronized (hashMap) {
            b b2 = (b)b.get(string2);
            if (b2 == null) {
                com.corrodinggames.rts.gameFramework.e.a.b("Folder link no longer open");
                return null;
            }
            return b2;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a() {
        HashMap hashMap = b;
        synchronized (hashMap) {
            for (b b2 : b.values()) {
                b2.a();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String a(Uri uri, boolean bl) {
        l.e("createSAFLink: " + uri);
        HashMap hashMap = b;
        synchronized (hashMap) {
            String string = "/saf-virtual/" + c + ".[saflink]";
            ++c;
            b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.a(uri, bl, string);
            if (b2 == null) {
                return null;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static b a(Uri uri, boolean bl, String string) {
        l.e("createSAFLink: " + uri + " to " + string);
        HashMap hashMap = b;
        synchronized (hashMap) {
            b b2 = (b)b.get(string);
            if (b2 != null) {
                l.b("createSAFLink: Already open");
            }
            b b3 = new b(uri, bl);
            try {
                b3.b();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                com.corrodinggames.rts.gameFramework.e.a.b("Failed to list files: " + iOException.getMessage());
                return null;
            }
            b.put(string, b3);
            return b3;
        }
    }

    public static String n(String string) {
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.m(string);
        String string3 = string.substring(string2.length());
        if (string3.startsWith("/") || string3.startsWith("\\")) {
            string3 = string3.substring(1);
        }
        if (string3.startsWith("/") || string3.startsWith("\\")) {
            string3 = string3.substring(1);
        }
        if (string3.contains("\\")) {
            string3 = string3.replace("\\", "/");
        }
        if (string3.contains("..")) {
            String[] stringArray = f.c(string3, '/');
            ArrayList<String> arrayList = new ArrayList<String>(stringArray.length);
            int n2 = 0;
            for (int i = stringArray.length - 1; i >= 0; --i) {
                if (stringArray[i].equals("..")) {
                    ++n2;
                    continue;
                }
                if (n2 > 0) {
                    --n2;
                    continue;
                }
                arrayList.add(0, stringArray[i]);
            }
            if (n2 != 0) {
                com.corrodinggames.rts.gameFramework.utility.a.a.j("getPathInZip: Backtracking attempt out of zip: " + string3);
            }
            string3 = f.a((CharSequence)"/", arrayList);
        }
        return string3;
    }

    @Override
    public boolean a(String string) {
        if (string.endsWith(".[saflink]") || string.endsWith(".[saflink]/") || string.endsWith(".[saflink]\\")) {
            return true;
        }
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.h("fileExists failed to open for: " + string);
            return false;
        }
        try {
            return b2.a(com.corrodinggames.rts.gameFramework.utility.a.a.n(string));
        }
        catch (IOException iOException) {
            com.corrodinggames.rts.gameFramework.utility.a.a.i("fileExists failed for: " + string);
            return false;
        }
    }

    @Override
    public String f(String string) {
        if (string.endsWith(".[saflink]") || string.endsWith(".[saflink]/") || string.endsWith(".[saflink]\\")) {
            return string;
        }
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.j("convertAbstractPathForDebug failed for: " + string);
            return string;
        }
        return b2.c + "/" + com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
    }

    @Override
    public boolean d(String string) {
        if (string.endsWith(".[saflink]") || string.endsWith(".[saflink]/") || string.endsWith(".[saflink]\\")) {
            return true;
        }
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            return false;
        }
        try {
            return b2.h(com.corrodinggames.rts.gameFramework.utility.a.a.n(string));
        }
        catch (IOException iOException) {
            com.corrodinggames.rts.gameFramework.utility.a.a.i("isDirectory failed for: " + string);
            return false;
        }
    }

    @Override
    public boolean e(String string) {
        if (string.endsWith(".[saflink]") || string.endsWith(".[saflink]/") || string.endsWith(".[saflink]\\")) {
            com.corrodinggames.rts.gameFramework.utility.a.a.i("createDirectory on root path: " + string);
            return false;
        }
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.j("createDirectory failed for: " + string);
            return false;
        }
        try {
            return b2.j(com.corrodinggames.rts.gameFramework.utility.a.a.n(string));
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return false;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            return false;
        }
    }

    @Override
    public String[] b(String string) {
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            return null;
        }
        try {
            return b2.g(com.corrodinggames.rts.gameFramework.utility.a.a.n(string));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            com.corrodinggames.rts.gameFramework.e.a.b("Failed to open saf, " + iOException.getMessage());
            return null;
        }
    }

    @Override
    public long a(String string, boolean bl) {
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, bl);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.j("saf==null: for '" + string + "'");
            return -1L;
        }
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
        long l2 = b2.d(string2);
        return l2;
    }

    @Override
    public j b(String string, boolean bl) {
        j j2;
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, bl);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.j("openAssetSteam: saf==null: for '" + string + "'");
            return null;
        }
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
        try {
            j2 = b2.b(string2);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            com.corrodinggames.rts.gameFramework.utility.a.a.j("Error opening: '" + string2 + "' in: '" + string + "'");
            return null;
        }
        if (j2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.k("openAssetSteam: Failed to find: '" + string2 + "' in: '" + string + "'");
        }
        return j2;
    }

    @Override
    public long g(String string) {
        long l2;
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.h("saf==null: for '" + string + "'");
            return 0L;
        }
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
        try {
            l2 = b2.c(string2);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return 0L;
        }
        return l2;
    }

    @Override
    public OutputStream c(String string, boolean bl) {
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            return null;
        }
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
        OutputStream outputStream = b2.a(string2, bl);
        if (outputStream == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.j("Failed to find: '" + string2 + "' in: '" + string + "'");
        }
        return outputStream;
    }

    @Override
    public boolean a(String string, String string2) {
        com.corrodinggames.rts.gameFramework.utility.a.a.h("Rename: " + string + " to " + string2);
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            return false;
        }
        String string3 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
        String string4 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string2);
        com.corrodinggames.rts.gameFramework.utility.a.a.i("Relative path: " + string3 + " to " + string4);
        return b2.a(string3, string4);
    }

    @Override
    public boolean c(String string) {
        com.corrodinggames.rts.gameFramework.utility.a.a.h("deleteFile: " + string);
        b b2 = com.corrodinggames.rts.gameFramework.utility.a.a.d(string, true);
        if (b2 == null) {
            com.corrodinggames.rts.gameFramework.utility.a.a.j("saf==null: for deleteFile: '" + string + "'");
            return false;
        }
        String string2 = com.corrodinggames.rts.gameFramework.utility.a.a.n(string);
        return b2.e(string2);
    }
}
