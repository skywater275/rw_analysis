package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamGameServerAPI$ServerMode;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamSharedLibraryLoader;

public class SteamGameServerAPI {

   private static boolean isRunning = false;


   public static boolean init(int var0, short var1, short var2, short var3, SteamGameServerAPI$ServerMode var4, String var5) {
      return init((String)null, var0, var1, var2, var3, var4, var5);
   }

   public static boolean init(String var0, int var1, short var2, short var3, short var4, SteamGameServerAPI$ServerMode var5, String var6) {
      SteamSharedLibraryLoader.loadLibraries(var0);
      isRunning = nativeInit(var1, var2, var3, var4, var5.ordinal(), var6);
      return isRunning;
   }

   public static void shutdown() {
      isRunning = false;
      nativeShutdown();
   }

   public static SteamID getSteamID() {
      return new SteamID(nativeGetSteamID());
   }

   private static native boolean nativeInit(int var0, short var1, short var2, short var3, int var4, String var5);

   private static native void nativeShutdown();

   public static native void runCallbacks();

   public static native boolean isSecure();

   private static native long nativeGetSteamID();

   static native long getSteamGameServerPointer();

   static native long getSteamGameServerNetworkingPointer();

   static native long getSteamGameServerStatsPointer();

   static native long getSteamGameServerHTTPPointer();

}
