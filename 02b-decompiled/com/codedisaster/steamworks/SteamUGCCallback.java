package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;

public interface SteamUGCCallback {

   void onUGCQueryCompleted(SteamUGCQuery var1, int var2, int var3, boolean var4, SteamResult var5);

   void onSubscribeItem(SteamPublishedFileID var1, SteamResult var2);

   void onUnsubscribeItem(SteamPublishedFileID var1, SteamResult var2);

   void onRequestUGCDetails(SteamUGCDetails var1, SteamResult var2);

   void onCreateItem(SteamPublishedFileID var1, boolean var2, SteamResult var3);

   void onSubmitItemUpdate(boolean var1, SteamResult var2);

   void onDownloadItemResult(int var1, SteamPublishedFileID var2, SteamResult var3);

   void onUserFavoriteItemsListChanged(SteamPublishedFileID var1, boolean var2, SteamResult var3);

   void onSetUserItemVote(SteamPublishedFileID var1, boolean var2, SteamResult var3);

   void onGetUserItemVote(SteamPublishedFileID var1, boolean var2, boolean var3, boolean var4, SteamResult var5);

   void onStartPlaytimeTracking(SteamResult var1);

   void onStopPlaytimeTracking(SteamResult var1);

   void onStopPlaytimeTrackingForAllItems(SteamResult var1);
}
