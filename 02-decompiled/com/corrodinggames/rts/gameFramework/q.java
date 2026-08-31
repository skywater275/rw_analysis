/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.utility.m;

public strictfp class q {
    m a = new m();

    public void a(Runnable runnable) {
        this.a.add(runnable);
    }

    public void a() {
        if (this.a.a > 0) {
            for (Runnable runnable : this.a) {
                runnable.run();
            }
        }
    }

    public void b() {
        if (this.a.a > 0) {
            for (Runnable runnable : this.a) {
                runnable.run();
            }
            this.a.clear();
        }
    }
}
