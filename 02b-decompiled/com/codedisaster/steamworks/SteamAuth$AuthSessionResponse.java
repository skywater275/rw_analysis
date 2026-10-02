package com.codedisaster.steamworks;


public enum SteamAuth$AuthSessionResponse {

   OK("OK", 0),
   UserNotConnectedToSteam("UserNotConnectedToSteam", 1),
   NoLicenseOrExpired("NoLicenseOrExpired", 2),
   VACBanned("VACBanned", 3),
   LoggedInElseWhere("LoggedInElseWhere", 4),
   VACCheckTimedOut("VACCheckTimedOut", 5),
   AuthTicketCanceled("AuthTicketCanceled", 6),
   AuthTicketInvalidAlreadyUsed("AuthTicketInvalidAlreadyUsed", 7),
   AuthTicketInvalid("AuthTicketInvalid", 8),
   PublisherIssuedBan("PublisherIssuedBan", 9);
   private static final SteamAuth$AuthSessionResponse[] values = values();
   // $FF: synthetic field
   private static final SteamAuth$AuthSessionResponse[] $VALUES = new SteamAuth$AuthSessionResponse[]{OK, UserNotConnectedToSteam, NoLicenseOrExpired, VACBanned, LoggedInElseWhere, VACCheckTimedOut, AuthTicketCanceled, AuthTicketInvalidAlreadyUsed, AuthTicketInvalid, PublisherIssuedBan};


   private SteamAuth$AuthSessionResponse(String var1, int var2) {}

   static SteamAuth$AuthSessionResponse byOrdinal(int var0) {
      return values[var0];
   }

}
