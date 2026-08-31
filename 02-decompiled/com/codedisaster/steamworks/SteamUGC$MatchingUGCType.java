/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamUGC$MatchingUGCType {
    Items(0),
    ItemsMtx(1),
    ItemsReadyToUse(2),
    Collections(3),
    Artwork(4),
    Videos(5),
    Screenshots(6),
    AllGuides(7),
    WebGuides(8),
    IntegratedGuides(9),
    UsableInGame(10),
    ControllerBindings(11),
    GameManagedItems(12),
    All(-1);

    private final int value;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SteamUGC$MatchingUGCType() {
        void var3_1;
        this.value = var3_1;
    }

    static /* synthetic */ int access$000(SteamUGC$MatchingUGCType steamUGC$MatchingUGCType) {
        return steamUGC$MatchingUGCType.value;
    }
}
