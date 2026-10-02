/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package com.corrodinggames.rts.gameFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.gameFramework.l$2;

class l$2$1
implements DialogInterface.OnClickListener {
    final /* synthetic */ l$2 a;

    l$2$1(l$2 var1_1) {
        this.a = var1_1;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        this.a.a.bp = false;
    }
}
