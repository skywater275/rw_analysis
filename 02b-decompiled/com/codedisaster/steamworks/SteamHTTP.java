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

public class SteamHTTP extends SteamInterface {

   public SteamHTTP(SteamHTTPCallback var1, SteamHTTP$API var2) {
      super(var2 == SteamHTTP$API.Client?SteamAPI.getSteamHTTPPointer():SteamGameServerAPI.getSteamGameServerHTTPPointer(), createCallback(new SteamHTTPCallbackAdapter(var1), var2 == SteamHTTP$API.Client));
   }

   public SteamHTTPRequestHandle createHTTPRequest(SteamHTTP$HTTPMethod var1, String var2) {
      return new SteamHTTPRequestHandle(createHTTPRequest(this.pointer, var1.ordinal(), var2));
   }

   public boolean setHTTPRequestContextValue(SteamHTTPRequestHandle var1, long var2) {
      return setHTTPRequestContextValue(this.pointer, var1.handle, var2);
   }

   public boolean setHTTPRequestNetworkActivityTimeout(SteamHTTPRequestHandle var1, int var2) {
      return setHTTPRequestNetworkActivityTimeout(this.pointer, var1.handle, var2);
   }

   public boolean setHTTPRequestHeaderValue(SteamHTTPRequestHandle var1, String var2, String var3) {
      return setHTTPRequestHeaderValue(this.pointer, var1.handle, var2, var3);
   }

   public boolean setHTTPRequestGetOrPostParameter(SteamHTTPRequestHandle var1, String var2, String var3) {
      return setHTTPRequestGetOrPostParameter(this.pointer, var1.handle, var2, var3);
   }

   public SteamAPICall sendHTTPRequest(SteamHTTPRequestHandle var1) {
      return new SteamAPICall(sendHTTPRequest(this.pointer, this.callback, var1.handle));
   }

   public SteamAPICall sendHTTPRequestAndStreamResponse(SteamHTTPRequestHandle var1) {
      return new SteamAPICall(sendHTTPRequestAndStreamResponse(this.pointer, var1.handle));
   }

   public int getHTTPResponseHeaderSize(SteamHTTPRequestHandle var1, String var2) {
      return getHTTPResponseHeaderSize(this.pointer, var1.handle, var2);
   }

   public boolean getHTTPResponseHeaderValue(SteamHTTPRequestHandle var1, String var2, ByteBuffer var3) {
      if(!var3.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var4 = var3.position();
         int var5 = var3.limit() - var4;
         return getHTTPResponseHeaderValue(this.pointer, var1.handle, var2, var3, var4, var5);
      }
   }

   public int getHTTPResponseBodySize(SteamHTTPRequestHandle var1) {
      return getHTTPResponseBodySize(this.pointer, var1.handle);
   }

   public boolean getHTTPResponseBodyData(SteamHTTPRequestHandle var1, ByteBuffer var2) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var3 = var2.position();
         int var4 = var2.limit() - var3;
         return getHTTPResponseBodyData(this.pointer, var1.handle, var2, var3, var4);
      }
   }

   public boolean getHTTPStreamingResponseBodyData(SteamHTTPRequestHandle var1, int var2, ByteBuffer var3) {
      if(!var3.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var4 = var3.position();
         int var5 = var3.limit() - var4;
         return getHTTPStreamingResponseBodyData(this.pointer, var1.handle, var2, var3, var4, var5);
      }
   }

   public boolean releaseHTTPRequest(SteamHTTPRequestHandle var1) {
      return releaseHTTPRequest(this.pointer, var1.handle);
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
