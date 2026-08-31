/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;
import com.codedisaster.steamworks.SteamNetworkingCallback;

class SteamNetworkingCallbackAdapter
extends SteamCallbackAdapter {
    SteamNetworkingCallbackAdapter(SteamNetworkingCallback steamNetworkingCallback) {
        super(steamNetworkingCallback);
    }

    void onP2PSessionConnectFail(long l, int n) {
        SteamID steamID = new SteamID(l);
        ((SteamNetworkingCallback)this.callback).onP2PSessionConnectFail(steamID, SteamNetworking$P2PSessionError.byOrdinal(n));
    }

    void onP2PSessionRequest(long l) {
        SteamID steamID = new SteamID(l);
        ((SteamNetworkingCallback)this.callback).onP2PSessionRequest(steamID);
    }
}
