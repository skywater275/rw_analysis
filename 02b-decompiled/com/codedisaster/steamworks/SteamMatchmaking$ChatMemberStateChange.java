package com.codedisaster.steamworks;


public enum SteamMatchmaking$ChatMemberStateChange {

   Entered("Entered", 0, 1),
   Left("Left", 1, 2),
   Disconnected("Disconnected", 2, 4),
   Kicked("Kicked", 3, 8),
   Banned("Banned", 4, 16);
   private final int bits;
   // $FF: synthetic field
   private static final SteamMatchmaking$ChatMemberStateChange[] $VALUES = new SteamMatchmaking$ChatMemberStateChange[]{Entered, Left, Disconnected, Kicked, Banned};


   private SteamMatchmaking$ChatMemberStateChange(String var1, int var2, int var3) {
      this.bits = var3;
   }

   static boolean isSet(SteamMatchmaking$ChatMemberStateChange var0, int var1) {
      return (var0.bits & var1) == var0.bits;
   }

}
