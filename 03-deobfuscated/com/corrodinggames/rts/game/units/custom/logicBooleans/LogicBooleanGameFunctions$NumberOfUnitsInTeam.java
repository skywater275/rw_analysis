/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$NumberOfUnitsInTeam$HandleCallbackCount;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;

public final class LogicBooleanGameFunctions$NumberOfUnitsInTeam
extends LogicBoolean$AbstractNumberBoolean {
    public TeamTag _withTag;
    @LogicBoolean$Parameter
    public float withinRange = -1.0f;
    public float withinRangeSq = -1.0f;
    @LogicBoolean$Parameter
    public boolean incompleteBuildings;
    @LogicBoolean$Parameter
    public boolean factoryQueue;
    @LogicBoolean$Parameter
    public boolean neutralTeam;
    @LogicBoolean$Parameter
    public boolean allTeams;
    public boolean useAggressiveTeamInsteadOfNeutralTeam;
    public static final LogicBooleanGameFunctions$NumberOfUnitsInTeam$HandleCallbackCount handleCallbackCount = new LogicBooleanGameFunctions$NumberOfUnitsInTeam$HandleCallbackCount();

    @LogicBoolean$Parameter
    public void aggressiveTeam(boolean bl) {
        if (bl) {
            this.neutralTeam = true;
            this.useAggressiveTeamInsteadOfNeutralTeam = true;
        }
    }

    @LogicBoolean$Parameter
    public void withTag(String string) {
        this._withTag = TeamTag.intern(string);
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.withinRange > 1000.0f) {
            throw new BooleanParseException("For CPU reasons withinRange argument cannot be over 1000 (but unlimited range is fine) in function:" + string);
        }
        if (this.withinRange > 0.0f) {
            this.withinRangeSq = this.withinRange * this.withinRange;
            if (this.factoryQueue) {
                throw new BooleanParseException("'factoryQueue' and 'withinRange' are not supported at the same time in function:" + string);
            }
        }
    }

    @Override
    public String getName() {
        return "Unit count of " + this._withTag + (this.withinRange < 0.0f ? "" : " (within range " + this.withinRange + ")");
    }

    @Override
    public float getValue(UnitType y2) {
        Object object;
        int n2;
        TeamTag g2 = this._withTag;
        PlayerState n3 = this.allTeams ? null : (this.neutralTeam ? (!this.useAggressiveTeamInsteadOfNeutralTeam ? PlayerState.i : PlayerState.h) : y2.player);
        if (n3 == null) {
            n2 = 0;
            for (PlayerState n4 : PlayerState.d()) {  // 02b L82: n[] var5 = n.d() 铁证
                if (g2 == null) {
                    n2 += n4.a(this.incompleteBuildings, this.factoryQueue);
                    continue;
                }
                n2 += n4.a(g2, this.incompleteBuildings, this.factoryQueue);
            }
        } else {
            n2 = g2 == null ? n3.a(this.incompleteBuildings, this.factoryQueue) : n3.a(g2, this.incompleteBuildings, this.factoryQueue);
        }
        if (this.withinRange < 0.0f || n2 == 0) {
            return n2;
        }
        LogicBooleanGameFunctions$NumberOfUnitsInTeam.handleCallbackCount.withinRangeSq = this.withinRangeSq;
        LogicBooleanGameFunctions$NumberOfUnitsInTeam.handleCallbackCount.count = 0;
        LogicBooleanGameFunctions$NumberOfUnitsInTeam.handleCallbackCount.tag = g2;
        LogicBooleanGameFunctions$NumberOfUnitsInTeam.handleCallbackCount.incompleteBuildings = this.incompleteBuildings;
        LogicBooleanGameFunctions$NumberOfUnitsInTeam.handleCallbackCount.targetTeam = n3;
        object = GlobalState.B();
        // 02b: l.cc.a(...) — SpatialGrid 6参 03 未映射, 简化 TODO (空间查询回调)
        return LogicBooleanGameFunctions$NumberOfUnitsInTeam.handleCallbackCount.count;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
