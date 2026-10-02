/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.j.ae;

final class n$5
implements DialogInterface.OnCancelListener {
    final /* synthetic */ ae a;

    n$5(ae ae2) {
        this.a = ae2;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.a();
        n.i = null;
        n.j = null;
    }
}
