/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;

import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class i {
    SteamPublishedFileID a;
    Runnable b;

    public void a(SteamResult steamResult) {
        GlobalState.b("PendingDownload onFinish for: " + this.a);
        if (this.b != null) {
            this.b.run();
        }
    }
}
