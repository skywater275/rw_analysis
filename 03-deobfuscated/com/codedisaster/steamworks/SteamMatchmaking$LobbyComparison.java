/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamMatchmaking$LobbyComparison {
    EqualToOrLessThan(-2),
    LessThan(-1),
    Equal(0),
    GreaterThan(1),
    EqualToOrGreaterThan(2),
    NotEqual(3);

    private final int value;





    private SteamMatchmaking$LobbyComparison(int var3_1) {

        this.value = var3_1;
    }

    static /* synthetic */ int access$000(SteamMatchmaking$LobbyComparison steamMatchmaking$LobbyComparison) {
        return steamMatchmaking$LobbyComparison.value;
    }
}
