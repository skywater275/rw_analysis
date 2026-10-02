package com.codedisaster.steamworks;


public enum SteamUGC$ItemUpdateStatus {

   Invalid("Invalid", 0),
   PreparingConfig("PreparingConfig", 1),
   PreparingContent("PreparingContent", 2),
   UploadingContent("UploadingContent", 3),
   UploadingPreviewFile("UploadingPreviewFile", 4),
   CommittingChanges("CommittingChanges", 5);
   private static final SteamUGC$ItemUpdateStatus[] values = values();
   // $FF: synthetic field
   private static final SteamUGC$ItemUpdateStatus[] $VALUES = new SteamUGC$ItemUpdateStatus[]{Invalid, PreparingConfig, PreparingContent, UploadingContent, UploadingPreviewFile, CommittingChanges};


   private SteamUGC$ItemUpdateStatus(String var1, int var2) {}

   static SteamUGC$ItemUpdateStatus byOrdinal(int var0) {
      return values[var0];
   }

}
