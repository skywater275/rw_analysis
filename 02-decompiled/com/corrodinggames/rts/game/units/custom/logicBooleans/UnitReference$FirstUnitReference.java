/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class UnitReference$FirstUnitReference
extends UnitReference {
    public g _withTag;
    public q relation = q.f;
    @LogicBoolean.Parameter
    public boolean incompleteBuildings;

    @Override
    public String getClassDebugName() {
        return "globalSearchForFirstUnit";
    }

    @LogicBoolean.Parameter
    public void withTag(String string) {
        this._withTag = g.c(string);
    }

    @LogicBoolean.Parameter
    public void relation(String string) {
        try {
            this.relation = (q)ab.a(string, null, q.class);
        }
        catch (bo bo2) {
            throw new com.corrodinggames.rts.gameFramework.utility.am(bo2.getMessage(), bo2);
        }
    }

    @Override
    public am getSingleRaw(y y2) {
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (this.relation != q.f && !y2.bX.a(this.relation, am2.bX) || y2 == am2) continue;
            h h2 = am2.de();
            if (this._withTag != null && (h2 == null || !g.a(this._withTag, h2)) || am2.cm < 1.0f && !this.incompleteBuildings) continue;
            return am2;
        }
        return null;
    }
}
