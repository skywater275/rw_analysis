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
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.j.ae;

final class n$3
implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ ae b;

    n$3(EditText editText, ae ae2) {
        this.a = editText;
        this.b = ae2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        String string = this.a.getText().toString();
        this.b.a(string);
        n.i = null;
        n.j = null;
    }
}
