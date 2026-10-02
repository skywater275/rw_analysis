/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.ui.panels.k;
import com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.f;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionPanel;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;

strictfp class IntArray$5
extends k {  // 02b ad$5 L6: f.a.k=panels.k (UI 点击回调抽象, v19.117 新建)
    final /* synthetic */ f a;
    final /* synthetic */ GlobalState b;  // 02b ad$5 L11: l=GlobalState
    final /* synthetic */ NetEngine c;  // 02b ad$5 L13: ad=NetEngine

    IntArray$5(NetEngine ad2, f f2, GlobalState l2) {  // 02b ad$5 L16: f.a.f / l=GlobalState
        this.c = ad2;
        this.a = f2;
        this.b = l2;
    }


    public boolean a(BuildMenuPanel c2) {  // 02b ad$5 L22: f.a.c=BuildMenuPanel  // 02b ad$5 L22: f.a.c=panels.c
        this.a.i();
        this.b.a((Runnable)new IntArray$5$1(this));  // 02b ad$5 L24
        return true;
    }
}
