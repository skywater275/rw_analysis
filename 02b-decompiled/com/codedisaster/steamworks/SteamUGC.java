package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorage$PublishedFileVisibility;
import com.codedisaster.steamworks.SteamRemoteStorage$WorkshopFileType;
import com.codedisaster.steamworks.SteamUGC$ItemAdditionalPreview;
import com.codedisaster.steamworks.SteamUGC$ItemDownloadInfo;
import com.codedisaster.steamworks.SteamUGC$ItemInstallInfo;
import com.codedisaster.steamworks.SteamUGC$ItemState;
import com.codedisaster.steamworks.SteamUGC$ItemStatistic;
import com.codedisaster.steamworks.SteamUGC$ItemUpdateInfo;
import com.codedisaster.steamworks.SteamUGC$ItemUpdateStatus;
import com.codedisaster.steamworks.SteamUGC$MatchingUGCType;
import com.codedisaster.steamworks.SteamUGC$UGCQueryType;
import com.codedisaster.steamworks.SteamUGC$UserUGCList;
import com.codedisaster.steamworks.SteamUGC$UserUGCListSortOrder;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCCallbackAdapter;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;
import com.codedisaster.steamworks.SteamUGCUpdateHandle;
import java.util.Collection;
import java.util.Iterator;

public class SteamUGC extends SteamInterface {

   public SteamUGC(SteamUGCCallback var1) {
      super(SteamAPI.getSteamUGCPointer(), createCallback(new SteamUGCCallbackAdapter(var1)));
   }

   public SteamUGCQuery createQueryUserUGCRequest(long var1, SteamUGC$UserUGCList var3, SteamUGC$MatchingUGCType var4, SteamUGC$UserUGCListSortOrder var5, int var6, int var7, int var8) {
      return new SteamUGCQuery(createQueryUserUGCRequest(this.pointer, var1, var3.ordinal(), SteamUGC$MatchingUGCType.access$000(var4), var5.ordinal(), var6, var7, var8));
   }

   public SteamUGCQuery createQueryAllUGCRequest(SteamUGC$UGCQueryType var1, SteamUGC$MatchingUGCType var2, int var3, int var4, int var5) {
      return new SteamUGCQuery(createQueryAllUGCRequest(this.pointer, var1.ordinal(), SteamUGC$MatchingUGCType.access$000(var2), var3, var4, var5));
   }

   public SteamUGCQuery createQueryUGCDetailsRequest(SteamPublishedFileID var1) {
      long[] var2 = new long[]{var1.handle};
      return new SteamUGCQuery(createQueryUGCDetailsRequest(this.pointer, var2, 1));
   }

   public SteamUGCQuery createQueryUGCDetailsRequest(Collection var1) {
      int var2 = var1.size();
      long[] var3 = new long[var2];
      int var4 = 0;

      SteamPublishedFileID var6;
      for(Iterator var5 = var1.iterator(); var5.hasNext(); var3[var4++] = var6.handle) {
         var6 = (SteamPublishedFileID)var5.next();
      }

      return new SteamUGCQuery(createQueryUGCDetailsRequest(this.pointer, var3, var2));
   }

   public SteamAPICall sendQueryUGCRequest(SteamUGCQuery var1) {
      return new SteamAPICall(sendQueryUGCRequest(this.pointer, this.callback, var1.handle));
   }

   public boolean getQueryUGCResult(SteamUGCQuery var1, int var2, SteamUGCDetails var3) {
      return getQueryUGCResult(this.pointer, var1.handle, var2, var3);
   }

   public String getQueryUGCPreviewURL(SteamUGCQuery var1, int var2) {
      return getQueryUGCPreviewURL(this.pointer, var1.handle, var2);
   }

   public String getQueryUGCMetadata(SteamUGCQuery var1, int var2) {
      return getQueryUGCMetadata(this.pointer, var1.handle, var2);
   }

