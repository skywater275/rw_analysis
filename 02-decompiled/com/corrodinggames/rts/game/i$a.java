/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.i;

strictfp class i$a
extends Thread {
    final /* synthetic */ i a;

    i$a(i i2) {
        this.a = i2;
    }

    @Override
    public void run() {
        this.a.bX.b("gotoNextLevel");
    }
}
