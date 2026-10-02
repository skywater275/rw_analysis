package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamFriends$PersonaChange;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

class SteamFriendsCallbackAdapter extends SteamCallbackAdapter {

   private static final SteamFriends$PersonaChange[] personaChangeValues = SteamFriends$PersonaChange.values();


   SteamFriendsCallbackAdapter(SteamFriendsCallback var1) {
      super(var1);
   }

   void onSetPersonaNameResponse(boolean var1, boolean var2, int var3) {
      ((SteamFriendsCallback)this.callback).onSetPersonaNameResponse(var1, var2, SteamResult.byValue(var3));
   }

   void onPersonaStateChange(long var1, int var3) {
      SteamID var4 = new SteamID(var1);
      SteamFriends$PersonaChange[] var5 = personaChangeValues;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         SteamFriends$PersonaChange var8 = var5[var7];
         if(SteamFriends$PersonaChange.isSet(var8, var3)) {
            ((SteamFriendsCallback)this.callback).onPersonaStateChange(var4, var8);
         }
      }

   }

   void onGameOverlayActivated(boolean var1) {
      ((SteamFriendsCallback)this.callback).onGameOverlayActivated(var1);
   }

   void onGameLobbyJoinRequested(long var1, long var3) {
      ((SteamFriendsCallback)this.callback).onGameLobbyJoinRequested(new SteamID(var1), new SteamID(var3));
   }

   void onAvatarImageLoaded(long var1, int var3, int var4, int var5) {
      ((SteamFriendsCallback)this.callback).onAvatarImageLoaded(new SteamID(var1), var3, var4, var5);
   }

   void onFriendRichPresenceUpdate(long var1, int var3) {
      ((SteamFriendsCallback)this.callback).onFriendRichPresenceUpdate(new SteamID(var1), var3);
   }

   void onGameRichPresenceJoinRequested(long var1, String var3) {
      ((SteamFriendsCallback)this.callback).onGameRichPresenceJoinRequested(new SteamID(var1), var3);
   }

}
