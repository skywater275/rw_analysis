/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.PingTimer;

import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicBooleanGameFunctions$HasActiveWaypoint
extends LogicBoolean {
    WeaponTypeEnum type;

    @LogicBoolean$Parameter
    public void type(String string) {
        try {
            this.type = (WeaponTypeEnum) ab.a(string, null, WeaponTypeEnum.class);
        }
        catch (bo bo2) {
            throw new am(bo2.getMessage(), bo2);
        }
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = false;
        WeaponAction au2 = y2.ar();
        if (au2 != null) {
            bl = this.type == null ? true : au2.d() == this.type;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "HasActiveWaypoint(type=" + (Object)((Object)this.type) + ")";
    }
}
