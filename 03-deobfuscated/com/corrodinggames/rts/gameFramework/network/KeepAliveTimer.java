/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.NetworkPacket;
import java.io.IOException;
import java.util.TimerTask;

class KeepAliveTimer
extends TimerTask {
    private final NetEngine netEngineRef;
    public boolean keepAliveEnabled = true;
    public long lastKeepAliveTime = 0L;

    KeepAliveTimer(NetEngine ad2) {
        this.netEngineRef = ad2;
    }

    @Override
    public void run() {
        try {
            long l = System.currentTimeMillis();
            if (this.netEngineRef.au != 0L && (l > this.netEngineRef.au + 5L || l < this.netEngineRef.au)) {
                this.netEngineRef.au = 0L;
                this.netEngineRef.sendSync();
            }
            if (l > this.lastKeepAliveTime + 1000L || l < this.lastKeepAliveTime) {
                this.lastKeepAliveTime = l;
                if (this.keepAliveEnabled) {
                    OutputNetStream as2 = new OutputNetStream();
                    as2.a(System.currentTimeMillis());
                    as2.c(0);
                    NetworkPacket au2 = as2.b(108);  // 02b: au=NetworkPacket
                    this.netEngineRef.sendPacketToClients(au2);  // 02b ad L3913: g(au)=sendPacketToClients
                } else {
                    this.netEngineRef.stopServer();
                }
                this.keepAliveEnabled = !this.keepAliveEnabled;
            }
        }
        catch (RuntimeException iOException) {
            iOException.printStackTrace();
        }
    }
}
