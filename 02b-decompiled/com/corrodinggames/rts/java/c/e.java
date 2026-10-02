package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;
import com.codedisaster.steamworks.SteamMatchmaking$ChatMemberStateChange;
import com.codedisaster.steamworks.SteamMatchmaking$ChatRoomEnterResponse;
import com.codedisaster.steamworks.SteamMatchmakingCallback;
import com.codedisaster.steamworks.SteamResult;
import com.corrodinggames.rts.java.c.a;
import com.corrodinggames.rts.java.c.b;

public class e implements SteamMatchmakingCallback {

   b a;


   public e(b var1) {
      this.a = var1;
   }

   public void onFavoritesListChanged(int var1, int var2, int var3, int var4, int var5, boolean var6, int var7) {
      com.corrodinggames.rts.gameFramework.l.e("onFavoritesListChanged");
   }

   public void onLobbyInvite(SteamID var1, SteamID var2, long var3) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyInvite");
      a var5 = new a(this.a, var1, var2, var3);
      var5.a();
   }

   public void onLobbyEnter(SteamID var1, int var2, boolean var3, SteamMatchmaking$ChatRoomEnterResponse var4) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyEnter");
      if(var3) {
         com.corrodinggames.rts.gameFramework.l.e("onLobbyEnter blocked: " + var4);
      }

      this.a.c(var1);
   }

   public void onLobbyDataUpdate(SteamID var1, SteamID var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyDataUpdate success: " + var3);
   }

   public void onLobbyChatUpdate(SteamID var1, SteamID var2, SteamID var3, SteamMatchmaking$ChatMemberStateChange var4) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyChatUpdate steamIDUserChanged: " + var2 + " stateChange:" + var4);
   }

   public void onLobbyChatMessage(SteamID var1, SteamID var2, SteamMatchmaking$ChatEntryType var3, int var4) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyChatMessage");
   }

   public void onLobbyGameCreated(SteamID var1, SteamID var2, int var3, short var4) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyGameCreated");
      this.a.a(var1);
   }

   public void onLobbyMatchList(int var1) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyMatchList");
   }

   public void onLobbyKicked(SteamID var1, SteamID var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyKicked");
   }

   public void onLobbyCreated(SteamResult var1, SteamID var2) {
      com.corrodinggames.rts.gameFramework.l.e("onLobbyCreated");
      this.a.a(var2);
   }

   public void onFavoritesListAccountsUpdated(SteamResult var1) {
      com.corrodinggames.rts.gameFramework.l.e("onFavoritesListAccountsUpdated");
   }
}
