/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.h;

class h$1
extends Thread {
    final /* synthetic */ h a;

    h$1(h h2) {
        this.a = h2;
    }

    @Override
    public void run() {
        h.a(this.a).f();
        h.b(this.a).f();
        try {
            Thread.sleep(this.a.g.g() * 2);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        h.c(this.a).f();
        h.d(this.a).f();
        this.a.b();
        h.e(this.a);
    }
}
