/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamGameServerAPI$ServerMode;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamSharedLibraryLoader;

public class SteamGameServerAPI {
    private static boolean isRunning = false;

    public static boolean init(int n, short s, short s2, short s3, SteamGameServerAPI$ServerMode serverMode, String string) throws SteamException {
        return SteamGameServerAPI.init(null, n, s, s2, s3, serverMode, string);
    }

    public static boolean init(String string, int n, short s, short s2, short s3, SteamGameServerAPI$ServerMode steamGameServerAPI$ServerMode, String string2) throws SteamException {
        SteamSharedLibraryLoader.loadLibraries(string);
        isRunning = SteamGameServerAPI.nativeInit(n, s, s2, s3, steamGameServerAPI$ServerMode.ordinal(), string2);
        return isRunning;
    }

    public static void shutdown() {
        isRunning = false;
        SteamGameServerAPI.nativeShutdown();
    }

    public static SteamID getSteamID() {
        return new SteamID(SteamGameServerAPI.nativeGetSteamID());
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
