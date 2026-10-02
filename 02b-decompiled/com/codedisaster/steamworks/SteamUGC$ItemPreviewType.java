package com.codedisaster.steamworks;


public enum SteamUGC$ItemPreviewType {

   Image("Image", 0, 0),
   YouTubeVideo("YouTubeVideo", 1, 1),
   Sketchfab("Sketchfab", 2, 2),
   EnvironmentMap_HorizontalCross("EnvironmentMap_HorizontalCross", 3, 3),
   EnvironmentMap_LatLong("EnvironmentMap_LatLong", 4, 4),
   ReservedMax("ReservedMax", 5, 255),
   UnknownPreviewType_NotImplementedByAPI("UnknownPreviewType_NotImplementedByAPI", 6, -1);
   private final int value;
   private static final SteamUGC$ItemPreviewType[] values = values();
   // $FF: synthetic field
   private static final SteamUGC$ItemPreviewType[] $VALUES = new SteamUGC$ItemPreviewType[]{Image, YouTubeVideo, Sketchfab, EnvironmentMap_HorizontalCross, EnvironmentMap_LatLong, ReservedMax, UnknownPreviewType_NotImplementedByAPI};


   private SteamUGC$ItemPreviewType(String var1, int var2, int var3) {
      this.value = var3;
   }

   static SteamUGC$ItemPreviewType byValue(int var0) {
      SteamUGC$ItemPreviewType[] var1 = values;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SteamUGC$ItemPreviewType var4 = var1[var3];
         if(var4.value == var0) {
            return var4;
         }
      }

      return UnknownPreviewType_NotImplementedByAPI;
   }

}
