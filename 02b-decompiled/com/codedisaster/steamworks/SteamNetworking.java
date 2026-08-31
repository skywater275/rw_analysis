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

public class SteamNetworking extends SteamInterface {

   private final int[] tmpIntResult = new int[1];
   private final long[] tmpLongResult = new long[1];


   public SteamNetworking(SteamNetworkingCallback var1, SteamNetworking$API var2) {
      super(var2 == SteamNetworking$API.Client?SteamAPI.getSteamNetworkingPointer():SteamGameServerAPI.getSteamGameServerNetworkingPointer(), createCallback(new SteamNetworkingCallbackAdapter(var1), var2 == SteamNetworking$API.Client));
   }

   public boolean sendP2PPacket(SteamID var1, ByteBuffer var2, SteamNetworking$P2PSend var3, int var4) {
      int var5 = var2.position();
      int var6 = var2.limit() - var5;
      return this.sendP2PPacket(var1, var2, var5, var6, var3, var4);
   }

   public boolean sendP2PPacket(SteamID var1, ByteBuffer var2, int var3, int var4, SteamNetworking$P2PSend var5, int var6) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else if(var2.capacity() < var3 + var4) {
         throw new SteamException("Buffer capacity exceeded!");
      } else {
         return sendP2PPacket(this.pointer, var1.handle, var2, var3, var4, var5.ordinal(), var6);
      }
   }

   public int isP2PPacketAvailable(int var1) {
      return isP2PPacketAvailable(this.pointer, this.tmpIntResult, var1)?this.tmpIntResult[0]:0;
   }

   public int readP2PPacket(SteamID var1, ByteBuffer var2, int var3) {
      int var4 = var2.position();
      int var5 = var2.limit() - var4;
      return this.readP2PPacket(var1, var2, var4, var5, var3);
   }

   public int readP2PPacket(SteamID var1, ByteBuffer var2, int var3, int var4, int var5) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else if(var2.capacity() < var3 + var4) {
         throw new SteamException("Buffer capacity exceeded!");
      } else if(readP2PPacket(this.pointer, var2, var3, var4, this.tmpIntResult, this.tmpLongResult, var5)) {
         var2.position(var3);
         var2.limit(var3 + this.tmpIntResult[0]);
         var1.handle = this.tmpLongResult[0];
         return this.tmpIntResult[0];
      } else {
         return 0;
      }
   }

   public boolean acceptP2PSessionWithUser(SteamID var1) {
      return acceptP2PSessionWithUser(this.pointer, var1.handle);
   }

   public boolean closeP2PSessionWithUser(SteamID var1) {
      return closeP2PSessionWithUser(this.pointer, var1.handle);
   }

   public boolean closeP2PChannelWithUser(SteamID var1, int var2) {
      return closeP2PChannelWithUser(this.pointer, var1.handle, var2);
   }

   public boolean getP2PSessionState(SteamID var1, SteamNetworking$P2PSessionState var2) {
      return getP2PSessionState(this.pointer, var1.handle, var2);
   }

   public boolean allowP2PPacketRelay(boolean var1) {
      return allowP2PPacketRelay(this.pointer, var1);
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
