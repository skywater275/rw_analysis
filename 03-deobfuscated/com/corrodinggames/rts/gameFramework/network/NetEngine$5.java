/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.f;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionPanel;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.NetEngine$5$1;
import com.corrodinggames.rts.gameFramework.GlobalState;

strictfp class NetEngine$5
extends ActionPanel {
    final /* synthetic */ com.corrodinggames.rts.gameFramework.ui.panels.f a;
    final /* synthetic */ GlobalState b;
    final /* synthetic */ NetEngine c;

    NetEngine$5(NetEngine ad2, f f2, GlobalState l2) {
        this.c = ad2;
        this.a = f2;
        this.b = l2;
    }

    @Override
    public boolean a(com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel c2) {
        this.a.i();
        this.b.a(new NetEngine$5$1(this));
        return true;
    }
}
