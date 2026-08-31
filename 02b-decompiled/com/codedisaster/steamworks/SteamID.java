package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNativeHandle;

public class SteamID extends SteamNativeHandle {

   private static final long InvalidSteamID = getInvalidSteamID();


   public SteamID() {
      super(InvalidSteamID);
   }

   public SteamID(SteamID var1) {
      super(var1.handle);
   }

   SteamID(long var1) {
      super(var1);
   }

   public boolean isValid() {
      return isValid(this.handle);
   }

   public int getAccountID() {
      return (int)(this.handle % 4294967296L);
   }

   public static SteamID createFromNativeHandle(long var0) {
      return new SteamID(var0);
   }

   private static native boolean isValid(long var0);

   private static native long getInvalidSteamID();

}
