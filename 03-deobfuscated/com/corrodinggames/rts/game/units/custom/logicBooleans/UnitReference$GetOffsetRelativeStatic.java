/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class UnitReference$GetOffsetRelativeStatic
extends UnitReference$PlaceholderUnitReference {
    @LogicBoolean$Parameter(positional=0)
    public float x;
    @LogicBoolean$Parameter(positional=1)
    public float y;
    @LogicBoolean$Parameter
    public float height;
    @LogicBoolean$Parameter
    public float dirOffset;

    @Override
    public String getClassDebugName() {
        return "getOffsetRelativeStatic";
    }

    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitType y3 = y2.player.t;
        float f2 = y2.cg + this.dirOffset;
        float f3 = GameUtils.k(f2);
        float f4 = GameUtils.j(f2);
        float f5 = this.x;
        float f6 = this.y;
        float f7 = f3 * f6 - f4 * f5;
        float f8 = f4 * f6 + f3 * f5;
        y3.cg = f2;
        y3.eo = y2.eo + f7;
        y3.ep = y2.ep + f8;
        y3.eq = y2.eq + this.height;
        return y3;
    }
}
