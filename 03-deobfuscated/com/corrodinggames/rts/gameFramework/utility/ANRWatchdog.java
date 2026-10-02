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
import com.corrodinggames.rts.gameFramework.utility.ANRError;
import com.corrodinggames.rts.gameFramework.utility.MovementPath$1;
import com.corrodinggames.rts.gameFramework.utility.MovementPath$2;
import com.corrodinggames.rts.gameFramework.utility.MovementPath$3;
import com.corrodinggames.rts.gameFramework.utility.e;
import com.corrodinggames.rts.gameFramework.utility.f;

public class ANRWatchdog
extends Thread {
    private static final e a = new ANRWatchdog$1();
    private static final f b = new ANRWatchdog$2();
    private e c = a;
    private f d = b;
    private final Handler e = new Handler(Looper.b());
    private final int f;
    private String g = "";
    private boolean h = false;
    private boolean i = false;
    private volatile int j = 0;
    private final Runnable k = new ANRWatchdog$3(this);

    public ANRWatchdog() {
        this(5000);
    }

    public ANRWatchdog(int n) {
        this.f = n;
    }

    public ANRWatchdog a(e e2) {  // 02b utility/d.java L45-53: d.a(e)
        if (e2 == null) {
            this.c = a;
        } else {
            this.c = e2;
        }
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
            ANRError a2 = this.g != null ? ANRError.a(this.g, this.h) : ANRError.a();
            this.c.a(a2);
            return;
        }
    }

    static /* synthetic */ int getint(ANRWatchdog d2, int n) {
        d2.j = n;
        return d2.j;
    }

    static /* synthetic */ int getint(ANRWatchdog d2) {
        return d2.j;
    }
}
