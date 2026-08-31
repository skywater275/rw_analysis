/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.LibRocketContext;
import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.GameLauncher;

class GameLauncher$3
implements Runnable {
    final /* synthetic */ GameLauncher a;  // Main 幻觉名修正

    GameLauncher$3(GameLauncher main){
        this.a = main;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        GlobalState.e("got startGameEvent..");
        com.corrodinggames.rts.appFramework.DialogHelper.r();  // 02b: appFramework/n.r() (DesktopMusicPlayer 幻觉名修正)
        if (l2.bL == null || !l2.bL.W) {
            GlobalState.e("Not starting multiplayer game because map failed to load");
            l2.bX.onStartGameFailed();  // 02b: bX.af() (03 语义名)
            return;
        }
        l2.bX.packetLossDetected = true;  // 02b: bX.bd (字段序: bc=highLatencyDetected, bd=packetLossDetected)
        l2.bH = false;
        l2.aq = false;
        this.a.i.c(false);
        com.corrodinggames.librocket.a.a().f();
        this.a.p.getActiveDocument();
        if (this.a.p.c != null) {
            this.a.p.c.getRoot().resumeNonMenu();
        } else {
            GlobalState.e("startGameEvent: scriptEngine==null");
            GlobalState.T();
        }
    }
}
