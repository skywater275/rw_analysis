/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamAuth$UserHasLicenseForAppResult {
    HasLicense,
    DoesNotHaveLicense,
    NoAuth;

    private static final SteamAuth$UserHasLicenseForAppResult[] values;

    static SteamAuth$UserHasLicenseForAppResult byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamAuth$UserHasLicenseForAppResult.values();
    }
}
