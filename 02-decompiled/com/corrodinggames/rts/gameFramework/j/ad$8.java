/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.an;
import com.corrodinggames.rts.gameFramework.l;
import java.io.IOException;

strictfp class ad$8
implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ ad b;

    ad$8(ad ad2, boolean bl) {
        this.b = ad2;
        this.a = bl;
    }

    @Override
    public void run() {
        l l2 = l.B();
        l.e("startJoinServerInternalThread callback");
        an an2 = this.b.bF;
        this.b.bF = null;
        if (an2 == null) {
            l.e("startJoinServerInternalThread callback gameConnector==null");
            return;
        }
        if (an2.e != null) {
            l.e("startJoinServerInternalThread failed to connect: " + an2.e);
            if (this.a) {
                l2.bX.b("Reconnect failed: " + an2.e);
                this.b.b("Reconnect failed", "reconnect failed");
                l2.d("Reconnect failed", "Reconnect failed: " + an2.e);
                l2.i("Reconnect failed: " + an2.e);
            }
            return;
        }
        try {
            l2.bX.b("starting new");
            l2.bX.a(an2.g);
        }
        catch (IOException iOException) {
            String string = iOException.getMessage();
            l2.c(string, "Connection failed");
            iOException.printStackTrace();
        }
    }
}
