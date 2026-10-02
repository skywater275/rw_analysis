/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamResult;

public interface SteamMatchmakingCallback {
    public void onFavoritesListChanged(int var1, int var2, int var3, int var4, int var5, boolean var6, int var7);

    public void onLobbyInvite(SteamID var1, SteamID var2, long var3);

    public void onLobbyEnter(SteamID var1, int var2, boolean var3, SteamMatchmaking$ChatRoomEnterResponse var4);

    public void onLobbyDataUpdate(SteamID var1, SteamID var2, boolean var3);

    public void onLobbyChatUpdate(SteamID var1, SteamID var2, SteamID var3, SteamMatchmaking$ChatMemberStateChange var4);

    public void onLobbyChatMessage(SteamID var1, SteamID var2, SteamMatchmaking$ChatEntryType var3, int var4);

    public void onLobbyGameCreated(SteamID var1, SteamID var2, int var3, short var4);

    public void onLobbyMatchList(int var1);

    public void onLobbyKicked(SteamID var1, SteamID var2, boolean var3);

    public void onLobbyCreated(SteamResult var1, SteamID var2);

    public void onFavoritesListAccountsUpdated(SteamResult var1);
}
