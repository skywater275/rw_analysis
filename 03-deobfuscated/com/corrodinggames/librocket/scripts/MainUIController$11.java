/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.core.FilePickerCallback;

class MainUIController$11
extends FilePickerCallback {
    final /* synthetic */ MainUIController this$0;

    MainUIController$11(MainUIController root){
        this.this$0 = root;
    }

    @Override
    public void onFileSelected() {
        GlobalState.e("importFilePopup: onFileSelected");
    }

    @Override
    public void onCancelled() {
        GlobalState.e("importFilePopup: onCancelled");
    }
}
