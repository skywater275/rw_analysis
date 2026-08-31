/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNativeHandle;

public class SteamUGCUpdateHandle
extends SteamNativeHandle {
    SteamUGCUpdateHandle(long l) {
        super(l);
    }

    public boolean isValid() {
        return this.handle != -1L;
    }
}
