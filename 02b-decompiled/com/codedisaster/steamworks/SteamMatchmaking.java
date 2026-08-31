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

public class SteamMatchmaking extends SteamInterface {

   public SteamMatchmaking(SteamMatchmakingCallback var1) {
      super(SteamAPI.getSteamMatchmakingPointer(), createCallback(new SteamMatchmakingCallbackAdapter(var1)));
   }

   public SteamAPICall requestLobbyList() {
      return new SteamAPICall(requestLobbyList(this.pointer, this.callback));
   }

   public void addRequestLobbyListStringFilter(String var1, String var2, SteamMatchmaking$LobbyComparison var3) {
      addRequestLobbyListStringFilter(this.pointer, var1, var2, SteamMatchmaking$LobbyComparison.access$000(var3));
   }

   public void addRequestLobbyListNumericalFilter(String var1, int var2, SteamMatchmaking$LobbyComparison var3) {
      addRequestLobbyListNumericalFilter(this.pointer, var1, var2, SteamMatchmaking$LobbyComparison.access$000(var3));
   }

   public void addRequestLobbyListNearValueFilter(String var1, int var2) {
      addRequestLobbyListNearValueFilter(this.pointer, var1, var2);
   }

   public void addRequestLobbyListFilterSlotsAvailable(int var1) {
      addRequestLobbyListFilterSlotsAvailable(this.pointer, var1);
   }

   public void addRequestLobbyListDistanceFilter(SteamMatchmaking$LobbyDistanceFilter var1) {
      addRequestLobbyListDistanceFilter(this.pointer, var1.ordinal());
   }

   public void addRequestLobbyListResultCountFilter(int var1) {
      addRequestLobbyListResultCountFilter(this.pointer, var1);
   }

   public void addRequestLobbyListCompatibleMembersFilter(SteamID var1) {
      addRequestLobbyListCompatibleMembersFilter(this.pointer, var1.handle);
   }

   public SteamID getLobbyByIndex(int var1) {
      return new SteamID(getLobbyByIndex(this.pointer, var1));
   }

   public SteamAPICall createLobby(SteamMatchmaking$LobbyType var1, int var2) {
      return new SteamAPICall(createLobby(this.pointer, this.callback, var1.ordinal(), var2));
   }

   public SteamAPICall joinLobby(SteamID var1) {
      return new SteamAPICall(joinLobby(this.pointer, this.callback, var1.handle));
   }

   public void leaveLobby(SteamID var1) {
      leaveLobby(this.pointer, var1.handle);
   }

   public boolean inviteUserToLobby(SteamID var1, SteamID var2) {
      return inviteUserToLobby(this.pointer, var1.handle, var2.handle);
   }

   public int getNumLobbyMembers(SteamID var1) {
      return getNumLobbyMembers(this.pointer, var1.handle);
   }

   public SteamID getLobbyMemberByIndex(SteamID var1, int var2) {
      return new SteamID(getLobbyMemberByIndex(this.pointer, var1.handle, var2));
   }

   public String getLobbyData(SteamID var1, String var2) {
      return getLobbyData(this.pointer, var1.handle, var2);
   }

   public boolean setLobbyData(SteamID var1, String var2, String var3) {
      return setLobbyData(this.pointer, var1.handle, var2, var3);
   }

   public boolean setLobbyData(SteamID var1, SteamMatchMakingKeyValuePair var2) {
      return setLobbyData(this.pointer, var1.handle, var2.getKey(), var2.getValue());
   }

   public int getLobbyDataCount(SteamID var1) {
      return getLobbyDataCount(this.pointer, var1.handle);
   }

   public boolean getLobbyDataByIndex(SteamID var1, int var2, SteamMatchMakingKeyValuePair var3) {
      return getLobbyDataByIndex(this.pointer, var1.handle, var2, var3);
   }

   public boolean deleteLobbyData(SteamID var1, String var2) {
      return deleteLobbyData(this.pointer, var1.handle, var2);
   }

   public boolean sendLobbyChatMsg(SteamID var1, ByteBuffer var2) {
      int var3 = var2.position();
      int var4 = var2.limit() - var3;
      return this.sendLobbyChatMsg(var1, var2, var3, var4);
   }

   public boolean sendLobbyChatMsg(SteamID var1, ByteBuffer var2, int var3, int var4) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else if(var2.capacity() < var3 + var4) {
         throw new SteamException("Buffer capacity exceeded!");
      } else {
         return sendLobbyChatMsg(this.pointer, var1.handle, var2, var3, var4);
      }
   }

   public boolean sendLobbyChatMsg(SteamID var1, String var2) {
      return sendLobbyChatMsg(this.pointer, var1.handle, var2);
   }

   public int getLobbyChatEntry(SteamID var1, int var2, SteamMatchmaking$ChatEntry var3, ByteBuffer var4) {
      int var5 = var4.position();
      int var6 = var4.limit() - var5;
      return this.getLobbyChatEntry(var1, var2, var3, var4, var5, var6);
   }

   public int getLobbyChatEntry(SteamID var1, int var2, SteamMatchmaking$ChatEntry var3, ByteBuffer var4, int var5, int var6) {
      if(!var4.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else if(var4.capacity() < var5 + var6) {
         throw new SteamException("Buffer capacity exceeded!");
      } else {
         int var7 = getLobbyChatEntry(this.pointer, var1.handle, var2, var3, var4, var5, var6);
         if(var7 >= 0) {
            var4.position(var5);
            var4.limit(var5 + var7);
         }

         return var7;
      }
   }

   public boolean requestLobbyData(SteamID var1) {
      return requestLobbyData(this.pointer, var1.handle);
   }

   public boolean setLobbyMemberLimit(SteamID var1, int var2) {
      return setLobbyMemberLimit(this.pointer, var1.handle, var2);
   }

   public boolean getLobbyMemberLimit(SteamID var1) {
      return getLobbyMemberLimit(this.pointer, var1.handle);
   }

   public boolean setLobbyType(SteamID var1, SteamMatchmaking$LobbyType var2) {
      return setLobbyType(this.pointer, var1.handle, var2.ordinal());
   }

   public boolean setLobbyJoinable(SteamID var1, boolean var2) {
      return setLobbyJoinable(this.pointer, var1.handle, var2);
   }

   public SteamID getLobbyOwner(SteamID var1) {
      return new SteamID(getLobbyOwner(this.pointer, var1.handle));
   }

   public boolean setLobbyOwner(SteamID var1, SteamID var2) {
      return setLobbyOwner(this.pointer, var1.handle, var2.handle);
   }

   public boolean setLinkedLobby(SteamID var1, SteamID var2) {
      return setLinkedLobby(this.pointer, var1.handle, var2.handle);
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
