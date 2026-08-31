/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamFriends$FriendFlags;
import com.codedisaster.steamworks.SteamFriends$FriendRelationship;
import com.codedisaster.steamworks.SteamFriends$OverlayDialog;
import com.codedisaster.steamworks.SteamFriends$OverlayToStoreFlag;
import com.codedisaster.steamworks.SteamFriends$OverlayToUserDialog;
import com.codedisaster.steamworks.SteamFriends$PersonaState;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamFriendsCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;
import java.util.Collection;

public class SteamFriends
extends SteamInterface {
    public SteamFriends(SteamFriendsCallback steamFriendsCallback) {
        super(SteamAPI.getSteamFriendsPointer(), SteamFriends.createCallback(new SteamFriendsCallbackAdapter(steamFriendsCallback)));
    }

    public String getPersonaName() {
        return SteamFriends.getPersonaName(this.pointer);
    }

    public SteamAPICall setPersonaName(String string) {
        return new SteamAPICall(SteamFriends.setPersonaName(this.pointer, this.callback, string));
    }

    public SteamFriends$PersonaState getPersonaState() {
        return SteamFriends$PersonaState.byOrdinal(SteamFriends.getPersonaState(this.pointer));
    }

    public int getFriendCount(SteamFriends$FriendFlags steamFriends$FriendFlags) {
        return SteamFriends.getFriendCount(this.pointer, SteamFriends$FriendFlags.access$000(steamFriends$FriendFlags));
    }

    public int getFriendCount(Collection collection) {
        return SteamFriends.getFriendCount(this.pointer, SteamFriends$FriendFlags.asBits(collection));
    }

    public SteamID getFriendByIndex(int n, SteamFriends$FriendFlags steamFriends$FriendFlags) {
        return new SteamID(SteamFriends.getFriendByIndex(this.pointer, n, SteamFriends$FriendFlags.access$000(steamFriends$FriendFlags)));
    }

    public SteamID getFriendByIndex(int n, Collection collection) {
        return new SteamID(SteamFriends.getFriendByIndex(this.pointer, n, SteamFriends$FriendFlags.asBits(collection)));
    }

    public SteamFriends$FriendRelationship getFriendRelationship(SteamID steamID) {
        return SteamFriends$FriendRelationship.byOrdinal(SteamFriends.getFriendRelationship(this.pointer, steamID.handle));
    }

    public SteamFriends$PersonaState getFriendPersonaState(SteamID steamID) {
        return SteamFriends$PersonaState.byOrdinal(SteamFriends.getFriendPersonaState(this.pointer, steamID.handle));
    }

    public String getFriendPersonaName(SteamID steamID) {
        return SteamFriends.getFriendPersonaName(this.pointer, steamID.handle);
    }

    public int getSmallFriendAvatar(SteamID steamID) {
        return SteamFriends.getSmallFriendAvatar(this.pointer, steamID.handle);
    }

    public int getMediumFriendAvatar(SteamID steamID) {
        return SteamFriends.getMediumFriendAvatar(this.pointer, steamID.handle);
    }

    public int getLargeFriendAvatar(SteamID steamID) {
        return SteamFriends.getLargeFriendAvatar(this.pointer, steamID.handle);
    }

    public boolean requestUserInformation(SteamID steamID, boolean bl) {
        return SteamFriends.requestUserInformation(this.pointer, steamID.handle, bl);
    }

    public void activateGameOverlay(SteamFriends$OverlayDialog steamFriends$OverlayDialog) {
        SteamFriends.activateGameOverlay(this.pointer, SteamFriends$OverlayDialog.access$100(steamFriends$OverlayDialog));
    }

    public void activateGameOverlayToUser(SteamFriends$OverlayToUserDialog steamFriends$OverlayToUserDialog, SteamID steamID) {
        SteamFriends.activateGameOverlayToUser(this.pointer, SteamFriends$OverlayToUserDialog.access$200(steamFriends$OverlayToUserDialog), steamID.handle);
    }

    public void activateGameOverlayToWebPage(String string) {
        SteamFriends.activateGameOverlayToWebPage(this.pointer, string);
    }

    public void activateGameOverlayToStore(int n, SteamFriends$OverlayToStoreFlag steamFriends$OverlayToStoreFlag) {
        SteamFriends.activateGameOverlayToStore(this.pointer, n, steamFriends$OverlayToStoreFlag.ordinal());
    }

    public void activateGameOverlayInviteDialog(SteamID steamID) {
        SteamFriends.activateGameOverlayInviteDialog(this.pointer, steamID.handle);
    }

    public boolean setRichPresence(String string, String string2) {
        return SteamFriends.setRichPresence(this.pointer, string, string2 != null ? string2 : "");
    }

    public void clearRichPresence() {
        SteamFriends.clearRichPresence(this.pointer);
    }

    public String getFriendRichPresence(SteamID steamID, String string) {
        return SteamFriends.getFriendRichPresence(this.pointer, steamID.handle, string);
    }

    public int getFriendRichPresenceKeyCount(SteamID steamID) {
        return SteamFriends.getFriendRichPresenceKeyCount(this.pointer, steamID.handle);
    }

    public String getFriendRichPresenceKeyByIndex(SteamID steamID, int n) {
        return SteamFriends.getFriendRichPresenceKeyByIndex(this.pointer, steamID.handle, n);
    }

    public void requestFriendRichPresence(SteamID steamID) {
        SteamFriends.requestFriendRichPresence(this.pointer, steamID.handle);
    }

    public boolean inviteUserToGame(SteamID steamID, String string) {
        return SteamFriends.inviteUserToGame(this.pointer, steamID.handle, string);
    }

    private static native long createCallback(SteamFriendsCallbackAdapter var0);

    private static native String getPersonaName(long var0);

    private static native long setPersonaName(long var0, long var2, String var4);

    private static native int getPersonaState(long var0);

    private static native int getFriendCount(long var0, int var2);

    private static native long getFriendByIndex(long var0, int var2, int var3);

    private static native int getFriendRelationship(long var0, long var2);

    private static native int getFriendPersonaState(long var0, long var2);

    private static native String getFriendPersonaName(long var0, long var2);

    private static native int getSmallFriendAvatar(long var0, long var2);

    private static native int getMediumFriendAvatar(long var0, long var2);

    private static native int getLargeFriendAvatar(long var0, long var2);

    private static native boolean requestUserInformation(long var0, long var2, boolean var4);

    private static native void activateGameOverlay(long var0, String var2);

    private static native void activateGameOverlayToUser(long var0, String var2, long var3);

    private static native void activateGameOverlayToWebPage(long var0, String var2);

    private static native void activateGameOverlayToStore(long var0, int var2, int var3);

    private static native void activateGameOverlayInviteDialog(long var0, long var2);

    private static native boolean setRichPresence(long var0, String var2, String var3);

    private static native void clearRichPresence(long var0);

    private static native String getFriendRichPresence(long var0, long var2, String var4);

    private static native int getFriendRichPresenceKeyCount(long var0, long var2);

    private static native String getFriendRichPresenceKeyByIndex(long var0, long var2, int var4);

    private static native void requestFriendRichPresence(long var0, long var2);

    private static native boolean inviteUserToGame(long var0, long var2, String var4);
}
