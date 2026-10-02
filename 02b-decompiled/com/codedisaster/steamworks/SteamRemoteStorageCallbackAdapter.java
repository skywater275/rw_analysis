package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCHandle;

class SteamRemoteStorageCallbackAdapter extends SteamCallbackAdapter {

   SteamRemoteStorageCallbackAdapter(SteamRemoteStorageCallback var1) {
      super(var1);
   }

   void onFileShareResult(long var1, String var3, int var4) {
      ((SteamRemoteStorageCallback)this.callback).onFileShareResult(new SteamUGCHandle(var1), var3, SteamResult.byValue(var4));
   }

   void onDownloadUGCResult(long var1, int var3) {
      ((SteamRemoteStorageCallback)this.callback).onDownloadUGCResult(new SteamUGCHandle(var1), SteamResult.byValue(var3));
   }

   void onPublishFileResult(long var1, boolean var3, int var4) {
      ((SteamRemoteStorageCallback)this.callback).onPublishFileResult(new SteamPublishedFileID(var1), var3, SteamResult.byValue(var4));
   }

   void onUpdatePublishedFileResult(long var1, boolean var3, int var4) {
      ((SteamRemoteStorageCallback)this.callback).onUpdatePublishedFileResult(new SteamPublishedFileID(var1), var3, SteamResult.byValue(var4));
   }

   void onPublishedFileSubscribed(long var1, int var3) {
      ((SteamRemoteStorageCallback)this.callback).onPublishedFileSubscribed(new SteamPublishedFileID(var1), var3);
   }

   void onPublishedFileUnsubscribed(long var1, int var3) {
      ((SteamRemoteStorageCallback)this.callback).onPublishedFileUnsubscribed(new SteamPublishedFileID(var1), var3);
   }

   void onPublishedFileDeleted(long var1, int var3) {
      ((SteamRemoteStorageCallback)this.callback).onPublishedFileDeleted(new SteamPublishedFileID(var1), var3);
   }

   void onFileWriteAsyncComplete(int var1) {
      ((SteamRemoteStorageCallback)this.callback).onFileWriteAsyncComplete(SteamResult.byValue(var1));
   }

   void onFileReadAsyncComplete(long var1, int var3, int var4, int var5) {
      ((SteamRemoteStorageCallback)this.callback).onFileReadAsyncComplete(new SteamAPICall(var1), SteamResult.byValue(var3), var4, var5);
   }
}
