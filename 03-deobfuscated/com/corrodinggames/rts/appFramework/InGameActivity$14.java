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

class InGameActivity$14
implements DialogInterface.OnClickListener {
    final /* synthetic */ InGameActivity a;

    InGameActivity$14(InGameActivity g2) {
        this.a = g2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Returning to battleroom clicked.");  // 02b g$14: l.e 全限定
        GlobalState l2 = GlobalState.B();
        l2.bX.scheduleReturnToBattleroom();
        l2.bS.u = false;
    }
}
