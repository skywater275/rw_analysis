package com.codedisaster.steamworks;


public enum SteamGameServer$DenyReason {

   Invalid("Invalid", 0),
   InvalidVersion("InvalidVersion", 1),
   Generic("Generic", 2),
   NotLoggedOn("NotLoggedOn", 3),
   NoLicense("NoLicense", 4),
   Cheater("Cheater", 5),
   LoggedInElseWhere("LoggedInElseWhere", 6),
   UnknownText("UnknownText", 7),
   IncompatibleAnticheat("IncompatibleAnticheat", 8),
   MemoryCorruption("MemoryCorruption", 9),
   IncompatibleSoftware("IncompatibleSoftware", 10),
   SteamConnectionLost("SteamConnectionLost", 11),
   SteamConnectionError("SteamConnectionError", 12),
   SteamResponseTimedOut("SteamResponseTimedOut", 13),
   SteamValidationStalled("SteamValidationStalled", 14),
   SteamOwnerLeftGuestUser("SteamOwnerLeftGuestUser", 15);
   private static final SteamGameServer$DenyReason[] values = values();
   // $FF: synthetic field
   private static final SteamGameServer$DenyReason[] $VALUES = new SteamGameServer$DenyReason[]{Invalid, InvalidVersion, Generic, NotLoggedOn, NoLicense, Cheater, LoggedInElseWhere, UnknownText, IncompatibleAnticheat, MemoryCorruption, IncompatibleSoftware, SteamConnectionLost, SteamConnectionError, SteamResponseTimedOut, SteamValidationStalled, SteamOwnerLeftGuestUser};


   private SteamGameServer$DenyReason(String var1, int var2) {}

   static SteamGameServer$DenyReason byOrdinal(int var0) {
      return values[var0];
   }

}
