/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicBooleanGameFunctions$HasActiveWaypoint
extends LogicBoolean {
    av type;

    @LogicBoolean.Parameter
    public void type(String string) {
        try {
            this.type = (av)ab.a(string, null, av.class);
        }
        catch (bo bo2) {
            throw new am(bo2.getMessage(), bo2);
        }
    }

    @Override
    public boolean read(y y2) {
        boolean bl = false;
        au au2 = y2.ar();
        if (au2 != null) {
            bl = this.type == null ? true : au2.d() == this.type;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "HasActiveWaypoint(type=" + (Object)((Object)this.type) + ")";
    }
}
