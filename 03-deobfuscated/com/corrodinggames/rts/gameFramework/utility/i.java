/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.content.Context;
import android.content.res.AssetManager;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class i {
    private ArrayList c;
    public Context a;
    boolean b = true;

    public i(Context context) {
        this.a = context;
        this.a();
    }

    public void a() {
        i$1 i$1 = new i$1(this);
        i$1.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b() {
        i i2 = this;
        synchronized (i2) {
            if (this.c != null) {
                return;
            }
            AssetManager assetManager = this.a.d();
            ArrayList arrayList = new ArrayList();
            try {
                GlobalState.e("------- createIndex -------");
                arrayList.addAll(this.a(assetManager, "", 1));
            }
            catch (RuntimeException iOException) {
                throw new RuntimeException(iOException);
            }
            this.c = arrayList;
        }
    }

    public ArrayList a(AssetManager assetManager, String string, int n2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] stringArray = assetManager.c(string);
        if (string.length() > 0) {
            string = string + "/";
        }
        if (n2 > 140) {
            throw new RuntimeException("dirLevel>140 for: " + string);
        }
        GlobalState.e("c:" + string);
        for (String string2 : stringArray) {
            String string3 = string + string2;
            boolean bl = false;
            if (!string2.contains(".")) {
                bl = true;
            }
            if (string2.equals(".") || string2.equals("..") || string2.equals("")) continue;
            arrayList.add(string3);
            if (!bl) continue;
            arrayList.addAll(this.a(assetManager, string3, n2 + 1));
        }
        return arrayList;
    }

    public ArrayList c() {
        if (this.c != null) {
            if (this.b) {
                GlobalState.e("assetIndex: getFile was not blocked on load");
                this.b = false;
            }
            return this.c;
        }
        long l2 = GlobalState.V();
        this.b();
        if (this.b) {
            GlobalState.e("assetIndex: getFile is BLOCKED on load");
            this.b = false;
        }
        return this.c;
    }

    public boolean a(String string) {
        if (string.endsWith(File.separator)) {
            string = string.substring(0, string.length() - 1);
        }
        string = string.replace("//", "/");
        for (String string2 : (java.util.Collection<String>) (java.util.Collection) this.c()) {
            if (!string2.equals(string)) continue;
            return true;
        }
        return false;
    }

    public String[] b(String string) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String string2 = string;
        if (string2.endsWith(File.separator)) {
            string2 = string2.substring(0, string2.length() - 1);
        }
        int n2 = 0;
        for (String string3 : (java.util.Collection<String>) (java.util.Collection) this.c()) {
            String string4;
            if (!string3.startsWith(string2) || (string4 = string3.substring(string2.length())).length() == 0 || string4.charAt(0) != File.separatorChar || string4.indexOf(File.separator, 1) != -1) continue;
            ++n2;
            String string5 = string3.substring((string2 + "/").length());
            arrayList.add(string5);
        }
        return arrayList.toArray(new String[0]);
    }
}
