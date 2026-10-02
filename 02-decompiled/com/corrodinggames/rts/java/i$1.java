/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.i;

class i$1
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    i$1(i i2, String string) {
        this.b = i2;
        this.a = string;
    }

    @Override
    public void run() {
        l.e("slick post-alert:" + this.a);
        this.b.a.p.b("", this.a);
    }
}
