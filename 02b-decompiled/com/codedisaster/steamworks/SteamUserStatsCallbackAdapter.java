package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUserStatsCallback;

class SteamUserStatsCallbackAdapter extends SteamCallbackAdapter {

   SteamUserStatsCallbackAdapter(SteamUserStatsCallback var1) {
      super(var1);
   }

   void onUserStatsReceived(long var1, long var3, int var5) {
      ((SteamUserStatsCallback)this.callback).onUserStatsReceived(var1, new SteamID(var3), SteamResult.byValue(var5));
   }

   void onUserStatsStored(long var1, int var3) {
      ((SteamUserStatsCallback)this.callback).onUserStatsStored(var1, SteamResult.byValue(var3));
   }

   void onUserStatsUnloaded(long var1) {
      ((SteamUserStatsCallback)this.callback).onUserStatsUnloaded(new SteamID(var1));
   }

   void onUserAchievementStored(long var1, boolean var3, String var4, int var5, int var6) {
      ((SteamUserStatsCallback)this.callback).onUserAchievementStored(var1, var3, var4, var5, var6);
   }

   void onLeaderboardFindResult(long var1, boolean var3) {
      ((SteamUserStatsCallback)this.callback).onLeaderboardFindResult(new SteamLeaderboardHandle(var1), var3);
   }

   void onLeaderboardScoresDownloaded(long var1, long var3, int var5) {
      ((SteamUserStatsCallback)this.callback).onLeaderboardScoresDownloaded(new SteamLeaderboardHandle(var1), new SteamLeaderboardEntriesHandle(var3), var5);
   }

   void onLeaderboardScoreUploaded(boolean var1, long var2, int var4, boolean var5, int var6, int var7) {
      ((SteamUserStatsCallback)this.callback).onLeaderboardScoreUploaded(var1, new SteamLeaderboardHandle(var2), var4, var5, var6, var7);
   }

   void onGlobalStatsReceived(long var1, int var3) {
      ((SteamUserStatsCallback)this.callback).onGlobalStatsReceived(var1, SteamResult.byValue(var3));
   }
}
