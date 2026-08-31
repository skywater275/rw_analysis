/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$3;
import com.corrodinggames.rts.gameFramework.l;

class h$3$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ h.3 b;

    h$3$1(h.3 var1_1, String string) {
        this.b = var1_1;
        this.a = string;
    }

    @Override
    public void run() {
        l l2 = l.B();
        boolean bl = l2.cb.j();
        if (!bl) {
            boolean bl2 = l2.bL.E;
            h h2 = h.L();
            boolean bl3 = l2.dq;
            boolean bl4 = l2.dr;
            l2.cb.h = true;
            l2.cb.c(this.a);
            l2.cb.h = false;
            l2.dq = bl3;
            l2.dr = bl4;
            h h3 = h.L();
            if (h3 != null && h2 != null) {
                h3.a(h2);
            } else {
                l.b("Failed copySettingsFromAnotherEditor");
            }
            l2.bv = true;
            if (l2.bL != null) {
                l2.bL.E = bl2;
            }
            l2.cU = true;
            if (h3 != null) {
                h3.M();
            }
        } else {
            l.e("stopPlaybackRunnable: Already started");
        }
    }
}
