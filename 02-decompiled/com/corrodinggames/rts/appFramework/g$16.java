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
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.gameFramework.l;

class g$16
implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ boolean b;
    final /* synthetic */ g c;

    g$16(g g2, EditText editText, boolean bl) {
        this.c = g2;
        this.a = editText;
        this.b = bl;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        String string = this.a.getText().toString();
        l l2 = l.B();
        if (!string.trim().equals("")) {
            if (this.b) {
                l2.bX.l(string);
            } else {
                l2.bX.m(string);
            }
        }
        l2.bS.u = false;
    }
}
