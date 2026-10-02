/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;

import network.reliableudp.ReliableSocket;

class ReliableSocket$1
extends Thread {
    final /* synthetic */ ReliableSocket a;

    ReliableSocket$1(ReliableSocket h2) {
        this.a = h2;
    }

    @Override
    public void run() {
        ReliableSocket.a(this.a).f();
        ReliableSocket.b(this.a).f();
        try {
            Thread.sleep(this.a.profile.g() * 2);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        ReliableSocket.c(this.a).f();
        ReliableSocket.d(this.a).f();
        this.a.b();
        ReliableSocket.e(this.a);
    }
}
