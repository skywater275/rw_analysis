package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNativeHandle;

public class SteamUGCUpdateHandle extends SteamNativeHandle {

   SteamUGCUpdateHandle(long var1) {
      super(var1);
   }

   public boolean isValid() {
      return this.handle != -1L;
   }
}
