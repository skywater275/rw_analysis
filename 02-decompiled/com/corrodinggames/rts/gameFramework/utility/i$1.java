/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.i;

class i$1
extends Thread {
    final /* synthetic */ i a;

    i$1(i i2) {
        this.a = i2;
    }

    @Override
    public void run() {
        this.a.b();
    }
}
