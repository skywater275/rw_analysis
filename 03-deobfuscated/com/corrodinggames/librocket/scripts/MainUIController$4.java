/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;

class MainUIController$4
implements Runnable {
    final /* synthetic */ MainUIController this$0;

    MainUIController$4(MainUIController root){
        this.this$0 = root;
    }

    @Override
    public void run() {
        this.this$0.closePopup();
        this.this$0.back();
    }
}
