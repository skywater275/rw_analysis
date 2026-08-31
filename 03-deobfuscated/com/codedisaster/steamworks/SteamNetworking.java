/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamGameServerAPI;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamNetworking$API;
import com.codedisaster.steamworks.SteamNetworking$P2PSend;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionState;
import com.codedisaster.steamworks.SteamNetworkingCallback;
import com.codedisaster.steamworks.SteamNetworkingCallbackAdapter;
import java.nio.ByteBuffer;

public class SteamNetworking
extends SteamInterface {
    private final int[] tmpIntResult = new int[1];
    private final long[] tmpLongResult = new long[1];

    public SteamNetworking(SteamNetworkingCallback steamNetworkingCallback, SteamNetworking$API steamNetworking$API) {
        super(steamNetworking$API == SteamNetworking$API.Client ? SteamAPI.getSteamNetworkingPointer() : SteamGameServerAPI.getSteamGameServerNetworkingPointer(), SteamNetworking.createCallback(new SteamNetworkingCallbackAdapter(steamNetworkingCallback), steamNetworking$API == SteamNetworking$API.Client));
    }

    public boolean sendP2PPacket(SteamID steamID, ByteBuffer byteBuffer, SteamNetworking$P2PSend p2PSend, int n)  throws SteamException {
        int n2 = byteBuffer.position();
        int n3 = byteBuffer.limit() - n2;
        return this.sendP2PPacket(steamID, byteBuffer, n2, n3, p2PSend, n);
    }

    public boolean sendP2PPacket(SteamID steamID, ByteBuffer byteBuffer, int n, int n2, SteamNetworking$P2PSend steamNetworking$P2PSend, int n3)  throws SteamException {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        if (byteBuffer.capacity() < n + n2) {
            throw new SteamException("Buffer capacity exceeded!");
        }
        return SteamNetworking.sendP2PPacket(this.pointer, steamID.handle, byteBuffer, n, n2, steamNetworking$P2PSend.ordinal(), n3);
    }

    public int isP2PPacketAvailable(int n) {
        if (SteamNetworking.isP2PPacketAvailable(this.pointer, this.tmpIntResult, n)) {
            return this.tmpIntResult[0];
        }
        return 0;
    }

    public int readP2PPacket(SteamID steamID, ByteBuffer byteBuffer, int n)  throws SteamException {
        int n2 = byteBuffer.position();
        int n3 = byteBuffer.limit() - n2;
        return this.readP2PPacket(steamID, byteBuffer, n2, n3, n);
    }

    public int readP2PPacket(SteamID steamID, ByteBuffer byteBuffer, int n, int n2, int n3)  throws SteamException {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        if (byteBuffer.capacity() < n + n2) {
            throw new SteamException("Buffer capacity exceeded!");
        }
        if (SteamNetworking.readP2PPacket(this.pointer, byteBuffer, n, n2, this.tmpIntResult, this.tmpLongResult, n3)) {
            byteBuffer.position(n);
            byteBuffer.limit(n + this.tmpIntResult[0]);
            steamID.handle = this.tmpLongResult[0];
            return this.tmpIntResult[0];
        }
        return 0;
    }

    public boolean acceptP2PSessionWithUser(SteamID steamID) {
        return SteamNetworking.acceptP2PSessionWithUser(this.pointer, steamID.handle);
    }

    public boolean closeP2PSessionWithUser(SteamID steamID) {
        return SteamNetworking.closeP2PSessionWithUser(this.pointer, steamID.handle);
    }

    public boolean closeP2PChannelWithUser(SteamID steamID, int n) {
        return SteamNetworking.closeP2PChannelWithUser(this.pointer, steamID.handle, n);
    }

    public boolean getP2PSessionState(SteamID steamID, SteamNetworking$P2PSessionState p2PSessionState) {
        return SteamNetworking.getP2PSessionState(this.pointer, steamID.handle, p2PSessionState);
    }

    public boolean allowP2PPacketRelay(boolean bl) {
        return SteamNetworking.allowP2PPacketRelay(this.pointer, bl);
    }

    private static native long createCallback(SteamNetworkingCallbackAdapter var0, boolean var1);

    private static native boolean sendP2PPacket(long var0, long var2, ByteBuffer var4, int var5, int var6, int var7, int var8);

    private static native boolean isP2PPacketAvailable(long var0, int[] var2, int var3);

    private static native boolean readP2PPacket(long var0, ByteBuffer var2, int var3, int var4, int[] var5, long[] var6, int var7);

    private static native boolean acceptP2PSessionWithUser(long var0, long var2);

    private static native boolean closeP2PSessionWithUser(long var0, long var2);

    private static native boolean closeP2PChannelWithUser(long var0, long var2, int var4);

    private static native boolean getP2PSessionState(long var0, long var2, SteamNetworking$P2PSessionState var4);

    private static native boolean allowP2PPacketRelay(long var0, boolean var2);
}
