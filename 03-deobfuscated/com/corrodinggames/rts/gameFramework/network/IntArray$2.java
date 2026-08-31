/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.network.ServerStatus;

strictfp final class IntArray$2
extends ServerResult {
    final /* synthetic */ Object d;

    IntArray$2(Object object) {
        this.d = object;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(String string) {
        super.a(string);
        Object object = this.d;
        synchronized (object) {
            this.d.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(String string, ServerStatus x2, Exception exception) {
        super.a(string, x2, exception);
        Object object = this.d;
        synchronized (object) {
            this.d.notify();
        }
    }
}
