/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.ui.Waypoint;
import com.corrodinggames.rts.gameFramework.steam.Localization;

strictfp class BuildWaypoint
extends Waypoint {
    UnitTypeHandle a;
    int b;

    public BuildWaypoint(float f, float f2, UnitTypeHandle as2) {
        super(f, f2);
        this.a = as2;
        this.b = 1;
    }


    public boolean a(Waypoint au2) {
        if (super.a(au2) && au2 instanceof BuildWaypoint) {
            BuildWaypoint ar2 = (BuildWaypoint) au2;
            return ar2.a == this.a;
        }
        return false;
    }


    public void b(Waypoint au2) {
        this.c = au2.c;
        ++this.b;
        this.g = null;
        this.h = false;
    }


    public String a() {
        if (this.g == null) {
            String string = "gui.log.unitCreated";
            if (this.a.j()) {
                string = "gui.log.buildingConstructed";
            }
            this.g = String.format(com.corrodinggames.rts.gameFramework.steam.Localization.a(string, new Object[0]), this.a.e(), this.b);
        }
        return this.g;
    }
}
