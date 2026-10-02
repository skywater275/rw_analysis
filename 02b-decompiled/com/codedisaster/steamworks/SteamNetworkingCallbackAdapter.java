package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;
import com.codedisaster.steamworks.SteamNetworkingCallback;

class SteamNetworkingCallbackAdapter extends SteamCallbackAdapter {

   SteamNetworkingCallbackAdapter(SteamNetworkingCallback var1) {
      super(var1);
   }

   void onP2PSessionConnectFail(long var1, int var3) {
      SteamID var4 = new SteamID(var1);
      ((SteamNetworkingCallback)this.callback).onP2PSessionConnectFail(var4, SteamNetworking$P2PSessionError.byOrdinal(var3));
   }

   void onP2PSessionRequest(long var1) {
      SteamID var3 = new SteamID(var1);
      ((SteamNetworkingCallback)this.callback).onP2PSessionRequest(var3);
   }
}