   public long getQueryUGCStatistic(SteamUGCQuery var1, int var2, SteamUGC$ItemStatistic var3) {
      return getQueryUGCStatistic(this.pointer, var1.handle, var2, var3.ordinal());
   }

   public int getQueryUGCNumAdditionalPreviews(SteamUGCQuery var1, int var2) {
      return getQueryUGCNumAdditionalPreviews(this.pointer, var1.handle, var2);
   }

   public boolean getQueryUGCAdditionalPreview(SteamUGCQuery var1, int var2, int var3, SteamUGC$ItemAdditionalPreview var4) {
      return getQueryUGCAdditionalPreview(this.pointer, var1.handle, var2, var3, var4);
   }

   public int getQueryUGCNumKeyValueTags(SteamUGCQuery var1, int var2) {
      return getQueryUGCNumKeyValueTags(this.pointer, var1.handle, var2);
   }

   public boolean getQueryUGCKeyValueTag(SteamUGCQuery var1, int var2, int var3, String[] var4) {
      return getQueryUGCKeyValueTag(this.pointer, var1.handle, var2, var3, var4);
   }

   public boolean releaseQueryUserUGCRequest(SteamUGCQuery var1) {
      return releaseQueryUserUGCRequest(this.pointer, var1.handle);
   }

   public boolean addRequiredTag(SteamUGCQuery var1, String var2) {
      return addRequiredTag(this.pointer, var1.handle, var2);
   }

   public boolean addExcludedTag(SteamUGCQuery var1, String var2) {
      return addExcludedTag(this.pointer, var1.handle, var2);
   }

   public boolean setReturnOnlyIDs(SteamUGCQuery var1, boolean var2) {
      return setReturnOnlyIDs(this.pointer, var1.handle, var2);
   }

   public boolean setReturnKeyValueTags(SteamUGCQuery var1, boolean var2) {
      return setReturnKeyValueTags(this.pointer, var1.handle, var2);
   }

   public boolean setReturnLongDescription(SteamUGCQuery var1, boolean var2) {
      return setReturnLongDescription(this.pointer, var1.handle, var2);
   }

   public boolean setReturnMetadata(SteamUGCQuery var1, boolean var2) {
      return setReturnMetadata(this.pointer, var1.handle, var2);
   }

   public boolean setReturnChildren(SteamUGCQuery var1, boolean var2) {
      return setReturnChildren(this.pointer, var1.handle, var2);
   }

   public boolean setReturnAdditionalPreviews(SteamUGCQuery var1, boolean var2) {
      return setReturnAdditionalPreviews(this.pointer, var1.handle, var2);
   }

   public boolean setReturnTotalOnly(SteamUGCQuery var1, boolean var2) {
      return setReturnTotalOnly(this.pointer, var1.handle, var2);
   }

   public boolean setLanguage(SteamUGCQuery var1, String var2) {
      return setLanguage(this.pointer, var1.handle, var2);
   }

   public boolean setAllowCachedResponse(SteamUGCQuery var1, int var2) {
      return setAllowCachedResponse(this.pointer, var1.handle, var2);
   }

   public boolean setCloudFileNameFilter(SteamUGCQuery var1, String var2) {
      return setCloudFileNameFilter(this.pointer, var1.handle, var2);
   }

   public boolean setMatchAnyTag(SteamUGCQuery var1, boolean var2) {
      return setMatchAnyTag(this.pointer, var1.handle, var2);
   }

   public boolean setSearchText(SteamUGCQuery var1, String var2) {
      return setSearchText(this.pointer, var1.handle, var2);
   }

   public boolean setRankedByTrendDays(SteamUGCQuery var1, int var2) {
      return setRankedByTrendDays(this.pointer, var1.handle, var2);
   }

   public boolean addRequiredKeyValueTag(SteamUGCQuery var1, String var2, String var3) {
      return addRequiredKeyValueTag(this.pointer, var1.handle, var2, var3);
   }

