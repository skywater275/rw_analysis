/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.af;
import java.util.TimerTask;

strictfp class af$1
extends TimerTask {
    final /* synthetic */ af a;

    af$1(af af2) {
        this.a = af2;
    }

    @Override
    public void run() {
        if (!this.a.d.C) {
            this.a.a();
        }
    }
}
