/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$3;
import com.corrodinggames.rts.gameFramework.l;

class h$3$3
implements Runnable {
    final /* synthetic */ h.3 a;

    h$3$3(h.3 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        l l2 = l.B();
        boolean bl = l2.cb.j();
        if (!bl) {
            l.e("stopPlaybackRunnable: Already stopped");
        } else {
            l2.cb.e();
            l2.bt = 1.0f;
            l2.bv = true;
            h h2 = h.L();
            if (h2 != null) {
                l2.bs = h2.bX;
            }
        }
    }
}
