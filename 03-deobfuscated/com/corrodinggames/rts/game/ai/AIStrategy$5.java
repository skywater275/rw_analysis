/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.UnitBuildStrategy;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;

class AIStrategy$5
extends UnitBuildStrategy {
    final /* synthetic */ AIStrategy a;

    AIStrategy$5(AIStrategy a2, String string) {
        super(a2, string);
        this.a = a2;
    }


    @Override
    public boolean isUnitTypeAllowed(UnitTypeHandle as2) {
        UnitInstance am2 = UnitInstance.b(as2);
        if (am2.isFactoryBuilding()) {
            if (as2 instanceof ModUnitRegistry) {
                ModUnitRegistry l2 = (ModUnitRegistry)as2;
                if (l2.fw) {
                    return false;
                }
            }
            boolean bl2 = false;
            for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) am2.N()) {
                UnitTypeHandle as3;
                AbstractBuildAction w2;
                if (s2 == null || !(s2 instanceof AbstractBuildAction) || (w2 = (AbstractBuildAction)s2).F() || (as3 = w2.i()) == null || as3.j() || !as3.m()) continue;
                bl2 = true;
            }
            if (bl2) {
                return true;
            }
        }
        return false;
    }
}
