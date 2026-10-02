/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root;

class Root$4
implements Runnable {
    final /* synthetic */ Root this$0;

    Root$4(Root root) {
        this.this$0 = root;
    }

    @Override
    public void run() {
        this.this$0.closePopup();
        this.this$0.back();
    }
}
