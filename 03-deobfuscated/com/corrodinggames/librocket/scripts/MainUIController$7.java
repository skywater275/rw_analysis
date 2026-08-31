/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.File;

class MainUIController$7
implements Runnable {
    final /* synthetic */ GlobalState val$game;  // 02b Root$7: l val$game; HoverUnit 幻觉名修正
    final /* synthetic */ String val$saveName;
    final /* synthetic */ MainUIController this$0;

    MainUIController$7(MainUIController root, GlobalState l2, String string){
        this.this$0 = root;
        this.val$game = l2;
        this.val$saveName = string;
    }

    @Override
    public void run() {
        this.this$0.closePopup();
        File file = this.val$game.ca.a(this.val$saveName, false);
        com.corrodinggames.rts.gameFramework.core.PlatformExtension.a(file);  // 02b: l/a.a(File)
    }
}
