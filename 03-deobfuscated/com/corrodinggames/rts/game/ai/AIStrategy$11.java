/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.UnitBuildStrategy;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTypeHandle;

class AIStrategy$11
extends UnitBuildStrategy {
    final /* synthetic */ AIStrategy a;

    AIStrategy$11(AIStrategy a2, String string) {
        super(a2, string);
        this.a = a2;
    }


    @Override
    public boolean isUnitTypeAllowed(UnitTypeHandle as2) {
        return this.a.transportUnits.isUnitTypeAllowed(as2) && as2.o() == MovementTypeEnum.d;
    }
}
