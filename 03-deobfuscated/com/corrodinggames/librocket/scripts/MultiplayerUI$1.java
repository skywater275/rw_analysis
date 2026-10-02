/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MultiplayerUI;
import java.io.IOException;

class MultiplayerUI$1
implements Runnable {
    final /* synthetic */ String val$teamIdString;
    final /* synthetic */ MultiplayerUI this$0;  // Multiplayer 幻觉名修正 (03 中仅 MultiplayerUI)

    MultiplayerUI$1(MultiplayerUI multiplayer, String string){
        this.this$0 = multiplayer;
        this.val$teamIdString = string;
    }

    @Override
    public void run() {
        try {
            this.this$0.showPlayerConfigNow(this.val$teamIdString);
        }
        catch (IOException iOException) {
            // Runnable 覆写不能 throws, 局部消化 (02b Multiplayer$1 run() 同结构)
            iOException.printStackTrace();
        }
    }
}
