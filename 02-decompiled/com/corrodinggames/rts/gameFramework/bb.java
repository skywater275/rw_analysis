/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ba;
import com.corrodinggames.rts.gameFramework.bd;
import com.corrodinggames.rts.gameFramework.l;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

strictfp class bb
implements Runnable {
    volatile boolean a = true;
    volatile int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    boolean h = false;
    public ConcurrentLinkedQueue i = new ConcurrentLinkedQueue();
    public long j = 0L;
    final /* synthetic */ ba k;

    bb(ba ba2) {
        this.k = ba2;
    }

    public synchronized void a(bd bd2) {
        if (this.h) {
            l.e("Replay:addCommand skipped due to stopped recording");
        }
        this.i.add(bd2);
        this.f = bd2.a;
        if (bd2.e != null) {
            ++this.k.A;
        }
        if (bd2.f != null) {
            ++this.k.B;
        }
        this.notifyAll();
    }

    public synchronized void a() {
        this.a = false;
        l l2 = l.B();
        ba.a("stop requested at:" + l2.bx);
        if (!ba.a(this.k)) {
            ba.a("Replay stop: warning: active==false");
        }
        if (this.k.u) {
            ba.a("Replay stop: warning: replaying==true");
        }
        this.b = l2.bx;
        this.c = l2.by;
        this.d = this.k.A;
        this.e = this.k.B;
        if (this.b < this.f) {
            l.e("Replay: stoppedFrame<lastCommandFrame: " + this.b + "<" + this.f);
            this.b = this.f;
        }
        this.j = 0L;
        this.notifyAll();
    }

    private synchronized void b() {
        try {
            if (this.a) {
                this.wait();
            }
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    @Override
    public void run() {
        l.aq();
        while (this.a) {
            if (this.i.size() > 0) {
                bd bd2 = (bd)this.i.remove();
                try {
                    if (bd2.e != null) {
                        this.k.J.e("rc");
                        this.k.J.a(bd2.a);
                        bd2.e.a(this.k.J);
                        this.k.J.a("rc");
                        this.g = bd2.a;
                    } else if (bd2.c != null) {
                        this.k.J.e("cs");
                        this.k.J.a(bd2.a);
                        this.k.J.a(bd2.c);
                        this.k.J.a("cs");
                    } else if (bd2.d != null) {
                        this.k.J.e("wait");
                        this.k.J.a(bd2.a);
                        this.k.J.a("wait");
                        this.k.J.e("es");
                        this.k.J.a(bd2.a);
                        this.k.J.a(bd2.d);
                        this.k.J.a("es");
                    } else if (bd2.f != null) {
                        this.k.J.e("wait");
                        this.k.J.a(bd2.a);
                        this.k.J.a("wait");
                        this.k.J.e("resync");
                        this.k.J.a(bd2.a);
                        this.k.J.a(bd2.h);
                        this.k.J.a(bd2.i);
                        this.k.J.a(bd2.j);
                        this.k.J.a(bd2.k);
                        this.k.J.a(bd2.f);
                        this.k.J.a("resync");
                    } else if (bd2.g != null) {
                        this.k.J.e("chat");
                        this.k.J.a(bd2.a);
                        this.k.J.a(bd2.g.a);
                        this.k.J.b(bd2.g.b);
                        this.k.J.b(bd2.g.c);
                        this.k.J.a("chat");
                    } else {
                        throw new RuntimeException("Unknown saved command");
                    }
                    if (this.j == 0L || this.j + 3000L < System.currentTimeMillis()) {
                        this.j = System.currentTimeMillis();
                        this.k.J.a();
                    }
                }
                catch (IOException iOException) {
                    l l2 = l.B();
                    l.a("Replay error", (Throwable)iOException);
                    l2.bS.h.a("", "IO error recording replay, disabling record");
                    ba.a(this.k, false);
                    this.h = true;
                    return;
                }
            }
            if (this.i.size() != 0) continue;
            this.b();
        }
        try {
            this.k.J.e("wait");
            this.k.J.a(this.b);
            this.k.J.a("wait");
            this.k.J.e("end");
            this.k.J.a("end");
            this.k.J.e("endReplayMetaData");
            this.k.J.c(0);
            this.k.J.a(this.b);
            this.k.J.a(this.c);
            this.k.J.a(this.d);
            this.k.J.a(this.e);
            this.k.J.c("{frames:" + this.b + ",time:" + this.c + ",commandCount:" + this.d + ",resyncCount:" + this.e + "}");
            this.k.J.a("endReplayMetaData");
            this.k.J.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        ba.a("Background writer stopping");
        ba.a("Remainding commands: " + this.i.size());
        ba.a("last command: " + this.f);
        ba.a("last command write: " + this.g);
        ba.a("Commands issued: " + this.d);
        this.h = true;
    }
}
