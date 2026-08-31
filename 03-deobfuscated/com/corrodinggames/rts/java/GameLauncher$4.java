/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.network.PacketDecoder;
import com.corrodinggames.rts.java.GameLauncher;

class GameLauncher$4
implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ PacketDecoder d;  // 02b j.c; DisplayMessagePump 幻觉名修正
    final /* synthetic */ GameLauncher e;  // Main 幻觉名修正

    GameLauncher$4(GameLauncher main, int n, String string, String string2, PacketDecoder c2){
        this.e = main;
        this.a = n;
        this.b = string;
        this.c = string2;
        this.d = c2;
    }

    @Override
    public void run() {
        this.e.p.c.getRoot().receiveChatMessage(this.a, this.b, this.c, this.d);
    }
}
