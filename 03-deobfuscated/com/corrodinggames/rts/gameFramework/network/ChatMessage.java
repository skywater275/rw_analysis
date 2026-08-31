/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.ChatSystem;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;

public class ChatMessage {
    int a;
    String b;
    String c;
    int d;
    long e;
    final /* synthetic */ ChatSystem f;  // 02b j/b.java L14: a=ChatSystem (ChatMessage 为其内部类)

    ChatMessage(ChatSystem a2, int n, String string, String string2, PacketDecoder c2) {
        this.f = a2;
        this.a = n;
        this.b = string;
        this.c = string2;
        if (c2 != null) {
            this.d = c2.c;
        }
        this.e = System.nanoTime();
    }

    public String a() {
        String string = this.b != null ? this.b + ": " + this.c : this.c;
        return string;
    }

    public String b() {
        String string = "";
        if (this.b != null) {
            int n2 = -1;
            if (this.a != -1) {
                n2 = PlayerState.i(this.a);  // 02b j/b.java L45: game/n.i(int)
            }
            string = "<strong> <font color='" + com.corrodinggames.rts.gameFramework.GameUtils.h(n2) + "'>" + this.f.a(this.b) + ": </font></strong>";
        }
        String[] stringArray = this.c.split("\n");
        boolean bl = true;
        for (String string2 : stringArray) {
            if (string2.trim().equals("")) continue;
            if (bl) {
                bl = false;
            } else {
                string = string + "<br/>";
            }
            string = string + this.f.a(string2);
        }
        return string;
    }
}
