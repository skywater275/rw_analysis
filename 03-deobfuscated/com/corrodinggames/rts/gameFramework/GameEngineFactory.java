/*
 * 02b v.java 直译 (创建 GameEngine)
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;

public class GameEngineFactory extends GlobalStateFactory {

    public GlobalState a(Context context) {
        return new com.corrodinggames.rts.game.GameEngine(context);
    }
}
