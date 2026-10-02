/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntryType;

public class SteamMatchmaking$ChatEntry {
    private long steamIDUser;
    private int chatEntryType;

    public SteamID getSteamIDUser() {
        return new SteamID(this.steamIDUser);
    }

    public SteamMatchmaking$ChatEntryType getChatEntryType() {
        return SteamMatchmaking$ChatEntryType.byCode(this.chatEntryType);
    }
}
