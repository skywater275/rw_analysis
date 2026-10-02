package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamController$ActionOrigin;
import com.codedisaster.steamworks.SteamController$LEDFlag;
import com.codedisaster.steamworks.SteamController$Pad;
import com.codedisaster.steamworks.SteamControllerActionSetHandle;
import com.codedisaster.steamworks.SteamControllerAnalogActionData;
import com.codedisaster.steamworks.SteamControllerAnalogActionHandle;
import com.codedisaster.steamworks.SteamControllerDigitalActionData;
import com.codedisaster.steamworks.SteamControllerDigitalActionHandle;
import com.codedisaster.steamworks.SteamControllerHandle;
import com.codedisaster.steamworks.SteamControllerMotionData;
import com.codedisaster.steamworks.SteamInterface;

public class SteamController extends SteamInterface {

   public static final int STEAM_CONTROLLER_MAX_COUNT = 16;
   public static final int STEAM_CONTROLLER_MAX_ANALOG_ACTIONS = 16;
   public static final int STEAM_CONTROLLER_MAX_DIGITAL_ACTIONS = 128;
   public static final int STEAM_CONTROLLER_MAX_ORIGINS = 8;
   public static final long STEAM_CONTROLLER_HANDLE_ALL_CONTROLLERS = -1L;
   public static final float STEAM_CONTROLLER_MIN_ANALOG_ACTION_DATA = -1.0F;
   public static final float STEAM_CONTROLLER_MAX_ANALOG_ACTION_DATA = 1.0F;
   private long[] controllerHandles = new long[16];
   private int[] actionOrigins = new int[8];


   public SteamController() {
      super(SteamAPI.getSteamControllerPointer());
   }

   public boolean init() {
      return init(this.pointer);
   }

   public boolean shutdown() {
      return shutdown(this.pointer);
   }

   public void runFrame() {
      runFrame(this.pointer);
   }

   public int getConnectedControllers(SteamControllerHandle[] var1) {
      if(var1.length < 16) {
         throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_COUNT");
      } else {
         int var2 = getConnectedControllers(this.pointer, this.controllerHandles);

         for(int var3 = 0; var3 < var2; ++var3) {
            var1[var3] = new SteamControllerHandle(this.controllerHandles[var3]);
         }

         return var2;
      }
   }

   public boolean showBindingPanel(SteamControllerHandle var1) {
      return showBindingPanel(this.pointer, var1.handle);
   }

   public SteamControllerActionSetHandle getActionSetHandle(String var1) {
      return new SteamControllerActionSetHandle(getActionSetHandle(this.pointer, var1));
   }

   public void activateActionSet(SteamControllerHandle var1, SteamControllerActionSetHandle var2) {
      activateActionSet(this.pointer, var1.handle, var2.handle);
   }

   public SteamControllerActionSetHandle getCurrentActionSet(SteamControllerHandle var1) {
      return new SteamControllerActionSetHandle(getCurrentActionSet(this.pointer, var1.handle));
   }

   public SteamControllerDigitalActionHandle getDigitalActionHandle(String var1) {
      return new SteamControllerDigitalActionHandle(getDigitalActionHandle(this.pointer, var1));
   }

   public void getDigitalActionData(SteamControllerHandle var1, SteamControllerDigitalActionHandle var2, SteamControllerDigitalActionData var3) {
      getDigitalActionData(this.pointer, var1.handle, var2.handle, var3);
   }

   public int getDigitalActionOrigins(SteamControllerHandle var1, SteamControllerActionSetHandle var2, SteamControllerDigitalActionHandle var3, SteamController$ActionOrigin[] var4) {
      if(var4.length < 8) {
         throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_ORIGINS");
      } else {
         int var5 = getDigitalActionOrigins(this.pointer, var1.handle, var2.handle, var3.handle, this.actionOrigins);

         for(int var6 = 0; var6 < var5; ++var6) {
            var4[var6] = SteamController$ActionOrigin.byOrdinal(this.actionOrigins[var6]);
         }

         return var5;
      }
   }

   public SteamControllerAnalogActionHandle getAnalogActionHandle(String var1) {
      return new SteamControllerAnalogActionHandle(getAnalogActionHandle(this.pointer, var1));
   }

   public void getAnalogActionData(SteamControllerHandle var1, SteamControllerAnalogActionHandle var2, SteamControllerAnalogActionData var3) {
      getAnalogActionData(this.pointer, var1.handle, var2.handle, var3);
   }

