package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;

public interface SteamNetworkingCallback {

   void onP2PSessionConnectFail(SteamID var1, SteamNetworking$P2PSessionError var2);

   void onP2PSessionRequest(SteamID var1);
}
