/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCHandle;

class SteamRemoteStorageCallbackAdapter
extends SteamCallbackAdapter {
    SteamRemoteStorageCallbackAdapter(SteamRemoteStorageCallback steamRemoteStorageCallback) {
        super(steamRemoteStorageCallback);
    }

    void onFileShareResult(long l, String string, int n) {
        ((SteamRemoteStorageCallback)this.callback).onFileShareResult(new SteamUGCHandle(l), string, SteamResult.byValue(n));
    }

    void onDownloadUGCResult(long l, int n) {
        ((SteamRemoteStorageCallback)this.callback).onDownloadUGCResult(new SteamUGCHandle(l), SteamResult.byValue(n));
    }

    void onPublishFileResult(long l, boolean bl, int n) {
        ((SteamRemoteStorageCallback)this.callback).onPublishFileResult(new SteamPublishedFileID(l), bl, SteamResult.byValue(n));
    }

    void onUpdatePublishedFileResult(long l, boolean bl, int n) {
        ((SteamRemoteStorageCallback)this.callback).onUpdatePublishedFileResult(new SteamPublishedFileID(l), bl, SteamResult.byValue(n));
    }

    void onPublishedFileSubscribed(long l, int n) {
        ((SteamRemoteStorageCallback)this.callback).onPublishedFileSubscribed(new SteamPublishedFileID(l), n);
    }

    void onPublishedFileUnsubscribed(long l, int n) {
        ((SteamRemoteStorageCallback)this.callback).onPublishedFileUnsubscribed(new SteamPublishedFileID(l), n);
    }

    void onPublishedFileDeleted(long l, int n) {
        ((SteamRemoteStorageCallback)this.callback).onPublishedFileDeleted(new SteamPublishedFileID(l), n);
    }

    void onFileWriteAsyncComplete(int n) {
        ((SteamRemoteStorageCallback)this.callback).onFileWriteAsyncComplete(SteamResult.byValue(n));
    }

    void onFileReadAsyncComplete(long l, int n, int n2, int n3) {
        ((SteamRemoteStorageCallback)this.callback).onFileReadAsyncComplete(new SteamAPICall(l), SteamResult.byValue(n), n2, n3);
    }
}
