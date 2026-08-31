package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamFriends$PersonaChange;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

public interface SteamFriendsCallback {

   void onSetPersonaNameResponse(boolean var1, boolean var2, SteamResult var3);

   void onPersonaStateChange(SteamID var1, SteamFriends$PersonaChange var2);

   void onGameOverlayActivated(boolean var1);

   void onGameLobbyJoinRequested(SteamID var1, SteamID var2);

   void onAvatarImageLoaded(SteamID var1, int var2, int var3, int var4);

   void onFriendRichPresenceUpdate(SteamID var1, int var2);

   void onGameRichPresenceJoinRequested(SteamID var1, String var2);
}
