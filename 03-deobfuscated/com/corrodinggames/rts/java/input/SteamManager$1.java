/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;

import com.corrodinggames.rts.gameFramework.steamworks.SteamEngine;
import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.network.PacketDecoder;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.SteamManager;
import java.net.Socket;
import java.io.IOException;

class SteamManager$1
implements Runnable {
    final /* synthetic */ SteamID a;
    final /* synthetic */ SteamManager b;

    SteamManager$1(SteamManager b2, SteamID steamID) {
        this.b = b2;
        this.a = steamID;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        try {
            this.b.a("connectTo runnable start");
            MainUIController root = ScriptEngine.getInstance().getRoot();
            l2.bX.m("starting new");
            this.b.n = this.a;
            this.b.p = this.b.d.getLobbyOwner(this.b.n);
            String string = l2.bQ.lastNetworkPlayerName;
            String string2 = com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().c();
            if (string2 != null && string == null) {
                string = string2;
                string = string.replace(" ", "_");
                string = com.corrodinggames.rts.gameFramework.GameUtils.a(string, 20);
            }
            l2.bX.y = string;
            k k2 = new k(this.b, this.b.p);
            this.b.l.put(this.b.p, k2);
            l2.bX.registerRelayServer((Socket) k2);
            for (PacketDecoder c2 : l2.bX.aM) {
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
