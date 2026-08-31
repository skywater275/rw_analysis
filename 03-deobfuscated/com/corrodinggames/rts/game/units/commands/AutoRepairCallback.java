/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.UnitCategory;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public strictfp class AutoRepairCallback
extends com.corrodinggames.rts.game.units.pathfinding.SpatialQuery {
    public float repairTarget;
    public boolean repairRange;
    public boolean repairRate;
    PlayerState d;  // 02b d/s: game.n d (n=PlayerState, 非 UnitCategory)
    UnitInstance e;
    float f;
    float g;
    boolean h;

    AutoRepairCallback(boolean bl) {
        this.repairRange = bl;
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public com.corrodinggames.rts.game.PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }

    public void a(float f, boolean bl) {
        this.repairTarget = f * f;
        this.h = bl;
        this.repairRate = true;
    }


    public void setup(UnitType y2, float f) {
        this.e = null;
        this.f = -1.0f;
        this.g = -1.0f;
        this.d = y2.player;
        if (!this.repairRate) {
            throw new RuntimeException("AutoRepairCallback not ready");
        }
        this.repairRate = false;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        float f3;
        if (y2 == am2) {
            return;
        }
        if ((am2.cu < am2.cv || am2.cm < 1.0f) && !am2.isDead && am2.cN == null && this.d.d(am2.player) && y2.a(am2) && (f3 = com.corrodinggames.rts.gameFramework.GameUtils.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.repairTarget) {
            CustomActionBase b2;
            if (am2.cm < 1.0f && (b2 = y2.g(am2)) != null) {
                return;
            }
            boolean bl = false;
            if (!this.h) {
                if (this.f == -1.0f || this.f > am2.cu) {
                    bl = true;
                }
            } else if (this.g == -1.0f || this.g > f3) {
                bl = true;
            }
            if (bl && am2.bd() == 0.0f) {
                this.f = am2.cu;
                this.g = f3;
                this.e = am2;
            }
        }
    }
}

