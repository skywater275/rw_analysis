/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.Toast
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.widget.Toast;
import com.corrodinggames.rts.gameFramework.l;

class l$1
implements Runnable {
    final /* synthetic */ l a;

    l$1(l l2) {
        this.a = l2;
    }

    @Override
    public void run() {
        String string = this.a.dE;
        try {
            if (string == null) {
                l.b("Cannot show toast, no message");
                return;
            }
            int n2 = 1;
            Toast toast = Toast.makeText((Context)this.a.am, (CharSequence)string, (int)n2);
            toast.show();
        }
        catch (Exception exception) {
            l.b("Error showing toast: " + string);
            exception.printStackTrace();
        }
    }
}
