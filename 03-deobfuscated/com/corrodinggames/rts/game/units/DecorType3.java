/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public strictfp class DecorType3
extends com.corrodinggames.rts.game.units.pathfinding.SpatialQuery {
    public int particleType;
    public float[] particleValues = new float[31];
    public boolean[] particleFlags = new boolean[31];
    int d;
    public boolean isActive;
    public boolean isLooping;

    DecorType3(boolean bl) {  // 02b ah(boolean)
        this.isActive = bl;
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public com.corrodinggames.rts.game.PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return y2.player;
    }

    public void a(UnitType y2) {
        float f2 = y2.b(false);
        this.d = y2.bl();
        for (int i2 = 0; i2 < this.d; ++i2) {
            float f3 = y2.z(i2);
            if (f3 > f2) {
                f3 = f2;
            }
            this.particleValues[i2] = f3 * f3 + 1.0f;
            this.particleFlags[i2] = false;
            if (y2.v(i2) != -1 || y2.cL[i2].targetUnit != null) continue;
            this.particleFlags[i2] = true;
        }
        this.isLooping = true;
    }


    public void setup(UnitType y2, float f2) {
        this.particleType = 0;
        if (!this.isLooping) {
            throw new RuntimeException("PassiveTargetCallback not ready");
        }
        this.isLooping = false;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        if (y2.b(am2, true)) {
            ++this.particleType;
            if (this.isActive) {
                if (!(am2 instanceof UnitType)) {
                    return;
                }
                UnitType y3 = (UnitType) am2;
                if (!y3.l() || !y3.k(y2)) {
                    return;
                }
            }
            float f3 = GameUtils.a(y2.eo, y2.ep, am2.eo, am2.ep);
            for (int i2 = 0; i2 < this.d; ++i2) {
                if (!this.particleFlags[i2]) continue;
                boolean bl = true;
                boolean bl2 = false;
                if (!y2.a(i2, am2, true, false) || !(f3 < this.particleValues[i2]) || !(f3 > y2.A(i2))) continue;
                this.particleValues[i2] = f3;
                y2.cL[i2].targetUnit = am2;
            }
        }
    }
}
