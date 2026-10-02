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
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GlobalState$2$1;
import com.corrodinggames.rts.gameFramework.GlobalState$2$2;

class GlobalState$2
implements Runnable {
    final /* synthetic */ GlobalState a;

    GlobalState$2(GlobalState l2) {
        this.a = l2;
    }

    @Override
    public void run() {
        GlobalState$2$1 GlobalState$2$1 = new GlobalState$2$1(this);
        GlobalState$2$2 GlobalState$2$2 = new GlobalState$2$2(this);
        l.e("showMessageBoxRunnable context:" + this.a.am.getClass().getName());
        try {
            new AlertDialog.Builder(this.a.am).setIcon(17301543).setTitle((CharSequence)this.a.dF).setMessage((CharSequence)this.a.dG).setOnCancelListener((DialogInterface.OnCancelListener)GlobalState$2$2).setPositiveButton((CharSequence)"Ok", (DialogInterface.OnClickListener)GlobalState$2$1).show();
        }
        catch (WindowManager.BadTokenException badTokenException) {
            l.b("Failed to show message: " + this.a.dG);
            badTokenException.printStackTrace();
        }
    }
}
