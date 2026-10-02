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

public class SteamRemoteStorage extends SteamInterface {

   public SteamRemoteStorage(SteamRemoteStorageCallback var1) {
      super(SteamAPI.getSteamRemoteStoragePointer(), createCallback(new SteamRemoteStorageCallbackAdapter(var1)));
   }

   public boolean fileWrite(String var1, ByteBuffer var2, int var3) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return fileWrite(this.pointer, var1, var2, var3);
      }
   }

   public boolean fileRead(String var1, ByteBuffer var2, int var3) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return fileRead(this.pointer, var1, var2, var3);
      }
   }

   public SteamAPICall fileWriteAsync(String var1, ByteBuffer var2) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return new SteamAPICall(fileWriteAsync(this.pointer, this.callback, var1, var2, var2.remaining()));
      }
   }

   public SteamAPICall fileReadAsync(String var1, int var2, int var3) {
      return new SteamAPICall(fileReadAsync(this.pointer, this.callback, var1, var2, var3));
   }

   public boolean fileReadAsyncComplete(SteamAPICall var1, ByteBuffer var2, int var3) {
      return fileReadAsyncComplete(this.pointer, var1.handle, var2, var3);
   }

   public boolean fileForget(String var1) {
      return fileForget(this.pointer, var1);
   }

   public boolean fileDelete(String var1) {
      return fileDelete(this.pointer, var1);
   }

   public SteamAPICall fileShare(String var1) {
      return new SteamAPICall(fileShare(this.pointer, this.callback, var1));
   }

   public boolean setSyncPlatforms(String var1, SteamRemoteStorage$RemoteStoragePlatform var2) {
      return setSyncPlatforms(this.pointer, var1, SteamRemoteStorage$RemoteStoragePlatform.access$000(var2));
   }

   public SteamUGCFileWriteStreamHandle fileWriteStreamOpen(String var1) {
      return new SteamUGCFileWriteStreamHandle(fileWriteStreamOpen(this.pointer, var1));
   }

   public boolean fileWriteStreamWriteChunk(SteamUGCFileWriteStreamHandle var1, ByteBuffer var2, int var3) {
      return fileWriteStreamWriteChunk(this.pointer, var1.handle, var2, var3);
   }

   public boolean fileWriteStreamClose(SteamUGCFileWriteStreamHandle var1) {
      return fileWriteStreamClose(this.pointer, var1.handle);
   }

   public boolean fileWriteStreamCancel(SteamUGCFileWriteStreamHandle var1) {
      return fileWriteStreamCancel(this.pointer, var1.handle);
   }

   public boolean fileExists(String var1) {
      return fileExists(this.pointer, var1);
   }

   public boolean filePersisted(String var1) {
      return filePersisted(this.pointer, var1);
   }

   public int getFileSize(String var1) {
      return getFileSize(this.pointer, var1);
   }

   public long getFileTimestamp(String var1) {
      return getFileTimestamp(this.pointer, var1);
   }

   public SteamRemoteStorage$RemoteStoragePlatform[] getSyncPlatforms(String var1) {
      int var2 = getSyncPlatforms(this.pointer, var1);
      return SteamRemoteStorage$RemoteStoragePlatform.byMask(var2);
   }

   public int getFileCount() {
      return getFileCount(this.pointer);
   }

   public String getFileNameAndSize(int var1, int[] var2) {
      return getFileNameAndSize(this.pointer, var1, var2);
   }

   public boolean getQuota(long[] var1, long[] var2) {
      return getQuota(this.pointer, var1, var2);
   }

   public boolean isCloudEnabledForAccount() {
      return isCloudEnabledForAccount(this.pointer);
   }

   public boolean isCloudEnabledForApp() {
      return isCloudEnabledForApp(this.pointer);
   }

   public void setCloudEnabledForApp(boolean var1) {
      setCloudEnabledForApp(this.pointer, var1);
   }

   public SteamAPICall ugcDownload(SteamUGCHandle var1, int var2) {
      return new SteamAPICall(ugcDownload(this.pointer, this.callback, var1.handle, var2));
   }

   public boolean getUGCDownloadProgress(SteamUGCHandle var1, int[] var2, int[] var3) {
      return getUGCDownloadProgress(this.pointer, var1.handle, var2, var3);
   }

   public int ugcRead(SteamUGCHandle var1, ByteBuffer var2, int var3, int var4, SteamRemoteStorage$UGCReadAction var5) {
      return ugcRead(this.pointer, var1.handle, var2, var3, var4, var5.ordinal());
   }

   public int getCachedUGCCount() {
      return getCachedUGCCount(this.pointer);
   }

   public SteamUGCHandle getCachedUGCHandle(int var1) {
      return new SteamUGCHandle(getCachedUGCHandle(this.pointer, var1));
   }

   public SteamAPICall publishWorkshopFile(String var1, String var2, int var3, String var4, String var5, SteamRemoteStorage$PublishedFileVisibility var6, String[] var7, SteamRemoteStorage$WorkshopFileType var8) {
      return new SteamAPICall(publishWorkshopFile(this.pointer, this.callback, var1, var2, var3, var4, var5, var6.ordinal(), var7, var7 != null?var7.length:0, var8.ordinal()));
   }

   public SteamPublishedFileUpdateHandle createPublishedFileUpdateRequest(SteamPublishedFileID var1) {
      return new SteamPublishedFileUpdateHandle(createPublishedFileUpdateRequest(this.pointer, var1.handle));
   }

   public boolean updatePublishedFileFile(SteamPublishedFileUpdateHandle var1, String var2) {
      return updatePublishedFileFile(this.pointer, var1.handle, var2);
   }

   public boolean updatePublishedFilePreviewFile(SteamPublishedFileUpdateHandle var1, String var2) {
      return updatePublishedFilePreviewFile(this.pointer, var1.handle, var2);
   }

   public boolean updatePublishedFileTitle(SteamPublishedFileUpdateHandle var1, String var2) {
      return updatePublishedFileTitle(this.pointer, var1.handle, var2);
   }

   public boolean updatePublishedFileDescription(SteamPublishedFileUpdateHandle var1, String var2) {
      return updatePublishedFileDescription(this.pointer, var1.handle, var2);
   }

   public boolean updatePublishedFileVisibility(SteamPublishedFileUpdateHandle var1, SteamRemoteStorage$PublishedFileVisibility var2) {
      return updatePublishedFileVisibility(this.pointer, var1.handle, var2.ordinal());
   }

   public boolean updatePublishedFileTags(SteamPublishedFileUpdateHandle var1, String[] var2) {
      return updatePublishedFileTags(this.pointer, var1.handle, var2, var2 != null?var2.length:0);
   }

   public SteamAPICall commitPublishedFileUpdate(SteamPublishedFileUpdateHandle var1) {
      return new SteamAPICall(commitPublishedFileUpdate(this.pointer, this.callback, var1.handle));
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
