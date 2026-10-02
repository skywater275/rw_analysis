/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ReplayEngine;
import com.corrodinggames.rts.gameFramework.DataBlock;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

strictfp class BackgroundWriter
implements Runnable {
    volatile boolean writeQueue = true;
    volatile int writerThread;
    int c;
    int d;
    int e;
    int f;
    int g;
    boolean h = false;
    public ConcurrentLinkedQueue i = new ConcurrentLinkedQueue();
    public long j = 0L;
    final /* synthetic */ ReplayEngine k;

    BackgroundWriter(ReplayEngine ba2) {
        this.k = ba2;
    }

    public synchronized void a(ReplayFrame bd2) {
        if (this.h) {
            GlobalState.e("Replay:addCommand skipped due to stopped recording");
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
        this.writeQueue = false;
        GlobalState l2 = GlobalState.B();
        ReplayEngine.a("stop requested at:" + l2.bx);
        if (!ReplayEngine.a(this.k)) {
            ReplayEngine.a("Replay stop: warning: active==false");
        }
        if (this.k.u) {
            ReplayEngine.a("Replay stop: warning: replaying==true");
        }
        this.writerThread = l2.bx;
        this.c = l2.by;
        this.d = this.k.A;
        this.e = this.k.B;
        if (this.writerThread < this.f) {
            GlobalState.e("Replay: stoppedFrame<lastCommandFrame: " + this.writerThread + "<" + this.f);
            this.writerThread = this.f;
        }
        this.j = 0L;
        this.notifyAll();
    }

    private synchronized void b() {
        try {
            if (this.writeQueue) {
                this.wait();
            }
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        while (this.writeQueue) {
            if (this.i.size() > 0) {
                ReplayFrame bd2 = (ReplayFrame) this.i.remove();
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
                    GlobalState l2 = GlobalState.B();
                    GlobalState.a("Replay error", (Throwable)iOException);
                    l2.bS.h.a("", "IO error recording replay, disabling record");
                    ReplayEngine.a(this.k, false);
                    this.h = true;
                    return;
                }
            }
            if (this.i.size() != 0) continue;
            this.b();  // 02b bb.java L154: this.b() (wait 空转)
        }
        try {
            this.k.J.e("wait");
            this.k.J.a(this.writerThread);
            this.k.J.a("wait");
            this.k.J.e("end");
            this.k.J.a("end");
            this.k.J.e("endReplayMetaData");
            this.k.J.c(0);
            this.k.J.a(this.writerThread);
            this.k.J.a(this.c);
            this.k.J.a(this.d);
            this.k.J.a(this.e);
            this.k.J.c("{frames:" + this.writerThread + ",time:" + this.c + ",commandCount:" + this.d + ",resyncCount:" + this.e + "}");
            this.k.J.a("endReplayMetaData");
            this.k.J.a();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        ReplayEngine.a("Background writer stopping");
        ReplayEngine.a("Remainding commands: " + this.i.size());
        ReplayEngine.a("last command: " + this.f);
        ReplayEngine.a("last command write: " + this.g);
        ReplayEngine.a("Commands issued: " + this.d);
        this.h = true;
    }
}
