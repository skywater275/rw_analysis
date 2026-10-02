/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import com.corrodinggames.rts.appFramework.AndroidUIHelper;
import com.corrodinggames.rts.appFramework.InGameActivity;

class InGameActivity$11
implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ InGameActivity b;

    InGameActivity$11(InGameActivity g2, Activity activity) {
        this.b = g2;
        this.a = activity;
    }

    @Override
    public void run() {
        if (c.b(this.a)) {
            InGameActivity.a(this.b, null);  // 02b g$11: g.a(var1,var2)
        }
    }
}
