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
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$NearestUnitReference$HandleCallbackNearest;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class UnitReference$NearestUnitReference
extends UnitReference {
    public float withinRange = 500.0f;
    public float withinRangeSq = this.withinRange * this.withinRange;
    public TeamTag _withTag;
    public TeamTag _withoutTag;
    public UnitTypeComparator relation;
    @LogicBoolean$Parameter
    public boolean incompleteBuildings;
    public static final UnitReference$NearestUnitReference$HandleCallbackNearest handleCallbackNearest = new UnitReference$NearestUnitReference$HandleCallbackNearest();

    @Override
    public String getClassDebugName() {
        return "NearestUnit";
    }

    @LogicBoolean$Parameter
    public void withinRange(float f) {
        if (f > 1500.0f) {
            throw new com.corrodinggames.rts.gameFramework.utility.am("NearestUnit distance cannot be over 1500 is: " + f);
        }
        this.withinRange = f;
        this.withinRangeSq = f * f;
    }

    @LogicBoolean$Parameter
    public void withTag(String string) {
        this._withTag = TeamTag.intern(string);
    }

    @LogicBoolean$Parameter
    public void withoutTag(String string) {
        this._withoutTag = TeamTag.intern(string);
    }

    @LogicBoolean$Parameter
    public void relation(String string) {
        try {
            this.relation = (UnitTypeComparator)ab.a(string, (Enum)null, UnitTypeComparator.class);  // 02b: (q)ab.a(var1,(Enum)null,q.class)
        }
        catch (bo bo2) {
            throw new com.corrodinggames.rts.gameFramework.utility.am(bo2.getMessage(), bo2);
        }
    }

    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitReference$NearestUnitReference.handleCallbackNearest.nearest = null;
        UnitReference$NearestUnitReference.handleCallbackNearest.withinRangeSq = this.withinRangeSq;
        UnitReference$NearestUnitReference.handleCallbackNearest.tag = this._withTag;
        UnitReference$NearestUnitReference.handleCallbackNearest.withoutTag = this._withoutTag;
        UnitReference$NearestUnitReference.handleCallbackNearest.incompleteBuildings = this.incompleteBuildings;
        UnitReference$NearestUnitReference.handleCallbackNearest.relation = this.relation;
        GlobalState l2 = GlobalState.B();
        l2.cc.a(y2.eo, y2.ep, this.withinRange, y2, 0.0f, handleCallbackNearest);
        return UnitReference$NearestUnitReference.handleCallbackNearest.nearest;
    }
}
