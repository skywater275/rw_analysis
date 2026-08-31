/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  com.corrodinggames.rts.appFramework.common.SAFInterface$FileRow
 */
package com.corrodinggames.rts.gameFramework.utility.filesystem;

import android.content.Context;
import android.net.Uri;
import com.corrodinggames.rts.appFramework.common.SAFInterface;
import com.corrodinggames.rts.gameFramework.utility.filesystem.b;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class c {
    String a;
    Uri b;
    boolean c;
    HashMap d;
    HashMap e;
    boolean f;
    int g;
    final /* synthetic */ b h;

    public c(b b2, String string, Uri uri, boolean bl) {
        this.h = b2;
        this.a = string;
        this.b = uri;
        this.c = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public HashMap a() throws IOException {
        if (this.d == null || this.f || this.g != this.h.g) {
            c c2 = this;
            synchronized (c2) {
                if (this.d == null || this.f || this.g != this.h.g) {
                    this.a(com.corrodinggames.rts.appFramework.c.a());
                }
            }
        }
        return this.d;
    }

    public void a(Context context) throws IOException {
        HashMap<String, c> hashMap = new HashMap<String, c>();
        HashMap<String, c> hashMap2 = new HashMap<String, c>();
        if (this.c) {
            ArrayList arrayList = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.listWithDetails(context, this.b);
            for (Iterator iterator = arrayList.iterator(); iterator.hasNext(); ) {
                SAFInterface.FileRow fileRow = (SAFInterface.FileRow) iterator.next();  // 02b a/c.java L53: (FileRow)var5.next()
                String string = fileRow.id;
                Uri uri = com.corrodinggames.rts.gameFramework.utility.filesystem.a.a.getChildUri(this.b, string);
                String string2 = fileRow.name;
                boolean bl = fileRow.isDirectory;
                if (string2.contains("/")) {
                    com.corrodinggames.rts.gameFramework.utility.filesystem.a.h("Name contains symbols: " + string2);
                    string2 = string2.replace("/", "_");
                }
                String string3 = this.a + "/" + string2;
                c c2 = new c(this.h, string3, uri, bl);
                hashMap.put(string2, c2);
                String string4 = string2.toLowerCase(Locale.ROOT);
                if (hashMap2.get(string4) != null) continue;
                hashMap2.put(string4, c2);
            }
        }
        this.d = hashMap;
        this.e = hashMap2;
        this.f = false;
        this.g = this.h.g;
    }
}
