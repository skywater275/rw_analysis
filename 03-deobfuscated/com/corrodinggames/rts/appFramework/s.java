/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 */
package com.corrodinggames.rts.appFramework;

import android.net.Uri;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.appFramework.AbstractAppBase;
import com.corrodinggames.rts.appFramework.AudioUtils$1;

public class s
extends GameActivity {
    boolean boolean1 = true;
    static boolean boolean2 = false;
    int[] e = new int[]{100, 250, 500, 1000, 2000, 5000, 10000};
    AbstractAppBase d = new s$1(this);

    public void l() {
        String string = "rustedWarfare".replace("//", "%2F");
        Uri uri = Uri.parse((String)("content://com.android.externalstorage.documents/document/primary%3A" + string));
        AndroidUIHelper.a(this, 9, true, "Select a Rusted Warfare Folder to use", uri);
    }
}
