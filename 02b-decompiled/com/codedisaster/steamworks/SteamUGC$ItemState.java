package com.codedisaster.steamworks;

import java.util.Collection;
import java.util.EnumSet;

public enum SteamUGC$ItemState {

   None("None", 0, 0),
   Subscribed("Subscribed", 1, 1),
   LegacyItem("LegacyItem", 2, 2),
   Installed("Installed", 3, 4),
   NeedsUpdate("NeedsUpdate", 4, 8),
   Downloading("Downloading", 5, 16),
   DownloadPending("DownloadPending", 6, 32);
   private final int bits;
   private static final SteamUGC$ItemState[] values = values();
   // $FF: synthetic field
   private static final SteamUGC$ItemState[] $VALUES = new SteamUGC$ItemState[]{None, Subscribed, LegacyItem, Installed, NeedsUpdate, Downloading, DownloadPending};


   private SteamUGC$ItemState(String var1, int var2, int var3) {
      this.bits = var3;
   }

   static Collection fromBits(int var0) {
      EnumSet var1 = EnumSet.noneOf(SteamUGC$ItemState.class);
      SteamUGC$ItemState[] var2 = values;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         SteamUGC$ItemState var5 = var2[var4];
         if((var0 & var5.bits) == var5.bits) {
            var1.add(var5);
         }
      }

      return var1;
   }

}
