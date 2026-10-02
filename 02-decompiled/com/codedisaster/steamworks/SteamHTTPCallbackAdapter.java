/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamHTTP$HTTPStatusCode;
import com.codedisaster.steamworks.SteamHTTPCallback;
import com.codedisaster.steamworks.SteamHTTPRequestHandle;

class SteamHTTPCallbackAdapter
extends SteamCallbackAdapter {
    SteamHTTPCallbackAdapter(SteamHTTPCallback steamHTTPCallback) {
        super(steamHTTPCallback);
    }

    void onHTTPRequestCompleted(long l, long l2, boolean bl, int n, int n2) {
        ((SteamHTTPCallback)this.callback).onHTTPRequestCompleted(new SteamHTTPRequestHandle(l), l2, bl, SteamHTTP$HTTPStatusCode.byValue(n), n2);
    }

    void onHTTPRequestHeadersReceived(long l, long l2) {
        ((SteamHTTPCallback)this.callback).onHTTPRequestHeadersReceived(new SteamHTTPRequestHandle(l), l2);
    }

    void onHTTPRequestDataReceived(long l, long l2, int n, int n2) {
        ((SteamHTTPCallback)this.callback).onHTTPRequestDataReceived(new SteamHTTPRequestHandle(l), l2, n, n2);
    }
}
