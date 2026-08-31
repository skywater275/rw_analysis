/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.a;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.Main;

class Main$3
implements Runnable {
    final /* synthetic */ Main a;

    Main$3(Main main) {
        this.a = main;
    }

    @Override
    public void run() {
        l l2 = l.B();
        l.e("got startGameEvent..");
        n.r();
        if (l2.bL == null || !l2.bL.W) {
            l.e("Not starting multiplayer game because map failed to load");
            l2.bX.af();
            return;
        }
        l2.bX.bd = true;
        l2.bH = false;
        l2.aq = false;
        this.a.i.c(false);
        com.corrodinggames.librocket.a.a().f();
        this.a.p.getActiveDocument();
        if (this.a.p.c != null) {
            this.a.p.c.getRoot().resumeNonMenu();
        } else {
            l.e("startGameEvent: scriptEngine==null");
            l.T();
        }
    }
}
