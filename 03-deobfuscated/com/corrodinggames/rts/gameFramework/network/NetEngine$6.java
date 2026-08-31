/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import java.util.TimerTask;

strictfp class NetEngine$6
extends TimerTask {
    final /* synthetic */ NetEngine a;

    NetEngine$6(NetEngine ad2) {
        this.a = ad2;
    }

    @Override
    public void run() {
        WebAPIClient.c();
    }
}
