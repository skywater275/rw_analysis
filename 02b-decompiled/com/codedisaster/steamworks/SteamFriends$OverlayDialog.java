package com.codedisaster.steamworks;


public enum SteamFriends$OverlayDialog {

   Friends("Friends", 0, "Friends"),
   Community("Community", 1, "Community"),
   Players("Players", 2, "Players"),
   Settings("Settings", 3, "Settings"),
   OfficialGameGroup("OfficialGameGroup", 4, "OfficialGameGroup"),
   Stats("Stats", 5, "Stats"),
   Achievements("Achievements", 6, "Achievements");
   private final String id;
   // $FF: synthetic field
   private static final SteamFriends$OverlayDialog[] $VALUES = new SteamFriends$OverlayDialog[]{Friends, Community, Players, Settings, OfficialGameGroup, Stats, Achievements};


   private SteamFriends$OverlayDialog(String var1, int var2, String var3) {
      this.id = var3;
   }

   // $FF: synthetic method
   static String access$100(SteamFriends$OverlayDialog var0) {
      return var0.id;
   }

}
