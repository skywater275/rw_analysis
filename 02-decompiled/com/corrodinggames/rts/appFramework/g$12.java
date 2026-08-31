/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.gameFramework.l;

class g$12
implements DialogInterface.OnClickListener {
    final /* synthetic */ g a;

    g$12(g g2) {
        this.a = g2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        l l2 = l.B();
        l2.bX.m("-surrender");
    }
}
