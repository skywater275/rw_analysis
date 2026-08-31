/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamFriends$PersonaState {
    Offline,
    Online,
    Busy,
    Away,
    Snooze,
    LookingToTrade,
    LookingToPlay;

    private static final SteamFriends$PersonaState[] values;

    static SteamFriends$PersonaState byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamFriends$PersonaState.values();
    }
}
