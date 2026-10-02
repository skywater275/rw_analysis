/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform.net;

import com.corrodinggames.rts.platform.net.TestCase;

public class TestNetworking
extends TestCase {
    public void a() {
        this.b();
    }

    public void b() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("networkSocks");
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        for (int j = 0; j < 10000; ++j) {
            l2.bX.m(false);
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            l2.bX.m("test");
        }
        com.corrodinggames.rts.gameFramework.GlobalState.e("done");
        try {
            Thread.sleep(100000L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
