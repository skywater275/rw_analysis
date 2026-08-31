/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;

final class DialogHelper$5
implements DialogInterface.OnCancelListener {
    final /* synthetic */ PasswordManager a;

    DialogHelper$5(PasswordManager ae2) {
        this.a = ae2;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.a();
        n.i = null;
        n.j = null;
    }
}
