/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamGameServerStatsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

class SteamGameServerStatsCallbackAdapter
extends SteamCallbackAdapter {
    SteamGameServerStatsCallbackAdapter(SteamGameServerStatsCallback steamGameServerStatsCallback) {
        super(steamGameServerStatsCallback);
    }

    void onStatsReceived(int n, long l) {
        ((SteamGameServerStatsCallback)this.callback).onStatsReceived(SteamResult.byValue(n), new SteamID(l));
    }

    void onStatsStored(int n, long l) {
        ((SteamGameServerStatsCallback)this.callback).onStatsStored(SteamResult.byValue(n), new SteamID(l));
    }

    void onStatsUnloaded(long l) {
        ((SteamGameServerStatsCallback)this.callback).onStatsUnloaded(new SteamID(l));
    }
}
