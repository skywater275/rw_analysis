/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.m;

public class UnitReference$TransportingUnitReference
extends UnitReference {
    l meta;
    @LogicBoolean.Parameter
    public int slot = -1;

    @Override
    public void forMeta(l l2) {
        if (l2 == null) {
            throw new com.corrodinggames.rts.gameFramework.utility.am("TransportingUnitReference requires metadata");
        }
        this.meta = l2;
    }

    @Override
    public am getSingleRaw(y y2) {
        am am2 = null;
        m m2 = y2.bz();
        if (m2 != null) {
            if (this.slot == -1) {
                if (m2.size() > 0) {
                    am2 = (am)m2.get(0);
                }
            } else if (this.slot >= 0 && this.slot < m2.size()) {
                am2 = (am)m2.get(this.slot);
            }
        }
        return am2;
    }

    @Override
    public String getClassDebugName() {
        return "transporting";
    }
}
