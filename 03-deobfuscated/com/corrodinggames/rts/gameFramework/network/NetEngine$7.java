/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.appFramework.DialogHelper;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;

strictfp final class NetEngine$7
implements Runnable {
    final /* synthetic */ PasswordManager a;

    NetEngine$7(PasswordManager ae2) {
        this.a = ae2;
    }

    @Override
    public void run() {
        DialogHelper.a(this.a);
    }
}
