package com.codedisaster.steamworks;


public enum SteamNetworking$P2PSessionError {

   None("None", 0),
   NotRunningApp("NotRunningApp", 1),
   NoRightsToApp("NoRightsToApp", 2),
   DestinationNotLoggedIn("DestinationNotLoggedIn", 3),
   Timeout("Timeout", 4);
   private static final SteamNetworking$P2PSessionError[] values = values();
   // $FF: synthetic field
   private static final SteamNetworking$P2PSessionError[] $VALUES = new SteamNetworking$P2PSessionError[]{None, NotRunningApp, NoRightsToApp, DestinationNotLoggedIn, Timeout};


   private SteamNetworking$P2PSessionError(String var1, int var2) {}

   public static SteamNetworking$P2PSessionError byOrdinal(int var0) {
      return values[var0];
   }

}
