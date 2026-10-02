/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly
extends SpatialQuery {
    public PlayerState ally;
    public TeamTag tag;
    public int count;
    public float withinRangeSq;
    public boolean incompleteBuildings;


    public void setup(UnitType y2, float f) {
    }


    public int excludeTeam(UnitType y2) {
        return -1;
    }


    public PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public PlayerState onlyTeam(UnitType y2) {
        return null;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        float f3;
        if (this.ally == am2.player) {
            return;
        }
        if (!this.ally.d(am2.player)) {
            return;
        }
        com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
        if ((this.tag == null || h2 != null && TeamTag.deserializeTags(this.tag, h2)) && (f3 = GameUtils.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.withinRangeSq) {
            if (am2.cm < 1.0f && !this.incompleteBuildings) {
                return;
            }
            ++this.count;
        }
    }
}
