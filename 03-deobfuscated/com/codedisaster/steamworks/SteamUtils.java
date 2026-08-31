/*
 * Decompiled with CFR 0.152.
 */
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

public class SteamUtils
extends SteamInterface {
    private SteamUtilsCallbackAdapter callbackAdapter;

    public SteamUtils(SteamUtilsCallback steamUtilsCallback) {
        super(SteamAPI.getSteamUtilsPointer());
        this.callbackAdapter = new SteamUtilsCallbackAdapter(steamUtilsCallback);
        this.setCallback(SteamUtils.createCallback(this.callbackAdapter));
    }

    public int getSecondsSinceAppActive() {
        return SteamUtils.getSecondsSinceAppActive(this.pointer);
    }

    public int getSecondsSinceComputerActive() {
        return SteamUtils.getSecondsSinceComputerActive(this.pointer);
    }

    public SteamUniverse getConnectedUniverse() {
        return SteamUniverse.byValue(SteamUtils.getConnectedUniverse(this.pointer));
    }

    public int getServerRealTime() {
        return SteamUtils.getServerRealTime(this.pointer);
    }

    public int getImageWidth(int n) {
        return SteamUtils.getImageWidth(this.pointer, n);
    }

    public int getImageHeight(int n) {
        return SteamUtils.getImageHeight(this.pointer, n);
    }

    public boolean getImageRGBA(int n, ByteBuffer byteBuffer, int n2)  throws SteamException {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamUtils.getImageRGBA(this.pointer, n, byteBuffer, n2);
    }

    public int getAppID() {
        return SteamUtils.getAppID(this.pointer);
    }

    public void setOverlayNotificationPosition(SteamUtils$NotificationPosition steamUtils$NotificationPosition) {
        SteamUtils.setOverlayNotificationPosition(this.pointer, steamUtils$NotificationPosition.ordinal());
    }

    public boolean isAPICallCompleted(SteamAPICall steamAPICall, boolean[] blArray) {
        return SteamUtils.isAPICallCompleted(this.pointer, steamAPICall.handle, blArray);
    }

    public SteamUtils$SteamAPICallFailure getAPICallFailureReason(SteamAPICall steamAPICall) {
        return SteamUtils$SteamAPICallFailure.byValue(SteamUtils.getAPICallFailureReason(this.pointer, steamAPICall.handle));
    }

    public void setWarningMessageHook(SteamAPIWarningMessageHook steamAPIWarningMessageHook) {
        this.callbackAdapter.setWarningMessageHook(steamAPIWarningMessageHook);
        SteamUtils.enableWarningMessageHook(this.callback, steamAPIWarningMessageHook != null);
    }

    public boolean isOverlayEnabled() {
        return SteamUtils.isOverlayEnabled(this.pointer);
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
