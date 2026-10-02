/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.y;

public class LogicNumberFunction$CreateMarker
extends UnitReference {
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=0, required=true)
    public LogicBoolean x;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=1, required=true)
    public LogicBoolean y;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=2, required=false)
    public LogicBoolean height;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number)
    public LogicBoolean teamId;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number)
    public LogicBoolean dir;

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.height == null) {
            this.height = LogicBoolean$StaticValueBoolean.static_0;
        }
        if (this.dir == null) {
            this.dir = LogicBoolean$StaticValueBoolean.static_0;
        }
        if (this.teamId == null) {
            this.teamId = LogicBoolean$StaticValueBoolean.static_neg1;
        }
    }

    @Override
    public am getSingleRaw(y y2) {
        float f2 = this.x.readNumber(y2);
        float f3 = this.y.readNumber(y2);
        float f4 = this.height.readNumber(y2);
        float f5 = this.dir.readNumber(y2);
        n n2 = n.k((int)this.teamId.readNumber(y2));
        if (n2 == null) {
            n2 = n.i;
        }
        y y3 = n2.t;
        y3.cg = f5;
        y3.eo = f2;
        y3.ep = f3;
        y3.eq = f4;
        return y3;
    }

    @Override
    public String getClassDebugName() {
        return "createMarker";
    }
}
