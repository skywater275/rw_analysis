/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.custom.RangeValue;
import com.corrodinggames.rts.game.units.UnitType;


import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$IsResourceLargerThan
extends LogicBoolean {
    ModUnitRegistry meta;
    com.corrodinggames.rts.game.units.custom.effects.LogicBoolean source;
    com.corrodinggames.rts.game.units.custom.effects.LogicBoolean compareTarget;
    @LogicBoolean$Parameter
    public float byMoreThan = 0.0f;
    @LogicBoolean$Parameter
    public float multiplyTargetBy = 1.0f;


    public void forMeta(ModUnitRegistry l2) {
        if (l2 == null) {
            throw new BooleanParseException("IsResourceLargerThan requires metadata");
        }
        this.meta = l2;
    }

    @LogicBoolean$Parameter
    public void source(String string) {
        this.source = this.meta.j(string);
        if (this.source == null) {
            throw new BooleanParseException("Could not find custom resource type of:" + this.source);
        }
    }

    @LogicBoolean$Parameter
    public void compareTarget(String string) {
        this.compareTarget = this.meta.j(string);
        if (this.compareTarget == null) {
            throw new BooleanParseException("Could not find custom resource type of:" + this.compareTarget);
        }
    }


    public void l(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.source == null) {
            throw new BooleanParseException("Requires 'source'");
        }
        if (this.compareTarget == null) {
            throw new BooleanParseException("Requires 'compareTarget'");
        }
    }


    public boolean read(UnitType y2) {
        double d2 = this.source.a(y2);
        double d3 = this.compareTarget.a(y2);
        d3 += (double)this.byMoreThan;
        d3 *= (double)this.multiplyTargetBy;
        boolean bl = false;
        if (d2 > d3) {
            bl = true;
        }
        return bl;
    }


    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "IsResourceLargerThan(" + this.source.j() + " vs " + this.compareTarget.j() + ")";
    }
}
