/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.java.Main;

class Main$4
implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ c d;
    final /* synthetic */ Main e;

    Main$4(Main main, int n, String string, String string2, c c2) {
        this.e = main;
        this.a = n;
        this.b = string;
        this.c = string2;
        this.d = c2;
    }

    @Override
    public void run() {
        this.e.p.c.getRoot().receiveChatMessage(this.a, this.b, this.c, this.d);
    }
}
