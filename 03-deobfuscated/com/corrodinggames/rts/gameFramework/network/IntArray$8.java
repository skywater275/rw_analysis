/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.ServerConnector;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;

strictfp class IntArray$8
implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ NetEngine b;  // 02b ad$8 L12: ad=NetEngine

    IntArray$8(NetEngine ad2, boolean bl) {
        this.b = ad2;
        this.a = bl;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();  // 02b ad$8 L21: l=GlobalState
        GlobalState.e("startJoinServerInternalThread callback");
        ServerConnector an2 = this.b.bF;  // 02b ad$8 L23: j/an=ServerConnector
        this.b.bF = null;
        if (an2 == null) {
            GlobalState.e("startJoinServerInternalThread callback gameConnector==null");
            return;
        }
        if (an2.e != null) {
            GlobalState.e("startJoinServerInternalThread failed to connect: " + an2.e);
            if (this.a) {
                l2.bX.m("Reconnect failed: " + an2.e);  // 02b ad L699: b(String)=m(String) Disconnect 日志
                this.b.m("Reconnect failed", "reconnect failed");  // 02b ad L3020: b(String,String)=m(String,String) closeBattleroom
                l2.d("Reconnect failed", "Reconnect failed: " + an2.e);
                l2.i("Reconnect failed: " + an2.e);
            }
            return;
        }
        try {
            l2.bX.m("starting new");  // 02b ad L699
            l2.bX.registerRelayServer(an2.g);  // 02b ad L3793: a(Socket)=registerRelayServer(Socket)
        }
        catch (IOException iOException) {
            String string = iOException.getMessage();
            l2.c(string, "Connection failed");
            iOException.printStackTrace();
        }
    }
}
