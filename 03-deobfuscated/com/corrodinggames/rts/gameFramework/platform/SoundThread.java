/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import com.corrodinggames.rts.gameFramework.platform.AndroidSoundFactory;
import com.corrodinggames.rts.gameFramework.platform.SoundPlayRequest;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class SoundThread
extends Thread {
    final /* synthetic */ AndroidSoundFactory a;

    public SoundThread(AndroidSoundFactory a2) {
        this.a = a2;
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        try {
            while (true) {
                SoundPlayRequest c2 = (SoundPlayRequest) this.a.a.take();
                c2.a();
                this.a.c.isEnabled(c2);
            }
        }
        catch (InterruptedException interruptedException) {
            return;
        }
    }
}
