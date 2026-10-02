/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ah;

final class NetworkException$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ah b;

    NetworkException$1(String string, ah ah2) {
        this.a = string;
        this.b = ah2;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1500L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        GlobalState.e("Running delayed close of zip: " + this.a);
        this.b.a();
    }
}
