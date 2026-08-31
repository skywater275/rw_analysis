/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine$5;

strictfp class NetEngine$5$1
implements Runnable {
    final /* synthetic */ NetEngine$5 a;

    NetEngine$5$1(NetEngine$5 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        this.a.c.m("already disconnected");
        this.a.b.bS.g.l();
    }
}
