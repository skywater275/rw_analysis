/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamMatchmakingCallback;
import com.codedisaster.steamworks.SteamResult;

class SteamMatchmakingCallbackAdapter
extends SteamCallbackAdapter {
    private static final SteamMatchmaking$ChatMemberStateChange[] stateChangeValues = SteamMatchmaking$ChatMemberStateChange.values();

    SteamMatchmakingCallbackAdapter(SteamMatchmakingCallback steamMatchmakingCallback) {
        super(steamMatchmakingCallback);
    }

    void onFavoritesListChanged(int n, int n2, int n3, int n4, int n5, boolean bl, int n6) {
        ((SteamMatchmakingCallback)this.callback).onFavoritesListChanged(n, n2, n3, n4, n5, bl, n6);
    }

    void onLobbyInvite(long l, long l2, long l3) {
        ((SteamMatchmakingCallback)this.callback).onLobbyInvite(new SteamID(l), new SteamID(l2), l3);
    }

    void onLobbyEnter(long l, int n, boolean bl, int n2) {
        ((SteamMatchmakingCallback)this.callback).onLobbyEnter(new SteamID(l), n, bl, SteamMatchmaking$ChatRoomEnterResponse.byCode(n2));
    }

    void onLobbyDataUpdate(long l, long l2, boolean bl) {
        ((SteamMatchmakingCallback)this.callback).onLobbyDataUpdate(new SteamID(l), new SteamID(l2), bl);
    }

    void onLobbyChatUpdate(long l, long l2, long l3, int n) {
        SteamID steamID = new SteamID(l);
        SteamID steamID2 = new SteamID(l2);
        SteamID steamID3 = new SteamID(l3);
        for (SteamMatchmaking$ChatMemberStateChange steamMatchmaking$ChatMemberStateChange : stateChangeValues) {
            if (!SteamMatchmaking$ChatMemberStateChange.isSet(steamMatchmaking$ChatMemberStateChange, n)) continue;
            ((SteamMatchmakingCallback)this.callback).onLobbyChatUpdate(steamID, steamID2, steamID3, steamMatchmaking$ChatMemberStateChange);
        }
    }

    void onLobbyChatMessage(long l, long l2, int n, int n2) {
        ((SteamMatchmakingCallback)this.callback).onLobbyChatMessage(new SteamID(l), new SteamID(l2), SteamMatchmaking$ChatEntryType.byCode(n), n2);
    }

    void onLobbyGameCreated(long l, long l2, int n, short s) {
        ((SteamMatchmakingCallback)this.callback).onLobbyGameCreated(new SteamID(l), new SteamID(l2), n, s);
    }

    void onLobbyMatchList(int n) {
        ((SteamMatchmakingCallback)this.callback).onLobbyMatchList(n);
    }

    void onLobbyKicked(long l, long l2, boolean bl) {
        ((SteamMatchmakingCallback)this.callback).onLobbyKicked(new SteamID(l), new SteamID(l2), bl);
    }

    void onLobbyCreated(int n, long l) {
        ((SteamMatchmakingCallback)this.callback).onLobbyCreated(SteamResult.byValue(n), new SteamID(l));
    }

    void onFavoritesListAccountsUpdated(int n) {
        ((SteamMatchmakingCallback)this.callback).onFavoritesListAccountsUpdated(SteamResult.byValue(n));
    }
}
