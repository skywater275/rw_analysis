package com.codedisaster.steamworks;


public enum SteamMatchmaking$LobbyComparison {

   EqualToOrLessThan("EqualToOrLessThan", 0, -2),
   LessThan("LessThan", 1, -1),
   Equal("Equal", 2, 0),
   GreaterThan("GreaterThan", 3, 1),
   EqualToOrGreaterThan("EqualToOrGreaterThan", 4, 2),
   NotEqual("NotEqual", 5, 3);
   private final int value;
   // $FF: synthetic field
   private static final SteamMatchmaking$LobbyComparison[] $VALUES = new SteamMatchmaking$LobbyComparison[]{EqualToOrLessThan, LessThan, Equal, GreaterThan, EqualToOrGreaterThan, NotEqual};


   private SteamMatchmaking$LobbyComparison(String var1, int var2, int var3) {
      this.value = var3;
   }

   // $FF: synthetic method
   static int access$000(SteamMatchmaking$LobbyComparison var0) {
      return var0.value;
   }

}
