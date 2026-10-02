/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.conditions;
import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.conditions.a;
import com.corrodinggames.rts.game.units.custom.conditions.c;
import com.corrodinggames.rts.game.units.custom.conditions.e;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;

public strictfp class f
extends SpatialQuery {
    public c curveType;
    public a startValue;
    public UnitInstance endValue;
    public float durationSeconds;


    public void setup(UnitType y2, float f2) {
    }


    public int excludeTeam(UnitType y2) {
        return -3;
    }


    public PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public PlayerState onlyTeam(UnitType y2) {
        return y2.player;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        if (y2 == am2) {
            return;
        }
        UnitConfig h2 = am2.getCustomStatusHandler();  // 02b h var4 (v19.133f5 TagFilter 幻觉修正)
        if (h2 != null && TeamTag.a(this.startValue.a, h2)) {  // 02b g.a(h,h) (v19.133f5 修正)
            e e2;
            float f3;
            if (y2.player != am2.player) {
                if (y2.player.d(am2.player)) {
                    if (!this.startValue.b) {
                        return;
                    }
                } else if (y2.player.c(am2.player)) {
                    if (!this.startValue.c) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if ((f3 = GameUtils.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.durationSeconds && ((e2 = this.curveType.a(this.startValue, false)) == null || e2.a(am2) == null)) {  // 02b a 字段 (v19.133f5 粘连修正)
                this.endValue = am2;
                this.durationSeconds = f3;
            }
        }
    }
}
