/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.InGameActivity;

class InGameActivity$8
implements Runnable {
    final /* synthetic */ InGameActivity a;

    InGameActivity$8(InGameActivity g2) {
        this.a = g2;
    }

    @Override
    public void run() {
        InGameActivity.a(this.a);  // 02b g$8: g.a(this.a) (g=InGameActivity 类名)
    }
}
