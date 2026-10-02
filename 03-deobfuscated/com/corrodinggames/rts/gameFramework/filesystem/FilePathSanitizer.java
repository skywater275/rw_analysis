/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.filesystem;

import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.filesystem.InputStreamHolder;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ag;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FilePathSanitizer {
    public static boolean a = true;

    public static final String a(char c) {
        return String.valueOf((int)c);
    }

    public static String a(String string, char c) {
        if (GameUtils.b(string, c)) {
            string = GameUtils.a(string, String.valueOf(c), "%" + FilePathSanitizer.a(c));
        }
        return string;
    }

    public static String a(String string) {
        if (string == null) {
            return "null";
        }
        string = GameUtils.a(string, "%", "%%");
        string = FilePathSanitizer.a(string, '/');
        string = FilePathSanitizer.a(string, '\\');
        string = FilePathSanitizer.a(string, ':');
        string = FilePathSanitizer.a(string, '\"');
        string = FilePathSanitizer.a(string, '\'');
        string = FilePathSanitizer.a(string, '|');
        string = FilePathSanitizer.a(string, '?');
        string = FilePathSanitizer.a(string, '*');
        string = FilePathSanitizer.a(string, '<');
        string = FilePathSanitizer.a(string, '>');
        if ((string = FilePathSanitizer.a(string, '\u0000')).contains("/")) {
            throw new IllegalArgumentException();
        }
        if (string.contains("\\")) {
            throw new IllegalArgumentException();
        }
        return string;
    }

    public static String a(String string, String string2, boolean bl) {
        String string3 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.getString3();
        String string4 = string3 + FilePathSanitizer.a(string) + ".cachedata";
        if (bl) {
            File file = new File(string4);
            if (!file.isDirectory() && !file.mkdirs()) {
                GlobalState.e("Failed to create folder for:" + file.getAbsolutePath());
            }
        }
        return string4 + "/" + FilePathSanitizer.a(string2);
    }

    public static boolean a(String string, String string2, String string3) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(string3.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
        return FilePathSanitizer.a(string, string2, byteArrayInputStream);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean a(String string, String string2, InputStream inputStream) {
        try {
            String string3 = FilePathSanitizer.a(string, string2, true);
            File file = new File(string3);
            File file2 = new File(string3 + ".tmp");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file2);){
                GameUtils.a(inputStream, fileOutputStream);
            }
            boolean bl = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(file2, file);
            if (!bl) {
                GlobalState.b("AddToCache: Failed to rename to final file: " + string3);
                return false;
            }
            if (a) {
                GlobalState.e("Wrote cache file at: " + file.getAbsolutePath());
            }
            return true;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
    }

    public static FileInputStream a(String string, String string2) {
        String string3 = FilePathSanitizer.a(string, string2, false);
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
        FileInputStream fileInputStream = FilePathSanitizer.a(string, string2);
        if (fileInputStream == null) {
            return null;
        }
        return GameUtils.a(fileInputStream);
    }

    public static void c(String string, String string2) {
        boolean bl;
        String string3 = FilePathSanitizer.a(string, string2, false);
        File file = new File(string3);
        if (file.exists() && !(bl = file.delete())) {
            GlobalState.isKeyJustPressed("Failed to delete: " + string3);
        }
    }

    private static InputStreamHolder b(String string, String string2, String string3) {
        String[] stringArray;
        String string4 = string2 + ".data";
        String string5 = string2 + ".meta";
        String string6 = FilePathSanitizer.b(string, string5);
        if (string6 != null && (stringArray = al.b(string6, ":")) != null) {
            Long l2 = GameUtils.parseLong(stringArray[0]);
            long l3 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.m(string2);
            String string7 = stringArray[1];
            if (l2 == null) {
                if (a) {
                    GlobalState.e("openAssetCached: Bad meta data for: " + string2);
                }
            } else if (l2 != l3) {
                if (a) {
                    GlobalState.e("openAssetCached: Stale timestamp for: " + string2 + " (" + l2 + "!=" + l3 + ")");
                }
            } else {
                if (string7.startsWith("null")) {
                    if (a) {
                        GlobalState.e("openAssetCached: Cache hit (null-type) for: " + string2 + " (" + l2 + "!=" + l3 + ")");
                    }
                    return new InputStreamHolder(null);
                }
                if (!string7.startsWith(string3)) {
                    if (a) {
                        GlobalState.e("openAssetCached: Unsupported type " + string7 + " for: " + string2 + " expected: " + string3);
                    }
                    return new InputStreamHolder(null);
                }
                FileInputStream fileInputStream = FilePathSanitizer.a(string, string4);
                if (fileInputStream != null) {
                    if (a) {
                        GlobalState.e("openAssetCached: Cache hit for: " + string2);
                    }
                    return new InputStreamHolder(fileInputStream);
                }
                if (a) {
                    GlobalState.e("openAssetCached: meta file but not data for: " + string2);
                }
            }
        }
        return null;
    }

    public static String[] d(String string, String string2) {
        long l2;
        String string3;
        if (!ag.i(string2)) {
            return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.h(string2);
        }
        String string4 = string2 + ".data";
        String string5 = string2 + ".meta";
        String string6 = "list";
        InputStreamHolder h2 = FilePathSanitizer.b(string, string2, "list");
        if (h2 != null) {
            if (h2.a == null) {
                return null;
            }
            String string7 = GameUtils.a(h2.a);
            h2.a();
            return al.e(string7);
        }
        String[] stringArray = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.h(string2);
        if (stringArray != null) {
            if (a) {
                GlobalState.e("listDirCached: Listing count: " + stringArray.length);
            }
            string3 = "list";
            l2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.m(string2);
            if (l2 == 0L) {
                if (a) {
                    GlobalState.e("openAssetCached: Got 0 timestamp for: " + string2 + " cannot cache");
                }
                return stringArray;
            }
            FilePathSanitizer.a(string, string4, al.a(stringArray));
        } else {
            if (a) {
                GlobalState.e("listDirCached: Null");
            }
            string3 = "null";
            l2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.m(string2);
        }
        String string8 = l2 + ":" + string3;
        FilePathSanitizer.a(string, string5, string8);
        return stringArray;
    }

    public static InputStream e(String string, String string2) {
        long l2;
        String string3;
        AssetStream j2;
        String string4 = string2 + ".data";
        String string5 = string2 + ".meta";
        String string6 = "data";
        InputStreamHolder h2 = FilePathSanitizer.b(string, string2, "data");
        if (h2 != null) {
            return h2.a;
        }
        if (a) {
            GlobalState.e("openAssetCached: Cache miss for: " + string2);
        }
        if ((j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string2)) != null) {
            if (a) {
                GlobalState.e("openAssetCached: Reading: " + string2);
            }
            string3 = "data";
            l2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.m(string2);
            if (l2 == 0L) {
                if (a) {
                    GlobalState.e("openAssetCached: Got 0 timestamp for: " + string2 + " cannot cache");
                }
                return j2;
            }
            if (!FilePathSanitizer.a(string, string4, j2)) {
                // empty if block
            }
        } else {
            if (a) {
                GlobalState.e("openAssetCached: Got null for: " + string2);
            }
            string3 = "null";
            l2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.m(string2);
        }
        String string7 = l2 + ":" + string3;
        FilePathSanitizer.a(string, string5, string7);
        if (j2 == null) {
            return null;
        }
        try {
            ((InputStream)j2).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        FileInputStream fileInputStream = FilePathSanitizer.a(string, string4);
        if (fileInputStream == null) {
            GlobalState.b("openAssetCached: Error. Failed to reopen cache: " + string2);
            return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(string2);
        }
        return fileInputStream;
    }

    public static boolean f(String string, String string2) {
        InputStream inputStream = FilePathSanitizer.e(string, string2);
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
