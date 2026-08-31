/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.c$2;

class c$2$1
implements Runnable {
    final /* synthetic */ c$2 a;

    c$2$1(c$2 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        if (this.a.b.bQ.hasSelectedAStorageType) {
            this.a.c.run();
        }
    }
}
