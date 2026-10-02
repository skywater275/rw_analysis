/*
 * Decompiled with CFR 0.152.
 */
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

public class SteamUGC
extends SteamInterface {
    public SteamUGC(SteamUGCCallback steamUGCCallback) {
        super(SteamAPI.getSteamUGCPointer(), SteamUGC.createCallback(new SteamUGCCallbackAdapter(steamUGCCallback)));
    }

    public SteamUGCQuery createQueryUserUGCRequest(long l, SteamUGC$UserUGCList steamUGC$UserUGCList, SteamUGC$MatchingUGCType steamUGC$MatchingUGCType, SteamUGC$UserUGCListSortOrder steamUGC$UserUGCListSortOrder, int n, int n2, int n3) {
        return new SteamUGCQuery(SteamUGC.createQueryUserUGCRequest(this.pointer, l, steamUGC$UserUGCList.ordinal(), SteamUGC$MatchingUGCType.access$000(steamUGC$MatchingUGCType), steamUGC$UserUGCListSortOrder.ordinal(), n, n2, n3));
    }

    public SteamUGCQuery createQueryAllUGCRequest(SteamUGC$UGCQueryType steamUGC$UGCQueryType, SteamUGC$MatchingUGCType steamUGC$MatchingUGCType, int n, int n2, int n3) {
        return new SteamUGCQuery(SteamUGC.createQueryAllUGCRequest(this.pointer, steamUGC$UGCQueryType.ordinal(), SteamUGC$MatchingUGCType.access$000(steamUGC$MatchingUGCType), n, n2, n3));
    }

    public SteamUGCQuery createQueryUGCDetailsRequest(SteamPublishedFileID steamPublishedFileID) {
        long[] lArray = new long[]{steamPublishedFileID.handle};
        return new SteamUGCQuery(SteamUGC.createQueryUGCDetailsRequest(this.pointer, lArray, 1));
    }

    public SteamUGCQuery createQueryUGCDetailsRequest(Collection collection) {
        int n = collection.size();
        long[] lArray = new long[n];
        int n2 = 0;
        for (SteamPublishedFileID steamPublishedFileID : (Collection<SteamPublishedFileID>) collection) {
            lArray[n2++] = steamPublishedFileID.handle;
        }
        return new SteamUGCQuery(SteamUGC.createQueryUGCDetailsRequest(this.pointer, lArray, n));
    }

    public SteamAPICall sendQueryUGCRequest(SteamUGCQuery steamUGCQuery) {
        return new SteamAPICall(SteamUGC.sendQueryUGCRequest(this.pointer, this.callback, steamUGCQuery.handle));
    }

    public boolean getQueryUGCResult(SteamUGCQuery steamUGCQuery, int n, SteamUGCDetails steamUGCDetails) {
        return SteamUGC.getQueryUGCResult(this.pointer, steamUGCQuery.handle, n, steamUGCDetails);
    }

    public String getQueryUGCPreviewURL(SteamUGCQuery steamUGCQuery, int n) {
        return SteamUGC.getQueryUGCPreviewURL(this.pointer, steamUGCQuery.handle, n);
    }

    public String getQueryUGCMetadata(SteamUGCQuery steamUGCQuery, int n) {
        return SteamUGC.getQueryUGCMetadata(this.pointer, steamUGCQuery.handle, n);
    }

    public long getQueryUGCStatistic(SteamUGCQuery steamUGCQuery, int n, SteamUGC$ItemStatistic steamUGC$ItemStatistic) {
        return SteamUGC.getQueryUGCStatistic(this.pointer, steamUGCQuery.handle, n, steamUGC$ItemStatistic.ordinal());
    }

    public int getQueryUGCNumAdditionalPreviews(SteamUGCQuery steamUGCQuery, int n) {
        return SteamUGC.getQueryUGCNumAdditionalPreviews(this.pointer, steamUGCQuery.handle, n);
    }

    public boolean getQueryUGCAdditionalPreview(SteamUGCQuery steamUGCQuery, int n, int n2, SteamUGC$ItemAdditionalPreview itemAdditionalPreview) {
        return SteamUGC.getQueryUGCAdditionalPreview(this.pointer, steamUGCQuery.handle, n, n2, itemAdditionalPreview);
    }

    public int getQueryUGCNumKeyValueTags(SteamUGCQuery steamUGCQuery, int n) {
        return SteamUGC.getQueryUGCNumKeyValueTags(this.pointer, steamUGCQuery.handle, n);
    }

    public boolean getQueryUGCKeyValueTag(SteamUGCQuery steamUGCQuery, int n, int n2, String[] stringArray) {
        return SteamUGC.getQueryUGCKeyValueTag(this.pointer, steamUGCQuery.handle, n, n2, stringArray);
    }

    public boolean releaseQueryUserUGCRequest(SteamUGCQuery steamUGCQuery) {
        return SteamUGC.releaseQueryUserUGCRequest(this.pointer, steamUGCQuery.handle);
    }

    public boolean addRequiredTag(SteamUGCQuery steamUGCQuery, String string) {
        return SteamUGC.addRequiredTag(this.pointer, steamUGCQuery.handle, string);
    }

    public boolean addExcludedTag(SteamUGCQuery steamUGCQuery, String string) {
        return SteamUGC.addExcludedTag(this.pointer, steamUGCQuery.handle, string);
    }

    public boolean setReturnOnlyIDs(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnOnlyIDs(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setReturnKeyValueTags(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnKeyValueTags(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setReturnLongDescription(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnLongDescription(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setReturnMetadata(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnMetadata(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setReturnChildren(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnChildren(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setReturnAdditionalPreviews(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnAdditionalPreviews(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setReturnTotalOnly(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setReturnTotalOnly(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setLanguage(SteamUGCQuery steamUGCQuery, String string) {
        return SteamUGC.setLanguage(this.pointer, steamUGCQuery.handle, string);
    }

    public boolean setAllowCachedResponse(SteamUGCQuery steamUGCQuery, int n) {
        return SteamUGC.setAllowCachedResponse(this.pointer, steamUGCQuery.handle, n);
    }

    public boolean setCloudFileNameFilter(SteamUGCQuery steamUGCQuery, String string) {
        return SteamUGC.setCloudFileNameFilter(this.pointer, steamUGCQuery.handle, string);
    }

    public boolean setMatchAnyTag(SteamUGCQuery steamUGCQuery, boolean bl) {
        return SteamUGC.setMatchAnyTag(this.pointer, steamUGCQuery.handle, bl);
    }

    public boolean setSearchText(SteamUGCQuery steamUGCQuery, String string) {
        return SteamUGC.setSearchText(this.pointer, steamUGCQuery.handle, string);
    }

    public boolean setRankedByTrendDays(SteamUGCQuery steamUGCQuery, int n) {
        return SteamUGC.setRankedByTrendDays(this.pointer, steamUGCQuery.handle, n);
    }

    public boolean addRequiredKeyValueTag(SteamUGCQuery steamUGCQuery, String string, String string2) {
        return SteamUGC.addRequiredKeyValueTag(this.pointer, steamUGCQuery.handle, string, string2);
    }

    @Deprecated
    public SteamAPICall requestUGCDetails(SteamPublishedFileID steamPublishedFileID, int n) {
        return new SteamAPICall(SteamUGC.requestUGCDetails(this.pointer, this.callback, steamPublishedFileID.handle, n));
    }

    public SteamAPICall createItem(int n, SteamRemoteStorage$WorkshopFileType steamRemoteStorage$WorkshopFileType) {
        return new SteamAPICall(SteamUGC.createItem(this.pointer, this.callback, n, steamRemoteStorage$WorkshopFileType.ordinal()));
    }

    public SteamUGCUpdateHandle startItemUpdate(int n, SteamPublishedFileID steamPublishedFileID) {
        return new SteamUGCUpdateHandle(SteamUGC.startItemUpdate(this.pointer, n, steamPublishedFileID.handle));
    }

    public boolean setItemTitle(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.setItemTitle(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean setItemDescription(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.setItemDescription(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean setItemUpdateLanguage(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.setItemUpdateLanguage(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean setItemMetadata(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.setItemMetadata(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean setItemVisibility(SteamUGCUpdateHandle steamUGCUpdateHandle, SteamRemoteStorage$PublishedFileVisibility steamRemoteStorage$PublishedFileVisibility) {
        return SteamUGC.setItemVisibility(this.pointer, steamUGCUpdateHandle.handle, steamRemoteStorage$PublishedFileVisibility.ordinal());
    }

    public boolean setItemTags(SteamUGCUpdateHandle steamUGCUpdateHandle, String[] stringArray) {
        return SteamUGC.setItemTags(this.pointer, steamUGCUpdateHandle.handle, stringArray, stringArray.length);
    }

    public boolean setItemContent(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.setItemContent(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean setItemPreview(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.setItemPreview(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean removeItemKeyValueTags(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return SteamUGC.removeItemKeyValueTags(this.pointer, steamUGCUpdateHandle.handle, string);
    }

    public boolean addItemKeyValueTag(SteamUGCUpdateHandle steamUGCUpdateHandle, String string, String string2) {
        return SteamUGC.addItemKeyValueTag(this.pointer, steamUGCUpdateHandle.handle, string, string2);
    }

    public SteamAPICall submitItemUpdate(SteamUGCUpdateHandle steamUGCUpdateHandle, String string) {
        return new SteamAPICall(SteamUGC.submitItemUpdate(this.pointer, this.callback, steamUGCUpdateHandle.handle, string));
    }

    public SteamUGC$ItemUpdateStatus getItemUpdateProgress(SteamUGCUpdateHandle steamUGCUpdateHandle, SteamUGC$ItemUpdateInfo steamUGC$ItemUpdateInfo) {
        long[] lArray = new long[2];
        SteamUGC$ItemUpdateStatus steamUGC$ItemUpdateStatus = SteamUGC$ItemUpdateStatus.byOrdinal(SteamUGC.getItemUpdateProgress(this.pointer, steamUGCUpdateHandle.handle, lArray));
        steamUGC$ItemUpdateInfo.bytesProcessed = lArray[0];
        steamUGC$ItemUpdateInfo.bytesTotal = lArray[1];
        return steamUGC$ItemUpdateStatus;
    }

    public SteamAPICall setUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean bl) {
        return new SteamAPICall(SteamUGC.setUserItemVote(this.pointer, this.callback, steamPublishedFileID.handle, bl));
    }

    public SteamAPICall getUserItemVote(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGC.getUserItemVote(this.pointer, this.callback, steamPublishedFileID.handle));
    }

    public SteamAPICall addItemToFavorites(int n, SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGC.addItemToFavorites(this.pointer, this.callback, n, steamPublishedFileID.handle));
    }

    public SteamAPICall removeItemFromFavorites(int n, SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGC.removeItemFromFavorites(this.pointer, this.callback, n, steamPublishedFileID.handle));
    }

    public SteamAPICall subscribeItem(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGC.subscribeItem(this.pointer, this.callback, steamPublishedFileID.handle));
    }

    public SteamAPICall unsubscribeItem(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGC.unsubscribeItem(this.pointer, this.callback, steamPublishedFileID.handle));
    }

    public int getNumSubscribedItems() {
        return SteamUGC.getNumSubscribedItems(this.pointer);
    }

    public int getSubscribedItems(SteamPublishedFileID[] steamPublishedFileIDArray) {
        long[] lArray = new long[steamPublishedFileIDArray.length];
        int n = SteamUGC.getSubscribedItems(this.pointer, lArray, steamPublishedFileIDArray.length);
        for (int i = 0; i < n; ++i) {
            steamPublishedFileIDArray[i] = new SteamPublishedFileID(lArray[i]);
        }
        return n;
    }

    public Collection getItemState(SteamPublishedFileID steamPublishedFileID) {
        return SteamUGC$ItemState.fromBits(SteamUGC.getItemState(this.pointer, steamPublishedFileID.handle));
    }

    public boolean getItemInstallInfo(SteamPublishedFileID steamPublishedFileID, SteamUGC$ItemInstallInfo itemInstallInfo) {
        return SteamUGC.getItemInstallInfo(this.pointer, steamPublishedFileID.handle, itemInstallInfo);
    }

    public boolean getItemDownloadInfo(SteamPublishedFileID steamPublishedFileID, SteamUGC$ItemDownloadInfo steamUGC$ItemDownloadInfo) {
        long[] lArray = new long[2];
        if (SteamUGC.getItemDownloadInfo(this.pointer, steamPublishedFileID.handle, lArray)) {
            steamUGC$ItemDownloadInfo.bytesDownloaded = lArray[0];
            steamUGC$ItemDownloadInfo.bytesTotal = lArray[1];
            return true;
        }
        return false;
    }

    public boolean downloadItem(SteamPublishedFileID steamPublishedFileID, boolean bl) {
        return SteamUGC.downloadItem(this.pointer, steamPublishedFileID.handle, bl);
    }

    public boolean initWorkshopForGameServer(int n, String string) {
        return SteamUGC.initWorkshopForGameServer(this.pointer, n, string);
    }

    public void suspendDownloads(boolean bl) {
        SteamUGC.suspendDownloads(this.pointer, bl);
    }

    public SteamAPICall startPlaytimeTracking(SteamPublishedFileID[] steamPublishedFileIDArray) {
        long[] lArray = new long[steamPublishedFileIDArray.length];
        for (int i = 0; i < lArray.length; ++i) {
            lArray[i] = steamPublishedFileIDArray[i].handle;
        }
        return new SteamAPICall(SteamUGC.startPlaytimeTracking(this.pointer, this.callback, lArray, lArray.length));
    }

    public SteamAPICall stopPlaytimeTracking(SteamPublishedFileID[] steamPublishedFileIDArray) {
        long[] lArray = new long[steamPublishedFileIDArray.length];
        for (int i = 0; i < lArray.length; ++i) {
            lArray[i] = steamPublishedFileIDArray[i].handle;
        }
        return new SteamAPICall(SteamUGC.stopPlaytimeTracking(this.pointer, this.callback, lArray, lArray.length));
    }

    public SteamAPICall stopPlaytimeTrackingForAllItems() {
        return new SteamAPICall(SteamUGC.stopPlaytimeTrackingForAllItems(this.pointer, this.callback));
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
