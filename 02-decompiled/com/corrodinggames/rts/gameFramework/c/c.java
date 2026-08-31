/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.c;

import com.corrodinggames.rts.gameFramework.c.a;
import com.corrodinggames.rts.gameFramework.l;

class c
implements Runnable {
    final /* synthetic */ a a;

    c(a a2) {
        this.a = a2;
    }

    @Override
    public void run() {
        l l2 = l.B();
        if (this.a.f) {
            this.a.f = false;
            return;
        }
        if (com.corrodinggames.rts.gameFramework.c.a.c) {
            if (l2.bL == null) {
                return;
            }
            l2.bN.F = true;
            if (!l2.bN.j()) {
                com.corrodinggames.rts.gameFramework.c.a.e += 1.0f;
            }
            if (com.corrodinggames.rts.gameFramework.c.a.e > 5.0f) {
                com.corrodinggames.rts.gameFramework.c.a.e = 0.0f;
                System.gc();
                System.gc();
                l2.bN.e();
            }
        }
        if (com.corrodinggames.rts.gameFramework.c.a.d && l2.bL != null) {
            l2.bL.g();
        }
    }
}
