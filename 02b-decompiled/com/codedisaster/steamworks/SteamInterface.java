package com.codedisaster.steamworks;


abstract class SteamInterface {

   protected final long pointer;
   protected long callback;


   SteamInterface(long var1) {
      this(var1, 0L);
   }

   SteamInterface(long var1, long var3) {
      if(var1 == 0L) {
         throw new RuntimeException("Steam interface created with null pointer. Always check result of SteamAPI.init(), or with SteamAPI.isSteamRunning()!");
      } else {
         this.pointer = var1;
         this.callback = var3;
      }
   }

   void setCallback(long var1) {
      this.callback = var1;
   }

   public void dispose() {
      deleteCallback(this.callback);
   }

   protected static native void deleteCallback(long var0);
}
