/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.i;

class i$2
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ i c;

    i$2(i i2, String string, String string2) {
        this.c = i2;
        this.a = string;
        this.b = string2;
    }

    @Override
    public void run() {
        l.e("slick messageBox:" + this.a);
        this.c.a.p.b(this.b, this.a);
    }
}
