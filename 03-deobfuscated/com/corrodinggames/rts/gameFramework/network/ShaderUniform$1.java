/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.UDPBroadcastListener;
import java.util.TimerTask;

strictfp class ShaderUniform$1
extends TimerTask {
    final /* synthetic */ UDPBroadcastListener a;  // 02b af$1.java L9: af=UDPBroadcastListener

    ShaderUniform$1(UDPBroadcastListener af2) {
        this.a = af2;
    }

    @Override
    public void run() {
        if (!this.a.d.C) {  // 02b af$1.java L17: af.d=NetEngine.C
            this.a.a();
        }
    }
}
