/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.rts.gameFramework.GlobalState;

class MainUIController$10
implements Runnable {
    final /* synthetic */ GlobalState val$game;  // 02b: l val$game; HoverUnit 幻觉名修正
    final /* synthetic */ String val$replayName;
    final /* synthetic */ MainUIController this$0;

    MainUIController$10(MainUIController root, GlobalState l2, String string){
        this.this$0 = root;
        this.val$game = l2;
        this.val$replayName = string;
    }

    @Override
    public void run() {
        this.val$game.cb.e(this.val$replayName);
        this.this$0.closePopup();
        this.this$0.showMaps();
    }
}
