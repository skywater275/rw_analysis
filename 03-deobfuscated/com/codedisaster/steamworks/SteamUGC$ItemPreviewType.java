/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public enum SteamUGC$ItemPreviewType {
    Image(0),
    YouTubeVideo(1),
    Sketchfab(2),
    EnvironmentMap_HorizontalCross(3),
    EnvironmentMap_LatLong(4),
    ReservedMax(255),
    UnknownPreviewType_NotImplementedByAPI(-1);

    private final int value;
    private static final SteamUGC$ItemPreviewType[] values;





    private SteamUGC$ItemPreviewType(int var3_1) {

        this.value = var3_1;
    }

    static SteamUGC$ItemPreviewType byValue(int n) {
        for (SteamUGC$ItemPreviewType steamUGC$ItemPreviewType : values) {
            if (steamUGC$ItemPreviewType.value != n) continue;
            return steamUGC$ItemPreviewType;
        }
        return UnknownPreviewType_NotImplementedByAPI;
    }

    static {
        values = SteamUGC$ItemPreviewType.values();
    }
}
