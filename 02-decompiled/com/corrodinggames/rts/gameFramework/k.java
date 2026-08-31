/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.j;

class k
extends Thread {
    boolean a = true;

    k() {
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
            j.b();
        }
    }
}
