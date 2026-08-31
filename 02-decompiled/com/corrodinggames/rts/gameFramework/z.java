/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Process
 */
package com.corrodinggames.rts.gameFramework;

import android.os.Process;
import com.corrodinggames.rts.gameFramework.l;

public strictfp class z
extends Thread {
    static int a = 0;
    public boolean b = true;
    long c;

    public synchronized void a(boolean bl) {
        this.b = bl;
    }

    public z() {
        super("GameThread" + a);
        ++a;
    }

    @Override
    public void run() {
        l.aq();
        if (!l.aU) {
            Process.setThreadPriority((int)-4);
        }
        float f2 = 1.0f;
        this.a();
        long l2 = this.c;
        l l3 = l.B();
        while (this.b) {
            long l4;
            long l5;
            long l6;
            long l7;
            long l8 = System.nanoTime();
            l2 = this.c;
            this.a();
            f2 = (float)(this.c - l2) * 0.060000002f;
            int n2 = (int)(this.c - l2);
            l3.a(f2, n2);
            if (!l3.bQ.batterySaving) {
                // empty if block
            }
            if ((l7 = (long)Math.round((l6 = l3.bQ.batterySaving ? 32258064L : (l3.bQ.highRefreshRate ? 3333333L : 16393442L)) - (System.nanoTime() - l8))) <= 0L) continue;
            long l9 = System.nanoTime();
            while ((l5 = System.nanoTime() - l9) <= l7 && l5 >= 0L && (l4 = (long)((double)(l7 - l5) / 1000000.0)) > 1L) {
                try {
                    Thread.sleep(l4);
                }
                catch (Exception exception) {}
            }
        }
    }

    public void a() {
        this.c = System.currentTimeMillis();
    }
}
