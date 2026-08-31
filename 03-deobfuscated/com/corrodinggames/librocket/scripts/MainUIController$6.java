/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;

class MainUIController$6
implements Runnable {
    final /* synthetic */ String val$serverListDataId;
    final /* synthetic */ String val$serverRowTemplateId;
    final /* synthetic */ String val$refreshButton;
    final /* synthetic */ MainUIController this$0;

    MainUIController$6(MainUIController root, String string, String string2, String string3){
        this.this$0 = root;
        this.val$serverListDataId = string;
        this.val$serverRowTemplateId = string2;
        this.val$refreshButton = string3;
    }

    @Override
    public void run() {
        this.this$0.scriptEngine.addScriptToQueue("displayServerListRaw(" + this.this$0.restrictedString(this.val$serverListDataId) + "," + this.this$0.restrictedString(this.val$serverRowTemplateId) + "," + this.this$0.restrictedString(this.val$refreshButton) + ")");
    }
}
