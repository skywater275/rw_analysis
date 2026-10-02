/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.FileWatcher;

public class SleepThread
extends Thread {
    boolean a = true;

    void k() {
    }

    @Override
    public void run() {
        while (this.a) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            FileWatcher.b();
        }
    }
}