   public int getAnalogActionOrigins(SteamControllerHandle var1, SteamControllerActionSetHandle var2, SteamControllerAnalogActionHandle var3, SteamController$ActionOrigin[] var4) {
      if(var4.length < 8) {
         throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_ORIGINS");
      } else {
         int var5 = getAnalogActionOrigins(this.pointer, var1.handle, var2.handle, var3.handle, this.actionOrigins);

         for(int var6 = 0; var6 < var5; ++var6) {
            var4[var6] = SteamController$ActionOrigin.byOrdinal(this.actionOrigins[var6]);
         }

         return var5;
      }
   }

   public void stopAnalogActionMomentum(SteamControllerHandle var1, SteamControllerAnalogActionHandle var2) {
      stopAnalogActionMomentum(this.pointer, var1.handle, var2.handle);
   }

   public void triggerHapticPulse(SteamControllerHandle var1, SteamController$Pad var2, int var3) {
      triggerHapticPulse(this.pointer, var1.handle, var2.ordinal(), var3);
   }

   public void triggerRepeatedHapticPulse(SteamControllerHandle var1, SteamController$Pad var2, int var3, int var4, int var5, int var6) {
      triggerRepeatedHapticPulse(this.pointer, var1.handle, var2.ordinal(), var3, var4, var5, var6);
   }

   public void triggerVibration(SteamControllerHandle var1, short var2, short var3) {
      triggerVibration(this.pointer, var1.handle, var2, var3);
   }

   public void setLEDColor(SteamControllerHandle var1, int var2, int var3, int var4, SteamController$LEDFlag var5) {
      setLEDColor(this.pointer, var1.handle, (byte)(var2 & 255), (byte)(var3 & 255), (byte)(var4 & 255), var5.ordinal());
   }

   public int getGamepadIndexForController(SteamControllerHandle var1) {
      return getGamepadIndexForController(this.pointer, var1.handle);
   }

   public SteamControllerHandle getControllerForGamepadIndex(int var1) {
      return new SteamControllerHandle(getControllerForGamepadIndex(this.pointer, var1));
   }

   public void getMotionData(SteamControllerHandle var1, SteamControllerMotionData var2) {
      getMotionData(this.pointer, var1.handle, var2.data);
   }

   public boolean showDigitalActionOrigins(SteamControllerHandle var1, SteamControllerDigitalActionHandle var2, float var3, float var4, float var5) {
      return showDigitalActionOrigins(this.pointer, var1.handle, var2.handle, var3, var4, var5);
   }

   public boolean showAnalogActionOrigins(SteamControllerHandle var1, SteamControllerAnalogActionHandle var2, float var3, float var4, float var5) {
      return showAnalogActionOrigins(this.pointer, var1.handle, var2.handle, var3, var4, var5);
   }

   public String getStringForActionOrigin(SteamController$ActionOrigin var1) {
      return getStringForActionOrigin(this.pointer, var1.ordinal());
   }

   public String getGlyphForActionOrigin(SteamController$ActionOrigin var1) {
      return getGlyphForActionOrigin(this.pointer, var1.ordinal());
   }

   private static native boolean init(long var0);

   private static native boolean shutdown(long var0);

   private static native void runFrame(long var0);

   private static native int getConnectedControllers(long var0, long[] var2);

   private static native boolean showBindingPanel(long var0, long var2);

   private static native long getActionSetHandle(long var0, String var2);

   private static native void activateActionSet(long var0, long var2, long var4);

   private static native long getCurrentActionSet(long var0, long var2);

   private static native long getDigitalActionHandle(long var0, String var2);

   private static native void getDigitalActionData(long var0, long var2, long var4, SteamControllerDigitalActionData var6);

   private static native int getDigitalActionOrigins(long var0, long var2, long var4, long var6, int[] var8);

   private static native long getAnalogActionHandle(long var0, String var2);

   private static native void getAnalogActionData(long var0, long var2, long var4, SteamControllerAnalogActionData var6);

   private static native int getAnalogActionOrigins(long var0, long var2, long var4, long var6, int[] var8);

   private static native void stopAnalogActionMomentum(long var0, long var2, long var4);

   private static native void triggerHapticPulse(long var0, long var2, int var4, int var5);

   private static native void triggerRepeatedHapticPulse(long var0, long var2, int var4, int var5, int var6, int var7, int var8);

   private static native void triggerVibration(long var0, long var2, short var4, short var5);

   private static native void setLEDColor(long var0, long var2, byte var4, byte var5, byte var6, int var7);

   private static native int getGamepadIndexForController(long var0, long var2);

   private static native long getControllerForGamepadIndex(long var0, int var2);

   private static native void getMotionData(long var0, long var2, float[] var4);

   private static native boolean showDigitalActionOrigins(long var0, long var2, long var4, float var6, float var7, float var8);

   private static native boolean showAnalogActionOrigins(long var0, long var2, long var4, float var6, float var7, float var8);

   private static native String getStringForActionOrigin(long var0, int var2);

   private static native String getGlyphForActionOrigin(long var0, int var2);
}
