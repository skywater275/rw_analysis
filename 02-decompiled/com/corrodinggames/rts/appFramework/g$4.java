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
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.appFramework.g$4$1;
import com.corrodinggames.rts.gameFramework.l;

class g$4
implements DialogInterface.OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ l b;
    final /* synthetic */ g c;

    g$4(g g2, EditText editText, l l2) {
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
            builder.setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)new g$4$1(this, string));
            builder.show();
        } else {
            this.b.bL.a(this.b.dl, "/SD/rustedWarfare/maps/" + string + ".tmx");
        }
    }
}
