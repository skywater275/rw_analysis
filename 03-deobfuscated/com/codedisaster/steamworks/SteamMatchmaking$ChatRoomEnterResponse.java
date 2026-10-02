/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamMatchmaking$ChatRoomEnterResponse {
    Success(1),
    DoesntExist(2),
    NotAllowed(3),
    Full(4),
    Error(5),
    Banned(6),
    Limited(7),
    ClanDisabled(8),
    CommunityBan(9),
    MemberBlockedYou(10),
    YouBlockedMember(11);

    private final int code;
    private static final SteamMatchmaking$ChatRoomEnterResponse[] values;





    private SteamMatchmaking$ChatRoomEnterResponse(int var3_1) {

        this.code = var3_1;
    }

    static SteamMatchmaking$ChatRoomEnterResponse byCode(int n) {
        for (SteamMatchmaking$ChatRoomEnterResponse steamMatchmaking$ChatRoomEnterResponse : values) {
            if (steamMatchmaking$ChatRoomEnterResponse.code != n) continue;
            return steamMatchmaking$ChatRoomEnterResponse;
        }
        return Error;
    }

    static {
        values = SteamMatchmaking$ChatRoomEnterResponse.values();
    }
}
