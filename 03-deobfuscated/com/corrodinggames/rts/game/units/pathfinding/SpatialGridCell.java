/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.UnitArrayList;

public class SpatialGridCell {
    public final UnitArrayList unitListA = new UnitArrayList();
    public final UnitArrayList[] unitListByCategory = new UnitArrayList[com.corrodinggames.rts.game.PlayerState.e];
    public final UnitArrayList unitListAll = new UnitArrayList();
    public final UnitArrayList unitListDynamic = new UnitArrayList();
    float e;

    public SpatialGridCell() {
        for (int i = 0; i < this.unitListByCategory.length; ++i) {
            this.unitListByCategory[i] = new UnitArrayList();
        }
    }

    public void a(UnitInstance am2) {
        this.unitListA.a(am2);
        int n2 = am2.dn;
        if (n2 >= 0) {
            this.unitListByCategory[n2].a(am2);
        } else if (n2 == -1) {
            this.unitListDynamic.a(am2);
        } else if (n2 == -2) {
            this.unitListAll.a(am2);
        }
        if (am2.cj > this.e) {
            this.e = am2.cj;
        }
    }

    public void b(UnitInstance am2) {
        this.unitListA.b(am2);
        int n2 = am2.dn;
        if (n2 >= 0) {
            this.unitListByCategory[n2].b(am2);
        } else if (n2 == -1) {
            this.unitListDynamic.b(am2);
        } else if (n2 == -2) {
            this.unitListAll.b(am2);
        }
        if (this.unitListA.b == 0) {
            this.e = 0.0f;
        }
    }
}
