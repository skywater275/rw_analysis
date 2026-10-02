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

public class SteamGameServer extends SteamInterface {

   public SteamGameServer(SteamGameServerCallback var1) {
      super(SteamGameServerAPI.getSteamGameServerPointer(), createCallback(new SteamGameServerCallbackAdapter(var1)));
   }

   public void setProduct(String var1) {
      setProduct(this.pointer, var1);
   }

   public void setGameDescription(String var1) {
      setGameDescription(this.pointer, var1);
   }

   public void setModDir(String var1) {
      setModDir(this.pointer, var1);
   }

   public void setDedicatedServer(boolean var1) {
      setDedicatedServer(this.pointer, var1);
   }

   public void logOn(String var1) {
      logOn(this.pointer, var1);
   }

   public void logOnAnonymous() {
      logOnAnonymous(this.pointer);
   }

   public void logOff() {
      logOff(this.pointer);
   }

   public boolean isLoggedOn() {
      return isLoggedOn(this.pointer);
   }

   public boolean isSecure() {
      return isSecure(this.pointer);
   }

   public SteamID getSteamID() {
      return new SteamID(getSteamID(this.pointer));
   }

   public boolean wasRestartRequested() {
      return wasRestartRequested(this.pointer);
   }

   public void setMaxPlayerCount(int var1) {
      setMaxPlayerCount(this.pointer, var1);
   }

   public void setBotPlayerCount(int var1) {
      setBotPlayerCount(this.pointer, var1);
   }

   public void setServerName(String var1) {
      setServerName(this.pointer, var1);
   }

   public void setMapName(String var1) {
      setMapName(this.pointer, var1);
   }

   public void setPasswordProtected(boolean var1) {
      setPasswordProtected(this.pointer, var1);
   }

   public void setSpectatorPort(short var1) {
      setSpectatorPort(this.pointer, var1);
   }

   public void setSpectatorServerName(String var1) {
      setSpectatorServerName(this.pointer, var1);
   }

   public void clearAllKeyValues() {
      clearAllKeyValues(this.pointer);
   }

   public void setKeyValue(String var1, String var2) {
      setKeyValue(this.pointer, var1, var2);
   }

   public void setGameTags(String var1) {
      setGameTags(this.pointer, var1);
   }

   public void setGameData(String var1) {
      setGameData(this.pointer, var1);
   }

   public void setRegion(String var1) {
      setRegion(this.pointer, var1);
   }

   public SteamID sendUserConnectAndAuthenticate(int var1, Buffer var2, int var3, SteamID var4) {
      long[] var5 = new long[1];
      return sendUserConnectAndAuthenticate(this.pointer, var1, var2, var3, var5)?new SteamID(var5[0]):null;
   }

   public SteamID createUnauthenticatedUserConnection() {
      return new SteamID(createUnauthenticatedUserConnection(this.pointer));
   }

   public void sendUserDisconnect(SteamID var1) {
      sendUserDisconnect(this.pointer, var1.handle);
   }

   public boolean updateUserData(SteamID var1, String var2, int var3) {
      return updateUserData(this.pointer, var1.handle, var2, var3);
   }

   public SteamAuthTicket getAuthSessionTicket(Buffer var1, int[] var2) {
      if(!var1.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var3 = getAuthSessionTicket(this.pointer, var1, var1.capacity(), var2);
         if((long)var3 != 0L) {
            var1.limit(var2[0]);
         }

         return new SteamAuthTicket((long)var3);
      }
   }

   public SteamAuth$BeginAuthSessionResult beginAuthSession(Buffer var1, SteamID var2) {
      if(!var1.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var3 = beginAuthSession(this.pointer, var1, var1.limit(), var2.handle);
         return SteamAuth$BeginAuthSessionResult.byOrdinal(var3);
      }
   }

   public void endAuthSession(SteamID var1) {
      endAuthSession(this.pointer, var1.handle);
   }

   public void cancelAuthTicket(SteamAuthTicket var1) {
      cancelAuthTicket(this.pointer, (int)var1.handle);
   }

   public SteamAuth$UserHasLicenseForAppResult userHasLicenseForApp(SteamID var1, int var2) {
      return SteamAuth$UserHasLicenseForAppResult.byOrdinal(userHasLicenseForApp(this.pointer, var1.handle, var2));
   }

   public boolean requestUserGroupStatus(SteamID var1, SteamID var2) {
      return requestUserGroupStatus(this.pointer, var1.handle, var2.handle);
   }

   public int getPublicIP() {
      return getPublicIP(this.pointer);
   }

   public boolean handleIncomingPacket(Buffer var1, int var2, short var3) {
      return handleIncomingPacket(this.pointer, var1, var1.limit(), var2, var3);
   }

   public int getNextOutgoingPacket(Buffer var1, int[] var2, short[] var3) {
      return getNextOutgoingPacket(this.pointer, var1, var1.capacity(), var2, var3);
   }

   public void enableHeartbeats(boolean var1) {
      enableHeartbeats(this.pointer, var1);
   }

   public void setHeartbeatInterval(int var1) {
      setHeartbeatInterval(this.pointer, var1);
   }

   public void forceHeartbeat() {
      forceHeartbeat(this.pointer);
   }

   public SteamAPICall associateWithClan(SteamID var1) {
      return new SteamAPICall(associateWithClan(this.pointer, var1.handle));
   }

   public SteamAPICall computeNewPlayerCompatibility(SteamID var1) {
      return new SteamAPICall(computeNewPlayerCompatibility(this.pointer, var1.handle));
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
