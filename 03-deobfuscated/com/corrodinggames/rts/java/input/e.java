/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;
import com.corrodinggames.rts.java.GameWindow;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamMatchmakingCallback;
import com.codedisaster.steamworks.SteamResult;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.a;
import com.corrodinggames.rts.java.input.SteamManager;

public class e
implements SteamMatchmakingCallback {
    SteamManager a;  // 外部类 SteamManager (GameWindow 幻觉, 同 java/input/a.java 模式)

    public e(SteamManager b2) {
        this.a = b2;
    }

    @Override
    public void onFavoritesListChanged(int n2, int n3, int n4, int n5, int n6, boolean bl, int n7) {
        GlobalState.e("onFavoritesListChanged");
    }

    @Override
    public void onLobbyInvite(SteamID steamID, SteamID steamID2, long l2) {
        GlobalState.e("onLobbyInvite");
        a a2 = new a(this.a, steamID, steamID2, l2);  // java/input/a 类 (InputManager 幻觉)
        a2.a();
    }

    @Override
    public void onLobbyEnter(SteamID steamID, int n2, boolean bl, SteamMatchmaking$ChatRoomEnterResponse steamMatchmaking$ChatRoomEnterResponse) {
        GlobalState.e("onLobbyEnter");
        if (bl) {
            GlobalState.e("onLobbyEnter blocked: " + (Object)((Object)steamMatchmaking$ChatRoomEnterResponse));
        }
        this.a.c(steamID);
    }

    @Override
    public void onLobbyDataUpdate(SteamID steamID, SteamID steamID2, boolean bl) {
        GlobalState.e("onLobbyDataUpdate success: " + bl);
    }

    @Override
    public void onLobbyChatUpdate(SteamID steamID, SteamID steamID2, SteamID steamID3, SteamMatchmaking$ChatMemberStateChange steamMatchmaking$ChatMemberStateChange) {
        GlobalState.e("onLobbyChatUpdate steamIDUserChanged: " + steamID2 + " stateChange:" + (Object)((Object)steamMatchmaking$ChatMemberStateChange));
    }

    @Override
    public void onLobbyChatMessage(SteamID steamID, SteamID steamID2, SteamMatchmaking$ChatEntryType chatEntryType, int n2) {  // 双前缀修正 (L8 import)
        GlobalState.e("onLobbyChatMessage");
    }

    @Override
    public void onLobbyGameCreated(SteamID steamID, SteamID steamID2, int n2, short s2) {
        GlobalState.e("onLobbyGameCreated");
        this.a.a(steamID);
    }

    @Override
    public void onLobbyMatchList(int n2) {
        GlobalState.e("onLobbyMatchList");
    }

    @Override
    public void onLobbyKicked(SteamID steamID, SteamID steamID2, boolean bl) {
        GlobalState.e("onLobbyKicked");
    }

    @Override
    public void onLobbyCreated(SteamResult steamResult, SteamID steamID) {
        GlobalState.e("onLobbyCreated");
        this.a.a(steamID);
    }

    @Override
    public void onFavoritesListAccountsUpdated(SteamResult steamResult) {
        GlobalState.e("onFavoritesListAccountsUpdated");
    }
}
