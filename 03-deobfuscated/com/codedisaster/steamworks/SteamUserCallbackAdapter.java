/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth$AuthSessionResponse;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamUserCallback;

class SteamUserCallbackAdapter
extends SteamCallbackAdapter {
    SteamUserCallbackAdapter(SteamUserCallback steamUserCallback) {
        super(steamUserCallback);
    }

    void onValidateAuthTicket(long l, int n, long l2) {
        ((SteamUserCallback)this.callback).onValidateAuthTicket(new SteamID(l), SteamAuth$AuthSessionResponse.byOrdinal(n), new SteamID(l2));
    }

    void onMicroTxnAuthorization(int n, long l, boolean bl) {
        ((SteamUserCallback)this.callback).onMicroTxnAuthorization(n, l, bl);
    }
}
