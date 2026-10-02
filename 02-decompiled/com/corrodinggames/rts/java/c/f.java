/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;
import com.codedisaster.steamworks.SteamNetworkingCallback;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.k;
import java.io.IOException;

public class f
implements SteamNetworkingCallback {
    b a;

    public f(b b2) {
        this.a = b2;
    }

    @Override
    public void onP2PSessionConnectFail(SteamID steamID, SteamNetworking$P2PSessionError steamNetworking$P2PSessionError) {
        l.e("onP2PSessionConnectFail:" + (Object)((Object)steamNetworking$P2PSessionError));
        k k2 = (k)this.a.l.get(steamID);
        if (k2 != null && !k2.isClosed()) {
            l.e("onP2PSessionConnectFail: closing active socket");
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
        l.e("onP2PSessionRequest:" + steamID);
        this.a.h.acceptP2PSessionWithUser(steamID);
    }
}
