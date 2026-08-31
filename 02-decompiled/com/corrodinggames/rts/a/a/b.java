/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;

public class b
extends l {
    public void a() {
        this.b();
    }

    public void b() {
        com.corrodinggames.rts.gameFramework.l.e("networkSocks");
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        for (int j = 0; j < 10000; ++j) {
            l2.bX.b(false);
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            l2.bX.b("test");
        }
        com.corrodinggames.rts.gameFramework.l.e("done");
        try {
            Thread.sleep(100000L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
