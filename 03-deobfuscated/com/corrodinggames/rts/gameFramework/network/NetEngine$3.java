/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.f;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionPanel;
import com.corrodinggames.rts.gameFramework.network.NetEngine;

strictfp class NetEngine$3
extends ActionPanel {
    final /* synthetic */ com.corrodinggames.rts.gameFramework.ui.panels.f a;
    final /* synthetic */ NetEngine b;

    NetEngine$3(NetEngine ad2, f f2) {
        this.b = ad2;
        this.a = f2;
    }

    @Override
    public boolean a(com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel c2) {
        this.a.i();
        return true;
    }
}
