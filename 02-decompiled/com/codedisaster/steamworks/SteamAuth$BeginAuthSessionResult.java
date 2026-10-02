/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamAuth$BeginAuthSessionResult {
    OK,
    InvalidTicket,
    DuplicateRequest,
    InvalidVersion,
    GameMismatch,
    ExpiredTicket;

    private static final SteamAuth$BeginAuthSessionResult[] values;

    static SteamAuth$BeginAuthSessionResult byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamAuth$BeginAuthSessionResult.values();
    }
}
