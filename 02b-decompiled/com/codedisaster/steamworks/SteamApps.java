package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;

public class SteamApps extends SteamInterface {

   public SteamApps() {
      super(SteamAPI.getSteamAppsPointer());
   }

   public boolean isSubscribedApp(int var1) {
      return isSubscribedApp(this.pointer, var1);
   }

   public String getCurrentGameLanguage() {
      return getCurrentGameLanguage(this.pointer);
   }

   public String getAvailableGameLanguages() {
      return getAvailableGameLanguages(this.pointer);
   }

   public SteamID getAppOwner() {
      return new SteamID(getAppOwner(this.pointer));
   }

   public int getAppBuildId() {
      return getAppBuildId(this.pointer);
   }

   private static native boolean isSubscribedApp(long var0, int var2);

   private static native String getCurrentGameLanguage(long var0);

   private static native String getAvailableGameLanguages(long var0);

   private static native long getAppOwner(long var0);

   private static native int getAppBuildId(long var0);
}
