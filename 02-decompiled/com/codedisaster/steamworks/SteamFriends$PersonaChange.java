/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamFriends$PersonaChange {
    Name(1),
    Status(2),
    ComeOnline(4),
    GoneOffline(8),
    GamePlayed(16),
    GameServer(32),
    Avatar(64),
    JoinedSource(128),
    LeftSource(256),
    RelationshipChanged(512),
    NameFirstSet(1024),
    FacebookInfo(2048),
    Nickname(4096),
    SteamLevel(8192);

    private final int bits;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SteamFriends$PersonaChange() {
        void var3_1;
        this.bits = var3_1;
    }

    static boolean isSet(SteamFriends$PersonaChange steamFriends$PersonaChange, int n) {
        return (steamFriends$PersonaChange.bits & n) == steamFriends$PersonaChange.bits;
    }
}
