/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.ModsUI;

class ModsUI$1
implements Runnable {
    final /* synthetic */ ModsUI this$0;

    ModsUI$1(ModsUI mods){
        this.this$0 = mods;
    }

    @Override
    public void run() {
        this.this$0.updateMods();
    }
}
