/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;
import com.corrodinggames.rts.java.GameWindow;

import com.codedisaster.steamworks.SteamUtilsCallback;
import com.corrodinggames.rts.java.input.SteamManager;

public class c
implements SteamUtilsCallback {
    final /* synthetic */ SteamManager a;

    public c(SteamManager b2) {
        this.a = b2;
    }

    @Override
    public void onSteamShutdown() {
    }
}
