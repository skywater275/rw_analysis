/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 */
package com.corrodinggames.rts.appFramework;

import android.net.Uri;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.d;
import com.corrodinggames.rts.appFramework.s$1;

public class s
extends b {
    boolean c = true;
    static boolean d = false;
    int[] e = new int[]{100, 250, 500, 1000, 2000, 5000, 10000};
    d f = new s$1(this);

    public void l() {
        String string = "rustedWarfare".replace("//", "%2F");
        Uri uri = Uri.parse((String)("content://com.android.externalstorage.documents/document/primary%3A" + string));
        com.corrodinggames.rts.appFramework.c.a(this, 9, true, "Select a Rusted Warfare Folder to use", uri);
    }
}
