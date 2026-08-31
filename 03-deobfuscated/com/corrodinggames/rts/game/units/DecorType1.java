/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public strictfp class DecorType1
extends com.corrodinggames.rts.game.units.pathfinding.SpatialQuery {
    public float animTimer;
    public float decorType;
    public com.corrodinggames.rts.game.units.custom.UnitConfig scaleValue;  // 02b ac.c = custom.h (UnitConfig)
    public float rotationAngle;
    public UnitInstance alphaValue;
    public boolean colorTint;
    public boolean groundOffset = false;


    public void setup(UnitType y2, float f) {
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public com.corrodinggames.rts.game.PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public com.corrodinggames.rts.game.PlayerState onlyTeam(UnitType y2) {
        return null;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        if (this.colorTint && am2.bd() <= 0.0f) {
            return;
        }
        float f3 = GameUtils.a(this.animTimer, this.decorType, am2.eo, am2.ep);
        if (f3 < this.rotationAngle) {
            if (am2.cm < 1.0f && !this.groundOffset) {
                return;
            }
            if (this.scaleValue != null && !com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.scaleValue, am2.getStatusEffects())) {
                return;
            }
            if (this.colorTint && !y2.g(am2, true)) {
                return;
            }
            if (am2.cN != null) {
                return;
            }
            this.alphaValue = am2;
            this.rotationAngle = f3;
        }
    }
}
