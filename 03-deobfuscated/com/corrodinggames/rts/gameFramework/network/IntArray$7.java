/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;

strictfp final class IntArray$7
implements Runnable {
    final /* synthetic */ PasswordManager a;  // 02b ad$7 L8: ae=PasswordManager

    IntArray$7(PasswordManager ae2) {
        this.a = ae2;
    }

    @Override
    public void run() {
        DialogHelper.a(this.a);  // 02b ad$7 L18: appFramework/n=DialogHelper
    }
}