   @Deprecated
   public SteamAPICall requestUGCDetails(SteamPublishedFileID var1, int var2) {
      return new SteamAPICall(requestUGCDetails(this.pointer, this.callback, var1.handle, var2));
   }

   public SteamAPICall createItem(int var1, SteamRemoteStorage$WorkshopFileType var2) {
      return new SteamAPICall(createItem(this.pointer, this.callback, var1, var2.ordinal()));
   }

   public SteamUGCUpdateHandle startItemUpdate(int var1, SteamPublishedFileID var2) {
      return new SteamUGCUpdateHandle(startItemUpdate(this.pointer, var1, var2.handle));
   }

   public boolean setItemTitle(SteamUGCUpdateHandle var1, String var2) {
      return setItemTitle(this.pointer, var1.handle, var2);
   }

   public boolean setItemDescription(SteamUGCUpdateHandle var1, String var2) {
      return setItemDescription(this.pointer, var1.handle, var2);
   }

   public boolean setItemUpdateLanguage(SteamUGCUpdateHandle var1, String var2) {
      return setItemUpdateLanguage(this.pointer, var1.handle, var2);
   }

   public boolean setItemMetadata(SteamUGCUpdateHandle var1, String var2) {
      return setItemMetadata(this.pointer, var1.handle, var2);
   }

   public boolean setItemVisibility(SteamUGCUpdateHandle var1, SteamRemoteStorage$PublishedFileVisibility var2) {
      return setItemVisibility(this.pointer, var1.handle, var2.ordinal());
   }

   public boolean setItemTags(SteamUGCUpdateHandle var1, String[] var2) {
      return setItemTags(this.pointer, var1.handle, var2, var2.length);
   }

   public boolean setItemContent(SteamUGCUpdateHandle var1, String var2) {
      return setItemContent(this.pointer, var1.handle, var2);
   }

   public boolean setItemPreview(SteamUGCUpdateHandle var1, String var2) {
      return setItemPreview(this.pointer, var1.handle, var2);
   }

   public boolean removeItemKeyValueTags(SteamUGCUpdateHandle var1, String var2) {
      return removeItemKeyValueTags(this.pointer, var1.handle, var2);
   }

   public boolean addItemKeyValueTag(SteamUGCUpdateHandle var1, String var2, String var3) {
      return addItemKeyValueTag(this.pointer, var1.handle, var2, var3);
   }

   public SteamAPICall submitItemUpdate(SteamUGCUpdateHandle var1, String var2) {
      return new SteamAPICall(submitItemUpdate(this.pointer, this.callback, var1.handle, var2));
   }

   public SteamUGC$ItemUpdateStatus getItemUpdateProgress(SteamUGCUpdateHandle var1, SteamUGC$ItemUpdateInfo var2) {
      long[] var3 = new long[2];
      SteamUGC$ItemUpdateStatus var4 = SteamUGC$ItemUpdateStatus.byOrdinal(getItemUpdateProgress(this.pointer, var1.handle, var3));
      var2.bytesProcessed = var3[0];
      var2.bytesTotal = var3[1];
      return var4;
   }

   public SteamAPICall setUserItemVote(SteamPublishedFileID var1, boolean var2) {
      return new SteamAPICall(setUserItemVote(this.pointer, this.callback, var1.handle, var2));
   }

   public SteamAPICall getUserItemVote(SteamPublishedFileID var1) {
      return new SteamAPICall(getUserItemVote(this.pointer, this.callback, var1.handle));
   }

   public SteamAPICall addItemToFavorites(int var1, SteamPublishedFileID var2) {
      return new SteamAPICall(addItemToFavorites(this.pointer, this.callback, var1, var2.handle));
   }

   public SteamAPICall removeItemFromFavorites(int var1, SteamPublishedFileID var2) {
      return new SteamAPICall(removeItemFromFavorites(this.pointer, this.callback, var1, var2.handle));
   }

