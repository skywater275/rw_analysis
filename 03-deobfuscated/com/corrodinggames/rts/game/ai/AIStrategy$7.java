/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.units.MovementType;
import com.corrodinggames.rts.game.ai.UnitBuildStrategy;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitTypeHandle;

class AIStrategy$7
extends UnitBuildStrategy {
    final /* synthetic */ AIStrategy a;

    AIStrategy$7(AIStrategy a2, String string) {
        super(a2, string);
        this.a = a2;
    }


    @Override
    public boolean isUnitTypeAllowed(UnitTypeHandle as2) {
        return AIStrategy.a(this.a, as2) && this.a(as2, MovementTypeEnum.d);
    }
}
