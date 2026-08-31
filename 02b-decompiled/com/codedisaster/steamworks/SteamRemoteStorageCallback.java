package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCHandle;

public interface SteamRemoteStorageCallback {

   void onFileShareResult(SteamUGCHandle var1, String var2, SteamResult var3);

   void onDownloadUGCResult(SteamUGCHandle var1, SteamResult var2);

   void onPublishFileResult(SteamPublishedFileID var1, boolean var2, SteamResult var3);

   void onUpdatePublishedFileResult(SteamPublishedFileID var1, boolean var2, SteamResult var3);

   void onPublishedFileSubscribed(SteamPublishedFileID var1, int var2);

   void onPublishedFileUnsubscribed(SteamPublishedFileID var1, int var2);

   void onPublishedFileDeleted(SteamPublishedFileID var1, int var2);

   void onFileWriteAsyncComplete(SteamResult var1);

   void onFileReadAsyncComplete(SteamAPICall var1, SteamResult var2, int var3, int var4);
}
