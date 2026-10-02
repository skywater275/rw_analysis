package com.codedisaster.steamworks;


public enum SteamUniverse {

   Invalid("Invalid", 0, 0),
   Public("Public", 1, 1),
   Beta("Beta", 2, 2),
   Internal("Internal", 3, 3),
   Dev("Dev", 4, 4);
   private final int value;
   private static final SteamUniverse[] values = values();
   // $FF: synthetic field
   private static final SteamUniverse[] $VALUES = new SteamUniverse[]{Invalid, Public, Beta, Internal, Dev};


   private SteamUniverse(String var1, int var2, int var3) {
      this.value = var3;
   }

   static SteamUniverse byValue(int var0) {
      SteamUniverse[] var1 = values;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SteamUniverse var4 = var1[var3];
         if(var4.value == var0) {
            return var4;
         }
      }

      return Invalid;
   }

}
