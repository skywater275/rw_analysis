package com.codedisaster.steamworks;


public enum SteamUGC$MatchingUGCType {

   Items("Items", 0, 0),
   ItemsMtx("ItemsMtx", 1, 1),
   ItemsReadyToUse("ItemsReadyToUse", 2, 2),
   Collections("Collections", 3, 3),
   Artwork("Artwork", 4, 4),
   Videos("Videos", 5, 5),
   Screenshots("Screenshots", 6, 6),
   AllGuides("AllGuides", 7, 7),
   WebGuides("WebGuides", 8, 8),
   IntegratedGuides("IntegratedGuides", 9, 9),
   UsableInGame("UsableInGame", 10, 10),
   ControllerBindings("ControllerBindings", 11, 11),
   GameManagedItems("GameManagedItems", 12, 12),
   All("All", 13, -1);
   private final int value;
   // $FF: synthetic field
   private static final SteamUGC$MatchingUGCType[] $VALUES = new SteamUGC$MatchingUGCType[]{Items, ItemsMtx, ItemsReadyToUse, Collections, Artwork, Videos, Screenshots, AllGuides, WebGuides, IntegratedGuides, UsableInGame, ControllerBindings, GameManagedItems, All};


   private SteamUGC$MatchingUGCType(String var1, int var2, int var3) {
      this.value = var3;
   }

   // $FF: synthetic method
   static int access$000(SteamUGC$MatchingUGCType var0) {
      return var0.value;
   }

}
