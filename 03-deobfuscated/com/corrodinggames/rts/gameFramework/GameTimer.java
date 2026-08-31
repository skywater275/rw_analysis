/*
 * v19.133f2: 02b gameFramework/ar.java 全文直译 (计时任务/曲目基类)
 * 构造参数 TeamColor 错标修正 → MusicPlayerBase (02b aq)
 */
package com.corrodinggames.rts.gameFramework;

public abstract class GameTimer {
    public String b;

    public GameTimer(String string, MusicPlayerBase aq2) {  // 02b ar.java: ar(String, aq)
        this.b = string;
    }
}
