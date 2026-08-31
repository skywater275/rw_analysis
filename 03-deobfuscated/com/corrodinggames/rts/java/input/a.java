/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;
import com.corrodinggames.rts.java.GameWindow;

import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.SteamManager;

public class a
implements Runnable {
    SteamManager a;  // 02b java/c/a.java: 外部类 SteamManager (GameWindow 幻觉)
    String b;
    SteamID c;
    SteamID d;
    long e;
    Thread f;

    public a(SteamManager b2, SteamID steamID, SteamID steamID2, long l) {
        this.a = b2;
        this.c = steamID;
        this.d = steamID2;
        this.e = l;
        this.b = b2.c.getFriendPersonaName(steamID);
    }

    public void a() {  // 02b java/c/a.java L27: a() 方法 (03 误写为无参构造)
        if (this.f != null) {
            throw new RuntimeException("already started");
        }
        a$1 a$1 = new a$1(this);
        ScriptEngine.getInstance().addRunnableToQueue(a$1);
    }

    @Override
    public void run() {
        GlobalState.e("Join clicked");
        MainUIController root = ScriptEngine.getInstance().getRoot();
        root.closePopup();
        GlobalState l2 = GlobalState.B();
        this.a.d.joinLobby(this.d);
    }
}
