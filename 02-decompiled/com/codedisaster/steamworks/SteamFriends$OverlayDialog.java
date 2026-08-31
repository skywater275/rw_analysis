/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamFriends$OverlayDialog {
    Friends("Friends"),
    Community("Community"),
    Players("Players"),
    Settings("Settings"),
    OfficialGameGroup("OfficialGameGroup"),
    Stats("Stats"),
    Achievements("Achievements");

    private final String id;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SteamFriends$OverlayDialog() {
        void var3_1;
        this.id = var3_1;
    }

    static /* synthetic */ String access$100(SteamFriends$OverlayDialog steamFriends$OverlayDialog) {
        return steamFriends$OverlayDialog.id;
    }
}
