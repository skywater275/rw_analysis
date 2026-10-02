/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.l.a;
import java.io.File;

class Root$9
implements Runnable {
    final /* synthetic */ l val$game;
    final /* synthetic */ String val$replayName;
    final /* synthetic */ Root this$0;

    Root$9(Root root, l l2, String string) {
        this.this$0 = root;
        this.val$game = l2;
        this.val$replayName = string;
    }

    @Override
    public void run() {
        this.this$0.closePopup();
        File file = this.val$game.cb.a(this.val$replayName, false);
        a.a(file);
    }
}
