/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamGameServer$DenyReason {
    Invalid,
    InvalidVersion,
    Generic,
    NotLoggedOn,
    NoLicense,
    Cheater,
    LoggedInElseWhere,
    UnknownText,
    IncompatibleAnticheat,
    MemoryCorruption,
    IncompatibleSoftware,
    SteamConnectionLost,
    SteamConnectionError,
    SteamResponseTimedOut,
    SteamValidationStalled,
    SteamOwnerLeftGuestUser;

    private static final SteamGameServer$DenyReason[] values;

    static SteamGameServer$DenyReason byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamGameServer$DenyReason.values();
    }
}
