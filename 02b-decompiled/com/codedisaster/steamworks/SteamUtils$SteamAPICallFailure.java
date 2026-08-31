package com.codedisaster.steamworks;


public enum SteamUtils$SteamAPICallFailure {

   None("None", 0, -1),
   SteamGone("SteamGone", 1, 0),
   NetworkFailure("NetworkFailure", 2, 1),
   InvalidHandle("InvalidHandle", 3, 2),
   MismatchedCallback("MismatchedCallback", 4, 3);
   private final int code;
   private static final SteamUtils$SteamAPICallFailure[] values = values();
   // $FF: synthetic field
   private static final SteamUtils$SteamAPICallFailure[] $VALUES = new SteamUtils$SteamAPICallFailure[]{None, SteamGone, NetworkFailure, InvalidHandle, MismatchedCallback};


   private SteamUtils$SteamAPICallFailure(String var1, int var2, int var3) {
      this.code = var3;
   }

   static SteamUtils$SteamAPICallFailure byValue(int var0) {
      SteamUtils$SteamAPICallFailure[] var1 = values;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SteamUtils$SteamAPICallFailure var4 = var1[var3];
         if(var4.code == var0) {
            return var4;
         }
      }

      return None;
   }

}
