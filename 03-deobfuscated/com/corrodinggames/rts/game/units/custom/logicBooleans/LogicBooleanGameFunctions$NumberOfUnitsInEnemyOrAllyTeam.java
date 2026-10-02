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
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountEnemies;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;

public final class LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam
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
    public boolean ally;
    public static final LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountEnemies handleCallbackCountEnemies = new LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountEnemies();
    public static final LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly handleCallbackCountAlly = new LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly();

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
        return "Enemy Unit count of " + this._withTag + (this.withinRange < 0.0f ? "" : " (within range " + this.withinRange + ")");
    }

    @Override
    public float getValue(UnitType y2) {
        PlayerState n2 = y2.player;
        int n3 = 0;
        n3 = !this.ally ? (n3 += n2.b(this._withTag, this.incompleteBuildings, this.factoryQueue)) : (n3 += n2.c(this._withTag, this.incompleteBuildings, this.factoryQueue));
        if (this.withinRange < 0.0f || n3 == 0) {
            return n3;
        }
        if (!this.ally) {
            LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountEnemies.withinRangeSq = this.withinRangeSq;
            LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountEnemies.count = 0;
            LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountEnemies.tag = this._withTag;
            LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountEnemies.incompleteBuildings = this.incompleteBuildings;
            GlobalState l2 = GlobalState.B();
            l2.cc.a(y2.eo, y2.ep, this.withinRange, y2, 0.0f, handleCallbackCountEnemies);
            return LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountEnemies.count;
        }
        LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountAlly.withinRangeSq = this.withinRangeSq;
        LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountAlly.count = 0;
        LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountAlly.tag = this._withTag;
        LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountAlly.incompleteBuildings = this.incompleteBuildings;
        LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountAlly.ally = y2.player;
        GlobalState l3 = GlobalState.B();
        l3.cc.a(y2.eo, y2.ep, this.withinRange, y2, 0.0f, handleCallbackCountAlly);
        return LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam.handleCallbackCountAlly.count;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
