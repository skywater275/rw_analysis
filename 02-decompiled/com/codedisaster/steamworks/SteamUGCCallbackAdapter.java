/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;

class SteamUGCCallbackAdapter
extends SteamCallbackAdapter {
    SteamUGCCallbackAdapter(SteamUGCCallback steamUGCCallback) {
        super(steamUGCCallback);
    }

    void onUGCQueryCompleted(long l, int n, int n2, boolean bl, int n3) {
        ((SteamUGCCallback)this.callback).onUGCQueryCompleted(new SteamUGCQuery(l), n, n2, bl, SteamResult.byValue(n3));
    }

    void onSubscribeItem(long l, int n) {
        ((SteamUGCCallback)this.callback).onSubscribeItem(new SteamPublishedFileID(l), SteamResult.byValue(n));
    }

    void onUnsubscribeItem(long l, int n) {
        ((SteamUGCCallback)this.callback).onUnsubscribeItem(new SteamPublishedFileID(l), SteamResult.byValue(n));
    }

    void onRequestUGCDetails(long l, int n, String string, String string2, long l2, long l3, String string3, boolean bl, int n2, int n3, long l4, int n4, int n5) {
        SteamUGCDetails steamUGCDetails = new SteamUGCDetails();
        steamUGCDetails.publishedFileID = l;
        steamUGCDetails.result = n;
        steamUGCDetails.title = string;
        steamUGCDetails.description = string2;
        steamUGCDetails.fileHandle = l2;
        steamUGCDetails.previewFileHandle = l3;
        steamUGCDetails.fileName = string3;
        steamUGCDetails.votesUp = n2;
        steamUGCDetails.votesDown = n3;
        steamUGCDetails.ownerID = l4;
        steamUGCDetails.timeCreated = n4;
        steamUGCDetails.timeUpdated = n5;
        ((SteamUGCCallback)this.callback).onRequestUGCDetails(steamUGCDetails, SteamResult.byValue(n));
    }

    void onCreateItem(long l, boolean bl, int n) {
        ((SteamUGCCallback)this.callback).onCreateItem(new SteamPublishedFileID(l), bl, SteamResult.byValue(n));
    }

    void onSubmitItemUpdate(boolean bl, int n) {
        ((SteamUGCCallback)this.callback).onSubmitItemUpdate(bl, SteamResult.byValue(n));
    }

    void onDownloadItemResult(int n, long l, int n2) {
        ((SteamUGCCallback)this.callback).onDownloadItemResult(n, new SteamPublishedFileID(l), SteamResult.byValue(n2));
    }

    void onUserFavoriteItemsListChanged(long l, boolean bl, int n) {
        ((SteamUGCCallback)this.callback).onUserFavoriteItemsListChanged(new SteamPublishedFileID(l), bl, SteamResult.byValue(n));
    }

    void onSetUserItemVote(long l, boolean bl, int n) {
        ((SteamUGCCallback)this.callback).onSetUserItemVote(new SteamPublishedFileID(l), bl, SteamResult.byValue(n));
    }

    void onGetUserItemVote(long l, boolean bl, boolean bl2, boolean bl3, int n) {
        ((SteamUGCCallback)this.callback).onGetUserItemVote(new SteamPublishedFileID(l), bl, bl2, bl3, SteamResult.byValue(n));
    }

    void onStartPlaytimeTracking(int n) {
        ((SteamUGCCallback)this.callback).onStartPlaytimeTracking(SteamResult.byValue(n));
    }

    void onStopPlaytimeTracking(int n) {
        ((SteamUGCCallback)this.callback).onStopPlaytimeTracking(SteamResult.byValue(n));
    }

    void onStopPlaytimeTrackingForAllItems(int n) {
        ((SteamUGCCallback)this.callback).onStopPlaytimeTrackingForAllItems(SteamResult.byValue(n));
    }
}
