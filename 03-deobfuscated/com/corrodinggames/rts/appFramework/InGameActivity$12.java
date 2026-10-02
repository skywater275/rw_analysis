/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;

class InGameActivity$12
implements DialogInterface.OnClickListener {
    final /* synthetic */ InGameActivity a;

    InGameActivity$12(InGameActivity g2) {
        this.a = g2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        GlobalState l2 = GlobalState.B();
        l2.bX.m("-surrender");
    }
}
