/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;

public strictfp class UnitBuildAction
extends AbstractBuildAction {
    UnitTypeHandle a;

    public UnitBuildAction(UnitTypeHandle as2) {
        this(as2, -999.0f);
    }

    public UnitBuildAction(UnitTypeHandle as2, float f2) {
        super("u_" + as2.v());
        UnitTypeHandle as3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(as2);
        if (as3 != null) {
            as2 = as3;
            this.getDescription("u_" + as2.v());
        }
        this.g = f2;
        this.a = as2;
    }


    @Override
    public String getDescription() {
        String string = this.a.f();
        boolean bl = false;
        boolean bl2 = true;
        string = string + "\n\n" + com.corrodinggames.rts.gameFramework.ui.ActionPanel.a(UnitInstance.c(this.a), false, bl, bl2);
        return string;
    }

    @Override
    public String getLabel() {
        return this.a.e();
    }


    @Override
    public int getResourceCost() {
        return this.B().a();
    }

    @Override
    public CustomActionBase B() {
        CustomActionBase b2 = this.h.isAffordable();
        if (b2 != null) {
            return b2;
        }
        return this.a.u();
    }

    @Override
    public CustomActionBase getSecondaryResourceComponent() {
        CustomActionBase b2 = this.h.isVisible();
        if (b2 != null) {
            return b2;
        }
        return this.a.B();
    }

    @Override
    public UnitTypeHandle i() {
        return this.a;
    }

    @Override
    public float K() {
        return this.a.D();
    }


    public ActionCategory f() {
        return ActionCategory.d;
    }


    public boolean isBuildable() {
        return !this.a.C();
    }


    public boolean g(UnitInstance am2) {
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }


    public boolean g() {
        return true;
    }
}
