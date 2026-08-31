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
import com.corrodinggames.rts.gameFramework.s;

class g$10
implements DialogInterface.OnClickListener {
    final /* synthetic */ g a;

    g$10(g g2) {
        this.a = g2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        l l2 = l.B();
        l2.L();
        l2.a(true, s.b);
        l2.J();
    }
}
