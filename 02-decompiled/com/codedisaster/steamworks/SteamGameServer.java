/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamAuth$BeginAuthSessionResult;
import com.codedisaster.steamworks.SteamAuth$UserHasLicenseForAppResult;
import com.codedisaster.steamworks.SteamAuthTicket;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamGameServerAPI;
import com.codedisaster.steamworks.SteamGameServerCallback;
import com.codedisaster.steamworks.SteamGameServerCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;
import java.nio.Buffer;

public class SteamGameServer
extends SteamInterface {
    public SteamGameServer(SteamGameServerCallback steamGameServerCallback) {
        super(SteamGameServerAPI.getSteamGameServerPointer(), SteamGameServer.createCallback(new SteamGameServerCallbackAdapter(steamGameServerCallback)));
    }

    public void setProduct(String string) {
        SteamGameServer.setProduct(this.pointer, string);
    }

    public void setGameDescription(String string) {
        SteamGameServer.setGameDescription(this.pointer, string);
    }

    public void setModDir(String string) {
        SteamGameServer.setModDir(this.pointer, string);
    }

    public void setDedicatedServer(boolean bl) {
        SteamGameServer.setDedicatedServer(this.pointer, bl);
    }

    public void logOn(String string) {
        SteamGameServer.logOn(this.pointer, string);
    }

    public void logOnAnonymous() {
        SteamGameServer.logOnAnonymous(this.pointer);
    }

    public void logOff() {
        SteamGameServer.logOff(this.pointer);
    }

    public boolean isLoggedOn() {
        return SteamGameServer.isLoggedOn(this.pointer);
    }

    public boolean isSecure() {
        return SteamGameServer.isSecure(this.pointer);
    }

    public SteamID getSteamID() {
        return new SteamID(SteamGameServer.getSteamID(this.pointer));
    }

    public boolean wasRestartRequested() {
        return SteamGameServer.wasRestartRequested(this.pointer);
    }

    public void setMaxPlayerCount(int n) {
        SteamGameServer.setMaxPlayerCount(this.pointer, n);
    }

    public void setBotPlayerCount(int n) {
        SteamGameServer.setBotPlayerCount(this.pointer, n);
    }

    public void setServerName(String string) {
        SteamGameServer.setServerName(this.pointer, string);
    }

    public void setMapName(String string) {
        SteamGameServer.setMapName(this.pointer, string);
    }

    public void setPasswordProtected(boolean bl) {
        SteamGameServer.setPasswordProtected(this.pointer, bl);
    }

    public void setSpectatorPort(short s) {
        SteamGameServer.setSpectatorPort(this.pointer, s);
    }

    public void setSpectatorServerName(String string) {
        SteamGameServer.setSpectatorServerName(this.pointer, string);
    }

    public void clearAllKeyValues() {
        SteamGameServer.clearAllKeyValues(this.pointer);
    }

    public void setKeyValue(String string, String string2) {
        SteamGameServer.setKeyValue(this.pointer, string, string2);
    }

    public void setGameTags(String string) {
        SteamGameServer.setGameTags(this.pointer, string);
    }

    public void setGameData(String string) {
        SteamGameServer.setGameData(this.pointer, string);
    }

    public void setRegion(String string) {
        SteamGameServer.setRegion(this.pointer, string);
    }

    public SteamID sendUserConnectAndAuthenticate(int n, Buffer buffer, int n2, SteamID steamID) {
        long[] lArray = new long[1];
        if (SteamGameServer.sendUserConnectAndAuthenticate(this.pointer, n, buffer, n2, lArray)) {
            return new SteamID(lArray[0]);
        }
        return null;
    }

    public SteamID createUnauthenticatedUserConnection() {
        return new SteamID(SteamGameServer.createUnauthenticatedUserConnection(this.pointer));
    }

    public void sendUserDisconnect(SteamID steamID) {
        SteamGameServer.sendUserDisconnect(this.pointer, steamID.handle);
    }

    public boolean updateUserData(SteamID steamID, String string, int n) {
        return SteamGameServer.updateUserData(this.pointer, steamID.handle, string, n);
    }

    public SteamAuthTicket getAuthSessionTicket(Buffer buffer, int[] nArray) {
        if (!buffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n = SteamGameServer.getAuthSessionTicket(this.pointer, buffer, buffer.capacity(), nArray);
        if ((long)n != 0L) {
            buffer.limit(nArray[0]);
        }
        return new SteamAuthTicket(n);
    }

    public SteamAuth$BeginAuthSessionResult beginAuthSession(Buffer buffer, SteamID steamID) {
        if (!buffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n = SteamGameServer.beginAuthSession(this.pointer, buffer, buffer.limit(), steamID.handle);
        return SteamAuth$BeginAuthSessionResult.byOrdinal(n);
    }

    public void endAuthSession(SteamID steamID) {
        SteamGameServer.endAuthSession(this.pointer, steamID.handle);
    }

    public void cancelAuthTicket(SteamAuthTicket steamAuthTicket) {
        SteamGameServer.cancelAuthTicket(this.pointer, (int)steamAuthTicket.handle);
    }

    public SteamAuth$UserHasLicenseForAppResult userHasLicenseForApp(SteamID steamID, int n) {
        return SteamAuth$UserHasLicenseForAppResult.byOrdinal(SteamGameServer.userHasLicenseForApp(this.pointer, steamID.handle, n));
    }

    public boolean requestUserGroupStatus(SteamID steamID, SteamID steamID2) {
        return SteamGameServer.requestUserGroupStatus(this.pointer, steamID.handle, steamID2.handle);
    }

    public int getPublicIP() {
        return SteamGameServer.getPublicIP(this.pointer);
    }

    public boolean handleIncomingPacket(Buffer buffer, int n, short s) {
        return SteamGameServer.handleIncomingPacket(this.pointer, buffer, buffer.limit(), n, s);
    }

    public int getNextOutgoingPacket(Buffer buffer, int[] nArray, short[] sArray) {
        return SteamGameServer.getNextOutgoingPacket(this.pointer, buffer, buffer.capacity(), nArray, sArray);
    }

    public void enableHeartbeats(boolean bl) {
        SteamGameServer.enableHeartbeats(this.pointer, bl);
    }

    public void setHeartbeatInterval(int n) {
        SteamGameServer.setHeartbeatInterval(this.pointer, n);
    }

    public void forceHeartbeat() {
        SteamGameServer.forceHeartbeat(this.pointer);
    }

    public SteamAPICall associateWithClan(SteamID steamID) {
        return new SteamAPICall(SteamGameServer.associateWithClan(this.pointer, steamID.handle));
    }

    public SteamAPICall computeNewPlayerCompatibility(SteamID steamID) {
        return new SteamAPICall(SteamGameServer.computeNewPlayerCompatibility(this.pointer, steamID.handle));
    }

    private static native long createCallback(SteamGameServerCallbackAdapter var0);

    private static native void setProduct(long var0, String var2);

    private static native void setGameDescription(long var0, String var2);

    private static native void setModDir(long var0, String var2);

    private static native void setDedicatedServer(long var0, boolean var2);

    private static native void logOn(long var0, String var2);

    private static native void logOnAnonymous(long var0);

    private static native void logOff(long var0);

    private static native boolean isLoggedOn(long var0);

    private static native boolean isSecure(long var0);

    private static native long getSteamID(long var0);

    private static native boolean wasRestartRequested(long var0);

    private static native void setMaxPlayerCount(long var0, int var2);

    private static native void setBotPlayerCount(long var0, int var2);

    private static native void setServerName(long var0, String var2);

    private static native void setMapName(long var0, String var2);

    private static native void setPasswordProtected(long var0, boolean var2);

    private static native void setSpectatorPort(long var0, short var2);

    private static native void setSpectatorServerName(long var0, String var2);

    private static native void clearAllKeyValues(long var0);

    private static native void setKeyValue(long var0, String var2, String var3);

    private static native void setGameTags(long var0, String var2);

    private static native void setGameData(long var0, String var2);

    private static native void setRegion(long var0, String var2);

    private static native boolean sendUserConnectAndAuthenticate(long var0, int var2, Buffer var3, int var4, long[] var5);

    private static native long createUnauthenticatedUserConnection(long var0);

    private static native void sendUserDisconnect(long var0, long var2);

    private static native boolean updateUserData(long var0, long var2, String var4, int var5);

    private static native int getAuthSessionTicket(long var0, Buffer var2, int var3, int[] var4);

    private static native int beginAuthSession(long var0, Buffer var2, int var3, long var4);

    private static native void endAuthSession(long var0, long var2);

    private static native void cancelAuthTicket(long var0, int var2);

    private static native int userHasLicenseForApp(long var0, long var2, int var4);

    private static native boolean requestUserGroupStatus(long var0, long var2, long var4);

    private static native int getPublicIP(long var0);

    private static native boolean handleIncomingPacket(long var0, Buffer var2, int var3, int var4, short var5);

    private static native int getNextOutgoingPacket(long var0, Buffer var2, int var3, int[] var4, short[] var5);

    private static native void enableHeartbeats(long var0, boolean var2);

    private static native void setHeartbeatInterval(long var0, int var2);

    private static native void forceHeartbeat(long var0);

    private static native long associateWithClan(long var0, long var2);

    private static native long computeNewPlayerCompatibility(long var0, long var2);
}
