package com.codedisaster.steamworks;


public enum SteamGameServerAPI$ServerMode {

   Invalid("Invalid", 0),
   NoAuthentication("NoAuthentication", 1),
   Authentication("Authentication", 2),
   AuthenticationAndSecure("AuthenticationAndSecure", 3);
   // $FF: synthetic field
   private static final SteamGameServerAPI$ServerMode[] $VALUES = new SteamGameServerAPI$ServerMode[]{Invalid, NoAuthentication, Authentication, AuthenticationAndSecure};


   private SteamGameServerAPI$ServerMode(String var1, int var2) {}

}
