/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.GameEngine;

strictfp class GameEngine$a
extends Thread {
    final /* synthetic */ GameEngine a;

    GameEngine$a(GameEngine i2) {
        this.a = i2;
    }

    @Override
    public void run() {
        this.a.bX.m("gotoNextLevel");  // 02b i$a: bX.b(String) L699; player 幻觉名修正
    }
}
