/*
 * Decompiled with CFR 0.152.
 */
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

public class SteamController
extends SteamInterface {
    public static final int STEAM_CONTROLLER_MAX_COUNT = 16;
    public static final int STEAM_CONTROLLER_MAX_ANALOG_ACTIONS = 16;
    public static final int STEAM_CONTROLLER_MAX_DIGITAL_ACTIONS = 128;
    public static final int STEAM_CONTROLLER_MAX_ORIGINS = 8;
    public static final long STEAM_CONTROLLER_HANDLE_ALL_CONTROLLERS = -1L;
    public static final float STEAM_CONTROLLER_MIN_ANALOG_ACTION_DATA = -1.0f;
    public static final float STEAM_CONTROLLER_MAX_ANALOG_ACTION_DATA = 1.0f;
    private long[] controllerHandles = new long[16];
    private int[] actionOrigins = new int[8];

    public SteamController() {
        super(SteamAPI.getSteamControllerPointer());
    }

    public boolean init() {
        return SteamController.init(this.pointer);
    }

    public boolean shutdown() {
        return SteamController.shutdown(this.pointer);
    }

    public void runFrame() {
        SteamController.runFrame(this.pointer);
    }

    public int getConnectedControllers(SteamControllerHandle[] steamControllerHandleArray) {
        if (steamControllerHandleArray.length < 16) {
            throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_COUNT");
        }
        int n = SteamController.getConnectedControllers(this.pointer, this.controllerHandles);
        for (int i = 0; i < n; ++i) {
            steamControllerHandleArray[i] = new SteamControllerHandle(this.controllerHandles[i]);
        }
        return n;
    }

    public boolean showBindingPanel(SteamControllerHandle steamControllerHandle) {
        return SteamController.showBindingPanel(this.pointer, steamControllerHandle.handle);
    }

    public SteamControllerActionSetHandle getActionSetHandle(String string) {
        return new SteamControllerActionSetHandle(SteamController.getActionSetHandle(this.pointer, string));
    }

    public void activateActionSet(SteamControllerHandle steamControllerHandle, SteamControllerActionSetHandle steamControllerActionSetHandle) {
        SteamController.activateActionSet(this.pointer, steamControllerHandle.handle, steamControllerActionSetHandle.handle);
    }

    public SteamControllerActionSetHandle getCurrentActionSet(SteamControllerHandle steamControllerHandle) {
        return new SteamControllerActionSetHandle(SteamController.getCurrentActionSet(this.pointer, steamControllerHandle.handle));
    }

    public SteamControllerDigitalActionHandle getDigitalActionHandle(String string) {
        return new SteamControllerDigitalActionHandle(SteamController.getDigitalActionHandle(this.pointer, string));
    }

    public void getDigitalActionData(SteamControllerHandle steamControllerHandle, SteamControllerDigitalActionHandle steamControllerDigitalActionHandle, SteamControllerDigitalActionData steamControllerDigitalActionData) {
        SteamController.getDigitalActionData(this.pointer, steamControllerHandle.handle, steamControllerDigitalActionHandle.handle, steamControllerDigitalActionData);
    }

    public int getDigitalActionOrigins(SteamControllerHandle steamControllerHandle, SteamControllerActionSetHandle steamControllerActionSetHandle, SteamControllerDigitalActionHandle steamControllerDigitalActionHandle, SteamController$ActionOrigin[] steamController$ActionOriginArray) {
        if (steamController$ActionOriginArray.length < 8) {
            throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_ORIGINS");
        }
        int n = SteamController.getDigitalActionOrigins(this.pointer, steamControllerHandle.handle, steamControllerActionSetHandle.handle, steamControllerDigitalActionHandle.handle, this.actionOrigins);
        for (int i = 0; i < n; ++i) {
            steamController$ActionOriginArray[i] = SteamController$ActionOrigin.byOrdinal(this.actionOrigins[i]);
        }
        return n;
    }

    public SteamControllerAnalogActionHandle getAnalogActionHandle(String string) {
        return new SteamControllerAnalogActionHandle(SteamController.getAnalogActionHandle(this.pointer, string));
    }

    public void getAnalogActionData(SteamControllerHandle steamControllerHandle, SteamControllerAnalogActionHandle steamControllerAnalogActionHandle, SteamControllerAnalogActionData steamControllerAnalogActionData) {
        SteamController.getAnalogActionData(this.pointer, steamControllerHandle.handle, steamControllerAnalogActionHandle.handle, steamControllerAnalogActionData);
    }

    public int getAnalogActionOrigins(SteamControllerHandle steamControllerHandle, SteamControllerActionSetHandle steamControllerActionSetHandle, SteamControllerAnalogActionHandle steamControllerAnalogActionHandle, SteamController$ActionOrigin[] steamController$ActionOriginArray) {
        if (steamController$ActionOriginArray.length < 8) {
            throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_ORIGINS");
        }
        int n = SteamController.getAnalogActionOrigins(this.pointer, steamControllerHandle.handle, steamControllerActionSetHandle.handle, steamControllerAnalogActionHandle.handle, this.actionOrigins);
        for (int i = 0; i < n; ++i) {
            steamController$ActionOriginArray[i] = SteamController$ActionOrigin.byOrdinal(this.actionOrigins[i]);
        }
        return n;
    }

    public void stopAnalogActionMomentum(SteamControllerHandle steamControllerHandle, SteamControllerAnalogActionHandle steamControllerAnalogActionHandle) {
        SteamController.stopAnalogActionMomentum(this.pointer, steamControllerHandle.handle, steamControllerAnalogActionHandle.handle);
    }

    public void triggerHapticPulse(SteamControllerHandle steamControllerHandle, SteamController$Pad steamController$Pad, int n) {
        SteamController.triggerHapticPulse(this.pointer, steamControllerHandle.handle, steamController$Pad.ordinal(), n);
    }

    public void triggerRepeatedHapticPulse(SteamControllerHandle steamControllerHandle, SteamController$Pad steamController$Pad, int n, int n2, int n3, int n4) {
        SteamController.triggerRepeatedHapticPulse(this.pointer, steamControllerHandle.handle, steamController$Pad.ordinal(), n, n2, n3, n4);
    }

    public void triggerVibration(SteamControllerHandle steamControllerHandle, short s, short s2) {
        SteamController.triggerVibration(this.pointer, steamControllerHandle.handle, s, s2);
    }

    public void setLEDColor(SteamControllerHandle steamControllerHandle, int n, int n2, int n3, SteamController$LEDFlag steamController$LEDFlag) {
        SteamController.setLEDColor(this.pointer, steamControllerHandle.handle, (byte)(n & 0xFF), (byte)(n2 & 0xFF), (byte)(n3 & 0xFF), steamController$LEDFlag.ordinal());
    }

    public int getGamepadIndexForController(SteamControllerHandle steamControllerHandle) {
        return SteamController.getGamepadIndexForController(this.pointer, steamControllerHandle.handle);
    }

    public SteamControllerHandle getControllerForGamepadIndex(int n) {
        return new SteamControllerHandle(SteamController.getControllerForGamepadIndex(this.pointer, n));
    }

    public void getMotionData(SteamControllerHandle steamControllerHandle, SteamControllerMotionData steamControllerMotionData) {
        SteamController.getMotionData(this.pointer, steamControllerHandle.handle, steamControllerMotionData.data);
    }

    public boolean showDigitalActionOrigins(SteamControllerHandle steamControllerHandle, SteamControllerDigitalActionHandle steamControllerDigitalActionHandle, float f, float f2, float f3) {
        return SteamController.showDigitalActionOrigins(this.pointer, steamControllerHandle.handle, steamControllerDigitalActionHandle.handle, f, f2, f3);
    }

    public boolean showAnalogActionOrigins(SteamControllerHandle steamControllerHandle, SteamControllerAnalogActionHandle steamControllerAnalogActionHandle, float f, float f2, float f3) {
        return SteamController.showAnalogActionOrigins(this.pointer, steamControllerHandle.handle, steamControllerAnalogActionHandle.handle, f, f2, f3);
    }

    public String getStringForActionOrigin(SteamController$ActionOrigin steamController$ActionOrigin) {
        return SteamController.getStringForActionOrigin(this.pointer, steamController$ActionOrigin.ordinal());
    }

    public String getGlyphForActionOrigin(SteamController$ActionOrigin steamController$ActionOrigin) {
        return SteamController.getGlyphForActionOrigin(this.pointer, steamController$ActionOrigin.ordinal());
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
