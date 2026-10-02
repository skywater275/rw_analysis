/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamNetworking$P2PSessionError {
    None,
    NotRunningApp,
    NoRightsToApp,
    DestinationNotLoggedIn,
    Timeout;

    private static final SteamNetworking$P2PSessionError[] values;

    public static SteamNetworking$P2PSessionError byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamNetworking$P2PSessionError.values();
    }
}
