/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.j.ae;

final class n$4
implements DialogInterface.OnClickListener {
    final /* synthetic */ ae a;

    n$4(ae ae2) {
        this.a = ae2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        this.a.a();
        n.i = null;
        n.j = null;
    }
}
