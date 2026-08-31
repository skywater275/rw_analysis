/*
 * Decompiled with CFR 0.152.
 */
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

public class SteamUserStats
extends SteamInterface {
    public SteamUserStats(SteamUserStatsCallback steamUserStatsCallback) {
        super(SteamAPI.getSteamUserStatsPointer(), SteamUserStats.createCallback(new SteamUserStatsCallbackAdapter(steamUserStatsCallback)));
    }

    public boolean requestCurrentStats() {
        return SteamUserStats.requestCurrentStats(this.pointer);
    }

    public int getStatI(String string, int n) {
        int[] nArray = new int[1];
        if (SteamUserStats.getStat(this.pointer, string, nArray)) {
            return nArray[0];
        }
        return n;
    }

    public boolean setStatI(String string, int n) {
        return SteamUserStats.setStat(this.pointer, string, n);
    }

    public float getStatF(String string, float f) {
        float[] fArray = new float[1];
        if (SteamUserStats.getStat(this.pointer, string, fArray)) {
            return fArray[0];
        }
        return f;
    }

    public boolean setStatF(String string, float f) {
        return SteamUserStats.setStat(this.pointer, string, f);
    }

    public boolean isAchieved(String string, boolean bl) {
        boolean[] blArray = new boolean[1];
        if (SteamUserStats.getAchievement(this.pointer, string, blArray)) {
            return blArray[0];
        }
        return bl;
    }

    public boolean setAchievement(String string) {
        return SteamUserStats.setAchievement(this.pointer, string);
    }

    public boolean clearAchievement(String string) {
        return SteamUserStats.clearAchievement(this.pointer, string);
    }

    public boolean storeStats() {
        return SteamUserStats.storeStats(this.pointer);
    }

    public boolean indicateAchievementProgress(String string, int n, int n2) {
        return SteamUserStats.indicateAchievementProgress(this.pointer, string, n, n2);
    }

    public int getNumAchievements() {
        return SteamUserStats.getNumAchievements(this.pointer);
    }

    public String getAchievementName(int n) {
        return SteamUserStats.getAchievementName(this.pointer, n);
    }

    public boolean resetAllStats(boolean bl) {
        return SteamUserStats.resetAllStats(this.pointer, bl);
    }

    public SteamAPICall findOrCreateLeaderboard(String string, SteamUserStats$LeaderboardSortMethod steamUserStats$LeaderboardSortMethod, SteamUserStats$LeaderboardDisplayType steamUserStats$LeaderboardDisplayType) {
        return new SteamAPICall(SteamUserStats.findOrCreateLeaderboard(this.pointer, this.callback, string, steamUserStats$LeaderboardSortMethod.ordinal(), steamUserStats$LeaderboardDisplayType.ordinal()));
    }

    public SteamAPICall findLeaderboard(String string) {
        return new SteamAPICall(SteamUserStats.findLeaderboard(this.pointer, this.callback, string));
    }

    public String getLeaderboardName(SteamLeaderboardHandle steamLeaderboardHandle) {
        return SteamUserStats.getLeaderboardName(this.pointer, steamLeaderboardHandle.handle);
    }

    public int getLeaderboardEntryCount(SteamLeaderboardHandle steamLeaderboardHandle) {
        return SteamUserStats.getLeaderboardEntryCount(this.pointer, steamLeaderboardHandle.handle);
    }

    public SteamUserStats$LeaderboardSortMethod getLeaderboardSortMethod(SteamLeaderboardHandle steamLeaderboardHandle) {
        return SteamUserStats$LeaderboardSortMethod.values()[SteamUserStats.getLeaderboardSortMethod(this.pointer, steamLeaderboardHandle.handle)];
    }

    public SteamUserStats$LeaderboardDisplayType getLeaderboardDisplayType(SteamLeaderboardHandle steamLeaderboardHandle) {
        return SteamUserStats$LeaderboardDisplayType.values()[SteamUserStats.getLeaderboardDisplayType(this.pointer, steamLeaderboardHandle.handle)];
    }

    public SteamAPICall downloadLeaderboardEntries(SteamLeaderboardHandle steamLeaderboardHandle, SteamUserStats$LeaderboardDataRequest steamUserStats$LeaderboardDataRequest, int n, int n2) {
        return new SteamAPICall(SteamUserStats.downloadLeaderboardEntries(this.pointer, this.callback, steamLeaderboardHandle.handle, steamUserStats$LeaderboardDataRequest.ordinal(), n, n2));
    }

    public boolean getDownloadedLeaderboardEntry(SteamLeaderboardEntriesHandle steamLeaderboardEntriesHandle, int n, SteamLeaderboardEntry steamLeaderboardEntry, int[] nArray) {
        return nArray == null ? SteamUserStats.getDownloadedLeaderboardEntry(this.pointer, steamLeaderboardEntriesHandle.handle, n, steamLeaderboardEntry) : SteamUserStats.getDownloadedLeaderboardEntry(this.pointer, steamLeaderboardEntriesHandle.handle, n, steamLeaderboardEntry, nArray, nArray.length);
    }

    public SteamAPICall uploadLeaderboardScore(SteamLeaderboardHandle steamLeaderboardHandle, SteamUserStats$LeaderboardUploadScoreMethod steamUserStats$LeaderboardUploadScoreMethod, int n, int[] nArray) {
        return new SteamAPICall(nArray == null ? SteamUserStats.uploadLeaderboardScore(this.pointer, this.callback, steamLeaderboardHandle.handle, steamUserStats$LeaderboardUploadScoreMethod.ordinal(), n) : SteamUserStats.uploadLeaderboardScore(this.pointer, this.callback, steamLeaderboardHandle.handle, steamUserStats$LeaderboardUploadScoreMethod.ordinal(), n, nArray, nArray.length));
    }

    public SteamAPICall requestGlobalStats(int n) {
        return new SteamAPICall(SteamUserStats.requestGlobalStats(this.pointer, this.callback, n));
    }

    public long getGlobalStat(String string, long l) {
        long[] lArray = new long[1];
        if (SteamUserStats.getGlobalStat(this.pointer, string, lArray)) {
            return lArray[0];
        }
        return l;
    }

    public double getGlobalStat(String string, double d) {
        double[] dArray = new double[1];
        if (SteamUserStats.getGlobalStat(this.pointer, string, dArray)) {
            return dArray[0];
        }
        return d;
    }

    public int getGlobalStatHistory(String string, long[] lArray) {
        return SteamUserStats.getGlobalStatHistory(this.pointer, string, lArray, lArray.length);
    }

    public int getGlobalStatHistory(String string, double[] dArray) {
        return SteamUserStats.getGlobalStatHistory(this.pointer, string, dArray, dArray.length);
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
