/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$2;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

class h$2$1
implements Runnable {
    final /* synthetic */ h.2 a;

    h$2$1(h.2 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        l l2 = l.B();
        boolean bl = l2.cb.k();
        if (!bl) {
            h h2 = h.L();
            l2.bS.e = true;
            if (!l2.bX.B) {
                long l3 = l2.bX.w;
                l2.bX.o = true;
                int n2 = l2.bX.ay.d;
                l2.bX.R();
                l2.bX.ay.d = n2;
                l2.bX.w = l3;
                l2.bX.aW = true;
                l2.bx = 0;
                l2.bX.X = l2.bx + 1;
                l2.bX.w();
            }
            String string = "[sandbox]" + l2.al() + " [v" + l2.v() + "] (" + f.a("d MMM yyyy HH.mm.ss") + ").replay";
            l2.cb.d(string);
            l2.bS.e = false;
            l.f(null, "Replay started as: " + string);
            h h3 = h.L();
            if (h3 != null && h2 != null) {
                h3.a(h2);
                h3.r = string;
            } else {
                l.b("Failed copySettingsFromAnotherEditor");
            }
        } else {
            l2.cb.e();
        }
    }
}
