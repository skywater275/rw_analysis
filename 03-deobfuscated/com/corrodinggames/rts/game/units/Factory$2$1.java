/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.Factory$2;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;

class Factory$2$1
implements Runnable {
    final /* synthetic */ Factory$2 a;

    Factory$2$1(Factory$2 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.k();
        if (!bl) {
            Factory h2 = Factory.L();
            l2.bS.e = true;
            if (!l2.bX.B) {
                long l3 = l2.bX.w;
                l2.bX.o = true;
                int n2 = l2.bX.ay.d;
                l2.bX.processPackets();
                l2.bX.ay.d = n2;
                l2.bX.w = l3;
                l2.bX.aW = true;
                l2.bx = 0;
                l2.bX.X = l2.bx + 1;
                l2.bX.quickResync();
            }
            String string = "[sandbox]" + l2.getDisplayMapName() + " [v" + l2.v() + "] (" + GameUtils.a("d MMM yyyy HH.mm.ss") + ").replay";
            l2.cb.d(string);
            l2.bS.e = false;
            GlobalState.f(null, "Replay started as: " + string);
            Factory h3 = Factory.L();
            if (h3 != null && h2 != null) {
                h3.a(h2);
                h3.r = string;
            } else {
                GlobalState.b("Failed copySettingsFromAnotherEditor");
            }
        } else {
            l2.cb.e();
        }
    }
}
