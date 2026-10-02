/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public abstract class SteamNativeHandle {
    long handle;

    SteamNativeHandle(long l) {
        this.handle = l;
    }

    public static long getNativeHandle(SteamNativeHandle steamNativeHandle) {
        return steamNativeHandle.handle;
    }

    public int hashCode() {
        return Long.valueOf(this.handle).hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof SteamNativeHandle) {
            return this.handle == ((SteamNativeHandle)object).handle;
        }
        return false;
    }

    public String toString() {
        return Long.toHexString(this.handle);
    }
}
