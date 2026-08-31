/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.DebugUI;
import com.corrodinggames.rts.gameFramework.network.PacketDecoder;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;

class DebugUI$1
implements Runnable {
    final /* synthetic */ DebugUI this$0;

    DebugUI$1(DebugUI debug){
        this.this$0 = debug;
    }

    @Override
    public void run() {
        java.util.Iterator iterator = this.this$0.backgroundClientConnections.iterator();
        while (iterator.hasNext()) {
            PacketDecoder c2 = (PacketDecoder)iterator.next();
        }
    }
}
