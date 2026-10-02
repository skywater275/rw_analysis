package com.codedisaster.steamworks;


public enum SteamHTTP$HTTPMethod {

   Invalid("Invalid", 0),
   GET("GET", 1),
   HEAD("HEAD", 2),
   POST("POST", 3),
   PUT("PUT", 4),
   DELETE("DELETE", 5),
   OPTIONS("OPTIONS", 6);
   // $FF: synthetic field
   private static final SteamHTTP$HTTPMethod[] $VALUES = new SteamHTTP$HTTPMethod[]{Invalid, GET, HEAD, POST, PUT, DELETE, OPTIONS};


   private SteamHTTP$HTTPMethod(String var1, int var2) {}

}
