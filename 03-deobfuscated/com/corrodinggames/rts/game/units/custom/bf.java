/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bg;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class bf
extends SpatialQuery {
    public float colorName;
    public float colorValue;
    public bg gradientColor1;
    public int gradientColor2;


    public void setup(UnitType y2, float f) {
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public PlayerState onlyTeam(UnitType y2) {
        return null;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        float f3;
        if (y2 == am2) {
            return;
        }
        com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
        UnitConfig h3 = this.gradientColor1.c;
        if ((h3 == null || h2 != null && TeamTag.deserializeTags(h3, h2)) && (f3 = GameUtils.a(this.colorName, this.colorValue, am2.eo, am2.ep)) < this.gradientColor1.f) {
            if (am2.cm < 1.0f && this.gradientColor1.i) {
                return;
            }
            if (this.gradientColor1.j && !am2.isFactoryBuilding()) {
                return;
            }
            if (this.gradientColor1.d != null && !y2.player.a(this.gradientColor1.d, am2.player)) {
                return;
            }
            ++this.gradientColor2;
        }
    }
}
