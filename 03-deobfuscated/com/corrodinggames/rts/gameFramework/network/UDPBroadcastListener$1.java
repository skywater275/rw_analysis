/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.UDPBroadcastListener;
import java.util.TimerTask;

strictfp class UDPBroadcastListener$1
extends TimerTask {
    final /* synthetic */ UDPBroadcastListener a;

    UDPBroadcastListener$1(UDPBroadcastListener af2) {
        this.a = af2;
    }

    @Override
    public void run() {
        if (!this.a.d.C) {
            this.a.a();
        }
    }
}
