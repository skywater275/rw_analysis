/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamHTTP$HTTPStatusCode;
import com.codedisaster.steamworks.SteamHTTPRequestHandle;

public interface SteamHTTPCallback {
    public void onHTTPRequestCompleted(SteamHTTPRequestHandle var1, long var2, boolean var4, SteamHTTP$HTTPStatusCode var5, int var6);

    public void onHTTPRequestHeadersReceived(SteamHTTPRequestHandle var1, long var2);

    public void onHTTPRequestDataReceived(SteamHTTPRequestHandle var1, long var2, int var4, int var5);
}
