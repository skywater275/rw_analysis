package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;

class SteamUGCCallbackAdapter extends SteamCallbackAdapter {

   SteamUGCCallbackAdapter(SteamUGCCallback var1) {
      super(var1);
   }

   void onUGCQueryCompleted(long var1, int var3, int var4, boolean var5, int var6) {
      ((SteamUGCCallback)this.callback).onUGCQueryCompleted(new SteamUGCQuery(var1), var3, var4, var5, SteamResult.byValue(var6));
   }

   void onSubscribeItem(long var1, int var3) {
      ((SteamUGCCallback)this.callback).onSubscribeItem(new SteamPublishedFileID(var1), SteamResult.byValue(var3));
   }

   void onUnsubscribeItem(long var1, int var3) {
      ((SteamUGCCallback)this.callback).onUnsubscribeItem(new SteamPublishedFileID(var1), SteamResult.byValue(var3));
   }

   void onRequestUGCDetails(long var1, int var3, String var4, String var5, long var6, long var8, String var10, boolean var11, int var12, int var13, long var14, int var16, int var17) {
      SteamUGCDetails var18 = new SteamUGCDetails();
      var18.publishedFileID = var1;
      var18.result = var3;
      var18.title = var4;
      var18.description = var5;
      var18.fileHandle = var6;
      var18.previewFileHandle = var8;
      var18.fileName = var10;
      var18.votesUp = var12;
      var18.votesDown = var13;
      var18.ownerID = var14;
      var18.timeCreated = var16;
      var18.timeUpdated = var17;
      ((SteamUGCCallback)this.callback).onRequestUGCDetails(var18, SteamResult.byValue(var3));
   }

   void onCreateItem(long var1, boolean var3, int var4) {
      ((SteamUGCCallback)this.callback).onCreateItem(new SteamPublishedFileID(var1), var3, SteamResult.byValue(var4));
   }

   void onSubmitItemUpdate(boolean var1, int var2) {
      ((SteamUGCCallback)this.callback).onSubmitItemUpdate(var1, SteamResult.byValue(var2));
   }

   void onDownloadItemResult(int var1, long var2, int var4) {
      ((SteamUGCCallback)this.callback).onDownloadItemResult(var1, new SteamPublishedFileID(var2), SteamResult.byValue(var4));
   }

   void onUserFavoriteItemsListChanged(long var1, boolean var3, int var4) {
      ((SteamUGCCallback)this.callback).onUserFavoriteItemsListChanged(new SteamPublishedFileID(var1), var3, SteamResult.byValue(var4));
   }

   void onSetUserItemVote(long var1, boolean var3, int var4) {
      ((SteamUGCCallback)this.callback).onSetUserItemVote(new SteamPublishedFileID(var1), var3, SteamResult.byValue(var4));
   }

   void onGetUserItemVote(long var1, boolean var3, boolean var4, boolean var5, int var6) {
      ((SteamUGCCallback)this.callback).onGetUserItemVote(new SteamPublishedFileID(var1), var3, var4, var5, SteamResult.byValue(var6));
   }

   void onStartPlaytimeTracking(int var1) {
      ((SteamUGCCallback)this.callback).onStartPlaytimeTracking(SteamResult.byValue(var1));
   }

   void onStopPlaytimeTracking(int var1) {
      ((SteamUGCCallback)this.callback).onStopPlaytimeTracking(SteamResult.byValue(var1));
   }

   void onStopPlaytimeTrackingForAllItems(int var1) {
      ((SteamUGCCallback)this.callback).onStopPlaytimeTrackingForAllItems(SteamResult.byValue(var1));
   }
}
