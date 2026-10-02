package com.codedisaster.steamworks;


public enum SteamMatchmaking$ChatRoomEnterResponse {

   Success("Success", 0, 1),
   DoesntExist("DoesntExist", 1, 2),
   NotAllowed("NotAllowed", 2, 3),
   Full("Full", 3, 4),
   Error("Error", 4, 5),
   Banned("Banned", 5, 6),
   Limited("Limited", 6, 7),
   ClanDisabled("ClanDisabled", 7, 8),
   CommunityBan("CommunityBan", 8, 9),
   MemberBlockedYou("MemberBlockedYou", 9, 10),
   YouBlockedMember("YouBlockedMember", 10, 11);
   private final int code;
   private static final SteamMatchmaking$ChatRoomEnterResponse[] values = values();
   // $FF: synthetic field
   private static final SteamMatchmaking$ChatRoomEnterResponse[] $VALUES = new SteamMatchmaking$ChatRoomEnterResponse[]{Success, DoesntExist, NotAllowed, Full, Error, Banned, Limited, ClanDisabled, CommunityBan, MemberBlockedYou, YouBlockedMember};


   private SteamMatchmaking$ChatRoomEnterResponse(String var1, int var2, int var3) {
      this.code = var3;
   }

   static SteamMatchmaking$ChatRoomEnterResponse byCode(int var0) {
      SteamMatchmaking$ChatRoomEnterResponse[] var1 = values;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SteamMatchmaking$ChatRoomEnterResponse var4 = var1[var3];
         if(var4.code == var0) {
            return var4;
         }
      }

      return Error;
   }

}
