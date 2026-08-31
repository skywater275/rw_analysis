package com.codedisaster.steamworks;


public enum SteamAuth$BeginAuthSessionResult {

   OK("OK", 0),
   InvalidTicket("InvalidTicket", 1),
   DuplicateRequest("DuplicateRequest", 2),
   InvalidVersion("InvalidVersion", 3),
   GameMismatch("GameMismatch", 4),
   ExpiredTicket("ExpiredTicket", 5);
   private static final SteamAuth$BeginAuthSessionResult[] values = values();
   // $FF: synthetic field
   private static final SteamAuth$BeginAuthSessionResult[] $VALUES = new SteamAuth$BeginAuthSessionResult[]{OK, InvalidTicket, DuplicateRequest, InvalidVersion, GameMismatch, ExpiredTicket};


   private SteamAuth$BeginAuthSessionResult(String var1, int var2) {}

   static SteamAuth$BeginAuthSessionResult byOrdinal(int var0) {
      return values[var0];
   }

}
