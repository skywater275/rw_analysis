/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.GameEngine;

strictfp class ActionAddCredits$a
extends Thread {
    final /* synthetic */ GameEngine a;

    ActionAddCredits$a(GameEngine i2) {
        this.a = i2;
    }

    @Override
    public void run() {
        this.a.bX.m("gotoNextLevel");
    }
}
