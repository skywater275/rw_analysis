/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.o.a;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.k;
import java.io.IOException;

class b$1
implements Runnable {
    final /* synthetic */ SteamID a;
    final /* synthetic */ b b;

    b$1(b b2, SteamID steamID) {
        this.b = b2;
        this.a = steamID;
    }

    @Override
    public void run() {
        l l2 = l.B();
        try {
            this.b.a("connectTo runnable start");
            Root root = ScriptEngine.getInstance().getRoot();
            l2.bX.b("starting new");
            this.b.n = this.a;
            this.b.p = this.b.d.getLobbyOwner(this.b.n);
            String string = l2.bQ.lastNetworkPlayerName;
            String string2 = com.corrodinggames.rts.gameFramework.o.a.a().c();
            if (string2 != null && string == null) {
                string = string2;
                string = string.replace(" ", "_");
                string = f.a(string, 20);
            }
            l2.bX.y = string;
            k k2 = new k(this.b, this.b.p);
            this.b.l.put(this.b.p, k2);
            l2.bX.a(k2);
            for (c c2 : l2.bX.aM) {
                c2.i = true;
            }
            this.b.a("connected");
            root.showBattleroom();
            this.b.a("connectTo runnable end");
        }
        catch (IOException iOException) {
            String string = iOException.getMessage();
            l2.c(string, "Connection failed");
            iOException.printStackTrace();
        }
    }
}
