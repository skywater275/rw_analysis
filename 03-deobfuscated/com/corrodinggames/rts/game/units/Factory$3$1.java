/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.gameFramework.GlobalState;

class Factory$3$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Factory$3 b;

    Factory$3$1(Factory$3 var1_1, String string) {
        this.b = var1_1;
        this.a = string;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.j();
        if (!bl) {
            boolean bl2 = l2.bL.tileHeight;
            Factory h2 = Factory.L();
            boolean bl3 = l2.dq;
            boolean bl4 = l2.dr;
            l2.cb.h = true;
            l2.cb.c(this.a);
            l2.cb.h = false;
            l2.dq = bl3;
            l2.dr = bl4;
            Factory h3 = Factory.L();
            if (h3 != null && h2 != null) {
                h3.a(h2);
            } else {
                GlobalState.b("Failed copySettingsFromAnotherEditor");
            }
            l2.bv = true;
            if (l2.bL != null) {
                l2.bL.tileHeight = bl2;
            }
            l2.cU = true;
            if (h3 != null) {
                h3.M();
            }
        } else {
            GlobalState.e("stopPlaybackRunnable: Already started");
        }
    }
}
