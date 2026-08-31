/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog$Builder
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.view.WindowManager$BadTokenException
 */
package com.corrodinggames.rts.gameFramework;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.WindowManager;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.l$2$1;
import com.corrodinggames.rts.gameFramework.l$2$2;

class l$2
implements Runnable {
    final /* synthetic */ l a;

    l$2(l l2) {
        this.a = l2;
    }

    @Override
    public void run() {
        l$2$1 l$2$1 = new l$2$1(this);
        l$2$2 l$2$2 = new l$2$2(this);
        l.e("showMessageBoxRunnable context:" + this.a.am.getClass().getName());
        try {
            new AlertDialog.Builder(this.a.am).setIcon(17301543).setTitle((CharSequence)this.a.dF).setMessage((CharSequence)this.a.dG).setOnCancelListener((DialogInterface.OnCancelListener)l$2$2).setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)l$2$1).show();
        }
        catch (WindowManager.BadTokenException badTokenException) {
            l.b("Failed to show message: " + this.a.dG);
            badTokenException.printStackTrace();
        }
    }
}
