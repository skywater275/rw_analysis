/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;



class AndroidUIHelper$2$1
implements Runnable {
    final /* synthetic */ AndroidUIHelper$2 a;  // v19.133f3: c$2 幻觉名修正

    AndroidUIHelper$2$1(AndroidUIHelper$2 var1_1) {  // v19.133f3
        this.a = var1_1;
    }

    @Override
    public void run() {
        if (this.a.b.bQ.hasSelectedAStorageType) {
            this.a.c.run();
        }
    }
}
