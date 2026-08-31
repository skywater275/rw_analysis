/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;
import java.util.HashMap;

class MainUIController$1
implements Runnable {
    final /* synthetic */ String val$level;
    final /* synthetic */ MainUIController this$0;

    MainUIController$1(MainUIController root, String string){
        this.this$0 = root;
        this.val$level = string;
    }

    @Override
    public void run() {
        HashMap hashMap = null;
        boolean bl = false;
        this.this$0.libRocket.setDocument(this.val$level, hashMap, bl);
        this.this$0.onShowNewScreen();
    }
}
