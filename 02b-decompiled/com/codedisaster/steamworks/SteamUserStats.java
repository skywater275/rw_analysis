package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardEntry;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamUserStats$LeaderboardDataRequest;
import com.codedisaster.steamworks.SteamUserStats$LeaderboardDisplayType;
import com.codedisaster.steamworks.SteamUserStats$LeaderboardSortMethod;
import com.codedisaster.steamworks.SteamUserStats$LeaderboardUploadScoreMethod;
import com.codedisaster.steamworks.SteamUserStatsCallback;
import com.codedisaster.steamworks.SteamUserStatsCallbackAdapter;

public class SteamUserStats extends SteamInterface {

   public SteamUserStats(SteamUserStatsCallback var1) {
      super(SteamAPI.getSteamUserStatsPointer(), createCallback(new SteamUserStatsCallbackAdapter(var1)));
   }

   public boolean requestCurrentStats() {
      return requestCurrentStats(this.pointer);
   }

   public int getStatI(String var1, int var2) {
      int[] var3 = new int[1];
      return getStat(this.pointer, var1, var3)?var3[0]:var2;
   }

   public boolean setStatI(String var1, int var2) {
      return setStat(this.pointer, var1, var2);
   }

   public float getStatF(String var1, float var2) {
      float[] var3 = new float[1];
      return getStat(this.pointer, var1, var3)?var3[0]:var2;
   }

   public boolean setStatF(String var1, float var2) {
      return setStat(this.pointer, var1, var2);
   }

   public boolean isAchieved(String var1, boolean var2) {
      boolean[] var3 = new boolean[1];
      return getAchievement(this.pointer, var1, var3)?var3[0]:var2;
   }

   public boolean setAchievement(String var1) {
      return setAchievement(this.pointer, var1);
   }

   public boolean clearAchievement(String var1) {
      return clearAchievement(this.pointer, var1);
   }

   public boolean storeStats() {
      return storeStats(this.pointer);
   }

   public boolean indicateAchievementProgress(String var1, int var2, int var3) {
      return indicateAchievementProgress(this.pointer, var1, var2, var3);
   }

   public int getNumAchievements() {
      return getNumAchievements(this.pointer);
   }

   public String getAchievementName(int var1) {
      return getAchievementName(this.pointer, var1);
   }

   public boolean resetAllStats(boolean var1) {
      return resetAllStats(this.pointer, var1);
   }

   public SteamAPICall findOrCreateLeaderboard(String var1, SteamUserStats$LeaderboardSortMethod var2, SteamUserStats$LeaderboardDisplayType var3) {
      return new SteamAPICall(findOrCreateLeaderboard(this.pointer, this.callback, var1, var2.ordinal(), var3.ordinal()));
   }

   public SteamAPICall findLeaderboard(String var1) {
      return new SteamAPICall(findLeaderboard(this.pointer, this.callback, var1));
   }

   public String getLeaderboardName(SteamLeaderboardHandle var1) {
      return getLeaderboardName(this.pointer, var1.handle);
   }

   public int getLeaderboardEntryCount(SteamLeaderboardHandle var1) {
      return getLeaderboardEntryCount(this.pointer, var1.handle);
   }

   public SteamUserStats$LeaderboardSortMethod getLeaderboardSortMethod(SteamLeaderboardHandle var1) {
      return SteamUserStats$LeaderboardSortMethod.values()[getLeaderboardSortMethod(this.pointer, var1.handle)];
   }

   public SteamUserStats$LeaderboardDisplayType getLeaderboardDisplayType(SteamLeaderboardHandle var1) {
      return SteamUserStats$LeaderboardDisplayType.values()[getLeaderboardDisplayType(this.pointer, var1.handle)];
   }

   public SteamAPICall downloadLeaderboardEntries(SteamLeaderboardHandle var1, SteamUserStats$LeaderboardDataRequest var2, int var3, int var4) {
      return new SteamAPICall(downloadLeaderboardEntries(this.pointer, this.callback, var1.handle, var2.ordinal(), var3, var4));
   }

