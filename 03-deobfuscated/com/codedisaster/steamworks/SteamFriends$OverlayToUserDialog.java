/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamFriends$OverlayToUserDialog {
    SteamID("steamid"),
    Chat("chat"),
    JoinTrade("jointrade"),
    Stats("stats"),
    Achievements("achievements"),
    FriendAdd("friendadd"),
    FriendRemove("friendremove"),
    FriendRequestAccept("friendrequestaccept"),
    FriendRequestIgnore("friendrequestignore");

    private final String id;





    private SteamFriends$OverlayToUserDialog(String var3_1) {

        this.id = var3_1;
    }

    static /* synthetic */ String access$200(SteamFriends$OverlayToUserDialog steamFriends$OverlayToUserDialog) {
        return steamFriends$OverlayToUserDialog.id;
    }
}
