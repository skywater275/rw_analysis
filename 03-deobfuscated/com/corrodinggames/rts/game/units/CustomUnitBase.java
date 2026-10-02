/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.UnitType;

public strictfp abstract class CustomUnitBase
extends UnitType {
    public CustomUnitBase(boolean bl) {
        super(bl);
    }


    public boolean I() {
        return true;
    }


    public boolean setTeamInternalById() {
        return false;
    }


    public boolean i() {
        return false;
    }


    public boolean Q() {
        return false;
    }
}
