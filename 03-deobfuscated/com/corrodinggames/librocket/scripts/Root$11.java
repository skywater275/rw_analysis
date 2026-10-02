/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.core.FilePickerCallback;

class Root$11
extends FilePickerCallback {
    final /* synthetic */ Root this$0;

    Root$11(Root root){
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
