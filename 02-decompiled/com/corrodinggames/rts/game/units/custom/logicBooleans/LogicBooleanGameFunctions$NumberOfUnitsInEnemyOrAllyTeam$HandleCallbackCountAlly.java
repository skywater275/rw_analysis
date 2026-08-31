/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public class LogicBooleanGameFunctions$NumberOfUnitsInEnemyOrAllyTeam$HandleCallbackCountAlly
extends i {
    public n ally;
    public g tag;
    public int count;
    public float withinRangeSq;
    public boolean incompleteBuildings;

    @Override
    public void setup(y y2, float f) {
    }

    @Override
    public int excludeTeam(y y2) {
        return -1;
    }

    @Override
    public n onlyEnemiesOfTeam(y y2) {
        return null;
    }

    @Override
    public n onlyTeam(y y2) {
        return null;
    }

    @Override
    public void callback(y y2, float f2, am am2) {
        float f3;
        if (this.ally == am2.bX) {
            return;
        }
        if (!this.ally.d(am2.bX)) {
            return;
        }
        h h2 = am2.de();
        if ((this.tag == null || h2 != null && g.a(this.tag, h2)) && (f3 = f.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.withinRangeSq) {
            if (am2.cm < 1.0f && !this.incompleteBuildings) {
                return;
            }
            ++this.count;
        }
    }
}
