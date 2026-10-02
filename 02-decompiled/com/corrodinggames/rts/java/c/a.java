/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.c.a$1;
import com.corrodinggames.rts.java.c.b;

public class a
implements Runnable {
    b a;
    String b;
    SteamID c;
    SteamID d;
    long e;
    Thread f;

    public a(b b2, SteamID steamID, SteamID steamID2, long l) {
        this.a = b2;
        this.c = steamID;
        this.d = steamID2;
        this.e = l;
        this.b = b2.c.getFriendPersonaName(steamID);
    }

    public void a() {
        if (this.f != null) {
            throw new RuntimeException("already started");
        }
        a$1 a$1 = new a$1(this);
        ScriptEngine.getInstance().addRunnableToQueue(a$1);
    }

    @Override
    public void run() {
        l.e("Join clicked");
        Root root = ScriptEngine.getInstance().getRoot();
        root.closePopup();
        l l2 = l.B();
        this.a.d.joinLobby(this.d);
    }
}
