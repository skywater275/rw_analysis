/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;

public interface SteamNetworkingCallback {
    public void onP2PSessionConnectFail(SteamID var1, SteamNetworking.P2PSessionError var2);

    public void onP2PSessionRequest(SteamID var1);
}
