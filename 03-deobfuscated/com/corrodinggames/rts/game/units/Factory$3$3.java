/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.gameFramework.GlobalState;

class Factory$3$3
implements Runnable {
    final /* synthetic */ Factory$3 a;

    Factory$3$3(Factory$3 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.j();
        if (!bl) {
            GlobalState.e("stopPlaybackRunnable: Already stopped");
        } else {
            l2.cb.e();
            l2.bt = 1.0f;
            l2.bv = true;
            Factory h2 = Factory.L();
            if (h2 != null) {
                l2.bs = h2.player;
            }
        }
    }
}
