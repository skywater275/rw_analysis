package com.codedisaster.steamworks;


public abstract class SteamNativeHandle {

   long handle;


   SteamNativeHandle(long var1) {
      this.handle = var1;
   }

   public static long getNativeHandle(SteamNativeHandle var0) {
      return var0.handle;
   }

   public int hashCode() {
      return Long.valueOf(this.handle).hashCode();
   }

   public boolean equals(Object var1) {
      return var1 instanceof SteamNativeHandle?this.handle == ((SteamNativeHandle)var1).handle:false;
   }

   public String toString() {
      return Long.toHexString(this.handle);
   }
}
