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

public class SteamFriends extends SteamInterface {

   public SteamFriends(SteamFriendsCallback var1) {
      super(SteamAPI.getSteamFriendsPointer(), createCallback(new SteamFriendsCallbackAdapter(var1)));
   }

   public String getPersonaName() {
      return getPersonaName(this.pointer);
   }

   public SteamAPICall setPersonaName(String var1) {
      return new SteamAPICall(setPersonaName(this.pointer, this.callback, var1));
   }

   public SteamFriends$PersonaState getPersonaState() {
      return SteamFriends$PersonaState.byOrdinal(getPersonaState(this.pointer));
   }

   public int getFriendCount(SteamFriends$FriendFlags var1) {
      return getFriendCount(this.pointer, SteamFriends$FriendFlags.access$000(var1));
   }

   public int getFriendCount(Collection var1) {
      return getFriendCount(this.pointer, SteamFriends$FriendFlags.asBits(var1));
   }

   public SteamID getFriendByIndex(int var1, SteamFriends$FriendFlags var2) {
      return new SteamID(getFriendByIndex(this.pointer, var1, SteamFriends$FriendFlags.access$000(var2)));
   }

   public SteamID getFriendByIndex(int var1, Collection var2) {
      return new SteamID(getFriendByIndex(this.pointer, var1, SteamFriends$FriendFlags.asBits(var2)));
   }

   public SteamFriends$FriendRelationship getFriendRelationship(SteamID var1) {
      return SteamFriends$FriendRelationship.byOrdinal(getFriendRelationship(this.pointer, var1.handle));
   }

   public SteamFriends$PersonaState getFriendPersonaState(SteamID var1) {
      return SteamFriends$PersonaState.byOrdinal(getFriendPersonaState(this.pointer, var1.handle));
   }

   public String getFriendPersonaName(SteamID var1) {
      return getFriendPersonaName(this.pointer, var1.handle);
   }

   public int getSmallFriendAvatar(SteamID var1) {
      return getSmallFriendAvatar(this.pointer, var1.handle);
   }

   public int getMediumFriendAvatar(SteamID var1) {
      return getMediumFriendAvatar(this.pointer, var1.handle);
   }

   public int getLargeFriendAvatar(SteamID var1) {
      return getLargeFriendAvatar(this.pointer, var1.handle);
   }

   public boolean requestUserInformation(SteamID var1, boolean var2) {
      return requestUserInformation(this.pointer, var1.handle, var2);
   }

   public void activateGameOverlay(SteamFriends$OverlayDialog var1) {
      activateGameOverlay(this.pointer, SteamFriends$OverlayDialog.access$100(var1));
   }

   public void activateGameOverlayToUser(SteamFriends$OverlayToUserDialog var1, SteamID var2) {
      activateGameOverlayToUser(this.pointer, SteamFriends$OverlayToUserDialog.access$200(var1), var2.handle);
   }

   public void activateGameOverlayToWebPage(String var1) {
      activateGameOverlayToWebPage(this.pointer, var1);
   }

   public void activateGameOverlayToStore(int var1, SteamFriends$OverlayToStoreFlag var2) {
      activateGameOverlayToStore(this.pointer, var1, var2.ordinal());
   }

   public void activateGameOverlayInviteDialog(SteamID var1) {
      activateGameOverlayInviteDialog(this.pointer, var1.handle);
   }

   public boolean setRichPresence(String var1, String var2) {
      return setRichPresence(this.pointer, var1, var2 != null?var2:"");
   }

   public void clearRichPresence() {
      clearRichPresence(this.pointer);
   }

   public String getFriendRichPresence(SteamID var1, String var2) {
      return getFriendRichPresence(this.pointer, var1.handle, var2);
   }

   public int getFriendRichPresenceKeyCount(SteamID var1) {
      return getFriendRichPresenceKeyCount(this.pointer, var1.handle);
   }

   public String getFriendRichPresenceKeyByIndex(SteamID var1, int var2) {
      return getFriendRichPresenceKeyByIndex(this.pointer, var1.handle, var2);
   }

   public void requestFriendRichPresence(SteamID var1) {
      requestFriendRichPresence(this.pointer, var1.handle);
   }

   public boolean inviteUserToGame(SteamID var1, String var2) {
      return inviteUserToGame(this.pointer, var1.handle, var2);
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