   public SteamAPICall subscribeItem(SteamPublishedFileID var1) {
      return new SteamAPICall(subscribeItem(this.pointer, this.callback, var1.handle));
   }

   public SteamAPICall unsubscribeItem(SteamPublishedFileID var1) {
      return new SteamAPICall(unsubscribeItem(this.pointer, this.callback, var1.handle));
   }

   public int getNumSubscribedItems() {
      return getNumSubscribedItems(this.pointer);
   }

   public int getSubscribedItems(SteamPublishedFileID[] var1) {
      long[] var2 = new long[var1.length];
      int var3 = getSubscribedItems(this.pointer, var2, var1.length);

      for(int var4 = 0; var4 < var3; ++var4) {
         var1[var4] = new SteamPublishedFileID(var2[var4]);
      }

      return var3;
   }

   public Collection getItemState(SteamPublishedFileID var1) {
      return SteamUGC$ItemState.fromBits(getItemState(this.pointer, var1.handle));
   }

   public boolean getItemInstallInfo(SteamPublishedFileID var1, SteamUGC$ItemInstallInfo var2) {
      return getItemInstallInfo(this.pointer, var1.handle, var2);
   }

   public boolean getItemDownloadInfo(SteamPublishedFileID var1, SteamUGC$ItemDownloadInfo var2) {
      long[] var3 = new long[2];
      if(getItemDownloadInfo(this.pointer, var1.handle, var3)) {
         var2.bytesDownloaded = var3[0];
         var2.bytesTotal = var3[1];
         return true;
      } else {
         return false;
      }
   }

   public boolean downloadItem(SteamPublishedFileID var1, boolean var2) {
      return downloadItem(this.pointer, var1.handle, var2);
   }

   public boolean initWorkshopForGameServer(int var1, String var2) {
      return initWorkshopForGameServer(this.pointer, var1, var2);
   }

   public void suspendDownloads(boolean var1) {
      suspendDownloads(this.pointer, var1);
   }

   public SteamAPICall startPlaytimeTracking(SteamPublishedFileID[] var1) {
      long[] var2 = new long[var1.length];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         var2[var3] = var1[var3].handle;
      }

