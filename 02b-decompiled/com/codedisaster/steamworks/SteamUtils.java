package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamAPIWarningMessageHook;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamUniverse;
import com.codedisaster.steamworks.SteamUtils$NotificationPosition;
import com.codedisaster.steamworks.SteamUtils$SteamAPICallFailure;
import com.codedisaster.steamworks.SteamUtilsCallback;
import com.codedisaster.steamworks.SteamUtilsCallbackAdapter;
import java.nio.ByteBuffer;

public class SteamUtils extends SteamInterface {

   private SteamUtilsCallbackAdapter callbackAdapter;


   public SteamUtils(SteamUtilsCallback var1) {
      super(SteamAPI.getSteamUtilsPointer());
      this.callbackAdapter = new SteamUtilsCallbackAdapter(var1);
      this.setCallback(createCallback(this.callbackAdapter));
   }

   public int getSecondsSinceAppActive() {
      return getSecondsSinceAppActive(this.pointer);
   }

   public int getSecondsSinceComputerActive() {
      return getSecondsSinceComputerActive(this.pointer);
   }

   public SteamUniverse getConnectedUniverse() {
      return SteamUniverse.byValue(getConnectedUniverse(this.pointer));
   }

   public int getServerRealTime() {
      return getServerRealTime(this.pointer);
   }

   public int getImageWidth(int var1) {
      return getImageWidth(this.pointer, var1);
   }

   public int getImageHeight(int var1) {
      return getImageHeight(this.pointer, var1);
   }

   public boolean getImageRGBA(int var1, ByteBuffer var2, int var3) {
      if(!var2.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return getImageRGBA(this.pointer, var1, var2, var3);
      }
   }

   public int getAppID() {
      return getAppID(this.pointer);
   }

   public void setOverlayNotificationPosition(SteamUtils$NotificationPosition var1) {
      setOverlayNotificationPosition(this.pointer, var1.ordinal());
   }

   public boolean isAPICallCompleted(SteamAPICall var1, boolean[] var2) {
      return isAPICallCompleted(this.pointer, var1.handle, var2);
   }

   public SteamUtils$SteamAPICallFailure getAPICallFailureReason(SteamAPICall var1) {
      return SteamUtils$SteamAPICallFailure.byValue(getAPICallFailureReason(this.pointer, var1.handle));
   }

   public void setWarningMessageHook(SteamAPIWarningMessageHook var1) {
      this.callbackAdapter.setWarningMessageHook(var1);
      enableWarningMessageHook(this.callback, var1 != null);
   }

   public boolean isOverlayEnabled() {
      return isOverlayEnabled(this.pointer);
   }

   private static native long createCallback(SteamUtilsCallbackAdapter var0);

   private static native int getSecondsSinceAppActive(long var0);

   private static native int getSecondsSinceComputerActive(long var0);

   private static native int getConnectedUniverse(long var0);

   private static native int getServerRealTime(long var0);

   private static native String getIPCountry(long var0);

   private static native int getImageWidth(long var0, int var2);

   private static native int getImageHeight(long var0, int var2);

   private static native boolean getImageRGBA(long var0, int var2, ByteBuffer var3, int var4);

   private static native int getAppID(long var0);

   private static native void setOverlayNotificationPosition(long var0, int var2);

   private static native boolean isAPICallCompleted(long var0, long var2, boolean[] var4);

   private static native int getAPICallFailureReason(long var0, long var2);

   private static native void enableWarningMessageHook(long var0, boolean var2);

   private static native boolean isOverlayEnabled(long var0);
}
