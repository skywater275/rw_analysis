package com.codedisaster.steamworks;


public enum SteamFriends$OverlayToUserDialog {

   SteamID("SteamID", 0, "steamid"),
   Chat("Chat", 1, "chat"),
   JoinTrade("JoinTrade", 2, "jointrade"),
   Stats("Stats", 3, "stats"),
   Achievements("Achievements", 4, "achievements"),
   FriendAdd("FriendAdd", 5, "friendadd"),
   FriendRemove("FriendRemove", 6, "friendremove"),
   FriendRequestAccept("FriendRequestAccept", 7, "friendrequestaccept"),
   FriendRequestIgnore("FriendRequestIgnore", 8, "friendrequestignore");
   private final String id;
   // $FF: synthetic field
   private static final SteamFriends$OverlayToUserDialog[] $VALUES = new SteamFriends$OverlayToUserDialog[]{SteamID, Chat, JoinTrade, Stats, Achievements, FriendAdd, FriendRemove, FriendRequestAccept, FriendRequestIgnore};


   private SteamFriends$OverlayToUserDialog(String var1, int var2, String var3) {
      this.id = var3;
   }

   // $FF: synthetic method
   static String access$200(SteamFriends$OverlayToUserDialog var0) {
      return var0.id;
   }

}
