/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;

class UIRunnable
implements Runnable {
    public String a;
    final /* synthetic */ InGameActivity b;

    UIRunnable(InGameActivity g2) {
        this.b = g2;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        l2.L();
        l2.ca.b(this.a, false);
        l2.J();
        if (this.b.e != null && this.b.e.isShowing()) {
            this.b.b(0);
        }
    }
}
