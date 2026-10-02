/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNativeHandle;

public class SteamID
extends SteamNativeHandle {
    private static final long InvalidSteamID = SteamID.getInvalidSteamID();

    public SteamID() {
        super(InvalidSteamID);
    }

    public SteamID(SteamID steamID) {
        super(steamID.handle);
    }

    SteamID(long l) {
        super(l);
    }

    public boolean isValid() {
        return SteamID.isValid(this.handle);
    }

    public int getAccountID() {
        return (int)(this.handle % 0x100000000L);
    }

    public static SteamID createFromNativeHandle(long l) {
        return new SteamID(l);
    }

    private static native boolean isValid(long var0);

    private static native long getInvalidSteamID();
}
