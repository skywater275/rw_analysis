/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.ScriptEngine;

class MainUIController$2
implements Runnable {
    final /* synthetic */ MainUIController this$0;

    MainUIController$2(MainUIController root){
        this.this$0 = root;
    }

    @Override
    public void run() {
        ScriptEngine.getInstance().addScriptToQueue("joinServerCallback();");
    }
}
