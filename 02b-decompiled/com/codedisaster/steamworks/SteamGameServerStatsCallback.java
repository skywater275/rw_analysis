package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

public interface SteamGameServerStatsCallback {

   void onStatsReceived(SteamResult var1, SteamID var2);

   void onStatsStored(SteamResult var1, SteamID var2);

   void onStatsUnloaded(SteamID var1);
}
