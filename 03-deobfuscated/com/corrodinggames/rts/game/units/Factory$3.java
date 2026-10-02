/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.Factory$3$1;
import com.corrodinggames.rts.game.units.Factory$3$2;
import com.corrodinggames.rts.game.units.Factory$3$3;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$3
extends AbstractImmediateAction {
    Factory$3(String string) {
        super(string);
    }


    public String getDescription() {
        return "Start playback of last Localization replay";
    }


    public String getLabel() {
        return "Start Playback";
    }


    public String getDisplayString() {
        String string = "Start Playback";
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.j();
        string = !bl ? "Start Playback" : "Stop Playback";
        return string;
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        boolean bl2 = l2.cb.k();
        Factory h2 = Factory.L();
        if (h2 == null) {
            return false;
        }
        return h2.r != null && !bl2;
    }


    public boolean getLabel(UnitInstance am2) {
        Factory h2 = Factory.L();
        if (h2 == null) {
            return false;
        }
        return h2.r != null;
    }


    public boolean getDescription(UnitInstance am2) {
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.j();
        return bl;
    }


    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        String string = Factory.L().r;
        if (string == null) {
            l2.i("No last replay found");
            return false;
        }
        boolean bl2 = l2.cb.j();
        if (!bl2) {
            Factory$3$1 h$3$1 = new Factory$3$1(this, string);
            com.corrodinggames.rts.gameFramework.ui.panels.f f2 = com.corrodinggames.rts.gameFramework.ui.panels.f.a("Start playback of last recording?", true);
            f2.a(Localization.a("menus.common.ok", new Object[0]), new Factory$3$2(this, f2, l2, h$3$1));
            l2.bS.a(f2);
        } else {
            Factory$3$3 h$3$3 = new Factory$3$3(this);
            l2.a(h$3$3);
        }
        return false;
    }
}
