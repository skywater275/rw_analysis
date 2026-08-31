/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import a.a.c;
import com.corrodinggames.rts.gameFramework.j.ao;
import com.corrodinggames.rts.gameFramework.l;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

strictfp class ao$1
extends c {
    final /* synthetic */ ao a;

    ao$1(ao ao2) {
        this.a = ao2;
    }

    @Override
    public boolean a(SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            return this.a.a(((InetSocketAddress)socketAddress).getAddress(), false);
        }
        l.e("AcceptFilter: Unhandled SocketAddress type:" + socketAddress.getClass().getName());
        return true;
    }
}
