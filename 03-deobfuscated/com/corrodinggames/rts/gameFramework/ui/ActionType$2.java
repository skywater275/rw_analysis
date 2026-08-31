/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.HUDOverlay;
import com.corrodinggames.rts.gameFramework.ui.RateGameDialog;
import com.corrodinggames.rts.gameFramework.GlobalState;

class ActionType$2
extends HUDOverlay {
    final /* synthetic */ RateGameDialog b;

    ActionType$2(RateGameDialog f2, String string) {
        super(string);
        this.b = f2;
    }

    @Override
    void b() {
        GlobalState l2 = GlobalState.B();
        l2.dr = true;
    }
}
