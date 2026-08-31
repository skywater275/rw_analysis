/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.GlobalState;

strictfp class DataStreamReader$3
extends Thread {
    final /* synthetic */ GlobalState a;

    DataStreamReader$3(GlobalState l2) {
        this.a = l2;
    }

    @Override
    public void run() {
        try {
            l$3.sleep(3000L);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
        this.a.ab();
    }
}
