/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamSharedLibraryLoader;
import java.io.PrintStream;

public class SteamAPI {
    private static boolean isRunning = false;

    public static boolean init() {
        return SteamAPI.init(null);
    }

    public static boolean init(String string) {
        SteamSharedLibraryLoader.loadLibraries(string);
        isRunning = SteamAPI.nativeInit();
        return isRunning;
    }

    public static void shutdown() {
        isRunning = false;
        SteamAPI.nativeShutdown();
    }

    public static boolean isSteamRunning() {
        return SteamAPI.isSteamRunning(false);
    }

    public static boolean isSteamRunning(boolean bl) {
        return isRunning && (!bl || SteamAPI.isSteamRunningNative());
    }

    public static void printDebugInfo(PrintStream printStream) {
        if (SteamSharedLibraryLoader.alreadyLoaded) {
            printStream.println("  shared libraries loaded from: " + SteamSharedLibraryLoader.librarySystemPath);
        } else {
            printStream.println("  shared libraries not loaded");
        }
        printStream.println("  Steam API initialized: " + isRunning);
        printStream.println("  Steam client active: " + SteamAPI.isSteamRunning());
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
