/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.widget.EditText
 */
package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.widget.EditText;
import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;

class InGameActivity$2
implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ boolean b;
    final /* synthetic */ InGameActivity c;

    InGameActivity$2(InGameActivity g2, EditText editText, boolean bl) {
        this.c = g2;
        this.a = editText;
        this.b = bl;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        String string = this.a.getText().toString();
        GlobalState l2 = GlobalState.B();
        if (!string.trim().equals("")) {
            if (this.b) {
                l2.bX.prepareChatMessage(string);
            } else {
                l2.bX.m(string);
            }
        }
        l2.bS.u = false;
        l2.bS.setPingAction();
    }
}
