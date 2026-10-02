package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;

public class SteamNetworking$P2PSessionState {

   byte connectionActive;
   byte connecting;
   byte sessionError;
   byte usingRelay;
   int bytesQueuedForSend;
   int packetsQueuedForSend;
   int remoteIP;
   short remotePort;


   public boolean isConnectionActive() {
      return this.connectionActive != 0;
   }

   public boolean isConnecting() {
      return this.connecting != 0;
   }

   public SteamNetworking$P2PSessionError getLastSessionError() {
      return SteamNetworking$P2PSessionError.byOrdinal(this.sessionError);
   }

   public boolean isUsingRelay() {
      return this.usingRelay != 0;
   }

   public int getBytesQueuedForSend() {
      return this.bytesQueuedForSend;
   }

   public int getPacketsQueuedForSend() {
      return this.packetsQueuedForSend;
   }

   public int getRemoteIP() {
      return this.remoteIP;
   }

   public short getRemotePort() {
      return this.remotePort;
   }
}
