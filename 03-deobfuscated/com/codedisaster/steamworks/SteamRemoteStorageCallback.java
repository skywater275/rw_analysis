/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCHandle;

public interface SteamRemoteStorageCallback {
    public void onFileShareResult(SteamUGCHandle var1, String var2, SteamResult var3);

    public void onDownloadUGCResult(SteamUGCHandle var1, SteamResult var2);

    public void onPublishFileResult(SteamPublishedFileID var1, boolean var2, SteamResult var3);

    public void onUpdatePublishedFileResult(SteamPublishedFileID var1, boolean var2, SteamResult var3);

    public void onPublishedFileSubscribed(SteamPublishedFileID var1, int var2);

    public void onPublishedFileUnsubscribed(SteamPublishedFileID var1, int var2);

    public void onPublishedFileDeleted(SteamPublishedFileID var1, int var2);

    public void onFileWriteAsyncComplete(SteamResult var1);

    public void onFileReadAsyncComplete(SteamAPICall var1, SteamResult var2, int var3, int var4);
}
