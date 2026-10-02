/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.UnitBuildStrategy;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;

class AIStrategy$10
extends UnitBuildStrategy {
    final /* synthetic */ AIStrategy a;

    AIStrategy$10(AIStrategy a2, String string) {
        super(a2, string);
        this.a = a2;
    }


    @Override
    public boolean isUnitTypeAllowed(UnitTypeHandle as2) {
        UnitInstance am2 = UnitInstance.b(as2);
        if (this.a.g(am2)) {
            if (as2 instanceof ModUnitRegistry) {
                ModUnitRegistry l2 = (ModUnitRegistry)as2;
                if (l2.fw) {
                    return false;
                }
            }
            if (as2.o() == MovementTypeEnum.d || as2.o() == MovementTypeEnum.f || as2.o() == MovementTypeEnum.h) {
                return true;
            }
        }
        return false;
    }
}
