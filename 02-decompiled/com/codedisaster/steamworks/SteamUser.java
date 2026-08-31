/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAuth$BeginAuthSessionResult;
import com.codedisaster.steamworks.SteamAuth$UserHasLicenseForAppResult;
import com.codedisaster.steamworks.SteamAuthTicket;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamUserCallback;
import com.codedisaster.steamworks.SteamUserCallbackAdapter;
import java.nio.Buffer;

public class SteamUser
extends SteamInterface {
    public SteamUser(SteamUserCallback steamUserCallback) {
        super(SteamAPI.getSteamUserPointer(), SteamUser.createCallback(new SteamUserCallbackAdapter(steamUserCallback)));
    }

    public SteamID getSteamID() {
        return new SteamID(SteamUser.getSteamID(this.pointer));
    }

    public SteamAuthTicket getAuthSessionTicket(Buffer buffer, int[] nArray) {
        if (!buffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n = SteamUser.getAuthSessionTicket(this.pointer, buffer, buffer.capacity(), nArray);
        if ((long)n != 0L) {
            buffer.limit(nArray[0]);
        }
        return new SteamAuthTicket(n);
    }

    public SteamAuth$BeginAuthSessionResult beginAuthSession(Buffer buffer, SteamID steamID) {
        if (!buffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int n = SteamUser.beginAuthSession(this.pointer, buffer, buffer.limit(), steamID.handle);
        return SteamAuth$BeginAuthSessionResult.byOrdinal(n);
    }

    public void endAuthSession(SteamID steamID) {
        SteamUser.endAuthSession(this.pointer, steamID.handle);
    }

    public void cancelAuthTicket(SteamAuthTicket steamAuthTicket) {
        SteamUser.cancelAuthTicket(this.pointer, (int)steamAuthTicket.handle);
    }

    public SteamAuth$UserHasLicenseForAppResult userHasLicenseForApp(SteamID steamID, int n) {
        return SteamAuth$UserHasLicenseForAppResult.byOrdinal(SteamUser.userHasLicenseForApp(this.pointer, steamID.handle, n));
    }

    private static native long createCallback(SteamUserCallbackAdapter var0);

    private static native long getSteamID(long var0);

    private static native int getAuthSessionTicket(long var0, Buffer var2, int var3, int[] var4);

    private static native int beginAuthSession(long var0, Buffer var2, int var3, long var4);

    private static native void endAuthSession(long var0, long var2);

    private static native void cancelAuthTicket(long var0, int var2);

    private static native int userHasLicenseForApp(long var0, long var2, int var4);
}
