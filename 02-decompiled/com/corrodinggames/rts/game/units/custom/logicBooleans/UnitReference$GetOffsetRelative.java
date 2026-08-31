/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public class UnitReference$GetOffsetRelative
extends UnitReference$PlaceholderUnitReference {
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=0)
    public LogicBoolean x = LogicBoolean$StaticValueBoolean.static_0;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=1)
    public LogicBoolean y = LogicBoolean$StaticValueBoolean.static_0;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number)
    public LogicBoolean height = LogicBoolean$StaticValueBoolean.static_0;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number)
    public LogicBoolean dirOffset = LogicBoolean$StaticValueBoolean.static_0;

    @Override
    public String getClassDebugName() {
        return "getOffsetRelative";
    }

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }

    @Override
    public am getSingleRaw(y y2) {
        y y3 = y2.bX.t;
        y y4 = UnitReference$GetOffsetRelative.getParameterContext(y2);
        float f2 = y2.cg + this.dirOffset.readNumber(y4);
        float f3 = f.k(f2);
        float f4 = f.j(f2);
        float f5 = this.x.readNumber(y4);
        float f6 = this.y.readNumber(y4);
        float f7 = f3 * f6 - f4 * f5;
        float f8 = f4 * f6 + f3 * f5;
        y3.cg = f2;
        y3.eo = y2.eo + f7;
        y3.ep = y2.ep + f8;
        y3.eq = y2.eq + this.height.readNumber(y4);
        return y3;
    }
}
