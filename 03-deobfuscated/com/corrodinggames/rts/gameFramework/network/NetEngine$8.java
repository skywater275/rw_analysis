/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.ServerConnector;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;

strictfp class NetEngine$8
implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ NetEngine b;

    NetEngine$8(NetEngine ad2, boolean bl) {
        this.b = ad2;
        this.a = bl;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        GlobalState.e("startJoinServerInternalThread callback");
        ServerConnector an2 = this.b.bF;
        this.b.bF = null;
        if (an2 == null) {
            GlobalState.e("startJoinServerInternalThread callback gameConnector==null");
            return;
        }
        if (an2.e != null) {
            GlobalState.e("startJoinServerInternalThread failed to connect: " + an2.e);
            if (this.a) {
                l2.bX.m("Reconnect failed: " + an2.e);
                this.b.m("Reconnect failed", "reconnect failed");
                l2.d("Reconnect failed", "Reconnect failed: " + an2.e);
                l2.i("Reconnect failed: " + an2.e);
            }
            return;
        }
        try {
            l2.bX.m("starting new");
            l2.bX.registerRelayServer(an2.g);
        }
        catch (IOException iOException) {
            String string = iOException.getMessage();
            l2.c(string, "Connection failed");
            iOException.printStackTrace();
        }
    }
}