      return new SteamAPICall(startPlaytimeTracking(this.pointer, this.callback, var2, var2.length));
   }

   public SteamAPICall stopPlaytimeTracking(SteamPublishedFileID[] var1) {
      long[] var2 = new long[var1.length];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         var2[var3] = var1[var3].handle;
      }

      return new SteamAPICall(stopPlaytimeTracking(this.pointer, this.callback, var2, var2.length));
   }

   public SteamAPICall stopPlaytimeTrackingForAllItems() {
      return new SteamAPICall(stopPlaytimeTrackingForAllItems(this.pointer, this.callback));
   }

   private static native long createCallback(SteamUGCCallbackAdapter var0);

   private static native long createQueryUserUGCRequest(long var0, long var2, int var4, int var5, int var6, int var7, int var8, int var9);

   private static native long createQueryAllUGCRequest(long var0, int var2, int var3, int var4, int var5, int var6);

   private static native long createQueryUGCDetailsRequest(long var0, long[] var2, int var3);

   private static native long sendQueryUGCRequest(long var0, long var2, long var4);

   private static native boolean getQueryUGCResult(long var0, long var2, int var4, SteamUGCDetails var5);

   private static native String getQueryUGCPreviewURL(long var0, long var2, int var4);

   private static native String getQueryUGCMetadata(long var0, long var2, int var4);

   private static native long getQueryUGCStatistic(long var0, long var2, int var4, int var5);

   private static native int getQueryUGCNumAdditionalPreviews(long var0, long var2, int var4);

   private static native boolean getQueryUGCAdditionalPreview(long var0, long var2, int var4, int var5, SteamUGC$ItemAdditionalPreview var6);

   private static native int getQueryUGCNumKeyValueTags(long var0, long var2, int var4);

   private static native boolean getQueryUGCKeyValueTag(long var0, long var2, int var4, int var5, String[] var6);

   private static native boolean releaseQueryUserUGCRequest(long var0, long var2);

   private static native boolean addRequiredTag(long var0, long var2, String var4);

   private static native boolean addExcludedTag(long var0, long var2, String var4);

   private static native boolean setReturnOnlyIDs(long var0, long var2, boolean var4);

   private static native boolean setReturnKeyValueTags(long var0, long var2, boolean var4);

   private static native boolean setReturnLongDescription(long var0, long var2, boolean var4);

   private static native boolean setReturnMetadata(long var0, long var2, boolean var4);

   private static native boolean setReturnChildren(long var0, long var2, boolean var4);

   private static native boolean setReturnAdditionalPreviews(long var0, long var2, boolean var4);

   private static native boolean setReturnTotalOnly(long var0, long var2, boolean var4);

   private static native boolean setLanguage(long var0, long var2, String var4);

   private static native boolean setAllowCachedResponse(long var0, long var2, int var4);

   private static native boolean setCloudFileNameFilter(long var0, long var2, String var4);

   private static native boolean setMatchAnyTag(long var0, long var2, boolean var4);

   private static native boolean setSearchText(long var0, long var2, String var4);

   private static native boolean setRankedByTrendDays(long var0, long var2, int var4);

   private static native boolean addRequiredKeyValueTag(long var0, long var2, String var4, String var5);

   private static native long requestUGCDetails(long var0, long var2, long var4, int var6);

   private static native long createItem(long var0, long var2, int var4, int var5);

   private static native long startItemUpdate(long var0, int var2, long var3);

   private static native boolean setItemTitle(long var0, long var2, String var4);

   private static native boolean setItemDescription(long var0, long var2, String var4);

   private static native boolean setItemUpdateLanguage(long var0, long var2, String var4);

   private static native boolean setItemMetadata(long var0, long var2, String var4);

   private static native boolean setItemVisibility(long var0, long var2, int var4);

   private static native boolean setItemTags(long var0, long var2, String[] var4, int var5);

   private static native boolean setItemContent(long var0, long var2, String var4);

   private static native boolean setItemPreview(long var0, long var2, String var4);

   private static native boolean removeItemKeyValueTags(long var0, long var2, String var4);

   private static native boolean addItemKeyValueTag(long var0, long var2, String var4, String var5);

   private static native long submitItemUpdate(long var0, long var2, long var4, String var6);

   private static native int getItemUpdateProgress(long var0, long var2, long[] var4);

   private static native long setUserItemVote(long var0, long var2, long var4, boolean var6);

   private static native long getUserItemVote(long var0, long var2, long var4);

   private static native long addItemToFavorites(long var0, long var2, int var4, long var5);

   private static native long removeItemFromFavorites(long var0, long var2, int var4, long var5);

   private static native long subscribeItem(long var0, long var2, long var4);

   private static native long unsubscribeItem(long var0, long var2, long var4);

   private static native int getNumSubscribedItems(long var0);

   private static native int getSubscribedItems(long var0, long[] var2, int var3);

   private static native int getItemState(long var0, long var2);

   private static native boolean getItemInstallInfo(long var0, long var2, SteamUGC$ItemInstallInfo var4);

   private static native boolean getItemDownloadInfo(long var0, long var2, long[] var4);

   private static native boolean downloadItem(long var0, long var2, boolean var4);

   private static native boolean initWorkshopForGameServer(long var0, int var2, String var3);

   private static native void suspendDownloads(long var0, boolean var2);

   private static native long startPlaytimeTracking(long var0, long var2, long[] var4, int var5);

   private static native long stopPlaytimeTracking(long var0, long var2, long[] var4, int var5);

   private static native long stopPlaytimeTrackingForAllItems(long var0, long var2);
}
