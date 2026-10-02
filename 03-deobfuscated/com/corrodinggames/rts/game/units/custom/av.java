/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class av
extends VariableScope$CachedWriter$WriterElement {
    public at barColorNormal;
    public LogicBoolean barColorDamaged;
    public com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator barColorCritical;

    @Override
    public void writeToUnit(UnitType y2) throws bo {
        if (!(y2 instanceof CustomUnitType)) {
            ModUnitRegistry.n("Cannot change data on non custom unit:" + y2);
            return;
        }
        CustomUnitType j2 = (CustomUnitType) y2;
        this.barColorNormal.a(j2, this.barColorDamaged, this.barColorCritical);
    }
}
