/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.ui.panels.BuildMenuPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.f;
import com.corrodinggames.rts.gameFramework.ui.panels.ActionPanel;
import com.corrodinggames.rts.gameFramework.GlobalState;

class Factory$3$2
extends ActionPanel {
    final /* synthetic */ f a;
    final /* synthetic */ GlobalState b;
    final /* synthetic */ Runnable c;
    final /* synthetic */ Factory$3 d;

    Factory$3$2(Factory$3 var1_1, f f2, GlobalState l2, Runnable runnable) {
        this.d = var1_1;
        this.a = f2;
        this.b = l2;
        this.c = runnable;
    }

    @Override
    public boolean a(BuildMenuPanel c2) {
        this.a.i();
        this.b.a(this.c);
        return true;
    }
}
