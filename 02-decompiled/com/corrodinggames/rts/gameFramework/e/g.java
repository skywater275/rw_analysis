/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.e;

import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.e.h;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ag;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class g {
    public static boolean a = true;

    public static final String a(char c) {
        return String.valueOf((int)c);
    }

    public static String a(String string, char c) {
        if (f.b(string, c)) {
            string = f.a(string, String.valueOf(c), "%" + g.a(c));
        }
        return string;
    }

    public static String a(String string) {
        if (string == null) {
            return "null";
        }
        string = f.a(string, "%", "%%");
        string = g.a(string, '/');
        string = g.a(string, '\\');
        string = g.a(string, ':');
        string = g.a(string, '\"');
        string = g.a(string, '\'');
        string = g.a(string, '|');
        string = g.a(string, '?');
        string = g.a(string, '*');
        string = g.a(string, '<');
        string = g.a(string, '>');
        if ((string = g.a(string, '\u0000')).contains("/")) {
            throw new IllegalArgumentException();
        }
        if (string.contains("\\")) {
            throw new IllegalArgumentException();
        }
        return string;
    }

    public static String a(String string, String string2, boolean bl) {
        boolean bl2;
        Object object;
        String string3 = com.corrodinggames.rts.gameFramework.e.a.e();
        String string4 = string3 + g.a(string) + ".cachedata";
        if (bl && !((File)(object = new File(string4))).isDirectory() && !(bl2 = ((File)object).mkdirs())) {
            l.e("Failed to create folder for:" + ((File)object).getAbsolutePath());
        }
        object = string4 + "/" + g.a(string2);
        return object;
    }

    public static boolean a(String string, String string2, String string3) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(string3.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
        return g.a(string, string2, byteArrayInputStream);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean a(String string, String string2, InputStream inputStream) {
        try {
            String string3 = g.a(string, string2, true);
            File file = new File(string3);
            File file2 = new File(string3 + ".tmp");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file2);){
                f.a(inputStream, fileOutputStream);
            }
            boolean bl = com.corrodinggames.rts.gameFramework.e.a.a(file2, file);
            if (!bl) {
                l.b("AddToCache: Failed to rename to final file: " + string3);
                return false;
            }
            if (a) {
                l.e("Wrote cache file at: " + file.getAbsolutePath());
            }
            return true;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
    }

    public static FileInputStream a(String string, String string2) {
        String string3 = g.a(string, string2, false);
        try {
            File file = new File(string3);
            if (!file.exists()) {
                return null;
            }
            long l2 = System.currentTimeMillis();
            file.setLastModified(l2);
            FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    public static String b(String string, String string2) {
        FileInputStream fileInputStream = g.a(string, string2);
        if (fileInputStream == null) {
            return null;
        }
        return f.a(fileInputStream);
    }

    public static void c(String string, String string2) {
        boolean bl;
        String string3 = g.a(string, string2, false);
        File file = new File(string3);
        if (file.exists() && !(bl = file.delete())) {
            l.g("Failed to delete: " + string3);
        }
    }

    private static h b(String string, String string2, String string3) {
        String[] stringArray;
        String string4 = string2 + ".data";
        String string5 = string2 + ".meta";
        String string6 = g.b(string, string5);
        if (string6 != null && (stringArray = al.b(string6, ":")) != null) {
            Long l2 = f.m(stringArray[0]);
            long l3 = com.corrodinggames.rts.gameFramework.e.a.m(string2);
            String string7 = stringArray[1];
            if (l2 == null) {
                if (a) {
                    l.e("openAssetCached: Bad meta data for: " + string2);
                }
            } else if (l2 != l3) {
                if (a) {
                    l.e("openAssetCached: Stale timestamp for: " + string2 + " (" + l2 + "!=" + l3 + ")");
                }
            } else {
                if (string7.startsWith("null")) {
                    if (a) {
                        l.e("openAssetCached: Cache hit (null-type) for: " + string2 + " (" + l2 + "!=" + l3 + ")");
                    }
                    return new h(null);
                }
                if (!string7.startsWith(string3)) {
                    if (a) {
                        l.e("openAssetCached: Unsupported type " + string7 + " for: " + string2 + " expected: " + string3);
                    }
                    return new h(null);
                }
                FileInputStream fileInputStream = g.a(string, string4);
                if (fileInputStream != null) {
                    if (a) {
                        l.e("openAssetCached: Cache hit for: " + string2);
                    }
                    return new h(fileInputStream);
                }
                if (a) {
                    l.e("openAssetCached: meta file but not data for: " + string2);
                }
            }
        }
        return null;
    }

    public static String[] d(String string, String string2) {
        long l2;
        String string3;
        if (!ag.i(string2)) {
            return com.corrodinggames.rts.gameFramework.e.a.h(string2);
        }
        String string4 = string2 + ".data";
        String string5 = string2 + ".meta";
        String string6 = "list";
        h h2 = g.b(string, string2, "list");
        if (h2 != null) {
            if (h2.a == null) {
                return null;
            }
            String string7 = f.a(h2.a);
            h2.a();
            return al.e(string7);
        }
        String[] stringArray = com.corrodinggames.rts.gameFramework.e.a.h(string2);
        if (stringArray != null) {
            if (a) {
                l.e("listDirCached: Listing count: " + stringArray.length);
            }
            string3 = "list";
            l2 = com.corrodinggames.rts.gameFramework.e.a.m(string2);
            if (l2 == 0L) {
                if (a) {
                    l.e("openAssetCached: Got 0 timestamp for: " + string2 + " cannot cache");
                }
                return stringArray;
            }
            g.a(string, string4, al.a(stringArray));
        } else {
            if (a) {
                l.e("listDirCached: Null");
            }
            string3 = "null";
            l2 = com.corrodinggames.rts.gameFramework.e.a.m(string2);
        }
        String string8 = l2 + ":" + string3;
        g.a(string, string5, string8);
        return stringArray;
    }

    public static InputStream e(String string, String string2) {
        long l2;
        String string3;
        j j2;
        String string4 = string2 + ".data";
        String string5 = string2 + ".meta";
        String string6 = "data";
        h h2 = g.b(string, string2, "data");
        if (h2 != null) {
            return h2.a;
        }
        if (a) {
            l.e("openAssetCached: Cache miss for: " + string2);
        }
        if ((j2 = com.corrodinggames.rts.gameFramework.e.a.k(string2)) != null) {
            if (a) {
                l.e("openAssetCached: Reading: " + string2);
            }
            string3 = "data";
            l2 = com.corrodinggames.rts.gameFramework.e.a.m(string2);
            if (l2 == 0L) {
                if (a) {
                    l.e("openAssetCached: Got 0 timestamp for: " + string2 + " cannot cache");
                }
                return j2;
            }
            if (!g.a(string, string4, j2)) {
                // empty if block
            }
        } else {
            if (a) {
                l.e("openAssetCached: Got null for: " + string2);
            }
            string3 = "null";
            l2 = com.corrodinggames.rts.gameFramework.e.a.m(string2);
        }
        String string7 = l2 + ":" + string3;
        g.a(string, string5, string7);
        if (j2 == null) {
            return null;
        }
        try {
            ((InputStream)j2).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        FileInputStream fileInputStream = g.a(string, string4);
        if (fileInputStream == null) {
            l.b("openAssetCached: Error. Failed to reopen cache: " + string2);
            return com.corrodinggames.rts.gameFramework.e.a.k(string2);
        }
        return fileInputStream;
    }

    public static boolean f(String string, String string2) {
        InputStream inputStream = g.e(string, string2);
        if (inputStream == null) {
            return false;
        }
        try {
            inputStream.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return true;
    }
}
