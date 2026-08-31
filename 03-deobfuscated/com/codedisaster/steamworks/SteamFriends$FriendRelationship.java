/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamFriends$FriendRelationship {
    None,
    Blocked,
    Recipient,
    Friend,
    RequestInitiator,
    Ignored,
    IgnoredFriend,
    Suggested_DEPRECATED,
    Max;

    private static final SteamFriends$FriendRelationship[] values;

    static SteamFriends$FriendRelationship byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamFriends$FriendRelationship.values();
    }
}
