/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.GlobalState;

strictfp final class IntArray$1
extends PasswordManager {  // 02b ad$1 L5: j/ae=PasswordManager (class-discoveries)
    final /* synthetic */ Object a;

    IntArray$1(Object object) {
        this.a = object;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public void a(String string) {
        GlobalState l2 = GlobalState.B();  // 02b ad$1 L16: l=GlobalState
        GlobalState.e("Entered password");
        if (l2.bX.C) {
            GlobalState.a("Cannot enter a password when we are a server");
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

    public void a() {
        Object object = this.a;
        synchronized (object) {
            this.a.notify();
        }
    }
}
