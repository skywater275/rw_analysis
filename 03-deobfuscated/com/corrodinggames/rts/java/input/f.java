/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;
import com.codedisaster.steamworks.SteamNetworkingCallback;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.SteamManager;
import com.corrodinggames.rts.java.input.k;
import java.io.IOException;

public class f
implements SteamNetworkingCallback {
    SteamManager a;

    public f(SteamManager b2) {
        this.a = b2;
    }

    @Override
    public void onP2PSessionConnectFail(SteamID steamID, SteamNetworking$P2PSessionError steamNetworking$P2PSessionError) {
        GlobalState.e("onP2PSessionConnectFail:" + (Object)((Object)steamNetworking$P2PSessionError));
        k k2 = (k)this.a.l.get(steamID);
        if (k2 != null && !k2.isClosed()) {
            GlobalState.e("onP2PSessionConnectFail: closing active socket");
            try {
                k2.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    @Override
    public void onP2PSessionRequest(SteamID steamID) {
        GlobalState.e("onP2PSessionRequest:" + steamID);
        this.a.h.acceptP2PSessionWithUser(steamID);
    }
}
