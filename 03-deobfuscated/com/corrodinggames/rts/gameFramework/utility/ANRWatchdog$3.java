/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.ANRWatchdog;

class ANRWatchdog$3
implements Runnable {
    final /* synthetic */ ANRWatchdog a;

    ANRWatchdog$3(ANRWatchdog d2) {
        this.a = d2;
    }

    @Override
    public void run() {
        ANRWatchdog.getint(this.a, (ANRWatchdog.getint(this.a) + 1) % Integer.MAX_VALUE);
    }
}
