/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 */
package com.corrodinggames.rts.gameFramework.utility.filesystem;

import android.content.Context;
import android.net.Uri;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.utility.filesystem.AIStrategy;
import com.corrodinggames.rts.gameFramework.utility.filesystem.c;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class b {
    Uri a;
    Uri b;
    String c;
    boolean d;
    c e;
    boolean f = false;
    int g = 1;

    public b(Uri uri, boolean bl) {
        this.a = uri;
        this.b = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.buildDocumentUriUsingTree(uri);
        this.c = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.getReadablePath(this.c(), uri);
        this.d = bl;
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("== new SafLink write:" + bl + " ==");
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("root:" + this.a);
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("rootDocument:" + this.b);
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("shownUrl:" + this.c);
        this.e = new c(this, "", this.b, true);
    }

    public void a() {
        this.f = true;
        ++this.g;
    }

    public void b() throws IOException {  // 02b utility/a/b.java L47: b() 方法 (CFR 误当构造器)
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("== testRoot ==");
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.listWithDetails(this.c(), this.b);
    }

    public Context c() {
        return com.corrodinggames.rts.appFramework.c.a();
    }

    public boolean a(String string) throws IOException {
        boolean bl = false;
        if ("mod-info.txt".equals(string)) {
            bl = true;
        }
        if (bl) {
            return com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.exists(this.c(), this.f(string));
        }
        c c2 = this.k(string);
        return c2 != null;
    }

    public AssetStream b(String string) throws IOException {
        Uri uri;
        Object object;
        boolean bl = false;
        if ("mod-info.txt".equals(string)) {
            bl = true;
        }
        if (!bl) {
            object = this.k(string);
            if (object == null) {
                return null;
            }
            uri = ((c)object).b;
        } else {
            uri = this.f(string);
        }
        if (uri == null) {
            return null;
        }
        try {
            object = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.read(this.c(), uri);
        }
        catch (FileNotFoundException fileNotFoundException) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("openAssetSteam: " + fileNotFoundException.getMessage() + " (file: " + string + ")");
            return null;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("openAssetSteam: " + illegalArgumentException.getMessage() + " (file: " + string + ")");
            return null;
        }
        if (object == null) {
            return null;
        }
        AssetStream j2 = new AssetStream((InputStream)object, this.a + "/" + string);  // 02b utility/j = AssetStream
        return j2;
    }

    public long c(String string) throws IOException {
        Uri uri = this.f(string);
        if (uri == null) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("getLastModified file missing: " + string);
            return 0L;
        }
        long l = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.getLastModified(this.c(), uri);
        return l;
    }

    public long d(String string) {
        Uri uri = this.f(string);
        if (uri == null) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("getEntrySize file missing: " + string);
            return -1L;
        }
        long l = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.getFileSize(this.c(), uri);
        return l;
    }

    public OutputStream a(String string, boolean bl) {
        String string2;
        Object object;
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.i("writableOutputSteam:" + string);
        Uri uri = this.f(string);
        if (uri == null) {
            object = new File(string);
            string2 = ((File)object).getName();
            Uri uri2 = this.i(string);
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.i("writableOutputSteam creating: " + string2 + " in " + uri2);
            if (uri2 == null) {
                com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("writableOutputSteam: Parent folder not found for: " + string);
                return null;
            }
            try {
                uri = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.createFile(this.c(), uri2, "", string2);
                com.corrodinggames.rts.gameFramework.utility.filesystem.a.i("newFileUri: " + uri);
            }
            catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
                return null;
            }
        }
        try {
            string2 = "w";
            if (bl) {
                string2 = "wa";
            }
            object = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.write(this.c(), uri, string2);
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }
        this.a();
        return (OutputStream)object;  // 02b b.java L169: return var10 (OutputStream)
    }

    public boolean e(String string) {
        boolean bl;
        if (!this.d) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("deleteFile: Not open as writable");
            return false;
        }
        Uri uri = this.f(string);
        if (uri == null) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("deleteFile: fileUri==null for:" + string);
            return false;
        }
        if (com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.isDirectory(this.c(), uri)) {
            throw new RuntimeException("Attempted to delete folder at: " + string + " url:" + uri);
        }
        try {
            bl = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.deleteFile(this.c(), uri);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            return false;
        }
        this.a();
        return bl;
    }

    public boolean a(String string, String string2) {
        Uri uri;
        if (!this.d) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("renameFile: Not open as writable");
            return false;
        }
        Uri uri2 = this.f(string);
        if (uri2 == null) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("renameFile: fileUri==null for:" + string);
            return false;
        }
        String string3 = GameUtils.cosFast(string2);
        com.corrodinggames.rts.gameFramework.utility.filesystem.a.i("Rename: " + uri2 + " to " + string3);
        try {
            uri = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.renameFile(this.c(), uri2, string3);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
        this.a();
        return uri != null;
    }

    public Uri f(String string) {
        c c2 = this.k(string);
        if (c2 == null) {
            return null;
        }
        return c2.b;
    }

    public String[] g(String string) throws IOException {
        c c2 = this.k(string);
        if (c2 == null) {
            return null;
        }
        if (!c2.c) {
            return null;
        }
        HashMap hashMap = c2.a();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : (java.util.Collection<String>) (java.util.Collection) hashMap.keySet()) {
            arrayList.add(string2);
        }
        return arrayList.toArray(new String[0]);
    }

    public boolean h(String string) throws IOException {
        if (string.equals("/") || string.equals("")) {
            return true;
        }
        c c2 = this.k(string);
        if (c2 == null) {
            return false;
        }
        return c2.c;
    }

    public Uri i(String string) {
        Uri uri;
        File file = new File(string);
        String string2 = file.getParent();
        if (string2 == null) {
            string2 = "";
        }
        if ((uri = this.f(string2)) == null) {
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.j("createDirectory: Parent folder: " + string2 + " not found");
        }
        return uri;
    }

    public boolean j(String string) throws FileNotFoundException {
        File file = new File(string);
        String string2 = file.getName();
        Uri uri = this.i(string);
        if (uri == null) {
            return false;
        }
        Uri uri2 = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.createDirectory(this.c(), uri, string2);
        this.a();
        return uri2 != null;
    }

    private c k(String string) {
        return this.l(string);
    }

    private c l(String string) {
        String[] stringArray = string.split("\\\\|\\/");
        c c2 = this.e;
        for (String string2 : stringArray) {
            HashMap hashMap;
            if (string2.trim().equals("")) continue;
            try {
                hashMap = c2.a();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return null;
            }
            c c3 = (c)hashMap.get(string2);
            if (c3 != null) {
                c2 = c3;
                continue;
            }
            String string3 = string2.toLowerCase(Locale.ROOT);
            c3 = (c)c2.e.get(string3);
            if (c3 != null) {
                c2 = c3;
                continue;
            }
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.i("child null for: " + string);
            com.corrodinggames.rts.gameFramework.utility.filesystem.a.i("element: " + string2);
            return null;
        }
        return c2;
    }
}
