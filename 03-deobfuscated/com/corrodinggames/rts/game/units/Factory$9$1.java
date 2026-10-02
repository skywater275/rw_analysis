/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.Factory$9;
import com.corrodinggames.rts.game.units.UnitCategory;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.network.PasswordManager;
import com.corrodinggames.rts.gameFramework.GlobalState;

class Factory$9$1
extends PasswordManager {
    final /* synthetic */ Factory$9 a;

    Factory$9$1(Factory$9 var1_1) {
        this.a = var1_1;
    }

    @Override
    public void a(String string) {
        GlobalState.e("Searching for: " + string);
        GlobalState l2 = GlobalState.B();
        if (l2.cb.i()) {
            l2.c("Reply active", "Changing search filter is currently not supported while recording a replay");
            return;
        }
        Factory h2 = Factory.L();
        if (h2 == null) {
            GlobalState.e("search: No editor");
            return;
        }
        if (string == null || string.trim().equals("")) {
            GlobalState.e("search: No text entered");
            if (h2.G == UnitCategory.e) {
                h2.G = UnitCategory.a;
            }
            h2.H = null;
            h2.I = true;
            InGameUI.invalidateUI();
            return;
        }
        h2.G = UnitCategory.e;
        h2.H = string;
        h2.I = true;
        InGameUI.invalidateUI();
    }

    @Override
    public strictfp void a() {
    }
}
