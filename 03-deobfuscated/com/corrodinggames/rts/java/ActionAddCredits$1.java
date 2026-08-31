/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.DesktopPlatform;

class ActionAddCredits$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ DesktopPlatform b;

    ActionAddCredits$1(DesktopPlatform i2, String string) {
        this.b = i2;
        this.a = string;
    }

    @Override
    public void run() {
        GlobalState.e("slick post-alert:" + this.a);
        this.b.a.p.b("", this.a);
    }
}
