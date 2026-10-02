/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import java.util.Comparator;

final class ANRError$1
implements Comparator {
    final /* synthetic */ Thread a;

    ANRError$1(Thread thread) {
        this.a = thread;
    }

    public int a(Thread thread, Thread thread2) {
        if (thread == thread2) {
            return 0;
        }
        if (thread == this.a) {
            return 1;
        }
        if (thread2 == this.a) {
            return -1;
        }
        return thread2.getName().compareTo(thread.getName());
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((Thread)object, (Thread)object2);
    }
}
