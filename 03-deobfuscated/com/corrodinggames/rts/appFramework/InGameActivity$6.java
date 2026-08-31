/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog$Builder
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.widget.EditText
 */
package com.corrodinggames.rts.appFramework;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import com.corrodinggames.rts.appFramework.InGameActivity;


class InGameActivity$6
implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ InGameActivity b;

    InGameActivity$6(InGameActivity g2, EditText editText) {
        this.b = g2;
        this.a = editText;
    }

    public void onClick(DialogInterface dialogInterface, int n) {
        String string = this.a.getText().toString();
        if (string.contains("/") || string.contains("\\") || string.contains(":") || string.contains("*") || string.contains("?") || string.contains("\"") || string.contains("<") || string.contains(">")) {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)this.b);
            builder.setTitle((CharSequence)"Bad Save Name");
            builder.setMessage((CharSequence)"The characters /\\:*?\"<> are not allowed (fat32 formatting)");
            builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new InGameActivity$6$1(this, string));  // 02b g$6: g$6$1 为幻觉类名
            builder.show();
        } else {
            this.b.d(string);
        }
    }
}
