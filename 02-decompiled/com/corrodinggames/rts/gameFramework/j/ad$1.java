/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.l;

strictfp final class ad$1
extends ae {
    final /* synthetic */ Object a;

    ad$1(Object object) {
        this.a = object;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(String string) {
        l l2 = l.B();
        l.e("Entered password");
        if (l2.bX.C) {
            l.a("Cannot enter a password when we are a server");
        } else {
            l2.bX.n = string;
        }
        Object object = this.a;
        synchronized (object) {
            this.a.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a() {
        Object object = this.a;
        synchronized (object) {
            this.a.notify();
        }
    }
}
