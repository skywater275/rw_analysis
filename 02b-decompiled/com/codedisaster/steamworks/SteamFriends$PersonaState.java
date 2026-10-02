package com.codedisaster.steamworks;


public enum SteamFriends$PersonaState {

   Offline("Offline", 0),
   Online("Online", 1),
   Busy("Busy", 2),
   Away("Away", 3),
   Snooze("Snooze", 4),
   LookingToTrade("LookingToTrade", 5),
   LookingToPlay("LookingToPlay", 6);
   private static final SteamFriends$PersonaState[] values = values();
   // $FF: synthetic field
   private static final SteamFriends$PersonaState[] $VALUES = new SteamFriends$PersonaState[]{Offline, Online, Busy, Away, Snooze, LookingToTrade, LookingToPlay};


   private SteamFriends$PersonaState(String var1, int var2) {}

   static SteamFriends$PersonaState byOrdinal(int var0) {
      return values[var0];
   }

}
