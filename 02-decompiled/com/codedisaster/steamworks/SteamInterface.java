/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

abstract class SteamInterface {
    protected final long pointer;
    protected long callback;

    SteamInterface(long l) {
        this(l, 0L);
    }

    SteamInterface(long l, long l2) {
        if (l == 0L) {
            throw new RuntimeException("Steam interface created with null pointer. Always check result of SteamAPI.init(), or with SteamAPI.isSteamRunning()!");
        }
        this.pointer = l;
        this.callback = l2;
    }

    void setCallback(long l) {
        this.callback = l;
    }

    public void dispose() {
        SteamInterface.deleteCallback(this.callback);
    }

    protected static native void deleteCallback(long var0);
}
