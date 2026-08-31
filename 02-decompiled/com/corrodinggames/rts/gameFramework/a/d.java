/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.a;

import com.corrodinggames.rts.gameFramework.a.a;
import com.corrodinggames.rts.gameFramework.a.c;
import com.corrodinggames.rts.gameFramework.l;

public class d
extends Thread {
    final /* synthetic */ a a;

    public d(a a2) {
        this.a = a2;
    }

    @Override
    public void run() {
        l.aq();
        try {
            while (true) {
                c c2 = (c)this.a.a.take();
                c2.a();
                this.a.c.a(c2);
            }
        }
        catch (InterruptedException interruptedException) {
            return;
        }
    }
}
