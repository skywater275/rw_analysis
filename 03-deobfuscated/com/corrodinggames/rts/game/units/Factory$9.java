/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.Factory$9$1;
import com.corrodinggames.rts.game.units.UnitCategory;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

final class Factory$9
extends AbstractImmediateAction {
    Factory$9(String string) {
        super(string);
    }

    @Override
    public Texture j() {
        return Factory.g;
    }


    public String getDescription() {
        return "Search for units";
    }


    public String getLabel() {
        GlobalState l2 = GlobalState.B();
        Factory h2 = Factory.L();
        if (h2 != null && h2.G == UnitCategory.e) {
            return "Search: " + GameUtils.b(h2.H, 8);
        }
        return "Search units";
    }


    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (l2.cb.i()) {
            l2.c("Reply active", "Changing search filter is currently not supported while recording a replay");
            return false;
        }
        Factory$9$1 h$9$1 = new Factory$9$1(this);
        h$9$1.passwordHash = "Search units by internal name or text title.";
        h$9$1.saltValue = "Search units";
        h$9$1.algorithmName = "Search";
        h$9$1.displayLabel = "Cancel";
        NetEngine.a(h$9$1);
        return false;
    }
}
