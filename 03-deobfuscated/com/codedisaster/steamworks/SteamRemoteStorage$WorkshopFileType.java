/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamRemoteStorage$WorkshopFileType {
    Community,
    Microtransaction,
    Collection,
    Art,
    Video,
    Screenshot,
    Game,
    Software,
    Concept,
    WebGuide,
    IntegratedGuide,
    Merch,
    ControllerBinding,
    SteamworksAccessInvite,
    SteamVideo,
    GameManagedItem;

    private static final SteamRemoteStorage$WorkshopFileType[] values;

    static SteamRemoteStorage$WorkshopFileType byOrdinal(int n) {
        return values[n];
    }

    static {
        values = SteamRemoteStorage$WorkshopFileType.values();
    }
}
