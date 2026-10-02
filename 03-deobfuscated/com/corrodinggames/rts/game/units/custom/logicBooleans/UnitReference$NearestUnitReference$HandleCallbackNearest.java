/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.custom.UnitTypeComparator;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class UnitReference$NearestUnitReference$HandleCallbackNearest
extends SpatialQuery {
    public TeamTag tag;
    public TeamTag withoutTag;
    public float withinRangeSq;
    public boolean incompleteBuildings;
    public UnitTypeComparator relation;
    public UnitInstance nearest;


    public void setup(UnitType y2, float f) {
    }


    public int excludeTeam(UnitType y2) {
        return -3;
    }


    public PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public PlayerState onlyTeam(UnitType y2) {
        return null;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        float f3;
        if (this.relation != null && !y2.player.a(this.relation, am2.player)) {
            return;
        }
        if (y2 == am2) {
            return;
        }
        com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
        if ((this.tag == null || h2 != null && TeamTag.deserializeTags(this.tag, h2)) && (f3 = GameUtils.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.withinRangeSq) {
            if (am2.cm < 1.0f && !this.incompleteBuildings) {
                return;
            }
            if (this.withoutTag != null && h2 != null && TeamTag.deserializeTags(this.withoutTag, h2)) {
                return;
            }
            this.withinRangeSq = f3;
            this.nearest = am2;
        }
    }
}
