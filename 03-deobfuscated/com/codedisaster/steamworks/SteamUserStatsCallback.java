/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamResult;

public interface SteamUserStatsCallback {
    public void onUserStatsReceived(long var1, SteamID var3, SteamResult var4);

    public void onUserStatsStored(long var1, SteamResult var3);

    public void onUserStatsUnloaded(SteamID var1);

    public void onUserAchievementStored(long var1, boolean var3, String var4, int var5, int var6);

    public void onLeaderboardFindResult(SteamLeaderboardHandle var1, boolean var2);

    public void onLeaderboardScoresDownloaded(SteamLeaderboardHandle var1, SteamLeaderboardEntriesHandle var2, int var3);

    public void onLeaderboardScoreUploaded(boolean var1, SteamLeaderboardHandle var2, int var3, boolean var4, int var5, int var6);

    public void onGlobalStatsReceived(long var1, SteamResult var3);
}
