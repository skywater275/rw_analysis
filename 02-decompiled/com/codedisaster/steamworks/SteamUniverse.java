/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamUniverse {
    Invalid(0),
    Public(1),
    Beta(2),
    Internal(3),
    Dev(4);

    private final int value;
    private static final SteamUniverse[] values;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SteamUniverse() {
        void var3_1;
        this.value = var3_1;
    }

    static SteamUniverse byValue(int n) {
        for (SteamUniverse steamUniverse : values) {
            if (steamUniverse.value != n) continue;
            return steamUniverse;
        }
        return Invalid;
    }

    static {
        values = SteamUniverse.values();
    }
}
