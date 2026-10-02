/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.ActionFilter;
import com.corrodinggames.rts.game.units.actions.ActionWrapper;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitCategory;

final class Factory$16
extends ActionFilter {
    Factory$16() {
    }

    @Override
    public boolean isAvailable(GameAction s2, UnitInstance am2) {
        Factory h2;
        if (s2 instanceof ActionWrapper) {
            s2 = ((ActionWrapper) s2).q_();
        }
        if ((h2 = Factory.L()) == null) {
            return true;
        }
        UnitCategory n2 = h2.G;
        if (n2 == null) {
            n2 = UnitCategory.a;
        }
        if (n2 == UnitCategory.a && Factory.a(s2, am2)) {
            return false;
        }
        if (n2 == UnitCategory.d && s2 == Factory.actionReloadUnits) {
            return true;
        }
        if (n2 == UnitCategory.d && s2 == Factory.actionReloadActive) {
            return true;
        }
        if (n2 == UnitCategory.e && s2 == Factory.y) {
            return true;
        }
        if (s2 == Factory.actionEnableTriggerDebug && !Factory.actionEnableTriggerDebug.getLabel(am2)) {
            return false;
        }
        if (s2 == Factory.actionClearSaveHistory && !Factory.actionClearSaveHistory.getLabel(am2)) {
            return false;
        }
        UnitTypeHandle as2 = s2.i();
        return n2.a(as2);
    }
}
