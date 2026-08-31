/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.NetworkException$1;
import com.corrodinggames.rts.gameFramework.utility.ah;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ag
extends af {
    static HashMap a = new HashMap();

    public static void h(String string) {
        GlobalState.e("Zip: " + string);
    }


    public boolean a(String string) {
        if (string.endsWith(".rwmod") || string.endsWith(".rwmod/") || string.endsWith(".rwmod\\")) {
            return true;
        }
        ah ah2 = ag.d(string, true);
        if (ah2 == null) {
            return false;
        }
        return ah2.c(ag.l(string));
    }


    public String f(String string) {
        return string;
    }

    public static boolean i(String string) {
        return string.contains(".rwmod/") || string.contains(".rwmod\\") || string.endsWith(".rwmod");
    }


    public boolean d(String string) {
        if (string.endsWith(".rwmod") || string.endsWith(".rwmod/") || string.endsWith(".rwmod\\")) {
            return true;
        }
        ah ah2 = ag.d(string, true);
        if (ah2 == null) {
            return false;
        }
        return ah2.d(ag.l(string));
    }


    public boolean e(String string) {
        ag.h("createDirectory not supported in zip files: " + string);
        return false;
    }


    public String[] b(String string) {
        ah ah2 = ag.d(string, true);
        if (ah2 == null) {
            return null;
        }
        return ah2.e(ag.l(string));
    }


    public long a(String string, boolean bl) {
        ah ah2 = ag.d(string, bl);
        if (ah2 == null) {
            return -1L;
        }
        String string2 = ag.l(string);
        long l2 = ah2.h(string2);
        return l2;
    }


    public AssetStream b(String string, boolean bl) {
        ah ah2 = ag.d(string, bl);
        if (ah2 == null) {
            return null;
        }
        String string2 = ag.l(string);
        AssetStream j2 = ah2.i(string2);
        return j2;
    }


    public long g(String string) {
        String string2 = ag.j(string);
        af af2 = ae.b(string2);
        if (af2 != null) {
            return af2.g(string2);
        }
        File file = new File(string2);
        return file.lastModified();
    }


    public OutputStream c(String string, boolean bl) {
        ag.h("writableOutputSteam not supported in zip files: " + string);
        return null;
    }


    public boolean a(String string, String string2) {
        ag.h("Rename not supported in zip files: " + string + " to " + string2);
        return false;
    }


    public boolean c(String string) {
        ag.h("Delete not supported in zip files: " + string);
        return false;
    }

    public static String j(String string) {
        int n2 = string.indexOf(".rwmod/");
        int n3 = string.indexOf(".rwmod\\");
        if (n3 != -1 && (n3 < n2 || n2 == -1)) {
            n2 = n3;
        }
        if (n2 == -1 && string.endsWith(".rwmod")) {
            n2 = string.length() - ".rwmod".length();
        }
        if (n2 == -1) {
            throw new RuntimeException("Could not find .rwmod in path: " + string);
        }
        return string.substring(0, n2 + ".rwmod".length());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ah d(String string, boolean bl) {
        String string2 = ag.j(string);
        HashMap hashMap = a;
        synchronized (hashMap) {
            ah ah2 = (ah)a.get(string2);
            if (ah2 == null) {
                String string3 = bl ? string2 : com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(string2);
                try {
                    ah2 = new ah(string2, string3);
                }
                catch (IOException iOException) {
                    ag.h("Failed to open source zip: '" + string3 + "'");
                    iOException.printStackTrace();
                    String string4 = "Failed to open zip, " + iOException.getMessage();
                    String string5 = "";
                    if (com.corrodinggames.rts.gameFramework.filesystem.FileLoader.g(string2)) {
                        ag.h("isDirectory: " + string2);
                        string4 = "Failed to open .rwmod file (Appears to be FileLoader directory!). Please remove .rwmod from any folder names.";
                    }
                    com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(string4 + string5);
                    return null;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    ag.h("Failed to open source zip: '" + string3 + "'");
                    illegalArgumentException.printStackTrace();
                    com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b("Failed to open zip, " + illegalArgumentException.getMessage());
                    return null;
                }
                a.put(string2, ah2);
            }
            return ah2;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void e(String string, boolean bl) {
        String string2 = ag.j(string);
        HashMap hashMap = a;
        synchronized (hashMap) {
            ah ah2 = (ah)a.remove(string2);
            if (ah2 != null) {
                GlobalState.e("Closing zip file: " + string2);
                ag$1 ag$1 = new ag$1(string2, ah2);
                new Thread(ag$1).start();
            }
        }
    }

    public void k(String string) {
        ag.e(string, false);
    }


    public void a() {
    }

    public static String l(String string) {
        String string2 = ag.j(string);
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
            String[] stringArray = GameUtils.c(string3, '/');
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
                ag.h("getPathInZip: Backtracking attempt out of zip: " + string3);
            }
            string3 = GameUtils.a((CharSequence)"/", arrayList);
        }
        return string3;
    }
}
