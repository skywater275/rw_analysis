/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad$5;

strictfp class ad$5$1
implements Runnable {
    final /* synthetic */ ad$5 a;

    ad$5$1(ad$5 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        this.a.c.b("already disconnected");
        this.a.b.bS.g.l();
    }
}
