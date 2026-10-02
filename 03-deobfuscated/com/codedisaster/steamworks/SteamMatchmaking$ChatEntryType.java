/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamMatchmaking$ChatEntryType {
    Invalid(0),
    ChatMsg(1),
    Typing(2),
    InviteGame(3),
    Emote(4),
    LeftConversation(6),
    Entered(7),
    WasKicked(8),
    WasBanned(9),
    Disconnected(10),
    HistoricalChat(11),
    Reserved1(12),
    Reserved2(13),
    LinkBlocked(14);

    private final int code;
    private static final SteamMatchmaking$ChatEntryType[] values;





    private SteamMatchmaking$ChatEntryType(int var3_1) {

        this.code = var3_1;
    }

    static SteamMatchmaking$ChatEntryType byCode(int n) {
        for (SteamMatchmaking$ChatEntryType steamMatchmaking$ChatEntryType : values) {
            if (steamMatchmaking$ChatEntryType.code != n) continue;
            return steamMatchmaking$ChatEntryType;
        }
        return Invalid;
    }

    static {
        values = SteamMatchmaking$ChatEntryType.values();
    }
}
