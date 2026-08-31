/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.UpdateChecker;
import com.corrodinggames.rts.java.LicenseValidator;

public class ScreenshotCapture
extends Thread {
    final /* synthetic */ UpdateChecker a;

    public ScreenshotCapture(UpdateChecker o2) {
        this.a = o2;
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        try {
            while (true) {
                LicenseValidator p2 = (LicenseValidator) this.a.b.take();
                p2.a();
                this.a.c.isEnabled(p2);
            }
        }
        catch (InterruptedException interruptedException) {
            return;
        }
    }
}
