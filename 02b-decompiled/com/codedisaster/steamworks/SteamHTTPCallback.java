package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamHTTP$HTTPStatusCode;
import com.codedisaster.steamworks.SteamHTTPRequestHandle;

public interface SteamHTTPCallback {

   void onHTTPRequestCompleted(SteamHTTPRequestHandle var1, long var2, boolean var4, SteamHTTP$HTTPStatusCode var5, int var6);

   void onHTTPRequestHeadersReceived(SteamHTTPRequestHandle var1, long var2);

   void onHTTPRequestDataReceived(SteamHTTPRequestHandle var1, long var2, int var4, int var5);
}
