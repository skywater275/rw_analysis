/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Debug
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.utility.a;
import com.corrodinggames.rts.gameFramework.utility.d$1;
import com.corrodinggames.rts.gameFramework.utility.d$2;
import com.corrodinggames.rts.gameFramework.utility.d$3;
import com.corrodinggames.rts.gameFramework.utility.e;
import com.corrodinggames.rts.gameFramework.utility.f;

public class d
extends Thread {
    private static final e a = new d$1();
    private static final f b = new d$2();
    private e c = a;
    private f d = b;
    private final Handler e = new Handler(Looper.b());
    private final int f;
    private String g = "";
    private boolean h = false;
    private boolean i = false;
    private volatile int j = 0;
    private final Runnable k = new d$3(this);

    public d() {
        this(5000);
    }

    public d(int n) {
        this.f = n;
    }

    public d a(e e2) {
        this.c = e2 == null ? a : e2;
        return this;
    }

    @Override
    public void run() {
        this.setName("|ANR-WatchDog|");
        int n = -1;
        while (!this.isInterrupted()) {
            int n2 = this.j;
            this.e.a(this.k);
            try {
                Thread.sleep(this.f);
            }
            catch (InterruptedException interruptedException) {
                this.d.a(interruptedException);
                return;
            }
            if (this.j != n2) continue;
            if (!this.i && Debug.isDebuggerConnected()) {
                if (this.j != n) {
                    Log.c("ANRWatchdog", "An ANR was detected but ignored because the debugger is connected (you can prevent this with setIgnoreDebugger(true))");
                }
                n = this.j;
                continue;
            }
            a a2 = this.g != null ? com.corrodinggames.rts.gameFramework.utility.a.a(this.g, this.h) : com.corrodinggames.rts.gameFramework.utility.a.a();
            this.c.a(a2);
            return;
        }
    }

    static /* synthetic */ int a(d d2, int n) {
        d2.j = n;
        return d2.j;
    }

    static /* synthetic */ int a(d d2) {
        return d2.j;
    }
}
