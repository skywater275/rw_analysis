/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.widget.Toast
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.widget.Toast;
import com.corrodinggames.rts.gameFramework.GlobalState;

class DataStreamReader$1
implements Runnable {
    final /* synthetic */ GlobalState a;

    DataStreamReader$1(GlobalState l2) {
        this.a = l2;
    }

    @Override
    public void run() {
        String string = this.a.dE;
        try {
            if (string == null) {
                GlobalState.b("Cannot show toast, no message");
                return;
            }
            int n2 = 1;
            Toast toast = Toast.makeText((Context)this.a.am, (CharSequence)string, (int)n2);
            toast.show();
        }
        catch (Exception exception) {
            GlobalState.b("Error showing toast: " + string);
            exception.printStackTrace();
        }
    }
}
