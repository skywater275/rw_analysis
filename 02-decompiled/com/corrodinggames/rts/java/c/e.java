/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamMatchmakingCallback;
import com.codedisaster.steamworks.SteamResult;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.c.a;
import com.corrodinggames.rts.java.c.b;

public class e
implements SteamMatchmakingCallback {
    b a;

    public e(b b2) {
        this.a = b2;
    }

    @Override
    public void onFavoritesListChanged(int n2, int n3, int n4, int n5, int n6, boolean bl, int n7) {
        l.e("onFavoritesListChanged");
    }

    @Override
    public void onLobbyInvite(SteamID steamID, SteamID steamID2, long l2) {
        l.e("onLobbyInvite");
        a a2 = new a(this.a, steamID, steamID2, l2);
        a2.a();
    }

    @Override
    public void onLobbyEnter(SteamID steamID, int n2, boolean bl, SteamMatchmaking$ChatRoomEnterResponse steamMatchmaking$ChatRoomEnterResponse) {
        l.e("onLobbyEnter");
        if (bl) {
            l.e("onLobbyEnter blocked: " + (Object)((Object)steamMatchmaking$ChatRoomEnterResponse));
        }
        this.a.c(steamID);
    }

    @Override
    public void onLobbyDataUpdate(SteamID steamID, SteamID steamID2, boolean bl) {
        l.e("onLobbyDataUpdate success: " + bl);
    }

    @Override
    public void onLobbyChatUpdate(SteamID steamID, SteamID steamID2, SteamID steamID3, SteamMatchmaking$ChatMemberStateChange steamMatchmaking$ChatMemberStateChange) {
        l.e("onLobbyChatUpdate steamIDUserChanged: " + steamID2 + " stateChange:" + (Object)((Object)steamMatchmaking$ChatMemberStateChange));
    }

    @Override
    public void onLobbyChatMessage(SteamID steamID, SteamID steamID2, SteamMatchmaking.ChatEntryType chatEntryType, int n2) {
        l.e("onLobbyChatMessage");
    }

    @Override
    public void onLobbyGameCreated(SteamID steamID, SteamID steamID2, int n2, short s2) {
        l.e("onLobbyGameCreated");
        this.a.a(steamID);
    }

    @Override
    public void onLobbyMatchList(int n2) {
        l.e("onLobbyMatchList");
    }

    @Override
    public void onLobbyKicked(SteamID steamID, SteamID steamID2, boolean bl) {
        l.e("onLobbyKicked");
    }

    @Override
    public void onLobbyCreated(SteamResult steamResult, SteamID steamID) {
        l.e("onLobbyCreated");
        this.a.a(steamID);
    }

    @Override
    public void onFavoritesListAccountsUpdated(SteamResult steamResult) {
        l.e("onFavoritesListAccountsUpdated");
    }
}
