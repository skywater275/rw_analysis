/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUserStatsCallback;

class SteamUserStatsCallbackAdapter
extends SteamCallbackAdapter {
    SteamUserStatsCallbackAdapter(SteamUserStatsCallback steamUserStatsCallback) {
        super(steamUserStatsCallback);
    }

    void onUserStatsReceived(long l, long l2, int n) {
        ((SteamUserStatsCallback)this.callback).onUserStatsReceived(l, new SteamID(l2), SteamResult.byValue(n));
    }

    void onUserStatsStored(long l, int n) {
        ((SteamUserStatsCallback)this.callback).onUserStatsStored(l, SteamResult.byValue(n));
    }

    void onUserStatsUnloaded(long l) {
        ((SteamUserStatsCallback)this.callback).onUserStatsUnloaded(new SteamID(l));
    }

    void onUserAchievementStored(long l, boolean bl, String string, int n, int n2) {
        ((SteamUserStatsCallback)this.callback).onUserAchievementStored(l, bl, string, n, n2);
    }

    void onLeaderboardFindResult(long l, boolean bl) {
        ((SteamUserStatsCallback)this.callback).onLeaderboardFindResult(new SteamLeaderboardHandle(l), bl);
    }

    void onLeaderboardScoresDownloaded(long l, long l2, int n) {
        ((SteamUserStatsCallback)this.callback).onLeaderboardScoresDownloaded(new SteamLeaderboardHandle(l), new SteamLeaderboardEntriesHandle(l2), n);
    }

    void onLeaderboardScoreUploaded(boolean bl, long l, int n, boolean bl2, int n2, int n3) {
        ((SteamUserStatsCallback)this.callback).onLeaderboardScoreUploaded(bl, new SteamLeaderboardHandle(l), n, bl2, n2, n3);
    }

    void onGlobalStatsReceived(long l, int n) {
        ((SteamUserStatsCallback)this.callback).onGlobalStatsReceived(l, SteamResult.byValue(n));
    }
}
