/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.n;

final class n$2
implements Runnable {
    final /* synthetic */ n a;
    final /* synthetic */ String b;

    n$2(n n2, String string) {
        this.a = n2;
        this.b = string;
    }

    @Override
    public void run() {
        this.a.b();
        if (this.b != null) {
            // empty if block
        }
    }
}
