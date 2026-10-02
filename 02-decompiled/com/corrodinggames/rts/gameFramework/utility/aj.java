/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import java.util.concurrent.ConcurrentLinkedQueue;

public class aj {
    ConcurrentLinkedQueue a = new ConcurrentLinkedQueue();

    public void a(Runnable runnable) {
        this.a.add(runnable);
    }

    public void a() {
        Runnable runnable;
        while ((runnable = (Runnable)this.a.poll()) != null) {
            runnable.run();
        }
    }
}
