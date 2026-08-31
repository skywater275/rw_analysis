/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.g;

class g$11
implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ g b;

    g$11(g g2, Activity activity) {
        this.b = g2;
        this.a = activity;
    }

    @Override
    public void run() {
        if (c.b(this.a)) {
            g.a(this.b, null);
        }
    }
}
