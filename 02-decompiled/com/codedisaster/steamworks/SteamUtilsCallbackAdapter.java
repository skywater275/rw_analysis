/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPIWarningMessageHook;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamUtilsCallback;

class SteamUtilsCallbackAdapter
extends SteamCallbackAdapter {
    private SteamAPIWarningMessageHook messageHook;

    SteamUtilsCallbackAdapter(SteamUtilsCallback steamUtilsCallback) {
        super(steamUtilsCallback);
    }

    void setWarningMessageHook(SteamAPIWarningMessageHook steamAPIWarningMessageHook) {
        this.messageHook = steamAPIWarningMessageHook;
    }

    void onWarningMessage(int n, String string) {
        if (this.messageHook != null) {
            this.messageHook.onWarningMessage(n, string);
        }
    }

    void onSteamShutdown() {
        ((SteamUtilsCallback)this.callback).onSteamShutdown();
    }
}
