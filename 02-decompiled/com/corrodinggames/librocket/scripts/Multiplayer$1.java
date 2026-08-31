/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Multiplayer;

class Multiplayer$1
implements Runnable {
    final /* synthetic */ String val$teamIdString;
    final /* synthetic */ Multiplayer this$0;

    Multiplayer$1(Multiplayer multiplayer, String string) {
        this.this$0 = multiplayer;
        this.val$teamIdString = string;
    }

    @Override
    public void run() {
        this.this$0.showPlayerConfigNow(this.val$teamIdString);
    }
}
