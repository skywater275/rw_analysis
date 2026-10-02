package com.codedisaster.steamworks;


public class SteamMatchMakingKeyValuePair {

   private String key;
   private String value;


   public SteamMatchMakingKeyValuePair() {}

   public SteamMatchMakingKeyValuePair(String var1, String var2) {
      this.key = var1;
      this.value = var2;
   }

   public String getKey() {
      return this.key;
   }

   public String getValue() {
      return this.value;
   }
}
