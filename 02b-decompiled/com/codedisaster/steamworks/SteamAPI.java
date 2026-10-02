package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamSharedLibraryLoader;
import java.io.PrintStream;

public class SteamAPI {

   private static boolean isRunning = false;


   public static boolean init() {
      return init((String)null);
   }

   public static boolean init(String var0) {
      SteamSharedLibraryLoader.loadLibraries(var0);
      isRunning = nativeInit();
      return isRunning;
   }

   public static void shutdown() {
      isRunning = false;
      nativeShutdown();
   }

   public static boolean isSteamRunning() {
      return isSteamRunning(false);
   }

   public static boolean isSteamRunning(boolean var0) {
      return isRunning && (!var0 || isSteamRunningNative());
   }

   public static void printDebugInfo(PrintStream var0) {
      if(SteamSharedLibraryLoader.alreadyLoaded) {
         var0.println("  shared libraries loaded from: " + SteamSharedLibraryLoader.librarySystemPath);
      } else {
         var0.println("  shared libraries not loaded");
      }

      var0.println("  Steam API initialized: " + isRunning);
      var0.println("  Steam client active: " + isSteamRunning());
   }

   public static native boolean restartAppIfNecessary(int var0);

   public static native void releaseCurrentThreadMemory();

   private static native boolean nativeInit();

   private static native void nativeShutdown();

   public static native void runCallbacks();

   private static native boolean isSteamRunningNative();

   static native long getSteamAppsPointer();

   static native long getSteamControllerPointer();

   static native long getSteamFriendsPointer();

   static native long getSteamHTTPPointer();

   static native long getSteamMatchmakingPointer();

   static native long getSteamNetworkingPointer();

   static native long getSteamRemoteStoragePointer();

   static native long getSteamUGCPointer();

   static native long getSteamUserPointer();

   static native long getSteamUserStatsPointer();

   static native long getSteamUtilsPointer();

}
