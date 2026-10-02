/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamMatchmaking$ChatMemberStateChange {
    Entered(1),
    Left(2),
    Disconnected(4),
    Kicked(8),
    Banned(16);

    private final int bits;





    private SteamMatchmaking$ChatMemberStateChange(int var3_1) {

        this.bits = var3_1;
    }

    static boolean isSet(SteamMatchmaking$ChatMemberStateChange steamMatchmaking$ChatMemberStateChange, int n) {
        return (steamMatchmaking$ChatMemberStateChange.bits & n) == steamMatchmaking$ChatMemberStateChange.bits;
    }
}
