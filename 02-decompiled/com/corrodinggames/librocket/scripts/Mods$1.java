/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Mods;

class Mods$1
implements Runnable {
    final /* synthetic */ Mods this$0;

    Mods$1(Mods mods) {
        this.this$0 = mods;
    }

    @Override
    public void run() {
        this.this$0.updateMods();
    }
}
