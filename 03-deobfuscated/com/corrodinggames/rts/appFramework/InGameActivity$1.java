/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.InGameActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;

class InGameActivity$1
implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ InGameActivity b;

    InGameActivity$1(InGameActivity g2, int n) {
        this.b = g2;
        this.a = n;
    }

    @Override
    public void run() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("inner selectMenuOption: " + this.a);  // 02b g$1: l.e 全限定
        this.b.d(this.a);
    }
}
