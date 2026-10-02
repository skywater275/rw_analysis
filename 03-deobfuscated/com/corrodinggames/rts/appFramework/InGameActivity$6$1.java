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


class InGameActivity$6$1
implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ InGameActivity$6 b;  // v19.133f3: g$6 幻觉名修正

    InGameActivity$6$1(InGameActivity$6 var1_1, String string) {  // v19.133f3
        this.b = var1_1;
        this.a = string;
    }

    public void onClick(DialogInterface dialogInterface, int n) {
        InGameActivity.a(this.b.b, this.a);  // 02b g$6$1: g.a(var1,var2)
    }
}
