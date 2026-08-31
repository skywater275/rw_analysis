package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamGameServerAPI;
import com.codedisaster.steamworks.SteamGameServerStatsCallback;
import com.codedisaster.steamworks.SteamGameServerStatsCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;

public class SteamGameServerStats extends SteamInterface {

   public SteamGameServerStats(SteamGameServerStatsCallback var1) {
      super(SteamGameServerAPI.getSteamGameServerStatsPointer(), createCallback(new SteamGameServerStatsCallbackAdapter(var1)));
   }

   public SteamAPICall requestUserStats(SteamID var1) {
      return new SteamAPICall(requestUserStats(this.pointer, var1.handle));
   }

   public int getUserStatI(SteamID var1, String var2, int var3) {
      int[] var4 = new int[1];
      return getUserStat(this.pointer, var1.handle, var2, var4)?var4[0]:var3;
   }

   public float getUserStatF(SteamID var1, String var2, float var3) {
      float[] var4 = new float[1];
      return getUserStat(this.pointer, var1.handle, var2, var4)?var4[0]:var3;
   }

   public boolean getUserAchievement(SteamID var1, String var2, boolean var3) {
      boolean[] var4 = new boolean[1];
      return getUserAchievement(this.pointer, var1.handle, var2, var4)?var4[0]:var3;
   }

   public boolean setUserStatI(SteamID var1, String var2, int var3) {
      return setUserStat(this.pointer, var1.handle, var2, var3);
   }

   public boolean setUserStatF(SteamID var1, String var2, float var3) {
      return setUserStat(this.pointer, var1.handle, var2, var3);
   }

   public boolean updateUserAvgRateStat(SteamID var1, String var2, float var3, double var4) {
      return updateUserAvgRateStat(this.pointer, var1.handle, var2, var3, var4);
   }

   public boolean setUserAchievement(SteamID var1, String var2) {
      return setUserAchievement(this.pointer, var1.handle, var2);
   }

   public boolean clearUserAchievement(SteamID var1, String var2) {
      return clearUserAchievement(this.pointer, var1.handle, var2);
   }

   public SteamAPICall storeUserStats(SteamID var1) {
      return new SteamAPICall(storeUserStats(this.pointer, var1.handle));
   }

   private static native long createCallback(SteamGameServerStatsCallbackAdapter var0);

   private static native long requestUserStats(long var0, long var2);

   private static native boolean getUserStat(long var0, long var2, String var4, int[] var5);

   private static native boolean getUserStat(long var0, long var2, String var4, float[] var5);

   private static native boolean getUserAchievement(long var0, long var2, String var4, boolean[] var5);

   private static native boolean setUserStat(long var0, long var2, String var4, int var5);

   private static native boolean setUserStat(long var0, long var2, String var4, float var5);

   private static native boolean updateUserAvgRateStat(long var0, long var2, String var4, float var5, double var6);

   private static native boolean setUserAchievement(long var0, long var2, String var4);

   private static native boolean clearUserAchievement(long var0, long var2, String var4);

   private static native long storeUserStats(long var0, long var2);
}
