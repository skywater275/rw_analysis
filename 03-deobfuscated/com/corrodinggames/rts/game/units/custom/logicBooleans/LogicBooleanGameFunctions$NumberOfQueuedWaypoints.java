/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.PingTimer;

import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicBooleanGameFunctions$NumberOfQueuedWaypoints
extends LogicBoolean$AbstractNumberBoolean {
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
    public String getName() {
        return "NumberOfQueuedWaypoints";
    }

    @Override
    public float getValue(UnitType y2) {
        if (this.type == null) {
            return y2.av();
        }
        int n2 = 0;
        int n3 = y2.av();
        for (int i = 0; i < n3; ++i) {
            boolean bl;
            WeaponAction au2 = y2.k(i);
            if (au2 == null) continue;
            boolean bl2 = bl = au2.d() == this.type;
            if (!bl) continue;
            ++n2;
        }
        return n2;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
