/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public strictfp class DecorType2
extends com.corrodinggames.rts.game.units.pathfinding.SpatialQuery {
    public int animType;
    public float scaleValue;
    public boolean fadeInEnabled;
    public boolean fadeOutEnabled;

    DecorType2(boolean bl) {  // 02b ae(boolean)
        this.fadeInEnabled = bl;
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public com.corrodinggames.rts.game.PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return y2.player;
    }

    public void a(float f) {
        this.scaleValue = f * f + 1.0f;
        this.fadeOutEnabled = true;
    }


    public void setup(UnitType y2, float f) {
        this.animType = 0;
        if (!this.fadeOutEnabled) {
            throw new RuntimeException("PassiveTargetCallback not ready");
        }
        this.fadeOutEnabled = false;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        if (y2.b(am2, true)) {
            float f3;
            ++this.animType;
            if (this.fadeInEnabled) {
                if (!(am2 instanceof UnitType)) {
                    return;
                }
                UnitType y3 = (UnitType) am2;
                if (!y3.l() || !y3.k(y2)) {
                    return;
                }
            }
            if ((f3 = com.corrodinggames.rts.gameFramework.GameUtils.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.scaleValue) {
                this.scaleValue = f3;
                y2.R = am2;
            }
        }
    }
}
