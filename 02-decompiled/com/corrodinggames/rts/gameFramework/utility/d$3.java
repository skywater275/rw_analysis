/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.d;

class d$3
implements Runnable {
    final /* synthetic */ d a;

    d$3(d d2) {
        this.a = d2;
    }

    @Override
    public void run() {
        d.a(this.a, (d.a(this.a) + 1) % Integer.MAX_VALUE);
    }
}
