/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.DialogHelper;

final class DialogHelper$2
implements Runnable {
    final /* synthetic */ DialogHelper a;
    final /* synthetic */ String b;

    DialogHelper$2(DialogHelper n2, String string) {
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
