/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamMatchMakingKeyValuePair;
import com.codedisaster.steamworks.SteamMatchmaking$ChatEntry;
import com.codedisaster.steamworks.SteamMatchmaking$LobbyComparison;
import com.codedisaster.steamworks.SteamMatchmaking$LobbyDistanceFilter;
import com.codedisaster.steamworks.SteamMatchmaking$LobbyType;
import com.codedisaster.steamworks.SteamMatchmakingCallback;
import com.codedisaster.steamworks.SteamMatchmakingCallbackAdapter;
import java.nio.ByteBuffer;

public class SteamMatchmaking
extends SteamInterface {
    public SteamMatchmaking(SteamMatchmakingCallback steamMatchmakingCallback) {
        super(SteamAPI.getSteamMatchmakingPointer(), SteamMatchmaking.createCallback(new SteamMatchmakingCallbackAdapter(steamMatchmakingCallback)));
    }

    public SteamAPICall requestLobbyList() {
        return new SteamAPICall(SteamMatchmaking.requestLobbyList(this.pointer, this.callback));
    }

    public void addRequestLobbyListStringFilter(String string, String string2, SteamMatchmaking$LobbyComparison steamMatchmaking$LobbyComparison) {
        SteamMatchmaking.addRequestLobbyListStringFilter(this.pointer, string, string2, SteamMatchmaking$LobbyComparison.access$000(steamMatchmaking$LobbyComparison));
    }

    public void addRequestLobbyListNumericalFilter(String string, int n, SteamMatchmaking$LobbyComparison steamMatchmaking$LobbyComparison) {
        SteamMatchmaking.addRequestLobbyListNumericalFilter(this.pointer, string, n, SteamMatchmaking$LobbyComparison.access$000(steamMatchmaking$LobbyComparison));
    }

    public void addRequestLobbyListNearValueFilter(String string, int n) {
        SteamMatchmaking.addRequestLobbyListNearValueFilter(this.pointer, string, n);
    }

    public void addRequestLobbyListFilterSlotsAvailable(int n) {
        SteamMatchmaking.addRequestLobbyListFilterSlotsAvailable(this.pointer, n);
    }

    public void addRequestLobbyListDistanceFilter(SteamMatchmaking$LobbyDistanceFilter steamMatchmaking$LobbyDistanceFilter) {
        SteamMatchmaking.addRequestLobbyListDistanceFilter(this.pointer, steamMatchmaking$LobbyDistanceFilter.ordinal());
    }

    public void addRequestLobbyListResultCountFilter(int n) {
        SteamMatchmaking.addRequestLobbyListResultCountFilter(this.pointer, n);
    }

    public void addRequestLobbyListCompatibleMembersFilter(SteamID steamID) {
        SteamMatchmaking.addRequestLobbyListCompatibleMembersFilter(this.pointer, steamID.handle);
    }

    public SteamID getLobbyByIndex(int n) {
        return new SteamID(SteamMatchmaking.getLobbyByIndex(this.pointer, n));
    }

    public SteamAPICall createLobby(SteamMatchmaking$LobbyType steamMatchmaking$LobbyType, int n) {
        return new SteamAPICall(SteamMatchmaking.createLobby(this.pointer, this.callback, steamMatchmaking$LobbyType.ordinal(), n));
    }

    public SteamAPICall joinLobby(SteamID steamID) {
        return new SteamAPICall(SteamMatchmaking.joinLobby(this.pointer, this.callback, steamID.handle));
    }

    public void leaveLobby(SteamID steamID) {
        SteamMatchmaking.leaveLobby(this.pointer, steamID.handle);
    }

    public boolean inviteUserToLobby(SteamID steamID, SteamID steamID2) {
        return SteamMatchmaking.inviteUserToLobby(this.pointer, steamID.handle, steamID2.handle);
    }

    public int getNumLobbyMembers(SteamID steamID) {
        return SteamMatchmaking.getNumLobbyMembers(this.pointer, steamID.handle);
    }

    public SteamID getLobbyMemberByIndex(SteamID steamID, int n) {
        return new SteamID(SteamMatchmaking.getLobbyMemberByIndex(this.pointer, steamID.handle, n));
    }

    public String getLobbyData(SteamID steamID, String string) {
        return SteamMatchmaking.getLobbyData(this.pointer, steamID.handle, string);
    }

    public boolean setLobbyData(SteamID steamID, String string, String string2) {
        return SteamMatchmaking.setLobbyData(this.pointer, steamID.handle, string, string2);
    }

    public boolean setLobbyData(SteamID steamID, SteamMatchMakingKeyValuePair steamMatchMakingKeyValuePair) {
        return SteamMatchmaking.setLobbyData(this.pointer, steamID.handle, steamMatchMakingKeyValuePair.getKey(), steamMatchMakingKeyValuePair.getValue());
    }

    public int getLobbyDataCount(SteamID steamID) {
        return SteamMatchmaking.getLobbyDataCount(this.pointer, steamID.handle);
    }

    public boolean getLobbyDataByIndex(SteamID steamID, int n, SteamMatchMakingKeyValuePair steamMatchMakingKeyValuePair) {
        return SteamMatchmaking.getLobbyDataByIndex(this.pointer, steamID.handle, n, steamMatchMakingKeyValuePair);
    }

    public boolean deleteLobbyData(SteamID steamID, String string) {
        return SteamMatchmaking.deleteLobbyData(this.pointer, steamID.handle, string);
    }

    public boolean sendLobbyChatMsg(SteamID steamID, ByteBuffer byteBuffer)  throws SteamException {
        int n = byteBuffer.position();
        int n2 = byteBuffer.limit() - n;
        return this.sendLobbyChatMsg(steamID, byteBuffer, n, n2);
    }

    public boolean sendLobbyChatMsg(SteamID steamID, ByteBuffer byteBuffer, int n, int n2)  throws SteamException {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        if (byteBuffer.capacity() < n + n2) {
            throw new SteamException("Buffer capacity exceeded!");
        }
        return SteamMatchmaking.sendLobbyChatMsg(this.pointer, steamID.handle, byteBuffer, n, n2);
    }

    public boolean sendLobbyChatMsg(SteamID steamID, String string) {
        return SteamMatchmaking.sendLobbyChatMsg(this.pointer, steamID.handle, string);
    }

    public int getLobbyChatEntry(SteamID steamID, int n, SteamMatchmaking$ChatEntry chatEntry, ByteBuffer byteBuffer)  throws SteamException {
        int n2 = byteBuffer.position();
        int n3 = byteBuffer.limit() - n2;
        return this.getLobbyChatEntry(steamID, n, chatEntry, byteBuffer, n2, n3);
    }

    public int getLobbyChatEntry(SteamID steamID, int n, SteamMatchmaking$ChatEntry chatEntry, ByteBuffer byteBuffer, int n2, int n3)  throws SteamException {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        if (byteBuffer.capacity() < n2 + n3) {
            throw new SteamException("Buffer capacity exceeded!");
        }
        int n4 = SteamMatchmaking.getLobbyChatEntry(this.pointer, steamID.handle, n, chatEntry, byteBuffer, n2, n3);
        if (n4 >= 0) {
            byteBuffer.position(n2);
            byteBuffer.limit(n2 + n4);
        }
        return n4;
    }

    public boolean requestLobbyData(SteamID steamID) {
        return SteamMatchmaking.requestLobbyData(this.pointer, steamID.handle);
    }

    public boolean setLobbyMemberLimit(SteamID steamID, int n) {
        return SteamMatchmaking.setLobbyMemberLimit(this.pointer, steamID.handle, n);
    }

    public boolean getLobbyMemberLimit(SteamID steamID) {
        return SteamMatchmaking.getLobbyMemberLimit(this.pointer, steamID.handle);
    }

    public boolean setLobbyType(SteamID steamID, SteamMatchmaking$LobbyType steamMatchmaking$LobbyType) {
        return SteamMatchmaking.setLobbyType(this.pointer, steamID.handle, steamMatchmaking$LobbyType.ordinal());
    }

    public boolean setLobbyJoinable(SteamID steamID, boolean bl) {
        return SteamMatchmaking.setLobbyJoinable(this.pointer, steamID.handle, bl);
    }

    public SteamID getLobbyOwner(SteamID steamID) {
        return new SteamID(SteamMatchmaking.getLobbyOwner(this.pointer, steamID.handle));
    }

    public boolean setLobbyOwner(SteamID steamID, SteamID steamID2) {
        return SteamMatchmaking.setLobbyOwner(this.pointer, steamID.handle, steamID2.handle);
    }

    public boolean setLinkedLobby(SteamID steamID, SteamID steamID2) {
        return SteamMatchmaking.setLinkedLobby(this.pointer, steamID.handle, steamID2.handle);
    }

    private static native long createCallback(SteamMatchmakingCallbackAdapter var0);

    private static native long requestLobbyList(long var0, long var2);

    private static native void addRequestLobbyListStringFilter(long var0, String var2, String var3, int var4);

    private static native void addRequestLobbyListNumericalFilter(long var0, String var2, int var3, int var4);

    private static native void addRequestLobbyListNearValueFilter(long var0, String var2, int var3);

    private static native void addRequestLobbyListFilterSlotsAvailable(long var0, int var2);

    private static native void addRequestLobbyListDistanceFilter(long var0, int var2);

    private static native void addRequestLobbyListResultCountFilter(long var0, int var2);

    private static native void addRequestLobbyListCompatibleMembersFilter(long var0, long var2);

    private static native long getLobbyByIndex(long var0, int var2);

    private static native long createLobby(long var0, long var2, int var4, int var5);

    private static native long joinLobby(long var0, long var2, long var4);

    private static native void leaveLobby(long var0, long var2);

    private static native boolean inviteUserToLobby(long var0, long var2, long var4);

    private static native int getNumLobbyMembers(long var0, long var2);

    private static native long getLobbyMemberByIndex(long var0, long var2, int var4);

    private static native String getLobbyData(long var0, long var2, String var4);

    private static native boolean setLobbyData(long var0, long var2, String var4, String var5);

    private static native int getLobbyDataCount(long var0, long var2);

    private static native boolean getLobbyDataByIndex(long var0, long var2, int var4, SteamMatchMakingKeyValuePair var5);

    private static native boolean deleteLobbyData(long var0, long var2, String var4);

    private static native boolean sendLobbyChatMsg(long var0, long var2, ByteBuffer var4, int var5, int var6);

    private static native boolean sendLobbyChatMsg(long var0, long var2, String var4);

    private static native int getLobbyChatEntry(long var0, long var2, int var4, SteamMatchmaking$ChatEntry var5, ByteBuffer var6, int var7, int var8);

    private static native boolean requestLobbyData(long var0, long var2);

    private static native boolean setLobbyMemberLimit(long var0, long var2, int var4);

    private static native boolean getLobbyMemberLimit(long var0, long var2);

    private static native boolean setLobbyType(long var0, long var2, int var4);

    private static native boolean setLobbyJoinable(long var0, long var2, boolean var4);

    private static native long getLobbyOwner(long var0, long var2);

    private static native boolean setLobbyOwner(long var0, long var2, long var4);

    private static native boolean setLinkedLobby(long var0, long var2, long var4);
}
