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


class InGameActivity$4$1
implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ InGameActivity$4 b;  // v19.133f3: g$4 幻觉名修正

    InGameActivity$4$1(InGameActivity$4 var1_1, String string) {  // v19.133f3
        this.b = var1_1;
        this.a = string;
    }

    public void onClick(DialogInterface dialogInterface, int n) {
        InGameActivity.b(this.b.c, this.a);  // 02b g$4$1: g.b(var1,var2)
    }
}
