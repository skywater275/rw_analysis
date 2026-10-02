package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamMatchmakingCallback;
import com.codedisaster.steamworks.SteamResult;

class SteamMatchmakingCallbackAdapter extends SteamCallbackAdapter {

   private static final SteamMatchmaking$ChatMemberStateChange[] stateChangeValues = SteamMatchmaking$ChatMemberStateChange.values();


   SteamMatchmakingCallbackAdapter(SteamMatchmakingCallback var1) {
      super(var1);
   }

   void onFavoritesListChanged(int var1, int var2, int var3, int var4, int var5, boolean var6, int var7) {
      ((SteamMatchmakingCallback)this.callback).onFavoritesListChanged(var1, var2, var3, var4, var5, var6, var7);
   }

   void onLobbyInvite(long var1, long var3, long var5) {
      ((SteamMatchmakingCallback)this.callback).onLobbyInvite(new SteamID(var1), new SteamID(var3), var5);
   }

   void onLobbyEnter(long var1, int var3, boolean var4, int var5) {
      ((SteamMatchmakingCallback)this.callback).onLobbyEnter(new SteamID(var1), var3, var4, SteamMatchmaking$ChatRoomEnterResponse.byCode(var5));
   }

   void onLobbyDataUpdate(long var1, long var3, boolean var5) {
      ((SteamMatchmakingCallback)this.callback).onLobbyDataUpdate(new SteamID(var1), new SteamID(var3), var5);
   }

   void onLobbyChatUpdate(long var1, long var3, long var5, int var7) {
      SteamID var8 = new SteamID(var1);
      SteamID var9 = new SteamID(var3);
      SteamID var10 = new SteamID(var5);
      SteamMatchmaking$ChatMemberStateChange[] var11 = stateChangeValues;
      int var12 = var11.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         SteamMatchmaking$ChatMemberStateChange var14 = var11[var13];
         if(SteamMatchmaking$ChatMemberStateChange.isSet(var14, var7)) {
            ((SteamMatchmakingCallback)this.callback).onLobbyChatUpdate(var8, var9, var10, var14);
         }
      }

   }

   void onLobbyChatMessage(long var1, long var3, int var5, int var6) {
      ((SteamMatchmakingCallback)this.callback).onLobbyChatMessage(new SteamID(var1), new SteamID(var3), SteamMatchmaking$ChatEntryType.byCode(var5), var6);
   }

   void onLobbyGameCreated(long var1, long var3, int var5, short var6) {
      ((SteamMatchmakingCallback)this.callback).onLobbyGameCreated(new SteamID(var1), new SteamID(var3), var5, var6);
   }

   void onLobbyMatchList(int var1) {
      ((SteamMatchmakingCallback)this.callback).onLobbyMatchList(var1);
   }

   void onLobbyKicked(long var1, long var3, boolean var5) {
      ((SteamMatchmakingCallback)this.callback).onLobbyKicked(new SteamID(var1), new SteamID(var3), var5);
   }

   void onLobbyCreated(int var1, long var2) {
      ((SteamMatchmakingCallback)this.callback).onLobbyCreated(SteamResult.byValue(var1), new SteamID(var2));
   }

   void onFavoritesListAccountsUpdated(int var1) {
      ((SteamMatchmakingCallback)this.callback).onFavoritesListAccountsUpdated(SteamResult.byValue(var1));
   }

}
