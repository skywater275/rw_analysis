/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.UnitTypeComparator;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class UnitReference$FirstUnitReference
extends UnitReference {
    public TeamTag _withTag;
    public UnitTypeComparator relation;
    @LogicBoolean$Parameter
    public boolean incompleteBuildings;

    @Override
    public String getClassDebugName() {
        return "globalSearchForFirstUnit";
    }

    @LogicBoolean$Parameter
    public void withTag(String string) {
        this._withTag = TeamTag.intern(string);
    }

    @LogicBoolean$Parameter
    public void relation(String string) {
        try {
            this.relation = (UnitTypeComparator)ab.a(string, (Enum)null, UnitTypeComparator.class);  // 02b L37: (q)ab.a(var1,(Enum)null,q.class)
        }
        catch (bo bo2) {
            throw new com.corrodinggames.rts.gameFramework.utility.am(bo2.getMessage(), bo2);
        }
    }

    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitInstance[] amArray = UnitInstance.bE.a();
        int n2 = UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (this.relation != null && !y2.player.a(this.relation, am2.player) || y2 == am2) continue;
            com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
            if (this._withTag != null && (h2 == null || !TeamTag.a(this._withTag, h2)) || am2.cm < 1.0f && !this.incompleteBuildings) continue;
            return am2;
        }
        return null;
    }
}
