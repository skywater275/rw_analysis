/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamPublishedFileUpdateHandle;
import com.codedisaster.steamworks.SteamRemoteStorage$PublishedFileVisibility;
import com.codedisaster.steamworks.SteamRemoteStorage$RemoteStoragePlatform;
import com.codedisaster.steamworks.SteamRemoteStorage$UGCReadAction;
import com.codedisaster.steamworks.SteamRemoteStorage$WorkshopFileType;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamRemoteStorageCallbackAdapter;
import com.codedisaster.steamworks.SteamUGCFileWriteStreamHandle;
import com.codedisaster.steamworks.SteamUGCHandle;
import java.nio.ByteBuffer;

public class SteamRemoteStorage
extends SteamInterface {
    public SteamRemoteStorage(SteamRemoteStorageCallback steamRemoteStorageCallback) {
        super(SteamAPI.getSteamRemoteStoragePointer(), SteamRemoteStorage.createCallback(new SteamRemoteStorageCallbackAdapter(steamRemoteStorageCallback)));
    }

    public boolean fileWrite(String string, ByteBuffer byteBuffer, int n) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamRemoteStorage.fileWrite(this.pointer, string, byteBuffer, n);
    }

    public boolean fileRead(String string, ByteBuffer byteBuffer, int n) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamRemoteStorage.fileRead(this.pointer, string, byteBuffer, n);
    }

    public SteamAPICall fileWriteAsync(String string, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return new SteamAPICall(SteamRemoteStorage.fileWriteAsync(this.pointer, this.callback, string, byteBuffer, byteBuffer.remaining()));
    }

    public SteamAPICall fileReadAsync(String string, int n, int n2) {
        return new SteamAPICall(SteamRemoteStorage.fileReadAsync(this.pointer, this.callback, string, n, n2));
    }

    public boolean fileReadAsyncComplete(SteamAPICall steamAPICall, ByteBuffer byteBuffer, int n) {
        return SteamRemoteStorage.fileReadAsyncComplete(this.pointer, steamAPICall.handle, byteBuffer, n);
    }

    public boolean fileForget(String string) {
        return SteamRemoteStorage.fileForget(this.pointer, string);
    }

    public boolean fileDelete(String string) {
        return SteamRemoteStorage.fileDelete(this.pointer, string);
    }

    public SteamAPICall fileShare(String string) {
        return new SteamAPICall(SteamRemoteStorage.fileShare(this.pointer, this.callback, string));
    }

    public boolean setSyncPlatforms(String string, SteamRemoteStorage$RemoteStoragePlatform steamRemoteStorage$RemoteStoragePlatform) {
        return SteamRemoteStorage.setSyncPlatforms(this.pointer, string, SteamRemoteStorage$RemoteStoragePlatform.access$000(steamRemoteStorage$RemoteStoragePlatform));
    }

    public SteamUGCFileWriteStreamHandle fileWriteStreamOpen(String string) {
        return new SteamUGCFileWriteStreamHandle(SteamRemoteStorage.fileWriteStreamOpen(this.pointer, string));
    }

    public boolean fileWriteStreamWriteChunk(SteamUGCFileWriteStreamHandle steamUGCFileWriteStreamHandle, ByteBuffer byteBuffer, int n) {
        return SteamRemoteStorage.fileWriteStreamWriteChunk(this.pointer, steamUGCFileWriteStreamHandle.handle, byteBuffer, n);
    }

    public boolean fileWriteStreamClose(SteamUGCFileWriteStreamHandle steamUGCFileWriteStreamHandle) {
        return SteamRemoteStorage.fileWriteStreamClose(this.pointer, steamUGCFileWriteStreamHandle.handle);
    }

    public boolean fileWriteStreamCancel(SteamUGCFileWriteStreamHandle steamUGCFileWriteStreamHandle) {
        return SteamRemoteStorage.fileWriteStreamCancel(this.pointer, steamUGCFileWriteStreamHandle.handle);
    }

    public boolean fileExists(String string) {
        return SteamRemoteStorage.fileExists(this.pointer, string);
    }

    public boolean filePersisted(String string) {
        return SteamRemoteStorage.filePersisted(this.pointer, string);
    }

    public int getFileSize(String string) {
        return SteamRemoteStorage.getFileSize(this.pointer, string);
    }

    public long getFileTimestamp(String string) {
        return SteamRemoteStorage.getFileTimestamp(this.pointer, string);
    }

    public SteamRemoteStorage$RemoteStoragePlatform[] getSyncPlatforms(String string) {
        int n = SteamRemoteStorage.getSyncPlatforms(this.pointer, string);
        return SteamRemoteStorage$RemoteStoragePlatform.byMask(n);
    }

    public int getFileCount() {
        return SteamRemoteStorage.getFileCount(this.pointer);
    }

    public String getFileNameAndSize(int n, int[] nArray) {
        return SteamRemoteStorage.getFileNameAndSize(this.pointer, n, nArray);
    }

    public boolean getQuota(long[] lArray, long[] lArray2) {
        return SteamRemoteStorage.getQuota(this.pointer, lArray, lArray2);
    }

    public boolean isCloudEnabledForAccount() {
        return SteamRemoteStorage.isCloudEnabledForAccount(this.pointer);
    }

    public boolean isCloudEnabledForApp() {
        return SteamRemoteStorage.isCloudEnabledForApp(this.pointer);
    }

    public void setCloudEnabledForApp(boolean bl) {
        SteamRemoteStorage.setCloudEnabledForApp(this.pointer, bl);
    }

    public SteamAPICall ugcDownload(SteamUGCHandle steamUGCHandle, int n) {
        return new SteamAPICall(SteamRemoteStorage.ugcDownload(this.pointer, this.callback, steamUGCHandle.handle, n));
    }

    public boolean getUGCDownloadProgress(SteamUGCHandle steamUGCHandle, int[] nArray, int[] nArray2) {
        return SteamRemoteStorage.getUGCDownloadProgress(this.pointer, steamUGCHandle.handle, nArray, nArray2);
    }

    public int ugcRead(SteamUGCHandle steamUGCHandle, ByteBuffer byteBuffer, int n, int n2, SteamRemoteStorage$UGCReadAction steamRemoteStorage$UGCReadAction) {
        return SteamRemoteStorage.ugcRead(this.pointer, steamUGCHandle.handle, byteBuffer, n, n2, steamRemoteStorage$UGCReadAction.ordinal());
    }

    public int getCachedUGCCount() {
        return SteamRemoteStorage.getCachedUGCCount(this.pointer);
    }

    public SteamUGCHandle getCachedUGCHandle(int n) {
        return new SteamUGCHandle(SteamRemoteStorage.getCachedUGCHandle(this.pointer, n));
    }

    public SteamAPICall publishWorkshopFile(String string, String string2, int n, String string3, String string4, SteamRemoteStorage$PublishedFileVisibility steamRemoteStorage$PublishedFileVisibility, String[] stringArray, SteamRemoteStorage$WorkshopFileType steamRemoteStorage$WorkshopFileType) {
        return new SteamAPICall(SteamRemoteStorage.publishWorkshopFile(this.pointer, this.callback, string, string2, n, string3, string4, steamRemoteStorage$PublishedFileVisibility.ordinal(), stringArray, stringArray != null ? stringArray.length : 0, steamRemoteStorage$WorkshopFileType.ordinal()));
    }

    public SteamPublishedFileUpdateHandle createPublishedFileUpdateRequest(SteamPublishedFileID steamPublishedFileID) {
        return new SteamPublishedFileUpdateHandle(SteamRemoteStorage.createPublishedFileUpdateRequest(this.pointer, steamPublishedFileID.handle));
    }

    public boolean updatePublishedFileFile(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String string) {
        return SteamRemoteStorage.updatePublishedFileFile(this.pointer, steamPublishedFileUpdateHandle.handle, string);
    }

    public boolean updatePublishedFilePreviewFile(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String string) {
        return SteamRemoteStorage.updatePublishedFilePreviewFile(this.pointer, steamPublishedFileUpdateHandle.handle, string);
    }

    public boolean updatePublishedFileTitle(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String string) {
        return SteamRemoteStorage.updatePublishedFileTitle(this.pointer, steamPublishedFileUpdateHandle.handle, string);
    }

    public boolean updatePublishedFileDescription(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String string) {
        return SteamRemoteStorage.updatePublishedFileDescription(this.pointer, steamPublishedFileUpdateHandle.handle, string);
    }

    public boolean updatePublishedFileVisibility(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, SteamRemoteStorage$PublishedFileVisibility steamRemoteStorage$PublishedFileVisibility) {
        return SteamRemoteStorage.updatePublishedFileVisibility(this.pointer, steamPublishedFileUpdateHandle.handle, steamRemoteStorage$PublishedFileVisibility.ordinal());
    }

    public boolean updatePublishedFileTags(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String[] stringArray) {
        return SteamRemoteStorage.updatePublishedFileTags(this.pointer, steamPublishedFileUpdateHandle.handle, stringArray, stringArray != null ? stringArray.length : 0);
    }

    public SteamAPICall commitPublishedFileUpdate(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle) {
        return new SteamAPICall(SteamRemoteStorage.commitPublishedFileUpdate(this.pointer, this.callback, steamPublishedFileUpdateHandle.handle));
    }

    private static native long createCallback(SteamRemoteStorageCallbackAdapter var0);

    private static native boolean fileWrite(long var0, String var2, ByteBuffer var3, int var4);

    private static native boolean fileRead(long var0, String var2, ByteBuffer var3, int var4);

    private static native long fileWriteAsync(long var0, long var2, String var4, ByteBuffer var5, int var6);

    private static native long fileReadAsync(long var0, long var2, String var4, int var5, int var6);

    private static native boolean fileReadAsyncComplete(long var0, long var2, ByteBuffer var4, int var5);

    private static native boolean fileForget(long var0, String var2);

    private static native boolean fileDelete(long var0, String var2);

    private static native long fileShare(long var0, long var2, String var4);

    private static native boolean setSyncPlatforms(long var0, String var2, int var3);

    private static native long fileWriteStreamOpen(long var0, String var2);

    private static native boolean fileWriteStreamWriteChunk(long var0, long var2, ByteBuffer var4, int var5);

    private static native boolean fileWriteStreamClose(long var0, long var2);

    private static native boolean fileWriteStreamCancel(long var0, long var2);

    private static native boolean fileExists(long var0, String var2);

    private static native boolean filePersisted(long var0, String var2);

    private static native int getFileSize(long var0, String var2);

    private static native long getFileTimestamp(long var0, String var2);

    private static native int getSyncPlatforms(long var0, String var2);

    private static native int getFileCount(long var0);

    private static native String getFileNameAndSize(long var0, int var2, int[] var3);

    private static native boolean getQuota(long var0, long[] var2, long[] var3);

    private static native boolean isCloudEnabledForAccount(long var0);

    private static native boolean isCloudEnabledForApp(long var0);

    private static native void setCloudEnabledForApp(long var0, boolean var2);

    private static native long ugcDownload(long var0, long var2, long var4, int var6);

    private static native boolean getUGCDownloadProgress(long var0, long var2, int[] var4, int[] var5);

    private static native int ugcRead(long var0, long var2, ByteBuffer var4, int var5, int var6, int var7);

    private static native int getCachedUGCCount(long var0);

    private static native long getCachedUGCHandle(long var0, int var2);

    private static native long publishWorkshopFile(long var0, long var2, String var4, String var5, int var6, String var7, String var8, int var9, String[] var10, int var11, int var12);

    private static native long createPublishedFileUpdateRequest(long var0, long var2);

    private static native boolean updatePublishedFileFile(long var0, long var2, String var4);

    private static native boolean updatePublishedFilePreviewFile(long var0, long var2, String var4);

    private static native boolean updatePublishedFileTitle(long var0, long var2, String var4);

    private static native boolean updatePublishedFileDescription(long var0, long var2, String var4);

    private static native boolean updatePublishedFileVisibility(long var0, long var2, int var4);

    private static native boolean updatePublishedFileTags(long var0, long var2, String[] var4, int var5);

    private static native long commitPublishedFileUpdate(long var0, long var2, long var4);
}
