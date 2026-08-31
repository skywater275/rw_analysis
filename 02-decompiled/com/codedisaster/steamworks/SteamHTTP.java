/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamGameServerAPI;
import com.codedisaster.steamworks.SteamHTTP$API;
import com.codedisaster.steamworks.SteamHTTP$HTTPMethod;
import com.codedisaster.steamworks.SteamHTTPCallback;
import com.codedisaster.steamworks.SteamHTTPCallbackAdapter;
import com.codedisaster.steamworks.SteamHTTPRequestHandle;
import com.codedisaster.steamworks.SteamInterface;
import java.nio.ByteBuffer;

public class SteamHTTP
extends SteamInterface {
    public SteamHTTP(SteamHTTPCallback steamHTTPCallback, SteamHTTP$API steamHTTP$API) {
        super(steamHTTP$API == SteamHTTP$API.Client ? SteamAPI.getSteamHTTPPointer() : SteamGameServerAPI.getSteamGameServerHTTPPointer(), SteamHTTP.createCallback(new SteamHTTPCallbackAdapter(steamHTTPCallback), steamHTTP$API == SteamHTTP$API.Client));
    }

    public SteamHTTPRequestHandle createHTTPRequest(SteamHTTP$HTTPMethod steamHTTP$HTTPMethod, String string) {
        return new SteamHTTPRequestHandle(SteamHTTP.createHTTPRequest(this.pointer, steamHTTP$HTTPMethod.ordinal(), string));
    }

    public boolean setHTTPRequestContextValue(SteamHTTPRequestHandle steamHTTPRequestHandle, long l) {
        return SteamHTTP.setHTTPRequestContextValue(this.pointer, steamHTTPRequestHandle.handle, l);
    }

    public boolean setHTTPRequestNetworkActivityTimeout(SteamHTTPRequestHandle steamHTTPRequestHandle, int n) {
        return SteamHTTP.setHTTPRequestNetworkActivityTimeout(this.pointer, steamHTTPRequestHandle.handle, n);
    }

    public boolean setHTTPRequestHeaderValue(SteamHTTPRequestHandle steamHTTPRequestHandle, String string, String string2) {
        return SteamHTTP.setHTTPRequestHeaderValue(this.pointer, steamHTTPRequestHandle.handle, string, string2);
    }

    public boolean setHTTPRequestGetOrPostParameter(SteamHTTPRequestHandle steamHTTPRequestHandle, String string, String string2) {
        return SteamHTTP.setHTTPRequestGetOrPostParameter(this.pointer, steamHTTPRequestHandle.handle, string, string2);
    }

    public SteamAPICall sendHTTPRequest(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return new SteamAPICall(SteamHTTP.sendHTTPRequest(this.pointer, this.callback, steamHTTPRequestHandle.handle));
    }

    public SteamAPICall sendHTTPRequestAndStreamResponse(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return new SteamAPICall(SteamHTTP.sendHTTPRequestAndStreamResponse(this.pointer, steamHTTPRequestHandle.handle));
    }

    public int getHTTPResponseHeaderSize(SteamHTTPRequestHandle steamHTTPRequestHandle, String string) {
        return SteamHTTP.getHTTPResponseHeaderSize(this.pointer, steamHTTPRequestHandle.handle, string);
    }

    public boolean getHTTPResponseHeaderValue(SteamHTTPRequestHandle steamHTTPRequestHandle, String string, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n = byteBuffer.position();
        int n2 = byteBuffer.limit() - n;
        return SteamHTTP.getHTTPResponseHeaderValue(this.pointer, steamHTTPRequestHandle.handle, string, byteBuffer, n, n2);
    }

    public int getHTTPResponseBodySize(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return SteamHTTP.getHTTPResponseBodySize(this.pointer, steamHTTPRequestHandle.handle);
    }

    public boolean getHTTPResponseBodyData(SteamHTTPRequestHandle steamHTTPRequestHandle, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n = byteBuffer.position();
        int n2 = byteBuffer.limit() - n;
        return SteamHTTP.getHTTPResponseBodyData(this.pointer, steamHTTPRequestHandle.handle, byteBuffer, n, n2);
    }

    public boolean getHTTPStreamingResponseBodyData(SteamHTTPRequestHandle steamHTTPRequestHandle, int n, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n2 = byteBuffer.position();
        int n3 = byteBuffer.limit() - n2;
        return SteamHTTP.getHTTPStreamingResponseBodyData(this.pointer, steamHTTPRequestHandle.handle, n, byteBuffer, n2, n3);
    }

    public boolean releaseHTTPRequest(SteamHTTPRequestHandle steamHTTPRequestHandle) {
        return SteamHTTP.releaseHTTPRequest(this.pointer, steamHTTPRequestHandle.handle);
    }

    private static native long createCallback(SteamHTTPCallbackAdapter var0, boolean var1);

    private static native long createHTTPRequest(long var0, int var2, String var3);

    private static native boolean setHTTPRequestContextValue(long var0, long var2, long var4);

    private static native boolean setHTTPRequestNetworkActivityTimeout(long var0, long var2, int var4);

    private static native boolean setHTTPRequestHeaderValue(long var0, long var2, String var4, String var5);

    private static native boolean setHTTPRequestGetOrPostParameter(long var0, long var2, String var4, String var5);

    private static native long sendHTTPRequest(long var0, long var2, long var4);

    private static native long sendHTTPRequestAndStreamResponse(long var0, long var2);

    private static native int getHTTPResponseHeaderSize(long var0, long var2, String var4);

    private static native boolean getHTTPResponseHeaderValue(long var0, long var2, String var4, ByteBuffer var5, int var6, int var7);

    private static native int getHTTPResponseBodySize(long var0, long var2);

    private static native boolean getHTTPResponseBodyData(long var0, long var2, ByteBuffer var4, int var5, int var6);

    private static native boolean getHTTPStreamingResponseBodyData(long var0, long var2, int var4, ByteBuffer var5, int var6, int var7);

    private static native boolean releaseHTTPRequest(long var0, long var2);
}