   public boolean getDownloadedLeaderboardEntry(SteamLeaderboardEntriesHandle var1, int var2, SteamLeaderboardEntry var3, int[] var4) {
      return var4 == null?getDownloadedLeaderboardEntry(this.pointer, var1.handle, var2, var3):getDownloadedLeaderboardEntry(this.pointer, var1.handle, var2, var3, var4, var4.length);
   }

   public SteamAPICall uploadLeaderboardScore(SteamLeaderboardHandle var1, SteamUserStats$LeaderboardUploadScoreMethod var2, int var3, int[] var4) {
      return new SteamAPICall(var4 == null?uploadLeaderboardScore(this.pointer, this.callback, var1.handle, var2.ordinal(), var3):uploadLeaderboardScore(this.pointer, this.callback, var1.handle, var2.ordinal(), var3, var4, var4.length));
   }

   public SteamAPICall requestGlobalStats(int var1) {
      return new SteamAPICall(requestGlobalStats(this.pointer, this.callback, var1));
   }

   public long getGlobalStat(String var1, long var2) {
      long[] var4 = new long[1];
      return getGlobalStat(this.pointer, var1, var4)?var4[0]:var2;
   }

   public double getGlobalStat(String var1, double var2) {
      double[] var4 = new double[1];
      return getGlobalStat(this.pointer, var1, var4)?var4[0]:var2;
   }

   public int getGlobalStatHistory(String var1, long[] var2) {
      return getGlobalStatHistory(this.pointer, var1, var2, var2.length);
   }

   public int getGlobalStatHistory(String var1, double[] var2) {
      return getGlobalStatHistory(this.pointer, var1, var2, var2.length);
   }

   private static native long createCallback(SteamUserStatsCallbackAdapter var0);

   private static native boolean requestCurrentStats(long var0);

   private static native boolean getStat(long var0, String var2, int[] var3);

   private static native boolean setStat(long var0, String var2, int var3);

   private static native boolean getStat(long var0, String var2, float[] var3);

   private static native boolean setStat(long var0, String var2, float var3);

   private static native boolean getAchievement(long var0, String var2, boolean[] var3);

   private static native boolean setAchievement(long var0, String var2);

   private static native boolean clearAchievement(long var0, String var2);

   private static native boolean storeStats(long var0);

   private static native boolean indicateAchievementProgress(long var0, String var2, int var3, int var4);

   private static native int getNumAchievements(long var0);

   private static native String getAchievementName(long var0, int var2);

   private static native boolean resetAllStats(long var0, boolean var2);

   private static native long findOrCreateLeaderboard(long var0, long var2, String var4, int var5, int var6);

   private static native long findLeaderboard(long var0, long var2, String var4);

   private static native String getLeaderboardName(long var0, long var2);

   private static native int getLeaderboardEntryCount(long var0, long var2);

   private static native int getLeaderboardSortMethod(long var0, long var2);

   private static native int getLeaderboardDisplayType(long var0, long var2);

   private static native long downloadLeaderboardEntries(long var0, long var2, long var4, int var6, int var7, int var8);

   private static native boolean getDownloadedLeaderboardEntry(long var0, long var2, int var4, SteamLeaderboardEntry var5, int[] var6, int var7);

   private static native boolean getDownloadedLeaderboardEntry(long var0, long var2, int var4, SteamLeaderboardEntry var5);

   private static native long uploadLeaderboardScore(long var0, long var2, long var4, int var6, int var7, int[] var8, int var9);

   private static native long uploadLeaderboardScore(long var0, long var2, long var4, int var6, int var7);

   private static native long requestGlobalStats(long var0, long var2, int var4);

   private static native boolean getGlobalStat(long var0, String var2, long[] var3);

   private static native boolean getGlobalStat(long var0, String var2, double[] var3);

   private static native int getGlobalStatHistory(long var0, String var2, long[] var3, int var4);

   private static native int getGlobalStatHistory(long var0, String var2, double[] var3, int var4);
}
