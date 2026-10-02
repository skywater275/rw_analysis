/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.game.GameEngine;
import com.corrodinggames.rts.game.GameEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class NetworkUtils {
    public static int a(int n) {
        return n + 5;
    }

    public static String a() {
        if (!GlobalState.at()) {
            return null;
        }
        GameEngine i2 = (GameEngine) GlobalState.B();  // 02b ar.java L10: game/i=GameEngine (v19.98 仲裁)
        String string = i2.onMatchComplete();  // 02b game/i.java L2137: o()=应用签名 (03 onMatchComplete 误名)
        return string;
    }
}
