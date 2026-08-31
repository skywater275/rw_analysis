/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ah;

final class ag$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ah b;

    ag$1(String string, ah ah2) {
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
        l.e("Running delayed close of zip: " + this.a);
        this.b.a();
    }
}
