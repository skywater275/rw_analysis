/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.DesktopPlatform;

class DesktopPlatform$2
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ DesktopPlatform c;

    DesktopPlatform$2(DesktopPlatform i2, String string, String string2) {
        this.c = i2;
        this.a = string;
        this.b = string2;
    }

    @Override
    public void run() {
        GlobalState.e("slick messageBox:" + this.a);
        this.c.a.p.b(this.b, this.a);
    }
}
