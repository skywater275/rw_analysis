package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamResult;

public interface SteamMatchmakingCallback {

   void onFavoritesListChanged(int var1, int var2, int var3, int var4, int var5, boolean var6, int var7);

   void onLobbyInvite(SteamID var1, SteamID var2, long var3);

   void onLobbyEnter(SteamID var1, int var2, boolean var3, SteamMatchmaking$ChatRoomEnterResponse var4);

   void onLobbyDataUpdate(SteamID var1, SteamID var2, boolean var3);

   void onLobbyChatUpdate(SteamID var1, SteamID var2, SteamID var3, SteamMatchmaking$ChatMemberStateChange var4);

   void onLobbyChatMessage(SteamID var1, SteamID var2, SteamMatchmaking$ChatEntryType var3, int var4);

   void onLobbyGameCreated(SteamID var1, SteamID var2, int var3, short var4);

   void onLobbyMatchList(int var1);

   void onLobbyKicked(SteamID var1, SteamID var2, boolean var3);

   void onLobbyCreated(SteamResult var1, SteamID var2);

   void onFavoritesListAccountsUpdated(SteamResult var1);
}
