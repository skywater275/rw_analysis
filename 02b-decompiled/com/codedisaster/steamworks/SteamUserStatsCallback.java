package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamResult;

public interface SteamUserStatsCallback {

   void onUserStatsReceived(long var1, SteamID var3, SteamResult var4);

   void onUserStatsStored(long var1, SteamResult var3);

   void onUserStatsUnloaded(SteamID var1);

   void onUserAchievementStored(long var1, boolean var3, String var4, int var5, int var6);

   void onLeaderboardFindResult(SteamLeaderboardHandle var1, boolean var2);

   void onLeaderboardScoresDownloaded(SteamLeaderboardHandle var1, SteamLeaderboardEntriesHandle var2, int var3);

   void onLeaderboardScoreUploaded(boolean var1, SteamLeaderboardHandle var2, int var3, boolean var4, int var5, int var6);

   void onGlobalStatsReceived(long var1, SteamResult var3);
}
