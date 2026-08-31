/*
 * v19.133f2: 02b gameFramework/aw.java 全文直译 (文本包构造器, extends ar=GameTimer)
 */
package com.corrodinggames.rts.gameFramework;

public class TextPacketBuilder extends GameTimer {  // 02b aw extends ar

    PingTimer a;  // 02b aw.java: av a

    public TextPacketBuilder(String string, PingTimer av2) {  // 02b aw(String, av)
        super(string, av2);
        this.a = av2;
    }
}
