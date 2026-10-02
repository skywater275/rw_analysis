/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import java.util.TimerTask;

strictfp class IntArray$6
extends TimerTask {
    final /* synthetic */ NetEngine a;  // 02b ad$6 L10: ad=NetEngine

    IntArray$6(NetEngine ad2) {
        this.a = ad2;
    }

    @Override
    public void run() {
        WebAPIClient.c();  // 02b ad$6 L18: j/n=WebAPIClient
    }
}
