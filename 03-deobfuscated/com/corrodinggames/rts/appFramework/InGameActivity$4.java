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

import com.corrodinggames.rts.gameFramework.GlobalState;

class InGameActivity$4
implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ GlobalState b;
    final /* synthetic */ InGameActivity c;

    InGameActivity$4(InGameActivity g2, EditText editText, GlobalState l2) {
        this.c = g2;
        this.a = editText;
        this.b = l2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        String string = this.a.getText().toString();
        if (string.contains("/") || string.contains("\\") || string.contains(":") || string.contains("*") || string.contains("?") || string.contains("\"") || string.contains("<") || string.contains(">")) {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)this.c);
            builder.setTitle((CharSequence)"Bad Map Name");
            builder.setMessage((CharSequence)"The characters /\\:*?\"<> are not allowed (fat32 formatting)");
            builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new InGameActivity$4$1(this, string));  // 02b g$4: g$4$1 为幻觉类名
            builder.show();
        } else {
            this.b.bL.a(this.b.dl, "/SD/rustedWarfare/maps/" + string + ".tmx");
        }
    }
}
