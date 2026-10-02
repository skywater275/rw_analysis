/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.ui.panels.k;
import com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.f;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionPanel;
import com.corrodinggames.rts.gameFramework.network.NetEngine;

strictfp class IntArray$4
extends k {  // 02b ad$4 L5: f.a.k=panels.k (UI 点击回调抽象, v19.117 新建)
    final /* synthetic */ f a;
    final /* synthetic */ NetEngine b;  // 02b ad$4 L10: ad=NetEngine

    IntArray$4(NetEngine ad2, f f2) {  // 02b ad$4 L13: f.a.f=panels.f
        this.b = ad2;
        this.a = f2;
    }


    public boolean a(BuildMenuPanel c2) {  // 02b ad$4 L18: f.a.c=BuildMenuPanel  // 02b ad$4 L18: f.a.c=panels.c
        this.a.i();
        this.b.reconnectToServer();  // 02b ad L3756: V()=reconnectToServer
        return true;
    }
}
